package sia20.rocketSaver;

public class MPU9250Mag extends Slave{

    MPU9250Mag(MPU9250 master){
        super(master, 0x0C);
    }

    void whoAmI(){
        int res = read(0x00);
        System.out.println("I should be 0x48, I am: " + String.format("0x%02X", res));
    }
}
