package sia20.rocketSaver;

public class Main {

    private void readBMPCalVals(){
        MPU9520 mpu = new MPU9520();
        mpu.disableBypass();
        mpu.resetAllSlaves();
        mpu.configureSlaveRead(0, 0x77, 0xAA, 12);
        mpu.configureSlaveRead(1, 0x77, 0xAA+12, 10);
        mpu.activateSlaves(new int[] {0, 1});
        byte[] firstData = mpu.readExtSensorData(22);
        mpu.resetAllSlaves();
        mpu.configureSlaveRead(0, 0x77, 0xAA, 10);
        mpu.configureSlaveRead(1, 0x77, 0xAA+10, 12);
        mpu.activateSlaves(new int[] {0, 1});
        byte[] secondData = mpu.readExtSensorData(22);
        System.out.println("Data: ");
        for (int i = 0; i < 22; i++) {
            System.out.println(firstData[i] + ",  " + secondData[i]);
        }
    }

    public static void main(String[] args){
        System.out.println("Hello Space!");
        Main app = new Main();
        app.readBMPCalVals();
    }
}
