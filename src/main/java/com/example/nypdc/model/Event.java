package com.example.nypdc.model;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "NYPDData")
public class Event {

    @MongoId
    @NotNull
    private String Id;

    private Integer complaintID;

    private Integer offenseCode;

    public @NotNull String getId() {
        return Id;
    }

    public void setId(@NotNull String id) {
        Id = id;
    }

    public Integer getComplaintID() {
        return complaintID;
    }

    public void setComplaintID(Integer complaintID) {
        this.complaintID = complaintID;
    }

    public Integer getOffenseCode() {
        return offenseCode;
    }

    public void setOffenseCode(Integer offenseCode) {
        this.offenseCode = offenseCode;
    }

    public Event(@NotNull String id, Integer complaintID, Integer offenseCode) {
        Id = id;
        this.complaintID = complaintID;
        this.offenseCode = offenseCode;
    }
}
