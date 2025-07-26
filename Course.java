import java.util.ArrayList;
import java.util.List;

public class Course {
    private String title;
    private List<Student> enrolledStudents = new ArrayList<>();

    public Course(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void enrollStudent(Student student) {
        enrolledStudents.add(student);
        System.out.println(student.name + " enrolled in course: " + title);
    }
}
