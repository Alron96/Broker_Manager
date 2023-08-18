package com.broker_manager.to;

import com.broker_manager.util.validation.NoHtml;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserTo {

    private Integer id;

    @NotBlank(message = "Full name cannot be empty")
    @Size(max = 256)
    @NoHtml
    private String fullName;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email is not correct")
    @Size(max = 128)
    private String email;

    @NotBlank(message = "PhoneNumber cannot be empty")
    private String phoneNumber;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, max = 128)
    private String password;
}