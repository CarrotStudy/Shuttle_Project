package springboot.shuttle.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springboot.shuttle.domain.ResponseDTO;

@Slf4j
@RestController
public class PayAPIController {

    @PostMapping("/api/payments/complete")
    public ResponseDTO<Integer> inicisPay(@RequestBody int uid){
        log.info("uid={}",uid);
        return new ResponseDTO<>(HttpStatus.OK.value(),1);
    }
}
