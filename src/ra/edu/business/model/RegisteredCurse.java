package ra.edu.business.model;

import java.time.LocalDateTime;

public class RegisteredCurse {
    private int id;
    private int idCourse;
    private String courseName;
    private int duration;
    private String instructor;
    private E_status status;
    private LocalDateTime registrationDate;

    public RegisteredCurse() {
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

    public E_status getStatus() {
        return status;
    }

    public void setStatus(E_status status) {
        this.status = status;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
    public void displayData(){
        System.out.printf("\u001B[36m│\u001B[32m%-12d\u001B[36m│%-14d│%-32s│%-12d│%-22s│%-12s│%-22s│\n\u001B[0m",
                id,
                idCourse,
                courseName,
                duration,
                instructor,
                status,
                registrationDate.toString()
        );
    }
}
