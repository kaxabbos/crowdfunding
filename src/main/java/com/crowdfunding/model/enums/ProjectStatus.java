package com.crowdfunding.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProjectStatus {
    WAITING("Ожидание"),
    ACTIVE("Активный"),
    REJECT("Отказано"),
    ;
    private final String name;
}

