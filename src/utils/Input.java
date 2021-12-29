package utils;

import java.util.Scanner;

public class Input {
    private static Scanner instance;

    private Input() {

    }
    public static Scanner getInstance(){
        if( instance ==null){
             instance = new Scanner(System.in);
        }
        return instance;
    }
    public static boolean isInputOutRange(int choice, int start,int end){
        if (start > choice  || end < choice){
            return true;
        }
        return false;
    }
    public static int getInputWithValidate(int start,int end){
        System.out.print("Your choose: ");
        int choice = getInstance().nextInt();
        if(isInputOutRange(choice,start,end)){
            System.out.println("Your choice is invalid");
            return -1;
        }
        return choice;
    }

}
