package ra.edu.business.service.managerStudent;

import ra.edu.business.dao.ManagerStudent.ManagerStudentDAOImp;
import ra.edu.business.model.Pagination;
import ra.edu.business.model.Student;
import ra.edu.validate.Validator;
import ra.edu.validate.ValidatorChoice;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagerStudentServiceImp implements ManagerStudentService {
    private ManagerStudentDAOImp managerStudentDAOImp;
    public ManagerStudentServiceImp() {
        managerStudentDAOImp = new ManagerStudentDAOImp();
    }
    Pagination pagination = new Pagination();

    @Override
    public Student findStudentByEmail(String email) {
        return managerStudentDAOImp.findStudentByEmail(email);
    }

    @Override
    public void findAllStudentAllPagination(Scanner scanner) {
        pagination.setCurrentpage(1);
        pagination.setPagesize(10);
        int totalStudent = managerStudentDAOImp.countTotalStudent();
        pagination.setTotalpages(totalStudent);
        boolean Exit = false;
        do {
            List<Student> studentList = managerStudentDAOImp.findAllStudentPagination(pagination.getPagesize(), pagination.getCurrentpage());
            if(studentList.isEmpty()) {
                System.out.println("\u001B[31mKhông có sinh viên!\u001B[0m");
            }else {
                navigateToStudent(scanner, studentList);
                Exit = pagination.navigate(scanner);
            }
        }while (!Exit);
    }

    @Override
    public int countTotalStudent() {
        return managerStudentDAOImp.countTotalStudent();
    }

    @Override
    public String findPhone(String phone) {
        return managerStudentDAOImp.findPhone(phone);
    }

    @Override
    public Student findStudentById(int id) {
        return managerStudentDAOImp.findStudentById(id);
    }

    @Override
    public void findStudentPagination(Scanner scanner) {
        boolean Exit = false;
        pagination.setCurrentpage(1);
        pagination.setPagesize(10);
        int  totalStudent = 0;
        List<Student> studentList = new ArrayList<Student>();
        System.out.println("1. Tìm kiếm theo email");
        System.out.println("2. Tìm kiếm theo tên");
        System.out.println("3. Tìm kiếm theo mã học viên");
        int choice1 = ValidatorChoice.validater(scanner);
        switch (choice1) {
            case 1:
                String email = Validator.validateString(scanner,1,100,"Nhập vào email: ", "Email");
                studentList = managerStudentDAOImp.findStdByPagation(email,null,"EMAIL",pagination.getCurrentpage(),pagination.getPagesize());
                if(studentList.isEmpty()) {
                    System.out.println("\u001B[31mKhông tìm thấy học viên nào!\u001B[0m");
                }else {
                    totalStudent = countTotalFind(email,null,"EMAIL");
                    pagination.setTotalpages(totalStudent);
                    do {
                        studentList = managerStudentDAOImp.findStdByPagation(email,null,"EMAIL",pagination.getCurrentpage(),pagination.getPagesize());
                        navigateToStudent(scanner, studentList);
                        Exit = pagination.navigate(scanner);
                    }while (!Exit);
                }
                break;
            case 2:
                System.out.print("Nhập vào tên: ");
                String name = Validator.validateString(scanner,1,100,"Nhập vào tên học viên: ", "Tên học viên");
                studentList = managerStudentDAOImp.findStdByPagation(null,name,"NAME",pagination.getCurrentpage(),pagination.getPagesize());
                if(studentList.isEmpty()) {
                    System.out.println("\u001B[31mKhông tìm thấy học viên nào!\u001B[0m");
                }else {
                    totalStudent = countTotalFind(null,name,"NAME");
                    pagination.setTotalpages(totalStudent);
                    do {
                        studentList = managerStudentDAOImp.findStdByPagation(null,name,"NAME",pagination.getCurrentpage(),pagination.getPagesize());
                        navigateToStudent(scanner, studentList);
                        Exit = pagination.navigate(scanner);
                    }while (!Exit);
                }
                break;
            case 3:
                int id = Validator.validateInt(scanner,1,1000,"Nhập vào mã học viên: ","Mã học viên");
                Student student = managerStudentDAOImp.findStudentById(id);
                if(student == null) {
                    System.out.println("\u001B[31mKhông tìm thấy học viên!\u001B[0m");
                }else {
                    System.out.println(student.toString());
                }
                break;
            default:
                System.out.println("\u001B[31mLựa chọn không hợp lệ vui lòng nhập lại!\u001B[0m");
        }
    }

    @Override
    public int countTotalFind(String email, String name, String Search) {
        return managerStudentDAOImp.countTotalFind(email,name,Search);
    }

    @Override
    public void sortStudentPagination(Scanner scanner) {
        boolean Exit = false;
        List<Student> studentList = new ArrayList<>();
        pagination.setCurrentpage(1);
        pagination.setPagesize(10);
        pagination.setTotalpages(countTotalStudent());
        String order = "";
        System.out.println("1. Sắp xếp theo tên");
        System.out.println("2. Sắp xếp theo mã học viên");
        System.out.println("3. Sắp xếp theo email");
        int choice1 = ValidatorChoice.validater(scanner);
        switch (choice1) {
            case 1:
                order = orderByStudent(scanner);
                studentList = managerStudentDAOImp.sortStudent("NAME",order,pagination.getCurrentpage(),pagination.getPagesize());
                if(studentList.isEmpty()) {
                    System.out.println("\u001B[31mKhông có học viên nào!\u001B[0m");
                }else {
                    while(!Exit) {
                        studentList = managerStudentDAOImp.sortStudent("NAME",order,pagination.getCurrentpage(),pagination.getPagesize());
                        navigateToStudent(scanner, studentList);
                        Exit = pagination.navigate(scanner);
                    }
                }
                break;
            case 2:
                order = orderByStudent(scanner);
                studentList = managerStudentDAOImp.sortStudent("ID",order,pagination.getCurrentpage(),pagination.getPagesize());
                if(studentList.isEmpty()) {
                    System.out.println("\u001B[31mKhông có học viên nào!\u001B[0m");
                }else {
                    while(!Exit) {
                        studentList = managerStudentDAOImp.sortStudent("ID",order,pagination.getCurrentpage(),pagination.getPagesize());
                        navigateToStudent(scanner, studentList);
                        Exit = pagination.navigate(scanner);
                    }
                }
                break;
            case 3:
                order = orderByStudent(scanner);
                studentList = managerStudentDAOImp.sortStudent("EMAIL",order,pagination.getCurrentpage(),pagination.getPagesize());
                if(studentList.isEmpty()) {
                    System.out.println("\u001B[31mKhông có học viên nào!\u001B[0m");
                }else {
                    while(!Exit) {
                        studentList = managerStudentDAOImp.sortStudent("EMAIL",order,pagination.getCurrentpage(),pagination.getPagesize());
                        navigateToStudent(scanner, studentList);
                        Exit = pagination.navigate(scanner);
                    }
                }
                break;
            default:
                System.out.println("\u001B[31mLựa chọn không hợp lệ vui lòng nhập lại!\u001B[0m");
        }
    }
    public String orderByStudent(Scanner scanner) {
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
                    System.out.println("\u001B[31mLựa chọn không hợp lệ vui lòng nhập lại!\u001B[0m");
            }
        }
    }
    @Override
    public int countTotalSort(String sortBy, String sortOrder) {
        return managerStudentDAOImp.countSortStudent(sortBy,sortOrder);
    }

    @Override
    public int choiceStudentPagination(Scanner scanner) {
        pagination.setCurrentpage(1);
        pagination.setPagesize(5);
        int totalStudent = managerStudentDAOImp.countTotalStudent();
        pagination.setTotalpages(totalStudent);
        int idStudent = -1;
        do {
            List<Student> studentList = managerStudentDAOImp.findAllStudentPagination(pagination.getPagesize(), pagination.getCurrentpage());
            if (!studentList.isEmpty()) {
                System.out.println("\u001B[36m┌───────┬──────────────────────────┬─────────────────────┬───────────┬───────────┬───────────────────────────┬───────────┐\u001B[0m");
                System.out.printf("\u001B[36m│ \u001B[33m%-5s\u001B[36m │ %-25s│ %-20s│ %-10s│ %-10s│ %-26s│ %-10s│\n\u001B[0m",
                        "Mã HS", "Email", "Tên học sinh", "Ngày sinh", "Giới tính", "Số điện thoại", "Ngày tạo");
                System.out.println("\u001B[36m├───────┼──────────────────────────┼─────────────────────┼───────────┼───────────┼───────────────────────────┼───────────┤\u001B[0m");
                for (Student student : studentList) {
                    student.displayInfo();
                }
                System.out.println("\u001B[36m└───────┴──────────────────────────┴─────────────────────┴───────────┴───────────┴───────────────────────────┴───────────┘\u001B[0m");

                System.out.print("Trang: ");
                if (pagination.getCurrentpage() > 1) {
                    System.out.print("Previous");
                }
                for (int i = 1; i <= pagination.getTotalpages(); i++) {
                    if (i == 1 || i == pagination.getTotalpages() || (i >= pagination.getCurrentpage() - 3 && i <= pagination.getCurrentpage() + 3)) {
                        if (i == pagination.getCurrentpage()) {
                            System.out.printf("\u001B[33m%5d\u001B[0m", i);
                        } else {
                            System.out.printf("%5d", i);
                        }
                    } else if (i == 2 && pagination.getCurrentpage() > 5) {
                        System.out.printf("%5s", "...");
                        i = pagination.getCurrentpage() - 4;
                    } else if (i == pagination.getCurrentpage() + 4 && pagination.getCurrentpage() + 4 < pagination.getTotalpages()) {
                        System.out.printf("%5s", "...");
                        i = pagination.getTotalpages() - 1;
                    }
                }
                if (pagination.getCurrentpage() < pagination.getTotalpages()) {
                    System.out.print("      Next");
                }
                System.out.println();
                if (pagination.getCurrentpage() > 1) System.out.println("P. Trang trước");
                if (pagination.getCurrentpage() < pagination.getTotalpages()) System.out.println("N. Trang tiếp");
                System.out.println("1. Chọn trang");
                System.out.println("2. Chọn đăng ký");
                char choice = ValidatorChoice.validateChoiceChar(scanner);
                switch (choice) {
                    case '1':
                        int page = Validator.validateInt(scanner, 1, pagination.getTotalpages(), "Nhập trang: ", "Trang");
                        pagination.setCurrentpage(page);
                        break;
                    case '2':
                        idStudent = Validator.validateInt(scanner,0,1000,"Nhập vào mã học viên muốn chọn: ", "Mã học viên");
                        return idStudent;
                    case 'P':
                        if (pagination.getCurrentpage() > 1)
                            pagination.setCurrentpage(pagination.getCurrentpage() - 1);
                        break;
                    case 'N':
                        if (pagination.getCurrentpage() < pagination.getTotalpages())
                            pagination.setCurrentpage(pagination.getCurrentpage() + 1);
                        break;
                    default:
                        System.out.println("\u001B[31mLựa chọn không hợp lệ vui lòng nhập lại!\u001B[0m");
                }
            } else {
                System.out.println("\u001B[31mKhông có học viên nào!\u001B[0m");
                break;
            }
        } while (true);
        return idStudent;
    }

    @Override
    public List<Student> findAll() {
        return List.of();
    }

    @Override
    public boolean save(Student student) {
        return managerStudentDAOImp.save(student);
    }

    @Override
    public boolean update(Student student) {
        return managerStudentDAOImp.update(student);
    }

    @Override
    public boolean delete(Student student) {
        return managerStudentDAOImp.delete(student);
    }

    public void navigateToStudent(Scanner scanner, List<Student> students) {
        System.out.println("\u001B[36m┌───────┬──────────────────────────┬─────────────────────┬───────────┬───────────┬───────────────────────────┬───────────┐\u001B[0m");
        System.out.printf("\u001B[36m│ \u001B[33m%-5s\u001B[36m │ %-25s│ %-20s│ %-10s│ %-10s│ %-26s│ %-10s│\n\u001B[0m",
                "Mã HS", "Email", "Tên học sinh", "Ngày sinh", "Giới tính", "Số điện thoại", "Ngày tạo");
        System.out.println("\u001B[36m├───────┼──────────────────────────┼─────────────────────┼───────────┼───────────┼───────────────────────────┼───────────┤\u001B[0m");
        for (Student student : students) {
            student.displayInfo();
        }
        System.out.println("\u001B[36m└───────┴──────────────────────────┴─────────────────────┴───────────┴───────────┴───────────────────────────┴───────────┘\u001B[0m");
    }
}
