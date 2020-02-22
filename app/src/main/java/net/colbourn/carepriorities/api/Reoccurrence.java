package net.colbourn.carepriorities.api;

public interface Reoccurrence {

    void setInterval(int interval);

    void setPeriodicity(PeriodicityOption periodicity);

    public enum Option { RoundUpToNext, RoundDownToLast, SkipPublicHolidays };

    public enum PeriodicityOption { Hourly, Daily, Weekly, Monthly, MonthlyByDay, Yearly };
    
    int getInterval();

    PeriodicityOption getPeriodicity();
}
