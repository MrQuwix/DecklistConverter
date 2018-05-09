import javax.swing.*;
import java.awt.*;

public class IntroductionWindow extends JFrame{

    public String deckList = "Magic: the Gathering Decklist Converter\n Submit a decklist to receive HTML code for your website.";

    public IntroductionWindow(){
        //Using TextArea for this window so that anything in it is Copy/Pastable.
        TextArea textArea = new TextArea(deckList);
        textArea.setEditable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocation(400, 400);
        JPanel main = new JPanel(new BorderLayout());
        main.add(textArea, BorderLayout.CENTER);

        JButton mainButton = new JButton("Enter New Deck");
        mainButton.addActionListener(e -> {
            deckList = new DeckInputWindow().showDecklistInput("Please Enter Your Decklist:");

            //Insert Danny's Call method using deckList as the parameter to receive the updated
            deckList = convertString(deckList);
            //String containing HTML Code and overwrite deckList as that String.
            textArea.setText("HTML CODE:\n" + deckList);
        });
        main.add(mainButton, BorderLayout.SOUTH);
        getContentPane().add(main);
    }

    public String convertString(String decklist){

        JavaHttpUrlConnectionReader myReader = new JavaHttpUrlConnectionReader();

        String[] decklistArray = decklist.split("\\n");
        Decklist newDeck = new Decklist(decklistArray);
        String[] urlReadyList = newDeck.formatURL();
        String[] multiverseIds = new String[decklist.length()];
        for(int i=0; i<decklistArray.length; i++){
            try{
                multiverseIds[i] = myReader.getMultiverseIdFromName(urlReadyList[i]);
            } catch (Exception e) {
                // deal with the exception in your "controller"
            }
        }
        String[] hyperlinks = newDeck.makeMultiverseURL(multiverseIds);
        newDeck.addCounts(hyperlinks);
        String finalList ="";
        for(int i=0; i<hyperlinks.length; i++){
            finalList += hyperlinks[i].toString() + "\n";
        }

        return finalList;

    }
}
