package Assignment3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderGenerator extends JFrame{

    private  JTextField[] fields = {new JTextField("Enter price here. (bids in negative)", 34), new JTextField("Enter volume here.", 34), new JTextField("Enter name here.", 34)};
    private JButton submit = new JButton("SUBMIT");
    private JButton reset = new JButton("RESET");
    private JLabel last = new JLabel("Last order : none");

    public OrderGenerator(final OrderBook book) {
        //creating the frame and setting some of its attributes
        super("Order Generator");
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 196);
        this.setResizable(false);
        //centering the frame
        setLocationRelativeTo(null);
        //adding text fields
        add(fields[0]);
        add(fields[1]);
        add(fields[2]);
        //setting the fields to the right size
        for(JTextField field : fields) {
            field.setPreferredSize(new Dimension(380, 26));
        }
        //adding the submit button and setting its size
        add(submit);
        submit.setPreferredSize(new Dimension(189, 26));
        //creating an action listener for the submit button
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //try to read the text inputs, but tell the user if this fails
                try {
                    for(JTextField field : fields) {
                        if (field.getText() == "") {
                            throw new NumberFormatException();
                        }
                    }
                    //create a bid or offer depending on the sign of the integer
                    if(Double.parseDouble(fields[0].getText()) < 0) {
                        book.matchingEngine(new BidOrder(fields[2].getText(), Math.abs(Double.parseDouble(fields[0].getText())), Integer.parseInt(fields[1].getText())));
                        last.setText("Bid : $" + fields[0].getText().substring(1) + " x" + fields[1].getText());
                    } else {
                        book.matchingEngine(new OfferOrder(fields[2].getText(), Double.parseDouble(fields[0].getText()), Integer.parseInt(fields[1].getText())));
                        last.setText("Offer : $" + fields[0].getText() + " x" + fields[1].getText());
                    }
                    reset();
                } catch (NumberFormatException ex) {
                    last.setText("error in number format, or field empty");
                }
            }
        });
        //adding the reset button and setting its size
        add(reset);
        reset.setPreferredSize(new Dimension(189, 26));
        //creating and action listener for the reset button
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        //increasing the font size of the last label and adding it to the frame
        this.last.setFont(new Font(this.last.getFont().getName(), Font.PLAIN, 22));
        add(this.last);
        //making the frame visible
        this.setVisible(true);
    }

    /**
     * resets all the text fields to empty
     */
    private void reset() {
        fields[0].setText("Enter price here. (bids in negative)");
        fields[1].setText("Enter volume here.");
        fields[2].setText("Enter name here.");
    }
}
