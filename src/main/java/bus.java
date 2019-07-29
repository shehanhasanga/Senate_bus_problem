
import java.util.concurrent.Semaphore;

public class bus implements Runnable {
    private final Semaphore riderentranceSem;
    private final Semaphore busDepartureSem;
    private final Semaphore mutex;
    private final int index;
//    private halt bushalt;
    private int rider_count;

    public bus(int index ,halt bushalt) {
        this.riderentranceSem = bushalt.getRiderAreaEntranceSem();
        this.busDepartureSem = bushalt.getBusDepartureSem();
        this.mutex = bushalt.getMutex();
        this.index = index;
        this.rider_count=bushalt.getRidersCount();

//        this.bushalt = bushalt;

    }

//    private WaitingArea waitingArea;

    public void run() {
        try {
            mutex.acquire();
            arrive();
            if(rider_count>0){
                riderentranceSem.release();
                busDepartureSem.acquire();
            }
            else{
                System.out.println("there is not any riders at waiting area");
            }

            mutex.release();

            depart();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void arrive(){
        System.out.println("Bus: "+ index +"  is arrived..");
    }
    public void depart(){
        System.out.println("Bus: "+ index +" is departed..");
    }
}
