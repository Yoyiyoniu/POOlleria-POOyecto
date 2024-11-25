import enums.ChickenCutType;
import enums.RostTypes;
import utils.RandomString;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Nav extends JPanel implements ActionListener {

    private final JButton sell;
    private final JButton update;
    private final JButton search;

    public Nav() throws IOException {
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
            SpinnerModel model = new SpinnerNumberModel(0, 0, 1000, 1);
            SpinnerModel model2 = new SpinnerNumberModel(0, 0, 1000, 1);

            JSpinner quantityField = new JSpinner(model);
            JSpinner priceField = new JSpinner(model2);

            JTextArea description = new JTextArea("", 10, 4);


            JComboBox<RostTypes> chikenBox = new JComboBox<>(RostTypes.values());
            JComboBox<ChickenCutType> cutType = new JComboBox<>(ChickenCutType.values());

            Object[] message = {
                    "Cantidad:", quantityField,
                    "Precio:", priceField,
                    "Tipo de pollo", chikenBox,
                    "Tipo de corte de pollo", cutType
            };

            int option = JOptionPane.showConfirmDialog(this, message, "Formulario de Venta de POOllo", JOptionPane.OK_CANCEL_OPTION);


            if (option == JOptionPane.OK_OPTION) {
                String id = RandomString.getRandomString();
                int price = (int) priceField.getValue();
                String desc = description.getText();
                int amount = (int) quantityField.getValue();
                RostTypes rostTypes = (RostTypes) chikenBox.getSelectedItem();
                ChickenCutType cut = (ChickenCutType) cutType.getSelectedItem();

                System.out.println(id + price + desc + amount + rostTypes + cut);
                // TODO: STORAGE THE ROASTEDCHICKEN OBJECT IN TEXT FILE


            }
        }

        if (actionEvent.getSource() == search) {
            System.out.println("Buscar por item");
            //TODO: ADD TO SEARCH OPTION
        }

        if (actionEvent.getSource() == update) {
            System.out.println("Botón 'Actualizar lista' clicado");
            //TODO: READ THE STORAGE FILES
        }
    }
}