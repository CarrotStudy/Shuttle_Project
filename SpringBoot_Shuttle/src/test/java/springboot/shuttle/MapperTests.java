package springboot.shuttle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;
import springboot.shuttle.domain.Board;
import springboot.shuttle.mapper.BoardMapper;

import java.util.List;

@SpringBootTest
class MapperTests {

    @Autowired
    private BoardMapper boardMapper;

    @Test
    public void testOfInsert() {
        Board board = new Board();
        board.setTitle("1번 게시글 제목");
        board.setContent("1번 게시글 내용");
        board.setWriter("테스터");

        int result = boardMapper.insertBoard(board);
        System.out.println("결과는 " + result + "입니다.");
    }

    @Test
    public void testOfSelectDetail() {
        Board board = boardMapper.detailBoard((long) 1);
        try {
            String boardJson = new ObjectMapper().writeValueAsString(board);

            System.out.println("=========================");
            System.out.println(boardJson);
            System.out.println("=========================");

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOfDelete() {
        int result = boardMapper.deleteBoard((long) 1);
        if (result == 1) {
            Board board = boardMapper.detailBoard((long) 1);
            try {
                String boardJson = new ObjectMapper().writeValueAsString(board);

                System.out.println("=========================");
                System.out.println(boardJson);
                System.out.println("=========================");

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testMultipleInsert() {
        for (int i = 2; i <= 50; i++) {
            Board board = new Board();
            board.setTitle(i + "번 게시글 제목");
            board.setContent(i + "번 게시글 내용");
            board.setWriter(i + "번 게시글 작성자");
            boardMapper.insertBoard(board);
        }
    }

    @Test
    public void testSelectList() {
        int boardTotalCount = boardMapper.countBoard();
        if (boardTotalCount > 0) {
            List<Board> boardList = boardMapper.listBoard();
            if (CollectionUtils.isEmpty(boardList) == false) {
                for (Board board : boardList) {
                    System.out.println("=========================");
                    System.out.println(board.getTitle());
                    System.out.println(board.getContent());
                    System.out.println(board.getWriter());
                    System.out.println("=========================");
                }
            }
        }
    }
}