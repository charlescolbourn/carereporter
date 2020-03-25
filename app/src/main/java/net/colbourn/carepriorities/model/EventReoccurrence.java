package net.colbourn.carepriorities.model;

import java.util.List;
import net.colbourn.carepriorities.api.Reoccurrence;

public class EventReoccurrence implements Reoccurrence{

    public PeriodicityOption getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(PeriodicityOption periodicity) {
        this.periodicity = periodicity;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }



    private PeriodicityOption periodicity;

    public enum dayOfWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday };

    private int interval;



    private List<Option> options;
}
