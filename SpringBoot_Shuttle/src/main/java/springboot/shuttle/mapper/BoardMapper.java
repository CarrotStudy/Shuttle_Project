package springboot.shuttle.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.shuttle.domain.Board;

import java.util.List;

@Mapper
public interface BoardMapper {
    /* 기존엔 DAO(Data Access Object) 클래스에 @Repository 어노테이션을 선언하여 해당 클래스가 데이터베이스와 통신하는 클래스임을 나타냄 */
    /* mybatis는 @Mapper 어노테이션으로 XML Mapperd에서 메서드 이름과 일치하는 SQL문을 찾아 실행함 즉 이 mapper클래스는 SQL문을 호출하는것이 전부이며 다른 로직 x */

    int insertBoard(Board board); /* 게시글을 생성하는 insert 쿼리를 호출하는 메서드 Board 클래스로 선언된 board 변수에 게시글의 정보가 담김 */

    Board detailBoard(Long bno); /* 하나의 게시글을 조회하는 즉 상세보기 SQL문을 호출하는 메서드, 리턴타입으로 Board 클래스이며 매핑 됨, 파라미터로 게시글 번호를 받아 글을 찾아 조회 */

    int insertImage(Board board); /* 사진 썸네일 */

    int updateBoard(Board board); /* 게시글을 수정하는 SQL문을 호출하는 메서드, board 변수에 게시글의 정보가 담김 */

    int deleteBoard(Long bno); /* 게시글을 삭제하는 SQL문을 호출하는 메서드, 파라미터로 게시글 번호를 줘서 번호로 구분하여 삭제 */

    List<Board> listBoard(Board board); /* 게시글 목록을 조회하는 SQL문을 호출하는 메서드, <>안에 타입을 파라미터로 갖는 형태를 제네릭 타입이라고 함, 리스트 안에 하나의 글을 조회하는 detailBoard 메서드를 여러개 호출하여 저장 한 것과 유사 */

    int countBoard(Board board); /* 개시글의 개수를 조회하는 SQL문을 호출하는 메서드, 페이징 처리 할 때 사용 */

    List<String> getAddress(); /* 주소 가져오기 */

    boolean cntPlus(Long bno); /* 조회수 */

    String findByBoardName(Long bno);

    String findByWriter(Long bno);
}
