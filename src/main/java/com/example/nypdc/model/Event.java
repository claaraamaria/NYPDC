package com.example.nypdc.model;

public class Event {

  //  @JsonProperty("CMPLNT_NUM")
    private String complaintID;

  //  @JsonProperty("KY_CD")
    private String offenseCode;

    public String getComplaintID() {
        return complaintID;
    }

    public void setComplaintID(String complaintID) {
        this.complaintID = complaintID;
    }

    public String getOffenseCode() {
        return offenseCode;
    }

    public void setOffenseCode(String offenseCode) {
        this.offenseCode = offenseCode;
    }

    @Override
    public String toString() {
        return "Event{" +
                "complaintID='" + complaintID + '\'' +
                ", offenseCode='" + offenseCode + '\'' +
                '}';
    }
}
