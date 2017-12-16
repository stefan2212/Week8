package org.stefan.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;



public class SearchProjectsInterceptor {

    @AroundInvoke
    public Object methodInterceptor(InvocationContext ctx) throws Exception {
        System.out.println("Intercepting SearchBean method:" + ctx.getMethod().getName());
        return ctx.proceed();
    }

}
