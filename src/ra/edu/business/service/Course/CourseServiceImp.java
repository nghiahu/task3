package ra.edu.business.service.Course;

import ra.edu.business.dao.Course.CourseDAOImp;
import ra.edu.business.model.Course;

import java.util.List;

public class CourseServiceImp implements CourseService {
    private CourseDAOImp courseDAOImp;
    public CourseServiceImp(){
        courseDAOImp = new CourseDAOImp();
    }
    @Override
    public Course findByName(String name) {
        return courseDAOImp.findByName(name);
    }

    @Override
    public List<Course> listPagination(int limit, int page) {
        return courseDAOImp.listPagination(limit, page);
    }

    @Override
    public int totalCourse() {
        return courseDAOImp.totalCount();
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
        return false;
    }
}
