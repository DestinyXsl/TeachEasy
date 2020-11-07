package com.homework.teach.util;



import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestCode {

    public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException {

        System.out.println(System.getProperty("user.dir"));//user.dir指定了当前的路径

//        String basePath = System.getProperty("user.dir");
//        String path = basePath + "\\src\\main\\resources\\static\\tcodeImgs\\";
        String path = "C:\\Users\\xsl\\Desktop\\";
        System.out.println("path::"+path);
        BufferedImage image = BarCodeUtils.insertWords(BarCodeUtils.getBarCode("200001010100038"), "(1年2班)(英语)(听写本)(小明)");
        ImageIO.write(image, "jpg", new File(path+"200001010100038"+".jpg"));

//        Calendar cal = Calendar.getInstance();
//        int month = cal.get(Calendar.MONTH) + 1;
//        SimpleDateFormat formatter = new SimpleDateFormat("yy");
//        System.out.println(formatter.format(new Date())+(month >=8?1:0));
//
//        DecimalFormat g1=new DecimalFormat("0000000");
//        String startZeroStr = g1.format(Integer.valueOf(fileName));

//        Integer a = 1,b = 2;
//        System.out.println("before:a="+a+",b="+b);
//        swap(a,b);
//        System.out.println("before:a="+a+",b="+b);
    }
    public static void swap(Integer i1,Integer i2) throws NoSuchFieldException, IllegalAccessException {
        Field field = Integer.class.getDeclaredField("value");
        field.setAccessible(true);
        int tmp = i1.intValue();
        field.set(i1,i2.intValue());
        field.set(i2,tmp);
        System.out.println(tmp);
        System.out.println(Integer.valueOf(tmp));
        System.out.println(Integer.valueOf(tmp));
    }
}
