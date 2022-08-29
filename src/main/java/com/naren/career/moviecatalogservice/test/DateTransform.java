package com.naren.career.moviecatalogservice.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.*;

public class DateTransform {

    public static List<String> transformDateFormat(List<String> dates) {

        List<String> formatedDateList = new ArrayList<>();
        for (String date : dates) {

            if (date.contains("-") || date.contains("/")) {
                String returnFormat = "YYYYMMDD";
                String format = null;
                if (date.contains("-"))
                    format = "MM-DD-YYYY";
                else if (date.contains("/")) {
                    char atTwo = date.charAt(2);
                    char atFour = date.charAt(4);
                    System.out.println("atFour is" + atFour);
                    System.out.println("atTwo is" + atTwo);
                    if (Character.toString(atTwo).equals("/"))
                        format = "DD/MM/YYYY";
                    else if (Character.toString(atFour).equals("/"))
                        format = "YYYY/MM/DD";
                }
                Date parsedDate = null;
                SimpleDateFormat sDateFormate = new SimpleDateFormat(format);
                try {
                    parsedDate = sDateFormate.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                sDateFormate = new SimpleDateFormat(returnFormat);
                String formatedDate = sDateFormate.format(parsedDate);
                formatedDateList.add(formatedDate);
            }
        }
        return formatedDateList;
    }



    public static void main(String[] args) {        
        List<String> dates = transformDateFormat(Arrays.asList("2010/02/20", "19/12/2016", "11-18-2012", "20130720"));
        for(String date : dates) {
            System.out.println(date);
        }
    }
}