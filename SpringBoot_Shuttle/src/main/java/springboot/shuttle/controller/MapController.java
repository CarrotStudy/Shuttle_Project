package springboot.shuttle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {

    // 테스트
    @GetMapping("/map")
    public String googleMap(){
        return "map/map";
    }

    // 검색을 통한 위치 찾기
    @GetMapping("/map2")
    public String googleMap2(){
        return "map/map2";
    }

    // 우리가 하려고 하는 것
    @GetMapping("/map3")
    public String googleMap3(){
        return "map/map3";
    }

}
