package com.scm.forms;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserForm {
    private String name;
    private String email;
    private String password;
    private String description;
    private String phoneNumber;
}
