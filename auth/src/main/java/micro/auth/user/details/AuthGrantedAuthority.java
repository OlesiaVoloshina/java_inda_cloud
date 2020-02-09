package micro.auth.user.details;

import micro.auth.user.data.UserRole;
import org.springframework.security.core.GrantedAuthority;

public class AuthGrantedAuthority implements GrantedAuthority {

    private UserRole role;

    public AuthGrantedAuthority(UserRole role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role.name();
    }
}
