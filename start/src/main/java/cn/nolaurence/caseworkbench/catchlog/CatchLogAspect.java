package cn.nolaurence.caseworkbench.catchlog;

import cn.nolaurence.caseworkbench.exception.BizException;
import cn.nolaurence.caseworkbench.exception.SysException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;

/**
 * @Author: nolaurence
 * @Description: CatchLogAspect
 * @Date: 11/2/2023
 */
@Aspect
@Slf4j
@Order(1)
public class CatchLogAspect {
    @Pointcut("@within(CatchAndLog) && execution(public * *(..))")
    public void pointcut() {}

//    @Around(value = "pointcut()")
//    public Object around(ProceedingJoinPoint joinPoint) {
//        long startTime = System.currentTimeMillis();
//
//        log
//    }

    private Object HandleException(ProceedingJoinPoint joinPoint, Throwable e) {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Class<?> returnType = ms.getReturnType();

        if (e instanceof BizException) {
            log.warn("BIZ EXCEPTION : {}", e.getMessage());
            // 在Debug的时候，对于BizException也打印堆栈
            if (log.isDebugEnabled()) {
                log.error(e.getMessage(), e);
            }
            return ResponseHandlerFactory.get().handle(returnType,
                    ((BizException) e).getErrCode(), e.getMessage());
        } else if (e instanceof SysException) {
            log.error("SYS EXCEPTION: {}",e.getMessage(), e);
            return ResponseHandlerFactory.get().handle(returnType, ((SysException) e).getErrCode(), e.getMessage());
        }

        log.error("UNKNOWN EXCEPTION: {}", e.getMessage(), e);
        return ResponseHandlerFactory.get().handle(returnType, "UNKNOWN_ERROR", e.getMessage());
    }

    private void logResponse(long startTime, Object response) {
        try {
            long endTime = System.currentTimeMillis();
            if (log.isDebugEnabled()) {
                log.debug("RESPONSE: {}", JSON.toJSONString(response));
                log.debug("COST: {}ms", (endTime - startTime));
            }
        } catch (Exception e) {
            //swallow it
            log.error("logResponse error: {}", e.getMessage() , e);
        }
    }

    private void logRequest(ProceedingJoinPoint joinPoint) {
        if (!log.isDebugEnabled()) {
            return;
        }

        try {
            log.debug("START PROCESSING: {}", joinPoint.getSignature().toShortString());
            Object[] args = joinPoint.getArgs();
            for (Object arg : args) {
                log.debug("REQUEST: {}", JSON.toJSONString(arg, SerializerFeature.IgnoreErrorGetter));
            }
        } catch (Exception e) {
            //swallow it
            log.error("logReqeust error: {}", e.getMessage() , e);
        }
    }
}