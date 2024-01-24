package com.example.springsample.login.aspect;

// import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
// import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
// import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    
    // @Around Bean名で指定(DIに登録されているBean名でAOP対象を指定できる)
    @Around("bean(*Controller)")
    public Object startLog(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("メソッド開始 : " + jp.getSignature());

        try {
            // メソッド実行
            Object result = jp.proceed();

            System.out.println("メソッド終了 : " + jp.getSignature());

            return result;
        } catch(Exception e) {
            System.out.println("メソッド異常終了 : " + jp.getSignature());
            e.printStackTrace();
            throw e;
        }
    }

     // UserDaoクラスのログ出力
     @Around("execution(* *..*.*UserDao*.*(..))")
     public Object daoLog(ProceedingJoinPoint jp) throws Throwable {
     
        System.out.println("メソッド開始 : " + jp.getSignature()); 

        try {
            Object result = jp.proceed();

            System.out.println("メソッド終了 : " + jp.getSignature());

            return result;
            
        } catch(Exception e) {
            System.out.println("メソッド異常終了 : " + jp.getSignature());
            e.printStackTrace();
            throw e;
        }
     }


    // AOPの実装 execution(<戻り値><パッケージ名>.<クラス名>.<メソッド名>(<引数>))
    // @Before("execution(* *..*.*Controller.*(..))")
    // public void startLog(JoinPoint jp) {
    //     System.out.println("メソッド開始 : " + jp.getSignature());
    // }

    // AOPの実装 *: 任意の文字列 ..: 任意の引数 
    // @After("execution(* *..*.*Controller.*(..))")
    // public void endLog(JoinPoint jp) {
    //     System.out.println("メソッド終了 : " + jp.getSignature());
    // }
}
