package TaskTest2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
public class StudentRegistry {
    @Setter
    private  Map<Integer,Student> students;
    public StudentRegistry() {
        students = new HashMap<>();
    }

    public void addStudent(Student student){
       students.put(student.getId(),student);
    }
    public void removeStudent(int id){
        if (students.isEmpty()) throw new  IllegalStateException();
        students.remove(id);
    }
    public List<Student> findStudentsByMajor(String major){
        return students.values().stream()
                .filter(student -> student.getMajor().equals(major))
                .toList();
    }
    public double calculateAverageGrade(){
        return students.values().stream()
                .mapToDouble(Student::getGrade)
                .average()
                .orElse(0.0);
    }
    public List<Student> listStudentsByYear(int year){
        return students.values().stream()
                .filter(student -> student.getYear()==year)
                .toList();
    }
    public Student getStudent(int id){
        return students.get(id);
    }
    public int getTotalNumberOfStudents(){
        return students.values().size();
    }
    public List<Student> getStudentsWithGradeAbove(double grade){
        return students.values().stream()
                .filter(student -> student.getGrade()>grade)
                .toList();
    }
    public double getAverageGradeByMajor(String major){
        return students.values().stream()
                .filter(student -> student.getMajor().equals(major))
                .mapToDouble(Student::getGrade)
                .average()
                .orElse(0.0);
    }
    public boolean isStudentPresent(String email){
        return students.values().stream().anyMatch(student -> student.getEmail().equals(email));
    }
}
