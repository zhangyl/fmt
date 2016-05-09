package com.tqmall.zeus.service;

import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.objenesis.ObjenesisStd;

//import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.tqmall.core.common.entity.BaseResult;
import com.tqmall.core.common.exception.BusinessCheckFailException;
import com.tqmall.core.common.exception.BusinessProcessFailException;

@Slf4j
public class DubboServiceInterceptor implements MethodInterceptor {

    private ObjenesisStd generator = new ObjenesisStd();

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            Object result = invocation.proceed();
            return result;
        } catch (BusinessProcessFailException e) {
            BaseResult result = exceptionProcessor(invocation, e);
            result.setCode(e.getErrorCode());
            result.setMessage(e.getMessage());
            result.setSuccess(false);
            return result;
        } catch (BusinessCheckFailException e) {
            BaseResult result = exceptionProcessor(invocation, e);
            result.setCode(e.getErrorCode());
            result.setMessage(e.getMessage());
            result.setSuccess(false);
            return result;
        } catch (Exception e) {
            return exceptionProcessor(invocation, e);
        }
    }

    private BaseResult exceptionProcessor(MethodInvocation invocation, Throwable e) {
        Object[] args = invocation.getArguments();
        Method method = invocation.getMethod();
        String methodName = method.getDeclaringClass().getName() + "." + method.getName();
        log.error("服务[method=" + methodName + "] params={}" + JSONArray.toJSONString(args) + "异常：", e);

        Class<?> returnType = method.getReturnType();
        BaseResult result = (BaseResult) generator.newInstance(returnType);
        result.setCode("11100001");
        result.setSuccess(false);
        result.setMessage("系统内部错误，请稍后再试！");
        return result;
    }

}
