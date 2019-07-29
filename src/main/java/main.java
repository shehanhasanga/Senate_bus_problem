import java.util.Scanner;

public class main {
    public static void main(String[] args) throws InterruptedException {

        float riderArrivalMeanTime = 30f * 1000;
        float busArrivalMeanTime = 20 * 60f * 1000;

        Scanner scanner = new Scanner(System.in);
        String userInput;
        halt bushalt = new halt();

        System.out.println("\n*******  Press any key to exit.  *******\n");

        rider_generator riderGenerator = new rider_generator(riderArrivalMeanTime,bushalt);
        (new Thread(riderGenerator)).start();

        bus_generator busGenerator = new bus_generator(busArrivalMeanTime,bushalt);
        (new Thread(busGenerator)).start();

        // Program Termination with a user input
        while (true) {
            userInput = scanner.nextLine();
            if (userInput != null)
                System.exit(0);
        }
    }
}
