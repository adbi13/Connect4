package players.minimax;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import board.Board;
import board.BoardEvaluation;

public class MinimaxNode
{
    private boolean isMax;
    private int lastMove;
    private int value;
    private char player;
    private char oponent;
    private List<MinimaxNode> children;

    public MinimaxNode(boolean isMax, int lastMove, char player, char oponent)
    {
        this.isMax = isMax;
        this.lastMove = lastMove;
        this.player = player;
        this.oponent = oponent;
        this.children = new ArrayList<MinimaxNode>();
    }

    private void valuateBoard(Board board)
    {
        value = BoardEvaluation.countTriplets(board, player) * 100;
        value -= BoardEvaluation.countTriplets(board, oponent) * 100;

        int boardCenter = Board.BOARD_WIDTH / 2;

        for (int row = 0; row < Board.BOARD_HEIGHT; row++)
        {
            for (int col = 0; col < Board.BOARD_WIDTH; col++)
            {
                if (board.getField(row, col) == player)
                {
                    value += boardCenter - Math.abs(col - boardCenter);
                }
                if (board.getField(row, col) == oponent)
                {
                    value -= boardCenter - Math.abs(col - boardCenter);
                }
            }
        }

        if (!isMax)
        {
            value = -value;
        }
    }

    public void applyMinimax(Board board, int depth)
    {
        if (BoardEvaluation.isPlayerWinning(board, oponent))
        {
            value = (isMax ? -10000 : 10000) * (MinimaxTree.LIMIT + 1 - depth);
            return;
        }

        if (depth == MinimaxTree.LIMIT)
        {
            valuateBoard(board);
            return;
        }

        for (int col = 0; col < Board.BOARD_WIDTH; col++)
        {
            if (board.isColumnFull(col))
            {
                continue;
            }

            MinimaxNode child = new MinimaxNode(!isMax, col, oponent, player);

            board.move(col, player);
            child.applyMinimax(board, depth + 1);
            children.add(child);
            board.reverseColumnMove(col);
        }

        if (isMax)
        {
            value = Collections.max(children, (x, y) -> Integer.compare(x.value, y.value)).value;
        }
        else
        {
            value = Collections.min(children, (x, y) -> Integer.compare(x.value, y.value)).value;
        }
    }

    public void applyMinimaxWithAlphaBetaPruning(Board board, int depth, int alpha, int beta)
    {
        if (BoardEvaluation.isPlayerWinning(board, oponent))
        {
            value = (isMax ? -1000 : 1000) * (MinimaxTree.LIMIT + 1 - depth);
            return;
        }

        if (depth == MinimaxTree.LIMIT)
        {
            valuateBoard(board);
            return;
        }

        value = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int col = 0; col < Board.BOARD_WIDTH; col++)
        {
            if (board.isColumnFull(col))
            {
                continue;
            }

            MinimaxNode child = new MinimaxNode(!isMax, col, oponent, player);

            board.move(col, player);
            child.applyMinimaxWithAlphaBetaPruning(board, depth + 1, alpha, beta);
            children.add(child);
            board.reverseColumnMove(col);


            if (isMax)
            {
                value = Math.max(value, child.value);
                alpha = Math.max(alpha, value);
            }
            else
            {
                value = Math.min(value, child.value);
                beta = Math.min(beta, value);
            }

            if (beta <= alpha)
            {
                return;
            }
        }
    }

    public int getBestMove()
    {
        if (isMax)
        {
            return Collections.max(children, (x, y) -> Integer.compare(x.value, y.value)).lastMove;
        }
        else
        {
            return Collections.min(children, (x, y) -> Integer.compare(x.value, y.value)).lastMove;
        }
    }

    public void print()
    {
        if (children.size() == 0)
        {
            System.out.printf(" %d", value);
        }
        System.out.printf(" %d (", value);
        for (int i = 0; i < children.size(); i++)
        {
            children.get(i).print();
        }
        System.out.print(")");
    }
}
