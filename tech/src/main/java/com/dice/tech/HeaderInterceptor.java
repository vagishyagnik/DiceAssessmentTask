package com.dice.tech;

import com.dice.tech.service.ClientService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;

@Component
public class HeaderInterceptor implements HandlerInterceptor {

    @Autowired
    ClientService clientService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!canInterceptHeader(handler)) {
            return true;
        }
        String parsedClientId = request.getHeader("clientId");
        String clientSecret = request.getHeader("clientSecret");
        Long clientId;

        if (parsedClientId == null || parsedClientId.isEmpty()) {
            // Handle the case when clientId is not present
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("clientId header is missing");
            return false;
        }
        clientId = Long.parseLong(parsedClientId);
        if (clientSecret == null || clientSecret.isEmpty()) {
            // Handle the case when clientSecret is not present
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("clientSecret header is missing");
            return false;
        }

        return clientService.verifyClient(clientId, clientSecret);
    }

    private Boolean canInterceptHeader(Object handler) {
        if (handler instanceof HandlerMethod handlerMethod) {
            // Check if the Controller class or method is marked with @ExcludeHeaderInterceptor
            // Exclude the interceptor for controllers/methods with the annotation
            return handlerMethod.hasMethodAnnotation(IncludeHeaderInterceptor.class) ||
                    handlerMethod.getBeanType().isAnnotationPresent(IncludeHeaderInterceptor.class); // Allow the request to proceed without interceptor checks
        }
        return false;
    }
}
