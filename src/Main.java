import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main extends JFrame {

    public static void main(String[] args) {
        try {
            JFrame frame = new JFrame("POOyecto - POOlleria");
            frame.setSize(1200, 900);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setLayout(new BorderLayout());

            frame.add(new Nav(), BorderLayout.NORTH);
            frame.add(new Body(), BorderLayout.CENTER);

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}