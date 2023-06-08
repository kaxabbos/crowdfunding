package com.crowdfunding.model;

import com.crowdfunding.model.enums.ProjectStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Projects {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;
    private int price;
    private String[] photos;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ProjectsDescription description;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Statistics statistics;
    @ManyToOne(fetch = FetchType.LAZY)
    private Users owner;
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    public Projects(String name, int price, String[] photos) {
        this.name = name;
        this.price = price;
        this.photos = photos;
        status = ProjectStatus.WAITING;
    }

    public String[] photosAfter() {
        String[] res = new String[photos.length - 1];
        System.arraycopy(photos, 1, res, 0, photos.length - 1);
        return res;
    }
}
