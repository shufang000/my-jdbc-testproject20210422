package com.shufang.data;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataTransDemo {
    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        Date date = sdf.parse("2021-02-13");

        System.out.println(date);

        System.out.println(sdf.format(date));


        java.sql.Date date1 = new java.sql.Date(date.getTime());

        System.out.println(date1);

        String format = sdf.format(date1);
        System.out.println(format);

        Timestamp timestamp = new Timestamp(date.getTime());
        System.out.println(timestamp);


    }
}
