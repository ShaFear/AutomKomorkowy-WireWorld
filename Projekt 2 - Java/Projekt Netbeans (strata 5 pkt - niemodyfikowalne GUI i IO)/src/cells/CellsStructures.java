/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cells;

import cells.cell.*;

/**
 *
 * @author shafe_000
 */
public class CellsStructures {

    private final boolean isStructureSetStyleWrapped;
    private final Cells cells;

    public CellsStructures(Cells cellsx, boolean isWrapped) {
        isStructureSetStyleWrapped = isWrapped;
        cells = cellsx;
    }

    //Special expressions
    private boolean setCellOfStructure(int x, int y, int var) {
        while (x > cells.getX()) {
            if (isStructureSetStyleWrapped) {
                x = x - cells.getX();
            } else {
                return false;
            }
        }
        while (y > cells.getY()) {
            if (isStructureSetStyleWrapped) {
                y = y - cells.getY();
            } else {
                return false;
            }
        }
        while (x < 1) {
            if (isStructureSetStyleWrapped) {
                x = x + cells.getX();
            } else {
                return false;
            }
        }
        while (y < 1) {
            if (isStructureSetStyleWrapped) {
                y = y + cells.getY();
            } else {
                return false;
            }
        }
        Cell cell;
        switch (var) {
            case 0: {
                cell = new Empty();
                break;
            }
            case 1: {
                cell = new Conductor();
                break;
            }
            case 2: {
                cell = new Tail();
                break;
            }
            case 3: {
                cell = new Head();
                break;
            }
            default: {
                cell = new Empty();
                break;
            }
        }
        cells.setCell(x, y, cell);
        return true;
    }

    public Cells setDiode(int x, int y, int pos) {
        if (pos == 0) {
            setCellOfStructure(x + (0), y + (0), 1);
            setCellOfStructure(x + (1), y + (-1), 1);
            setCellOfStructure(x + (1), y + (0), 1);
            setCellOfStructure(x + (1), y + (1), 1);
            setCellOfStructure(x + (2), y + (1), 1);
            setCellOfStructure(x + (2), y + (-1), 1);
            setCellOfStructure(x + (3), y + (0), 1);
            setCellOfStructure(x + (2), y + (0), 0);
            setCellOfStructure(x + (0), y + (-1), 0);
            setCellOfStructure(x + (0), y + (1), 0);
            setCellOfStructure(x + (3), y + (1), 0);
            setCellOfStructure(x + (3), y + (-1), 0);
            setCellOfStructure(x + (0), y + (-2), 0);
            setCellOfStructure(x + (1), y + (-2), 0);
            setCellOfStructure(x + (2), y + (-2), 0);
            setCellOfStructure(x + (3), y + (-2), 0);
            setCellOfStructure(x + (3), y + (2), 0);
            setCellOfStructure(x + (2), y + (2), 0);
            setCellOfStructure(x + (1), y + (2), 0);
            setCellOfStructure(x + (0), y + (2), 0);
        } else if (pos == 1) {
            setCellOfStructure(x + (0), y + (0), 1);
            setCellOfStructure(x + (-1), y + (1), 1);
            setCellOfStructure(x + (0), y + (1), 1);
            setCellOfStructure(x + (1), y + (1), 1);
            setCellOfStructure(x + (-1), y + (2), 1);
            setCellOfStructure(x + (1), y + (2), 1);
            setCellOfStructure(x + (0), y + (3), 1);
            setCellOfStructure(x + (-1), y + (0), 0);
            setCellOfStructure(x + (1), y + (0), 0);
            setCellOfStructure(x + (2), y + (0), 0);
            setCellOfStructure(x + (2), y + (1), 0);
            setCellOfStructure(x + (2), y + (2), 0);
            setCellOfStructure(x + (2), y + (3), 0);
            setCellOfStructure(x + (1), y + (3), 0);
            setCellOfStructure(x + (-1), y + (3), 0);
            setCellOfStructure(x + (-2), y + (3), 0);
            setCellOfStructure(x + (-2), y + (2), 0);
            setCellOfStructure(x + (-2), y + (1), 0);
            setCellOfStructure(x + (-2), y + (0), 0);
            setCellOfStructure(x + (0), y + (2), 0);
        } else if (pos == 2) {
            setCellOfStructure(x + (0), y + (0), 1);
            setCellOfStructure(x + (-1), y + (1), 1);
            setCellOfStructure(x + (-1), y + (0), 1);
            setCellOfStructure(x + (-1), y + (-1), 1);
            setCellOfStructure(x + (-2), y + (-1), 1);
            setCellOfStructure(x + (-2), y + (1), 1);
            setCellOfStructure(x + (0), y + (1), 0);
            setCellOfStructure(x + (0), y + (-1), 0);
            setCellOfStructure(x + (-3), y + (-1), 0);
            setCellOfStructure(x + (-3), y + (-2), 0);
            setCellOfStructure(x + (-2), y + (-2), 0);
            setCellOfStructure(x + (-1), y + (-2), 0);
            setCellOfStructure(x + (0), y + (-2), 0);
            setCellOfStructure(x + (-3), y + (2), 0);
            setCellOfStructure(x + (-2), y + (2), 0);
            setCellOfStructure(x + (-1), y + (2), 0);
            setCellOfStructure(x + (0), y + (2), 0);
            setCellOfStructure(x + (-3), y + (1), 0);
            setCellOfStructure(x + (-2), y + (0), 0);
            setCellOfStructure(x + (-3), y + (0), 1);
        } else if (pos == 3) {
            setCellOfStructure(x + (0), y + (0), 1);
            setCellOfStructure(x + (-1), y + (-1), 1);
            setCellOfStructure(x + (0), y + (-1), 1);
            setCellOfStructure(x + (1), y + (-1), 1);
            setCellOfStructure(x + (1), y + (-2), 1);
            setCellOfStructure(x + (-1), y + (-2), 1);
            setCellOfStructure(x + (0), y + (-3), 1);
            setCellOfStructure(x + (0), y + (-2), 0);
            setCellOfStructure(x + (-1), y + (-3), 0);
            setCellOfStructure(x + (-2), y + (-3), 0);
            setCellOfStructure(x + (-2), y + (-2), 0);
            setCellOfStructure(x + (-2), y + (-1), 0);
            setCellOfStructure(x + (-2), y + (0), 0);
            setCellOfStructure(x + (-1), y + (0), 0);
            setCellOfStructure(x + (1), y + (0), 0);
            setCellOfStructure(x + (2), y + (0), 0);
            setCellOfStructure(x + (2), y + (-1), 0);
            setCellOfStructure(x + (2), y + (-2), 0);
            setCellOfStructure(x + (2), y + (-3), 0);
            setCellOfStructure(x + (1), y + (-3), 0);
        }
        return cells;
    }

