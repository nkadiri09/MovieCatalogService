package com.naren.career.moviecatalogservice.test;

public class StringReverse {
    public static void main(String[] args) {
        System.out.println("reverseString is: "+reverseString("ardneran"));
        System.out.println("Factorial of 5 is: "+factorial(5));
    }
    public static String reverseString(String input){
        String output = "";
        if(input==null)
            return "";
        for(int i=input.length()-1; i>=0;i--)
            output = output + input.charAt(i);
        return output;
    }


   static Integer factorial(Integer a){
        if(a==1){
            return 1;
        }
        return a*factorial(a-1);
    }
}
