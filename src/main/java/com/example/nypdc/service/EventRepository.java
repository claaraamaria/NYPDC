package com.example.nypdc.service;

import com.example.nypdc.model.Event;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import org.bson.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class EventRepository {
    private static final String COLLECTION = "NYPDData";

    @Autowired
    MongoTemplate mongoTemplate;

    public EventRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public long countEventById() {
        Query query = new Query();
        return mongoTemplate.count(query, COLLECTION);
    }

    public List<Document> countEventByKY() {
        AggregateIterable<Document> findIterable = mongoTemplate.getCollection(COLLECTION)
                .aggregate(List.of(new Document("$group",
                        new Document("_id", "$KY_CD")
                                .append("count",
                                        new Document("$sum", 1L)))));

        Iterator<Document> iterator = findIterable.iterator();
        List<Document> results = new ArrayList<>();
        iterator.forEachRemaining(r -> results.add(process(r)));
        return results;
    }

    private Document process(Document r) {
        return new Document(Map.of("KY_CD", r.get("_id"), "numberOfApparition", r.get("count")));
    }

    public DeleteResult deleteEvent(String eventId) {
        return mongoTemplate.remove(query(where("CMPLNT_NUM").is(eventId)), COLLECTION);
    }

    public Document insertEvent(Event event) {
        Document document = new Document(Map.of("CMPLNT_NUM", event.getComplaintID(),
                                                "KY_CD", event.getOffenseCode()));
        return mongoTemplate.insert(document, COLLECTION);
    }

}
