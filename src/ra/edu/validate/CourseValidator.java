package ra.edu.validate;

import ra.edu.business.model.Course;
import ra.edu.business.service.course.CourseServiceImp;

import java.util.Scanner;

public class CourseValidator {
    private static CourseServiceImp courseServiceImp;
    public CourseValidator(){
        courseServiceImp = new CourseServiceImp();
    }
    public static String validateName(Scanner scanner){
        CourseValidator courseValidator = new CourseValidator();
        while (true){
            String name = Validator.validateString(scanner,0,100,"Nhập tên khóa học: ","Tên khóa học");
            Course course = courseServiceImp.findByName(name);
            if (course == null){
                return name;
            }else {
                System.out.println("\u001B[31mTên khóa học đã tồn tại vui lòng thử lại!\u001B[0m");
            }
        }
    }
}
