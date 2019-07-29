
import java.util.concurrent.Semaphore;
public class halt {

    private static int ridersCount = 0;
    private static int maximumBusCapacity = 50;

    // Semaphore used for riders to enter the waiting area, allowing 50 riders to remain at the waiting area
    private static final Semaphore multiplex = new Semaphore(maximumBusCapacity);

    // Semaphore used for riders to enter the boarding area
        private static final Semaphore entranceSem = new Semaphore(0);

    // Semaphore used for bus to depart after the riders are boarded
    private static final Semaphore allAboard = new Semaphore(0);

    // Semaphore used to handle the access to the riders count variable
    private static final Semaphore mutex = new Semaphore(1);

    //Method to get the riders count
    public int getRidersCount() {
        return ridersCount;
    }

    //Method to increment the riders count
    public void incrementRidersCount() {
        ridersCount++;
    }

    //Method to decrement the riders count
    public void decrementRidersCount() {
        ridersCount--;
    }

    //Method to access the semaphore that is used for riders to enter the waiting area
    public static Semaphore getRiderWaitingSem() {
        return multiplex;
    }

    //Method to access the semaphore that is used for riders to board the bus
    public static Semaphore getRiderAreaEntranceSem() {
        return entranceSem;
    }

    //Method to access the semaphore that is used for the bus to depart
    public static Semaphore getBusDepartureSem() {
        return allAboard;
    }

    //Method to access the semaphore that is used for handle access to the riders count
    public static Semaphore getMutex() {
        return mutex;
    }
}
