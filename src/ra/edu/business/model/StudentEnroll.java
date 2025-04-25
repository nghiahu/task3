package ra.edu.business.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StudentEnroll {
    private int id;
    private String name;
    private String courseName;
    private E_status status;
    private LocalDateTime createdAt;

    public StudentEnroll() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public E_status getStatus() {
        return status;
    }

    public void setStatus(E_status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public void displayData() {
        System.out.printf("|%-10d |%-20s |%-25s |%-15s |%-20s%n",
            id,
            name,
            courseName,
            status,
            createdAt != null ? createdAt.format(formatter) : "N/A"
        );
    }
}
