package com.dice.tech.jwt;

import com.dice.tech.IncludeHeaderInterceptor;
import com.dice.tech.model.Client;
import com.dice.tech.service.ClientService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    ClientService clientService;

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!canInterceptHeader(handler)) {
            return true;
        }
        String requestHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String clientName = null, token = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer")) {
            token = requestHeader.substring(7);
            try {
                clientName = this.jwtHelper.getClientFromToken(token);
            } catch (IllegalArgumentException e) {
                response.getWriter().write("Illegal Argument while fetching the username !!");
                return false;
            } catch (ExpiredJwtException e) {
                response.getWriter().write("Given jwt token is expired !!");
                return false;
            } catch (MalformedJwtException e) {
                response.getWriter().write("Some changed has done in token !! Invalid Token");
                return false;
            } catch (Exception e) {
                response.getWriter().write("Authorization token not valid !!");
                return false;
            }
        } else {
            response.getWriter().write("Invalid authorization header !!");
        }

        Client client = this.clientService.getClientByClientName(clientName);
        return this.jwtHelper.validateToken(token, client);
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
