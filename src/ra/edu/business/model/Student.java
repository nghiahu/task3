package ra.edu.business.model;

import java.time.LocalDate;

public class Student extends User {
    private String name;
    private LocalDate dob;
    private boolean sex;
    private String phone;
    private LocalDate create_at;

    public Student() {
        create_at = LocalDate.now();
    }

    public Student(int id, String email, String password, Std_status status, E_role role, String name, LocalDate dob, boolean sex, String phone, LocalDate create_at) {
        super(id, email, password, status, role);
        this.name = name;
        this.dob = dob;
        this.sex = sex;
        this.phone = phone;
        this.create_at = create_at;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getCreate_at() {
        return create_at;
    }

    public void setCreate_at(LocalDate create_at) {
        this.create_at = create_at;
    }
}