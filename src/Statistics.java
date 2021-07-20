import java.lang.Math;

public class Statistics {
    private int n;
    private float mean;
    private float median;
    private float variance;

    public Statistics(int[] array) {
        this.n = array.length;
        setMean(array);
        setMedian(array);
        setVariance(array);
    }

    public void setN(int n){
        this.n = n;
    }

    public void setMean(int[] array){
        float sum = 0;
        for(int i=0 ; i<n ; i++){
            sum += array[i];
        }
        this.mean = sum/n;
    }

    public void setMedian(int[] array){
        if (n%2 == 1){
            int median_index = (int) Math.ceil((double) n/2);
            this.median = array[median_index];
        }
        else{
            int x1 = n/2;
            int x2 = x1 + 1;
            this.median = (x1+x2)/2;
        }
    }

    public void setVariance(int[] array){
        float sum = 0;
        for(int i=0 ; i<n ; i++){
            sum += Math.pow(array[i] - this.mean, 2);
        }
        this.variance = sum/(n-1);
    }
}
