package ra.edu.business.service.enrollment;

import ra.edu.ManiApplication;
import ra.edu.business.dao.Course.CourseDAOImp;
import ra.edu.business.dao.Enrollment.EnrollmentDAOImp;
import ra.edu.business.model.*;
import ra.edu.business.service.course.CourseServiceImp;
import ra.edu.validate.Validator;
import ra.edu.validate.ValidatorChoice;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EnrollmentServiceImp implements EnrollmentService{
    private EnrollmentDAOImp enrollmentDAOImp;
    private CourseServiceImp courseServiceImp;
    private CourseDAOImp courseDAOImp;
    public EnrollmentServiceImp() {
        enrollmentDAOImp = new EnrollmentDAOImp();
        courseServiceImp = new CourseServiceImp();
        courseDAOImp = new CourseDAOImp();
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
        pagination.setPagesize(5);
        int totaledEnrollment = totaledEnrollment(ManiApplication.currentUser.getId());
        pagination.setTotalpages(totaledEnrollment);
        boolean Exit = false;
        do {
            List<RegisteredCurse> listEnrollmentRegistered = enrollmentDAOImp.listEnrollmentRegistered(ManiApplication.currentUser.getId(), pagination.getPagesize(),pagination.getCurrentpage());
            if(listEnrollmentRegistered.isEmpty()) {
                System.out.println("Chưa đăng ký khóa học nào");
            }else {
                navigateToStudent(scanner, listEnrollmentRegistered);
                char choice = ValidatorChoice.validateChoiceChar(scanner);
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
                        char choice = ValidatorChoice.validateChoiceChar(scanner);
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
                        char choice = ValidatorChoice.validateChoiceChar(scanner);
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

    @Override
    public boolean isRegistered(int idCourse, int idStudent) {
        return enrollmentDAOImp.isRegistered(idCourse, idStudent);
    }

    @Override
    public void listStudentEnroll(Scanner scanner) {
        pagination.setCurrentpage(1);
        pagination.setPagesize(5);
        int totaledEnrollment = totalEnrollment();
        pagination.setTotalpages(totaledEnrollment);
        boolean Exit = false;
        do {
            List<StudentEnroll> listStudentEnroll = enrollmentDAOImp.listStudentEnroll(pagination.getPagesize(),pagination.getCurrentpage());
            if(listStudentEnroll.isEmpty()) {
                System.out.println("Chưa có khóa học nào được đăng ký!");
            }else {
                navigateToEnrollment(scanner, listStudentEnroll);
                char choice = ValidatorChoice.validateChoiceChar(scanner);
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
    public int totalEnrollment() {
        return enrollmentDAOImp.totalEnrollment();
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
        System.out.printf("|%-10s|%-12s|%-30s|%-12s|%-22s|%-12s|%-20s|\n",
                "Mã đăng ký", "Mã khóa học", "Tên khóa học", "Thời lượng", "Giảng viên","Trạng thái","Ngày đăng ký");
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
    }
    public void navigateToEnrollment(Scanner scanner, List<StudentEnroll> listStudentEnroll) {
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.printf("|%-10s |%-20s |%-25s |%-15s |%-20s%n|",
                "Mã ĐK", "Học viên", "Khóa học", "Trạng thái", "Thời gian tạo");
        System.out.println("---------------------------------------------------------------------------------------------");
        for (StudentEnroll studentEnroll : listStudentEnroll) {
            studentEnroll.displayData();
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
    }

    public int choiceCourse(Scanner scanner) {
        pagination.setCurrentpage(1);
        pagination.setPagesize(5);
        int totalCourse = courseServiceImp.totalCourse();
        pagination.setTotalpages(totalCourse);
        int idCourse = -1;
        do {
            List<Course> listPagination = courseDAOImp.listPagination(pagination.getPagesize(), pagination.getCurrentpage());
            if (!listPagination.isEmpty()) {
                System.out.println("---------------------------------------------------------------------------------------------");
                System.out.printf("%-10s | %-25s | %-15s | %-25s | %-15s\n",
                        "Mã KH", "Tên khóa học", "Thời lượng (giờ)", "Giảng viên phụ trách", "Ngày thêm");
                System.out.println("---------------------------------------------------------------------------------------------");
                listPagination.forEach(course -> {
                    System.out.printf("%-10d | %-25s | %-15d | %-25s | %-15s\n",
                            course.getId(), course.getName(), course.getDuration(),
                            course.getInstructor(), course.getCreate_at());
                });
                System.out.println("----------------------------------------------------------------------------------------------");
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
                System.out.println("2. Chọn khóa học");
                char choice = ValidatorChoice.validateChoiceChar(scanner);
                switch (choice) {
                    case '1':
                        int page = Validator.validateInt(scanner, 1, pagination.getTotalpages(), "Nhập trang: ", "Trang");
                        pagination.setCurrentpage(page);
                        break;
                    case '2':
                        idCourse = Validator.validateInt(scanner,0,1000,"Nhập vào mã khóa học bạn chọn: ", "Mã khóa học: ");
                        return idCourse;
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
            } else {
                System.out.println("Không có khóa học nào.");
                break;
            }
        } while (true);
        return idCourse;
    }
    public int choiceEnrollment(Scanner scanner, int idCourse) {
        pagination.setCurrentpage(1);
        pagination.setPagesize(5);
        int totalCourse = enrollmentDAOImp.countEnrollmentByCourse(idCourse);
        pagination.setTotalpages(totalCourse);
        int idEnrollment = -1;
        do {
            List<StudentEnroll> listStudentEnroll = enrollmentDAOImp.listEnrollmentByCourse(idCourse,pagination.getPagesize(),pagination.getCurrentpage());
            if (!listStudentEnroll.isEmpty()) {
                System.out.println("---------------------------------------------------------------------------------------------");
                System.out.printf("|%-10s |%-20s |%-15s |%-20s%n|",
                        "Mã ĐK", "Học viên", "Khóa học", "Trạng thái", "Thời gian tạo");
                System.out.println("---------------------------------------------------------------------------------------------");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                listStudentEnroll.forEach(studentEnroll -> {
                    System.out.printf("|%-10d |%-20s |%-15s |%-20s%n",
                            studentEnroll.getId(),studentEnroll.getName(),studentEnroll.getStatus(),
                            (studentEnroll.getCreatedAt() != null ? studentEnroll.getCreatedAt().format(formatter) : "N/A"));
                });
                System.out.println("----------------------------------------------------------------------------------------------");
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
                System.out.println("2. Chọn đăng ký");
                char choice = ValidatorChoice.validateChoiceChar(scanner);
                switch (choice) {
                    case '1':
                        int page = Validator.validateInt(scanner, 1, pagination.getTotalpages(), "Nhập trang: ", "Trang");
                        pagination.setCurrentpage(page);
                        break;
                    case '2':
                        idEnrollment = Validator.validateInt(scanner,0,1000,"Nhập vào mã đăng học bạn chọn: ", "Mã đăng ký: ");
                        return idEnrollment;
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
            } else {
                System.out.println("Không có đăng ký nào!.");
                break;
            }
        } while (true);
        return idEnrollment;
    }

    @Override
    public void approveEnrollment(Scanner scanner) {
        System.out.println("Chọn khóa học");
        int idCourse = choiceCourse(scanner);
        Course course = courseServiceImp.findCourseById(idCourse);
        if (course == null){
            System.out.println("Khóa học không tồn tại!");
        }else {
            List<StudentEnroll> listStudentEnroll = enrollmentDAOImp.listEnrollmentByCourse(idCourse,pagination.getPagesize(),pagination.getCurrentpage());
            if (!listStudentEnroll.isEmpty()) {
                System.out.println(course.toString());
                System.out.println("Chọn đăng ký");
                int idEnrollment = choiceEnrollment(scanner, idCourse);
                Enrollment enrollment = enrollmentDAOImp.finById(idEnrollment);
                if (enrollment == null){
                    System.out.println("Đăng ký khóa học không hợp lệ!");
                }else {
                    if(enrollmentDAOImp.confirmEnrollment(enrollment.getId())){
                        System.out.println("Xác nhận thành công");
                    }else {
                        System.out.println("Xác nhận thất bại");
                    }
                }
            }else {
                System.out.println("Không có đăng ký nào!");
            }
        }
    }

    @Override
    public void deniedEnrollment(Scanner scanner) {
        System.out.println("Chọn khóa học");
        int idCourse = choiceCourse(scanner);
        Course course = courseServiceImp.findCourseById(idCourse);
        if (course == null){
            System.out.println("Khóa học không tồn tại!");
        }else {
            List<StudentEnroll> listStudentEnroll = enrollmentDAOImp.listEnrollmentByCourse(idCourse,pagination.getPagesize(),pagination.getCurrentpage());
            if (!listStudentEnroll.isEmpty()) {
                System.out.println(course.toString());
                System.out.println("Chọn đăng ký");
                int idEnrollment = choiceEnrollment(scanner, idCourse);
                Enrollment enrollment = enrollmentDAOImp.finById(idEnrollment);
                if (enrollment == null){

                    System.out.println("Đăng ký khóa học không hợp lệ!");
                }else {
                    if(enrollmentDAOImp.cancelEnrollment(enrollment.getId())){
                        System.out.println("Từ chối thành công");
                    }else {
                        System.out.println("Từ chối thất bại");
                    }
                }
            }else {
                System.out.println("Không có đăng ký nào!");
            }
        }
    }
}
