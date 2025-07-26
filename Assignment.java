public class Assignment {
    private String title;
    private String submission;

    public Assignment(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void submit(String submission) {
        this.submission = submission;
        System.out.println("Assignment submitted: " + submission);
    }
}
