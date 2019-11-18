package com.github.irgalin.plannerbot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Document
@RequiredArgsConstructor
public class User {

    @Id
    @NonNull
    private Integer id;
    private Long chatId;
    private Integer stateId;
    private Boolean isBot;
    @NonNull
    private String firstName;
    private String userName;
    private String lastName;
    private Boolean admin;

}
