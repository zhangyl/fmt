package com.tqmall.zeus.controller.tools;


import java.util.List;

public class MethodInfo {

    private String methodName;
    private List<ParamInfo> paramTypes;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<ParamInfo> getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(List<ParamInfo> paramTypes) {
        this.paramTypes = paramTypes;
    }
}
