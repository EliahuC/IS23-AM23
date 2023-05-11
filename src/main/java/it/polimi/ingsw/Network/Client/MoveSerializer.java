package it.polimi.ingsw.Network.Client;
import java.util.ArrayList;

import it.polimi.ingsw.CLICommands.CLICommandList;
import it.polimi.ingsw.Messages.ClientToServer.*;
import it.polimi.ingsw.Messages.ClientToServer.PossibleMoves.Move_SelectColumn;
import it.polimi.ingsw.Messages.ClientToServer.PossibleMoves.Move_SelectOrder;
import it.polimi.ingsw.Messages.ClientToServer.PossibleMoves.Move_SelectTiles;
import it.polimi.ingsw.Messages.Message;
import it.polimi.ingsw.Messages.ClientToServer.PossibleMoves.Move;
import it.polimi.ingsw.Messages.ServerToClient.StartingGameMessage;
import it.polimi.ingsw.Printer;

public class MoveSerializer implements Printer {

    private Move lastMove;

    public MoveSerializer(){

    }
    public Message serializeInput(String s){

        return convertCommandToMove(s);
    }

    private Message convertCommandToMove(String string) {
        String[] tokens = string.toUpperCase().split(" ");
        return convertCommandToMove(tokens);
    }


    private Message convertCommandToMove(String Command[]) {
      switch (checkCommand(Command[0])){
          case CREATE_LOBBY -> {
              if((Integer.parseInt(Command[2])<5)||(Integer.parseInt(Command[2])>1)){
                  Message m=new LobbyCreationMessage(Command[1],Integer.parseInt(Command[2]));
                  return m;
              }
              else invalidCommand();
          }
          case EXIT_LOBBY ->{
              Message m =new LobbyLogoutMessage();
              return m;
          }
          case ENTER_LOBBY -> {
              Message m=new LobbyEntranceMessage(Command[1]);
              return m;
          }
          case START_GAME -> {
              Message m=new StartingGameMessage();
              return m;
          }
          case SELECT_TILES -> {
              ArrayList<Integer> coordinates=new ArrayList<>();
              for(int i=1;i<Command.length;i++){
                  coordinates.add(Integer.parseInt(Command[i]));
              }
              if(coordinates.size()%2==1){
                  invalidCommand();
                  return null;
              }
              for(Integer i: coordinates){
                  if(i<0||i>8){
                      invalidCommand();
                      return null;
                  }
              }
              Move_SelectTiles move=new Move_SelectTiles();
              move.setCoordinates(coordinates);
              Message m=new CoordinatesMessage(move);
              return m;
          }
          case SELECT_COLUMN -> {
              if(Integer.parseInt(Command[1])>4||Integer.parseInt(Command[1])<0){
                  invalidCommand();
                  return null;
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
                      invalidCommand();
                      return null;
                  }
              }
              Move_SelectOrder move=new Move_SelectOrder();
              move.setOrder(order);
              Message m=new OrderMessage(move);
              return m;
          }
          case INVALID_COMMAND -> {
             invalidCommand();
          }
      }
      return null;
    }

    private void invalidCommand() {
        showMessage("The command is invalid, please insert a valid command");
        commandList();
    }

    private void commandList() {
        System.out.println(CLICommandList.getCommands());
    }


    private MoveCategory checkCommand(String s){
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
            default -> {
                return MoveCategory.INVALID_COMMAND;
            }

        }
    }

    public enum MoveCategory{
        CREATE_LOBBY,
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
