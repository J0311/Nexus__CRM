package com.joseph.Nexus.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class Prospect {

    private int id;
    private String first_name;
    private String last_name;
    private String description;
    private String email;
    private int phone_number;
    private boolean dealClosed;



}
