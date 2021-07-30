import java.lang.Math;
import java.util.Arrays;

public class Statistics {
    // variables
    private int n;      // number of passed courses
    private float mean;
    private float median;
    private float variance;
    private float std;
    private float minimum;
    private float maximum;


    // constructors
    public Statistics(float[] array, int n) {
        System.out.println("n = " + n);
        for(int i=0 ; i<n ; i++){
            System.out.println(array[i]);
        }
        this.n = n;
        setMean(array);
        setMedian(array);
        setVariance(array);
        setStd();
        setMinimum(array);
        setMaximum(array);
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

    public void setMean(float[] array){        
        float sum = 0;
        for(int i=0 ; i<array.length ; i++){
            if(array[i] < 0){
                continue;
            }
            else{
                sum += array[i];
            }
        }
        this.mean = sum/n;
    }

    // ! fix where -1 is present
    public void setMedian(float[] array){
        Arrays.sort(array);
        if(n == 1){
            this.median = array[0];
        }
        else if (n%2 == 1){
            int median_index = (int) Math.ceil((double) n/2);
            this.median = array[median_index];
        }
        else{
            int x1 = (int) Math.floor((double)n/2) - 1;
            int x2 = x1 + 1;
            this.median = (array[x1]+array[x2])/2;
        }
    }

    public void setVariance(float[] array){
        if(n == 1){
            this.variance = -1;
        }
        else{
            float sum = 0;
            for(int i=0 ; i<array.length ; i++){
                if(array[i] < 0){
                    continue;
                }
                else{
                    float res = (array[i] - this.mean);
                    sum += Math.pow(res, 2);
                    // System.out.println("array" + array[i]);
                    // System.out.println("mean" + this.mean);
                    System.out.println("sum = " + sum);
                    // System.out.println(array[i]);
                    // System.out.println("sum of "+i+" = "+sum);
                    // System.out.println("array of "+i+" = "+array[i]);
                    // System.out.println("mean =  "+this.mean);
                }                
            }
            this.variance = sum/(n-1);
            // System.out.println("n = " + n);
            // System.out.println(this.variance);
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

    public void setMinimum(float[] array){
        Arrays.sort(array);
        this.minimum = array[0];
    }

    public void setMaximum(float[] array){
        Arrays.sort(array);
        this.maximum = array[n-1];    
    }
}
