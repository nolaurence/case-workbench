package cn.nolaurence.caseworkbench.catchlog;

/**
 * @Author: nolaurence
 * @Description: ResponseHandlerFactory
 * @Date: 11/2/2023
 */
public class ResponseHandlerFactory {
    public static ResponseHandlerI get() {
        if (ApplicationContextHelper.getBean(ResponseHandlerI.class) != null) {
            return ApplicationContextHelper.getBean(ResponseHandlerI.class);
        }
        return new DefaultResponseHandler();
    }
}
