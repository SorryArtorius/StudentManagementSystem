package com.j16.pojo;

/**
 * @author Master
 * 学生表第二实体类 多表查询使用
 */
public class StudentAttached {
    private int studentNo;
    private String studentName;

    private String loginPwd;
    private String sex;


    /**
     * 此处是Name 使用此表需连接班级表
     * select student.studentNo,student.studentName,student.loginPwd,student.sex,
     * grade.gradeName,student.phone,student.address,student.bornDate,student.email,
     * student.identityCard,student.mark
     * from student  left join grade on student.gradeId = grade.gradeId
     * where mark=1
     */
    private String gradeName;
    private String phone;
    private String address;

    private String bornDate;

    private String email;

    private String identityCard;

    private int mark;


    public int getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(int studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBornDate() {
        return bornDate;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
