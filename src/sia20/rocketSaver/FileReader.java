package sia20.rocketSaver;

// data is saved in a structure as: {bmpTempMSB, bmpTempLSB, bmpPresMSB, bmpPresLSB, bmpPresXLSB...}

import java.io.*;

class FileReader {

    void printFolder(String path){
        File folder = new File(path);
        File[] files = folder.listFiles();
        for (File file : files) {
            byte[] data = readFile(file.getPath());
            System.out.print(file.getPath() + ": {");
            for (byte dat :
                    data) {
                System.out.print(dat + ", ");
            }
            System.out.println("}");
        }
    }

    byte[] readFile(String path){
        File file = new File(path);
        BufferedInputStream bis = null;
        byte[] data = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(file));
        }catch (FileNotFoundException e){
            System.out.println("File not found at: " + file.getAbsolutePath());
            System.out.println(e.getLocalizedMessage());
        }
        try {
            data = new byte[bis.available()];
            for (int i = 0; i < data.length; i++) {
                data[i] = (byte)bis.read();
            }
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    /*
            FileReader fr = new FileReader();
        File[] files = (new File("data/measurements").listFiles());
        byte[][] data = new byte[files.length][];
        for (int i = 0; i < files.length; i++) {
            data[i] = fr.readBytes(files[i]);
        }
        for (byte[] bytes : data) {
            for (byte byt : bytes) {
                System.out.print(byt + " ");
            }
            System.out.println();
        }
        //Data is structured as {AccXH, AccXL, AccYH, AccYL, AccZH, AccZL, TempH, TempL, GyrXH, GyrXL,
        //                              GyrYH, GyrZH, GyrZL, MagXL, MagXH, MagYL, MagYH, MagZL, MagZH}

     */
}
