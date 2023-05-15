package it.polimi.ingsw.Messages;

import it.polimi.ingsw.CLICommands.CLICommandList;
import it.polimi.ingsw.Messages.ClientToServer.*;
import it.polimi.ingsw.Messages.ClientToServer.PossibleMoves.Move_SelectColumn;
import it.polimi.ingsw.Messages.ClientToServer.PossibleMoves.Move_SelectOrder;
import it.polimi.ingsw.Messages.ClientToServer.PossibleMoves.Move_SelectTiles;
import it.polimi.ingsw.Messages.ServerToClient.ErrorMessage;
import it.polimi.ingsw.Messages.ServerToClient.GameIsStartingMessage;
import it.polimi.ingsw.Printer;

import java.util.ArrayList;

public class MoveSerializer implements Printer {



    public static Message serializeInput(String s){

        return convertCommandToMove(s);
    }

    private static Message convertCommandToMove(String string) {
        String[] tokens = string.toUpperCase().split(" ");
        return convertCommandToMove(tokens);
    }


    private static Message convertCommandToMove(String[] Command) {
      switch (checkCommand(Command[0])){
          case CREATE_LOBBY -> {
              if((Integer.parseInt(Command[1])<5)||(Integer.parseInt(Command[1])>1)){
                  return new LobbyCreationMessage(null,Integer.parseInt(Command[1]));
              }
              else
                  return invalidCommand();
          }
          case EXIT_LOBBY ->{
              return new LobbyLogoutMessage();
          }
          case ENTER_LOBBY -> {
              return new LobbyEntranceMessage();
          }
          case START_GAME -> {
              Message m=new StartGameMessage("Player");
              return m;
          }
          case SELECT_TILES -> {
              ArrayList<Integer> coordinates=new ArrayList<>();
              for(int i=1;i<Command.length;i++){
                  coordinates.add(Integer.parseInt(Command[i]));
              }
              if(coordinates.size()%2==1){
                  return invalidCommand();
              }
              for(Integer i: coordinates){
                  if(i<0||i>8){
                      return invalidCommand();
                  }
              }
              Move_SelectTiles move=new Move_SelectTiles();
              move.setCoordinates(coordinates);
              Message m=new CoordinatesMessage(move);
              return m;
          }
          case SELECT_COLUMN -> {
              if(Integer.parseInt(Command[1])>4||Integer.parseInt(Command[1])<0){
                  return invalidCommand();

              }
              Move_SelectColumn move =new Move_SelectColumn();
              move.setYBookshelf(Integer.parseInt(Command[1]));
              Message m=new ColumnMessage(move);
              return m;
          }
          case SELECT_ORDER -> {
              ArrayList<Integer> order=new ArrayList<>();
              for(int i=1;i<Command.length;i++){
                  order.add(Integer.parseInt(Command[i]));
              }
              for(Integer i: order){
                  if(i<1||i>3){
                      return invalidCommand();
                  }
              }
              Move_SelectOrder move=new Move_SelectOrder();
              move.setOrder(order);
              Message m=new OrderMessage(move);
              return m;
          }
          case CLOSE -> {
              return new CloseMessage();
          }
          case INVALID_COMMAND -> {
             return invalidCommand();
          }
      }
      return null;
    }

    private static Message invalidCommand() {
       ErrorMessage e=new ErrorMessage();
       e.setReturnMessage("The command isn't valid" +
                          "Command List: "+ CLICommandList.getCommands());
       return e;
    }

    private void commandList() {
        System.out.println(CLICommandList.getCommands());
    }


    private static MoveCategory checkCommand(String s){
        switch(s){
            case "/START" -> {
                return MoveCategory.START_GAME;
            }
            case "/CREATE" -> {
                return MoveCategory.CREATE_LOBBY;
            }
            case "/ENTER"-> {
                return MoveCategory.ENTER_LOBBY;
            }
            case "EXIT"->{
                return MoveCategory.EXIT_LOBBY;
            }
            case "/SELECT"-> {
                return MoveCategory.SELECT_TILES;
            }
            case "/COLUMN"-> {
                return MoveCategory.SELECT_COLUMN;
            }
            case "/ORDER"->{
                return MoveCategory.SELECT_ORDER;
            }
            case "/CLOSE"->{
                return MoveCategory.CLOSE;
            }
            default -> {
                return MoveCategory.INVALID_COMMAND;
            }

        }
    }

    public enum MoveCategory{
        CREATE_LOBBY,
        CLOSE,
        ENTER_LOBBY,
        EXIT_LOBBY,
        START_GAME,
        SELECT_TILES,
        SELECT_COLUMN,
        SELECT_ORDER,
        INVALID_COMMAND,


    }

    @Override
    public void showMessage(String s) {
        System.out.println(s);
    }
}
