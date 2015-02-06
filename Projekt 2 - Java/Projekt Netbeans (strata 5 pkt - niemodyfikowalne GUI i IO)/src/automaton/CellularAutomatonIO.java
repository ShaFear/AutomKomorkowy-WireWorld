/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automaton;

import automaton.rules.AutomatonRules;
import windows.GUI;
import automaton.neighbourhood.NormalNeighbourhood;
import automaton.rules.WireWorld;
import automaton.rules.Life;
import cells.Cells;
import cells.CellsStructures;
import cells.cell.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author shafe_000
 */
public class CellularAutomatonIO {

    public static void openFromFile(File file) throws IOException, CellsIOException, RulesIOException {
        AutomatonRules rules = new AutomatonRules(new WireWorld());
        BufferedReader in = new BufferedReader(new FileReader(file));
        String s;
        Cells cells = null;
        int lines = 0;
        boolean isLife = false;
        boolean isWrapped = false;
        if ((s = in.readLine()) != null) {
            String result[] = s.split(" ");
            if (result.length < 2) {
                throw new CellsIOException("Błędny format pliku:\n\t W pierwszej linii nie ma informacji o rozmiarach siatki. \n");
            }
            int x = Integer.parseInt(result[0]);
            int y = Integer.parseInt(result[1]);
            if ((x < 2) || (y < 2)) {
                throw new CellsIOException("Błędny format pliku:\n\t Wymiary siatki nie mogą być mniejsze niż 1x1. \n");
            }
            if ((x >= 1000) || (y >= 1000)) {
                throw new CellsIOException("Błędny format pliku:\n\t Wymiary siatki nie mogą być większe niż 999x999. \n");
            }
            cells = new Cells(x, y);
            lines++;
        }
        if ((s = in.readLine()) != null) {
            String result[] = s.split(" ");
            if (result.length < 2) {
                throw new RulesIOException("Błędny format pliku:\n\t W drugiej linii nie ma informacji o zasadach automatu. \n");
            }
            String info = "Błędny format pliku:\n\t W drugiej linii występują nieprawidłowe informacje o zasadach automatu. \n";
            if (null != result[0]) {
                switch (result[0]) {
                    case "Life":
                        if (null != result[1]) {
                            isLife = true;
                            switch (result[1]) {
                                case "normal":
                                    rules = new AutomatonRules(new Life(new NormalNeighbourhood()));
                                    break;
                                case "wrapped":
                                    rules = new AutomatonRules(new Life());
                                    isWrapped = true;
                                    break;
                                default:
                                    throw new RulesIOException(info);
                            }
                        }
                        break;
                    case "Wireworld":
                        if (null != result[1]) {
                            switch (result[1]) {
                                case "normal":
                                    rules = new AutomatonRules(new WireWorld(new NormalNeighbourhood()));
                                    break;
                                case "wrapped":
                                    rules = new AutomatonRules(new WireWorld());
                                    isWrapped = true;
                                    break;
                                default:
                                    throw new RulesIOException(info);
                            }
                        }
                        break;
                    default:
                        throw new RulesIOException(info);
                }
            }
            lines++;
        }

        while ((s = in.readLine()) != null) {
            readLineOfCell( cells,  lines,  s,  isLife,  isWrapped);
            lines++;
        }
        
        if (lines < 2) {
            throw new CellsIOException("Błędny format pliku:\n\t Plik nie zawiera wymaganych informacji!\n");
        }

        new GUI(rules, cells);
    }

    public static void writeToFile(File file, boolean isLife, boolean isWrapped, Cells cells) throws IOException {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
            out.write(cells.getX() + " " + cells.getY() + "\n");
            if (isLife) {
                out.write("Life ");
            } else {
                out.write("Wireworld ");
            }
            if (isWrapped) {
                out.write("wrapped\n");
            } else {
                out.write("normal\n");
            }
            for (int j = 0; j < cells.getY(); j++) {
                for (int i = 0; i < cells.getX(); i++) {
                    if (isLife) {
                        if (cells.getCell(i + 1, j + 1).getValue() == 1) {
                            out.write("ALIFE ");
                            out.write((i + 1) + " " + (j + 1) + "\n");
                        }
                    } else {
                        if (cells.getCell(i + 1, j + 1).getValue() == 1) {
                            out.write("CONDUCTOR ");
                            out.write((i + 1) + " " + (j + 1) + "\n");
                        } else if (cells.getCell(i + 1, j + 1).getValue() == 2) {
                            out.write("TAIL ");
                            out.write((i + 1) + " " + (j + 1) + "\n");
                        } else if (cells.getCell(i + 1, j + 1).getValue() == 3) {
                            out.write("HEAD ");
                            out.write((i + 1) + " " + (j + 1) + "\n");
                        }
                    }
                }
            }
        }
    }

    private static Cells readLineOfCell(Cells cells, int lines, String s, boolean isLife, boolean isWrapped) throws CellsIOException {
        String err = "Błędny format pliku:\n\t W linii nr. " + (lines + 1) + " jest błąd! \n";
        String result[] = s.split(" ");
        if (result.length < 3) {
            throw new CellsIOException(err);
        }

        int posX = Integer.parseInt(result[1]);
        int posY = Integer.parseInt(result[2]);

        if ((posX < 1) || (posY < 1)) {
            throw new CellsIOException(err);
        }
        if (posX > cells.getX()) {
            throw new CellsIOException(err);
        }
        if (posY > cells.getY()) {
            throw new CellsIOException(err);
        }

        if (isLife) {
            if (null != result[0]) {
                switch (result[0]) {
                    case "DEAD":
                        cells.setCell(posX, posY, new Cell());
                        break;
                    case "ALIFE":
                        cells.setCell(posX, posY, new Alife());
                        break;
                    default:
                        throw new CellsIOException(err);
                }
            }
        } else {
            if (null != result[0]) {
                switch (result[0]) {
                    case "EMPTY":
                        cells.setCell(posX, posY, new Cell());
                        break;
                    case "CONDUCTOR":
                        cells.setCell(posX, posY, new Conductor());
                        break;
                    case "TAIL":
                        cells.setCell(posX, posY, new Tail());
                        break;
                    case "HEAD":
                        cells.setCell(posX, posY, new Head());
                        break;
                    case "AND": {
                        CellsStructures cs = new CellsStructures(cells, isWrapped);
                        cells = cs.setAND(posX, posY, 0);
                        break;
                    }
                    case "OR": {
                        CellsStructures cs = new CellsStructures(cells, isWrapped);
                        cells = cs.setOR(posX, posY, 0);
                        break;
                    }
                    case "DIODE": {
                        CellsStructures cs = new CellsStructures(cells, isWrapped);
                        cells = cs.setDiode(posX, posY, 0);
                        break;
                    }
                    case "ELECTRON": {
                        CellsStructures cs = new CellsStructures(cells, isWrapped);
                        cells = cs.setElectron(posX, posY, 0);
                        break;
                    }
                    case "CLOCKS": {
                        CellsStructures cs = new CellsStructures(cells, isWrapped);
                        cells = cs.setClockGeneratorSmall(posX, posY, 0);
                        break;
                    }
                    case "CLOCKB": {
                        CellsStructures cs = new CellsStructures(cells, isWrapped);
                        cells = cs.setClockGeneratorBig(posX, posY, 0);
                        break;
                    }
                    default:
                        throw new CellsIOException(err);
                }
            }
        }
        return cells;
    }
   
}
