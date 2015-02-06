/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package automaton.rules;

import automaton.rules.AutomatonStates;
import cells.Cells;
import cells.cell.Cell;
import java.awt.Color;

/**
 *
 * @author shafe_000
 */
public class AutomatonRules implements AutomatonStates {
    private final AutomatonStates state;
    
    public AutomatonRules(AutomatonStates rules){
        this.state = rules;
    }
    
    public AutomatonStates getAutomatonState(){
        return state;
    }
    
    @Override
    public Cell getCellStateByRules(Cells cells, int x, int y) {
        return state.getCellStateByRules(cells, x, y);
    }

    @Override
    public Color[] getColors() {
        return state.getColors();
    }

    @Override
    public boolean getIsWrapped() {
        return state.getIsWrapped();
    }
    
}
