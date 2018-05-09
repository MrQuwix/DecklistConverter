import javax.swing.JOptionPane;
// THIS IS JUSTIN'S COMMENT!
public class Main {
    public static void main(String[] args) {
        JavaHttpUrlConnectionReader myReader = new JavaHttpUrlConnectionReader();
        String rawList = "2 Aether Hub\n" +
                "3 Buried Ruin\n" +
                "4 Darksteel Citadel\n" +
                "2 Forest\n" +
                "4 Grove of the Burnwillows\n" +
                "3 Inventors' Fair\n" +
                "2 Myr Retriever\n" +
                "4 Scrap Trawler\n" +
                "4 Ancient Stirrings\n" +
                "3 Chromatic Sphere\n" +
                "4 Chromatic Star\n" +
                "3 Engineered Explosives\n" +
                "4 Ichor Wellspring\n" +
                "4 Krark-Clan Ironworks\n" +
                "4 Mind Stone\n" +
                "4 Mox Opal\n" +
                "2 Pyrite Spellbomb\n" +
                "4 Terrarion\n" +
                "2 Defense Grid\n" +
                "3 Galvanic Blast\n" +
                "2 Ghirapur Aether Grid\n" +
                "2 Guttural Response\n" +
                "4 Nature's Claim\n" +
                "2 Wurmcoil Engine";

        String[] decklist = rawList.split("\\n");
        Decklist newDeck = new Decklist(decklist);
        String[] urlReadyList = newDeck.formatURL();
        String[] multiverseIds = new String[rawList.length()];
        for(int i=0; i<decklist.length; i++){
            try{
                multiverseIds[i] = myReader.getMultiverseIdFromName(urlReadyList[i]);
            } catch (Exception e) {
                // deal with the exception in your "controller"
            }
        }
        String[] hyperlinks = newDeck.makeMultiverseURL(multiverseIds);
        hyperlinks = newDeck.addCounts(hyperlinks);
        String finalList ="";
        for(int i=0; i<hyperlinks.length; i++){
            finalList += hyperlinks[i].toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, finalList, "Decklist", JOptionPane.INFORMATION_MESSAGE);







    }
}