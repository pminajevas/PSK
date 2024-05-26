package com.example.pirmaslab.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@Interceptor
@Duration
public class DurationInterceptor implements Serializable {
    @AroundInvoke
    public Object Duration(InvocationContext context) throws Exception {
        long startTime = System.currentTimeMillis();

        try {
            return context.proceed();
        } finally {
            long executionTime = System.currentTimeMillis() - startTime;
            String methodName = context.getMethod().getName();
            String className = context.getTarget().getClass().getName();
            System.out.printf("Method %s; Class %s; Execution time: %d;%n", methodName, className, executionTime);
        }
    }
}
