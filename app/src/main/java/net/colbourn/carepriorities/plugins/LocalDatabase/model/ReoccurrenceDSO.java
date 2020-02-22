package net.colbourn.carepriorities.plugins.LocalDatabase.model;

import net.colbourn.carepriorities.api.Reoccurrence;
import net.colbourn.carepriorities.model.EventReoccurrence;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Transient;

@Entity
public class ReoccurrenceDSO {

    @Id
    private long id;

    private int interval;

    @Transient
    private Reoccurrence.PeriodicityOption periodicity;


    public ReoccurrenceDSO(Reoccurrence reoccurrence)
    {
        this.setInterval(reoccurrence.getInterval());
        this.periodicity = reoccurrence.getPeriodicity();
    }

    public Reoccurrence getReoccurrence()
    {
        Reoccurrence reoccurrence = new EventReoccurrence();
        reoccurrence.setInterval(this.getInterval());
        reoccurrence.setPeriodicity(this.periodicity);
        return reoccurrence;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
