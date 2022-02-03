package plo.study.ploSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data" , "hello");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name = "name" , required = true) String name, Model model){
        model.addAttribute("name" , name);
        return "hello-template";
    };

    @GetMapping("hello-spring")
    @ResponseBody // http통신 프로토콜 body부에 내용을 직접 넣어주겠다.
    public String helloString(@RequestParam("name") String name){
        return "hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody //ResponseBody : 객체를 입력받을시 default로 json타입의 데이터를 넘겨준다.
                    //객체를 입력받을시 (기본 문자처리)viewresolver가 아닌 (기본 객체처리)MappingJackson2HttpMessageConverter가 동작
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
