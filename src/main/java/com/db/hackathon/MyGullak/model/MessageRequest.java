package com.db.hackathon.MyGullak.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MessageRequest {
    @JsonProperty("phNumber")
    private String phNumber;

    @JsonProperty("message")
    private String message;
}
