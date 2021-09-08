package springboot.shuttle.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@Slf4j
public class MemberController {

    @Autowired //의존성 주입
    MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder; //비밀번호 암호화


    //로그인
    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginForm loginForm) {
        return "members/loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginForm loginform, BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURL, HttpServletRequest httpServletRequest) {

        if (bindingResult.hasErrors()) { //form에 입력한 값에 대한 검증을 해서 에러가 발생하는 지 체크
            return "members/loginForm";
        }
        Optional<Member> byLoginId = memberService.findByLoginId(loginform.getLoginId());

        if(passwordEncoder.matches(loginform.getPassword(), byLoginId.get().getPassword())) {
            log.info("비밀번호일치");
        }

        Member loginMember = memberService.login(loginform.getLoginId(), loginform.getPassword());
        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호를 잘못 입력했습니다.");
            return "members/loginForm";
        } /** DP_ 아이디, 비밀번호 오류일 때 예외상황 **/


        //세션 : 일정 시간동안 같은 사용자(정확하게 브라우저)로 부터 들어오는 일련의 요구를 하나의 상태로 보고 그 상태를 일정하게 유지시키는 기술
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = httpServletRequest.getSession(true);
        //세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);


        return "redirect:" + redirectURL;
    }

    //회원가입
    @GetMapping("/signUp")
    public String signUpForm(@ModelAttribute Member member){
        return "members/signUpForm";
    }

    @PostMapping("/signUp")
    public String memberSave(@ModelAttribute Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "members/signUpForm";
        }
        String encodedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodedPassword); //암호화된 패스워드로 저장
        memberService.memberSave(member);
        return "redirect:/";
    }


}
