package util;

import java.util.Random;

public class CheckCodeUtils {

    private static String[] arr = {"0","1","2","3","4","5","6","7","8","9"};

    private static Random random = new Random();

    //生成随机验证码
    public static String generateCode(int length){
        StringBuilder sb = new StringBuilder();
        for(int i =0 ; i<length ; i++ ){
            sb.append(arr[random.nextInt(arr.length)]);
        }
        return sb.toString();
    }

}
