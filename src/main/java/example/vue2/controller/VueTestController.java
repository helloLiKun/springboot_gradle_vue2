package example.vue2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2018/10/21 0021.
 */
@Controller
public class VueTestController {
    @RequestMapping("/vue2")
    public String vue2(){
        return "vue2/vue2";
    }
}
