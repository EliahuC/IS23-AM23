package view;
import java.util.Scanner;
import server.Move;

public class MoveSerializer {
    private  String myMove=null;
    private Move lastMove;
    public void getFromKeyboard(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("It's your turn, do your move" );
        myMove = keyboard.nextLine();
        convertToMove(myMove);
    }

    private void convertToMove(String myMove) {

    }


}
