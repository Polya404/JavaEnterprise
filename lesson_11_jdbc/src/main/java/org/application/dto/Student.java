package org.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Student {
    private int id;
    private String name;
    private String phone;
    private String description;
}
