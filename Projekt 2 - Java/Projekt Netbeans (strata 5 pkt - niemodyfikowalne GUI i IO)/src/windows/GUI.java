/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package windows;

import automaton.CellularAutomatonIO;
import automaton.rules.AutomatonRules;
import cells.*;
import automaton.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author shafe_000
 */
public class GUI extends javax.swing.JFrame {

    private Cells cells;
    private final Visualization draw;
    private final AutomatonRules rules;
    private Cells[] generatedCells;
    private int generations;
    private int actualGeneration;
    private Timer autoplayTimer;
    private boolean isCreateMod = false;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jButtonEditActualGeneration;
    private javax.swing.JButton jButtonEreaseElectrons;
    private javax.swing.JButton jButtonGenerate;
    private javax.swing.JButton jButtonJumpToGeneration;
    private javax.swing.JButton jButtonNext;
    private javax.swing.JButton jButtonPlay;
    private javax.swing.JButton jButtonPrevious;
    private javax.swing.JCheckBox jCheckBoxAND;
    private javax.swing.JCheckBox jCheckBoxConductor;
    private javax.swing.JCheckBox jCheckBoxEmpty;
    private javax.swing.JCheckBox jCheckBoxHead;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemConductor;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemEmpty;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemHead;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemTail;
    private javax.swing.JCheckBox jCheckBoxOR;
    private javax.swing.JCheckBox jCheckBoxStructure;
    private javax.swing.JCheckBox jCheckBoxTail;
    private javax.swing.JComboBox jComboBoxStructurePosition;
    private javax.swing.JComboBox jComboBoxStructureSwitch;
    private javax.swing.JLabel jLabelShowedGeneration;
    private javax.swing.JLabel jLabelTools;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemAbout;
    private javax.swing.JMenuItem jMenuItemNew;
    private javax.swing.JMenuItem jMenuItemWrite;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelDesignAndGenerate;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollBar jScrollBarHorizontal;
    private javax.swing.JScrollBar jScrollBarVertical;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSpinner jSpinnerHowManyToGenerate;
    private javax.swing.JSpinner jSpinnerJumpToGeneration;
    // End of variables declaration//GEN-END:variables

