package it.polimi.ingsw.view;
import java.util.Scanner;

import it.polimi.ingsw.Message;
import it.polimi.ingsw.PossibleMoves.Move;

public class MoveSerializer implements Printer{
    private  String myMove=null;
    private Move lastMove;
    public void getFromKeyboard(){
        Scanner keyboard = new Scanner(System.in);
        showMessage("It's your turn, do your move");
        myMove = keyboard.nextLine();
        myMove=myMove.toUpperCase();
        String tokens[]=myMove.split(" ");
        convertCommandToMove(tokens);
    }

    private void convertCommandToMove(String Command[]) {
      switch (checkCommand(Command[0])){
          case CREATE_LOBBY -> {
              Message m=new Message(null,Command[1]);
              m.setCategory(Message.MessageCategory.CREATE_LOBBY);
          }
          case INVALID_COMMAND -> {
              showMessage("The command is invalid, please insert a valid command");
              commandList();
          }
      }
    }

    private void commandList() {
        showMessage("/create_lobby nickname");
        showMessage("/enter_lobby nickname");
        showMessage("/start_game");
        showMessage("/select_tiles x1 y1 x2 y2 x3 y3");
        showMessage("/select_column y");
        showMessage("");

    }


    private MoveCategory checkCommand(String s){
        switch(s){
            case "/START_GAME" -> {
                return MoveCategory.START_GAME;
            }
            case "/CREATE_LOBBY" -> {
                return MoveCategory.CREATE_LOBBY;
            }
            case "/ENTER_LOBBY"-> {
                return MoveCategory.ENTER_LOBBY;
            }
            case "/SELECT_TILES"-> {
                return MoveCategory.SELECT_TILES;
            }
            case "/SELECT_COLUMN"-> {
                return MoveCategory.SELECT_COLUMN;
            }
            default -> {
                return MoveCategory.INVALID_COMMAND;
            }

        }
    }

    public enum MoveCategory{
        CREATE_LOBBY,
        ENTER_LOBBY,
        START_GAME,
        SELECT_TILES,
        SELECT_COLUMN,
        INVALID_COMMAND,


    }

    @Override
    public void showMessage(String s) {
        System.out.println(s);
    }
}
