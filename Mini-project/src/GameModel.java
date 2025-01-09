class GameModel {
    private int score = 0;

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        score++;
    }

    public void decrementScore() {
        score--;
    }
}