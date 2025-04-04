package com.platformcommons.DTO;

import com.platformcommons.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentProfileDTO {

    private String email;
    private String mobileNumber;
    private String fatherName;
    private String motherName;
    private List<Address> addresses;
}
