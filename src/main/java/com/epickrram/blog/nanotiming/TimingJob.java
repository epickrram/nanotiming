package com.epickrram.blog.nanotiming;

import org.HdrHistogram.Histogram;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public final class TimingJob implements Runnable
{
    private static final long HIGHEST_TRACKABLE_VALUE = TimeUnit.MILLISECONDS.toNanos(10L);

    private final Histogram histogram = new Histogram(HIGHEST_TRACKABLE_VALUE, 2);

    @Override
    public void run()
    {
        long lastReportedNanos = 0L;
        final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;
        while(!Thread.currentThread().isInterrupted())
        {
            final long startNanos = System.nanoTime();
            final long endNanos = System.nanoTime();

            histogram.recordValue(Math.min(HIGHEST_TRACKABLE_VALUE, endNanos - startNanos));

            if(endNanos > lastReportedNanos + TimeUnit.SECONDS.toNanos(1L))
            {
                if(lastReportedNanos != 0)
                {
                    System.out.printf("%s avg. time between calls to System.nanoTime() %dns%n",
                            formatter.format(LocalTime.now()), (long) histogram.getMean());
                }

                lastReportedNanos = endNanos;
                histogram.reset();
            }
        }
    }
}
