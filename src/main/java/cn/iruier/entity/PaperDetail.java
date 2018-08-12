package cn.iruier.entity;

public class PaperDetail {
    private int paper_id;
    private int ques_id;
    private int score;

    public int getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(int paper_id) {
        this.paper_id = paper_id;
    }

    public int getQues_id() {
        return ques_id;
    }

    public void setQues_id(int ques_id) {
        this.ques_id = ques_id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "PaperDetail{" +
                "paper_id=" + paper_id +
                ", ques_id=" + ques_id +
                ", score=" + score +
                '}';
    }
}
