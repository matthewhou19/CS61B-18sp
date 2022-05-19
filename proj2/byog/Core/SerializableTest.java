package byog.Core;

import java.awt.desktop.SystemSleepEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializableTest {
    public static void main(String[] args) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.txt"))) {
            oos.writeChar('S');
            oos.writeChar('N');
            oos.writeChar('W');



        }catch (Exception e) {
            e.printStackTrace();
        }

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.txt"))) {
            StringBuilder st = new StringBuilder();
            char a = ois.readChar();
            char b = ois.readChar();
            char c = ois.readChar();
            st.append(a);
            st.append(b);
            st.append(c);
            System.out.println(st.toString());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
