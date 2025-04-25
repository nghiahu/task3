package ra.edu.business.model;

import ra.edu.validate.Validator;
import ra.edu.validate.ValidatorChoice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pagination {
    private int pagesize;
    private int currentpage;
    private int totalpages;
    public Pagination() {
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getCurrentpage() {
        return currentpage;
    }

    public void setCurrentpage(int currentpage) {
        this.currentpage = currentpage;
    }

    public int getTotalpages() {
        return totalpages;
    }

    public void setTotalpages(int total) {
        this.totalpages = total / this.pagesize;
        if(total % this.pagesize != 0) {
            totalpages++;
        }
    }
    public boolean navigate(Scanner scanner) {
            System.out.print("Trang: ");
            if (currentpage > 1) {
                System.out.print("Previous      ");
                if (currentpage >= 3) System.out.print("... ");
                System.out.print(currentpage - 1);
            }
            System.out.print("\u001B[33m" + "    " + currentpage + "     " + "\u001B[0m");
            if (currentpage < totalpages) {
                System.out.print(" " + (currentpage + 1));
                if (totalpages - currentpage >= 2) System.out.print(" ...");
                System.out.print("      Next");
            }
            System.out.println();
            if (currentpage > 1) System.out.println("P. Trang trước");
            if (currentpage < totalpages) System.out.println("N. Trang tiếp");
            System.out.println("1. Chọn trang");
            System.out.println("2. Thoát");
            char choice = ValidatorChoice.validateChoiceChar(scanner);
            switch (choice) {
                case '1':
                    int page = Validator.validateInt(scanner, 1, totalpages, "Nhập trang: ", "Trang");
                    this.setCurrentpage(page);
                    break;
                case '2':
                    return true;
                case 'P':
                    if (currentpage > 1)
                        this.setCurrentpage(currentpage - 1);
                    break;
                case 'N':
                    if (currentpage < totalpages)
                        this.setCurrentpage(currentpage + 1);
                    break;
                default:
                    System.out.println("\u001B[31mLựa chọn không hợp lệ vui lòng nhập lại!\u001B[0m");
            }
        return false;
    }
}
