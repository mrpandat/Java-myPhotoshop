package GUI.view.layout;

import GUI.model.DrawModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;


public class MyToolbar extends JPanel {
    public JPanel mainPanel;
    public JPanel drawPanel = new JPanel();
    public JPanel erasePanel = new JPanel();
    public JPanel textPanel = new JPanel();


    MyToolbar(JPanel mainPanel) {
        this.mainPanel = mainPanel;


        drawPanel.setPreferredSize(new Dimension(200, 500));
        erasePanel.setPreferredSize(new Dimension(200, 500));
        textPanel.setPreferredSize(new Dimension(200, 500));

        erasePanel.setBorder(new EtchedBorder());
        drawPanel.setBorder(new EtchedBorder());
        textPanel.setBorder(new EtchedBorder());

        drawPanel.setVisible(false);
        erasePanel.setVisible(false);
        textPanel.setVisible(false);

    }


    public void generateButtons() {
        setBounds(60, 400, 220, 30);

        //DRAW BUTTON
        JButton drawbutton = new JButton();
        drawbutton.setIcon(new ImageIcon("asset/brush.png"));
        drawbutton.addActionListener(e -> {
            reset();

            DrawModel.getInstance().setType("draw");

            drawPanel.setVisible(true);

        });
        add(drawbutton);

        //ERASE BUTTON

        drawbutton = new JButton();
        drawbutton.setIcon(new ImageIcon("asset/eraser.png"));
        drawbutton.addActionListener(e -> {
            reset();

            DrawModel.getInstance().setType("erase");
            erasePanel.setVisible(true);
        });
        add(drawbutton);

        //TEXT BUTTON

        drawbutton = new JButton();
        drawbutton.setText("text");
        drawbutton.addActionListener(e -> {
            reset();

            DrawModel.getInstance().setType("text");
            textPanel.setVisible(true);
        });
        //add(drawbutton);


        setVisible(true);
        generateDrawMenu();
        generateEraseMenu();
        generateTextMenu();
    }

    public void generateDrawMenu() {
        JPanel jPanel = new JPanel();
        JLabel color = new JLabel("Your color :");
        color.setIcon(new ImageIcon("asset/color.png"));

        jPanel.add(color);
        jPanel.setPreferredSize(new Dimension(190, 25));


        drawPanel.add(jPanel);
        drawPanel.add(getColorChooser(jPanel));
        drawPanel.add(getSizeChooser(drawPanel));
        drawPanel.add(getShapeChooser(drawPanel));

        add(drawPanel);

    }

    public void generateEraseMenu() {
        erasePanel.add(getSizeChooser(erasePanel));
        erasePanel.add(getOpacityChooser(erasePanel));
        add(erasePanel);

    }

    public void generateTextMenu() {

        JPanel jPanel = new JPanel();
        JLabel color = new JLabel("Your color :");

        jPanel.add(color);
        jPanel.setPreferredSize(new Dimension(190, 25));

        textPanel.add(jPanel);
        textPanel.add(getColorChooser(textPanel));
        textPanel.add(getSizeChooser(textPanel));

        add(textPanel);

    }

    public JSlider getSizeChooser(JPanel j) {
        JLabel jLabel = new JLabel("Size :");
        jLabel.setIcon(new ImageIcon("asset/size.png"));
        j.add(jLabel);
        JSlider sizeChooser = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);
        sizeChooser.setMajorTickSpacing(25);
        sizeChooser.setMinorTickSpacing(1);
        sizeChooser.setPaintTicks(true);
        sizeChooser.setPaintLabels(true);
        sizeChooser.addChangeListener(e -> {
            DrawModel.getInstance().setSize(sizeChooser.getValue());
        });

        return sizeChooser;
    }

    public JList getShapeChooser(JPanel j) {
        String label[] = {"Oval", "Square", "Rectangle"};
        j.add(new JLabel("Shape :"));

        JList list = new JList<>(label);
        DefaultListSelectionModel sm = new DefaultListSelectionModel();
        sm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectionModel(sm);
        list.addListSelectionListener(e -> DrawModel.getInstance().setShape((String) list.getModel().getElementAt(list.getSelectedIndex())));
        return list;
    }

    public JButton getColorChooser(JPanel jPanel) {

        JButton drawbutton = new JButton();
        drawbutton.setText("Choose your color");
        drawbutton.addActionListener(e -> {
                    DrawModel.getInstance().setColor(JColorChooser.showDialog(null, "Choose your color", Color.WHITE));
                    jPanel.setBackground(DrawModel.getInstance().getColor());
                }
        );
        drawbutton.setPreferredSize(new Dimension(190, 25));

        return drawbutton;
    }

    public JSlider getOpacityChooser(JPanel j) {
        JSlider opacity = new JSlider(JSlider.HORIZONTAL, 1, 250, 125);
        JLabel jLabel = new JLabel("Opacity :");
        jLabel.setIcon(new ImageIcon("asset/opacity.png"));
        j.add(jLabel);

        opacity.setMajorTickSpacing(249);
        opacity.setMinorTickSpacing(25);
        opacity.setPaintTicks(true);
        opacity.setPaintLabels(true);
        opacity.addChangeListener(e -> {
            DrawModel.getInstance().setOpacity(opacity.getValue());
        });
        return opacity;
    }

    public void reset() {
        DrawModel.getInstance().reset();
        drawPanel.setVisible(false);
        erasePanel.setVisible(false);
        textPanel.setVisible(false);
    }

}
