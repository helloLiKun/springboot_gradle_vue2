package example.user.controller;

import com.alibaba.fastjson.JSON;
import example.config.MyAuthenticationProvider;
import example.user.entity.User;
import example.user.mapping.UserMapping;
import example.user.service.UserService;
import example.util.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2018/6/10 0010.
 */
@Controller
public class UserController implements UserMapping {
    @RequestMapping(LOGIN)
    public String userList(ModelMap map){
        map.put("userJSON",JSON.toJSONString(new User()));
        return "user/form";
    }

    @RequestMapping("/test")
    public String test(ModelMap map){
        map.put("userJSON",JSON.toJSONString(new User()));
        return "user/form";
    }

    @RequestMapping("/sys/userSubmit")
    @ResponseBody
    public  String userSubmit(@RequestBody User user, HttpServletRequest request){
        AjaxResponse.Body resp=AjaxResponse.FAILED.body();
        System.out.println("user:"+user.toString());
        UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(user.getName(),user.getPwd());
        Authentication authentication=authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        HttpSession session = request.getSession();
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext()); // 这个非常重要，否则验证后将无法登陆
        resp=AjaxResponse.SUCCEEDED.body();
        resp.setMsg(authentication.getName());
        return JSON.toJSONString(resp);
    }

    @RequestMapping("/sys/formMultiple")
    @ResponseBody
    public String formMultiple(String id,String userJSON,MultipartFile[] files){
        AjaxResponse.Body resp=AjaxResponse.FAILED.body();
        System.out.println("length:"+files.length);
        for(MultipartFile file:files){
            System.out.println(file.getOriginalFilename());
        }
        resp=AjaxResponse.SUCCEEDED.body();
        return JSON.toJSONString(resp);
    }

    @Autowired
    UserService userService;
    @Autowired
    MyAuthenticationProvider myAuthenticationProvider;
    @Autowired
    private AuthenticationManager authenticationManager;  // 这样就可以自动注入？oh ，mygod ,how can it do so?
}
