package com.theinsideshine.interceptor;

import com.theinsideshine.anotations.PopulateEcheqInfo;
import com.theinsideshine.models.EcheqDTO;
import com.theinsideshine.models.UserInfo;
import com.theinsideshine.service.EcheqClientService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
@Order(2)
public class EcheqInfoInterceptor implements HandlerInterceptor {

    private static final String SERVICE_NAME = "SERVICIO1-EcheqInfoInterceptor";

    @Autowired
    @Lazy
    private EcheqClientService echeqClientService; // Usa el servicio

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod handlerMethod) {
            PopulateEcheqInfo annotation = handlerMethod.getMethodAnnotation(PopulateEcheqInfo.class);

            if (annotation != null) {
                String path = request.getRequestURI();
                UserInfo userInfo = (UserInfo) request.getAttribute("userInfo");

                if (userInfo != null && userInfo.getOperador() != null) {
                    String operador = userInfo.getOperador();
                    log.info("[{}] Found operador='{}' in request attributes", SERVICE_NAME, operador);
                    EcheqDTO echeqInfo = echeqClientService.getEcheqByOperador(operador, path); // Llama al servicio
                    log.info("[{}] Successfully fetched echeq info for operador='{}'", SERVICE_NAME, echeqInfo);
                    request.setAttribute("echeqInfo", echeqInfo);
                } else {
                    log.warn("[{}] UserInfo or operador not found in request attributes.", SERVICE_NAME);
                    // Decidí no lanzar excepción aquí, ya que PopulateEcheqInfo podría usarse en otros escenarios
                    // donde la info del usuario no es obligatoria. Puedes ajustar esto según tu necesidad.
                }
            }
        }
        return true;
    }
}