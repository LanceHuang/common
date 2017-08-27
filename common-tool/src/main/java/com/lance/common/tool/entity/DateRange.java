package com.lance.common.tool.entity;

/**
 * @author Lance
 * @since 2017/4/15
 */
public class DateRange {
    private long start;
    private long end;
    private long interval;

    public DateRange() {
    }

    public DateRange(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public DateRange(long start, long end, long interval) {
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    public long start() {
        return start;
    }

    public long end() {
        return end;
    }

    public long interval() {
        return interval;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    @Override
    public String toString() {
        return "DateRange{" +
                "start=" + start +
                ", end=" + end +
                ", interval=" + interval +
                '}';
    }
}
