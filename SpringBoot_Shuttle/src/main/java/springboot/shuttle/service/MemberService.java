package springboot.shuttle.service;

import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springboot.shuttle.domain.Member;
import springboot.shuttle.domain.PwUpdate;
import springboot.shuttle.mapper.MemberMapper;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MemberService {

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    private PasswordEncoder passwordEncoder; //비밀번호 암호화



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

}
