import java.lang.Math;
import java.util.Arrays;

public class Statistics {
    private int n;
    private float mean;
    private float median;
    private float variance;
    private float std;
    private float minimum;
    private float maximum;
    private int numberOfPassedCourses;


    public int getNumberOfPassedCourses() {
        return numberOfPassedCourses;
    }

    public void setNumberOfPassedCourses(float[] array) {
        int res = 0;
        for(int i=0 ; i<n ; i++){
            if(array[i] >= 5){
                res++;
            }
            this.numberOfPassedCourses = res;
        }
    }

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

    public Statistics(float[] array, int n) {
        this.n = n;
        setMean(array);
        setMedian(array);
        setVariance(array);
        setStd();
        setMinimum(array);
        setMaximum(array);
        setNumberOfPassedCourses(array);
    }

    public void setN(int n){
        this.n = n;
    }

    public void setMean(float[] array){
        float sum = 0;
        for(int i=0 ; i<n ; i++){
            sum += array[i];
        }
        this.mean = sum/n;
    }

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
            for(int i=0 ; i<n ; i++){
                sum += Math.pow(array[i] - this.mean, 2);
            }
            this.variance = sum/(n-1);
        }
    }

    public void setStd(){
        this.std = (float) Math.sqrt(this.variance);
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
