package ma.kata.product_app.security.jwt.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component("authService")
public class AuthService {

    private static final String ADMIN_EMAIL = "admin@admin.com";

    public boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            return ADMIN_EMAIL.equalsIgnoreCase(userDetails.getUsername());
        }
        return false;
    }
}
