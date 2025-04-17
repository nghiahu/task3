package ra.edu.business.service.Course;

import ra.edu.business.dao.AppDAO;
import ra.edu.business.model.Course;

import java.util.List;

public interface CourseService extends AppDAO<Course> {
    Course findByName(String name);
    List<Course> listPagination(int limit, int page);
    int totalCourse();
    Course findCourseById(int id);
}
