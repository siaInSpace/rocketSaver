package sia20.rocketSaver;

class Slave {
    private MPU9250 master;
    private int address;

    Slave(MPU9250 master, int address){
        this.master = master;
        this.address = address;
    }

    byte read(int register){
        return read(register, 1)[0];
    }

    byte[] read(int register, int length){
        master.disableBypass();
        int slave0Address = 0x25;
        int slave0Register = 0x26;
        int slave0Ctrl = 0x27;
        master.write(slave0Address, (byte)(0x80 | address));
        master.write(slave0Register, (byte)register);
        master.write(slave0Ctrl, (byte)(0b10001111 | (0xF & length)));
        byte[] data = new byte[length];
        for (int i = 0; i < length; i++) {
            data[i] = master.read(0x49+i);
        }
        return data;
    }

    void write(int register, byte data){
        master.disableBypass();
        int slave0Address = 0x25;
        int slave0Register = 0x26;
        int slave0Ctrl = 0x27;
        int slave0DataOut = 0x63;
        master.write(slave0Ctrl, (byte)0x00); //disable slave while configure
        master.write(slave0Address, (byte)address);
        master.write(slave0Register, (byte)register);
        master.write(slave0DataOut, data);
        master.write(slave0Ctrl, (byte)0x01);
    }
}
