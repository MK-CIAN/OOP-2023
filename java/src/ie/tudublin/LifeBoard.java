package ie.tudublin;

import processing.core.PApplet;

public class LifeBoard extends PApplet {
    boolean[][] board;
    boolean[][] next;

    private int size;
    PApplet p;

    float cellWidth;
    //boolean paused;

    public boolean getCell(int row, int col)
    {
        if (row >= 0 && row < size && col >= 0 && col < size)
        {
            return board[row][col];
        }
        else
        {
            return false;
        }
    }

    public int countCells(int row, int col)
    {
        int count = 0;
        for(int i = -1 ; i <= 1 ; i ++)
        {
            for(int j = -1 ; j <= 1 ; j ++)
            {
                if (! (i == 0 && j == 0))
                {
                    if (getCell(row + i, col + j))
                    {
                        count ++;
                    }
                }
            }
        }
        return count;
    }


    public void applyRules()
    {
        for(int row = 0 ; row < size ; row ++)
        {
            for(int col = 0 ; col < size ; col ++)
            {
                int count = countCells(row, col);
                if (board[row][col])
                {
                    if (count == 2 || count == 3)
                    {
                        next[row][col] = true;
                    }
                    else
                    {
                        next[row][col] = false;
                    }
                }
                else
                {
                    if (count == 3)
                    {
                        next[row][col] = true;
                    }
                    else
                    {
                        next[row][col] = false;
                    }
                }
            }
        }
        boolean[][]temp = board;
        board = next;
        next = temp;
    }
    
    public LifeBoard(int size, PApplet p)
    {
        this.size = size;
        this.p = p;
        board = new boolean[size][size];
        next = new boolean[size][size];
        cellWidth = p.width / (float) size;

        //setting paused to false
       // paused = false;
    }

    public void randomise()
    {
        for(int row = 0 ; row < size ; row ++)
        {
            for(int col = 0 ; col < size ; col ++)
            {
                float dice = p.random(0.0f, 1.0f);
                board[row][col] = (dice <= 0.5f);
            }
        }
    }

    public void render()
    {
        p.background(0);
        for(int row = 0 ; row < size ; row ++)
        {
            p.stroke(255);
            for(int col = 0 ; col < size ; col ++)
            {
                float x = col * cellWidth;
                float y = row * cellWidth;

                if(board[row][col])
                {
                    p.fill(0, 255, 0);
                }
                else
                {
                    p.noFill();
                }
                p.rect(x, y, cellWidth, cellWidth);
            }
        }
    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }
}
