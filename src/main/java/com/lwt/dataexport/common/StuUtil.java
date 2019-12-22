package com.lwt.dataexport.common;

import com.lwt.dataexport.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StuUtil {
    public static List<Student> getStus(int amount){
        List<Student> result = new ArrayList<Student>();
        for(int i=0;i<amount;i++){
            Student student = new Student();
            student.setDept(i);
            student.setName("李明"+i);
            student.setSex((i%2==0)?'男':'女');
            student.setAddress((i%2==0)?"男生宿舍":"女生宿舍");
            student.setPhone(createMobile());
            result.add(student);
        }
        return  result;
    }



    //中国移动
    private static final String[] CHINA_MOBILE = {
            "134", "135", "136", "137", "138", "139", "150", "151", "152", "157", "158", "159",
            "182", "183", "184", "187", "188", "178", "147", "172", "198"
    };

    /**
     * 生成手机号
     */
    private static String createMobile() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        String mobile01;//手机号前三位
        int temp;
        mobile01 = CHINA_MOBILE[random.nextInt(CHINA_MOBILE.length)];
        if (mobile01.length() > 3) {
            return mobile01;
        }
        sb.append(mobile01);
        //生成手机号后8位
        for (int i = 0; i < 8; i++) {
            temp = random.nextInt(10);
            sb.append(temp);
        }
        return sb.toString();
    }
}