    public GUI(AutomatonRules r, Cells cellsx) {
        
         
        isCreateMod = false; // set true, if You want help with making new structures, it will write out special code lines You can copy to structures.
        
        if(isCreateMod){
         for(int j=1; j<=cellsx.getX(); j++)
         for(int i=1; i<=cellsx.getY(); i++)
         cellsx.setCell(j, i, 3);}
         
        cells = cellsx;
        rules = r;
        actualGeneration = 0;

        setSize(500 * cells.getX(), 500 * cellsx.getY());

        draw = new Visualization(cells, 250, 5, rules, new Dimension(1024, 600));
        draw.setCreateMod(isCreateMod);
        add(draw);
        setVisible(true);
        initComponents();
        initTimer();
        setVisibleEditor();
        jSpinnerHowManyToGenerate.setValue(50);

        buttonGroup1.add(jCheckBoxHead);
        buttonGroup1.add(jCheckBoxTail);
        buttonGroup1.add(jCheckBoxEmpty);
        buttonGroup1.add(jCheckBoxConductor);
        buttonGroup1.add(jCheckBoxAND);
        buttonGroup1.add(jCheckBoxOR);
        buttonGroup1.add(jCheckBoxStructure);

        //centering window
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);
    }

    private void setVisibleEditor() {
        jProgressBar1.setVisible(true);
        jProgressBar1.paint(jProgressBar1.getGraphics());
        jPanel4.setVisible(true);
        jPanel3.setVisible(false);
        if (rules.getAutomatonState().getColors()[0] == Color.WHITE) {
            jComboBoxStructurePosition.setVisible(false);
            jCheckBoxStructure.setVisible(false);
            jCheckBoxHead.setVisible(false);
            jCheckBoxTail.setVisible(false);
            jButtonEreaseElectrons.setVisible(false);
            jComboBoxStructureSwitch.setVisible(false);
            jCheckBoxEmpty.setText("Martwa komórka");
            jCheckBoxConductor.setText("Żywa komórka");
            jCheckBoxAND.setVisible(false);
            jCheckBoxOR.setVisible(false);
        }

    }

    private void setVisiblePlayer() {
        jPanel4.setVisible(false);
        jPanel3.setVisible(true);
        jButtonPlay.setText("Start");
    }

    private void initTimer() {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (actualGeneration < generations) {
                    actualGeneration++;
                    draw.RefreshCells(generatedCells[actualGeneration]);
                    jLabelShowedGeneration.setText("WYŚWIETLANA GENERACJA: " + actualGeneration);
                }
            }
        };
        autoplayTimer = new Timer(250, listener);
    }

    private void jButtonGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenerateActionPerformed
        draw.setMouse(false);
        generations = (Integer) jSpinnerHowManyToGenerate.getValue();
        CellularAutomaton cellularAutomaton = new CellularAutomaton(cells);
        cellularAutomaton.setRules(rules);
        if (generations > 999) {
            JOptionPane.showMessageDialog(null, "Generacji musi być mniej niż 1000!");
            jProgressBar1.setValue(0);
        } else if (generations > 1) {
            try {
                generatedCells = new Cells[generations + 1];
                generatedCells[0] = cells;
                for (int j = 1; j < generations + 1; j++) {
                    cellularAutomaton.generate();
                    cells = cellularAutomaton.getCells();
                    generatedCells[j] = cells;
                    setVisiblePlayer();
                    jProgressBar1.setVisible(true);
                    jLabelShowedGeneration.setText("WYŚWIETLANA GENERACJA: " + actualGeneration);
                    jProgressBar1.setValue(j * 100 / generations);
                    jProgressBar1.paint(jProgressBar1.getGraphics());
                }
                jProgressBar1.setValue(0);
                jProgressBar1.setVisible(false);
            } catch (OutOfMemoryError e) {
                setVisibleEditor();
                jProgressBar1.setValue(0);
                generatedCells = null;
                JOptionPane.showMessageDialog(null, "Za duża liczba generacji, zmień ją!");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Generacji musi być więcej niż jedna!");
            jProgressBar1.setValue(0);
        }
    }//GEN-LAST:event_jButtonGenerateActionPerformed

    private void jButtonPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlayActionPerformed
        if ("Start".equals(jButtonPlay.getText())) {
            jButtonPlay.setText("Stop");
            autoplayTimer.start();
        } else {
            autoplayTimer.stop();
            jButtonPlay.setText("Start");
        }

    }//GEN-LAST:event_jButtonPlayActionPerformed

    private void jButtonNextActionPerformed(java.awt.event.ActionEvent evt) {
        if (actualGeneration < generations) {
            actualGeneration++;
            draw.RefreshCells(generatedCells[actualGeneration]);
            jLabelShowedGeneration.setText("WYŚWIETLANA GENERACJA: " + actualGeneration);
        } else {
            JOptionPane.showMessageDialog(null, "To była już ostatnia generacja!");
        }
        autoplayTimer.stop();
        jButtonPlay.setVisible(true);
    }

    private void jButtonPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPreviousActionPerformed
        if (actualGeneration > 0) {
            actualGeneration--;
            draw.RefreshCells(generatedCells[actualGeneration]);
            jLabelShowedGeneration.setText("WYŚWIETLANA GENERACJA: " + actualGeneration);
        } else {
            JOptionPane.showMessageDialog(null, "Nie ma generacji przed generacją zero!");
        }
        autoplayTimer.stop();
        jButtonPlay.setVisible(true);
    }//GEN-LAST:event_jButtonPreviousActionPerformed

    private void jButtonJumpToGenerationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonJumpToGenerationActionPerformed
        int jumpToGen = (Integer) jSpinnerJumpToGeneration.getValue();
        if (jumpToGen >= 0 && jumpToGen <= generations) {
            actualGeneration = jumpToGen;
            draw.RefreshCells(generatedCells[actualGeneration]);
            jLabelShowedGeneration.setText("WYŚWIETLANA GENERACJA: " + actualGeneration);
        } else {
            JOptionPane.showMessageDialog(null, "Nieprawidłowy numer generacji, nie możesz do niej skoczyć!");
        }
        autoplayTimer.stop();
        jButtonPlay.setVisible(true);
    }//GEN-LAST:event_jButtonJumpToGenerationActionPerformed

    private void jCheckBoxConductorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxConductorActionPerformed
        draw.setValue(1);
    }//GEN-LAST:event_jCheckBoxConductorActionPerformed

    private void jCheckBoxEmptyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxEmptyActionPerformed
        draw.setValue(0);
    }//GEN-LAST:event_jCheckBoxEmptyActionPerformed

    private void jCheckBoxTailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxTailActionPerformed
        draw.setValue(2);
    }//GEN-LAST:event_jCheckBoxTailActionPerformed

    private void jCheckBoxHeadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxHeadActionPerformed
        draw.setValue(3);
    }//GEN-LAST:event_jCheckBoxHeadActionPerformed

    private void jButtonEditActualGenerationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditActualGenerationActionPerformed
        cells = generatedCells[actualGeneration];
        generatedCells = null;
        actualGeneration = 0;
        setVisibleEditor();
        autoplayTimer.stop();
    }//GEN-LAST:event_jButtonEditActualGenerationActionPerformed

    private void jMenuItemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAboutActionPerformed
        String informacja = "Automat Komórkowy, implementacja Wireworld i Life.\n Autor: Michał Jereczek \n";
        JOptionPane.showMessageDialog(null, informacja);
    }//GEN-LAST:event_jMenuItemAboutActionPerformed

    private void jMenuItemNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNewActionPerformed
        Object[] options = {"Tak",
            "Nie"};
        int n = JOptionPane.showOptionDialog(null,
                "Jesteś pewny? Niezapisana siatka zostanie utracona!",
                "Ostrzeżenie",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, //do not use a custom Icon
                options, //the titles of buttons
                options[0]); //default button title
        if (n == 0) {
            this.dispose();
            new Launcher().setVisible(true);
        }
    }//GEN-LAST:event_jMenuItemNewActionPerformed

    private void jScrollBarHorizontalAdjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
        double varX = jScrollBarHorizontal.getValue();
        double varY = jScrollBarVertical.getValue();
        double maxX = cells.getX() * draw.getSIZE() * 1.05;
        double maxY = cells.getY() * draw.getSIZE() * 1.05;
        if (varX != 0) {
            varX = ((varX) / 90.0f) * maxX;
        } else {
            varX = 0;
        }
        if (varY != 0) {
            varY = ((varY) / 90.0f) * maxY;
        } else {
            varY = 0;
        }
        draw.setMods(-(int) varX, -(int) varY);
        this.repaint();
    }

    private void jScrollBarVerticalAdjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
        jScrollBarHorizontalAdjustmentValueChanged(evt);
    }

    private void jPanel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseEntered
        if (jButtonGenerate.isVisible()) {
            draw.setMouse(true);
        }
    }//GEN-LAST:event_jPanel1MouseEntered

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxMenuItemEmpty = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemConductor = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemTail = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemHead = new javax.swing.JCheckBoxMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jSlider1 = new javax.swing.JSlider();
        jPanel2 = new javax.swing.JPanel();
        jPanelDesignAndGenerate = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jButtonEreaseElectrons = new javax.swing.JButton();
        jComboBoxStructureSwitch = new javax.swing.JComboBox();
        jComboBoxStructurePosition = new javax.swing.JComboBox();
        jCheckBoxStructure = new javax.swing.JCheckBox();
        jCheckBoxOR = new javax.swing.JCheckBox();
        jCheckBoxAND = new javax.swing.JCheckBox();
        jCheckBoxHead = new javax.swing.JCheckBox();
        jCheckBoxTail = new javax.swing.JCheckBox();
        jCheckBoxConductor = new javax.swing.JCheckBox();
        jCheckBoxEmpty = new javax.swing.JCheckBox();
        jLabelTools = new javax.swing.JLabel();
        jSpinnerHowManyToGenerate = new javax.swing.JSpinner();
        jButtonGenerate = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabelShowedGeneration = new javax.swing.JLabel();
        jButtonPrevious = new javax.swing.JButton();
        jButtonPlay = new javax.swing.JButton();
        jButtonNext = new javax.swing.JButton();
        jButtonJumpToGeneration = new javax.swing.JButton();
        jSpinnerJumpToGeneration = new javax.swing.JSpinner();
        jButtonEditActualGeneration = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollBarVertical = new javax.swing.JScrollBar();
        jScrollBarHorizontal = new javax.swing.JScrollBar();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemNew = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemWrite = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItemAbout = new javax.swing.JMenuItem();

        jCheckBoxMenuItemEmpty.setSelected(true);
        jCheckBoxMenuItemEmpty.setText("jCheckBoxMenuItem1");

        jCheckBoxMenuItemConductor.setSelected(true);
        jCheckBoxMenuItemConductor.setText("jCheckBoxMenuItem2");

        jCheckBoxMenuItemTail.setSelected(true);
        jCheckBoxMenuItemTail.setText("jCheckBoxMenuItem3");

        jCheckBoxMenuItemHead.setSelected(true);
        jCheckBoxMenuItemHead.setText("jCheckBoxMenuItem4");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1024, 600));
        setResizable(false);

        jButtonEreaseElectrons.setText("Usuń wszystkie elektrony");
        jButtonEreaseElectrons.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEreaseElectronsActionPerformed(evt);
            }
        });

        jComboBoxStructureSwitch.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dioda", "GeneratorM", "GeneratorD", "Elektron" }));
        jComboBoxStructureSwitch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxStructureSwitchActionPerformed(evt);
            }
        });

        jComboBoxStructurePosition.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "z lewa", "z góry", "z prawa", "z dołu" }));
        jComboBoxStructurePosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxStructurePositionActionPerformed(evt);
            }
        });

        jCheckBoxStructure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxStructureActionPerformed(evt);
            }
        });

        jCheckBoxOR.setText("Bramka OR");
        jCheckBoxOR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxORActionPerformed(evt);
            }
        });

        jCheckBoxAND.setText("Bramka AND");
        jCheckBoxAND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxANDActionPerformed(evt);
            }
        });

        jCheckBoxHead.setText("Głowa Elektronu");
        jCheckBoxHead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxHeadActionPerformed(evt);
            }
        });

        jCheckBoxTail.setText("Ogon Elektronu");
        jCheckBoxTail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxTailActionPerformed(evt);
            }
        });

        jCheckBoxConductor.setText("Przewodnik");
        jCheckBoxConductor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxConductorActionPerformed(evt);
            }
        });

        jCheckBoxEmpty.setSelected(true);
        jCheckBoxEmpty.setText("Pusty");
        jCheckBoxEmpty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxEmptyActionPerformed(evt);
            }
        });

        jLabelTools.setText("Przybornik:");

        jSpinnerHowManyToGenerate.setValue(10);

        jButtonGenerate.setText("Generuj!");
        jButtonGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenerateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jCheckBoxStructure)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxStructureSwitch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxStructurePosition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButtonGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerHowManyToGenerate, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))
                    .addComponent(jCheckBoxAND)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jCheckBoxEmpty))
                    .addComponent(jCheckBoxConductor)
                    .addComponent(jCheckBoxHead)
                    .addComponent(jCheckBoxTail)
                    .addComponent(jCheckBoxOR)
                    .addComponent(jLabelTools)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonEreaseElectrons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinnerHowManyToGenerate)
                    .addComponent(jButtonGenerate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelTools)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxEmpty)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxConductor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxTail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxHead)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxAND)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxOR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxStructurePosition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxStructure)
                    .addComponent(jComboBoxStructureSwitch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEreaseElectrons)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabelShowedGeneration.setText("jLabel2");

        jButtonPrevious.setText("<");
        jButtonPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPreviousActionPerformed(evt);
            }
        });

        jButtonPlay.setText("Start");
        jButtonPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlayActionPerformed(evt);
            }
        });

        jButtonNext.setText(">");
        jButtonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNextActionPerformed(evt);
            }
        });

        jButtonJumpToGeneration.setText("Skocz do generacji:");
        jButtonJumpToGeneration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonJumpToGenerationActionPerformed(evt);
            }
        });

        jButtonEditActualGeneration.setText("Edytuj aktualną generację");
        jButtonEditActualGeneration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditActualGenerationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonEditActualGeneration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButtonPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonPlay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonNext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabelShowedGeneration))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButtonJumpToGeneration, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinnerJumpToGeneration, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelShowedGeneration)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPrevious)
                    .addComponent(jButtonPlay)
                    .addComponent(jButtonNext))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonJumpToGeneration)
                    .addComponent(jSpinnerJumpToGeneration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEditActualGeneration)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelDesignAndGenerateLayout = new javax.swing.GroupLayout(jPanelDesignAndGenerate);
        jPanelDesignAndGenerate.setLayout(jPanelDesignAndGenerateLayout);
        jPanelDesignAndGenerateLayout.setHorizontalGroup(
            jPanelDesignAndGenerateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDesignAndGenerateLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator4))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDesignAndGenerateLayout.createSequentialGroup()
                .addGroup(jPanelDesignAndGenerateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelDesignAndGenerateLayout.setVerticalGroup(
            jPanelDesignAndGenerateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDesignAndGenerateLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel1MouseEntered(evt);
            }
        });

        jScrollBarVertical.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                jScrollBarVerticalAdjustmentValueChanged(evt);
            }
        });

        jScrollBarHorizontal.setOrientation(javax.swing.JScrollBar.HORIZONTAL);
        jScrollBarHorizontal.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollBarHorizontal.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                jScrollBarHorizontalAdjustmentValueChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollBarHorizontal, javax.swing.GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollBarVertical, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollBarHorizontal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollBarVertical, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56))
        );

        jMenu1.setText("Plik");

        jMenuItemNew.setText("Nowy");
        jMenuItemNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNewActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemNew);
        jMenu1.add(jSeparator1);

        jMenuItemWrite.setText("Zapisz");
        jMenuItemWrite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemWriteActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemWrite);
        jMenu1.add(jSeparator2);

        jMenuItemAbout.setText("O programie");
        jMenuItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAboutActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemAbout);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelDesignAndGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelDesignAndGenerate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxStructurePositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxStructurePositionActionPerformed
        setStructure();
    }//GEN-LAST:event_jComboBoxStructurePositionActionPerformed

    private void jCheckBoxStructureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxStructureActionPerformed
        setStructure();
    }//GEN-LAST:event_jCheckBoxStructureActionPerformed

    private void jComboBoxStructureSwitchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxStructureSwitchActionPerformed
        setStructure();
    }//GEN-LAST:event_jComboBoxStructureSwitchActionPerformed

    private void jButtonEreaseElectronsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEreaseElectronsActionPerformed
        for (int j = 1; j <= cells.getY(); j++) {
            for (int i = 1; i <= cells.getX(); i++) {
                if ((cells.getCell(i, j).getValue() == 2) || (cells.getCell(i, j).getValue() == 3)) {
                    cells.setCell(i, j, 1);
                }
            }
        }
        draw.RefreshCells(cells);
    }//GEN-LAST:event_jButtonEreaseElectronsActionPerformed

    private void jCheckBoxANDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxANDActionPerformed
        draw.setValue(8);
        draw.setStructurePosition(0);
    }//GEN-LAST:event_jCheckBoxANDActionPerformed

    private void jCheckBoxORActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxORActionPerformed
        draw.setValue(9);
        draw.setStructurePosition(0);
    }//GEN-LAST:event_jCheckBoxORActionPerformed

    private void jMenuItemWriteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemWriteActionPerformed
        Cells cellsx;
        if (jButtonGenerate.isVisible()) {
            cellsx = cells;
        } else {
            cellsx = generatedCells[actualGeneration];
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Pliki automatu komórkowego (.automat)", "automat"));
        fileChooser.setCurrentDirectory(new File("."));
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();
                if (!fileChooser.getSelectedFile().getAbsolutePath().endsWith("automat")) {
                    file = new File(file.getAbsolutePath() + ".automat");
                }
                CellularAutomatonIO.writeToFile(file, (rules.getAutomatonState().getColors()[0] == Color.WHITE), rules.getIsWrapped(), cellsx);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }//GEN-LAST:event_jMenuItemWriteActionPerformed

    private void setStructure() {
        jCheckBoxStructure.setSelected(true);
        draw.setStructurePosition(jComboBoxStructurePosition.getSelectedIndex());
        draw.setValue(jComboBoxStructureSwitch.getSelectedIndex() + 4);
    }
}
