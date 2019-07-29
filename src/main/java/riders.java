import java.util.concurrent.Semaphore;

public class riders implements Runnable {
    private final Semaphore countwaitingrides;
    private final Semaphore entranceSem;
    private final Semaphore busDepartureSem;
    private final Semaphore mutex;
    private final int index;
    private halt bushalt;

    public riders(int index, halt bushalt) {
        this.countwaitingrides = bushalt.getRiderWaitingSem();
        this.entranceSem = bushalt.getRiderAreaEntranceSem();
        this.busDepartureSem = bushalt.getBusDepartureSem();
        this.index = index;
        this.mutex = bushalt.getMutex();
        this.bushalt = bushalt;
    }
    public void run() {

        try {
            countwaitingrides.acquire();
            mutex.acquire();
            enterbushalt();
            bushalt.incrementRidersCount();
            mutex.release();

            entranceSem.acquire();
            boardBus();

            countwaitingrides.release();
            bushalt.decrementRidersCount();

            if (bushalt.getRidersCount() == 0)
                busDepartureSem.release();
                // If there are more riders waiting, allowing them to get into the bus
            else
                entranceSem.release();

        } catch (InterruptedException e) {
        e.printStackTrace();
    }
    }
    public void boardBus() {
        System.out.println("Rider :" + index + " boarded");
    }

    public void enterbushalt() {
        System.out.println("Rider :" + index + " entered boarding area");
    }
}
