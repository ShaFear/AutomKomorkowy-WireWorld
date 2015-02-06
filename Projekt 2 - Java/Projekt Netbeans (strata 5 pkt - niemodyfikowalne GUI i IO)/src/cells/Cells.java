/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cells;

import cells.cell.Cell;
import cells.cell.Conductor;
import cells.cell.Head;
import cells.cell.Tail;

/**
 *
 * @author shafe_000
 */
public class Cells {

    private final Cell[] cells;
    private final int x;
    private final int y;

    public Cells() {
        cells = new Cell[100];
        this.x = 10;
        this.y = 10;
        for (int j = 0; j < 100; j++) {
            cells[j] = new Cell();
        }
    }

    public Cells(int x, int y) {
        cells = new Cell[x * y];
        this.x = x;
        this.y = y;
        for (int j = 0; j < cells.length; j++) {
            cells[j] = new Cell();
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setCell(int x, int y, Cell v) {
        if (x > this.getX()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (y > this.getY()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (x < 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (y < 1) {
            throw new ArrayIndexOutOfBoundsException();
        }

        x--;
        y--;

        cells[x + this.x * y] = v;
    }

    public void setCell(int x, int y, int v) {
        Cell c;
        switch (v) {
            case 0: {
                c = new Cell();
                break;
            }
            case 1: {
                c = new Conductor();
                break;
            }
            case 2: {
                c = new Tail();
                break;
            }
            case 3: {
                c = new Head();
                break;
            }
            default: {
                c = new Cell();
                break;
            }
        }
        if (x > this.getX()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (y > this.getY()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (x < 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (y < 1) {
            throw new ArrayIndexOutOfBoundsException();
        }

        x--;
        y--;

        cells[x + this.x * y] = c;
    }

    public Cell getCell(int x, int y) {
        //just to be ensure about ArrayIndexOutOfBoundsException when x and y have bad values.
        if (x > this.getX()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (y > this.getY()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (x < 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (y < 1) {
            throw new ArrayIndexOutOfBoundsException();
        }

        x--;
        y--;

        return cells[x + this.x * y];
    }

    @Override
    public String toString() {
        String build = new String();
        for (int j = 1; j <= this.y; j++) {
            for (int i = 1; i <= this.x; i++) {
                build += this.getCell(i, j) + " ";
            }
            build += "\n";
        }
        return build;
    }

}
