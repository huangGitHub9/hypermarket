package cn.xu.core.utils;

public class SubStringTimeUtils {


    /**
     *
     * @param stringTime "2018-02-01 - 2018-03-01"
     * @return
     */
    public static String getStatTime(String stringTime){
        if(stringTime==null || stringTime == ""){
            return null;
        }
        return  stringTime.substring(0,10);
    }

    /**
     *
     * @param stringTime "2018-02-01 - 2018-03-01"
     * @return
     */
    public static String getEndTime(String stringTime){
        if(stringTime ==null || stringTime == "" || stringTime.length()<13){
            return null;
        }
        return  stringTime.substring(13);
    }



}
