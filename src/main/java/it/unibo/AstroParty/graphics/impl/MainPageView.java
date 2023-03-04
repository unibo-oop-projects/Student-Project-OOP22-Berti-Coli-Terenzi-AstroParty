package it.unibo.AstroParty.graphics.impl;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

public class MainPageView extends JFrame {

    private static final double DIMENSION_PERC = 0.5;
    
    public MainPageView() {
        super("AstroParty");
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int) (screenSize.getWidth() * DIMENSION_PERC), (int) (screenSize.getHeight() * DIMENSION_PERC));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        final JPanel canvas = new JPanel(new BorderLayout());
        final JButton start = new JButton("START THE GAME");
        canvas.add(start, BorderLayout.CENTER);

        this.setContentPane(canvas);
        this.setLocationByPlatform(true);
        this.setVisible(true);

        start.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                //TODO
            }
            
        });
    }
}
