package com.example.webik.config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
@Component
public class ApplicationContext {

        public static final AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(myConfig.class);

}