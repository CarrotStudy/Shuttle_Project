package springboot.shuttle.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springboot.shuttle.domain.Member;

import java.util.List;

@Repository
@Mapper
public interface MemberMapper {

    /** public 생략 ? 대표 **/
    List<Member> findAll();

}
