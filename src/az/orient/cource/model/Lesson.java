package az.orient.cource.model;

public class Lesson extends Abstract {
    private String lessonName;
    private Integer lessonTime;
    private Double lessonPrice;

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public Integer getLessonTime() {
        return lessonTime;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "lessonName='" + lessonName + '\'' +
                ", lessonTime=" + lessonTime +
                ", lessonPrice=" + lessonPrice +
                '}';
    }

    public void setLessonTime(Integer lessonTime) {
        this.lessonTime = lessonTime;
    }

    public Double getLessonPrice() {
        return lessonPrice;
    }

    public void setLessonPrice(Double lessonPrice) {
        this.lessonPrice = lessonPrice;
    }

}
