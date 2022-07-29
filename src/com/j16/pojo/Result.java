package com.j16.pojo;

/**
 * @author Master
 * 学生成绩表
 */
public class Result {
    private int id;
    private int studentNo;
    private int subjectNo;
    private String examDate;
    private int studentResult;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(int studentNo) {
        this.studentNo = studentNo;
    }

    public int getSubjectNo() {
        return subjectNo;
    }

    public void setSubjectNo(int subjectNo) {
        this.subjectNo = subjectNo;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public int getStudentResult() {
        return studentResult;
    }

    public void setStudentResult(int studentResult) {
        this.studentResult = studentResult;
    }
}
