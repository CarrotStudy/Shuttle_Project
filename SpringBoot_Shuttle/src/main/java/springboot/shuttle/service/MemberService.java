package springboot.shuttle.service;

import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.shuttle.domain.Member;
import springboot.shuttle.mapper.MemberMapper;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MemberService {

    @Autowired
    MemberMapper memberMapper;

    public void memberSave(Member member) {memberMapper.memberSave(member);}

    public List<Member> findAll() {
        return memberMapper.findAll();
    }

    public Optional<Member> findByLoginId(String loginId) {
        return memberMapper.findAll().stream().filter(m -> m.getLoginId().equals(loginId)).findAny();
    } /** DP_ stream=for **/

    public Member login(String loginId, String password) {
        return memberMapper.findByLoginId(loginId).filter(m -> m.getPassword().equals(password)).orElse(null);
    }

    public int idCheck(String loginId) {
        return memberMapper.idCheck(loginId);
    }
}
