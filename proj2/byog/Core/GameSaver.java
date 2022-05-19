package byog.Core;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GameSaver {
    public static void save(String s) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save.txt"))) {
            oos.writeUTF(s);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String load() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save.txt"))) {
            return ois.readUTF();

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
