package it.polimi.ingsw.Messages;

import it.polimi.ingsw.CLICommands.CLICommandList;
import it.polimi.ingsw.Messages.ClientToServer.*;
import it.polimi.ingsw.Messages.ClientToServer.PossibleMoves.Move_SelectColumn;
import it.polimi.ingsw.Messages.ClientToServer.PossibleMoves.Move_SelectOrder;
import it.polimi.ingsw.Messages.ClientToServer.PossibleMoves.Move_SelectTiles;


import it.polimi.ingsw.Printer;

import java.util.ArrayList;

/**
 * Class to serialize an input string from the client
 * @author Eliahu Cohen
 *
 */
public class MoveSerializer implements Printer {


    /**
     * @author Eliahu Cohen
     * @param s input/command from the client
     * @return the message that have to send due to the command received
     */
    public static Message serializeInput(String s){

        return convertCommandToMove(s);
    }

    /**
     * Method that splits the input in various strings
     * @author Eliahu Cohen
     * @param string input/command from the client
     * @return the message that have to send due to the command received
     *
     */
    private static Message convertCommandToMove(String string) {
        String[] tokens = string.split(" ");
        return convertCommandToMove(tokens);
    }

    /**
     * Method that generate the message based on the input
     * @author Eliahu Cohen
     * @param Command input split in parts
     * @return the message that have to send due to the command received
     *
     */
    private static Message convertCommandToMove(String[] Command) {
        try{
      switch (checkCommand(Command[0].toUpperCase())){
          case CREATE_LOBBY -> {
              if(Command.length<2){
                  invalidCommand();
                  break;
              }
              if((Integer.parseInt(Command[1])<5)||(Integer.parseInt(Command[1])>1)){
                  return new LobbyCreationMessage(null,Integer.parseInt(Command[1]));
              }
              else
                  return invalidCommand();
          }
          case ENTER_LOBBY -> {
              return new LobbyEntranceMessage();
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
              ClientMessage m=new CoordinatesMessage(move);
              return m;
          }
          case SELECT_COLUMN -> {
              if(Command.length>1) {
                  if (Integer.parseInt(Command[1]) > 4 || Integer.parseInt(Command[1]) < 0)
                      return invalidCommand();
              }else
                  return invalidCommand();
              Move_SelectColumn move =new Move_SelectColumn();
              move.setYBookshelf(Integer.parseInt(Command[1]));
              ClientMessage m=new ColumnMessage(move);
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
              for(int i= 1; i<Command.length; i++){
                  if(Integer.parseInt(Command[i]) >= Command.length)
                      return invalidCommand();
              }
              Move_SelectOrder move=new Move_SelectOrder();
              move.setOrder(order);
              ClientMessage m=new OrderMessage(move);
              return m;
          }
          case NICKNAME -> {
              return new NickNameMessage(Command[1]);
          }
          case INVALID_COMMAND -> {
             return invalidCommand();
          }
      }}catch (NumberFormatException e){
            return invalidCommand();
        }
      return null;
    }

    /**
     * @author Eliahu Cohen
     * @return an error message and print the CommandList
     */
    private static Message invalidCommand() {
       ClientErrorMessage e=new ClientErrorMessage();
       e.setReturnMessage("The command isn't valid" +
                          "Command List: "+ CLICommandList.getCommands());
       return e;
    }


    /**
     * @author Eliahu Cohen
     * @param s first part of the command that implicates what message the class have to generate
     * @return the MoveCategory associated to the command
     */
    private static MoveCategory checkCommand(String s){
        switch(s){
            case "/CREATE" -> {
                return MoveCategory.CREATE_LOBBY;
            }
            case "/ENTER"-> {
                return MoveCategory.ENTER_LOBBY;
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
            case"/NICKNAME"->{
                return MoveCategory.NICKNAME;
            }
            default -> {
                return MoveCategory.INVALID_COMMAND;
            }

        }
    }

    public enum MoveCategory{
        CREATE_LOBBY,
        ENTER_LOBBY,
        SELECT_TILES,
        NICKNAME,
        SELECT_COLUMN,
        SELECT_ORDER,
        INVALID_COMMAND,


    }

    @Override
    public void showMessage(String s) {
        System.out.println(s);
    }
}
