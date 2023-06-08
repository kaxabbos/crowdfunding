package com.crowdfunding.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Statistics {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Projects project;
    private int invested;

    public Statistics(Projects project) {
        this.project = project;
        this.invested = 0;
    }
}
