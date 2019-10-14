package com.atz.springboot.interceptor;

/**
 * @author ATower
 * @date 2019-09-21 17:56
 */

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atz.springboot.model.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Service
public class LoginInterceptor extends HandlerInterceptorAdapter {

    /**
     * 在业务处理器处理请求之前被调用
     * 如果返回false
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     * 如果返回true
     *    执行下一个拦截器,直到所有的拦截器都执行完毕
     *    再执行被拦截的Controller
     *    然后进入拦截器链,
     *    从最后一个拦截器往回执行所有的postHandle()
     *    接着再从最后一个拦截器往回执行所有的afterCompletion()
     */
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        String contextPath=session.getServletContext().getContextPath();
        String[] NeedAuthPage = new String[]{
                "/publish"};

        String uri = request.getRequestURI();
        uri = StringUtils.remove(uri, contextPath);
//        System.out.println(uri);
            if(Arrays.asList(NeedAuthPage).contains(uri)){
                User user =(User) session.getAttribute("user");
                if(null==user){
                    request.setAttribute("message","未登录!");
                    request.getRequestDispatcher("/").forward(request,response);
                    return false;
                }
            }

        return true;
    }

    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
     * 可在modelAndView中加入数据，比如当前时间
     */

    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等  
     *
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion() 
     */

    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

}