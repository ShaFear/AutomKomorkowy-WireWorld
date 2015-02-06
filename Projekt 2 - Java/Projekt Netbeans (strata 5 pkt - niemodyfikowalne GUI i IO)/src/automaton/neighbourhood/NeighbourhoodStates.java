/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package automaton.neighbourhood;

import cells.Cells;

/**
 *
 * @author shafe_000
 */
public interface NeighbourhoodStates {
    public int getNumberOfNeighbourhoodByState(Cells cells, int x, int y, int state);
}
