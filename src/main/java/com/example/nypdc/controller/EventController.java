package com.example.nypdc.controller;

import com.example.nypdc.service.EventRepository;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/dataset")
@RestController
public class EventController {

    private final EventRepository eventRepository;

    @Autowired
    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("/stats/total")
    public long getNumberAllEvents() {
        return eventRepository.countEventById();
    }

    @GetMapping("/stats/offenses")
    public List<Document> getNumberAllEventsGrouped() {
        return eventRepository.countEventByKY();
    }

    @DeleteMapping( "/{EventId}")
    public void deleteEvent(@PathVariable("EventId") String EventId) {
        eventRepository.deleteEvent(EventId);
    }

}
