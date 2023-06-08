package com.crowdfunding.model;

import com.crowdfunding.model.enums.Scope;
import com.crowdfunding.model.enums.Type;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProjectsDescription {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Scope scope;
    @Enumerated(EnumType.STRING)
    private Type type;
    private int quantity;
    private String description;
    private int collected;

    public ProjectsDescription(Scope scope, Type type, int quantity, String description, int collected) {
        this.scope = scope;
        this.type = type;
        this.quantity = quantity;
        this.description = description;
        this.collected = collected;
    }
}
