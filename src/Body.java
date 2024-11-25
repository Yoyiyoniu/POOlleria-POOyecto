import javax.swing.*;
import java.awt.*;

class Body extends JPanel {

    Object[][] data = {
            {"Kathy", "Smith", "Snowboarding", 5},
            {"John", "Doe", "Rowing", 3},
            {"Sue", "Black", "Knitting", 2},
            {"Jane", "White", "Speed reading", 20},
            {"Joe", "Brown", "Pool", 10}
    };

    String[] columnNames = {
            "Nombre del cliente",
            "Producto comprado",
            "ID de producto",
            "Total de compra"
    };

    // TODO: READ TEXT FILE THEN SET THE INFORMATION TO MATRIX THEN SET ALL INFORMATON IN THEE TABLE

    public Body() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Datos de ventas");
        Font f = new Font("serif", Font.BOLD, 20);
        label.setFont(f);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.setPreferredSize(new Dimension(400, 200));

        add(label);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(scrollPane);
    }
}