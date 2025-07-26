public class Student extends User {

    public Student(int id, String name, String email) {
        super(id, name, email);
    }

    @Override
    public void login() {
        System.out.println("Student " + name + " logged in.");
    }

    public void uploadAssignment(Assignment assignment) {
        System.out.println(name + " uploaded an assignment: " + assignment.getTitle());
    }

    public void viewGrades(Grade grade) {
        System.out.println(name + "'s Grade: " + grade.getScore());
    }
}
