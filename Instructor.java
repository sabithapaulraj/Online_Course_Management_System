public class Instructor extends User {

    public Instructor(int id, String name, String email) {
        super(id, name, email);
    }

    @Override
    public void login() {
        System.out.println("Instructor " + name + " logged in.");
    }

    public void createCourse(Course course) {
        System.out.println(name + " created course: " + course.getTitle());
    }

    public void gradeAssignment(Assignment assignment, Grade grade) {
        System.out.println(name + " graded assignment: " + assignment.getTitle() + " with score " + grade.getScore());
    }
}
