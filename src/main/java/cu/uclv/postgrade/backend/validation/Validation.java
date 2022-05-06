package cu.uclv.postgrade.backend.validation;

public class Validation {

    //Methods
    public static boolean validateNumber(String s){
        for (int i = 0; i < s.length(); i++){
            if (!Character.isDigit(s.charAt(i)))
                return false;
        }
        return true;
    }

    public static boolean validateName(String s){
        for (int i = 0; i < s.length(); i++){
            if (!Character.isLetter(s.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
