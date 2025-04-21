package ra.edu.business.service.Enrollment;

import ra.edu.ManiApplication;
import ra.edu.business.dao.Enrollment.EnrollmentDAOImp;
import ra.edu.business.model.Enrollment;
import ra.edu.business.model.Pagination;
import ra.edu.business.model.RegisteredCurse;
import ra.edu.business.model.Student;
import ra.edu.validate.Validator;
import ra.edu.validate.ValidatorChoice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EnrollmentServiceImp implements EnrollmentService{
    private EnrollmentDAOImp enrollmentDAOImp;
    public EnrollmentServiceImp() {
        enrollmentDAOImp = new EnrollmentDAOImp();
    }
    Pagination pagination = new Pagination();
    @Override
    public List<Enrollment> findAll() {
        return List.of();
    }

    @Override
    public boolean save(Enrollment enrollment) {
        return enrollmentDAOImp.save(enrollment);
    }

    @Override
    public boolean update(Enrollment enrollment) {
        return false;
    }

    @Override
    public boolean delete(Enrollment enrollment) {
        return enrollmentDAOImp.delete(enrollment);
    }

    @Override
    public void listEnrollmentRegistered(Scanner scanner) {
        pagination.setCurrentpage(1);
        pagination.setPagesize(2);
        int totaledEnrollment = totaledEnrollment(ManiApplication.currentUser.getId());
        pagination.setTotalpages(totaledEnrollment);
        boolean Exit = false;
        do {
            List<RegisteredCurse> listEnrollmentRegistered = enrollmentDAOImp.listEnrollmentRegistered(ManiApplication.currentUser.getId(), pagination.getPagesize(),pagination.getCurrentpage());
            if(listEnrollmentRegistered.isEmpty()) {
                System.out.println("Chưa đăng ký khóa học nào");
            }else {
                navigateToStudent(scanner, listEnrollmentRegistered);
                char choice = Character.toUpperCase(scanner.nextLine().charAt(0));
                switch (choice) {
                    case '1':
                        int page = Validator.validateInt(scanner, 1, pagination.getTotalpages(), "Nhập trang: ", "Trang");
                        pagination.setCurrentpage(page);
                        break;
                    case '2':
                        Exit = true;
                        break;
                    case 'P':
                        if (pagination.getCurrentpage() > 1)
                            pagination.setCurrentpage(pagination.getCurrentpage() - 1);
                        break;
                    case 'N':
                        if (pagination.getCurrentpage() < pagination.getTotalpages())
                            pagination.setCurrentpage(pagination.getCurrentpage() + 1);
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ vui lòng nhập lại!");
                }
            }
        }while (!Exit);
    }

    @Override
    public int totaledEnrollment(int idStudent) {
        return enrollmentDAOImp.totaledEnrollment(idStudent);
    }

    @Override
    public void sortRegisteredEnrollment(Scanner scanner) {
        boolean Exit = false;
        boolean Next = false;
        List<RegisteredCurse> listEnrollmentRegistered = new ArrayList<>();
        pagination.setCurrentpage(1);
        pagination.setPagesize(10);
        int totaledEnrollment = totaledEnrollment(ManiApplication.currentUser.getId());
        pagination.setTotalpages(totaledEnrollment);
        String order = "";
        System.out.println("============== Menu sắp xếp =====================");
        System.out.println("1. Sắp xếp theo tên");
        System.out.println("2. Sắp xếp theo ngày đăng ký");
        int choice1 = ValidatorChoice.validater(scanner);
        switch (choice1) {
            case 1:
                order = orderByEnrollment(scanner);
                listEnrollmentRegistered = enrollmentDAOImp.sortedEnrollment(ManiApplication.currentUser.getId(), "Name",order,pagination.getPagesize(),pagination.getCurrentpage());
                if(listEnrollmentRegistered.isEmpty()) {
                    System.out.println("Chưa đăng ký khóa học nào");
                }else {
                    while(!Next) {
                        listEnrollmentRegistered = enrollmentDAOImp.sortedEnrollment(ManiApplication.currentUser.getId(), "Name",order,pagination.getPagesize(),pagination.getCurrentpage());
                        navigateToStudent(scanner, listEnrollmentRegistered);
                        char choice = Character.toUpperCase(scanner.nextLine().charAt(0));
                        switch (choice) {
                            case '1':
                                int page = Validator.validateInt(scanner, 1, pagination.getTotalpages(), "Nhập trang: ", "Trang");
                                pagination.setCurrentpage(page);
                                break;
                            case '2':
                                Next = true;
                            case 'P':
                                if (pagination.getCurrentpage() > 1)
                                    pagination.setCurrentpage(pagination.getCurrentpage() - 1);
                                break;
                            case 'N':
                                if (pagination.getCurrentpage() < pagination.getTotalpages())
                                    pagination.setCurrentpage(pagination.getCurrentpage() + 1);
                                break;
                            default:
                                System.out.println("Lựa chọn không hợp lệ vui lòng nhập lại!");
                        }
                    }
                }
                break;
            case 2:
                order = orderByEnrollment(scanner);
                listEnrollmentRegistered = enrollmentDAOImp.sortedEnrollment(ManiApplication.currentUser.getId(), "CREATE_AT",order,pagination.getPagesize(),pagination.getCurrentpage());
                if(listEnrollmentRegistered.isEmpty()) {
                    System.out.println("Chưa đăng ký khóa học nào");
                }else {
                    while(!Next) {
                        listEnrollmentRegistered = enrollmentDAOImp.sortedEnrollment(ManiApplication.currentUser.getId(), "CREATE_AT",order,pagination.getPagesize(),pagination.getCurrentpage());
                        navigateToStudent(scanner, listEnrollmentRegistered);
                        char choice = Character.toUpperCase(scanner.nextLine().charAt(0));
                        switch (choice) {
                            case '1':
                                int page = Validator.validateInt(scanner, 1, pagination.getTotalpages(), "Nhập trang: ", "Trang");
                                pagination.setCurrentpage(page);
                                break;
                            case '2':
                                Next = true;
                            case 'P':
                                if (pagination.getCurrentpage() > 1)
                                    pagination.setCurrentpage(pagination.getCurrentpage() - 1);
                                break;
                            case 'N':
                                if (pagination.getCurrentpage() < pagination.getTotalpages())
                                    pagination.setCurrentpage(pagination.getCurrentpage() + 1);
                                break;
                            default:
                                System.out.println("Lựa chọn không hợp lệ vui lòng nhập lại!");
                        }
                    }
                }
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
        }
    }

    public String orderByEnrollment(Scanner scanner) {
        while (true){
            System.out.println("1. Tăng dần");
            System.out.println("2. Giảm dần");
            int choice1 = ValidatorChoice.validater(scanner);
            switch (choice1) {
                case 1:
                    return "asc";
                case 2:
                    return "desc";
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
            }
        }
    }

    public void navigateToStudent(Scanner scanner, List<RegisteredCurse> registeredCurses) {
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("|%-10s|%-12s|%-30s|%-12s|%-22s|%-20s|\n",
                "Mã đăng ký", "Mã khóa học", "Tên khóa học", "Thời lượng", "Giảng viên", "Ngày đăng ký");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        for (RegisteredCurse registeredCurse : registeredCurses) {
            registeredCurse.displayData();
        }
        System.out.print("Trang: ");
        if (pagination.getCurrentpage() > 1) {
            System.out.print("Previous      ");
            if (pagination.getCurrentpage() >= 3) System.out.print("... ");
            System.out.print(pagination.getCurrentpage() - 1);
        }
        System.out.print("\u001B[33m" + "    " + pagination.getCurrentpage() + "     " + "\u001B[0m");
        if (pagination.getCurrentpage() < pagination.getTotalpages()) {
            System.out.print(" " + (pagination.getCurrentpage() + 1));
            if (pagination.getTotalpages() - pagination.getCurrentpage() >= 2) System.out.print(" ...");
            System.out.print("      Next");
        }
        System.out.println();
        if (pagination.getCurrentpage() > 1) System.out.println("P. Trang trước");
        if (pagination.getCurrentpage() < pagination.getTotalpages()) System.out.println("N. Trang tiếp");
        System.out.println("1. Chọn trang");
        System.out.println("2. Thoát");
        System.out.print("Lựa chọn: ");
    }
}
