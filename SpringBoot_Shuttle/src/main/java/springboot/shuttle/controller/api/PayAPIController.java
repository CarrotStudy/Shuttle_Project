package springboot.shuttle.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springboot.shuttle.domain.Member;
import springboot.shuttle.domain.ResponseDTO;
import springboot.shuttle.service.MemberService;
import springboot.shuttle.web.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
public class PayAPIController {

    @Autowired
    private MemberService memberService;

//    @PostMapping("/api/payments/complete")
//    public ResponseDTO<Integer> inicisPay(@RequestBody int uid){
//        log.info("uid={}",uid);
//        return new ResponseDTO<>(HttpStatus.OK.
//        value(),1);
//    }

    @PostMapping("/api/payments/complete")
    public ResponseDTO<Integer> kakaopay(@RequestBody String point, HttpServletRequest request){
        HttpSession session = request.getSession();
        Member user = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        memberService.chargePoint(Integer.parseInt(point),user.getLoginId());
        return new ResponseDTO<>(HttpStatus.OK.value(),1);
    }
}
