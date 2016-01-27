package com.epickrram.blog.nanotiming;

public final class ContendingJob implements Runnable
{
    public volatile long lastReportedNanos;
    public volatile int loopCount = 5000;

    @Override
    public void run()
    {
        while(!Thread.currentThread().isInterrupted())
        {
            int counter = loopCount;
            while(--counter != 0 && loopCount != 0)
            {
                // spin
            }

            lastReportedNanos = System.nanoTime();
        }
    }
}
