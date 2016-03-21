package Assignment3;

import javax.swing.*;
import java.awt.*;

public class OrderGenerator extends JFrame{

    private JTextField price = new JTextField("Enter price here.", 34);
    private JTextField volume = new JTextField("Enter volume here.", 34);
    private JTextField name = new JTextField("Enter name here.", 34);
    private JButton submit = new JButton("SUBMIT");
    private JButton reset = new JButton("RESET");
    private JLabel last = new JLabel("Last order : none");

    public OrderGenerator(String last) {
        super("Order Generator");
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);
        this.setResizable(false);
        setLocationRelativeTo(null);
        add(price);
        add(volume);
        add(name);
        add(submit);
        submit.setPreferredSize(new Dimension(189, 26));
        add(reset);
        reset.setPreferredSize(new Dimension(189, 26));
        this.last.setText("Last order : " + last);
        this.last.setFont(new Font(this.last.getFont().getName(), Font.PLAIN, 22));
        add(this.last);
        this.setVisible(true);
    }

}
