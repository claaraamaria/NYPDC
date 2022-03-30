package com.example.nypdc.controller;

import com.example.nypdc.model.Event;
import com.example.nypdc.service.EventRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
