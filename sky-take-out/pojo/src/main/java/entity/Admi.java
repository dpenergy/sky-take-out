package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admi {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Integer age;
    private String phoneNumber;
    private LocalDate entryDate;
}
