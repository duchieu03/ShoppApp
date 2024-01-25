package com.example.shopapp.dto.request;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    private String fullName;
    private String phoneNumber;
    private String address;
    private String password;
    private String retypePassword;
    private Date dateOfBirth;
    private Long facebookAccountId;
    private Long googleAccountId;
    private Long roleId;
}
