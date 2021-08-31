package springboot.shuttle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.shuttle.domain.Board;
import springboot.shuttle.mapper.BoardMapper;

import java.util.Collections;
import java.util.List;

@Service /* 해당 클래스가 비지니스 로직을 담당하는 서비스 클래스임을 의미하는 어노테이션 */
public class BoardService {

    @Autowired
    private BoardMapper boardMapper;

    public boolean registerBoard(Board board) {
        int queryResult = 0;

        if (board.getBno() == null) {
            queryResult = boardMapper.insertBoard(board);
        } else {
            queryResult = boardMapper.updateBoard(board);
        }

        return (queryResult == 1) ? true : false;
    }
    /* 게시글 생성, 수정 두 가지 모두 게시글을 작성하는 로직이니 메서드를 나누지 않고 한 메서드에서 진행 */
    /* if else문은 파라미터로 받은 board의 bno 즉 글 번호가 널 값이면 글을 처음 작성한다는 의미이니 insertBoard */
    /* bno가 널 값이 아니면 이미 작성 된 글을 수정한다는 의 */
    /* insertBoard, updateBoard 둘다 리턴타입이 int이니 성공하면 1을 실패하면 0을 반환하니 1이면 true, 0이면 false */

    public Board detailBoard(Long bno) {
        return boardMapper.detailBoard(bno);
    }
    /* 하나의 게시글을 조회하는 detailBoard 메서드의 결괏값을 반환합니다. */

    public boolean deleteBoard(Long bno) {
        int queryResult = 0;

        Board board = boardMapper.detailBoard(bno);

        if (board != null) {
            queryResult = boardMapper.deleteBoard(bno);
        }

        return (queryResult == 1) ? true : false;
    }
    /* bno 글번호를 파라미터로 받아오고 Board 클래스로 선언 된 board 변수에 detailBoard 쿼리를 불러와 저장 */
    /* board에 담긴 내용이 널이 아닐 시 글이 존재한다는 의미이니 삭제 쿼리 불러와 진행 성공시 queryResult에 값 1 저장 */

    public List<Board> listBoard() {
        List<Board> boardList = Collections.emptyList();

        int boardTotalCount = boardMapper.countBoard();

        if (boardTotalCount > 0) {
            boardList = boardMapper.listBoard();
        }

        return boardList;
    }
    /* 삭제되지 않은 전체 글을 조회 */
    /* NPE (널포익) 을 방지하기 위 Collections 클래스의 emptyList메서드를 이용하여 비어있는 리스트 선언 */
    /* boardTotalCount는 countBoard를 통하여 삭제되지 않은 게시글을 카운팅 하여 저장 */

}
