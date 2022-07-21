package board;

import java.security.InvalidParameterException;

public class Board
{
    public static final int BOARD_WIDTH = 8;
    public static final int BOARD_HEIGHT = 8;
    private static final char EMPTY_FIELD = ' ';

    private char desk[][] = new char[BOARD_HEIGHT][BOARD_WIDTH];

    public Board()
    {
        for (int row = 0; row < BOARD_HEIGHT; row++)
        {
            for (int col = 0; col < BOARD_WIDTH; col++)
            {
                desk[row][col] = EMPTY_FIELD;
            }
        }
    }

    public char getField(int row, int col)
    {
        return desk[row][col];
    }


    public boolean isColumnFull(int column)
    {
        return desk[0][column] != EMPTY_FIELD;
    }

    public boolean isFull()
    {
        for (int row = 0; row < BOARD_HEIGHT; row++)
        {
            for (int col = 0; col < BOARD_WIDTH; col++)
            {
                if (desk[row][col] == EMPTY_FIELD)
                {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isValidMove(int column)
    {
        return column >= 0 && column < BOARD_WIDTH && !isColumnFull(column);
    }

    private int topOfColumn(int column)
    {
        int row = BOARD_HEIGHT - 1;
        while (desk[row][column] != EMPTY_FIELD)
        {
            row--;
            if (row < 0)
            {
                return -1;
            }
        }
        return row;
    }

    public void move(int column, char playerSymbol)
    {
        int row = topOfColumn(column);
        if (row == -1)
        {
            throw new InvalidParameterException("Column is full!");
        }
        desk[row][column] = playerSymbol;
    }

    public void reverseColumnMove(int column)
    {
        int row = topOfColumn(column);
        if (row < BOARD_HEIGHT - 1)
        {
            desk[row + 1][column] = EMPTY_FIELD;
        }
    }

    public Board copy()
    {
        Board newBoard = new Board();

        for (int row = 0; row < BOARD_HEIGHT; row++)
        {
            for (int col = 0; col < BOARD_WIDTH; col++)
            {
                newBoard.desk[row][col] = desk[row][col];
            }
        }

        return newBoard;
    }
}
