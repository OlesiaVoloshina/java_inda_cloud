package org.nipu.po.order.clients.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FallbackUserSpecificationRepository implements UserSpecificationRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(FallbackUserSpecificationRepository.class);

    @Override
    public UserData getUserByLogin(String userLogin, String token) {
        LOGGER.error("FALLBACK FOR getUserByLogin(" + userLogin + ")");
        return null;
    }
}
