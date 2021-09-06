package springboot.shuttle.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import springboot.shuttle.domain.Member;

import java.util.List;
import java.util.Optional;

@Repository //해당 클래스를 루트 컨테이너에 bean 객체로 생성, DB나 파일같은 외부 I/O 작업 처리
@Mapper //매핑파일에 기재된 SQL을 호출하기 위한 인터페이스
public interface MemberMapper {


    void memberSave(Member member);
    List<Member> findAll();
    Optional<Member> findByLoginId(String loginId);
    Member login(String id, String password);

}
