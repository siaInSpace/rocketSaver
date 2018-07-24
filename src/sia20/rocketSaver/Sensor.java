package sia20.rocketSaver;


import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

import java.io.IOException;

class Sensor {

    private I2CDevice device;
    Sensor(int address){
        I2CBus bus;
        try {
            bus = I2CFactory.getInstance(I2CBus.BUS_1);
            device = bus.getDevice(address);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    byte read(int regAddress){
        byte data = 0;
        try {
            data = (byte)device.read(regAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    byte[] read(int regAddress, int length){
        byte[] data = new byte[length];
        for (int i = 0; i < length; i++) {
            data[i] = read(regAddress+i);
        }
        return data;
    }

    void write(int regAddress, byte data){
        try {
            device.write(regAddress, data);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public I2CDevice getDevice() {
        return device;
    }
}
