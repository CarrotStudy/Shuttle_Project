package springboot.shuttle.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springboot.shuttle.domain.Member;
import springboot.shuttle.domain.PwUpdate;
import springboot.shuttle.service.MemberService;
import springboot.shuttle.web.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j

public class MyPageController {

        @Autowired
        MemberService memberService;

        @Autowired
        private PasswordEncoder passwordEncoder;

        //myPage로 이동
        @GetMapping("/myPage")
        public String myPage(Model model, HttpServletRequest request) {
            HttpSession session = request.getSession(); //세션 가져옴
            Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
            model.addAttribute("loginMember", loginMember);
            return "members/myPage";
        }


        @RequestMapping(value = "/changePW", method = RequestMethod.POST)
        @ResponseBody
        public String checkPw() throws Exception {
            return "members/changePW";
        }

        //비밀번호 변경으로 이동
        @GetMapping("/members/changePW")
        public String updateForm(@ModelAttribute PwUpdate pwUpdate, Model model, HttpServletRequest request) {
            HttpSession session = request.getSession();
            Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
            model.addAttribute("loginMember", loginMember);
            return "members/changePW";
        }

        //현재 비밀번호 확인 처리 요청
        @RequestMapping(value = "/pwCheck", method = RequestMethod.POST)
        @ResponseBody
        public String pwCheck(String password, HttpServletRequest request) throws Exception{
            HttpSession session = request.getSession();
            Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
            if (passwordEncoder.matches(password, loginMember.getPassword())) {
                log.info("success");
                return "success";
            }else{
                log.info("fail");
                return "fail";
            }
        }

        //비밀번호 변경 요청
        @PostMapping("/members/changePW")
        public String update(Model model, @ModelAttribute PwUpdate pwUpdate, BindingResult bindingResult, HttpServletRequest request) {

            if (bindingResult.hasErrors()) {
                return "members/loginForm"; //에러 시 로그인 폼으로 이동
            }
            HttpSession session = request.getSession();
            Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
            model.addAttribute("loginMember", loginMember);

            pwUpdate.setLoginId(loginMember.getLoginId());
            String encodedPassword = passwordEncoder.encode(pwUpdate.getChangePw());
            pwUpdate.setChangePw(encodedPassword);
            memberService.updatePw(pwUpdate);
            if (session != null) {
                session.invalidate();
            }
            return "index";
        }


    }
