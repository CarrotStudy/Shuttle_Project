package springboot.shuttle.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data /** 대표 - lombok getter setter 메서드 생성 **/
@AllArgsConstructor /** 대표 - 모든 변수를 파라미터로 갖는 생성자 생성 **/
@NoArgsConstructor /** 대표 - 기본 생성자 생성 **/
public class Board extends CommonDTO{

    private Long bno; // 글번호 자동 증가
    private String title; //글제목
    private String content; //글내용
    private String writer; //작성자
    private int view_cnt; //조회수
    private String notice_Yn; //공지여부
    private String secret_Yn; //비밀여부
//    private String delete_Yn; //삭제여부
//    private LocalDateTime insert_Time; //등록일
//    private LocalDateTime update_Time; //수정일
//    private LocalDateTime delete_Time; //삭제여부
    /* CommonDTO에서 작성되고 상속받아 제거 됨 */

    private String address; // 게시글 작성 주소
    private Long price; // 가격
    private String save_name; // 썸네일
    private String loginId;

}
