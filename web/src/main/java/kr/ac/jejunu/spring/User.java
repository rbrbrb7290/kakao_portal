package kr.ac.jejunu.spring;

import lombok.Builder;
import lombok.Data;

@Data//getter setter hash ToString 까지 만들어줌
@Builder
public class User {
    private Integer id;
    private String name;
    private String password;
}
