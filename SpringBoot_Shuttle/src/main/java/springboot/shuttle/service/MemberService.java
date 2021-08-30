package springboot.shuttle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.shuttle.domain.Member;
import springboot.shuttle.mapper.MemberMapper;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    MemberMapper memberMapper;

    public List<Member> findAll() {
        return memberMapper.findAll();
    }
}
