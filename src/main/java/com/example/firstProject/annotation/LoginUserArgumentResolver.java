package com.example.firstProject.annotation;

import com.example.firstProject.dto.UserSessionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public  class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
    private final HttpSession session;

    @Override
    public boolean supportsParameter//- @LoginUser 어노테이션이 붙어 있고, 파라미터 클래스 타입이 UserSessionDto인가 판단 후 true를 반환
            (MethodParameter parameter) {
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
        boolean isUserClass = UserSessionDto.class.equals(parameter.getParameterType());
        return isLoginUserAnnotation && isUserClass;
    }

    @Override
    public Object resolveArgument//- 파라미터에 전달할 객체 생성 ( 세션에서 객체를 가져옴 )
            (MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return session.getAttribute("playUser");
    }
}
