import java.util.Random;
public class rider_generator implements Runnable    {
    private halt bushalt;
    private float arrivalMeanTime;
    private static Random random;

    public rider_generator (float arrivalMeanTime,halt bushalt){
        this.arrivalMeanTime = arrivalMeanTime;
        this.bushalt=bushalt;
        random = new Random();
    }
    public void run() {
        int riderIndex = 1;
        while (!Thread.currentThread().isInterrupted()) {
            try {
            riders rider = new riders(riderIndex, bushalt);
            (new Thread(rider)).start();
            riderIndex++;
            Thread.sleep(getExponentiallyDistributedRiderInterArrivalTime());


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public long getExponentiallyDistributedRiderInterArrivalTime() {
        float lambda = 1 / arrivalMeanTime;
        return Math.round(-Math.log(1 - random.nextFloat()) / lambda);
    }

}
