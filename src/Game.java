import board.Board;
import board.BoardEvaluation;
import board.BoardUtils;
import players.Player;

public class Game
{
    private Board board;
    private Player[] players;
    private int actualPlayer;

    public Game(Board board, Player[] players)
    {
        this.board = board;
        this.players = players;
        actualPlayer = 0;
    }

    public boolean playOneMove()
    {
        if (board.isFull())
        {
            System.out.println("The board is full, there isn't any possible move! Draw.");
            return false;
        }

        if (System.getProperty("os.name").startsWith("Linux"))
        {
            System.out.print("\033[H\033[2J");  
            System.out.flush();
        }

        Player player = players[actualPlayer];
        System.out.printf("Player %d (%c):\n", actualPlayer + 1, player.getSymbol());

        BoardUtils.printBoard(board);

        int chosenColumn = player.getNextMove(board);

        board.move(chosenColumn, player.getSymbol());

        if (BoardEvaluation.isPlayerWinning(board, player.getSymbol()))
        {
            if (System.getProperty("os.name").startsWith("Linux"))
            {
                System.out.print("\033[H\033[2J");  
                System.out.flush();
            }
            BoardUtils.printBoard(board);
            System.out.printf("Player %d (%c) won!\n", actualPlayer + 1, player.getSymbol());
            return false;
        }

        actualPlayer = (actualPlayer + 1) % players.length;
        return true;
    }

    public void play()
    {
        System.out.println(System.getProperty("os.name"));
        System.out.println("Welcome to Connect 4!");

        while (playOneMove());

        System.out.println("Bye.");
    }
}
