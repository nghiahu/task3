package ra.edu.business.model;

import ra.edu.validate.StudentValidator;
import ra.edu.validate.Validator;

import java.time.LocalDate;
import java.util.Scanner;

public class Student extends User {
    private String name;
    private LocalDate dob;
    private boolean sex;
    private String phone;
    private LocalDate create_at;

    public Student() {
        setRole(E_role.STUDENT);
        setStatus(Std_status.ACTIVE);
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

    public void displayInfo() {
        System.out.printf("\u001B[36m│ \u001B[32m%-5d\u001B[36m │ %-25s│ %-20s│ %-10s│ %-10s│ %-26s│ %-10s│\n\u001B[0m",
                getId(),
                getEmail(),
                name,
                dob,
                (sex ? "Nam" : "Nữ"),
                phone,
                create_at
        );
    }
    public void inputDate(Scanner scanner) {
        setEmail(StudentValidator.validateEmail(scanner, false));
        setPassword(StudentValidator.validatePassword(scanner));
        this.name = Validator.validateString(scanner,1,100,"Nhập vào tên học viên: ","Tên học viên");
        this.dob = StudentValidator.validateBirthday(scanner);
        this.sex = StudentValidator.validateGender(scanner);
        this.phone = StudentValidator.validatePhone(scanner, false);
    }
    @Override
    public String toString() {
        return "Mã học viên: " + getId() + " - Email:" + getEmail() + " - Tên: " + this.name + " - Ngày sinh: " + this.dob + " - Giới tính: " + (this.sex?"Nam":"Nữ") + " - Ngày tạo: " + this.getCreate_at();
    }
}