package springboot.shuttle.controller;

import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springboot.shuttle.domain.LoginForm;
import springboot.shuttle.domain.Member;
import springboot.shuttle.service.MemberService;
import springboot.shuttle.web.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller //View 반환
@RequiredArgsConstructor //final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션
@Slf4j //로깅 라이브러리들을 하나의 통일된 방식으로 사용할 수 있는 방법 제공, 로깅 Facade
public class MemberController {

    @Autowired //의존성 주입
    MemberService memberService;

    //회원가입
    @GetMapping("/signUp")
    public String signUpForm(@ModelAttribute Member member){ //ModelAttribute 어노테이션이 붙은 객체를 자동으로 생성한다.
        return "members/signUpForm";
    }

    @PostMapping("/signUp")
    public String memberSave(@ModelAttribute Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "members/signUpForm";
        }// Member객체인 member로 signUpForm 이동

        memberService.memberSave(member);
        return "redirect:/";
    }

    //로그인
    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginForm loginForm) {
        return "members/loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginForm loginform, BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURL, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return "members/loginForm";
        }
        Optional<Member> byLoginId = memberService.findByLoginId(loginform.getLoginId());


        Member loginMember = memberService.login(loginform.getLoginId(), loginform.getPassword());

        //아이디, 비밀번호 오류 시
        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호를 잘못 입력했습니다.");
            return "members/loginForm";
        }

        //세션 : 일정 시간동안 같은 사용자(정확하게 브라우저)로 부터 들어오는 일련의 요구를 하나의 상태로 보고 그 상태를 일정하게 유지시키는 기술
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = httpServletRequest.getSession(true);
        //세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:" + redirectURL;
    }

    @GetMapping("/logout")
        public String logout (HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        //getSession(true) -> 세션이 있는지 확인을 함, 있다면 반환 없으면 생성
        //getSession(false) -> 세션이 있으면 그 세션을 리턴, 없으면 null 리턴
        if (session != null) {
            session.invalidate(); //설정된 세션의 값을 모두 사라지게 함
        }
        return "redirect:/";
    }
}