    public Cells setClockGeneratorSmall(int x, int y, int pos) {

        if (pos == 0) {
            setCellOfStructure(x + (0), y + (0), 1);
            setCellOfStructure(x + (1), y + (1), 1);
            setCellOfStructure(x + (2), y + (1), 1);
            setCellOfStructure(x + (3), y + (0), 1);
            setCellOfStructure(x + (2), y + (-1), 1);
            setCellOfStructure(x + (1), y + (-1), 1);
            setCellOfStructure(x + (4), y + (0), 0);
            setCellOfStructure(x + (4), y + (-1), 0);
            setCellOfStructure(x + (3), y + (-1), 0);
            setCellOfStructure(x + (3), y + (1), 0);
            setCellOfStructure(x + (4), y + (1), 0);
            setCellOfStructure(x + (3), y + (-2), 0);
            setCellOfStructure(x + (2), y + (-2), 0);
            setCellOfStructure(x + (1), y + (-2), 0);
            setCellOfStructure(x + (0), y + (-2), 0);
            setCellOfStructure(x + (0), y + (-1), 0);
            setCellOfStructure(x + (0), y + (1), 0);
            setCellOfStructure(x + (0), y + (2), 0);
            setCellOfStructure(x + (1), y + (2), 0);
            setCellOfStructure(x + (2), y + (2), 0);
            setCellOfStructure(x + (3), y + (2), 0);
            setCellOfStructure(x + (1), y + (0), 0);
            setCellOfStructure(x + (2), y + (0), 0);
            setCellOfStructure(x + (2), y + (1), 2);
            setCellOfStructure(x + (3), y + (0), 3);
        }
        if (pos == 1) {
            setCellOfStructure(x + (0), y + (0), 1);
            setCellOfStructure(x + (1), y + (1), 1);
            setCellOfStructure(x + (1), y + (2), 1);
            setCellOfStructure(x + (0), y + (3), 1);
            setCellOfStructure(x + (-1), y + (2), 1);
            setCellOfStructure(x + (-1), y + (1), 1);
            setCellOfStructure(x + (0), y + (2), 0);
            setCellOfStructure(x + (0), y + (1), 0);
            setCellOfStructure(x + (-1), y + (3), 0);
            setCellOfStructure(x + (-1), y + (0), 0);
            setCellOfStructure(x + (1), y + (0), 0);
            setCellOfStructure(x + (1), y + (3), 0);
            setCellOfStructure(x + (2), y + (3), 0);
            setCellOfStructure(x + (2), y + (2), 0);
            setCellOfStructure(x + (2), y + (1), 0);
            setCellOfStructure(x + (2), y + (0), 0);
            setCellOfStructure(x + (-2), y + (3), 0);
            setCellOfStructure(x + (-2), y + (2), 0);
            setCellOfStructure(x + (-2), y + (1), 0);
            setCellOfStructure(x + (-2), y + (0), 0);
            setCellOfStructure(x + (-1), y + (4), 0);
            setCellOfStructure(x + (0), y + (4), 0);
            setCellOfStructure(x + (1), y + (4), 0);
            setCellOfStructure(x + (0), y + (3), 3);
            setCellOfStructure(x + (1), y + (2), 2);
        }
        if (pos == 2) {
            setCellOfStructure(x + (0), y + (0), 1);
            setCellOfStructure(x + (-1), y + (-1), 1);
            setCellOfStructure(x + (-2), y + (-1), 1);
            setCellOfStructure(x + (-2), y + (1), 1);
            setCellOfStructure(x + (-1), y + (1), 1);
            setCellOfStructure(x + (-3), y + (0), 1);
            setCellOfStructure(x + (-2), y + (0), 0);
            setCellOfStructure(x + (-1), y + (0), 0);
            setCellOfStructure(x + (0), y + (1), 0);
            setCellOfStructure(x + (0), y + (2), 0);
            setCellOfStructure(x + (0), y + (-1), 0);
            setCellOfStructure(x + (0), y + (-2), 0);
            setCellOfStructure(x + (-2), y + (-2), 0);
            setCellOfStructure(x + (-1), y + (-2), 0);
            setCellOfStructure(x + (-3), y + (-2), 0);
            setCellOfStructure(x + (-3), y + (-1), 0);
            setCellOfStructure(x + (-4), y + (-1), 0);
            setCellOfStructure(x + (-4), y + (0), 0);
            setCellOfStructure(x + (-4), y + (1), 0);
            setCellOfStructure(x + (-3), y + (1), 0);
            setCellOfStructure(x + (-3), y + (2), 0);
            setCellOfStructure(x + (-2), y + (2), 0);
            setCellOfStructure(x + (-1), y + (2), 0);
            setCellOfStructure(x + (-3), y + (0), 3);
            setCellOfStructure(x + (-2), y + (1), 2);
        }
        if (pos == 3) {
            setCellOfStructure(x + (0), y + (0), 1);
            setCellOfStructure(x + (1), y + (-1), 1);
            setCellOfStructure(x + (1), y + (-2), 1);
            setCellOfStructure(x + (0), y + (-3), 1);
            setCellOfStructure(x + (-1), y + (-2), 1);
            setCellOfStructure(x + (-1), y + (-1), 1);
            setCellOfStructure(x + (0), y + (-4), 0);
            setCellOfStructure(x + (1), y + (-3), 0);
            setCellOfStructure(x + (-1), y + (-3), 0);
            setCellOfStructure(x + (1), y + (-4), 0);
            setCellOfStructure(x + (-1), y + (-4), 0);
            setCellOfStructure(x + (-2), y + (-3), 0);
            setCellOfStructure(x + (-2), y + (-2), 0);
            setCellOfStructure(x + (-2), y + (-1), 0);
            setCellOfStructure(x + (-2), y + (0), 0);
            setCellOfStructure(x + (-1), y + (0), 0);
            setCellOfStructure(x + (1), y + (0), 0);
            setCellOfStructure(x + (2), y + (0), 0);
            setCellOfStructure(x + (2), y + (-1), 0);
            setCellOfStructure(x + (2), y + (-2), 0);
            setCellOfStructure(x + (2), y + (-3), 0);
            setCellOfStructure(x + (0), y + (-1), 0);
            setCellOfStructure(x + (0), y + (-2), 0);
            setCellOfStructure(x + (0), y + (-3), 3);
            setCellOfStructure(x + (1), y + (-2), 2);
        }
        return cells;
    }

