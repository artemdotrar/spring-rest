package by.tms.springrest.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class Address {

    @NotEmpty
    @NotBlank
    private String city;

    @NotEmpty
    @NotBlank
    private String street;

    @NotEmpty
    @NotBlank
    private String num_house;
}
