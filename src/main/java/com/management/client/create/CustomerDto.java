package com.management.client.create;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CustomerDto {

    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    @Past
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public @NotBlank String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank String lastName) {
        this.lastName = lastName;
    }

    public @Past LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(@Past LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
