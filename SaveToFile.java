import java.io.FileWriter;
import java.io.IOException;

public class SaveToFile {
    private String name, surname;

    public String getName()
    {
        return name;
    }
    public String getSurname()
    {
        return surname;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public void save() throws IOException {
        FileWriter writer = new FileWriter("data.txt", true);
        writer.write("Imie: " + name + "\n");
        writer.write("Nazwisko: " + surname + "\n");
        writer.close();
    }
}
