package players.human;

import java.util.InputMismatchException;
import java.util.Scanner;
import board.Board;
import players.Player;

public class HumanPlayer extends Player
{
    private Scanner scanner;

    public HumanPlayer(char symbol, Scanner scanner)
    {
        super(symbol);
        this.scanner = scanner;
    }

    @Override
    public int getNextMove(Board board)
    {
        System.out.print("Enter any number: ");

        int column;
        while(true)
        {
            try
            {
                column = scanner.nextInt();

                if (board.isValidMove(column))
                {
                    return column;
                }

                System.out.println("Invalid move. Try again:");
            }
            catch (InputMismatchException e)
            {
                scanner.next();
                System.out.println("Number expected. Try again:");
            }
        }
    }
}
