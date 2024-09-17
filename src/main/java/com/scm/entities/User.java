package com.scm.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @Column(nullable = false)
    private String userId;
    private String name;
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(length = 1000)
    private String about;
    @Column(length = 1000)
    private String profilePic;
    private String phoneNumber;
    private boolean enabled = false;
    private boolean emailVerified = false;
    private boolean phoneVerified = false;

    //through which method user has login inside the application
    private Providers provider=Providers.SELF;
    private Providers providerUserId;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Contact> contacts=new ArrayList<>();


}
