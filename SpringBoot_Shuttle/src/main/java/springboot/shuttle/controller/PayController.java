package springboot.shuttle.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PayController {

    @GetMapping("/pay")
    public String kakaPay(){
        return "pay/pay";
    }

    @GetMapping("/pay2")
    public String inicisPay(){
        return "pay/pay2";
    }

}