    public Cells setClockGeneratorBig(int x, int y, int pos) {
        if (pos == 0) { //from left
            setCellOfStructure(x + (0), y + (0), 1);
            setCellOfStructure(x + (1), y + (1), 1);
            setCellOfStructure(x + (2), y + (1), 1);
            setCellOfStructure(x + (3), y + (1), 1);
            setCellOfStructure(x + (4), y + (1), 1);
            setCellOfStructure(x + (5), y + (1), 1);
            setCellOfStructure(x + (1), y + (-1), 1);
            setCellOfStructure(x + (2), y + (-1), 1);
            setCellOfStructure(x + (3), y + (-1), 1);
            setCellOfStructure(x + (4), y + (-1), 1);
            setCellOfStructure(x + (5), y + (-1), 1);
            setCellOfStructure(x + (6), y + (0), 1);
            setCellOfStructure(x + (0), y + (1), 0);
            setCellOfStructure(x + (0), y + (2), 0);
            setCellOfStructure(x + (1), y + (2), 0);
            setCellOfStructure(x + (2), y + (2), 0);
            setCellOfStructure(x + (3), y + (2), 0);
            setCellOfStructure(x + (4), y + (2), 0);
            setCellOfStructure(x + (5), y + (2), 0);
            setCellOfStructure(x + (6), y + (2), 0);
            setCellOfStructure(x + (6), y + (1), 0);
            setCellOfStructure(x + (7), y + (1), 0);
            setCellOfStructure(x + (7), y + (0), 0);
            setCellOfStructure(x + (7), y + (-1), 0);
            setCellOfStructure(x + (6), y + (-1), 0);
            setCellOfStructure(x + (6), y + (-2), 0);
            setCellOfStructure(x + (5), y + (-2), 0);
            setCellOfStructure(x + (4), y + (-2), 0);
            setCellOfStructure(x + (3), y + (-2), 0);
            setCellOfStructure(x + (2), y + (-2), 0);
            setCellOfStructure(x + (1), y + (-2), 0);
            setCellOfStructure(x + (0), y + (-2), 0);
            setCellOfStructure(x + (0), y + (-1), 0);
            setCellOfStructure(x + (1), y + (0), 0);
            setCellOfStructure(x + (2), y + (0), 0);
            setCellOfStructure(x + (3), y + (0), 0);
            setCellOfStructure(x + (4), y + (0), 0);
            setCellOfStructure(x + (5), y + (0), 0);
            setCellOfStructure(x + (6), y + (0), 3);
            setCellOfStructure(x + (5), y + (1), 2);
        }
        if (pos == 1) { //from up
            setCellOfStructure(x + (0), y + (0), 1);
            setCellOfStructure(x + (-1), y + (1), 1);
            setCellOfStructure(x + (-1), y + (2), 1);
            setCellOfStructure(x + (-1), y + (3), 1);
            setCellOfStructure(x + (-1), y + (4), 1);
            setCellOfStructure(x + (-1), y + (5), 1);
            setCellOfStructure(x + (0), y + (6), 1);
            setCellOfStructure(x + (1), y + (5), 1);
            setCellOfStructure(x + (1), y + (4), 1);
            setCellOfStructure(x + (1), y + (3), 1);
            setCellOfStructure(x + (1), y + (2), 1);
            setCellOfStructure(x + (1), y + (1), 1);
            setCellOfStructure(x + (-1), y + (5), 2);
            setCellOfStructure(x + (0), y + (6), 3);
            setCellOfStructure(x + (-1), y + (6), 0);
            setCellOfStructure(x + (-1), y + (7), 0);
            setCellOfStructure(x + (0), y + (7), 0);
            setCellOfStructure(x + (1), y + (7), 0);
            setCellOfStructure(x + (1), y + (6), 0);
            setCellOfStructure(x + (0), y + (5), 0);
            setCellOfStructure(x + (0), y + (4), 0);
            setCellOfStructure(x + (0), y + (3), 0);
            setCellOfStructure(x + (0), y + (2), 0);
            setCellOfStructure(x + (0), y + (1), 0);
            setCellOfStructure(x + (-1), y + (0), 0);
            setCellOfStructure(x + (1), y + (0), 0);
            setCellOfStructure(x + (2), y + (0), 0);
            setCellOfStructure(x + (2), y + (1), 0);
            setCellOfStructure(x + (2), y + (2), 0);
            setCellOfStructure(x + (2), y + (3), 0);
            setCellOfStructure(x + (2), y + (4), 0);
            setCellOfStructure(x + (2), y + (5), 0);
            setCellOfStructure(x + (2), y + (6), 0);
            setCellOfStructure(x + (-2), y + (6), 0);
            setCellOfStructure(x + (-2), y + (5), 0);
            setCellOfStructure(x + (-2), y + (4), 0);
            setCellOfStructure(x + (-2), y + (3), 0);
            setCellOfStructure(x + (-2), y + (2), 0);
            setCellOfStructure(x + (-2), y + (1), 0);
            setCellOfStructure(x + (-2), y + (0), 0);
        }
        if (pos == 2) { //from right
            setCellOfStructure(x + (0), y + (0), 1);
            setCellOfStructure(x + (-1), y + (1), 1);
            setCellOfStructure(x + (-2), y + (1), 1);
            setCellOfStructure(x + (-3), y + (1), 1);
            setCellOfStructure(x + (-4), y + (1), 1);
            setCellOfStructure(x + (-5), y + (1), 1);
            setCellOfStructure(x + (-5), y + (0), 1);
            setCellOfStructure(x + (-6), y + (0), 1);
            setCellOfStructure(x + (-4), y + (-1), 1);
            setCellOfStructure(x + (-5), y + (-1), 1);
            setCellOfStructure(x + (-3), y + (-1), 1);
            setCellOfStructure(x + (-2), y + (-1), 1);
            setCellOfStructure(x + (-1), y + (-1), 1);
            setCellOfStructure(x + (-5), y + (0), 0);
            setCellOfStructure(x + (-4), y + (0), 0);
            setCellOfStructure(x + (-3), y + (0), 0);
            setCellOfStructure(x + (-1), y + (0), 0);
            setCellOfStructure(x + (-2), y + (0), 0);
            setCellOfStructure(x + (0), y + (1), 0);
            setCellOfStructure(x + (0), y + (2), 0);
            setCellOfStructure(x + (0), y + (-1), 0);
            setCellOfStructure(x + (0), y + (-2), 0);
            setCellOfStructure(x + (-7), y + (0), 0);
            setCellOfStructure(x + (-7), y + (-1), 0);
            setCellOfStructure(x + (-6), y + (-1), 0);
            setCellOfStructure(x + (-6), y + (-2), 0);
            setCellOfStructure(x + (-5), y + (-2), 0);
            setCellOfStructure(x + (-4), y + (-2), 0);
            setCellOfStructure(x + (-3), y + (-2), 0);
            setCellOfStructure(x + (-2), y + (-2), 0);
            setCellOfStructure(x + (-1), y + (-2), 0);
            setCellOfStructure(x + (-6), y + (1), 0);
            setCellOfStructure(x + (-7), y + (1), 0);
            setCellOfStructure(x + (-5), y + (2), 0);
            setCellOfStructure(x + (-6), y + (2), 0);
            setCellOfStructure(x + (-4), y + (2), 0);
            setCellOfStructure(x + (-3), y + (2), 0);
            setCellOfStructure(x + (-2), y + (2), 0);
            setCellOfStructure(x + (-1), y + (2), 0);
            setCellOfStructure(x + (-6), y + (0), 3);
            setCellOfStructure(x + (-5), y + (-1), 2);
        }
        if (pos == 3) { //from down
            setCellOfStructure(x + (0), y + (0), 1);
            setCellOfStructure(x + (1), y + (-1), 1);
            setCellOfStructure(x + (1), y + (-2), 1);
            setCellOfStructure(x + (1), y + (-3), 1);
            setCellOfStructure(x + (1), y + (-4), 1);
            setCellOfStructure(x + (1), y + (-5), 1);
            setCellOfStructure(x + (0), y + (-6), 1);
            setCellOfStructure(x + (-1), y + (-5), 1);
            setCellOfStructure(x + (-1), y + (-4), 1);
            setCellOfStructure(x + (-1), y + (-3), 1);
            setCellOfStructure(x + (-1), y + (-2), 1);
            setCellOfStructure(x + (-1), y + (-1), 1);
            setCellOfStructure(x + (-1), y + (-6), 0);
            setCellOfStructure(x + (-1), y + (-7), 0);
            setCellOfStructure(x + (0), y + (-7), 0);
            setCellOfStructure(x + (1), y + (-7), 0);
            setCellOfStructure(x + (1), y + (-6), 0);
            setCellOfStructure(x + (2), y + (-6), 0);
            setCellOfStructure(x + (2), y + (-5), 0);
            setCellOfStructure(x + (2), y + (-4), 0);
            setCellOfStructure(x + (2), y + (-3), 0);
            setCellOfStructure(x + (2), y + (-2), 0);
            setCellOfStructure(x + (2), y + (-1), 0);
            setCellOfStructure(x + (2), y + (0), 0);
            setCellOfStructure(x + (1), y + (0), 0);
            setCellOfStructure(x + (-1), y + (0), 0);
            setCellOfStructure(x + (-2), y + (0), 0);
            setCellOfStructure(x + (-2), y + (-2), 0);
            setCellOfStructure(x + (-2), y + (-1), 0);
            setCellOfStructure(x + (-2), y + (-3), 0);
            setCellOfStructure(x + (-2), y + (-4), 0);
            setCellOfStructure(x + (-2), y + (-5), 0);
            setCellOfStructure(x + (-2), y + (-6), 0);
            setCellOfStructure(x + (0), y + (-5), 0);
            setCellOfStructure(x + (0), y + (-4), 0);
            setCellOfStructure(x + (0), y + (-3), 0);
            setCellOfStructure(x + (0), y + (-2), 0);
            setCellOfStructure(x + (0), y + (-1), 0);
            setCellOfStructure(x + (0), y + (-6), 3);
            setCellOfStructure(x + (-1), y + (-5), 2);
        }
        return cells;
    }

