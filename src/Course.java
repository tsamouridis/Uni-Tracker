import java.io.File;

public class Course {
    // variables
    private String name;
    private String semester;
    private int grade;
    private boolean graded;

    private boolean passed;

    // constructors
    public Course(String name, String semester, int grade, boolean passed, boolean graded) {
        this.name = name;
        this.semester = semester;
        this.grade = grade;
        this.passed = passed;
        this.graded = graded;
        createFile();
    }

    public Course() {
        
    }

    // getters and setters
    public boolean isGraded() {
        return graded;
    }

    public void setGraded(boolean graded) {
        this.graded = graded;
    }

    public boolean isPassed() {
        return this.passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    // methods
    public void createFile(){
        File courseFile = new File(this.name + ".csv");
    } 
}
