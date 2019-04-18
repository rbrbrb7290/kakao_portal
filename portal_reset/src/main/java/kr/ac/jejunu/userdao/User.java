package kr.ac.jejunu.userdao;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class User {
    private Long id;
    private String name;
    private String password;
}