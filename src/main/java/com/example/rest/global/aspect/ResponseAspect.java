package com.example.rest.global.aspect;

import com.example.rest.global.dto.RsData;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ResponseAspect {
    @Around("""
            (
                within
                (
                    @org.springframework.web.bind.annotation.RestController *
                )
                &&
                (
                    @annotation(org.springframework.web.bind.annotation.GetMapping)
                    ||
                    @annotation(org.springframework.web.bind.annotation.PostMapping)
                    ||
                    @annotation(org.springframework.web.bind.annotation.PutMapping)
                    ||
                    @annotation(org.springframework.web.bind.annotation.DeleteMapping)
                )
            )
            ||
            @annotation(org.springframework.web.bind.annotation.ResponseBody)
            """)
    public Object test(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("pre"); // 전처리

        Object rst = joinPoint.proceed(); // 실제 수행 메서드

        if(rst instanceof RsData rsData) {
            String msg = rsData.getMsg();
            System.out.println("msg :" + msg);
        }

        System.out.println("post");
        return rst;
    }
}