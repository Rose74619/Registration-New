package com.apiexamples.payload;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.message.Message;

@Getter
@Setter
public class RegistrationDto {
    private Long id;
    @Size(min = 3,max = 100,message = "Enter name maximum of 3 characters")
    private String name;
    @Email(message = "Enter valid email address")
    private String email;
    private String phone;
    private String message;

}