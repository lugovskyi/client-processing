package clientprocessing.authservice.config;

import clientprocessing.authservice.exception.InvalidSidException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SidAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws InvalidSidException {
        String sid = request.getHeader("x-request-sid");
        if (sid == null || !isValidSid(sid)) {
            throw new InvalidSidException("Invalid or missing SID");
        }
        return true;
    }

    private boolean isValidSid(String sid) {
        // Simple validation logic for demonstration
        return sid.length() == 10;
    }
}
