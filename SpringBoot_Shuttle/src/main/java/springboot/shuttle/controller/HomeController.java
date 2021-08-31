package springboot.shuttle.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.shuttle.domain.Member;
import springboot.shuttle.service.MemberService;

import java.util.List;

@Controller /** 대표 - Controller 어노테이션이 적용 된 클래스는 Controller임을 나타내고 bean으로 등 **/
@RequiredArgsConstructor /** 대표 - 쉽게 생각하면 Getter, Setter 생성 허나 Getter Setter보다 좋음. **/
@Slf4j
public class HomeController {

    @Autowired
    MemberService memberService;

    @GetMapping("/")
    public String home(){
        return "index";
    }

}
