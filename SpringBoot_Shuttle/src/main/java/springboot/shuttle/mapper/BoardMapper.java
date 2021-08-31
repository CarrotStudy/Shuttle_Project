package springboot.shuttle.mapper;


import org.apache.ibatis.annotations.Mapper;
import springboot.shuttle.domain.Board;

/** 대표 - 기존 스프링은 @Repository를 선언해서 해당 클래스가 데이터베이스와 통신하는 클래스임을 나타냈지만 마이바티스는 @Mapper만 지정하면
    XMl Mapper에서 메서드 이름과 일치하는 SQL문을 찾아 실행함.Mapper 영역은 데이터베이스와의 통신, 즉 SQL 쿼리를 호출하는 것이 전부이며,
 다른 로직은 전혀 필요하지 않음. MVC **/
@Mapper
public interface BoardMapper {
    int insertBoard(Board board); /** 게시글 생성 insert 쿼리 호출 , board에는 게시글 정보 담겨있음 **/
}
