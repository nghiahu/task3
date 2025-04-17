package ra.edu.business.model;

import java.time.LocalDate;

public class Student extends User {
    private LocalDate dob;
    private String email;
    private String phone;
    private Std_status status;
    private LocalDate create_at;

    public Student() {
        status = Std_status.INACTIVE;
        create_at = LocalDate.now();
    }

    public Student(int id, String username, String password, LocalDate dob, String email, String phone, Std_status status, LocalDate create_at) {
        super(id, username, password);
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.create_at = create_at;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Std_status getStatus() {
        return status;
    }

    public void setStatus(Std_status status) {
        this.status = status;
    }

    public LocalDate getCreate_at() {
        return create_at;
    }

    public void setCreate_at(LocalDate create_at) {
        this.create_at = create_at;
    }
}
