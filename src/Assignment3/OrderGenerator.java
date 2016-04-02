package Assignment3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderGenerator extends JFrame{

    private  JTextField[] fields = {new JTextField("Enter price here.", 34), new JTextField("Enter volume here.", 34), new JTextField("Enter name here.", 34)};
    private JButton submit = new JButton("SUBMIT");
    private JButton reset = new JButton("RESET");
    private JLabel last = new JLabel("Last order : none");

    public OrderGenerator(final OrderBook book) {
        super("Order Generator");
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 196);
        this.setResizable(false);
        setLocationRelativeTo(null);
        add(fields[0]);
        add(fields[1]);
        add(fields[2]);
        for(JTextField field : fields) {
            field.setPreferredSize(new Dimension(380, 26));
        }
        add(submit);
        submit.setPreferredSize(new Dimension(189, 26));
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Integer.parseInt(fields[0].getText()) < 0) {
                    book.add(new BidOrder(Long.parseLong(fields[2].getText()), Math.abs(Double.parseDouble(fields[0].getText())), Integer.parseInt(fields[1].getText())));
                } else {
                    book.add(new BidOrder(Long.parseLong(fields[2].getText()), Double.parseDouble(fields[0].getText()), Integer.parseInt(fields[1].getText())));
                }
                //book.matchingEngine();
                book.outputBook();
                last.setText("fix");
                reset();
            }
        });
        add(reset);
        reset.setPreferredSize(new Dimension(189, 26));
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        this.last.setText("Last order : " + "fix");
        this.last.setFont(new Font(this.last.getFont().getName(), Font.PLAIN, 22));
        add(this.last);
        this.setVisible(true);
    }

    private void reset() {
        for(JTextField field : fields) {
            field.setText("");
        }
    }
}
