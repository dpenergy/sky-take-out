package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admi {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private LocalDate birthday;
    private Integer status;
    private String phoneNumber;
    private LocalDate entryDate;
    private LocalDateTime updateTime;
}
