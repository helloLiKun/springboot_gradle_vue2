package example.config;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by lk on 2019/3/13.
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //这里可以通过数据库来查找到实际的用户信息，这里我们先模拟下,后续我们用数据库来实现
        if(username.equals("admin"))
        {
            //假设返回的用户信息如下;
            MyUserDetails myUserDetails=new MyUserDetails("admin", "b594510740d2ac4261c1b2fe87850d08", "ROLE_ADMIN", true,true,true, true);
            return myUserDetails;

        }

        return null;
    }

    public static void main(String[] args){
        //这里我们还要判断密码是否正确，实际应用中，我们的密码一般都会加密，以Md5加密为例
        Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
        //这里第个参数，是salt
        //就是加点盐的意思，这样的好处就是用户的密码如果都是123456，由于盐的不同，密码也是不一样的，就不用怕相同密码泄漏之后，不会批量被破解。
        String encodePwd = md5PasswordEncoder.encodePassword("123456", "admin");
        System.out.println(encodePwd);

    }
}
