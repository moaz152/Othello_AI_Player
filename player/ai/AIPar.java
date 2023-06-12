package player.ai;

import game.GamePlayer;

import java.awt.*;

public class AIPar extends GamePlayer {

    private int searchDepth;
    private Evaluator evaluator;

    public AIPar(int mark, int depth) {
        super(mark);
        searchDepth = depth;
        evaluator = new mob();
    }

    @Override
    public boolean isUserPlayer() {
        return false;
    }

    @Override
    public String playerName() {
        return "Parity AI (Depth " + searchDepth + ")";
    }

    @Override
    public Point play(int[][] board) {
        return Minimax.solve(board,myMark,searchDepth,evaluator);
    }
}
