package example.util;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2018/8/19 0019.
 */
public class JSONObject {
    public Object getJSONParam(HttpServletRequest request){
        Object obj=null;
        try {
            // 获取输入流
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));

            // 写入数据到Stringbuilder
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = streamReader.readLine()) != null) {
                sb.append(line);
            }
            obj= JSON.parse(sb.toString());
            // 直接将json信息打印出来
            System.out.println(obj.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
