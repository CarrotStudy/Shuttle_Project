package springboot.shuttle.service;

import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springboot.shuttle.domain.Member;
import springboot.shuttle.domain.PwUpdate;
import springboot.shuttle.mapper.MemberMapper;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
public class MemberService {

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    private PasswordEncoder passwordEncoder; //비밀번호 암호화

    @Autowired
    private JavaMailSender sender;
    @Value("${spring.mail.username}")
    private String setMail;

    public void memberSave(Member member) {memberMapper.memberSave(member);}

    public void updatePw(PwUpdate pwUpdate) {memberMapper.updatePw(pwUpdate);}

    public List<Member> findAll() {
        return memberMapper.findAll();
    }

    public Optional<Member> findByLoginId(String loginId) {
        return memberMapper.findAll().stream().filter(m -> m.getLoginId().equals(loginId)).findAny();
    }

    public Member login(String loginId, String password) {
        return memberMapper.findByLoginId(loginId).filter(m -> passwordEncoder.matches(password, m.getPassword())).orElse(null);
    } //회원가입 후 암호화된 비밀번호를 가입 시 입력했던 비밀번호를 가져와 로그인

    public int idCheck(String loginId) {
        return memberMapper.idCheck(loginId);
    }

    public void chargePoint(int point, String loginId){
        Member member = memberMapper.findByLoginId(loginId).orElseThrow();
        member.setPoint(member.getPoint() + point);
        memberMapper.chargePoint(member);
    }

    public String mailSender(String email){

        Random random = new Random();
        int mailNum = random.nextInt(888888) + 111111;

        String setFrom = setMail;
        String toMail = email;
        String title = "회원가입 인증 이메일 입니다.";
        String content =
                "홈페이지를 방문해주셔서 감사합니다." +
                        "<br><br>" +
                        "인증 번호는 " + mailNum + "입니다." +
                        "<br>" +
                        "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
        //보낼 이메일의 내용

        /** 이메일 전송을 위한 코드 **/
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content,true);
            sender.send(message);
        }catch(Exception e) {
            e.printStackTrace();
        }

        return Integer.toString(mailNum);
    }
}
