package com.example.nypdc.controller;

import com.example.nypdc.model.Event;
import com.example.nypdc.service.EventRepository;
import org.bson.Document;
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

    @DeleteMapping("/{EventId}")
    public boolean deleteEvent(@PathVariable("EventId") String EventId) {
        return eventRepository.deleteEvent(EventId).getDeletedCount() > 0;
    }

    @PostMapping
    public Document insertEvent(@RequestBody Event event) {
        Document documentError = validate(event);
        if (documentError != null) {
            return documentError;
        } else {
            return eventRepository.insertEvent(event);
        }
    }

    private Document validate(Event event) {
        if (event.getComplaintID() != null && event.getOffenseCode() != null) {
            return null;
        }
        Document document = new Document();
        if (event.getComplaintID() == null) {
            document.put("Error CMPLNT_NUM", "CMPLNT_NUM must be not null!");
        }
        if (event.getOffenseCode() == null) {
            document.put("Error KY_CD", "KY_CD must be not null!");
        }
        return document;
    }

}
