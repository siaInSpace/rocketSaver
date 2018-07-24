package sia20.rocketSaver;

public class Main {

    private void readBMPCalVals(){
        MPU9520 mpu = new MPU9520();
        mpu.disableBypass();
        mpu.resetAllSlaves();
        mpu.configureSlaveRead(0, 0x77, 0xAA, 12);
        mpu.configureSlaveRead(1, 0x77, 0xAA+12, 10);
        mpu.activateSlaves(new int[] {0, 1});
        System.out.println("Data: ");
        byte[] data = mpu.readExtSensorData(22);
        for (byte calVal : data) {
            System.out.println(calVal);
        }
    }

    public static void main(String[] args){
        System.out.println("Hello Space!");
        Main app = new Main();
        app.readBMPCalVals();
    }
}
