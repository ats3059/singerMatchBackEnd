package success.singermatch.global.config.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import success.singermatch.global.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// log.info를 사용하기 위해 slf4j를 선언해야한다.
@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Request가 들어오고 Controller에 넘어가기 직전에 처리

        String requestURI = request.getRequestURI();

        String uuid = StringUtil.generateRandomStr();

        // 들어온 url을 받아줄 수 있는 클래스가 있는지 확인한다.
/*
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
        }
*/

        log.info("REQUEST [{}] [{}]", requestURI, handler);

        // true - 다음으로 진행, false - 종료
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Controller에서 요청이 다 마무리하고, View로 Rendering하게 전에 처리

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Controller에서 요청이 다 마무리되고, View의 Rendering이 다 끝나면 처리

    }
}
