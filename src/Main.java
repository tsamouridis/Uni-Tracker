import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;  
import java.io.FileReader; 
import java.io.IOException;  
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        Settings s = new Settings();
        int numOfSemesters = Integer.parseInt(s.getSettings()[4]) * 2;  // set numOfSemesters
        Semester semArray[] = new Semester[numOfSemesters];             // create array for semesters
        for(int i=0 ; i<numOfSemesters ; i++){                          //initialize the semArray
            semArray[i] = new Semester();
            semArray[i].setSerialNumber(i);
        }
        int courseNum = Course.getCourseNum();                          //read number of courses
        
        String data[] = new String[5];
        for(int i=0 ; i<courseNum ; i++){
            String id = "C_"+i;
// System.out.println(id);
            data = read_courseData(id);
            int belongsToSem = Integer.parseInt(data[1]); 
            semArray[belongsToSem-1].addCourse(data[0], Integer.parseInt(data[1]),
                                                 Float.parseFloat(data[2]), Boolean.parseBoolean(data[3]), Boolean.parseBoolean(data[4]), id, false);
// System.out.println(semArray[belongsToSem-1].getCourses()[0].getId());
        }

        new Gui(semArray);
    
    }

    static String[] read_courseData(String id){ 
        String[] data = new String[5];
        try{
            BufferedReader csvReader = new BufferedReader(new FileReader(id + ".csv"));
            String row;
            int count = 0;
            while ((row = csvReader.readLine()) != null) {
                String[] fileInfo = row.split(",");
                switch(count){
                    case 0:     
                        data[0] = fileInfo[1];
                        break;
                    case 1:
                        data[1] = fileInfo[1];
                        break;
                    case 2:   
                        data[2] = fileInfo[1];
                        break;
                    case 3:
                        data[3] = fileInfo[1];
                        break;
                    case 4:   
                        data[4] = fileInfo[1];
                        break;
                }
                count++;
            }
            csvReader.close();
        }
        catch (IOException e){  
            e.printStackTrace();    
        }
        return data;
    }

}
