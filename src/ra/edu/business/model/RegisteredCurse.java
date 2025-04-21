package ra.edu.business.model;

import java.time.LocalDateTime;

public class RegisteredCurse {
    private int id;
    private int idCourse;
    private String courseName;
    private int duration;
    private String instructor;
    private LocalDateTime registrationDate;

    public RegisteredCurse() {
    }

    public RegisteredCurse(int id, int idCourse, String courseName, int duration, String instructor, LocalDateTime registrationDate) {
        this.id = id;
        this.idCourse = idCourse;
        this.courseName = courseName;
        this.duration = duration;
        this.instructor = instructor;
        this.registrationDate = registrationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
    public void displayData(){
        System.out.printf("|%-10d|%-12d|%-30s|%-12d|%-22s|%-20s|\n",
                id,
                idCourse,
                courseName,
                duration,
                instructor,
                registrationDate.toString()
        );
    }
}
