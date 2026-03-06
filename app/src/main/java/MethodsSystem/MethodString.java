
package MethodsSystem;

public class MethodString{

    public static String removeBlankLines(String s){
        return s.replaceAll("(?m)^[ \t]*\r?\n", "");
    }
    
    public static boolean checkLike(Object value, String like){
        if(value == null) return false;
        return checkLike(value.toString(), like);
    }
    
    public static boolean checkLike(Integer value, String like){
        if(value == null) return false;
        return checkLike(String.valueOf(value), like);
    }
    
    public static boolean checkLike(String value, String like){
        if(value == null) return false;
        value = value.toLowerCase();
        like = like.toLowerCase();
        return value.startsWith(like) || value.contains(like) || value.endsWith(like);
    }

}
