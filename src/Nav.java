import enums.AccompanimentType;
import enums.ChickenCutType;
import enums.RostTypes;
import models.RoastedChicken;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

public class Nav extends JPanel implements ActionListener {

    private final JButton sell;
    private final JButton update;
    private final JButton search;
    private final Body body;

    public Nav(Body body) throws IOException {
        this.body = body;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        sell = new JButton("Vender pollo");
        update = new JButton("Actualizar lista");
        search = new JButton("Buscar por ID de venta");

        add(createLogoPanel());
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(createButtonsPanel());

        sell.addActionListener(this);
        update.addActionListener(this);
        search.addActionListener(this);
    }

    private JPanel createLogoPanel() throws IOException {
        BufferedImage myPicture = ImageIO.read(new File("assets/logo.png"));
        Image scaledImage = myPicture.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel picLabel = new JLabel(new ImageIcon(scaledImage));
        picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel logoPanel = new JPanel();
        logoPanel.add(picLabel);
        return logoPanel;
    }

    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());

        buttonsPanel.add(sell);
        buttonsPanel.add(update);
        buttonsPanel.add(search);

        return buttonsPanel;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == sell) {
            handleSellAction();
        } else if (actionEvent.getSource() == search) {
            handleSearchAction();
        } else if (actionEvent.getSource() == update) {
            handleUpdateAction();
        }
    }

    private void handleSellAction() {
        SpinnerModel model = new SpinnerNumberModel(0, 0, 1000, 1);

        JSpinner valueField = new JSpinner(model);

        JTextArea description = new JTextArea("", 10, 4);

        JComboBox<RostTypes> chikenBox = new JComboBox<>(RostTypes.values());
        JComboBox<ChickenCutType> cutType = new JComboBox<>(ChickenCutType.values());
        JComboBox<AccompanimentType> acom = new JComboBox<>(AccompanimentType.values());



        Object[] message = {
                "Costo", valueField,
                "Tipo de pollo", chikenBox,
                "Tipo de corte de pollo", cutType,
                "Acompañamiento", acom,
                "Descripcion", description
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Formulario de Venta de Pollo", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {

            String desc = description.getText();
            RostTypes rostTypes = (RostTypes) chikenBox.getSelectedItem();
            ChickenCutType cut = (ChickenCutType) cutType.getSelectedItem();
            AccompanimentType accType = (AccompanimentType) acom.getSelectedItem();
            int price = (int) valueField.getValue();

            if(desc.isEmpty() || price == 0 || rostTypes == null || cut == null || accType == null){
                JOptionPane.showMessageDialog(this, "Por favor llene todos los campos");
                return;
            }
            RoastedChicken roastedChicken = new RoastedChicken(rostTypes, cut, desc, price, accType);

            System.out.println(roastedChicken);
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("ventas.txt", true));
                writer.write(roastedChicken + "\n");
                writer.close();
            } catch (IOException e) {
                System.err.println("Error al escribir en el archivo" + e.getMessage());
            }
        }
    }


    private void handleSearchAction() {
        String id = JOptionPane.showInputDialog(this, "Ingrese el ID de venta a buscar:");
        if (id != null && !id.trim().isEmpty()) {
            String result = buscarPorId(id.trim());
            if (result != null) {
                JOptionPane.showMessageDialog(this, "Datos de la venta:\n" + result);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ninguna venta con el ID proporcionado.");
            }
        }
    }

    private String buscarPorId(String id) {
        String archivo = "ventas.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split("~");
                if (fields.length == 6 && fields[0].trim().equals(id)) {
                    return "ID de Venta: " + fields[0].trim() + "\n" +
                            "Tipo de Pollo: " + fields[1].trim() + "\n" +
                            "Tipo de Corte: " + fields[2].trim() + "\n" +
                            "Acompañamiento: " + fields[3].trim() + "\n" +
                            "Precio: " + fields[4].trim() + "\n" +
                            "Descripción: " + fields[5].trim();
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo " + archivo + ": " + e.getMessage());
        }
        return null;
    }

    private void handleUpdateAction() {
        body.actualizarDatos();
        JOptionPane.showMessageDialog(this, "Lista actualizada exitosamente.");
    }
}