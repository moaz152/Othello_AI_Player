package game;

import player.*;
import player.ai.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements GameEngine {
    private JButton startButton,restartButton;

    //reversi board
    int[][] board;

    //player turn
    //black plays first
    int turn = 1;

    //swing elements
    BoardCell[][] cells;
    JLabel score1;
    JLabel score2;

    int totalscore1 = 0;
    int totalscore2 = 0;

    JLabel tscore1;
    JLabel tscore2;

    GamePlayer player1 ;
    GamePlayer player2;


    Timer player1HandlerTimer;
    Timer player2HandlerTimer;

    @Override
    public int getBoardValue(int i,int j){
        return board[i][j];
    }

    @Override
    public void setBoardValue(int i,int j,int value){
        board[i][j] = value;
    }

    public GamePanel(String difficulty,String mode){
        if(mode =="Human Vs Human"){
                 player1 = new HumanPlayer(1);
                 player2 = new HumanPlayer(2);
        }
        else if(mode =="Ai Vs Human" && difficulty =="Hard"){
                 player1 = new HumanPlayer(1);
                 player2 = new AIHard(2,4);
        }
                else if(mode =="Ai Vs Human" && difficulty =="Easy"){
                 player1 = new HumanPlayer(1);
                 player2 = new EasyPlayer(2);
        }

        else{
                 player1 = new AIHard(1,4);
                 player2 = new AIHard(2,4);
        }
        restartButton = new JButton("Restart Game");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Restart the game
                restartGame();
                repaint();
            }
        });
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());

        JPanel reversiBoard = new JPanel();
        reversiBoard.setLayout(new GridLayout(8,8));
        reversiBoard.setPreferredSize(new Dimension(500,500));
        reversiBoard.setBackground(new Color(0,255, 204));

        //init board
        resetBoard();

        cells = new BoardCell[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cells[i][j] = new BoardCell(this,reversiBoard,i,j);
                reversiBoard.add(cells[i][j]);
            }
        }


        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar,BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(200,0));
        sidebar.add(restartButton);

        score1 = new JLabel("Score 1");
        score2 = new JLabel("Score 2");

        tscore1 = new JLabel("Total Score 1");
        tscore2 = new JLabel("Total Score 2");

        sidebar.add(score1);
        sidebar.add(score2);

        sidebar.add(new JLabel("-----------"));

        sidebar.add(tscore1);
        sidebar.add(tscore2);


        this.add(sidebar,BorderLayout.WEST);
        this.add(reversiBoard);

        //
        updateBoardInfo();
        updateTotalScore();

        //AI Handler Timer (to unfreeze gui)
        player1HandlerTimer = new Timer(1000,(ActionEvent e) -> {
            handleAI(player1);
            player1HandlerTimer.stop();
            manageTurn();
        });

        player2HandlerTimer = new Timer(1000,(ActionEvent e) -> {
            handleAI(player2);
            player2HandlerTimer.stop();
            manageTurn();
        });

        manageTurn();
    }

    private boolean awaitForClick = false;
    private void restartGame() {
        // Reset the board
        resetBoard();
        turn = 1;
        manageTurn();
    }

    public void manageTurn(){
        if(BoardHelper.hasAnyMoves(board,1) || BoardHelper.hasAnyMoves(board,2)) {
            updateBoardInfo();
            if (turn == 1) {
                if(BoardHelper.hasAnyMoves(board,1)) {
                    if (player1.isUserPlayer()) {
                        awaitForClick = true;
                        //after click this function should be call backed
                    } else {
                        player1HandlerTimer.start();
                    }
                }else{
                    //forfeit this move and pass the turn
                    System.out.println("Player 1 has no legal moves !");
                    turn = 2;
                    manageTurn();
                }
            } else {
                if(BoardHelper.hasAnyMoves(board,2)) {
                    if (player2.isUserPlayer()) {
                        awaitForClick = true;
                        //after click this function should be call backed
                    } else {
                        player2HandlerTimer.start();
                    }
                }else{
                    //forfeit this move and pass the turn
                    System.out.println("Player 2 has no legal moves !");
                    turn = 1;
                    manageTurn();
                }
            }
        }else{
            //game finished
            System.out.println("Game Finished !");
            int winner = BoardHelper.getWinner(board);
            if(winner==1) totalscore1++;
            else if(winner==2) totalscore2++;
            updateTotalScore();
            //restart
            //resetBoard();
            //turn=1;
            //manageTurn();
        }
    }

    public void resetBoard(){
        board = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j]=0;
            }
        }
        //initial board state
        setBoardValue(3,3,2);
        setBoardValue(3,4,1);
        setBoardValue(4,3,1);
        setBoardValue(4,4,2);
        setBoardValue(0,0,0);setBoardValue(0,1,0);setBoardValue(0,2,0);setBoardValue(0,3,0);setBoardValue(0,4,0);setBoardValue(0,5,0);setBoardValue(0,6,0);setBoardValue(0,7,0);
        setBoardValue(1,0,0);setBoardValue(1,1,0);setBoardValue(1,2,0);setBoardValue(1,3,0);setBoardValue(1,4,0);setBoardValue(1,5,0);setBoardValue(1,6,0);setBoardValue(1,7,0);
        setBoardValue(2,0,0);setBoardValue(2,1,0);setBoardValue(2,2,0);setBoardValue(2,3,0);setBoardValue(2,4,0);setBoardValue(2,5,0);setBoardValue(2,6,0);setBoardValue(2,7,0);
        setBoardValue(3,0,0);setBoardValue(3,1,0);setBoardValue(3,2,0);setBoardValue(3,5,0);setBoardValue(3,6,0);setBoardValue(3,7,0);
        setBoardValue(4,0,0);setBoardValue(4,1,0);setBoardValue(4,2,0);setBoardValue(4,5,0);setBoardValue(4,6,0);setBoardValue(4,7,0);
        setBoardValue(5,0,0);setBoardValue(5,1,0);setBoardValue(5,2,0);setBoardValue(5,3,0);setBoardValue(5,4,0);setBoardValue(5,5,0);setBoardValue(5,6,0);setBoardValue(5,7,0);
        setBoardValue(6,0,0);setBoardValue(6,1,0);setBoardValue(6,2,0);setBoardValue(6,3,0);setBoardValue(6,4,0);setBoardValue(6,5,0);setBoardValue(6,6,0);setBoardValue(6,7,0);setBoardValue(7,0,0);setBoardValue(7,1,0);setBoardValue(7,2,0);setBoardValue(7,3,0);setBoardValue(7,4,0);setBoardValue(7,5,0);setBoardValue(7,6,0);setBoardValue(7,7,0);
    }
    //update highlights on possible moves and scores
    public void updateBoardInfo(){

        int p1score = 0;
        int p2score = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j] == 1) p1score++;
                if(board[i][j] == 2) p2score++;

                if(BoardHelper.canPlay(board,turn,i,j)){
                    cells[i][j].highlight = 1;
                }else{
                    cells[i][j].highlight = 0;
                }
            }
        }

        score1.setText(player1.playerName() + " : " + p1score);
        score2.setText(player2.playerName() + " : " + p2score);
    }

    public void updateTotalScore(){
        tscore1.setText(player1.playerName() + " : " + totalscore1);
        tscore2.setText(player2.playerName() + " : " + totalscore2);
    }

    @Override
    public void handleClick(int i,int j){
        if(awaitForClick && BoardHelper.canPlay(board,turn,i,j)){
            System.out.println("User Played in : "+ i + " , " + j);

            //update board
            board = BoardHelper.getNewBoardAfterMove(board,new Point(i,j),turn);

            //advance turn
            turn = (turn == 1) ? 2 : 1;

            repaint();

            awaitForClick = false;

            //callback
            manageTurn();
        }
    }

    public void handleAI(GamePlayer ai){
        Point aiPlayPoint = ai.play(board);
        int i = aiPlayPoint.x;
        int j = aiPlayPoint.y;
        if(!BoardHelper.canPlay(board,ai.myMark,i,j)) System.err.println("FATAL : AI Invalid Move !");
        System.out.println(ai.playerName() + " Played in : "+ i + " , " + j);

        //update board
        board = BoardHelper.getNewBoardAfterMove(board,aiPlayPoint,turn);

        //advance turn
        turn = (turn == 1) ? 2 : 1;

        repaint();
    }

}
