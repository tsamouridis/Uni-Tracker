import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;  
import java.io.FileReader; 
import java.io.IOException;  
import java.io.PrintWriter;

public class Settings {
    // variables
    private String[] allSettings;

    public void setAllSettings() {
        getSettings();
    }

    // constructors
    Settings(){
        getSettings();
    }

    // methods
    public String[] getSettings(){
        try{
            BufferedReader csvReader = new BufferedReader(new FileReader("settings.csv"));
            String row = csvReader.readLine();
            this.allSettings = row.split(",");
            csvReader.close();
        }
        catch (IOException e){  
            e.printStackTrace();  
        }   
        return this.allSettings;
    }

    public void editSettings(String[] newSettings){
        try (PrintWriter writer = new PrintWriter(new File("settings.csv"))) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            for(i=0 ; i<newSettings.length-1 ; i++){
                sb.append(newSettings[i]);
                sb.append(",");
            }
            sb.append(newSettings[i]);
            writer.write(sb.toString());   
        } 
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
