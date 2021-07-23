public class Semester {
    private int serialNumber;
    private int numberOfCourses;
    Course[] courses;
    Course[] gradedCourses;
    float semesterGrades[];
    private Statistics stats;
    
    // construcors
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

        for(int i=0 ; i<numOfGradedCourses ; i++){
            semesterGrades[i] = courses[indexOfGradedCourses[i]].getGrade();
        }
        if(numOfGradedCourses != 0){
            stats = new Statistics(semesterGrades, numOfGradedCourses);
        }
    }
}
