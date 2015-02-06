/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package windows;

import cells.Cells;
import cells.CellsStructures;
import automaton.rules.AutomatonRules;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 *
 * @author shafe_000
 */
public class Visualization extends JPanel implements MouseListener, MouseMotionListener {

    Rectangle2D[][] cellsRectangle;
    private final AutomatonRules r;
    private static int SIZE;
    private Cells cells;
    private final int pointX;
    private final int pointY;
    private int modX;
    private int modY;
    private int visibleLeft;
    private int visibleRight;
    private int visibleUp;
    private int visibleDown;
    private int value;
    private final Dimension windowDimension;
    private int structurePosition;
    private boolean isCreateMod = false;
    
    public void setCreateMod(boolean isCM){
        isCreateMod = isCM;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getSIZE() {
        return SIZE;
    }

    public Cells getCells(){
        return cells;
    }
    
    public Visualization(Cells cellsx, int pointX, int pointY, AutomatonRules rules, Dimension windowDimension) {
        value = 0;
        this.windowDimension = windowDimension;
        modX = 0;
        modY = 0;
        structurePosition = 0;
        r = rules;

        this.cells = cellsx;

        visibleLeft = 0;
        visibleRight = cells.getX();
        visibleUp = 0;
        visibleDown = cells.getY();

        this.pointY = pointY;

        this.pointX = pointX;
        int max;
        if (cells.getX() > cells.getY()) {
            max = cells.getX();
        } else {
            max = cells.getY();
        }
        if (max
                < 10) {
            SIZE = 50;
        } else if (max
                < 25) {
            SIZE = 25;
        } else {
            SIZE = 15;
        }

        this.setPreferredSize(
                new Dimension(100, 100));
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        boolean first = true;
        cellsRectangle = new Rectangle2D[cells.getX()][cells.getY()];
        for (int j = 0; j < cells.getY(); j++) {
            for (int i = 0; i < cells.getX(); i++) {
                if ((((SIZE * i + pointX + i + modX) >= pointX) && ((SIZE * j + pointY + j + modY) >= pointY))
                        && (((SIZE * i + pointX + i + modX + SIZE) <= windowDimension.getWidth() - 25) && ((SIZE * j + pointY + j + modY + SIZE) <= windowDimension.height - 75))) {
                    g2.setPaint(Color.GRAY);
                    cellsRectangle[i][j] = new Rectangle2D.Double(SIZE * i + pointX + i + modX, SIZE * j + pointY + j + modY, SIZE, SIZE);
                    if (first) {
                        visibleUp = j;
                        visibleLeft = i;
                        first = false;
                    }
                    visibleRight = i + 1;
                    visibleDown = j + 1;
                    g2.draw(cellsRectangle[i][j]);
                    g2.setPaint(r.getColors()[cells.getCell(i + 1, j + 1).getValue()]);
                    g2.fill(cellsRectangle[i][j]);
                }

            }
        }
    }

    public void RefreshCells(Cells cellsx) {
        cells = cellsx;
        this.paintComponent(this.getGraphics());
    }

    public void setMods(int x, int y) {
        modX = x;
        modY = y;
        this.RefreshCells(cells);
    }

    public void mouseActionInput(MouseEvent me) {
        double X = me.getX();
        double Y = me.getY();

        if ((me.getButton() == MouseEvent.BUTTON1) /*&& jCheckBoxEmpty.isVisible()*/) {
            for (int i = visibleUp; i < visibleDown; i++) {
                for (int j = visibleLeft; j < visibleRight; j++) {
                    double xPos = cellsRectangle[j][i].getX();
                    double Pos = cellsRectangle[j][i].getY();
                    if ((X >= xPos) && (X <= (xPos + SIZE)) && (Y >= Pos) && (Y <= (Pos + SIZE))) {
                        //Help for making new structures.
                        if(isCreateMod)System.err.println("setCellOfStructure( x + (" + (j - cells.getX() / 2) + "), y + (" + (i - cells.getY() / 2) + "), " + value + ");");
                        if (value < 4) {
                            cells.setCell(j + 1, i + 1, value);
                        } else {
                            if (value == 4) {
                                cells = new CellsStructures(cells, r.getIsWrapped()).setDiode(j + 1, i + 1, structurePosition);
                            } else if (value == 5) {
                                cells = new CellsStructures(cells, r.getIsWrapped()).setClockGeneratorSmall(j + 1, i + 1, structurePosition);
                            } else if (value == 6) {
                                cells = new CellsStructures(cells, r.getIsWrapped()).setClockGeneratorBig(j + 1, i + 1, structurePosition);
                            } else if (value == 7) {
                                cells = new CellsStructures(cells, r.getIsWrapped()).setElectron(j + 1, i + 1, structurePosition);
                            } else if (value == 8) {
                                cells = new CellsStructures(cells, r.getIsWrapped()).setAND(j + 1, i + 1, structurePosition);
                            } else if (value == 9) {
                                cells = new CellsStructures(cells, r.getIsWrapped()).setOR(j + 1, i + 1, structurePosition);
                            }
                        }
                        this.RefreshCells(cells);
                        break;
                    }
                }
            }

        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        mouseActionInput(me);
    }

    @Override
    public void mousePressed(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if(!isCreateMod)
        mouseActionInput(me);
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        if ((me.getX() < pointX) || (me.getX() > windowDimension.width - 25) || (me.getY() > windowDimension.height - 75)) {
            setMouse(false);
        }
    }

    public void setMouse(boolean state) {
        if (state) {
            addMouseListener(this);
            addMouseMotionListener(this);
        } else {
            removeMouseListener(this);
            removeMouseMotionListener(this);
        }
    }

    @Override
    public void mouseDragged(MouseEvent me) {
    }

    public void setStructurePosition(int pos) {
        structurePosition = pos;
    }
}
