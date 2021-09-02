package springboot.shuttle.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springboot.shuttle.domain.Board;
import springboot.shuttle.service.BoardService;

import java.util.List;

/* GET은 데이터의 조회를 의미, POST는 데이터의 생성을 의미*/
/* 예시로 select 쿼리 같은 경우에는 GET, insert update 쿼리 같은 경우에는 POST */

@Controller /* 해당 어노테이션이 사용자의 요청과 응답을 기다리는 즉 UI를 담당하는 Controller 클래스임을 의미한다. */
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    @Autowired
    private BoardService boardService;

    /* String 리턴 타입으로 리턴되는 html의 경로의 끝에는 접미사로 .html을 자동으로 연결 */
    /* 메서드의 파라미터로 저장되는 Model 인터페이스는 데이터를 뷰로 전달하는데 사용 */

    @GetMapping("/board/list")
    public String boardListForm(Model model) {

        List<Board> boardList = boardService.listBoard();
        model.addAttribute("boardList", boardList);

        return "/board/boardList";
    }
    /* boardServcie.listBoard() 로 board list들을 가져와 boardList 변수에 저장하여 model.addAttribute로 뷰로 전달 */

    @GetMapping("/board/list1")
    public String boardListForm1(Model model) {

        List<Board> boardList = boardService.listBoard();
        log.info("여기 : " + boardList);
        model.addAttribute("boardList", boardList);

        return "/board/boardList1";
    }
    /* boardServcie.listBoard() 로 board list들을 가져와 boardList 변수에 저장하여 model.addAttribute로 뷰로 전달 */

    @GetMapping("/board/add")
    public String boardAddForm(@RequestParam(value = "bno", required = false) Long bno, Model model) {

        if (bno == null) {
            model.addAttribute("board", new Board());
        } else {
            Board board = boardService.detailBoard(bno);
            if (board == null) {
                return "redirect:/board/list.do";
            }
            model.addAttribute("board", board);
        }

        return "/board/boardAdd";
    }
    /* @RequestParam은 뷰에서 전달받은 파라미터를 처리하는데 사용 예를들어 게시글 리스트 페이지에서 등록 페이지로 이동하면 bno(글 번호)는 null로 보내짐 */
    /* 그러나 게시글 수정하기 버튼을 클릭하면 bno(글 번호)가 전달되고 전달 */
    /* 위에서 말했듯이 항상 bno가 필요하진 않으니 기본 값을 false로 해서 전달 만약 false로 지정하지 않으면 전달 값이 null 일 때 오류가 남 */

    @PostMapping("/board/add")
    public String boardAdd(final Board board) {
        try {
            boolean isRegistered = boardService.registerBoard(board);
            if (isRegistered == false) {
                // TODO => 게시글 등록에 실패하였다는 메시지를 전달
            }
        } catch (DataAccessException e) {
            // TODO => 데이터베이스 처리 과정에 문제가 발생하였다는 메시지를 전달

        } catch (Exception e) {
            // TODO => 시스템에 문제가 발생하였다는 메시지를 전달
        }

        return "redirect:/board/list";
    }

    @GetMapping(value = "/board/detail")
    public String openBoardDetail(@RequestParam(value = "bno", required = false) Long bno, Model model) {
        if (bno == null) {
            // TODO => 올바르지 않은 접근이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
            return "redirect:/board/list";
        }

        Board board = boardService.detailBoard(bno);
        if (board == null || "Y".equals(board.getDelete_Yn())) {
            // TODO => 없는 게시글이거나, 이미 삭제된 게시글이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
            return "redirect:/board/list";
        }
        model.addAttribute("board", board);

        return "board/boardDetail";
    }

    @PostMapping(value = "/board/delete")
    public String deleteBoard(@RequestParam(value = "bno", required = false) Long bno) {

        if (bno == null) {
            // TODO => 올바르지 않은 접근이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
            return "redirect:/board/list";
        }

        try {
            boolean isDeleted = boardService.deleteBoard(bno);
            if (isDeleted == false) {
                // TODO => 게시글 삭제에 실패하였다는 메시지를 전달
            }
        } catch (DataAccessException e) {
            // TODO => 데이터베이스 처리 과정에 문제가 발생하였다는 메시지를 전달

        } catch (Exception e) {
            // TODO => 시스템에 문제가 발생하였다는 메시지를 전달
        }

        return "redirect:/board/list";
    }

    /* 게시글 삭제 할 때 필요한 bno 글 번호를 파라미터로 받음 */
    /* try문에서 deleteBoard(bno)를 성공하면 true 실패하면 false를 iseDeleted에 주고 그에 따른 메세지 전달 */
}