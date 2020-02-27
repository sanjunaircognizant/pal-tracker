package io.pivotal.pal.tracker;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryTimeEntryRepository implements  TimeEntryRepository{

    private HashMap<Long, TimeEntry> items= new HashMap<Long, TimeEntry>();
    private long timeId=0;
    public TimeEntry create(TimeEntry timeEntry) {
        long id= ++timeId;
        timeEntry.setId(id);
        items.put(id, timeEntry);
        return find(id);
    }

    public TimeEntry update(long timeEntryId, TimeEntry timeEntry) {

        if(null == find(timeEntryId)){
            return null;
        }
        timeEntry.setId(timeEntryId);
        items.put(timeEntryId, timeEntry);
        return timeEntry;
    }

    public TimeEntry find(long timeEntryId) {
        return items.get(timeEntryId);
    }

    public void delete(long timeEntryId) {
        TimeEntry entry = items.get(timeEntryId);
        items.remove(timeEntryId);
    }

    public List list() {
        return items.entrySet().stream().map((e)-> e.getValue()).collect(Collectors.toList());
    }
}
