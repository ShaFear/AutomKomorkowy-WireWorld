/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automaton.rules;

import cells.Cells;
import automaton.neighbourhood.*;
import cells.cell.Alife;
import cells.cell.Cell;
import cells.cell.Dead;
import java.awt.Color;

/**
 *
 * @author shafe_000
 */
public class Life implements AutomatonStates {

    final private Neighbourhood nbh;
    private boolean isWrapped;

    public Life() {
        this.nbh = new Neighbourhood(new WrappedNeighbourhood());
        isWrapped = true;
    }

    public Life(NeighbourhoodStates nbh) {
        this.nbh = new Neighbourhood(nbh);
        if(this.nbh == new Neighbourhood(new NormalNeighbourhood())) isWrapped = false;
    }

    @Override
    public Cell getCellStateByRules(Cells cells, int x, int y) {
        switch (cells.getCell(x, y).getValue()) {
            case 0: {
                if (nbh.getNumberOfNeighbourhoodByState(cells, x, y, 1) == 3) {
                    return new Alife();
                } else {
                    return new Dead();
                }
            }
            case 1: {
                if (nbh.getNumberOfNeighbourhoodByState(cells, x, y, 1) == 2) {
                    return new Alife();
                } else if (nbh.getNumberOfNeighbourhoodByState(cells, x, y, 1) == 3) {
                    return new Alife();
                } else {
                    return new Dead();
                }
            }
            default: {
                return new Dead();
            }
        }
    }

    @Override
    public Color[] getColors() {
        Color colors[] = new Color[200];
        for (int j = 0; j < 200; j++) {
            colors[j] = Color.WHITE;
        }
        colors[1] = Color.BLACK;
        return colors;
    }

    @Override
    public boolean getIsWrapped() {
        return isWrapped;
    }

}
