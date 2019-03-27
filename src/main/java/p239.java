import java.util.ArrayList;
import java.util.Scanner;

public class p239 {
    int numPlayers;
    Scanner keyboard;
    Board board;

    public p239(){
        keyboard = new Scanner(System.in);
    }

    public void readPlayers(){
        numPlayers = keyboard.nextInt();
        keyboard.nextLine();
        board = new Board(numPlayers);
    }

    public void readShoots(){
        boolean showPlace = true;
        String proves[] = new String[numPlayers];
        String caso;
        ArrayList<Integer> tiros;

        for (int i = 0; i < numPlayers; i++)
            proves[i] = keyboard.nextLine();


        for (int player = 0; player < numPlayers; player++) {

            caso = proves[player];

            tiros = getShoots(caso);

            for (int numCasillas : tiros){

               if(numCasillas < 2 || numCasillas > 12){
                   System.out.println("INVALIDO");
                   showPlace = false;
                   break;
               }else
                   board.move(numCasillas, player+1);

            }

            if(showPlace)
                System.out.println(board.getCurrentBox(player+1));

            showPlace = true;

        }

    }

    private ArrayList<Integer> getShoots(String secuence){
        ArrayList<Integer> shoots = new ArrayList<Integer>();
        String shoot = "";
        int num;
        for (int i = 0; i < secuence.length(); i++) {

            if(secuence.charAt(i) != ' ') {
                shoot += String.valueOf(secuence.charAt(i));
            }
            else {
                num = Integer.valueOf(shoot);
                shoots.add(num);
                shoot = "";
            }
        }

        num = Integer.valueOf(shoot);
        shoots.add(num);

        return shoots;
    }

    public static void main(String[] args) {

        p239 problem = new p239();

        problem.readPlayers();
        problem.readShoots();


    }
}
