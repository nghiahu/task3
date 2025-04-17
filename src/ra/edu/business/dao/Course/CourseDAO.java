package ra.edu.business.dao.Course;

import ra.edu.business.dao.AppDAO;
import ra.edu.business.model.Course;

import java.util.List;

public interface CourseDAO extends AppDAO<Course> {
    Course findByName(String name);
    List<Course> listPagination(int limit, int page);
    int totalCount();
    Course findById(int id);
}
