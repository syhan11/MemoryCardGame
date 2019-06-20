import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class MemoryCardGame {
    public class Card {
        String name;
        int value;
        ImageIcon icon;

        public Card(String name, int value, ImageIcon icon) {
            this.name = name;
            this.value = value;
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public ImageIcon getIcon() {
            return icon;
        }

        public void setIcon(ImageIcon icon) {
            this.icon = icon;
        }
    }


    public static void shuffleCards (String[] varCards, String[][] varBoard, int varMax) {
        ArrayList <String> tmpAllCards = new ArrayList<String>();
        ArrayList<String> tmpAll = new ArrayList<String>();
        String tmpCard;
        int szCard = varCards.length;

        // initialize allCards

        for (int i = 0; i < szCard; i++){
            tmpCard = varCards[i];
            tmpAll.add(tmpCard);
            tmpAll.add(tmpCard);
        }

       // shuffle cards
        Random randomN = new Random();
        int ranNo;

        while (true) {
            ranNo = randomN.nextInt(tmpAll.size());

            tmpCard = tmpAll.get(ranNo);
            tmpAllCards.add(tmpCard);
            tmpAll.remove(ranNo);

            if (tmpAll.size() == 0)
                break;
        }


        for (int i = 0; i < varMax; i++ ){
            for (int j = 0; j < varMax; j++) {
                varBoard[i][j] = tmpAllCards.get(0);
                tmpAllCards.remove(0);

            }
        }
    }

    public static void printBoard(String varBoard[][]) {
        // print out board status
        for (String[] str : varBoard)
            System.out.println(Arrays.toString(str));

    }

    public static String selectCard(Scanner valKB, String[][] valBoard,
                                     String[][]usrBoard, int[] position) {
        int rowVal, colVal;
        String cardVal;

        System.out.println("Enter a row number (0 - 3) for a card");
        rowVal = valKB.nextInt();

        System.out.println("Enter a column number (0 - 3) for a card");
        colVal = valKB.nextInt();

        cardVal = valBoard[rowVal][colVal];

        usrBoard[rowVal][colVal] = cardVal;
        position[0] = rowVal;
        position[1] = colVal;

        return(cardVal);
    }


    public static void main(String[] args){
        String[][] answerBoard = new String[4][4];
        String[][] userBoard = new String[4][4];
        Boolean[][] matchBoard = new Boolean[4][4];
        String [] cards = {"apple", "boy", "cat", "dog", "egg", "fish", "girl", "hat"};

        for (int i = 0 ; i < 4; i++)
            for (int j = 0; j < 4; j++)
                matchBoard[i][j] = false;

        for (int i = 0 ; i < 4; i++)
            for (int j = 0; j < 4; j++)
                userBoard[i][j] = "?";

        shuffleCards(cards, answerBoard, 4);

    printBoard(answerBoard);

        // print out user board status
        printBoard(userBoard);

        String cardVal1, cardVal2;
        int[] cardPos1 = {-1, -1};
        int[] cardPos2 = {-1, -1};
        int found = 0;

        Scanner keyboard = new Scanner(System.in);

        while (found < 8) {
            cardVal1 = selectCard(keyboard, answerBoard, userBoard, cardPos1);
            printBoard(userBoard);

            cardVal2 = selectCard(keyboard, answerBoard, userBoard, cardPos2);
            printBoard(userBoard);

            if (!cardVal1.equals(cardVal2)) {
                // cards do not match
                userBoard[cardPos1[0]][cardPos1[1]] = "?";
                userBoard[cardPos2[0]][cardPos2[1]] = "?";
                System.out.println("No match.  Try again.\n");
                printBoard(userBoard);
            } else
                found++;

    System.out.println("*** found " + found);
    printBoard(answerBoard);

        }

    }

}
