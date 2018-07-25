package sia20.rocketSaver;

public class Main {

    private MPU9520 mpu;
    private Bmp180 bmp;
    private Ak8963 mag;

    private Main(){
        mpu = new MPU9520();
        bmp = new Bmp180(mpu, Bmp180.Oss.STANDARD);
        mag = new Ak8963(mpu);
    }
    private void whoAmI(){
        mpu.whoAmI();
        bmp.whoAmI();
        mag.whoAmI();
    }

    private void readData(){
        String[] dataName = {"bmp calibration values", "bmp temperature", "bmp pressure", "mpu data"};
        byte[][] data = new byte[4][];

        data[0] = bmp.readCalibrationValues();
        data[1] = bmp.readRawTemp();
        data[2] = bmp.readRawPressure();
        data[3] = mpu.read(0x3B, 14);

        for (int i = 0; i < 4; i++) {
            System.out.print(dataName[i] + ": { ");
            for (byte dataByte : data[i]) {
                System.out.print(dataByte + ", ");
            }
            System.out.println("}");
        }
    }

    public static void main(String[] args){
        System.out.println("Hello Space!");
        Main app = new Main();
        app.whoAmI();
        app.readData();
    }
}
