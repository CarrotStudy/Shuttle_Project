package springboot.shuttle.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import springboot.shuttle.domain.Board;
import springboot.shuttle.domain.ImageDTO;
import springboot.shuttle.domain.Member;
import springboot.shuttle.mapper.BoardMapper;
import springboot.shuttle.mapper.ImageMapper;
import springboot.shuttle.controller.paging.PaginationInfo;
import springboot.shuttle.util.FileUtils;
import springboot.shuttle.web.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service /* 해당 클래스가 비지니스 로직을 담당하는 서비스 클래스임을 의미하는 어노테이션 */
@Slf4j
public class BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private ImageMapper imageMapper;

    @Autowired
    private FileUtils fileUtils;


    public boolean registerBoard(Board board, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER); // 현재 로그인 된 아이디의 정보가 들어있는 session값을 넣어줌

        board.setWriter(loginMember.getName());
        board.setAddress(loginMember.getAddress());
        board.setLoginId(loginMember.getLoginId());

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

    public boolean registerBoard(Board board, MultipartFile[] files, HttpServletRequest request) {
        int queryResult = 1;

        if (registerBoard(board, request) == false) {
            return false;
        }

        List<ImageDTO> fileList = fileUtils.uploadFiles(files, board.getBno());
        board.setSave_name(fileList.get(0).getSave_name());
        boardMapper.insertImage(board);
        if (CollectionUtils.isEmpty(fileList) == false) {
            queryResult = imageMapper.insertImage(fileList);
            if (queryResult < 1) {
                queryResult = 0;
            }
        }

        return (queryResult > 0);
    }

    public Board detailBoard(Long bno) {
        return boardMapper.detailBoard(bno);
    }
    /* 하나의 게시글을 조회하는 detailBoard 메서드의 결괏값을 반환합니다. */

    public boolean deleteBoard(Long bno) {
        int queryResult = 0;

        Board board = boardMapper.detailBoard(bno);
        if (board != null && "N".equals(board.getDelete_Yn())) {
            queryResult = boardMapper.deleteBoard(bno);
        }

        return (queryResult == 1) ? true : false;
    }
    /* bno 글번호를 파라미터로 받아오고 Board 클래스로 선언 된 board 변수에 detailBoard 쿼리를 불러와 저장 */
    /* board에 담긴 내용이 널이 아닐 시 글이 존재한다는 의미이니 삭제 쿼리 불러와 진행 성공시 queryResult에 값 1 저장 */

    public List<Board> listBoard(Board board) {
        List<Board> boardList = Collections.emptyList();

        int boardTotalCount = boardMapper.countBoard(board);


        PaginationInfo paginationInfo = new PaginationInfo(board);
        paginationInfo.setTotalRecordCount(boardTotalCount);

        board.setPaginationInfo(paginationInfo);

        if (boardTotalCount > 0) {
            boardList = boardMapper.listBoard(board);
        }

        return boardList;
    }
    /* 삭제되지 않은 전체 글을 조회 */
    /* NPE (널포익) 을 방지하기 위 Collections 클래스의 emptyList메서드를 이용하여 비어있는 리스트 선언 */
    /* boardTotalCount는 countBoard를 통하여 삭제되지 않은 게시글을 카운팅 하여 저장 */

    public List<ImageDTO> getImageFileList(Long board_bno) {

        int fileTotalCount = imageMapper.countImage(board_bno);
        if (fileTotalCount < 1) {
            return Collections.emptyList();
        }
        return imageMapper.listImage(board_bno);
    }
    /* 파일 (이미지)의 개수를 조회하고 파일 개수가 1개 이상이면 board_bno에 해당하는 게시글에 포함된 파일 리스트를 반환 */

    public List<String> getAddress(){
        return boardMapper.getAddress();
    } /* 주소 가져오기 */


    public boolean cntPlus(Long bno){
        return boardMapper.cntPlus(bno);
    } /* 조회수 */

    public String findByBoardName(Long bno){
        return boardMapper.findByBoardName(bno);
    }

    public String findByWriter(Long bno){
        return boardMapper.findByWriter(bno);
    }
}