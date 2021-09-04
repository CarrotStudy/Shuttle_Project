package springboot.shuttle.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.shuttle.domain.ImageDTO;

import java.util.List;

@Mapper
public interface ImageMapper {

    int insertImage(List<ImageDTO> imageList); /* 파일 정보를 저장하는 insert 쿼리 호출 */

    ImageDTO detailImage(Long ino); /* 파라미터로 전달받은 파일 번호에 해당하는 파일의 상세 정보 조회 다운로드에 필요 삭제 예정 */

    int deleteImage(Long board_bno); /* 파일을 삭제 */

    List<ImageDTO> listImage(Long board_bno); /* 특정 게시글에 포함된 파일 목록을 조회 */

    int countImage(Long board_bno); /* 특정 게시글에 포함된 파일 개수를 조회 */
}
