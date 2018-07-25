package sia20.rocketSaver;

public class Main {

    private MPU9520 mpu;
    private Bmp180 bmp;
    private Ak8963 mag;

    private Main(){
        mpu = new MPU9520();
        bmp = new Bmp180(mpu, Bmp180.Oss.STANDARD);
        mag = new Ak8963(mpu);
    }
    private void whoAmI(){
        mpu.whoAmI();
        bmp.whoAmI();
        mag.whoAmI();
    }

    public static void main(String[] args){
        System.out.println("Hello Space!");
        Main app = new Main();
        app.whoAmI();
    }
}
