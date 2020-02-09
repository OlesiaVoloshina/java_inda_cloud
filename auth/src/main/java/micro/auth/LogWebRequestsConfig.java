package micro.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LogWebRequestsConfig implements WebMvcConfigurer {

    private static final String SERVICE_PREFIX = "[MICROSERVICES.AUTH-SERVICE] ";

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(true);
        filter.setBeforeMessagePrefix(SERVICE_PREFIX);
        filter.setAfterMessagePrefix(SERVICE_PREFIX);
        return filter;
    }
}
