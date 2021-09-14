package springboot.shuttle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springboot.shuttle.domain.Board;
import springboot.shuttle.service.BoardService;

import java.util.List;

@Controller
public class MapController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/map")
    public String googleMap(Model model){
        List<String> address = boardService.getAddress();
        model.addAttribute("address",address);
        return "map/map";
    }

    // 검색을 통한 위치 찾기
    @GetMapping("/map2")
    public String googleMap2(){
        return "map/map2";
    }

}
