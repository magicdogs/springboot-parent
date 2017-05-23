package com.magic.springboot.demo.web.interceptor;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by magicdog on 2017/5/23.
 */
public class CatInterceptor implements HandlerInterceptor {

    private ThreadLocal<Transaction> tranLocal = new ThreadLocal<Transaction>();
    private ThreadLocal<Transaction> pageLocal = new ThreadLocal<Transaction>();

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        Transaction t = Cat.newTransaction("URL", uri);
        Cat.logEvent("URL.Method", request.getMethod(), Message.SUCCESS,request.getRequestURL().toString());
        Cat.logEvent("URL.Host", request.getMethod(),Message.SUCCESS,request.getRemoteHost());
        tranLocal.set(t);
        System.out.println("preHandle");
        return true;
    }

    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

        String viewName = modelAndView != null?modelAndView.getViewName():"无";
        Transaction t = Cat.newTransaction("View", viewName);
        pageLocal.set(t);
        System.out.println("postHandle");
    }

    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        //请求-页面渲染前
        Transaction pt = pageLocal.get();
        pageLocal.remove(); //need remove
        pt.setStatus(Transaction.SUCCESS);
        pt.complete();

        //总计
        Transaction t = tranLocal.get();
        tranLocal.remove(); //need remove
        t.setStatus(Transaction.SUCCESS);
        t.complete();
        System.out.println("afterCompletion");
    }
}
