package springboot.shuttle.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class PayController {

    @GetMapping("/choice")
    public String choice(){
        return "pay/choice";
    }

    @PostMapping("/pay")
    public String kakaoPay(@RequestParam String choice, Model model){
        model.addAttribute("price",choice);
        return "pay/kakaopay";

    }

    @PostMapping("/pay2")
    public String inicisPay(@RequestParam String choice, Model model){
        log.info("choice={}",choice);
        model.addAttribute("price",choice);
        return "pay/inicispay";
    }

}
