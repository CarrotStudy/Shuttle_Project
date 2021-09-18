package springboot.shuttle.domain;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data //lombok getter, setter 메서드 생성
@AllArgsConstructor // 모든 변수를 파라미터로 갖는 생성자 생성
@NoArgsConstructor //기본 생성자 생성
public class Member {

    private String loginId; //id
    private String password; //비밀번호
    private String name; //이름
    private String email; //이메일
    private String postcode; //우편번호
    private String address; //주소
    private String detailAddress; //상세주소
    private String role; //역할
    private String tel; //전화번호
    private int point; //포인트
}
