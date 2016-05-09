package com.tqmall.zeus.controller.tools;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.tqmall.core.common.entity.Result;


/**
 * 内部工具接口,测试用
 */
@Controller
@RequestMapping(value = "tools")
@Slf4j
public class InnerToolsController {
	
    @RequestMapping(value = "dubbo/service/view",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public Object viewDubboServiceMethod(@RequestBody String service) {
        try {
            List<MethodInfo> methodDescList = new ArrayList<>();
            Class<?> clazz = Class.forName(service.trim());

            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                MethodInfo methodDesc = new MethodInfo();
                methodDescList.add(methodDesc);
                methodDesc.setMethodName(method.getName());

                Class<?>[] paramTypesArray = method.getParameterTypes();
                if (paramTypesArray != null && paramTypesArray.length > 0) {
                    List<ParamInfo> paramTypes = new ArrayList<>();
                    for (Class<?> paramTypeClass : paramTypesArray) {
                        ParamInfo p = new ParamInfo();
                        paramTypes.add(p);
                        p.setParamType(paramTypeClass.getName());
                        if (!ClassUtil.isPrimtiveType(paramTypeClass)) {
                            Object paramTypeInstance = null;
                            try {
                                paramTypeInstance = paramTypeClass.newInstance();
                            } catch (Exception ignore) {
                                // 不存在无参数构造函数，只是不给出参数demo
                            }
                            if (paramTypeInstance != null) {
                                String json = JSON.toJSONString(paramTypeInstance, SerializerFeature.WriteMapNullValue);
                                p.setParamDemo(json);
                            }
                        }
                    }
                    methodDesc.setParamTypes(paramTypes);
                }
            }
            return methodDescList;

        } catch (Exception e) {
            log.error("调用dubbo服务[" + service + "]异常：", e);
            return Result.wrapErrorResult(null, e.getMessage());
        }
    }

    @RequestMapping(value = "dubbo/service",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public Object invokeDubboService(@RequestBody DubboParam param) {
        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        String service = param.getService().trim();
        try {
            Class<?> clazz = Class.forName(service);
            Object o = wac.getBean(clazz);

            List<Param> params = param.getParams();
            if (params != null) {
                Class<?>[] parameterTypes = new Class[params.size()];
                Object[] args = new Object[params.size()];
                for (int i = 0; i < params.size(); ++i) {
                    Param p = params.get(i);
                    Class<?> paramClazz = Class.forName(p.getParamType());
                    parameterTypes[i] = paramClazz;
                    if (ClassUtil.isPrimtiveType(paramClazz)) {
                        Class<?> targetClass = ClassUtil.getPrimtiveTypeWrapperClass(paramClazz);
                        Constructor<?> ctor = targetClass.getConstructor(String.class);
                        args[i] = ctor.newInstance(p.getParam());
                    } else {
                        Object paramObj = JSON.parseObject(p.getParam(), paramClazz);
                        args[i] = paramObj;
                    }
                }
                Method method = clazz.getMethod(param.getMethod(), parameterTypes);
                Object result = method.invoke(o, args);
                return result;
            } else {
                Method method = clazz.getMethod(param.getMethod());
                Object result = method.invoke(o);
                return result;
            }

        } catch (Exception e) {
            log.error("调用dubbo服务[" + service + "]异常：", e);
            return Result.wrapErrorResult(null, e.getMessage());
        }
    }
}
