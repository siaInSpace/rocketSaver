package sia20.rocketSaver;

class MPU9250 extends Sensor {

    MPU9250() {
        super(0x68);
    }

    byte[] returnExtData(int length){
        return word.readBytes(0x49, length);
    }

    void readExtData(int length) {
        byte[] data = word.readBytes(0x49, length);
        for (byte d : data) {
            System.out.println(d);
        }
    }

    byte[][] readRawValues() {
        byte[][] data = new byte[4][];
        data[0] = readAccRaw();
        data[1] = readTempRaw();
        data[2] = readGyrRaw();
        return data;
        //{{AccXH, AccXL, AccYH, AccYL, AccZH, AccZL},
        // {TempH, TempL},
        // {GyrXH, GyrXL, GyrYH, GyrZH, GyrZL},
        // {MagXL, MagXH, MagYL, MagYH, MagZL, MagZH}}
    }

    private byte[] readAccRaw() {
        return word.readBytes(0x3b, 6);
    }

    private byte[] readTempRaw() {
        return word.readBytes(0x41, 2);
    }

    private byte[] readGyrRaw() {
        return word.readBytes(0x43, 6);
    }


    void whoAmI() {
        int res = read(0x75);
        System.out.println("I should be 0x73, I am: " + String.format("0x%02X", res));
    }

    void disableBypass() {
        int i2c_mst_ctrl = 0x24;
        int int_config = 0x37;
        int user_ctrl = 0x6A;
        write(i2c_mst_ctrl, (byte) 0b01011101); //configures the i2c (clock speed etc.)
        write(int_config, (byte) 0b00000000); //Disables bypass mode
        write(user_ctrl, (byte) 0b00100000); //Enables i2c master
    }

    void enableBypass() {
        write(0x37, (byte) 0b00000010);
        write(0x24, (byte) 0x5D);
        write(0x6A, (byte) 0x00);
    }

    void configureSlave(int slave, boolean read, int addr, int register, int length, boolean oddGroup){
        byte address;
        byte ctrl;
        int slave0StartAddr = 0x25;
        int startAddress = slave0StartAddr + 3*slave;

        if (read){
            address = (byte)(0xFF & addr);
        }else {
            address = (byte)(0x7F & addr);
        }
        if (oddGroup){
            ctrl = 0b1010;
        }else {
            ctrl = 0b1011;
        }
        ctrl = (byte)(ctrl<<4 | length);

        write(startAddress, address);
        write(startAddress+1, (byte)register);
        write(startAddress+2, ctrl);
    }

    void slaveDataOut(int slave, byte data){
        write(0x63+slave, data);
    }
}

