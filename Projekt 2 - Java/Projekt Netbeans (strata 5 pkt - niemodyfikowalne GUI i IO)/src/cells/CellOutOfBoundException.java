/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cells;

/**
 *
 * @author shafe_000
 */
public class CellOutOfBoundException extends IndexOutOfBoundsException {

    private final int ver;
    private final int hor;

    public CellOutOfBoundException(int ver, int hor, String string) {
        super(string);
        this.ver = ver;
        this.hor = hor;
    }

    public int getHor() {
        return hor;
    }

    public int getVer() {
        return ver;
    }

}
