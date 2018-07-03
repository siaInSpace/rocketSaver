package sia20.rocketSaver;

class BMP180temp extends Slave {

    BMP180temp(MPU9250 master){
        super(master, 0x77);
    }

    byte[] getRaw() {
        write(0xF4, (byte)0x2E);
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            System.out.println("Could not wait 5ms, idk why");
            e.printStackTrace();
        }
        byte[] data = {read(0xF6), read(0xF7)};
        return data;
    }
}
