INSERT INTO Students(Student_ID,First_Name,Last_Name,Country,Gpa)VALUES (1, 'Hasan', 'Naghiyev', 'Azerbaijan', 4.0);
INSERT INTO Students(Student_ID,First_Name,Last_Name,Country,Gpa) VALUES (2, 'Subhan', 'Naghiyev','Azerbaijan', 3.5);
INSERT INTO Students(Student_ID,First_Name,Last_Name,Country,Gpa) VALUES (3, 'Xanlar', 'Naghiyev','Azerbaijan', 2.7);
INSERT INTO Students(Student_ID,First_Name,Last_Name,Country,Gpa) VALUES (4, 'Mustafa', 'Naghiyev','Azerbaijan', 3.9);
INSERT INTO Students(Student_ID,First_Name,Last_Name,Country,Gpa) VALUES (5, 'Camal', 'Naghiyev','Azerbaijan', 3.6);


INSERT INTO Courses(Course_ID, Coursename, Course_level, prerequisite) VALUES (102, 'Web and Mobile 2', 'Hard', 'yes');
INSERT INTO Courses (Course_ID, Coursename, Course_level, prerequisite) VALUES (220, 'Cyber Security', 'Hard', 'no' );
INSERT INTO Courses (Course_ID, Coursename, Course_level, prerequisite) VALUES(345, 'System Analysis and Design', 'Normal', 'no');
INSERT INTO Courses (Course_ID, Coursename, Course_level, prerequisite) VALUES(400, 'Probability and Statics', 'Normal', 'yes');


INSERT INTO Courses_Students VALUES(1, 102);
INSERT INTO Courses_Students VALUES(1, 220);
INSERT INTO Courses_Students VALUES(1, 400);
INSERT INTO Courses_Students VALUES(2, 220);
INSERT INTO Courses_Students VALUES(2, 102);
INSERT INTO Courses_Students VALUES(3, 220);
INSERT INTO Courses_Students VALUES(3, 345);
INSERT INTO Courses_Students VALUES(4, 220);
INSERT INTO Courses_Students VALUES(4, 400);
INSERT INTO Courses_Students VALUES(5, 102);
INSERT INTO Courses_Students VALUES(5, 345);







