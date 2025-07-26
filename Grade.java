public class Grade {
    private int score;
    private String feedback;

    public Grade(int score, String feedback) {
        this.score = score;
        this.feedback = feedback;
    }

    public int getScore() {
        return score;
    }

    public String getFeedback() {
        return feedback;
    }
}
