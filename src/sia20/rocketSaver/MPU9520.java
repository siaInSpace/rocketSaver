package sia20.rocketSaver;

class MPU9520 extends Sensor{
    MPU9520(){
        super(0x68);
    }
    void configureSlaveRead(int slaveNumber, int slaveAddress, int regAddress, int length){
        length = length & 0xF;
        int slaveAddr = 0x25 + (3*slaveNumber);
        int slaveReg = slaveAddr + 1;
        int slaveCtrl = slaveReg + 1;
        write(slaveAddr, (byte)(0x80 | slaveAddress));
        write(slaveReg, (byte)regAddress);
        write(slaveCtrl, (byte)length);
    }
    void activateSlaves(int[] activeSlaves){
        int slaveCrtl = 0x27;
        for (int slave :
                activeSlaves) {
            int currentSlaveCrtl = slaveCrtl+(3*slave);
            byte ctrl = read(currentSlaveCrtl);
            write(currentSlaveCrtl, (byte)(ctrl | 0x80));
        }
    }
    void resetAllSlaves(){
        int slaveCrtl = 0x27;
        for (int i = 0; i < 4; i++) {
            write(slaveCrtl+(3*i), (byte)0x00);
        }
        write(0x34, (byte)0x00);
    }
    byte[] readExtSensorData(int length){
        return read(0x49, length);

    }

    void whoAmI(){
        int address = read(0x75);
        System.out.println("I should be 0x73, I am: " + String.format("0x%02X", address));
    }
    void disableBypass(){
        write(0x24, (byte)0x0D); //configure clock speed
        write(0x37, (byte)0x00); //disable bypass
        write(0x6A, (byte)0x20); //enable master
    }
}
