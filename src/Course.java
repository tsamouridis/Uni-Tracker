import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;  
import java.io.FileReader; 
import java.io.IOException;  
import java.io.PrintWriter;

public class Course {
    // variables
    private String id;
    private String name;
    private int semester;
    private float grade;
    private boolean graded;
    private boolean passed;

    // constructors
    public Course(String name, int semester, float grade, boolean passed, boolean graded){
        this.name = name;
        this.semester = semester;
        this.grade = grade;
        this.passed = passed;
        this.graded = graded;
        setId(true);
        createFile();
    }

    public Course(String name, int semester, float grade, boolean passed, boolean graded, String id, boolean createFile){
        this.name = name;
        this.semester = semester;
        this.grade = grade;
        this.passed = passed;
        this.graded = graded;
        this.id = id;
    }

    public Course(String name, int semester, float grade, boolean passed, boolean graded, boolean createFile){
        this.name = name;
        this.semester = semester;
        this.grade = grade;
        this.passed = passed;
        this.graded = graded;
    }

    public Course(String name, int semester) {
        this.name = name;
        this.semester = semester;
        this.grade = -1;    // -1 means no grade available
        this.passed = false;
        this.graded = false;
        createFile();
    }

    public Course() {
        
    }

    // getters and setters
    public void setId(boolean editCourseNum) {
        int courseNum = getCourseNum();
        if(editCourseNum){
            courseNum++;
            saveCourseNum(courseNum);
        }
        this.id = "C_" + (courseNum-1);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }


    public boolean isGraded() {
        return graded;
    }

    public void setGraded(boolean graded) {
        this.graded = graded;
        write_csv();
    }

    public boolean isPassed() {
        return this.passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
        write_csv();
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
        write_csv();
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
        this.graded = true;
        if(grade >= 5){
            this.passed = true;
        }
        if(grade >= 0){
            this.graded = true;
        }
        write_csv();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
        write_csv();
    }

    // methods
    public static int getCourseNum(){
        int courseNum = -1;
        try{
            BufferedReader csvReader = new BufferedReader(new FileReader("csvFiles/courseNum.csv"));
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

    public void saveCourseNum(int num){ 
        try (PrintWriter writer = new PrintWriter(new File("csvFiles/courseNum.csv"))) {

            StringBuilder sb = new StringBuilder();
            
            sb.append("courseNum,");
            sb.append(num);
            sb.append('\n');

            writer.write(sb.toString());   
          } 
          catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
          }
        }
    
    public void createFile(){
        try {
            File myObj = new File("csvFiles/"+ id + ".csv");
            if (myObj.createNewFile()) {
                write_csv();
                System.out.println("File created: " + myObj.getName());
            } 
            else {
                System.out.println("File already exists.");
            }
        } 
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    } 

    public void read_csv(){ 
        try{
            BufferedReader csvReader = new BufferedReader(new FileReader("csvFiles/" + id + ".csv"));
            String row;
            int count = 0;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                switch(count){
                    case 0:     this.name = data[1];
                    case 1:     this.semester = Integer.parseInt(data[1]);
                    case 2:     this.grade = Float.parseFloat(data[1]);
                    case 3:     this.graded = Boolean.parseBoolean(data[1]);
                    case 4:     this.passed = Boolean.parseBoolean(data[1]);
                }
            }
            csvReader.close();
        }
        catch (IOException e){  
            e.printStackTrace();  
        }   
    }

    public void read_csv(String id){ 
        this.id = id;
        try{
            BufferedReader csvReader = new BufferedReader(new FileReader("csvFiles/" + id + ".csv"));
            String row;
            int count = 0;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                switch(count){
                    case 0:
                        this.name = data[1];
                        break;
                    case 1:     
                        this.semester = Integer.parseInt(data[1]);
                        break;
                    case 2:
                        this.grade = Float.parseFloat(data[1]);
                        break;
                    case 3:
                        this.graded = Boolean.parseBoolean(data[1]);
                        break;
                    case 4:
                        this.passed = Boolean.parseBoolean(data[1]);
                        break;
                }
                count++;    
            }
            csvReader.close();
        }
        catch (IOException e){  
            e.printStackTrace();  
        }   
    }

    public void write_csv(){ 
        try (PrintWriter writer = new PrintWriter(new File("csvFiles/" + id + ".csv"))) {

            StringBuilder sb = new StringBuilder();
            sb.append("name,");
            sb.append(this.name);
            sb.append('\n');
      
            sb.append("semester,");
            sb.append(this.semester);
            sb.append('\n');
      

            sb.append("grade,");
            sb.append(this.grade);
            sb.append('\n');

            sb.append("graded,");
            sb.append(this.graded);
            sb.append('\n');

            sb.append("passed,");
            sb.append(this.passed);
            sb.append('\n');

            writer.write(sb.toString());
            writer.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void edit_csv(String id, String newName, String newGrade){
        String sem = "";
        // get semester
        try{
            BufferedReader csvReader = new BufferedReader(new FileReader("csvFiles/" + id + ".csv"));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                if(data[0].equals("semester")){
                    sem = data[1];
                    csvReader.close();
                    break;
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();  
        }
        float newGradeFloat = Float.parseFloat(newGrade);
        String newGraded = "true";
        boolean newPassed = false;
        if(newGradeFloat >= 5){
            newPassed = true;
        }

        try (PrintWriter writer = new PrintWriter(new File("csvFiles/" + id + ".csv"))) {

            writer.write("");

            StringBuilder sb = new StringBuilder();
            sb.append("name,");
            sb.append(newName);
            sb.append('\n');
      
            sb.append("semester,");
            sb.append(sem);
            sb.append('\n');
      

            sb.append("grade,");
            sb.append(newGrade);
            sb.append('\n');

            sb.append("graded,");
            sb.append(newGraded);
            sb.append('\n');

            sb.append("passed,");
            sb.append(newPassed);
            sb.append('\n');

            writer.write(sb.toString());
            writer.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
