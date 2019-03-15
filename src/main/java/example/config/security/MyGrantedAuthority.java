package example.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * Created by lk on 2019/3/15.
 */
@Component
public class MyGrantedAuthority implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return "ROLE_TEST";
    }
}
