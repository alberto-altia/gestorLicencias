package proyectoFCT.gestorLicencias.aspects;

import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionsLog {

    //PointCuts

    @Pointcut("@annotation(proyectoFCT.gestorLicencias.aspects.AnotacionLogException)")
    public void anotacionExcepcions() {
    }

    //consejos

    @Before("anotacionExcepcions()")
    public void apiRequestLog(JoinPoint jp) {
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info("--------------- exception : " + jp.getSignature().getName() + "---------------");
    }

    @AfterReturning(pointcut = "anotacionExcepcions()", returning = "result")
    public void apiResponseGetLog(JoinPoint jp, Object result) {
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).error(">>> RETURN EXCEPTION>>> " + jp.getSignature().getName() + ": " + result);
    }

    @AfterThrowing(pointcut = "anotacionExcepcions()", throwing = "exception")
    public void apiResponseExceptionGetLog(JoinPoint jp, Exception exception) {
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).error(">>> RETURN EXCEPTION >>> " + jp.getSignature().getName() + ": " + exception.getClass().getSimpleName() + "\n mensaje: " + exception.getMessage());
    }
}
