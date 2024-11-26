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

    private static final String VENTAS_FILE = "ventas.txt";
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

        BufferedImage myPicture = ImageIO.read(new File("assets/logo.png"));
        Image scaledImage = myPicture.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel picLabel = new JLabel(new ImageIcon(scaledImage));
        picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());

        buttonsPanel.add(sell);
        buttonsPanel.add(update);
        buttonsPanel.add(search);

        add(picLabel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(buttonsPanel);

        sell.addActionListener(this);
        update.addActionListener(this);
        search.addActionListener(this);
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
        JSpinner priceField = new JSpinner(model);

        JTextArea description = new JTextArea("", 10, 4);

        JComboBox<RostTypes> chikenBox = new JComboBox<>(RostTypes.values());
        JComboBox<ChickenCutType> cutType = new JComboBox<>(ChickenCutType.values());
        JComboBox<AccompanimentType> accompanimentType = new JComboBox<>(AccompanimentType.values());

        Object[] message = {
                "Precio:", priceField,
                "Tipo de pollo", chikenBox,
                "Tipo de corte de pollo", cutType,
                "Acompañamiento", accompanimentType,
                "Descripción:", description
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Formulario de Venta de POOllo", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            int price = (int) priceField.getValue();
            String desc = description.getText();

            RostTypes rostTypes = (RostTypes) chikenBox.getSelectedItem();
            ChickenCutType cut = (ChickenCutType) cutType.getSelectedItem();
            AccompanimentType accompaniment = (AccompanimentType) accompanimentType.getSelectedItem();

            RoastedChicken roastedChicken = new RoastedChicken(rostTypes, cut, price, desc, accompaniment);
            System.out.println(roastedChicken);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(VENTAS_FILE, true))) {
                writer.write(roastedChicken.toString());
                writer.newLine();
                JOptionPane.showMessageDialog(this, "Venta guardada exitosamente.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al guardar la venta: " + e.getMessage());
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
        try (BufferedReader br = new BufferedReader(new FileReader(VENTAS_FILE))) {
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
            System.err.println("Error al leer el archivo " + VENTAS_FILE + ": " + e.getMessage());
        }
        return null;
    }

    private void handleUpdateAction() {
        body.actualizarListaVentas();
    }
}