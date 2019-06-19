import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MemoryCardGame {

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

    public static void printBoard(String varBoard[][], int varMax) {

        for (int i = 0; i < varMax; i++ ) {
            for (int j = 0; j < varMax; j++)
                System.out.printf("*******************");
            System.out.println("");

            for (int j = 0; j < varMax; j++)
                System.out.printf("**%15s**", " ");
            System.out.println("");

            for (int j = 0; j < varMax; j++) {
                System.out.printf("**[%d][%d]:%7s **", i, j, varBoard[i][j]);
            }
            System.out.println("");

            for (int j = 0; j < varMax; j++)
                System.out.printf("**%15s**", " ");
            System.out.println("");

            for (int j = 0; j < varMax; j++)
                System.out.printf("*******************");
            System.out.println("");

        }
    }

    public static String selectCard(Scanner valKB, String[][] valBoard) {
        int rowVal, colVal;
        String cardVal;

        System.out.println("Enter row number (0 - 3) for a card");
        rowVal = valKB.nextInt();

        System.out.println("Enter column number (0 - 3) for a card");
        colVal = valKB.nextInt();

        System.out.println("selectCard: rowVal="+rowVal);
        System.out.println("selectCard: colVal="+colVal);

        cardVal = valBoard[rowVal][colVal];
        System.out.println("selectCard: cardVal="+cardVal);

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


        // print out user board status
        for (int i = 0; i < 4; i++)
            System.out.println(Arrays.toString(matchBoard[i]));

        shuffleCards(cards, answerBoard, 4);
        //printBoard(answerBoard, 4);

        // print out user board status
        for (int i = 0; i < 4; i++)
            System.out.println(Arrays.toString(answerBoard[i]));

        String cardVal1, cardVal2;
        int rowVal, colVal;
        Scanner keyboard = new Scanner(System.in);
        cardVal1 = selectCard(keyboard, answerBoard);

        System.out.println("main: cardVal1="+cardVal1);

        cardVal2 = selectCard(keyboard, answerBoard);

        System.out.println("carVal1=" + cardVal1);
        System.out.println("carVal2=" + cardVal2);
    }

}
