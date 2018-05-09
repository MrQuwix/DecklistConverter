import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// This will be the input window where our user will give us their decklist.

public class DeckInputWindow extends JOptionPane {

    //Method that will return the String our user inputs.
    public String showDecklistInput(final String newDecklist){

        String deckListEntry = null;

        class getDeckList extends JDialog implements ActionListener {
            JTextArea textArea = new JTextArea(30, 50);
            JButton submitButton = new JButton("  SUBMIT  ");
            JButton cancelButton = new JButton("  CANCEL  ");
            String newString = null;

            // Constructor for our window
            public getDeckList() {
                setModal(true);
                getContentPane().setLayout(new BorderLayout());
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                setLocation(400, 300);
                getContentPane().add(new JLabel(newDecklist), BorderLayout.NORTH);
                getContentPane().add(textArea, BorderLayout.CENTER);
                JPanel jp = new JPanel();
                submitButton.addActionListener(this);
                cancelButton.addActionListener(this);
                jp.add(submitButton);
                jp.add(cancelButton);
                getContentPane().add(jp, BorderLayout.SOUTH);
                pack();
                setVisible(true);
            }

            // Check which button our user presses.
            // Return an error message if they cancelled out of it.
            public void actionPerformed(ActionEvent newAction){
                if(newAction.getSource() == submitButton){
                    newString = textArea.getText();
                }
                if(newAction.getSource() == cancelButton){
                    newString = "\n NO DECKLIST ENTERED";
                }

                dispose();
            }

            public String returnDeckList(){
                return newString;
            }
        }

        deckListEntry = new getDeckList().returnDeckList();
        return deckListEntry;

    }

}
