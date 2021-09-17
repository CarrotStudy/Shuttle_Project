package springboot.shuttle.domain;

import lombok.Data;

@Data
public class PwUpdate {

    private String loginId; //로그인 아이디
    private String password; //현재 비밀번호
    private String changePw; //변경 할 비밀번혼

}
