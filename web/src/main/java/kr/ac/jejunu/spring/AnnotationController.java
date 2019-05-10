package kr.ac.jejunu.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
// step#1: /annotation이라는 url요청을 보냄 -> dispatcher-servlet
@RequestMapping//("/annotation") //annotation class가 빠져도 hello메소드가 실행가능
public class AnnotationController {
    @RequestMapping("/hello")
    public ModelAndView hello(){
        ModelAndView modelAndView = new ModelAndView("helloworld");
        modelAndView.addObject("hello", "annotation hi");
        return modelAndView;
    }

}
