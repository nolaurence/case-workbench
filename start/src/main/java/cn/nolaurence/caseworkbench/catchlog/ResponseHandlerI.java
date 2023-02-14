package cn.nolaurence.caseworkbench.catchlog;

/**
 * @Author: nolaurence
 * @Description: ResponseHandlerI
 * @Date: 11/2/2023
 */
public interface ResponseHandlerI {
    public Object handle(Class returnType, String errCode, String errMsg);
}
