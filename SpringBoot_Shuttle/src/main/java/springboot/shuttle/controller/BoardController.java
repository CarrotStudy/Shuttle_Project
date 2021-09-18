package springboot.shuttle.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import springboot.shuttle.constant.Method;
import springboot.shuttle.domain.Board;
import springboot.shuttle.domain.ImageDTO;
import springboot.shuttle.service.BoardService;
import springboot.shuttle.util.UiUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/* GET은 데이터의 조회를 의미, POST는 데이터의 생성을 의미*/
/* 예시로 select 쿼리 같은 경우에는 GET, insert update 쿼리 같은 경우에는 POST */

@Controller /* 해당 어노테이션이 사용자의 요청과 응답을 기다리는 즉 UI를 담당하는 Controller 클래스임을 의미한다. */
@RequiredArgsConstructor
@Slf4j
public class BoardController extends UiUtils {

    @Autowired
    private BoardService boardService;

    /* String 리턴 타입으로 리턴되는 html의 경로의 끝에는 접미사로 .html을 자동으로 연결 */
    /* 메서드의 파라미터로 저장되는 Model 인터페이스는 데이터를 뷰로 전달하는데 사용 */

    @GetMapping("/board/list")
    public String boardListForm(@ModelAttribute("board") Board board, Model model) {

        List<Board> boardList = boardService.listBoard(board);
        model.addAttribute("boardList", boardList);
        return "/board/boardList";
    }
    /* boardServcie.listBoard() 로 board list들을 가져와 boardList 변수에 저장하여 model.addAttribute로 뷰로 전달 */
    /* @ModellAttribute를 사용하면 파라미터로 전달받은 객체를 자동으로 뷰까지 전달 할 수 있음 괄호 안에 "criteria"가 화면(view) 에서 사용 될 변수명 */


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
    public String boardAdd(final Board board, final MultipartFile[] files, HttpServletRequest request, Model model) {

        try {
            boolean isRegistered = boardService.registerBoard(board, files, request);
            if (isRegistered == false) {
                return showMessageWithRedirect("게시글 등록에 실패하였습니다.", "/board/list", Method.GET, null, model);
            }
        } catch (DataAccessException e) {
            return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/board/list", Method.GET, null, model);

        } catch (Exception e) {
            return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/board/list", Method.GET, null, model);
        }

        return showMessageWithRedirect("게시글 등록이 완료되었습니다.", "/board/list", Method.GET, null, model);
    }

    @GetMapping(value = "/board/detail")
    public String boardDetailForm(@RequestParam(value = "bno", required = false) Long bno, Model model) {

        if (bno == null) {
            return showMessageWithRedirect("올바르지 않은 접근입니다.", "/board/list", Method.GET, null, model);
        }

        Board board = boardService.detailBoard(bno);
        if (board == null || "Y".equals(board.getDelete_Yn())) {
            return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글입니다.", "/board/list", Method.GET, null, model);
        }
        model.addAttribute("board", board);

        boardService.cntPlus(bno);

        List<ImageDTO> fileList = boardService.getImageFileList(bno);

        model.addAttribute("fileList", fileList); /* 97~98 getImageFileList를 Model에 담아 뷰로 전달 */

        return "board/boardDetail";
    }

    @GetMapping(value = "/board/delete")
    public String deleteBoard(@RequestParam(value = "bno", required = false) Long bno, Model model) {
        if (bno == null) {
            return showMessageWithRedirect("올바르지 않은 접근입니다.", "/board/list", Method.GET, null, model);
        }

        try {
            boolean isDeleted = boardService.deleteBoard(bno);
            if (isDeleted == false) {
                return showMessageWithRedirect("게시글 삭제에 실패하였습니다.", "/board/list", Method.GET, null, model);
            }
        } catch (DataAccessException e) {
            return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/board/list", Method.GET, null, model);

        } catch (Exception e) {
            return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/board/list", Method.GET, null, model);
        }

        return showMessageWithRedirect("게시글 삭제가 완료되었습니다.", "/board/list", Method.GET, null, model);
    }

    /* 게시글 삭제 할 때 필요한 bno 글 번호를 파라미터로 받음 */
    /* try문에서 deleteBoard(bno)를 성공하면 true 실패하면 false를 iseDeleted에 주고 그에 따른 메세지 전달 */
}