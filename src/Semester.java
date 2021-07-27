import java.io.BufferedReader;  
import java.io.FileReader; 
import java.io.IOException;  

public class Semester {
    private int serialNumber;
    private int numberOfCourses;
    Course[] courses;
    Course[] gradedCourses;
    float semesterGrades[];
    private Statistics stats;
    
    // constructors
    public Semester(int serialNumber, int numberOfCourses) {
        this.serialNumber = serialNumber;
        this.numberOfCourses = numberOfCourses;
        this.courses = new Course[numberOfCourses];
        this.semesterGrades = new float[numberOfCourses];
        for(int i=0 ; i<this.numberOfCourses ; i++){
            courses[i] = new Course("Course " + (i+1), serialNumber);
        }
        createStats();
    }

    public Semester() {
        this.serialNumber = -1;
        this.numberOfCourses = 0;
        createStats();
    }

    // getters and setters
    public Course[] getCourses() {
        return courses;
    }

    public void setCourses(Course[] courses) {
        this.courses = courses;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }


    public int getNumberOfCourses() {
        return numberOfCourses;
    }


    public void setNumberOfCourses(int numberOfCourses) {
        this.numberOfCourses = numberOfCourses;
    }

    public void setSemesterGrades(){
        float semesterGrades[] = new float[numberOfCourses];
        for(int i=0 ; i<numberOfCourses ; i++){
            semesterGrades[i] = courses[i].getGrade();
        }
    } 

    public Statistics getStats(){
        createStats();
        return this.stats;
    }

    // methods
    public void addCourse(){
        this.numberOfCourses ++;
        Course newCourseArray[] = new Course[numberOfCourses];
        for(int i=0 ; i<numberOfCourses-1 ; i++){
            newCourseArray[i] = new Course(courses[i].getName(), courses[i].getSemester(), courses[i].getGrade(), courses[i].isPassed(), courses[i].isGraded(), false);
        }
        newCourseArray[numberOfCourses-1] = new Course();
        this.courses = newCourseArray;
    }


    public void addCourse(String name, int semester, float grade, boolean passed, boolean graded){
        this.numberOfCourses ++;
        Course newCourseArray[] = new Course[numberOfCourses];
        for(int i=0 ; i<numberOfCourses-1 ; i++){
            newCourseArray[i] = new Course(courses[i].getName(), courses[i].getSemester(), courses[i].getGrade(), courses[i].isPassed(), courses[i].isGraded(), false);
        }
        newCourseArray[numberOfCourses-1] = new Course(name, semester, grade, passed, graded);
        this.courses = newCourseArray;
    }

    public void addCourse(String name, int semester, float grade, boolean passed, boolean graded, String id, boolean createFile){
        this.numberOfCourses ++;
        Course newCourseArray[] = new Course[numberOfCourses];
        for(int i=0 ; i<numberOfCourses-1 ; i++){
            newCourseArray[i] = new Course(courses[i].getName(), courses[i].getSemester(), courses[i].getGrade(), courses[i].isPassed(), courses[i].isGraded(), false);
            newCourseArray[i].setId(courses[i].getId());
        }
        newCourseArray[numberOfCourses-1] = new Course(name, semester, grade, passed, graded, id, false);
        this.courses = newCourseArray;
    }

    public void addCourse(String name, int semester, float grade, boolean passed, boolean graded, boolean createFile){
        this.numberOfCourses ++;
        Course newCourseArray[] = new Course[numberOfCourses];
        for(int i=0 ; i<numberOfCourses-1 ; i++){
            newCourseArray[i] = new Course(courses[i].getName(), courses[i].getSemester(), courses[i].getGrade(), courses[i].isPassed(), courses[i].isGraded(), false);
            newCourseArray[i].setId(courses[i].getId());
        }
        newCourseArray[numberOfCourses-1] = new Course(name, semester, grade, passed, graded, false);
        this.courses = newCourseArray;
    }

    public void addCourse(String name, int semester, float grade){
        this.numberOfCourses ++;
        Course newCourseArray[] = new Course[numberOfCourses];
        for(int i=0 ; i<numberOfCourses-1 ; i++){
            newCourseArray[i] = new Course(courses[i].getName(), courses[i].getSemester(), courses[i].getGrade(), courses[i].isPassed(), courses[i].isGraded(), false);
        }
        boolean passed, graded;
        if (grade == -1){
            passed = false;
            graded = false;
        }
        else if(grade >=5){
            passed = true;
            graded = true;
        }
        else{
            passed = false;
            graded = true; 
        }
    
        newCourseArray[numberOfCourses-1] = new Course(name, semester, grade, passed, graded);
        this.courses = newCourseArray;
    }

    public void addCourse(String name, int semester){
        this.numberOfCourses ++;
        Course newCourseArray[] = new Course[numberOfCourses];
        for(int i=0 ; i<numberOfCourses-1 ; i++){
            newCourseArray[i] = new Course(courses[i].getName(), courses[i].getSemester(), courses[i].getGrade(), courses[i].isPassed(), courses[i].isGraded(), false);
        }
        newCourseArray[numberOfCourses-1] = new Course(name, semester, -1, false, false);
        this.courses = newCourseArray;
    }

    public void createStats(){
        int numOfGradedCourses = 0;
        int[] indexOfGradedCourses = new int[numberOfCourses];

        for(int i=0 ; i<numberOfCourses ; i++){
            indexOfGradedCourses[i] = -1;
        }

        int count = 0;
        for(int i=0 ; i<numberOfCourses ; i++){
            if(courses[i].isGraded()){
                numOfGradedCourses++;
                indexOfGradedCourses[count] = i;
                count++;
            }
        }
        gradedCourses = new Course[numOfGradedCourses];

        semesterGrades = new float[numberOfCourses];
        for(int i=0 ; i<numOfGradedCourses ; i++){
            semesterGrades[i] = courses[indexOfGradedCourses[i]].getGrade();
        }
        if(numOfGradedCourses != 0){
            stats = new Statistics(semesterGrades, numOfGradedCourses);
        }
    }

    public int getCourseNum(){
        int courseNum = -1;
        try{
            BufferedReader csvReader = new BufferedReader(new FileReader("courseNum.csv"));
            String row;
            row = csvReader.readLine();
            String[] data = row.split(",");
            courseNum = Integer.parseInt(data[1]);
            csvReader.close();
        }
        catch (IOException e){  
            e.printStackTrace();  
        }
        return courseNum; 
    }
}
