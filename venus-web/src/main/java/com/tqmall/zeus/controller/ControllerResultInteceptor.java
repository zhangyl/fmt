package com.tqmall.zeus.controller;


import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.alibaba.fastjson.JSONArray;
import com.tqmall.core.common.entity.PagingResult;
import com.tqmall.core.common.entity.Result;
import com.tqmall.core.common.exception.BusinessCheckFailException;
import com.tqmall.core.common.exception.BusinessProcessFailException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ControllerResultInteceptor implements MethodInterceptor {

    @Override
    public Object invoke(final MethodInvocation invocation) throws Throwable {
        try {
            return invocation.proceed();
        } catch (BusinessCheckFailException e) {
            log.error("BusinessCheckFailException", e);
            Object result = exceptionProcessor(invocation, e);
            return result;
        } catch (BusinessProcessFailException e) {
        	log.error("BusinessProcessFailException", e);
            Object result = exceptionProcessor(invocation, e);
        	return result;
        } catch (Throwable e) {
            log.error("Exception:", e);
            Object r = exceptionProcessor(invocation, e);
            return r;
        }
    }

    @SuppressWarnings("rawtypes")
	private Object exceptionProcessor(MethodInvocation invocation, Throwable e) {
        Object[] args = invocation.getArguments();
        Method method = invocation.getMethod();
        String methodName = method.getDeclaringClass().getName() + "." + method.getName();
        log.error("dubbo服务[method=" + methodName + "] params=" + JSONArray.toJSONString(args) + "异常：", e);

        Class<?> clazz = method.getReturnType();
        if (clazz.equals(Result.class)) {
        	Result result = new Result();
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            if(e instanceof BusinessCheckFailException) {
            	result.setCode(((BusinessCheckFailException)e).getErrorCode());
            }
            else if(e instanceof BusinessProcessFailException) {
            	result.setCode(((BusinessProcessFailException)e).getErrorCode());
            } else {
            	result.setMessage("系统内部错误");
            }
            return result;
        } else if (clazz.equals(PagingResult.class)) {
        	PagingResult result = new PagingResult();
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            if(e instanceof BusinessCheckFailException) {
            	result.setCode(((BusinessCheckFailException)e).getErrorCode());
            }
            else if(e instanceof BusinessProcessFailException) {
            	result.setCode(((BusinessProcessFailException)e).getErrorCode());
            } else {
                result.setCode("1111");
                result.setMessage("系统繁忙，请稍后重试");
            }
            return result;
        }
        log.error("dubbo拦截器发现服务签名错误method={}, returnType=", methodName, clazz);
        return null;
    }

}
