package board;

public class BoardUtils
{
    private static void printDeskLine(Board board, int row)
    {
        System.out.print("│");
        for (int col = 0; col < Board.BOARD_WIDTH; col++)
        {
            System.out.printf(" %c │", board.getField(row, col));
        }
        System.out.println();
    }

    private static void printDeskLineColors(Board board, int row)
    {
        System.out.print("│");
        for (int col = 0; col < Board.BOARD_WIDTH; col++)
        {
            switch (board.getField(row, col))
            {
                case 'X':
                    System.out.print("\u001B[36m▄▄▄\u001B[0m│");
                    break;

                case 'O':
                    System.out.print("\u001B[35m▄▄▄\u001B[0m│");
                    break;

                default:
                    System.out.print("   │");
            }
        }

        System.out.println();
        System.out.print("│");
        for (int col = 0; col < Board.BOARD_WIDTH; col++)
        {
            switch (board.getField(row, col))
            {
                case 'X':
                    System.out.print("\u001B[36m███\u001B[0m│");
                    break;

                case 'O':
                    System.out.print("\u001B[35m███\u001B[0m│");
                    break;

                default:
                    System.out.print("   │");
            }
        }
        System.out.println();
    }

    private static void printColumnNumbers()
    {
        System.out.print(" ");
        for (int col = 0; col < Board.BOARD_WIDTH; col++)
        {
            System.out.printf(" %d  ", col);
        }
        System.out.println();
    }

    private static void printDividingLine()
    {
        System.out.print("└");
        for (int col = 0; col < Board.BOARD_WIDTH - 1; col++)
        {
            System.out.printf("───┴", col);
        }
        System.out.println("───┘");
    }

    public static void printBoard(Board board)
    {
        printColumnNumbers();
        for (int row = 0; row < Board.BOARD_HEIGHT; row++)
        {
            if (System.getProperty("os.name").startsWith("Linux"))
            {
                printDeskLineColors(board, row);
            }
            else
            {
                printDeskLine(board, row);
            }
        }
        printDividingLine();
        printColumnNumbers();
    }
}
