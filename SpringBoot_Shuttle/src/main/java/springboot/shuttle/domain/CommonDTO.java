package springboot.shuttle.domain;
/* Paging DTO */

import lombok.Data;
import springboot.shuttle.controller.paging.Criteria;
import springboot.shuttle.controller.paging.PaginationInfo;

import java.time.LocalDateTime;

@Data
public class CommonDTO extends Criteria {

    /** 페이징 정보 */
    private PaginationInfo paginationInfo;

    /** 삭제 여부 */
    private String delete_Yn;

    /** 등록일 */
    private LocalDateTime insert_Time;

    /** 수정일 */
    private LocalDateTime update_Time;

    /** 삭제일 */
    private LocalDateTime delete_Time;
}
