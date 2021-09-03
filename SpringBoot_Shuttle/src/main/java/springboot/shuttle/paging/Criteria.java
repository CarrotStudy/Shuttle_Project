package springboot.shuttle.paging;

import lombok.Data;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Data
public class Criteria {

    private int currentPageNo; // 현재 페이지 번호
    private int recordsPerPage; // 페이지당 출력할 데이터 개수
    private int pageSize; // 페이지 사이즈

    public Criteria() {
        this.currentPageNo = 1;
        this.recordsPerPage = 8;
        this.pageSize = 10;
    }

    public String makeQueryString(int pageNo) {

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .queryParam("currentPageNo", pageNo)
                .queryParam("recordsPerPage", recordsPerPage)
                .queryParam("pageSize", pageSize)
                .build()
                .encode();

        return uriComponents.toUriString();
    }

}
