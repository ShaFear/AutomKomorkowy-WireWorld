/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automaton.rules;

import cells.Cells;
import automaton.neighbourhood.*;
import cells.cell.Cell;
import cells.cell.Conductor;
import cells.cell.Empty;
import cells.cell.Head;
import cells.cell.Tail;
import java.awt.Color;

/**
 *
 * @author shafe_000
 */
public class WireWorld implements AutomatonStates {

    final private Neighbourhood nbh;
    private boolean isWrapped;
    
    public WireWorld() {
        this.nbh = new Neighbourhood(new WrappedNeighbourhood());
        isWrapped = true;
    }

    public WireWorld(NeighbourhoodStates nbh) {
        this.nbh = new Neighbourhood(nbh);
        if(this.nbh == new Neighbourhood(new NormalNeighbourhood())) isWrapped = false;
    }

    @Override
    public Cell getCellStateByRules(Cells cells, int x, int y) {
        switch (cells.getCell(x, y).getValue()) {
            case 0: {
                return new Empty();
            }
            case 1: {
                if ((nbh.getNumberOfNeighbourhoodByState(cells, x, y, 3) == 1) || (nbh.getNumberOfNeighbourhoodByState(cells, x, y, 3) == 2)) {
                    return new Head();
                } else {
                    return new Conductor();
                }
            }
            case 2: {
                return new Conductor();
            }
            case 3: {
                return new Tail();
            }
            default: {
                return new Conductor();
            }
        }
    }

    @Override
    public Color[] getColors() {
        Color colors[] = new Color[200];
        for (int j = 0; j < 200; j++) {
            colors[j] = Color.BLACK;
        }
        colors[1] = Color.YELLOW;
        colors[2] = Color.RED;
        colors[3] = Color.BLUE;
        return colors;
    }

    @Override
    public boolean getIsWrapped() {
        return isWrapped;
    }

}
