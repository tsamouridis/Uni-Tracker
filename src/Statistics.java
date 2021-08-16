import java.io.IOException;
import java.lang.Math;
import java.util.Collections;
import java.util.ArrayList;

public class Statistics {
    // variables
    private int n;      // number of passed courses
    private float mean;
    private float median;
    private float variance;
    private float std;
    private float minimum;
    private float maximum;
    private float[] allGrades;
    ArrayList<Float> grades;

    // constructors
    public Statistics(float[] array) {
        allGrades = new float[array.length];
        grades = new ArrayList<Float>();
        for(int i=0 ; i<array.length ; i++){
            if(array[i] >= 5){
                grades.add(array[i]);
            }
            allGrades[i] = array[i];
        }
        this.n = grades.size();
        if(grades.size() == 0){
            this.mean = -1;
            this.median = -1;
            this.variance= -1;
            this.std = -1;
            this.minimum = -1;
            this.maximum = -1;
        }
        else{
            setMean();
            setMedian();
            setVariance();
            setStd();
            setMinimum();
            setMaximum();
            generateGraphs();
        }
    }

    // getters and setters
    public float getN() {
        return n;
    }
    
    public float getMean() {
        return mean;
    }

    public float getMedian() {
        return median;
    }

    public float getVariance() {
        return variance;
    }

    public float getStd() {
        return std;
    }

    public float getMinimum() {
        return this.minimum;
    }
    
    public float getMaximum() {
        return this.maximum;
    }

    
    public void setN(int n){
        this.n = n;
    }

    public void setMean(){        
        float sum = 0;
        for(int i=0 ; i<grades.size() ; i++){
            sum += grades.get(i);
        }
        this.mean = sum/grades.size();
    }

    public void setMedian(){
        Collections.sort(grades);
        if(grades.size() == 1){
            this.median = grades.get(0);
        }
        else if (grades.size() % 2 == 1){
            int median_index = (int) Math.ceil((double) grades.size()/2);
            this.median = grades.get(median_index);
        }
        else{
            int x1 = (int) Math.floor((double)grades.size()/2) - 1;
            int x2 = x1 + 1;
            this.median = (grades.get(x1)+grades.get(x2))/2;
        }
    }

    public void setVariance(){
        if(grades.size() == 1){
            this.variance = -1;
        }
        else{
            float sum = 0;
            for(int i=0 ; i<grades.size() ; i++){
                float res = (grades.get(i) - this.mean);
                sum += Math.pow(res, 2);
            }
            this.variance = sum/(grades.size()-1);
        }
    }

    public void setStd(){
        if(this.variance < 0){
            this.std = -1;
        }
        else{
            this.std = (float) Math.sqrt(this.variance);
        }
    }

    public void setMinimum(){
        Collections.sort(grades);
        this.minimum = grades.get(0);
    }

    public void setMaximum(){
        Collections.sort(grades);

        this.maximum = grades.get(grades.size()-1);  
    }

    public void generateGraphs(){
        String[] command = new String[allGrades.length+2];
        command[0] = "python";
        command[1] = "src/plotter.py";
        for(int i=2 ; i<command.length ; i++){
            command[i] = String.valueOf(allGrades[i-2]);
        }
        try{
            Runtime.getRuntime().exec(command);
        }
        catch (IOException e) {
            System.out.println("exception happened");
        }
    }
}
