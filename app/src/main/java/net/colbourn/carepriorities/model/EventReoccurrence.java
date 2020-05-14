package net.colbourn.carepriorities.model;

import java.util.Calendar;
import java.util.Date;

public enum EventReoccurrence {

    DAILY {
        public String getLabel(){ return "Daily"; }

        public Date getNextDate(Date startDate) {
            return addInterval(startDate, 1, Calendar.DAY_OF_YEAR);
        }
    },

    WEEKLY {
        public String getLabel(){ return "Weekly"; }

        public Date getNextDate(Date startDate) {
            return addInterval(startDate, 1, Calendar.WEEK_OF_YEAR);
        }
    },

    MONTHLY {
        public String getLabel(){ return "Monthly"; }

        public Date getNextDate(Date startDate) {
            return addInterval(startDate, 1, Calendar.MONTH);
        }
    },

    SINGLE_EVENT {
        public String getLabel() { return "Single event"; }

        public Date getNextDate(Date startDate) {
            return startDate;
        }
    };



    private static Date addInterval(Date startDate, int amount, int calendarInterval) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(calendarInterval, amount);
        return cal.getTime();
    }
}


//public class EventReoccurrence implements Reoccurrence{
//
//    public PeriodicityOption getPeriodicity() {
//        return periodicity;
//    }
//
//    public void setPeriodicity(PeriodicityOption periodicity) {
//        this.periodicity = periodicity;
//    }
//
//    public int getInterval() {
//        return interval;
//    }
//
//    public void setInterval(int interval) {
//        this.interval = interval;
//    }
//
//
//
//    private PeriodicityOption periodicity;
//
//    public enum dayOfWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday };
//
//    private int interval;
//
//
//
//    private List<Option> options;
//}
