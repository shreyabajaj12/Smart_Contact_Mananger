package com.scm.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Contact {
    @Id
    private String id;
    private String name;
    private String phoneNumber;
    private String address;
    private String picture;
    private String description;
    private boolean favourite=false;
    private String websiteLink;
    private String LinkedInLink;

    @OneToMany(mappedBy = "contact",cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    private List<SocialLink> links=new ArrayList<>();

    @ManyToOne
    private User user;
}
