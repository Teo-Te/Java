import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperations {
    public void createFile(String fileName) {
        //Create the file
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            System.out.println("An error occurred");
        }
    }

    public void deleteFile(String fileName) {
        //Delete the file
        File file = new File(fileName);
        if (file.delete()) {
            System.out.println("Deleted the file: " + file.getName());
        } else {
            System.out.println("Failed to delete the file");
        }
    }

    public void writeFile(String fileName, String content) {
        //Write to the file
        try {
            File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file, true); // Append mode
            fileWriter.write(content); 
            fileWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public String readFile(String fileName) {
        //Read from the file
        String data = "";
        try {
            File file = new File(fileName);
            java.util.Scanner scanner = new java.util.Scanner(file);
            while (scanner.hasNextLine()) {
                data = scanner.nextLine();
            }
            scanner.close(); 
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }

        return data;
    }
}
