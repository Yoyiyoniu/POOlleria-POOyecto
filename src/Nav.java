// src/Nav.java
import enums.AccompanimentType;
import enums.ChickenCutType;
import enums.RostTypes;
import models.Acompaniment;
import models.RoastedChicken;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

        JSpinner quantityField = new JSpinner(model);

        JTextArea description = new JTextArea("", 10, 4);

        JComboBox<RostTypes> chikenBox = new JComboBox<>(RostTypes.values());
        JComboBox<ChickenCutType> cutType = new JComboBox<>(ChickenCutType.values());
        JComboBox<AccompanimentType> acom = new JComboBox<>(AccompanimentType.values());

        Object[] message = {
                "Cantidad:", quantityField,
                "Tipo de pollo", chikenBox,
                "Tipo de corte de pollo", cutType,
                "Acompañamiento", acom,
                "Descripcion", description
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Formulario de Venta de Pollo", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String desc = description.getText();
            int amount = (int) quantityField.getValue();
            RostTypes rostTypes = (RostTypes) chikenBox.getSelectedItem();
            ChickenCutType cut = (ChickenCutType) cutType.getSelectedItem();
            AccompanimentType accType = (AccompanimentType) acom.getSelectedItem();
            Acompaniment acc = new Acompaniment(accType);

            RoastedChicken roastedChicken = new RoastedChicken(rostTypes, cut, desc, amount, acc);

            saveRoastedChickenData(roastedChicken);
        }
    }


    private void saveRoastedChickenData(RoastedChicken roastedChicken) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ventas.txt", true))) {
            writer.write(roastedChicken.toString() + " | " + roastedChicken.getAmount() + "\n");
            JOptionPane.showMessageDialog(this, "Datos guardados exitosamente.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleSearchAction() {
        System.out.println("Buscar por item");
    }

    private void handleUpdateAction() {
        body.actualizarDatos();
        JOptionPane.showMessageDialog(this, "Lista actualizada exitosamente.");
    }
}