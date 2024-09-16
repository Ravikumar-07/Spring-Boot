package com.example.school.service;

import com.example.school.model.Student;
import com.example.school.model.StudentRowMapper;
import com.example.school.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@Service
public class StudentH2Service implements studentsRepository {

    @Autowired
    private jdbcTemplate db;

    @Override
    public ArrayList<Student> getStudents() {
        List<Student> studentData = db.query("select * from student", new StudentRowMappeer());
        ArrayList<Student> students = new ArrayList<>(studentData);
        return students;
    }

    @Override
    public Student getStudentById(int studentId) {
        try {
            Student student = db.queryForObject("select * from student where studentId = ?", new StudentRowMapper(),
                    studentId);
            return student;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

@Override
public Student addStudent(Student student){
db.update("insert into student(studentName, Gender, Standard) vakues (?,?,?)" student.getStudentName(),
student.getGender(), student.getStandard());
Student saveStudent = db.queryForObject("select * from where studentName =? and gender = ? and standard = ?" new StudentRowMapper(),
student.getStudentName(), student.getGender(), student.getStandard());
return saveStudent;
}

    @Override
    public String addMultipleStudents(ArrayList<Student> studentsList) {
        for (Student eachStudent : studentsList) {
            db.update("insert into student(studentName, gender, standard) values (?,?,?)", eachStudent.getStudentName(),
                    eachStudent.Gender(), eachStudent.getStandard());
        }
        String responseMessage = String.format("Succesfully added %d students", studentsList.size());
        return responseMessage;
    }

    @Override
    public void deleteStudent(int studentId) {
        db.update("delete from student where studentId = ?", studentId);
    }

    @Override
    public String addMultipleStudents(ArrayList<Studnet> studentsList) {
        for (Student eachStudent : studentsList) {
            db.update("insert into student(studentName, gender, standard) values (?,?,?)", eachStudent.getStudentName(),
                    eachStudent.getGender(), ecahStudent.getStandard());
        }
        String reponseMessage = String.format("Succefully added %d students", studentList.size());
        return reponseMessage;
    }

    @Override
    public void deleteStudent(int studentId) {
        db.update("delete from student where studentId = ?", studentId);
    }

    @Override
    public Student updateStudent(int studentId, Student student) {
        if (student.getStudentName() != null) {
            db.update("update student set studentName = ? where studentId = ?", student.getStudentName(), studentId);
        }
        if (student.getGender() != null) {
            db.update("update student set Gender = ? where studentId = ?", student.getGender(), studentId);
        }
        if (student.getStandard() != 0) {
            db.update("update student set studentName = ? where studentId = ?", student.getStandard(), studentId);
        }
        return getStudentById(studentId);
    }
}