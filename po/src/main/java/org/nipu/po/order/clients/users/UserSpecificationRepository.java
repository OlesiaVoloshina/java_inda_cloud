package org.nipu.po.order.clients.users;

import org.nipu.po.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "auth-service", configuration = FeignConfiguration.class, fallback = FallbackUserSpecificationRepository.class)
public interface UserSpecificationRepository {

    @RequestMapping(method = RequestMethod.GET, path = "/user/by-login/{userLogin}")
    UserData getUserByLogin(@PathVariable("userLogin") String userLogin, @RequestHeader("Authorization") String auth);

}
