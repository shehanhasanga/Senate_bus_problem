
import java.util.Random;
public class bus_generator implements Runnable {
    private halt bushalt;
    private static Random random;
    private float arrivalMeanTime;
    public bus_generator(float arrivalMeanTime,halt bushalt) {
        this.arrivalMeanTime = arrivalMeanTime;
        this.bushalt = bushalt;
        random = new Random();

    }


    public void run() {
        int busIndex = 1;
        while (!Thread.currentThread().isInterrupted()){
            try {
                bus bus = new bus(busIndex, bushalt);
                (new Thread(bus)).start();
                busIndex++;
                Thread.sleep(getExponentiallyDistributedBusInterArrivalTime());
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("All buses have finished");
        }
    }

    public long getExponentiallyDistributedBusInterArrivalTime() {
        float lambda = 1 / arrivalMeanTime;
        return Math.round(-Math.log(1 - random.nextFloat()) / lambda);
    }
}
