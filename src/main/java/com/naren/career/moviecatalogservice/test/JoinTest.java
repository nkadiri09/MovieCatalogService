package com.naren.career.moviecatalogservice.test;

public class JoinTest extends Thread {
   public void run() {
      for(int i=1; i <= 3; i++) {
         try {
            Thread.sleep(1000);
         } catch(Exception e) {
            System.out.println(e);
         }
         System.out.println(Thread.currentThread().getName()+"TutorialsPoint "+ i);
      }
   }
   public static void main(String args[]) throws InterruptedException {
      JoinTest t1 = new JoinTest();
      JoinTest t2 = new JoinTest();
      JoinTest t3 = new JoinTest();
      t1.start();
      System.out.println("Thread one started");
      Thread.sleep(10000);
      System.out.println("Thread one ended");
      try {
         t1.join(); // calling join() method
      } catch(Exception e) {
         System.out.println(e);
      }
      t2.setName("Thread-2");
      t2.start();
      t3.setName("Thread-3");
      t3.start();
   }
}