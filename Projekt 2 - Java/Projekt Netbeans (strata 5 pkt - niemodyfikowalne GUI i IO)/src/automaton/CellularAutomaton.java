/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automaton;

import automaton.rules.AutomatonRules;
import automaton.rules.WireWorld;
import automaton.rules.AutomatonStates;
import cells.Cells;
import cells.cell.Cell;
import java.util.Observable;

/**
 *
 * @author shafe_000
 */
public class CellularAutomaton {

    private Cells cells;
    private AutomatonRules rules;

    public CellularAutomaton() {
        this.cells = new Cells();
        rules = new AutomatonRules(new WireWorld());
    }

    public CellularAutomaton(Cells cells) {
        this.cells = cells;
        rules = new AutomatonRules(new WireWorld());
    }

    public void generate() {
        Cells tmp = new Cells(cells.getX(), cells.getY());
        Cell newValue;
        for (int j = 1; j <= cells.getY(); j++) {
            for (int i = 1; i <= cells.getX(); i++) {
                newValue = rules.getCellStateByRules(cells, i, j);
                tmp.setCell(i, j, newValue);
            }
        }
        cells = tmp;
    }

    public void setRules(AutomatonStates state) {
        rules = new AutomatonRules(state);
    }

    public Cells getCells() {
        return cells;
    }

}
