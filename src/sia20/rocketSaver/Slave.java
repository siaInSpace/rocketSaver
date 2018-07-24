package sia20.rocketSaver;

public class Slave {
    private MPU9520 master;
    private int adress;

    Slave(MPU9520 master, int address){
        this.master = master;
        this.adress = address;
    }


}
