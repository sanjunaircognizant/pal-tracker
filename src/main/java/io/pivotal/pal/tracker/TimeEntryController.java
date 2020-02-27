package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    @Autowired
    public TimeEntryController(TimeEntryRepository timeEntryRepository){
        this.timeEntryRepository = timeEntryRepository;
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list(){
        return new ResponseEntity<List<TimeEntry>>(timeEntryRepository.list(), HttpStatus.OK);
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id){
        TimeEntry timeEntry =timeEntryRepository.find(id);
        return timeEntry ==null ?  new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.NOT_FOUND) :
        new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.OK);
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry){
        return new ResponseEntity<TimeEntry>(timeEntryRepository.create(timeEntry), HttpStatus.CREATED);
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable long id, @RequestBody TimeEntry timeEntry){
        TimeEntry oldEntry = timeEntryRepository.update(id, timeEntry);
        return oldEntry ==null ?  new ResponseEntity<TimeEntry>(oldEntry, HttpStatus.NOT_FOUND) :
                new ResponseEntity<TimeEntry>(oldEntry, HttpStatus.OK);

    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id){
        timeEntryRepository.delete(id);
        return new ResponseEntity<Object>(null, HttpStatus.NO_CONTENT);
    }



}
