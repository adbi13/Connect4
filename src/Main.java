import java.util.Scanner;
import board.Board;
import players.Player;
import players.human.HumanPlayer;
import players.minimax.MinimaxPlayer;

public class Main
{
    public static void main(String args[])
    {
        Board board = new Board();

        Scanner scanner = new Scanner(System.in);
        Player player1 = new HumanPlayer('X', scanner);
        Player player2 = new MinimaxPlayer('O', 'X');

        Player[] players = { player1, player2 };

        Game game = new Game(board, players);

        game.play();

        scanner.close();
    }
}
