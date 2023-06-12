package player.ai;

import game.BoardHelper;

import static player.ai.StaticEvaluator.*;

public class Corner implements Evaluator {

    //Evaluation Function Changes during Early-Game / Mid-Game / Late-Game
    enum GamePhase {
        EARLY_GAME,
        MID_GAME,
        LATE_GAME
    }

    private GamePhase getGamePhase(int[][] board){
        int sc = BoardHelper.getTotalStoneCount(board);
        if(sc<20) return GamePhase.EARLY_GAME;
        else if(sc<=58) return GamePhase.MID_GAME;
        else return GamePhase.LATE_GAME;
    }

    public int eval(int[][] board , int player){

        //terminal
        if(BoardHelper.isGameFinished(board)){
            return 1000*evalDiscDiff(board, player);
        }

        //semi-terminal
        switch (getGamePhase(board)){
            case EARLY_GAME:
                return 1000*evalCorner(board,player);
            case MID_GAME:
                return 1000*evalCorner(board,player);
            case LATE_GAME:
            default:
                return 1000*evalCorner(board,player);
        }
    }

}
