package cn.iruier.common.vo;

public class ConditionVo {
    private String type;
    private String level;
    private int num;
    private int score;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "ConditionVo{" +
                "type='" + type + '\'' +
                ", level='" + level + '\'' +
                ", num=" + num +
                ", score=" + score +
                '}';
    }
}