    public Cells setElectron(int x, int y, int pos) {
        if (pos == 0) { //from left
            setCellOfStructure(x + (0), y + (0), 2);
            setCellOfStructure(x + (1), y + (0), 3);
        }
        if (pos == 1) { //from up
            setCellOfStructure(x + (0), y + (0), 2);
            setCellOfStructure(x + (0), y + (1), 3);
        }
        if (pos == 2) { //from right
            setCellOfStructure(x + (0), y + (0), 2);
            setCellOfStructure(x + (-1), y + (0), 3);
        }
        if (pos == 3) { //from down
            setCellOfStructure(x + (0), y + (0), 2);
            setCellOfStructure(x + (0), y + (-1), 3);
        }
        return cells;
    }

    public Cells setAND(int x, int y, int pos) {
        if (pos == 0) { //from left
            setCellOfStructure(x + (0), y + (0), 1);
            setCellOfStructure(x + (1), y + (0), 1);
            setCellOfStructure(x + (2), y + (0), 1);
            setCellOfStructure(x + (3), y + (-1), 1);
            setCellOfStructure(x + (4), y + (-2), 1);
            setCellOfStructure(x + (5), y + (-3), 1);
            setCellOfStructure(x + (6), y + (-3), 1);
            setCellOfStructure(x + (7), y + (-3), 1);
            setCellOfStructure(x + (8), y + (-3), 1);
            setCellOfStructure(x + (9), y + (-2), 1);
            setCellOfStructure(x + (10), y + (-2), 1);
            setCellOfStructure(x + (11), y + (-2), 1);
            setCellOfStructure(x + (8), y + (-1), 1);
            setCellOfStructure(x + (7), y + (0), 1);
            setCellOfStructure(x + (6), y + (0), 1);
            setCellOfStructure(x + (6), y + (-1), 1);
            setCellOfStructure(x + (5), y + (0), 1);
            setCellOfStructure(x + (6), y + (1), 1);
            setCellOfStructure(x + (8), y + (1), 1);
            setCellOfStructure(x + (9), y + (2), 1);
            setCellOfStructure(x + (10), y + (2), 1);
            setCellOfStructure(x + (10), y + (3), 1);
            setCellOfStructure(x + (10), y + (1), 1);
            setCellOfStructure(x + (11), y + (2), 1);
            setCellOfStructure(x + (12), y + (-1), 1);
            setCellOfStructure(x + (12), y + (0), 1);
            setCellOfStructure(x + (12), y + (1), 1);
            setCellOfStructure(x + (12), y + (3), 1);
            setCellOfStructure(x + (13), y + (3), 1);
            setCellOfStructure(x + (14), y + (3), 1);
            setCellOfStructure(x + (4), y + (1), 1);
            setCellOfStructure(x + (4), y + (2), 1);
            setCellOfStructure(x + (4), y + (3), 1);
            setCellOfStructure(x + (4), y + (4), 1);
            setCellOfStructure(x + (3), y + (5), 1);
            setCellOfStructure(x + (2), y + (4), 1);
            setCellOfStructure(x + (2), y + (3), 1);
            setCellOfStructure(x + (1), y + (2), 1);
            setCellOfStructure(x + (0), y + (2), 1);
            setCellOfStructure(x + (0), y + (-1), 0);
            setCellOfStructure(x + (1), y + (-1), 0);
            setCellOfStructure(x + (2), y + (-1), 0);
            setCellOfStructure(x + (2), y + (-2), 0);
            setCellOfStructure(x + (3), y + (-2), 0);
            setCellOfStructure(x + (3), y + (-3), 0);
            setCellOfStructure(x + (4), y + (-3), 0);
            setCellOfStructure(x + (4), y + (-4), 0);
            setCellOfStructure(x + (5), y + (-4), 0);
            setCellOfStructure(x + (6), y + (-4), 0);
            setCellOfStructure(x + (7), y + (-4), 0);
            setCellOfStructure(x + (8), y + (-4), 0);
            setCellOfStructure(x + (9), y + (-4), 0);
            setCellOfStructure(x + (9), y + (-3), 0);
            setCellOfStructure(x + (10), y + (-3), 0);
            setCellOfStructure(x + (11), y + (-3), 0);
            setCellOfStructure(x + (12), y + (-3), 0);
            setCellOfStructure(x + (12), y + (-2), 0);
            setCellOfStructure(x + (13), y + (-2), 0);
            setCellOfStructure(x + (13), y + (-1), 0);
            setCellOfStructure(x + (13), y + (0), 0);
            setCellOfStructure(x + (13), y + (1), 0);
            setCellOfStructure(x + (12), y + (2), 0);
            setCellOfStructure(x + (13), y + (2), 0);
            setCellOfStructure(x + (14), y + (2), 0);
            setCellOfStructure(x + (14), y + (4), 0);
            setCellOfStructure(x + (13), y + (4), 0);
            setCellOfStructure(x + (12), y + (4), 0);
            setCellOfStructure(x + (11), y + (3), 0);
            setCellOfStructure(x + (11), y + (4), 0);
            setCellOfStructure(x + (10), y + (4), 0);
            setCellOfStructure(x + (9), y + (4), 0);
            setCellOfStructure(x + (9), y + (3), 0);
            setCellOfStructure(x + (8), y + (3), 0);
            setCellOfStructure(x + (8), y + (2), 0);
            setCellOfStructure(x + (7), y + (2), 0);
            setCellOfStructure(x + (7), y + (1), 0);
            setCellOfStructure(x + (6), y + (2), 0);
            setCellOfStructure(x + (5), y + (1), 0);
            setCellOfStructure(x + (5), y + (2), 0);
            setCellOfStructure(x + (5), y + (3), 0);
            setCellOfStructure(x + (5), y + (4), 0);
            setCellOfStructure(x + (5), y + (5), 0);
            setCellOfStructure(x + (4), y + (5), 0);
            setCellOfStructure(x + (2), y + (5), 0);
            setCellOfStructure(x + (4), y + (6), 0);
            setCellOfStructure(x + (3), y + (6), 0);
            setCellOfStructure(x + (2), y + (6), 0);
            setCellOfStructure(x + (1), y + (5), 0);
            setCellOfStructure(x + (1), y + (4), 0);
            setCellOfStructure(x + (1), y + (3), 0);
            setCellOfStructure(x + (0), y + (3), 0);
            setCellOfStructure(x + (0), y + (1), 0);
            setCellOfStructure(x + (1), y + (1), 0);
            setCellOfStructure(x + (2), y + (1), 0);
            setCellOfStructure(x + (2), y + (2), 0);
            setCellOfStructure(x + (3), y + (2), 0);
            setCellOfStructure(x + (3), y + (3), 0);
            setCellOfStructure(x + (3), y + (4), 0);
            setCellOfStructure(x + (3), y + (1), 0);
            setCellOfStructure(x + (3), y + (0), 0);
            setCellOfStructure(x + (4), y + (0), 0);
            setCellOfStructure(x + (4), y + (-1), 0);
            setCellOfStructure(x + (5), y + (-1), 0);
            setCellOfStructure(x + (5), y + (-2), 0);
            setCellOfStructure(x + (6), y + (-2), 0);
            setCellOfStructure(x + (7), y + (-2), 0);
            setCellOfStructure(x + (7), y + (-1), 0);
            setCellOfStructure(x + (8), y + (-2), 0);
            setCellOfStructure(x + (8), y + (0), 0);
            setCellOfStructure(x + (8), y + (0), 0);
            setCellOfStructure(x + (9), y + (0), 0);
            setCellOfStructure(x + (9), y + (-1), 0);
            setCellOfStructure(x + (9), y + (1), 0);
            setCellOfStructure(x + (10), y + (0), 0);
            setCellOfStructure(x + (10), y + (-1), 0);
            setCellOfStructure(x + (11), y + (-1), 0);
            setCellOfStructure(x + (11), y + (0), 0);
            setCellOfStructure(x + (11), y + (1), 0);
            setCellOfStructure(x + (1), y + (0), 3);
            setCellOfStructure(x + (1), y + (2), 3);
            setCellOfStructure(x + (0), y + (0), 2);
            setCellOfStructure(x + (0), y + (2), 2);
        }
        if (pos == 1) { //from up

        }
        if (pos == 2) { //from right

        }
        if (pos == 3) { //from down

        }
        return cells;
    }

