package springboot.shuttle.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data /** 대표 - lombok getter setter 메서드 생성 **/
@AllArgsConstructor /** 대표 - 모든 변수를 파라미터로 갖는 생성자 생성 **/
@NoArgsConstructor /** 대표 - 기본 생성자 생성 **/
public class Member {

    private String name;
    private int age;
}
