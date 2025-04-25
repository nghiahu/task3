package ra.edu.validate;

import java.util.Scanner;

public class ValidatorChoice {
    public static int validater(Scanner scanner){
        while (true){
            System.out.print("\u001B[35m➤ Nhập lựa chọn: \u001B[0m");
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            }catch (NumberFormatException e){
                System.out.print("\u001B[31mLựa chọn không hợp lệ vui lòng nhập lại!\u001B[0m\n");
            }
        }
    }
    public static char validateChoiceChar(Scanner scanner){
        while (true){
            System.out.print("\u001B[35m➤ Lựa chọn: \u001B[0m");
            String input = scanner.nextLine();
            if (input.length() == 1){
                try {
                    return Character.toUpperCase(input.charAt(0));
                }catch (IndexOutOfBoundsException e){
                    System.out.print("\u001B[31mLựa chọn không hợp lệ vui lòng nhập lại!\u001B[0m\n");
                }
            }else {
                System.out.print("\u001B[31mLựa chọn không hợp lệ vui lòng nhập lại!\u001B[0m\n");
            }
        }
    }
}