    public Cells setOR(int x, int y, int pos) {
        if (pos == 0) { //from left
            setCellOfStructure(x + (0), y + (0), 1);
            setCellOfStructure(x + (1), y + (0), 1);
            setCellOfStructure(x + (2), y + (0), 1);
            setCellOfStructure(x + (0), y + (2), 1);
            setCellOfStructure(x + (0), y + (2), 1);
            setCellOfStructure(x + (1), y + (2), 1);
            setCellOfStructure(x + (2), y + (2), 1);
            setCellOfStructure(x + (3), y + (-1), 1);
            setCellOfStructure(x + (4), y + (-1), 1);
            setCellOfStructure(x + (3), y + (3), 1);
            setCellOfStructure(x + (4), y + (3), 1);
            setCellOfStructure(x + (5), y + (2), 1);
            setCellOfStructure(x + (5), y + (1), 1);
            setCellOfStructure(x + (4), y + (1), 1);
            setCellOfStructure(x + (5), y + (0), 1);
            setCellOfStructure(x + (6), y + (1), 1);
            setCellOfStructure(x + (7), y + (1), 1);
            setCellOfStructure(x + (8), y + (1), 1);
            setCellOfStructure(x + (0), y + (0), 2);
            setCellOfStructure(x + (0), y + (2), 2);
            setCellOfStructure(x + (1), y + (0), 3);
            setCellOfStructure(x + (1), y + (2), 3);
            setCellOfStructure(x + (0), y + (-1), 0);
            setCellOfStructure(x + (1), y + (-1), 0);
            setCellOfStructure(x + (2), y + (-1), 0);
            setCellOfStructure(x + (0), y + (1), 0);
            setCellOfStructure(x + (1), y + (1), 0);
            setCellOfStructure(x + (2), y + (1), 0);
            setCellOfStructure(x + (3), y + (1), 0);
            setCellOfStructure(x + (3), y + (0), 0);
            setCellOfStructure(x + (4), y + (0), 0);
            setCellOfStructure(x + (3), y + (2), 0);
            setCellOfStructure(x + (4), y + (2), 0);
            setCellOfStructure(x + (0), y + (3), 0);
            setCellOfStructure(x + (1), y + (3), 0);
            setCellOfStructure(x + (2), y + (3), 0);
            setCellOfStructure(x + (2), y + (4), 0);
            setCellOfStructure(x + (3), y + (4), 0);
            setCellOfStructure(x + (4), y + (4), 0);
            setCellOfStructure(x + (5), y + (4), 0);
            setCellOfStructure(x + (5), y + (3), 0);
            setCellOfStructure(x + (6), y + (3), 0);
            setCellOfStructure(x + (6), y + (2), 0);
            setCellOfStructure(x + (7), y + (2), 0);
            setCellOfStructure(x + (8), y + (2), 0);
            setCellOfStructure(x + (8), y + (0), 0);
            setCellOfStructure(x + (7), y + (0), 0);
            setCellOfStructure(x + (6), y + (0), 0);
            setCellOfStructure(x + (6), y + (-1), 0);
            setCellOfStructure(x + (5), y + (-1), 0);
            setCellOfStructure(x + (2), y + (-2), 0);
            setCellOfStructure(x + (3), y + (-2), 0);
            setCellOfStructure(x + (4), y + (-2), 0);
            setCellOfStructure(x + (5), y + (-2), 0);
        }
        if (pos == 1) { //from up

        }
        if (pos == 2) { //from right

        }
        if (pos == 3) { //from down

        }
        return cells;
    }

}
/*
 public Cells ExampleFunction(int x, int y, int pos){
 if (pos == 0) { //from left

 }
 if (pos == 1) { //from up

 }
 if (pos == 2) { //from right

 }
 if (pos == 3) { //from down

 }
 return cells;
 }
 */
