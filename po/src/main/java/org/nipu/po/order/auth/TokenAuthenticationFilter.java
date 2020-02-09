package org.nipu.po.order.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = request.getHeader("Authorization");
            if (token != null && token.startsWith("Bearer")) {
                TokenAuthentication tokenAuthentication = new TokenAuthentication(token.replaceAll("Bearer", "").trim());

                Authentication authentication = getAuthenticationManager().authenticate(tokenAuthentication);
                UsernamePasswordAuthenticationToken usernamePasswordAuthentication =
                        new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), authentication.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }

        filterChain.doFilter(request, response);
    }

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /*

    public TokenAuthenticationFilter() {
        super("/**");
        setAuthenticationSuccessHandler((request, response, authentication) ->
        {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            request.getRequestDispatcher(request.getServletPath() + request.getPathInfo()).forward(request, response);
        });
        setAuthenticationFailureHandler((request, response, authenticationException) -> {
            response.getOutputStream().print(authenticationException.getMessage());
        });
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        String token = request.getHeader("Authorization");
        if (token == null) {
            TokenAuthentication authentication = new TokenAuthentication(null);
            authentication.setAuthenticated(false);
            return authentication;
        }
        TokenAuthentication tokenAuthentication = new TokenAuthentication(token.replaceAll("Bearer", "").trim());
        Authentication authentication = getAuthenticationManager().authenticate(tokenAuthentication);
        //SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        super.doFilter(req, res, chain);
    }
*/

}
