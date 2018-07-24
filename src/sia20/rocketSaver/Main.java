package sia20.rocketSaver;

public class Main {

    private void readBMPCalVals(){
        MPU9520 mpu = new MPU9520();
        mpu.disableBypass();
        mpu.resetAllSlaves();
        mpu.configureSlaveRead(0, 0x77, 0xAA, 12);
        mpu.configureSlaveRead(1, 0x77, 0xAA+12, 10);
        mpu.activateSlaves(new int[] {1, 2});
        byte[] data = mpu.readExtSensorData(22);
        for (byte calVal : data) {
            System.out.println(calVal);
        }
        byte[] written = mpu.read(0x27, 7);
        for (byte info :
                written) {
            System.out.println(info);
        }
    }   

    public static void main(String[] args){
        System.out.println("Hello Space!");
        Main app = new Main();
        app.readBMPCalVals();
    }
}
