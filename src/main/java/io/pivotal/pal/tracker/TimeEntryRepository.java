package io.pivotal.pal.tracker;

import java.util.List;

public interface TimeEntryRepository {

    public TimeEntry create (TimeEntry timeEntry);
    public TimeEntry update (long timeEntryId, TimeEntry timeEntry);
    public TimeEntry find (long timeEntryId);
    public TimeEntry delete (long timeEntryId);
    public List list();
}
