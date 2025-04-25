package ra.edu.business.dao.Course;

import ra.edu.business.dao.AppDAO;
import ra.edu.business.model.Course;

import java.util.List;
import java.util.Map;

public interface CourseDAO extends AppDAO<Course> {
    Course findByName(String name);
    List<Course> listPagination(int limit, int page);
    int totalCount();
    Course findById(int id);
    List<Course> findByNamePagianation(String name, int limit, int page);
    List<Course> sortByName(int limit, int page, String sortBy);
    List<Course> sortById(int limit, int page, String sortBy);
    int countByName(String name);
    Map<Course,Integer> totalStdOfCourse(int pageSize, int currentPage);
    Map<Course,Integer> top5Course();
    Map<Course,Integer> CourseThan10Std(int pageSize, int currentPage);
}
