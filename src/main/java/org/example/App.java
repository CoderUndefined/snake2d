package org.example;

class Multiclass {
    int x = 0;

    public Multiclass(int x) {
        this.x = x;
    }

    public int doubleNum() {
        return x * 2;
    }

    public boolean b() {
        return true;
    }
}

/**
 * Hello world!
 *
 */
public class App {

    public static int getBigChars(String password) {
        int count = 0;

        for(int i = 0; i < password.length(); i++) {
            if(password.charAt(i) >= 'A' && password.charAt(i) <= 'Z') {
                count++;
            }
        }
        return count;
    }


    public static boolean checkPassword(String password) {
        if(password.length() >= 20) {
            return  true;
        }
        else if(password.length() >= 8) {
            boolean hasBigChar = false;
            boolean hasSmallChar = false;
            boolean hasNumber = false;
            boolean hasSpecialChar = false;

            for(int i = 0; i < password.length(); i++) {
                if(password.charAt(i) >= 'A' && password.charAt(i) <= 'Z') {
                    hasBigChar = true;
                }
                if(password.charAt(i) >= 'a' && password.charAt(i) <= 'z') {
                    hasSmallChar = true;
                }
                if(password.charAt(i) >= '0' && password.charAt(i) <= '9') {
                    hasNumber = true;
                }
                if(password.charAt(i) == '!' || password.charAt(i) == '"' ) {
                    hasSpecialChar = true;
                }
            }
            if(hasBigChar && hasSmallChar && hasNumber && hasSpecialChar) {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
    }
}


// Create password check validator according to test driven development
//
//public static int getBigChars(String password) {
//    return 0;
//}
