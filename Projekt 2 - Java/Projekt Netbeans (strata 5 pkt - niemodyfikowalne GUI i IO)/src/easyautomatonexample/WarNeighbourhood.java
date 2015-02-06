/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package easyautomatonexample;

import automaton.neighbourhood.*;
import cells.Cells;

/**
 *
 * @author shafe_000
 */
public class WarNeighbourhood implements NeighbourhoodStates{

    @Override
    public int getNumberOfNeighbourhoodByState(Cells cells, int x, int y, int state) {
        int number = 0;
        int xm1 = x-1;
        int xp1 = x+1;
        int ym1 = y-1;
        int yp1 = y+1;
        
        if(xm1<1) xm1 = cells.getX();
        if(ym1<1) ym1 = cells.getY();
        if(xp1>cells.getX()) xp1 = xp1 - cells.getX();
        if(yp1>cells.getY()) yp1 = yp1 - cells.getY();
        
        
        if(cells.getCell(xm1,y).getValue()==state) number++;
        if(cells.getCell(xp1,y).getValue()==state) number++;
        if(cells.getCell(x,ym1).getValue()==state) number++;
        if(cells.getCell(x,yp1).getValue()==state) number++;

        
        return number;
    }
    
}
