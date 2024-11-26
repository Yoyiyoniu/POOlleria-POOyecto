import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Body extends JPanel {

    String[] columnNames = {
            "ID de Venta",
            "Tipo de Pollo",
            "Tipo de Corte",
            "Acompañamiento",
            "Descripción",
            "Precio del Pollo",
            "Total de compra"
    };

    public Body() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Datos de ventas");
        Font f = new Font("serif", Font.BOLD, 20);
        label.setFont(f);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        Object[][] data = cargarDatosDeVentas(); // Cargar datos desde el archivo

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.setPreferredSize(new Dimension(600, 200));

        add(label);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(scrollPane);

        // Add context menu for deleting a sale
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("Eliminar pedido");
        popupMenu.add(deleteItem);

        table.setComponentPopupMenu(popupMenu);

        deleteItem.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String saleId = (String) table.getValueAt(selectedRow, 0);
                eliminarVenta(saleId);
                actualizarListaVentas();
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    JTable source = (JTable) e.getSource();
                    int row = source.rowAtPoint(e.getPoint());
                    int column = source.columnAtPoint(e.getPoint());

                    if (!source.isRowSelected(row)) {
                        source.changeSelection(row, column, false, false);
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    JTable source = (JTable) e.getSource();
                    int row = source.rowAtPoint(e.getPoint());
                    int column = source.columnAtPoint(e.getPoint());

                    if (!source.isRowSelected(row)) {
                        source.changeSelection(row, column, false, false);
                    }
                }
            }
        });
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
                        double precioAcompanamiento = 50.0; // Precio del Acompañamiento (puedes ajustar este valor según sea necesario)
                        venta[5] = precioPollo; // Precio del pollo
                        venta[6] = precioPollo + precioAcompanamiento; // Precio Total de compra (pollo + acompañamiento)

                        ventas.add(venta);
                    } catch (IllegalArgumentException e) {
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

    public void actualizarListaVentas() {
        Object[][] data = cargarDatosDeVentas();
        JTable table = (JTable) ((JScrollPane) getComponent(2)).getViewport().getView();
        table.setModel(new DefaultTableModel(data, columnNames));
    }

    private void eliminarVenta(String saleId) {
        File inputFile = new File("ventas.txt");
        File tempFile = new File("ventas_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String[] fields = currentLine.split("~");
                if (fields.length == 6 && fields[0].trim().equals(saleId)) {
                    continue; // Skip the line to be deleted
                }
                writer.write(currentLine);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al eliminar la venta: " + e.getMessage());
        }

        if (!inputFile.delete()) {
            System.err.println("No se pudo eliminar el archivo original");
        }

        if (!tempFile.renameTo(inputFile)) {
            System.err.println("No se pudo renombrar el archivo temporal");
        }
    }
}