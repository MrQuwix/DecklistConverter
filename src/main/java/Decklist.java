import javax.swing.*;

public class Decklist {
    String[] decklist;
    String[] counts;

    public Decklist(String[] decklist){
        separateCount(decklist);
    }

    public String[] formatURL(){
        String[] noSpace = new String[decklist.length];
        for(int i=0; i<decklist.length; i++){
            noSpace[i] = "https://api.magicthegathering.io/v1/cards?name=\""+decklist[i].replaceAll("\\s","%20")+"\"";
        }
        return noSpace;
    }

    public String[] makeMultiverseURL(String[] multiverseIds){
        String[] hyperlinks = new String[decklist.length];
        for(int i=0; i<decklist.length; i++){
            hyperlinks[i] = "<a href=\"http://gatherer.wizards.com/Pages/Card/Details.aspx?multiverseid=" + multiverseIds[i] + "\">" + decklist[i] + "</a>";
        }
        return hyperlinks;
    }

    private void separateCount(String[] fullList){
        counts = new String[fullList.length];
        decklist = new String[fullList.length];

        for(int i=0; i<fullList.length; i++){
            counts[i] = ""+fullList[i].charAt(0);
            decklist[i] = fullList[i].substring(2,fullList[i].length());
        }
    }

    public String[] addCounts(String[] decklist){
        String[] withCounts = new String[decklist.length];
        for(int i=0; i<decklist.length; i++){
            withCounts[i] = counts[i] + decklist[i];
        }
        return withCounts;
    }

}