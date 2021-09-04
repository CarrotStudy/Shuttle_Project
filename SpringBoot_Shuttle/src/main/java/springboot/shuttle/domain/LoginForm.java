package springboot.shuttle.domain;

import lombok.Data;

@Data // 롬북 getter, setter
public class LoginForm {

    private String loginId;
    private String password;
}
