package board;

public class BoardEvaluation
{
    private static final int WINNING_COUNT = 4;

    private static boolean checkDirection(Board board, char playerSymbol, int row, int col, int rowChange, int colChange)
    {
        int count = 0;
        while (row >= 0 && row < Board.BOARD_HEIGHT && col >= 0 && col < Board.BOARD_WIDTH)
        {
            if (board.getField(row, col) == playerSymbol)
            {
                count++;
                if (count >= WINNING_COUNT)
                {
                    return true;
                }
            }
            else
            {
                count = 0;
            }
            row += rowChange;
            col += colChange;
        }

        return false;
    }

    public static boolean isPlayerWinning(Board board, char playerSymbol)
    {
        for (int row = 0; row < Board.BOARD_HEIGHT; row++)
        {
            if (checkDirection(board, playerSymbol, row, 0, -1, 1)
                || checkDirection(board, playerSymbol, row, 0, 0, 1)
                || checkDirection(board, playerSymbol, row, 0, 1, 1)
                || checkDirection(board, playerSymbol, row, Board.BOARD_WIDTH - 1, -1, -1)
                || checkDirection(board, playerSymbol, row, Board.BOARD_WIDTH - 1, 1, -1))
            {
                return true;
            }
        }

        for (int col = 0; col < Board.BOARD_WIDTH; col++)
        {
            if (checkDirection(board, playerSymbol, 0, col, 1, 0))
            {
                return true;
            }
        }

        return false;
    }

    private static int countTripletsInDirection(Board board, char playerSymbol, int row, int col, int rowChange, int colChange)
    {
        int symbolCount = 0;
        int triplets = 0;
        while (row >= 0 && row < Board.BOARD_HEIGHT && col >= 0 && col < Board.BOARD_WIDTH)
        {
            if (board.getField(row, col) == playerSymbol)
            {
                symbolCount++;
                if (symbolCount == 3)
                {
                    triplets++;
                }
            }
            else
            {
                symbolCount = 0;
            }
            row += rowChange;
            col += colChange;
        }

        return triplets;
    }

    public static int countTriplets(Board board, char playerSymbol)
    {
        int count = 0;

        for (int row = 0; row < Board.BOARD_HEIGHT; row++)
        {
            count += countTripletsInDirection(board, playerSymbol, row, 0, -1, 1);
            count += countTripletsInDirection(board, playerSymbol, row, 0, 0, 1);
            count += countTripletsInDirection(board, playerSymbol, row, 0, 1, 1);
            count += countTripletsInDirection(board, playerSymbol, row, Board.BOARD_WIDTH - 1, -1, -1);
            count += countTripletsInDirection(board, playerSymbol, row, Board.BOARD_WIDTH - 1, 1, -1);
        }

        for (int col = 0; col < Board.BOARD_WIDTH; col++)
        {
            count += countTripletsInDirection(board, playerSymbol, 0, col, 1, 0);
        }

        return count;
    }
}
