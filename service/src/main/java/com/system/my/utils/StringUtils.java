package com.system.my.utils;

/**
 * @Author :Nibelung
 * @Date ：Created in 14:11 2019/10/28
 * @Description :
 */
public class StringUtils {
    /**
     * 按长度 拆分
     * @param str
     * @param splitLength
     * @return
     */
    public static String[] splitString(String str,int splitLength){
        char[] cs=str.toCharArray();
        int resultArrayLength=cs.length/splitLength;
        if(cs.length%splitLength!=0){
            resultArrayLength++;
        }
        String[] resultArray=new String[resultArrayLength];
        for (int j = 0; j <resultArray.length; j++) {
            if(j==resultArray.length-1){
                splitLength=cs.length-splitLength*j;
            }
            resultArray[j]=new String(cs,splitLength*j,splitLength);
        }
        return resultArray;
    }
}
