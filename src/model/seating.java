package model;

public class Seating {
    private int id;
    private String studentName;
    private String rollNumber;
    private String department;
    private int semester;
    private String examName;
    private String hallNumber;
    private String seatNumber;

    public Seating() {}

    public Seating(int id, String studentName, String rollNumber, String department, int semester, String examName, String hallNumber, String seatNumber) {
        this.id = id;
        this.studentName = studentName;
        this.rollNumber = rollNumber;
        this.department = department;
        this.semester = semester;
        this.examName = examName;
        this.hallNumber = hallNumber;
        this.seatNumber = seatNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
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

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getHallNumber() {
        return hallNumber;
    }

    public void setHallNumber(String hallNumber) {
        this.hallNumber = hallNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
}
