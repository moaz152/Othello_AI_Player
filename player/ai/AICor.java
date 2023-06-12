package player.ai;

import game.GamePlayer;

import java.awt.*;

public class AICor extends GamePlayer {

    private int searchDepth;
    private Evaluator evaluator;

    public AICor(int mark, int depth) {
        super(mark);
        searchDepth = depth;
        evaluator = new Corner();
    }

    @Override
    public boolean isUserPlayer() {
        return false;
    }

    @Override
    public String playerName() {
        return "Corner AI (Depth " + searchDepth + ")";
    }

    @Override
    public Point play(int[][] board) {
        return Minimax.solve(board,myMark,searchDepth,evaluator);
    }
}
