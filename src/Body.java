import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Body extends JPanel {

    private Object[][] data = {};
    private final String[] columnNames = {
            "ID de Venta",
            "Tipo de Pollo",
            "Tipo de Corte",
            "Acompañamiento",
            "Descripción",
            "Precio",
            "Total de compra"
    };

    private final JTable table;

    public Body() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Datos de ventas");
        label.setFont(new Font("serif", Font.BOLD, 20));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 200));

        add(label);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(scrollPane);

        actualizarDatos();
    }

    public void actualizarDatos() {
        data = cargarDatosDeVentas();
        table.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    private Object[][] cargarDatosDeVentas() {
        List<Object[]> ventas = new ArrayList<>();
        String archivo = "ventas.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String line;
            int lineaNumero = 0;
            while ((line = br.readLine()) != null) {
                lineaNumero++;
                String[] fields = line.split("~");
                if (fields.length == 6) {
                    try {
                        Object[] venta = new Object[7];
                        venta[0] = fields[0].trim(); // ID de Venta
                        venta[1] = fields[1].trim(); // Tipo de Pollo
                        venta[2] = fields[2].trim(); // Tipo de Corte
                        venta[3] = fields[3].trim(); // Acompañamiento
                        venta[4] = fields[5].trim(); // Descripción
                        double precioPollo = Double.parseDouble(fields[4].trim()); // Precio del Pollo
                        double precioExtra = 50.0; // Suponiendo un costo fijo para el extra
                        venta[5] = precioPollo; // Precio del pollo
                        venta[6] = precioPollo + precioExtra; // Precio Total de compra (pollos + extra)

                        ventas.add(venta);
                    } catch (NumberFormatException e) {
                        System.err.println("Error de formato en la línea " + lineaNumero + ": " + e.getMessage());
                    }
                } else {
                    System.err.println("Cantidad incorrecta de campos en la línea " + lineaNumero);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo " + archivo + ": " + e.getMessage());
        }

        if (ventas.isEmpty()) {
            System.err.println("No se cargaron datos de ventas. Verifique el archivo " + archivo);
        }

        return ventas.toArray(new Object[0][]);
    }
}