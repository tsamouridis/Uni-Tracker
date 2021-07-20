import java.io.BufferedReader;  
import java.io.FileReader; 
import java.io.IOException;  

public class Data {
    
    public void read_csv(){
       
        try{
            BufferedReader csvReader = new BufferedReader(new FileReader("test.csv"));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                for(int i=0 ; i<data.length ; i++){
                    System.out.println(data[i]);
                }
            }
            csvReader.close();
        }
        catch (IOException e){  
            e.printStackTrace();  
        }  
        
    }

    public static void main(String[] args) {
        Data test = new Data();
        test.read_csv();
    }

    public void write_csv(){
        
    }

}
