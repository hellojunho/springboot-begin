package hello.hellospring.controller;

import com.sun.source.tree.UsesTree;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // 웹 어플리케이션에서 /hello라고 들어오면
    //@GetMapping ~ hello(Model model) 클래스를 호출해준다
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "spring!!"); // key와 value 관계
        return "hello"; // 'resources/templates/hello.html' 파일을 찾아서 렌더링함
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody   // html의 body를 의미하는 것이 아님
                    // http의 body 부분에 return으로 받는 데이티를 직접 넣어주겠다는 의미
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;     // name=spring 이면, "hello spring"
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        // get 입력 : getter 자동 생성
        // set 입력 : setter 자동 생성
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
