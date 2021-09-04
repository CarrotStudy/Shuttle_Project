package springboot.shuttle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.shuttle.service.BoardService;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private BoardService boardService;

//    @GetMapping("/map")
    public Object map(){
        List<String> address = boardService.getAddress();
        return address;
    }
}
