package players.minimax;

import board.Board;

public class MinimaxTree
{
    public static final int LIMIT = 7;

    private MinimaxNode root;
    private char maxSymbol;
    private char minSymbol;
    private Board board;
    
    public MinimaxTree(char maxSymbol, char minSymbol)
    {
        this.maxSymbol = maxSymbol;
        this.minSymbol = minSymbol;
    }

    public void buildTree(Board board)
    {
        this.board = board.copy();
        root = new MinimaxNode(true, -1, maxSymbol, minSymbol);
        root.applyMinimaxWithAlphaBetaPruning(this.board, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public int getBestMove()
    {
        return root.getBestMove();
    }
}
