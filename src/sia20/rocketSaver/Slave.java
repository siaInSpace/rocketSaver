package sia20.rocketSaver;

public class Slave {
    private MPU9520 master;
    private int adress;

    Slave(MPU9520 master, int address){
        this.master = master;
        this.adress = address;
    }

    void configureRead(int slaveNumber, int regAddress, int length){
        master.configureSlaveRead(slaveNumber, adress, regAddress, length);
    }

    void configureWrite(int slaveNumber, int regAddress, byte data){
        master.configureSlaveWrite(slaveNumber, adress, regAddress, data);
    }
}
