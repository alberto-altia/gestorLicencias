package proyectoFCT.gestorLicencias.aspects;

import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class MetodosLog {

    @Before("@annotation(proyectoFCT.gestorLicencias.aspects.AnotacionLogMetodos)")
    public void apiRequestLog(JoinPoint jp) {
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info("--------------- entrada metodo: " + jp.getSignature().getName() + "---------------");
    }

    @AfterReturning(pointcut = "@annotation(proyectoFCT.gestorLicencias.aspects.AnotacionLogMetodos)", returning = "result")
    public void apiResponseGetLog(JoinPoint jp, Object result) {
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info(">>> RETURN >>> " + jp.getSignature().getName() + ": " + result);
    }

    @AfterThrowing(pointcut = "@annotation(proyectoFCT.gestorLicencias.aspects.AnotacionLogMetodos)", throwing = "exception")
    public void apiResponseExceptionGetLog(JoinPoint jp, Exception exception) {
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info(">>> RETURN EXCEPTION >>> " + jp.getSignature().getName() + ": " + exception.getClass().getSimpleName());
    }

    @Around("@annotation(proyectoFCT.gestorLicencias.aspects.AnotacionLogMetodos)")
    public Object adviceA(ProceedingJoinPoint jp) throws Throwable {
        long start = System.nanoTime();
        Object retval = jp.proceed();
        long end = System.nanoTime();
        LogManager.getLogger().debug("Ejecución de " + jp.getSignature().getName() + " duración " + TimeUnit.NANOSECONDS.toMillis(end - start) + " ms");
        return retval;
    }
}
