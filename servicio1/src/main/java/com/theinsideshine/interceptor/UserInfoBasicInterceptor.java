package com.theinsideshine.interceptor;

import com.theinsideshine.anotations.PopulateBasicUserInfo;
import com.theinsideshine.exceptions.ErrorCode;
import com.theinsideshine.exceptions.ExceptionFactory;
import com.theinsideshine.models.UserInfo;
import com.theinsideshine.service.UserInfoClientService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class UserInfoBasicInterceptor implements HandlerInterceptor {

   /* @Autowired
    @Lazy
    private UserInfoClient userInfoClient;*/


    @Autowired
    @Lazy
    private UserInfoClientService userInfoClientService;

    private static final String SERVICE_NAME = "SERVICIO1-UserInfoBasicInterceptor";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod handlerMethod) {
            PopulateBasicUserInfo annotation = handlerMethod.getMethodAnnotation(PopulateBasicUserInfo.class);

            if (annotation != null) {
                String userIdStr = request.getRequestURI().replaceAll(".*/", "");
                Long userId;

                try {
                    userId = Long.valueOf(userIdStr);
                    log.info("[{}] Extracted userId={} from URI", SERVICE_NAME, userId);
                } catch (NumberFormatException e) {
                    log.warn("[{}] Invalid userId format: {}", SERVICE_NAME, userIdStr);
                    throw ExceptionFactory.badRequest(ErrorCode.USER_INVALID_ID, SERVICE_NAME, request.getRequestURI());
                }

                UserInfo userInfo = userInfoClientService.getBasicInfo(userId, request.getRequestURI());
                log.info("[{}] Successfully fetched user info for userId={} via Feign", SERVICE_NAME, userId);
                request.setAttribute("userInfo", userInfo);
            }
        }
        return true;
    }
}

