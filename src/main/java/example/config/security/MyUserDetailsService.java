package example.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lk on 2019/3/13.
 */
@Component
public class MyUserDetailsService implements UserDetailsService,Serializable {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //这里可以通过数据库来查找到实际的用户信息，这里我们先模拟下,后续我们用数据库来实现
        if(username.equals("admin")) {
            //假设返回的用户信息如下;
            List<GrantedAuthority> authorities=new ArrayList<>();
            String[] authoritiesArr=new String[]{"ROLE_USER","ROLE_TEST"};
            for(String authorite:authoritiesArr){
                authorities.add(new GrantedAuthority() {
                    @Override
                    public String getAuthority() {
                        return authorite;
                    }
                });
            }
            User user=new User("admin", "b594510740d2ac4261c1b2fe87850d08",authorities);
            return user;
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
