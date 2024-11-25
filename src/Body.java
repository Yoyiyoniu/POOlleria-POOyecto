// src/Body.java
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Body extends JPanel {

    Object[][] data = {};

    String[] columnNames = {
            "Tipo de Pollo",
            "Tipo de Corte",
            "Acompañamiento",
            "ID de Venta",
            "Precio",
            "Descripción",
            "Cantidad",
            "Total de compra"
    };

    JTable table;

    public Body() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Datos de ventas");
        Font f = new Font("serif", Font.BOLD, 20);
        label.setFont(f);
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
                String[] fields = line.split("\\|"); // Usar solo "|" como delimitador
                if (fields.length == 8) {
                    try {
                        Object[] venta = new Object[8];
                        venta[0] = fields[0].trim(); // Tipo de Pollo
                        venta[1] = fields[1].trim(); // Tipo de Corte
                        venta[2] = fields[2].trim(); // Acompañamiento
                        venta[3] = fields[3].trim(); // ID de Venta
                        venta[4] = Double.parseDouble(fields[4].trim()); // Precio
                        venta[5] = fields[5].trim(); // Descripción
                        venta[6] = Integer.parseInt(fields[6].trim()); // Cantidad
                        venta[7] = Double.parseDouble(fields[7].trim()); // Total de compra
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