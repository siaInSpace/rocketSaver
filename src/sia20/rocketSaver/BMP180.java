package sia20.rocketSaver;

class BMP180 extends Slave {
    // calibration values
    /*private short AC1;
    private short AC2;
    private short AC3;
    private int AC4;
    private int AC5;
    private int AC6;
    private short B1;
    private short B2;
    private short MB;
    private short MC;
    private short MD;
*/
    private BMP180temp temp;
    private BMP180pressure pressure;

    BMP180(BMP180pressure.Oss oss, MPU9250 master) {
        super(master, 0x77);
        temp = new BMP180temp(master);
        pressure = new BMP180pressure(oss, master);
    }

    byte[] readCalibrationValuesRaw() {
        /*
        byte[] calValues = new byte[22];
        byte[] ac = read(0xAA, 12);
        byte[] re = read(0xAA + 12, 10);
        System.arraycopy(ac, 0, calValues, 0, ac.length);
        System.arraycopy(re, 0, calValues, ac.length, re.length);
        return calValues;
        */
        disabelAllSlaves();
        configureSlaveRead(0xAA, 12, 0);
        configureSlaveRead(0xAA+12, 10, 1);
        activateSlaves(new int[] {0, 1});
        return readSlaveData(24);
    }

    byte[] readRawData() {
        byte[] tempData = temp.getRaw();
        byte[] pressureData = pressure.getRaw();
        byte[] data = new byte[tempData.length + pressureData.length];
        System.arraycopy(tempData, 0, data, 0, tempData.length);
        System.arraycopy(pressureData, 0, data, tempData.length, pressureData.length);
        return data;
    }

    void whoAmI() {
        int res = super.read(0xD0);
        System.out.println("I should be 0x55, I am: " + String.format("0x%02X", res));
    }
}