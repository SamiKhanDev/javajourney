package filehandling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class ReadAndWritefromFile {
    public static void main(String[] args) throws FileNotFoundException {
        try{
            File myFile = new File("file.txt");

            if(myFile.createNewFile()){

                System.out.println("new file is created in java"+ myFile.getName());
            } else
                System.out.println("file already exist");
        } catch (Exception e) {
            System.out.println("an error occurred");
         e.printStackTrace();
        }


        try {
            FileWriter myFilewriter = new FileWriter("file.txt");

            myFilewriter.write("Successfully created file in java im happy now");
            myFilewriter.close();
            System.out.println("file written successfully");


        } catch (Exception e) {
            System.out.println("an error occurred");
            e.printStackTrace();
        }
        try {
            File myFile = new File("file.txt");
            Scanner Reader = new Scanner(myFile);
            while (Reader.hasNextLine()){
                String data = Reader.nextLine();
                System.out.println(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("unable to read the file");
        }


    }

}
