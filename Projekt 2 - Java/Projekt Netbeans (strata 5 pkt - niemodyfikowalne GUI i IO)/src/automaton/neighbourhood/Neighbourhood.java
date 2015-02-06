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
public class Neighbourhood implements NeighbourhoodStates{
    private final NeighbourhoodStates state;
    
    public Neighbourhood(NeighbourhoodStates state){
        this.state = state;
    }
    
    @Override
    public int getNumberOfNeighbourhoodByState(Cells cells, int x, int y, int state) {
        return this.state.getNumberOfNeighbourhoodByState(cells, x, y, state);
    }
    
}
