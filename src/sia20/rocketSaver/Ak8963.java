package sia20.rocketSaver;

class Ak8963 {
    private MPU9520 master;

    Ak8963(MPU9520 master){
        this.master = master;
    }

    void whoAmI(){
        master.resetAllSlaves();
        master.configureSlaveRead(0, 0x0C, 0x00, 1);
        int id = master.readExtSensorData(1)[0];
        System.out.println("I should be 0x48, I am: " + String.format("0x%02X", id));

    }
}
