package sia20.rocketSaver;

class Bmp180 {

    private MPU9520 master;
    private Oss oss;

    Bmp180(MPU9520 master, Oss oss){
        this.master = master;
        this.oss = oss;
    }

    byte[] readCalibrationValues(){
        master.disableBypass();
        master.resetAllSlaves();
        master.configureSlaveRead(0, 0x77, 0xAA, 12);
        master.configureSlaveRead(1, 0x77, 0xAA+12, 10);
        master.activateSlaves(new int[] {0, 1});
        return master.readExtSensorData(22);
    }

    byte[] readRawTemp(){
        master.disableBypass();
        master.configureSlaveWrite(0, 0x77, 0xF4, (byte)(0x34 + 0x2E));
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        master.resetAllSlaves();
        master.configureSlaveRead(0, 0x77, 0xF6, 2);
        master.activateSlaves(new int[] {0});
        return master.readExtSensorData(2);
    }

    byte[] readRawPressure(){
        master.disableBypass();
        master.configureSlaveWrite(0, 0x77, 0xF4, (byte)(0x34 + (oss.getVal()<<6)));
        try {
            Thread.sleep(oss.getTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        master.resetAllSlaves();
        master.configureSlaveRead(0, 0x77, 0xF6, 3);
        master.activateSlaves(new int[] {0});
        return master.readExtSensorData(3);
    }

    void whoAmI(){
        master.disableBypass();
        master.resetAllSlaves();
        master.configureSlaveRead(0, 0x77, 0xD0, 1);
        master.activateSlaves(new int[] {0});
        int id = master.readExtSensorData(1)[0];
        System.out.println("I should be 0x55, I am: " + String.format("0x%02X", id));
    }


    enum Oss {
        LOW_POWER(0, 5), STANDARD(1, 8), HIGHRES(2, 14), ULTRAHIGHRES(3, 26);
        private int id;
        private int waitTime;
        Oss(int val, int time) {
            this.id = val;
            this.waitTime = time;
        }
        int getVal() {
            return id;
        }
        int getTime() {
            return waitTime;
        }
    }
}