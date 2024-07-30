package com.example.lab6.Employee;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor

public class Employe {
    @NotEmpty(message = "id can not be Empty")
    @Size(min = 3,max = 11,message = "id should be between 3-11")
    private String id;
    @NotEmpty(message = "name can not be Empty")
    @Size(min = 3,max = 30,message = "name should be between 3-30")
    @Pattern(regexp="^[a-zA-Z0-9 ]+$",message="name must be alphanumeric only")
    private String name;
    @Email
    private String email;
    @Pattern(regexp = "/^(009665|9665|\\+9665|05|5)(5|0|3|6|4|9|1|8|7)([0-9]{7})$/")
    private String phoneNumber;
   @NotNull(message = "AGE CAN NOT BE EMPTY")
   @Pattern(regexp = "Pattern.compile(\"^\\\\d+$\")")
   @Min(26)
   @Max(100)
    private int age;
   @NotEmpty(message = " Position can not be empty ")
   @Pattern(regexp = "^(supervisor|coordinator)$")
   private String Position;
@AssertFalse(message = "false")
private boolean onLeave;
   @NotEmpty (message ="hire date can not be empty" )
//   @Pattern(regexp = "^(19[0-9][0-9]|20[0-2][0-4])-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$")
   @PastOrPresent
   private LocalDate hireDate;
    @NotNull (message ="AnnualLeave  can not be empty" )
    @Pattern(regexp = "[1-9][0-9]*")
   private int AnnualLeave;




}
