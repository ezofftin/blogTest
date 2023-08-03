package kr.ezen.blogTest.aop;

import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AopSample {
    public static void main(String[] args) throws Exception {
        Class myClass = Class.forName("kr.ezen.blogTest.aop.MyClass");
        Object o = myClass.newInstance();

        MyAdvice myAdvice = new MyAdvice();

        for (Method m : myClass.getDeclaredMethods()) {
            myAdvice.invoke(m, o);
        }
    }
}

class MyAdvice {
    Pattern pattern = Pattern.compile("a.*"); // a로 시작하는 문자열

    boolean matches(Method method) {
        Matcher matcher = pattern.matcher(method.getName());
        return matcher.matches();
    }

//    void invoke(Method method, Object obj, Object... args)
//            throws Exception {
//        System.out.println("########## before ~~~~~~~");
//        method.invoke(obj, args); // aaa(), aab(), bbb() 호출
//        System.out.println("~~~~~~~ after ~~~~~~~");
//    }

    // 패턴에 일치하는 메서드에 부가기능 적용하기
    void invoke(Method method, Object obj, Object... args)
            throws Exception {
//        if(matches(method))
        if(method.getAnnotation(Transactional.class) != null)
            System.out.println("########## before ~~~~~~~");

        method.invoke(obj, args); // aaa(), aab(), bbb() 호출

//        if(matches(method))
        if(method.getAnnotation(Transactional.class) != null)
            System.out.println("~~~~~~~ after ~~~~~~~");
    }
}

class MyClass {

    @Transactional
    void aaa() {
        System.out.println("aaa() 메소드 호출");
    }
    void aab() {
        System.out.println("aab() 메소드 호출");
    }
    void bbb() {
        System.out.println("bbb() 메소드 호출");
    }
}
