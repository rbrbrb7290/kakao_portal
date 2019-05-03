package kr.ac.test.spring;
import org.springframework.stereotype.Component;

@Component("hello")
public class HelloImpl implements Hello{
    @Override
    public String sayHello() {
        return "Hello!!";
    }
}
