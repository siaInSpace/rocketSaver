package sia20.rocketSaver;

import java.io.File;
import java.util.Scanner;

public class Main {
    private BMP180 bmp;
    private MPU9250 mpu;
    private MPU9250Mag mag;

    private Main() {
        //bmp = new BMP180(Oss.STANDARD, "data/CalibrationValues/bmp180calValuesRaw"); bmp180 is disconnected
        mpu = new MPU9250();
        mag = new MPU9250Mag(mpu);
        bmp = new BMP180(BMP180pressure.Oss.STANDARD, mpu);
    }

    private void menu() {
        System.out.println("What would you like to do?");
        System.out.println("1: whoAmI");
        System.out.println("2: save data");
        System.out.println("3: save bmp calibration values");
        System.out.println("4: read saved data");
        System.out.println("q: quit");
    }

    void whoAmI(){
        System.out.println("MPU9250");
        mpu.whoAmI();
        System.out.println("MPU9250Mag");
        mag.whoAmI();
        System.out.println("BMP180");
        bmp.whoAmI();
    }

    private void saveData(){
        FileSaver fs = new FileSaver();
        fs.start("data/measurements/" + System.nanoTime(), bmp.readRawData());
    }

    private void readData(){
        FileReader fr = new FileReader();
        fr.printFolder("data/measurements");
    }

    private void bmpReadCalibrationValues(){
        FileSaver fs = new FileSaver();
        fs.start("data/measurements/calibrationValues", bmp.readCalibrationValuesRaw());
    }
    private void run() {
        Scanner in = new Scanner(System.in);
        String choice;
        do {
            menu();
            choice = in.nextLine().toUpperCase();
            switch (choice) {
                case "1":
                    whoAmI();
                    break;
                case "2":
                    saveData();
                    break;
                case "3":
                    bmpReadCalibrationValues();
                    break;
                case "4":
                    readData();
                    break;
                case "Q":
                    in.close();
                    System.out.println("Quitting!");
                    break;
                default:
                    System.out.println("Please select a valid input!");
                    break;
            }
        } while (!choice.equals("Q"));
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.run();

    }
}
