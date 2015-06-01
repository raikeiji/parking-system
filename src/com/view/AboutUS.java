package com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class AboutUS extends JFrame {

    public AboutUS() {
        initUI();
    }

    private void initUI() {
        JPanel basic = new JPanel();
        basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
        add(basic);

        setDesign();

        JPanel topPanel = new JPanel(new BorderLayout(0, 0));
        topPanel.setMaximumSize(new Dimension(450, 0));
        JLabel hint = new JLabel("Tentang Pengembang");
        hint.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        topPanel.add(hint);

        ImageIcon icon = new ImageIcon("image/about.png");
        JLabel label = new JLabel(icon);
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        topPanel.add(label, BorderLayout.EAST);

        JSeparator separator = new JSeparator();
        separator.setForeground(Color.gray);

        topPanel.add(separator, BorderLayout.SOUTH);

        basic.add(topPanel);

        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 5, 25));
        JTextPane pane = new JTextPane();

        pane.setContentType("text/html");
        String text
                = "<p>Sistem Parkir USD Versi 4.0.0 dibuat untuk memenuhi nilai Proyek Teknologi Informasi. "
                + "Berikut daftar pengembang :</p>"
                + "<p>1. Dionisius Wisnu</b></p>"
                + "<p>2. Engelbertus Vione</b></p>"
                + "<p>3. Octaviani</b></p>"
                + "<p>4. Nicolaus E.W.N.</b></p>";

        pane.setText(text);
        pane.setEditable(false);
        textPanel.add(pane);

        basic.add(textPanel);

        JPanel boxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));

//        JCheckBox box = new JCheckBox("Show Tips at startup");
//        box.setMnemonic(KeyEvent.VK_S);
//        boxPanel.add(box);
        basic.add(boxPanel);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton close = new JButton("Close");
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                //System.exit(0);
            }
        });
        close.setMnemonic(KeyEvent.VK_C);

        bottom.add(close);
        basic.add(bottom);

        bottom.setMaximumSize(new Dimension(450, 0));

        setTitle("Tentang pengembang");
        setSize(new Dimension(450, 350));
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public final void setDesign() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AboutUS a = new AboutUS();
                a.setVisible(true);
            }
        });
    }
}
