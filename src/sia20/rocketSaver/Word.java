package sia20.rocketSaver;

import java.util.BitSet;

class Word {
    private Sensor device;

    public Word(Sensor dev) {
        device = dev;
    }

    public byte[] readBytes(int regAddress, int nrAddress) {
        byte[] bytes = new byte[nrAddress];
        for (int i = 0; i < nrAddress; i++) {
            bytes[i] = (byte) (device.read(regAddress + i) & 0xff);
        }
        return bytes;
    }

    private byte[] reverseArray(byte[] arr) {
        byte[] reversed = new byte[arr.length];
        for (int i = 0; i < arr.length; i++) {
            reversed[i] = arr[arr.length - (i + 1)];
        }
        return reversed;
    }

    public short toShort(byte[] values) {
        return (short) (BitSet.valueOf(reverseArray(values)).toLongArray()[0]);
    }

    public int toInt(byte[] values) {
        return (int) (BitSet.valueOf(reverseArray(values)).toLongArray()[0]);
    }

    public long toLong(byte[] values) {
        return (BitSet.valueOf(reverseArray(values)).toLongArray()[0]);
    }
}
