package ra.edu.validate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Validator {
    private static final String PHONE_VIETTEL_PREFIXES = "086|096|097|098|039|038|037|036|035|034|033|032";
    private static final String PHONE_VINAPHONE_PREFIXES = "091|094|088|083|084|085|081|082";
    private static final String PHONE_MOBIFONE_PREFIXES = "070|079|077|076|078|089|090|093";
    public static int validateInt(Scanner scanner,int min,int max,String message, String name) {
        while (true){
            System.out.print("\u001B[37m"+message+"\u001B[0m");
            String input = scanner.nextLine();
            if (input.isEmpty()){
                System.out.println("\u001B[31m" + name + " không được để trống"+"\u001B[0m");
            }else {
                try {
                    int valInput = Integer.parseInt(input);
                    if (Integer.parseInt(input) > max || Integer.parseInt(input) < min) {
                        System.out.println("\u001B[31m" + name + " không hợp lệ"+"\u001B[0m");
                    }else {
                        return valInput;
                    }
                }catch (NumberFormatException e) {
                    System.out.println("\u001B[31m" + name + " nhập vào không phải số nguyên!"+"\u001B[0m");
                }
            }
        }
    }

    public static String validateString(Scanner scanner,int minLength,int maxLength,String message, String name){
        while(true){
            System.out.print("\u001B[37m"+message+"\u001B[0m");
            String input = scanner.nextLine().trim();
            if(input.isEmpty()){
                System.out.println("\u001B[31m" + name + " không được để trống"+"\u001B[0m");
            }else if(input.length() < minLength || input.length() > maxLength){
                System.out.println("\u001B[31m" + "Độ dài "+name+" không hợp lệ"+"\u001B[0m");
            }else {
                return input;
            }
        }
    }
    public static String validateEmail(Scanner scanner){
        while(true){
            System.out.print("\u001B[37mNhập email: \u001B[0m");
            String input = scanner.nextLine().trim();
            String regexEmail = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            if(input.isEmpty()){
                System.out.println("\u001B[31mEmail không được để trống!\u001B[0m");
            }else if(!input.matches(regexEmail)){
                System.out.println("\u001B[31mDữ liệu không đúng định dạng email!\u001B[0m");
            }else {
                return input;
            }
        }
    }

    public static String validPhoneNumberVN(Scanner scanner) {
        String phoneRegex = "(" + PHONE_VIETTEL_PREFIXES + "|" + PHONE_VINAPHONE_PREFIXES + "|" + PHONE_MOBIFONE_PREFIXES + ")\\d{7}";
        while (true) {
            System.out.print("\u001B[37mNhập vào số điện thoại: \u001B[0m");
            String phoneNumber = scanner.nextLine().trim();
            if (phoneNumber.isEmpty()) {
                System.out.println("\u001B[31mSố điện thoại không được để trống\u001B[0m");
            } else if (!phoneNumber.matches(phoneRegex)) {
                System.out.println("\u001B[31mSố điện thoại không đúng định dạng số Việt Nam\u001B[0m");
            } else {
                return phoneNumber;
            }
        }
    }
    public static LocalDate validateDate(Scanner scanner, String message, String name) {
        while (true) {
            System.out.print("\u001B[37m"+ message +"\u001B[0m");
            String input = scanner.nextLine().trim();
            String regexDate = "\\d{2}-\\d{2}-\\d{4}";
            if(input.isEmpty()){
                System.out.println("\u001B[31m" + name + " không được để trống"+"\u001B[0m");
            }else if(!input.matches(regexDate)){
                System.out.println("\u001B[31mĐịnh dạng ngày khoomg hợp lệ!\u001B[0m");
            }else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate date = LocalDate.parse(input, formatter);
                return date;
            }
        }
    }
}
