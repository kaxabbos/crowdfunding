package com.crowdfunding.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Type {
    COMMERCIAL("Коммерческий"),
    UN_COMMERCIAL("Некоммерческий");
    private final String name;
}

