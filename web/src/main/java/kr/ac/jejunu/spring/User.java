package kr.ac.jejunu.spring;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//getter setter hash ToString 까지 만들어줌
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String password;
}
