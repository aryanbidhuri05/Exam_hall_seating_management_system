package model;

public class Exam {
    private int id;
    private String examName;
    private String subjectName;
    private String department;
    private int semester;
    private String examDate;

    public Exam() {}

    public Exam(int id, String examName, String subjectName, String department, int semester, String examDate) {
        this.id = id;
        this.examName = examName;
        this.subjectName = subjectName;
        this.department = department;
        this.semester = semester;
        this.examDate = examDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }
}
