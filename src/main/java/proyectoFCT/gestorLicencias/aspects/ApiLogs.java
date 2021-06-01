package proyectoFCT.gestorLicencias.aspects;

import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Aspect
public class ApiLogs {

    //Puntos de corte

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void peticionPost() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void peticionGet() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void peticionDelete() {
    }
    //peticion POST

    @Before("peticionPost()")
    public void apiRequestLog(JoinPoint jp) {
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info("--------------- peticion POST ---------------");
        String log = "Log entrada servicio Rest por metodo POST: " + jp.getSignature().getName() + " >>>";
        for (Object arg : jp.getArgs()) {
            log += "\n ARG: " + arg;
        }
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info(log);
    }

    @AfterReturning(pointcut = "peticionPost()", returning = "result")
    public void apiResponseLog(JoinPoint jp, Object result) {
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info(">>> RETURN >>> " + jp.getSignature().getName() + ": " + result);
    }

    @AfterThrowing(pointcut = "peticionPost()", throwing = "exception")
    public void apiResponseExceptionLog(JoinPoint jp, Exception exception) {
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).error(">>> RETURN EXCEPTION >>> " + jp.getSignature().getName() + ": " + exception.getClass().getSimpleName());
    }

    @Around("peticionPost()")
    public Object tiempoEjecucionPost(ProceedingJoinPoint jp) throws Throwable {
        long start = System.nanoTime();
        Object retval = jp.proceed();
        long end = System.nanoTime();
        String methodName = jp.getSignature().getName();
        LogManager.getLogger().debug("Tiempo de ejecución del servicio " + methodName + " " + TimeUnit.NANOSECONDS.toMillis(end - start) + " ms");
        return retval;
    }

    //peticion GET

    @Before("peticionGet()")
    public void apiRequestGetLog(JoinPoint jp) {
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info("--------------- peticion GET ---------------");
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info("Log entrada servicio Rest por metodo GET: " + jp.getSignature().getName());
    }

    @AfterReturning(pointcut = "peticionGet()", returning = "result")
    public void apiResponseGetLog(JoinPoint jp, Object result) {
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info(">>> RETURN >>> " + jp.getSignature().getName() + ": " + result);
    }

    @AfterThrowing(pointcut = "peticionGet()", throwing = "exception")
    public void apiResponseExceptionGetLog(JoinPoint jp, Exception exception) {
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).error(">>> RETURN EXCEPTION >>> " + jp.getSignature().getName() + ": " + exception.getClass().getSimpleName());
    }

    @Around("peticionGet()")
    public Object tiempoEjecucionGet(ProceedingJoinPoint jp) throws Throwable {
        long start = System.nanoTime();
        Object retval = jp.proceed();
        long end = System.nanoTime();
        LogManager.getLogger().info("Tiempo de ejecución del servicio " + jp.getSignature().getName() + " " + TimeUnit.NANOSECONDS.toMillis(end - start) + " ms");
        return retval;
    }

    //peticion DELETE
    @Before("peticionDelete()")
    public void apiRequestDeleteLog(JoinPoint jp) {
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info("--------------- peticion DELETE ---------------");
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info("Log entrada servicio Rest por metodo DELETE: " + jp.getSignature().getName());
    }

    @AfterReturning(pointcut = "peticionDelete()", returning = "result")
    public void apiResponseDeleteLog(JoinPoint jp, Object result) {
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info(">>> RETURN >>> " + jp.getSignature().getName() + ": " + result);
    }

    @AfterThrowing(pointcut = "peticionDelete()", throwing = "exception")
    public void apiResponseExceptionDeleteLog(JoinPoint jp, Exception exception) {
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).error(">>> RETURN EXCEPTION >>> " + jp.getSignature().getName() + ": " + exception.getClass().getSimpleName());
    }

    @Around("peticionDelete()")
    public Object tiempoEjecucionDelete(ProceedingJoinPoint jp) throws Throwable {
        long start = System.nanoTime();
        Object retval = jp.proceed();
        long end = System.nanoTime();
        LogManager.getLogger().info("Tiempo de ejecución del servicio " + jp.getSignature().getName() + " " + TimeUnit.NANOSECONDS.toMillis(end - start) + " ms");
        return retval;
    }

}
