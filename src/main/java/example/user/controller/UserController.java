package example.user.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import example.user.entity.User;
import example.user.mapping.UserMapping;
import example.user.service.UserService;
import example.util.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

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

    @RequestMapping("/userSubmit")
    @ResponseBody
    public  String userSubmit(@RequestBody User user){
        AjaxResponse.Body resp=AjaxResponse.FAILED.body();
        System.out.println("user:"+user.toString());
        resp=AjaxResponse.SUCCEEDED.body();
        return JSON.toJSONString(resp);
    }

    @RequestMapping("/formMultiple")
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
}
