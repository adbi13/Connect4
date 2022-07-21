package players.minimax;

import board.Board;
import players.Player;

public class MinimaxPlayer extends Player
{
    private MinimaxTree minimaxTree;

    public MinimaxPlayer(char symbol, char oponent)
    {
        super(symbol);
        minimaxTree = new MinimaxTree(symbol, oponent);
    }

    @Override
    public int getNextMove(Board board)
    {
        minimaxTree.buildTree(board);
        return minimaxTree.getBestMove();
    }
}
