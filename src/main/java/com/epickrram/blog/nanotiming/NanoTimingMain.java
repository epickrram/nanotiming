package com.epickrram.blog.nanotiming;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public final class NanoTimingMain
{
    public static void main(final String[] args) throws Exception
    {
        if (args.length == 0)
        {
            throw new IllegalArgumentException("Please specify number of contending threads (0+)");
        }

        fireAway(Integer.parseInt(args[0]));
    }

    private static void fireAway(final int numberOfContendingThreads) throws InterruptedException
    {
        System.out.printf("Measuring time to invoke System.nanoTime() with %d contending threads.%n", numberOfContendingThreads);
        System.out.printf("Available logical CPUs on this machine: %d%n", Runtime.getRuntime().availableProcessors());

        final ExecutorService executorService = Executors.newFixedThreadPool(numberOfContendingThreads + 1);

        for(int i = 0; i < numberOfContendingThreads; i++)
        {
            executorService.submit(new ContendingJob());
        }
        executorService.submit(new TimingJob());


        Thread.sleep(60000L);

        executorService.shutdown();
        if(!executorService.awaitTermination(1L, TimeUnit.SECONDS))
        {
            throw new IllegalStateException("Executor did not shut down in a timely fashion");
        }
    }
}