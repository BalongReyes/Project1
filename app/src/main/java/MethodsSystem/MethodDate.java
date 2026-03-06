
package MethodsSystem;

import ConsoleSystem.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class MethodDate{

    private static SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
    
    public static int[] toIntegerDate(Date date){
        if(date == null) return null;
        String[] strArray = formatDate.format(date).split("-");
        return new int[]{Integer.parseInt(strArray[0]), Integer.parseInt(strArray[1]), Integer.parseInt(strArray[2])};
    }
    
    public static boolean isEqual(Date date1, Date date2){
        int[] date1Array = toIntegerDate(date1);
        int[] date2Array = toIntegerDate(date2);
        return Arrays.equals(date1Array, date2Array);
    }
    
    public static Date toDate(int day, int month, int year){
        try{
            return formatDate.parse(day + "-" + month + "-" + year);
        }catch(ParseException e){
            Console.errorOut("Convert to date parse exception", e);
        }
        return null;
    }

}
