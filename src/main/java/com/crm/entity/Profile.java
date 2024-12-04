package com.crm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "bio", nullable = false)
    private String bio;

    @Column(name = "profile_picture", nullable = false)
    private String profilePicture;

    @OneToOne(mappedBy = "profile", orphanRemoval = true)
    private User user;

}