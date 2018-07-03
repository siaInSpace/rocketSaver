package sia20.rocketSaver;

public class BMP180pressure extends Slave{

    private Oss oss;

    BMP180pressure(Oss oss, MPU9250 master){
        super(master, 0x77);
        this.oss = oss;
    }

    byte[] getRaw(){
        byte signal = (byte) (0x34 + (oss.getVal() << 6));
        write(0xF4, signal);
        try {
            Thread.sleep(oss.getTime());
        } catch (InterruptedException e) {
            System.out.println("Could not wait Xms, idk why");
            e.printStackTrace();
        }
        byte[] data = {read(0xF6), read(0xF7), read(0xF8)};
        return data;
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
