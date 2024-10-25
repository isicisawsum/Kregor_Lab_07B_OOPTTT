import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TicTacToeFrame extends JFrame {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];
    private static String player;
    private static int moves;
    private static boolean playing;
    JPanel mainPnl;
    JPanel tttPnl;
    //JButton tile;
    JButton quit;
    JLabel title;
    public TicTacToeFrame(){
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        player = "X";
        playing = true;


        //Title probably doesn't need its own method
        title = new JLabel("Tic Tac Toe!", JLabel.CENTER);
        title.setFont(new Font("Impact", Font.PLAIN, 36));
        mainPnl.add(title, BorderLayout.NORTH);

        display();
        mainPnl.add(tttPnl, BorderLayout.CENTER);

        //quit button also probably doesn't need its own method
        quit = new JButton();
        quit.setText("QUIT");
        quit.addActionListener((ActionEvent ae) -> System.exit(0));
        mainPnl.add(quit, BorderLayout.SOUTH);

        add(mainPnl);
        setSize(600,750);
        setLocation(0,0);
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void clearBoard() {
        for (Component comp : tttPnl.getComponents()) {
            if (comp instanceof TicTacToeTile) {
                TicTacToeTile tile = (TicTacToeTile) comp;
                tile.setText(" ");
            }
        }

        // Reset the board array as well
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                board[row][col] = " ";
            }
        }

        //resetting stats
        player = "O";
        moves = 0;


        //for (int row = 0; row < ROW; row++) {
        //    for (int col = 0; col < COL; col++) {
        //         board[row][col] = " ";
        //    }
        // }
        // Remove all buttons from the panel
        // for (Component comp : tttPnl.getComponents()) {
        //    if (comp instanceof TicTacToeTile) {
        //        TicTacToeTile tile = (TicTacToeTile) comp;
        //       tile.setText(" ");  // Clear the text on each tile
        //    }
        //}
    }


    private void display(){
        tttPnl = new JPanel();
        tttPnl.setLayout(new GridLayout(ROW, COL));

        for(int row=0; row < ROW; row++)
        {
            for(int col=0; col < COL; col++)
            {
                board[row][col] = " ";
                TicTacToeTile tile = new TicTacToeTile(row, col);
                tttPnl.add(tile);

                tile.setFont(new Font("Impact", Font.PLAIN, 40));

                tile.addActionListener(e -> getClicked(tile.getRow(), tile.getCol(), tile));
            }
        }
    }

    private boolean isValidMove(int row, int col)
    {
        boolean retVal = false;
        if(board[row][col].equals(" "))
            retVal = true;
        else{
            JOptionPane.showMessageDialog(this, "Not a valid move");
        }

        return retVal;

    }

    private static boolean isWin(String player)
    {
        if(isColWin(player) || isRowWin(player) || isDiagnalWin(player))
        {
            return true;
        }

        return false;
    }
    private static boolean isColWin(String player)
    {
        // checks for a col win for specified player
        for(int col=0; col < COL; col++)
        {
            if(board[0][col].equals(player) &&
                    board[1][col].equals(player) &&
                    board[2][col].equals(player))
            {
                return true;
            }
        }
        return false; // no col win
    }
    private static boolean isRowWin(String player)
    {
        // checks for a row win for the specified player
        for(int row=0; row < ROW; row++)
        {
            if(board[row][0].equals(player) &&
                    board[row][1].equals(player) &&
                    board[row][2].equals(player))
            {
                return true;
            }
        }
        return false; // no row win
    }
    private static boolean isDiagnalWin(String player)
    {
        // checks for a diagonal win for the specified player

        if(board[0][0].equals(player) &&
                board[1][1].equals(player) &&
                board[2][2].equals(player) )
        {
            return true;
        }
        if(board[0][2].equals(player) &&
                board[1][1].equals(player) &&
                board[2][0].equals(player) )
        {
            return true;
        }
        return false;
    }

    private void getClicked(int row, int col, JButton tile){
        if(isValidMove(row, col)){
            board[row][col] = player;
            tile.setText(player);
            moves++;
            if(moves >= 5 && moves < 9){
                if (isWin(player)) {
                    JOptionPane.showMessageDialog(this, player + " wins!");

                    int response = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Play Again?", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        clearBoard();
                        System.out.println("Board cleared");
                        playing = true;
                    } else {
                        playing = false;
                        System.exit(0);
                    }
                }
            }
            else if(moves == 9){
                JOptionPane.showMessageDialog(this , "It's a tie!");
                int response = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Play Again?", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    clearBoard();
                    playing = true;
                } else {
                    playing = false;
                    System.exit(0);
                }
            }
            //else {
            if(player.equals("X")){
                player = "O";
            }
            else if (player.equals("O")){
                player = "X";
            }
            //}

        }
    }

}
