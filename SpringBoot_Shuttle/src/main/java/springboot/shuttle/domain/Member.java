package springboot.shuttle.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data //lombok getter, setter 메서드 생성
@AllArgsConstructor // 모든 변수를 파라미터로 갖는 생성자 생성
@NoArgsConstructor //기본 생성자 생성
public class Member {

    @NotBlank //null을 허용하지 않음, 공백도 허용 X
    @Pattern(regexp="^[a-zA-Z0-9].{6,14}$",
            message = "아이디는 6-14자리 영문 대소문자, 숫자만 가능합니다.")
    // (?=.[0-9]) : 숫자 적어도 하나, (?=.[a-zA-Z]) 영문 대, 소문자 적어도 하나
    // (?=.*\\W) : 특수문자 적어도 하나, (?=\\S+$) : 공백 제거
    // 정규표현식에 맞는 문자열이어야 함
    private String loginId; //id

    @NotBlank //(?=.*[0-9])(=.*[a-zA-Z])(?=.*\W)(?=\S+$).{8,20}
    @Pattern(regexp="^[a-zA-Z0-9].{8,16}$",
            message = "비밀번호는 8-16자리 영문 대소문자, 숫자만 가능합니다.")
    private String password; //비밀번호

    @NotBlank(message = "이름을 입력해주세요.")
    @Pattern(regexp="^[가-힣]*$",
            message = "한글만 입력 가능합니다.")
    private String name; //이름

    @NotBlank
    @Email(message = "이메일 형식을 맞춰주세요.") //이메일 양식이어야 함
    private String email; //이메일

    @NotNull(message = "우편 주소를 입력해주세요.")
    //Null만 허용하지 않음. "", " " 같은 공백은 허용
    private String postcode; //우편번호
    private String address; //주소
    private String detailAddress; //상세주소

    @NotBlank(message = "전화번호를 입력해주세요.")
    @Pattern(regexp="(^[0-9]*$)", //^ 패턴 시작, 숫자 범위, * 글자수 제한 없음, $ 패턴의 종료
            message = "숫자만 입력할 수 있습니다.")
    private String tel; //전화번호

    private String role; //역할
    private int point; //포인트
}
