create database managementCourse_Student;
use managementCourse_Student;

create table Account(
    id int primary key auto_increment,
    email varchar(100) not null unique,
    password varchar(255) not null,
    role enum('ADMIN', 'STUDENT') not null ,
    status enum ('ACTIVE','INACTIVE','BLOCKED')
);

create table Students(
    id_account int not null primary key,
    name varchar(100) not null,
    dob date not null,
    sex bit not null,
    phone varchar(20) null,
    create_at date default(now()),
    foreign key (id_account) references Account(id)
);

create table Courses(
    id int primary key auto_increment,
    name varchar(100) not null unique ,
    duration int not null,
    instructor varchar(100) not null,
    status enum ('ACTIVE','INACTIVE','BLOCKED'),
    create_at date default(now())
);

create table enrollments(
    id int primary key auto_increment,
    student_id int not null,
    course_id int not null,
    registered_at date default(current_timestamp()),
    status enum('WAITING','DENIED','CANCER','CONFIRM'),
    foreign key(student_id) references Students(id_account),
    foreign key(course_id) references Courses(id)
);

DELIMITER //
create procedure loginByAccount(
    email_in varchar(50),
    password_in varchar(255),
    out return_code int
)
begin
    declare statusAcc boolean;
    if exists (select * from Account where email = email_in and password = password_in) then
        set return_code = 1;
        select status into statusAcc from Account where email = email_in and password = password_in;
        if (statusAcc = 'BLOCK') then
            set return_code = 2;
        else
            select id,email, role from Account where email = email_in and password = password_in;
        end if ;
    else
        set return_code = 0;
    end if ;
end //
DELIMITER ;

DELIMITER //
create procedure findCourseByName(name_in varchar(100))
begin
    select * from Courses where name = name_in and status != 'BLOCKED';
end //
DELIMITER ;

DELIMITER //
create procedure findCourseById(id_in int)
begin
    select * from Courses where id = id_in and status != 'BLOCKED';
end //
DELIMITER ;

DELIMITER //
create procedure listCourse(
    limit_in int,
    page int
)
begin
    declare offset_in int;
    set offset_in = (page - 1) * limit_in;
    select * from Courses where status != 'BLOCKED'
    limit limit_in
    offset offset_in;
end //
DELIMITER ;

DELIMITER //
create procedure totalCourse()
begin
    select count(id) from Courses where status != 'BLOCKED';
end //
DELIMITER ;

DELIMITER //
create procedure addCourse(
    name_in varchar(100),
    duration_in int,
    instructor_in varchar(100),
    status_in enum ('ACTIVE','INACTIVE','BLOCKED')
)
begin
    insert into Courses (name, duration, instructor, status)
    values (name_in,duration_in,instructor_in,status_in);
end //
DELIMITER ;

DELIMITER //
create procedure updateCourse(
    id_in int,
    name_in varchar(100),
    duration_in int,
    instructor_in varchar(100)
)
begin
    update Courses
        set name = name_in,
            duration = duration_in,
            instructor = instructor_in
    where id = id_in;
end //
DELIMITER ;

DELIMITER //
create procedure delCourse(id_in int)
begin
    update Courses
        set status = 'BLOCKED'
    where id = id_in;
end //
DELIMITER ;

DELIMITER //
create procedure findCourseByNamePagination(
    name_in varchar(100),
    limit_in int,
    page int
)
begin
    declare offset_in int;
    set offset_in = (page - 1) * limit_in;
    select * from Courses where status != 'BLOCKED' and name like concat('%',name_in,'%')
    limit limit_in
    offset offset_in;
end //
DELIMITER ;

DELIMITER //
create procedure sortByName(
    order_in char(5),
    limit_in int,
    page int
)
begin
    declare offset_in int;
    set offset_in = (page - 1) * limit_in;
    if order_in = 'desc' then
        select * from Courses where status != 'BLOCKED'
        order by name desc
        limit limit_in
        offset offset_in;
    else
        select * from Courses where status != 'BLOCKED'
        order by name asc
        limit limit_in
        offset offset_in;
    end if ;
end //
DELIMITER ;

DELIMITER //
create procedure sortByID(
    order_in char(5),
    limit_in int,
    page int
)
begin
    declare offset_in int;
    set offset_in = (page - 1) * limit_in;
    if order_in = 'desc' then
        select * from Courses where status != 'BLOCKED'
        order by id desc
        limit limit_in
        offset offset_in;
    else
        select * from Courses where status != 'BLOCKED'
        order by id asc
        limit limit_in
        offset offset_in;
    end if ;
end //
DELIMITER ;

insert into Courses (name, duration, instructor, status)
values
    ('Web Development Bootcamp', 90, 'Alice Johnson', 'ACTIVE'),
    ('Data Science Fundamentals', 60, 'Bob Smith', 'ACTIVE'),
    ('Introduction to Python', 45, 'Carol Lee', 'INACTIVE'),
    ('Java for Beginners', 75, 'David Kim', 'ACTIVE'),
    ('Database Design', 30, 'Emma Stone', 'BLOCKED'),
    ('Cloud Computing Basics', 50, 'Frank Green', 'ACTIVE'),
    ('Machine Learning Intro', 60, 'Grace Hopper', 'INACTIVE'),
    ('Mobile App Development', 80, 'Henry Ford', 'ACTIVE'),
    ('Cybersecurity Essentials', 40, 'Ivy Baker', 'BLOCKED'),
    ('UI/UX Design Principles', 55, 'Jack Black', 'ACTIVE');
insert into Account(email, password, role, status)
values ('admin@gmail.com','admin123','ADMIN','ACTIVE'),
       ('nghia@gmail.com','nghia123','STUDENT','ACTIVE');