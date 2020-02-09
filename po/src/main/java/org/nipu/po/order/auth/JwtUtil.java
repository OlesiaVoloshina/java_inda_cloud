package org.nipu.po.order.auth;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.jwt.JwtHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JwtUtil {

    public static User parseToken(String token) {
        JsonParser parser = JsonParserFactory.getJsonParser();
        Map<String, ?> tokenData = parser.parseMap(JwtHelper.decode(token).getClaims());
        String userName = (String) tokenData.get("user_name");
        ArrayList<String> authorities = (ArrayList<String>) tokenData.get("authorities");
        List<GrantedAuthority> authorityList = new ArrayList<>();
        for (String authority : authorities) {
            authorityList.add(new SimpleGrantedAuthority(authority));
        }

        User user = new User(userName, token, authorityList);
        return user;
    }
}
