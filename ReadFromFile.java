import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFromFile {
    public void read() throws FileNotFoundException {
        Scanner reader = new Scanner(new File("data.txt"));

        while(reader.hasNextLine())
        {
            String line = reader.nextLine();
            System.out.println(line);
        }
        reader.close();
    }
}
