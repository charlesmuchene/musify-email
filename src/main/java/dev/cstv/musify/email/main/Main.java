package dev.cstv.musify.email.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

   public static void main(String[] args) {
      ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:context/applicationContext.xml");
   }
}
