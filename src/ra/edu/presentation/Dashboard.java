package ra.edu.presentation;

import ra.edu.business.service.course.CourseServiceImp;
import ra.edu.business.service.managerStudent.ManagerStudentServiceImp;
import ra.edu.validate.ValidatorChoice;

import java.util.Scanner;

public class Dashboard {
    private static CourseServiceImp courseServiceImp;
    private static ManagerStudentServiceImp managerStudentServiceImp;
    private Dashboard() {
        courseServiceImp = new CourseServiceImp();
        managerStudentServiceImp = new ManagerStudentServiceImp();
    }
    public static void main(String[] args) {
        Dashboard dashboard = new Dashboard();
        Scanner scanner = new Scanner(System.in);
        boolean Exit = false;
        do {
            System.out.println("\u001B[36m┌─────────────────────────────────────────────────┐");
            System.out.println("│                   THỐNG KÊ                      │");
            System.out.println("├─────────────────────────────────────────────────┤");
            System.out.println("│1. Thống kê tổng số lượng khóa học và học viên   │");
            System.out.println("│2. Thống kê học viên theo từng khóa học          │");
            System.out.println("│3. Top 5 khóa học dông học viên nhất             │");
            System.out.println("│4. Liệt kê khóa học có trên 10 học viên          │");
            System.out.println("│5. Quay về menu chính                            │");
            System.out.println("└─────────────────────────────────────────────────┘\u001B[0m");
            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    totalCourseAndStudent();
                    break;
                case 2:
                    courseServiceImp.totalStudentsOfCourse(scanner);
                    break;
                case 3:
                    courseServiceImp.top5Course(scanner);
                    break;
                case 4:
                    courseServiceImp.courseMore10Std(scanner);
                    break;
                case 5:
                    Exit = true;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn từ 1 - 5!");
            }
        }while (!Exit);
    }
    public static void totalCourseAndStudent() {
        int totalCourse = courseServiceImp.totalCourse();
        int totalStudent = managerStudentServiceImp.countTotalStudent();

        System.out.println("Tổng số khóa học: " + totalCourse);
        System.out.println("Tổng số học viên: " + totalStudent);
    }
}
