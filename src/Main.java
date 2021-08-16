import java.io.BufferedReader;  
import java.io.FileReader; 
import java.io.IOException;  

public class Main {
    public static void main(String[] args) {
        Settings s = new Settings();
        if(s.getSettings()[0].equals("true")){
            new StartGui();
        }
        else{
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
                data = read_courseData(id);
                int belongsToSem = Integer.parseInt(data[1]); 
                semArray[belongsToSem-1].addCourse(data[0], Integer.parseInt(data[1]),
                                                     Float.parseFloat(data[2]), Boolean.parseBoolean(data[3]), Boolean.parseBoolean(data[4]), id, false);
            }
            new MainGui(semArray);    
        }
        
    }

    static String[] read_courseData(String id){ 
        String[] data = new String[5];
        try{
            BufferedReader csvReader = new BufferedReader(new FileReader("csvFiles/" + id + ".csv"));
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
