/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyautomatonexample;

import automaton.neighbourhood.Neighbourhood;
import automaton.neighbourhood.NormalNeighbourhood;
import automaton.neighbourhood.WrappedNeighbourhood;
import automaton.rules.AutomatonStates;
import cells.Cells;
import cells.cell.Cell;
import java.awt.Color;
import javax.lang.model.util.AbstractAnnotationValueVisitor6;

/**
 *
 * @author shafe_000
 */
public class RulesOfWar implements AutomatonStates {

    @Override
    public boolean getIsWrapped() {
        return true;
    }

    @Override
    public Cell getCellStateByRules(Cells cells, int x, int y) {
        Neighbourhood ngh = new Neighbourhood(new NormalNeighbourhood());
        int nB = ngh.getNumberOfNeighbourhoodByState(cells, x, y, 2);
        int nY = ngh.getNumberOfNeighbourhoodByState(cells, x, y, 3);
        int nM = ngh.getNumberOfNeighbourhoodByState(cells, x, y, 4);
        int nR = ngh.getNumberOfNeighbourhoodByState(cells, x, y, 1);
        int nW = ngh.getNumberOfNeighbourhoodByState(cells, x, y, 0);

        switch (cells.getCell(x, y).getValue()) {
            case 1: return new PowerRed();
            case 2: return new PowerBlue();
            case 3: return new Empty();
            case 4: return new Empty();
            case 0: {if(nY + nM > 1){
                if(nY > nM ) return new Red();
                else return new Blue();
            }
            if(nR + nB > 1){
                if(nR > nB ) return new Empty();
                else return new Blue();
            }}
        }
        return new Empty();
    }


@Override
        public Color[] getColors() {
        Color c[] = new Color[5];
        c[0] = Color.WHITE;
        c[1] = Color.BLUE;
        c[2] = Color.RED;
        c[3] = Color.cyan;
        c[4] = Color.ORANGE;
        return c;
    }

}
