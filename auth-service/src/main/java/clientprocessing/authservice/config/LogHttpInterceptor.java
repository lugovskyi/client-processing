package clientprocessing.authservice.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Component
public class LogHttpInterceptor extends OncePerRequestFilter {

    private static final String LOG_FORMAT_STRING = """
            \nfinished processing: {}
            sid: {}
            uri: {}
            request params: {}
            request body: {}
            response code: {}
            response: {}
            processing time: {}
            """;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) {
        try {
            ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
            ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);


            long startTime = System.currentTimeMillis();
            filterChain.doFilter(requestWrapper, responseWrapper);
            long processingTime = System.currentTimeMillis() - startTime;

            String requestBody = getStringValue(requestWrapper.getContentAsByteArray());
            String responseBody = getStringValue(responseWrapper.getContentAsByteArray());
            Map<String, String> requestMap = this.getTypesafeRequestMap(request);

            log.info(LOG_FORMAT_STRING, request.getMethod(), request.getHeader("x-request-sid"), request.getRequestURI(), requestMap, requestBody,
                    response.getStatus(), responseBody, processingTime);
            responseWrapper.copyBodyToResponse();

        } catch (Throwable a) {
            log.error(a.getMessage());
        }
    }


    private String getStringValue(byte[] contentAsByteArray) {
        return new String(contentAsByteArray, 0, contentAsByteArray.length, StandardCharsets.UTF_8);
    }

    private Map<String, String> getTypesafeRequestMap(HttpServletRequest request) {
        Map<String, String> typesafeRequestMap = new HashMap<>();
        Enumeration<?> requestParamNames = request.getParameterNames();
        while (requestParamNames.hasMoreElements()) {
            String requestParamName = (String) requestParamNames.nextElement();
            typesafeRequestMap.put(requestParamName, request.getParameter(requestParamName));
        }
        return typesafeRequestMap;
    }
}