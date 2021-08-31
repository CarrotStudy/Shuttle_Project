package springboot.shuttle.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {

    private Long bno; // ㄷㅍ - 글번호
    private String title; // ㄷㅍ - 글제목
    private String content; // ㄷㅍ-내용
    private String writer; // ㄷㅍ- 글작성자
    private int viewCnt; // ㄷㅍ - 조회수
    private LocalDateTime insertTime; // ㄷㅍ - 작성시간
    private LocalDateTime updateTime; // ㄷㅍ - 수정시간

}
