package ra.edu.business.service.course;

import ra.edu.business.dao.Course.CourseDAOImp;
import ra.edu.business.model.Course;
import ra.edu.business.model.Pagination;
import ra.edu.validate.Validator;
import ra.edu.validate.ValidatorChoice;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CourseServiceImp implements CourseService {
    private CourseDAOImp courseDAOImp;
    public CourseServiceImp(){
        courseDAOImp = new CourseDAOImp();
    }
    Pagination pagination = new Pagination();

    @Override
    public Course findByName(String name) {
        return courseDAOImp.findByName(name);
    }

    public List<Course> listCourses(String search) {
        if(search == null){
            return courseDAOImp.listPagination(pagination.getPagesize(), pagination.getCurrentpage());
        }else {
            return courseDAOImp.findByNamePagianation(search, pagination.getPagesize(), pagination.getCurrentpage());
        }
    }
    private void displayCourseListPagination(Scanner scanner, String search,String sortBy, String sortOrder) {
        boolean Exit = false;
        pagination.setCurrentpage(1);
        pagination.setPagesize(5);
        int totalCourse = this.totalCourse();
        pagination.setTotalpages(totalCourse);
        do {
            List<Course> listPagination = null;
            if(sortBy == null){
                listPagination = (search == null) ?
                        courseDAOImp.listPagination(pagination.getPagesize(), pagination.getCurrentpage()) :
                        courseDAOImp.findByNamePagianation(search, pagination.getPagesize(), pagination.getCurrentpage());
                if(search != null){
                    pagination.setTotalpages(totalFind(search));
                }
            }else {
                listPagination = (sortBy.equals("NAME")) ?
                        courseDAOImp.sortByName(pagination.getPagesize(), pagination.getCurrentpage(), sortOrder) :
                        courseDAOImp.sortById(pagination.getPagesize(), pagination.getCurrentpage(), sortOrder);
                }
            if (!listPagination.isEmpty()) {
                System.out.println("\u001B[1;36m┌──────┬───────┬───────────────────────────┬──────────────────┬───────────────────────────┬─────────────────┐\u001B[0m");
                System.out.printf("\u001B[1;36m│ %-4s │ %-4s │ %-25s │ %-15s │ %-25s │ %-15s │\u001B[0m\n",
                        "STT", "Mã KH", "Tên khóa học", "Thời lượng (giờ)", "Giảng viên phụ trách", "Ngày thêm");
                System.out.println("\u001B[1;36m├──────┼───────┼───────────────────────────┼──────────────────┼───────────────────────────┼─────────────────┤\u001B[0m");
                int index = (pagination.getCurrentpage()*pagination.getPagesize()- pagination.getPagesize()+1);
                for (var course : listPagination) {
                    System.out.printf("\u001B[1;36m│\u001B[0m \u001B[33m%-4d\u001B[0m \u001B[1;36m│\u001B[0m \u001B[33m%-5d\u001B[0m \u001B[1;36m│\u001B[0m \u001B[32m%-25s\u001B[0m \u001B[1;36m│\u001B[0m \u001B[34m%-16d\u001B[0m \u001B[1;36m│\u001B[0m \u001B[35m%-25s\u001B[0m \u001B[1;36m│\u001B[0m \u001B[36m%-15s\u001B[0m \u001B[1;36m│\u001B[0m\n",
                            index++,
                            course.getId(),
                            course.getName(),
                            course.getDuration(),
                            course.getInstructor(),
                            course.getCreate_at());
                }
                System.out.println("\u001B[1;36m└──────┴───────┴───────────────────────────┴──────────────────┴───────────────────────────┴─────────────────┘\u001B[0m");
                Exit = pagination.navigate(scanner);
            } else {
                System.out.println("\u001B[31mKhông có khóa học nào.!\u001B[0m");
                break;
            }
        } while (!Exit);
    }

    @Override
    public void listCoursesPagination(Scanner scanner) {
        displayCourseListPagination(scanner, null, null, null);
    }

    @Override
    public int totalCourse() {
        return courseDAOImp.totalCount();
    }
    public int totalFind(String name){
        return courseDAOImp.countByName(name);
    }
    @Override
    public Course findCourseById(int id) {
        return courseDAOImp.findById(id);
    }

    @Override
    public List<Course> findAll() {
        return List.of();
    }

    @Override
    public boolean save(Course course) {
        return courseDAOImp.save(course);
    }

    @Override
    public boolean update(Course course) {
        return courseDAOImp.update(course);
    }

    @Override
    public boolean delete(Course course) {
        return courseDAOImp.delete(course);
    }

    public void finCourseByNamePagination(Scanner scanner , String search) {
        displayCourseListPagination(scanner, search, null, null);
    }


    @Override
    public void sortByName(Scanner scanner) {
        boolean Exit = false;
        do {
            System.out.println("Sắp xếp: ");
            System.out.println("1. Tăng dần");
            System.out.println("2. Giảm dần");
            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    displayCourseListPagination(scanner, null, "NAME", "asc");
                    Exit = true;
                    break;
                case 2:
                    displayCourseListPagination(scanner, null, "NAME", "desc");
                    Exit = true;
                    break;
                default:
                    System.out.println("\u001B[31mLựa chọn không hợp lệ, vui lòng nhập lại!\u001B[0m");
            }
        }while(!Exit);
    }

    @Override
    public void sortById(Scanner scanner) {
        boolean Exit = false;
        do {
            System.out.println("Sắp xếp: ");
            System.out.println("1. Tăng dần");
            System.out.println("2. Giảm dần");
            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    displayCourseListPagination(scanner, null, "ID", "asc");
                    Exit = true;
                    break;
                case 2:
                    displayCourseListPagination(scanner, null, "ID", "desc");
                    Exit = true;
                    break;
                default:
                    System.out.println("\u001B[31mLựa chọn không hợp lệ, vui lòng nhập lại!\u001B[0m");
            }
        }while(!Exit);
    }

    @Override
    public void totalStudentsOfCourse(Scanner scanner) {
        boolean Exit = false;
        pagination.setCurrentpage(1);
        pagination.setPagesize(5);
        int totalCourse = courseDAOImp.totalCount();

        pagination.setTotalpages(totalCourse);
        do {
            Map<Course,Integer> list = courseDAOImp.totalStdOfCourse(pagination.getPagesize(),pagination.getCurrentpage());
            if (!list.isEmpty()) {
                System.out.println("\u001B[36m┌────────────┬──────────────────────────┬───────────────────┐\u001B[0m");
                System.out.printf("\u001B[36m│ \u001B[33m%-10s\u001B[36m │ %-25s│ %-18s│\n\u001B[0m",
                        "Mã KH", "Tên khóa học", "Số lượng học viên");
                System.out.println("\u001B[36m├────────────┼──────────────────────────┼───────────────────┤\u001B[0m");
                list.forEach((k, v) -> {
                    System.out.printf("\u001B[36m│ \u001B[32m%-10s\u001B[36m │ %-25s│ %-18s│\n\u001B[0m", k.getId(), k.getName(), v);
                });
                System.out.println("\u001B[36m└────────────┴──────────────────────────┴───────────────────┘\u001B[0m");

                Exit = pagination.navigate(scanner);
            } else {
                System.out.println("\u001B[31mKhông có khóa học nào!\u001B[0m");
                break;
            }
        } while (!Exit);
    }

    @Override
    public void top5Course(Scanner scanner) {
        Map<Course,Integer> list = courseDAOImp.top5Course();
        if (!list.isEmpty()) {
            System.out.println("\u001B[36m┌────────────┬──────────────────────────┬───────────────────┐\u001B[0m");
            System.out.printf("\u001B[36m│ \u001B[33m%-10s\u001B[36m │ %-25s│ %-18s│\n\u001B[0m",
                    "Mã KH", "Tên khóa học", "Số lượng học viên");
            System.out.println("\u001B[36m├────────────┼──────────────────────────┼───────────────────┤\u001B[0m");
            list.forEach((k, v) -> {
                System.out.printf("\u001B[36m│ \u001B[32m%-10s\u001B[36m │ %-25s│ %-18s│\n\u001B[0m", k.getId(), k.getName(), v);
            });
            System.out.println("\u001B[36m└────────────┴──────────────────────────┴───────────────────┘\u001B[0m");

        }else {
            System.out.println("\u001B[31mKhông có khóa học nào!\u001B[0m");
        }
    }

    @Override
    public void courseMore10Std(Scanner scanner) {
        boolean Exit = false;
        pagination.setCurrentpage(1);
        pagination.setPagesize(5);
        int totalCourse = courseDAOImp.countCourseThan10Std();
        pagination.setTotalpages(totalCourse);
        do {
            Map<Course,Integer> list = courseDAOImp.CourseThan10Std(pagination.getPagesize(),pagination.getCurrentpage());
            if (!list.isEmpty()) {
                System.out.println("\u001B[36m┌────────────┬──────────────────────────┬───────────────────┐\u001B[0m");
                System.out.printf("\u001B[36m│ \u001B[33m%-10s\u001B[36m │ %-25s│ %-18s│\n\u001B[0m",
                        "Mã KH", "Tên khóa học", "Số lượng học viên");
                System.out.println("\u001B[36m├────────────┼──────────────────────────┼───────────────────┤\u001B[0m");
                list.forEach((k, v) -> {
                    System.out.printf("\u001B[36m│ \u001B[32m%-10s\u001B[36m │ %-25s│ %-18s│\n\u001B[0m", k.getId(), k.getName(), v);
                });
                System.out.println("\u001B[36m└────────────┴──────────────────────────┴───────────────────┘\u001B[0m");
                Exit = pagination.navigate(scanner);
            } else {
                System.out.println("\u001B[31mKhông có khóa học nào!\u001B[0m");
                break;
            }
        } while (!Exit);
    }
}
