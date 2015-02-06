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
public class NormalNeighbourhood implements NeighbourhoodStates{

    @Override
    public int getNumberOfNeighbourhoodByState(Cells cells, int x, int y, int state) {
        int number = 0;
        if(x>1)if(cells.getCell(x-1,y).getValue()==state) number++;
        if(x<cells.getX())if(cells.getCell(x+1,y).getValue()==state) number++;
        if(y>1)if(cells.getCell(x,y-1).getValue()==state) number++;
        if(y<cells.getY())if(cells.getCell(x,y+1).getValue()==state) number++;
        if((y>1)&&(x>1))if(cells.getCell(x-1,y-1).getValue()==state) number++;
        if((y<cells.getY())&&(x<cells.getX()))if(cells.getCell(x+1,y+1).getValue()==state) number++;
        if((x>1)&&(y<cells.getY()))if(cells.getCell(x-1,y+1).getValue()==state) number++;
        if((y>1)&&(x<cells.getX()))if(cells.getCell(x+1,y-1).getValue()==state) number++;
        return number;
    }
    
}
