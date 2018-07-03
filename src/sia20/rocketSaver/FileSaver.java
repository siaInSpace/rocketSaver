package sia20.rocketSaver;

import java.io.*;
import java.util.BitSet;

/**
 * FileSaver: Saves data to a file
 * 
 * @author Sindre Aalhus
 * @version 0.5
 */
class FileSaver implements Runnable {
    private String path;
    private byte[] saveData;

    private void saveBytes(byte[] bytes, String pathName) {
        File file = new File(pathName);
        file.getParentFile().mkdirs();
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bos.write(bytes);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Override
    public void run() {
        saveBytes(saveData, path);
    }

    void start(String pathName, byte[] dataToSave) {
        path = pathName;
        saveData = dataToSave;
        Runnable task = () -> run();
        Thread t = new Thread(task, "saveToFile: " + path);
        t.start();
    }

}