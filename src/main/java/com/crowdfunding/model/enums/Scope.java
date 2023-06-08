package com.crowdfunding.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Scope {
    STATE_PROJECT("Госпроект"),
    ECONOMY("Экономика"),
    BUSINESS("Бизнес"),
    TECHNOLOGY("Технология"),
    IT("ИТ"),
    MEDICINE("Медицина");
    private final String name;
}

