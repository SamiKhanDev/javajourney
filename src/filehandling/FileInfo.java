package filehandling;

import java.io.File;

public class FileInfo {
    public static void main(String[] args) {
        File myFile = new File("file.txt");
        if(myFile.exists()){
            System.out.println("Absolute path : " + myFile.getAbsolutePath());
            System.out.println("writable : "+ myFile.canWrite());
            System.out.println("Readable : "+ myFile.canRead());
            System.out.println("Size of the file : "+ myFile.length());
        }
    }
}
