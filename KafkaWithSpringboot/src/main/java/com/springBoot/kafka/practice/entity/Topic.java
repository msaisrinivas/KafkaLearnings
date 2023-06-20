package com.springBoot.kafka.practice.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Topic {
    private int id;

    private String message;

}
