package com.etsy.statsd.profiler;

import com.etsy.statsd.profiler.reporter.Reporter;

import java.util.concurrent.TimeUnit;

/**
 * Interface for profilers
 *
 * @author Andrew Johnson
 */
public abstract class Profiler {
    private Reporter reporter;

    public Profiler(Reporter reporter) {
        this.reporter = reporter;
    }

    /**
     * Perform profiling
     */
    public abstract void profile();

    /**
     * Hook to flush any remaining data cached by the profiler at JVM shutdown
     */
    public abstract void flushData();

    /**
     * Get the period to use for this profiler in the ScheduledExecutorService
     *
     * @return The ScheduledExecutorThread period for this profiler
     */
    public abstract long getPeriod();

    /**
     * Get the unit of time that corresponds to the period for this profiler
     *
     * @return A TimeUnit corresponding the the period for this profiler
     */
    public abstract TimeUnit getTimeUnit();

    /**
     * Record a gauge value
     *
     * @param key The key for the gauge
     * @param value The value of the gauge
     */
    protected void recordGaugeValue(String key, long value) {
        reporter.recordGaugeValue(key, value);
    }
}
