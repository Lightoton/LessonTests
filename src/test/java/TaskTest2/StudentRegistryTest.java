package TaskTest2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class StudentRegistryTest {
    StudentRegistry studentRegistry = new StudentRegistry();
    Student student1 = new Student(1, "Viktor", "Lenz", "light@gmail.com", 4.3, "BACKEND", 2024,
            true);
    Student student2 = new Student(2, "Alex", "Pupkin", "Pup@gmail.com", 4.5, "FRONTEND", 2023,
            false);
    Student student3 = new Student(2, "Stefan", "Schwarz", "Schwarz@gmail.com", 3.0, "BACKEND", 2022,
            false);

    @BeforeEach
    public void setUp() {
        studentRegistry.getStudents().clear();
        studentRegistry.addStudent(student1);
        studentRegistry.addStudent(student2);
        studentRegistry.addStudent(student3);
    }

    @Test
    void addStudentTest() {
        Assertions.assertEquals(student1, studentRegistry.getStudents().get(1));
    }

    @Test
    void removeStudentTest() {
        studentRegistry.removeStudent(1);
        Assertions.assertNull(studentRegistry.getStudents().get(1));
    }

    @Test
    void removeStudentExceptionTest() {
        studentRegistry.getStudents().clear();
        Assertions.assertThrows(IllegalStateException.class, () -> studentRegistry.removeStudent(1));
    }

    @Test
    void findStudentsByMajorTest() {
        List<Student> list =
                studentRegistry.getStudents().values().stream().filter(student -> student.getMajor().equals("BACKEND"))
                        .toList();
        Assertions.assertEquals(list, studentRegistry.findStudentsByMajor("BACKEND"));
    }

    @Test
    void findStudentsByMajorEmptyListOrNotFoundElementTest() {
        studentRegistry.getStudents().clear();
        List<Student> list = new ArrayList<>();
        Assertions.assertEquals(list, studentRegistry.findStudentsByMajor("QA"));
    }

    @Test
    void calculateAverageGradeTest() {
        double avgGrade =
                studentRegistry.getStudents().values().stream().mapToDouble(Student::getGrade).average().orElse(0.0);
        Assertions.assertEquals(avgGrade, studentRegistry.calculateAverageGrade());
    }

    @Test
    void calculateAverageGradeWithEmptyMapTest() {
        studentRegistry.getStudents().clear();
        Assertions.assertEquals(0.0, studentRegistry.calculateAverageGrade());
    }

    @Test
    void listStudentsByYearTest() {
        int year = 2024;
        List<Student> studentList =
                studentRegistry.getStudents().values().stream().filter(student -> student.getYear() == year).toList();
        Assertions.assertEquals(studentList, studentRegistry.listStudentsByYear(year));
    }

    @Test
    void listStudentsByYearEmptyMapOrNotFoundElementTest() {
        studentRegistry.getStudents().clear();
        int year = 2024;
        List<Student> studentList =
                new ArrayList<>();
        Assertions.assertEquals(studentList, studentRegistry.listStudentsByYear(year));
    }

    @Test
    void getStudentTest() {
        int id = 1;
        Student student = studentRegistry.getStudents().get(id);
        Assertions.assertEquals(student, studentRegistry.getStudent(id));
    }

    @Test
    void getStudentNotFoundElementTest() {
        studentRegistry.getStudents().clear();
        int id = 1;
        Assertions.assertNull(studentRegistry.getStudent(id));
    }

    @Test
    void getTotalNumberOfStudentsTest() {
        int countStudents = studentRegistry.getStudents().values().size();
        Assertions.assertEquals(countStudents, studentRegistry.getTotalNumberOfStudents());
    }

    @Test
    void getTotalNumberOfStudentsWithoutStudentsTest() {
        studentRegistry.getStudents().clear();
        Assertions.assertEquals(0, studentRegistry.getTotalNumberOfStudents());
    }

    @Test
    void getStudentsWithGradeAboveTest() {
        double grade = 4.0;
        List<Student> studentList =
                studentRegistry.getStudents().values().stream().filter(student -> student.getGrade() > grade).toList();
        Assertions.assertEquals(studentList, studentRegistry.getStudentsWithGradeAbove(grade));
    }

    @Test
    void getStudentsWithGradeNotFoundElementAboveTest() {
        double grade = 4.6;
        List<Student> studentList = new ArrayList<>();
        Assertions.assertEquals(studentList, studentRegistry.getStudentsWithGradeAbove(grade));
    }

    @Test
    void getAverageGradeByMajorTest() {
        String major = "BACKEND";
        double averageGrade =
                studentRegistry.getStudents().values().stream()
                        .filter(student -> student.getMajor().equals(major))
                        .mapToDouble(Student::getGrade)
                        .average()
                        .orElse(0.0);
        Assertions.assertEquals(averageGrade,studentRegistry.getAverageGradeByMajor(major));
    }
    @Test
    void getAverageGradeByMajorNotFoundElementTest() {
        studentRegistry.getStudents().clear();
        String major = "BACKEND";
        double averageGrade = 0.0;
        Assertions.assertEquals(averageGrade,studentRegistry.getAverageGradeByMajor(major));
    }

    @Test
    void isStudentPresent() {
        String email = "Pup@gmail.com";
        boolean isStudentPresent =
                studentRegistry.getStudents().values().stream().anyMatch(student -> student.getEmail().equals(email));
        Assertions.assertEquals(isStudentPresent,studentRegistry.isStudentPresent(email));
    }
}