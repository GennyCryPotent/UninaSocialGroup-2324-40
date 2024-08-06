package UninaSocialGroup;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Panel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.geom.Ellipse2D;

public class Home extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private String NU;

    /**
     * Launch the application.
     */
    /**
     * Create the frame.
     */

    public Home(String NU) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 737, 484);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Bentornato");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel.setBounds(210, 10, 106, 38);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel(NU);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_1.setBounds(326, 10, 260, 38);
        contentPane.add(lblNewLabel_1);

        TextField textField = new TextField();
        textField.setText("Ricerca");
        textField.setBounds(210, 54, 224, 21);
        contentPane.add(textField);

        Panel panel = new Panel();
        panel.setBackground(new Color(192, 192, 192));
        panel.setBounds(10, 10, 74, 427);
        contentPane.add(panel);

        CircularButton button = new CircularButton("Notifiche");
        button.setBounds(150, 100, 100, 100);
        contentPane.add(button);
    }

    class CircularButton extends JButton {
        private static final long serialVersionUID = 1L;

        public CircularButton(String label) {
            super(label);
            setPreferredSize(new Dimension(100, 100));
            setContentAreaFilled(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (getModel().isArmed()) {
                g.setColor(Color.LIGHT_GRAY);
            } else {
                g.setColor(Color.BLUE);
            }
            g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {
            g.setColor(Color.BLACK);
            g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
        }

        @Override
        public boolean contains(int x, int y) {
            Ellipse2D shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
            
        return shape.contains(x, y);
       }
   }
}