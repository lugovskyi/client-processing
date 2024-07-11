package clientprocessing.authservice.config;

import clientprocessing.authservice.config.properties.AuthProperties;
import clientprocessing.authservice.exception.InvalidSidException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import static java.util.Objects.isNull;

@Component
public class SidAuthInterceptor implements HandlerInterceptor {

    @Autowired
    public SidAuthInterceptor(AuthProperties authProperties) {
        this.authProperties = authProperties;
    }

    private final AuthProperties authProperties;


    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws InvalidSidException {
        String sid = request.getHeader("x-request-sid");

        if (!isValidSid(sid)) {
            throw new InvalidSidException("Invalid or missing SID");
        }

        MDC.put("sid", sid);
        return true;
    }

    private boolean isValidSid(String sid) {
        // Simple validation logic for demonstration
        return !isNull(sid) && sid.length() == 36;
    }
}
