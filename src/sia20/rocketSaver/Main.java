package sia20.rocketSaver;

public class Main {

    private void readBMPCalVals(){
        MPU9520 mpu = new MPU9520();
        mpu.disableBypass();
        mpu.resetAllSlaves();
        mpu.configureSlaveRead(0, 0x77, 0xAA, 12);
        mpu.configureSlaveRead(1, 0x77, 0xAA+12, 10);
        byte[] data = mpu.readExtSensorData(22);
        for (byte calVal : data) {
            System.out.println(Integer.toBinaryString(calVal));
        }
    }


    public static void Main(String[] args){
        System.out.println("Hello Space!");
    }
}
