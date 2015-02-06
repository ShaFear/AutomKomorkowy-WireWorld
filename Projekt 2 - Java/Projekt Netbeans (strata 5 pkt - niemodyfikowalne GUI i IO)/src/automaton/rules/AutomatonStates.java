/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package automaton.rules;

import cells.Cells;
import cells.cell.Cell;
import java.awt.Color;

/**
 *
 * @author shafe_000
 */
public interface AutomatonStates {
    public boolean getIsWrapped();
    public Cell getCellStateByRules(Cells cells, int x, int y);
    public Color []getColors();
}
