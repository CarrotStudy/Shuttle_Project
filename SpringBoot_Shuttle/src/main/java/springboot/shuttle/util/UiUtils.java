package springboot.shuttle.util;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import springboot.shuttle.constant.Method;

import java.util.Map;

@Controller
public class UiUtils {

    public String showMessageWithRedirect(@RequestParam(value = "message", required = false) String message,
                                          @RequestParam(value = "redirectUri", required = false) String redirectUri,
                                          @RequestParam(value = "method", required = false) Method method,
                                          @RequestParam(value = "params", required = false) Map<String,Object> params, Model model) {

        model.addAttribute("message", message); /* message - 사용자에게 전달할 메세지를 의미함 */
        model.addAttribute("redirectUri", redirectUri); /* 리다이렉트 할 URI를 의미 */
        model.addAttribute("method", method); /* HTTP 요청 메서드 */
        model.addAttribute("params", params);
        /* 화면(view)로 전달할 파라미터 파라미터의 개수는 어떤 페이지인지에 따라 달라질 수 있으니 key,value
        형태로 사용하는 Map 사용 ex) 3페이지에 30번글을 수정했으면 다시 3페이지로 돌아갈 때 param에 담긴 정보를 이용 */

        return "utils/messageRedirect";

    }
}
