package GameLogic;

/**
 * Created by Peter Jensen on 30-10-2015.
 */
public class TicTacToeLogic
{
    private int[][] board;// Store the user ID in that board posistion.
    private int turnInTotal;// total turn in game
    private int currentTurn = 0;// currerent turn
    private int size;

    TicTacToeLogic(int size)
    {
        this.size = size;
        board = new int[size][size];
        turnInTotal = size * size;
    }

    public boolean setPiece(int x, int y, int ID)
    {
        boolean done = false;
        if (board[x][y] == 0 && turnInTotal > currentTurn)
        {
            board[x][y] = ID;
            currentTurn++;
            done = true;
        }
        return done;
    }

    public int getTurnsLeft()
    {
        return turnInTotal - currentTurn;
    }


    //Use this users ID.
    public boolean getWin(int ID)
    {
        for (int i = 0; i < size; i++)
        {
            if (checkRow(i, ID))
                return true;
        }

        for (int i = 0; i < size; i++)
        {
            if (checkColm(i, ID))
                return true;
        }
        if (checkAcross(ID))
            return true;

        return false;
    }

    public int checkForWinner(int user1ID, int user2ID)
    {
        if (getWin(user1ID))
            return user1ID;
        else if (getWin(user2ID))
            return user2ID;
        else
            return 0;
    }

    private boolean checkColm(int colmNumber, int ID)
    {
        int i = 0;

        for (int j = 0; j < size; j++)
        {
            if (getSpot(colmNumber, j) == ID)
                i++;
            else
                return false;
        }

        return i == size;
    }

    private boolean checkAcross(int ID)
    {
        int i = 0;
        for (int j = 0; j < size; j++)
        {
            if (getSpot(j, j) == ID)
                i++;
        }
        if (i == size)
            return true;

        i = 0;
        for (int j = 0; j < size; j++)
        {
            if (getSpot(j, size - 1 - j) == ID)
                i++;
        }
        if (i == size)
            return true;
        return false;
    }

    private boolean checkRow(int rowNumber, int ID)
    {
        int i = 0;

        for (int j = 0; j < size; j++)
        {
            if (getSpot(j, rowNumber) == ID)
                i++;
            else
                return false;
        }

        return i == size;
    }

    private int getSpot(int x, int y)
    {
        return board[x][y];
    }

}
