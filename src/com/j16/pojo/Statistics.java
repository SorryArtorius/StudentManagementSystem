package com.j16.pojo;

/**
 * 适用于数据统计的实体类
 * 本类用于统计班级选择人数
 * @author Master
 */
public class Statistics {
    private int gradeId;

    private int countGradeId;

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getCountGradeId() {
        return countGradeId;
    }

    public void setCountGradeId(int countGradeId) {
        this.countGradeId = countGradeId;
    }
}
