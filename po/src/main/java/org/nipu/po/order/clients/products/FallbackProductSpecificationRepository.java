package org.nipu.po.order.clients.products;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FallbackProductSpecificationRepository implements ProductSpecificationRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(FallbackProductSpecificationRepository.class);

    @Override
    public Object existsById(String specificationId) {
        LOGGER.error("FALLBACK FOR existsById(" + specificationId + ")");
        return null;
    }
}
