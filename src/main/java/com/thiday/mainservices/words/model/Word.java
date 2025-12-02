package com.thiday.mainservices.words.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "words")
public class Word {

    @Id
    @JsonProperty("id")
    private String id;

    @Field("text")
    @JsonProperty("text")
    private String text;

    @Field("ownerId")
    @JsonProperty("ownerId")
    private String ownerId;

    @Field("createdAt")
    @JsonProperty("createdAt")
    private String createdAt;
}