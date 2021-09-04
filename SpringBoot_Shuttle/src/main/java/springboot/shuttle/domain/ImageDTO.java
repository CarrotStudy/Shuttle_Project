package springboot.shuttle.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ImageDTO {

    private Long ino; // 이미지 번호 PK
    private Long board_bno; // 게시글 번호 FK
    private String original_name; // 원본 파일명
    private String save_name; // 저장 파일명
    private long size; // 파일 크기
    private String delete_Yn; // 삭제 여부
    private LocalDateTime insert_Time; // 등록일
    private LocalDateTime delete_Time; // 삭제일
}
