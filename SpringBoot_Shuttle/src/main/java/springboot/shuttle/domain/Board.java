package springboot.shuttle.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data /** 대표 - lombok getter setter 메서드 생성 **/
@AllArgsConstructor /** 대표 - 모든 변수를 파라미터로 갖는 생성자 생성 **/
@NoArgsConstructor /** 대표 - 기본 생성자 생성 **/
public class Board {

    private Long bno; // 글번호 자동 증가
    private String title; //글제목
    private String content; //글내용
    private String writer; //작성자
    private int viewCnt; //조회수
    private String noticeYn; //공지여부
    private String secretYn; //비밀여부
    private String deleteYn; //삭제여부
    private LocalDateTime insertTime; //등록일
    private LocalDateTime updateTime; //수정일
    private LocalDateTime deleteTime; //삭제여부


}
