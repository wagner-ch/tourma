/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JPNTeamRound.java
 *
 * Created on 20 juil. 2010, 10:47:49
 */
package tourma;

import tourma.tableModel.mjtAnnexRankIndiv;
import tourma.tableModel.mjtRankingTeam;
import tourma.tableModel.mjtAnnexRankTeam;
import java.awt.FontMetrics;
import java.util.Vector;
import javax.swing.JTable;
import tourma.data.Round;
import tourma.data.Tournament;

/**
 *
 * @author Frederic Berger
 */
public class JPNTeamRound extends javax.swing.JPanel {

    Round _round;
    Tournament _tournament;

    /** Creates new form JPNTeamRound */
    public JPNTeamRound(Round r, Tournament t) {
        initComponents();
        _round = r;
        _tournament = t;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel8 = new javax.swing.JPanel();
        jbtGeneralTeam = new javax.swing.JButton();
        jbtScorePosTeam = new javax.swing.JButton();
        jbtScoreNegTeam = new javax.swing.JButton();
        jbtSorPosTeam = new javax.swing.JButton();
        jbtSorNegTeam = new javax.swing.JButton();
        jbtFoulPosTeam = new javax.swing.JButton();
        jbtFoulNegTeam = new javax.swing.JButton();
        jbtShowMatchTeam = new javax.swing.JButton();
        jpnTeam = new javax.swing.JPanel();
        jspVertical = new javax.swing.JSplitPane();
        jSplitPane2 = new javax.swing.JSplitPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtbRankingTeam = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jtbMostTdTeam = new javax.swing.JTable();
        jScrollPane11 = new javax.swing.JScrollPane();
        jtbMostSorTeam = new javax.swing.JTable();
        jScrollPane12 = new javax.swing.JScrollPane();
        jtbMostFoulTeam = new javax.swing.JTable();
        jScrollPane13 = new javax.swing.JScrollPane();
        jtbMostTdNegTeam = new javax.swing.JTable();
        jScrollPane14 = new javax.swing.JScrollPane();
        jtbMostSorNegTeam = new javax.swing.JTable();
        jScrollPane15 = new javax.swing.JScrollPane();
        jtbMostFoulNegTeam = new javax.swing.JTable();
        jspMatch = new javax.swing.JScrollPane();
        jtbMatches2 = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jbtGeneralTeam.setText("Général");
        jbtGeneralTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGeneralTeamActionPerformed(evt);
            }
        });
        jPanel8.add(jbtGeneralTeam);

        jbtScorePosTeam.setText("Scoreur");
        jbtScorePosTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtScorePosTeamActionPerformed(evt);
            }
        });
        jPanel8.add(jbtScorePosTeam);

        jbtScoreNegTeam.setText("Passoire");
        jbtScoreNegTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtScoreNegTeamActionPerformed(evt);
            }
        });
        jPanel8.add(jbtScoreNegTeam);

        jbtSorPosTeam.setText("Destructeur");
        jbtSorPosTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtSorPosTeamActionPerformed(evt);
            }
        });
        jPanel8.add(jbtSorPosTeam);

        jbtSorNegTeam.setText("Punching Ball");
        jbtSorNegTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtSorNegTeamActionPerformed(evt);
            }
        });
        jPanel8.add(jbtSorNegTeam);

        jbtFoulPosTeam.setText("Crampon");
        jbtFoulPosTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtFoulPosTeamActionPerformed(evt);
            }
        });
        jPanel8.add(jbtFoulPosTeam);

        jbtFoulNegTeam.setText("Paillasson");
        jbtFoulNegTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtFoulNegTeamActionPerformed(evt);
            }
        });
        jPanel8.add(jbtFoulNegTeam);

        jbtShowMatchTeam.setText("Vue des résultats");
        jbtShowMatchTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtShowMatchTeamActionPerformed(evt);
            }
        });
        jPanel8.add(jbtShowMatchTeam);

        add(jPanel8, java.awt.BorderLayout.SOUTH);

        jpnTeam.setLayout(new java.awt.BorderLayout());

        jspVertical.setDividerLocation(450);

        jSplitPane2.setDividerLocation(200);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Classement général"));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(466, 300));

        jtbRankingTeam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtbRankingTeam.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(jtbRankingTeam);

        jSplitPane2.setBottomComponent(jScrollPane3);

        jPanel7.setLayout(new java.awt.GridLayout(2, 3));

        jScrollPane7.setBorder(javax.swing.BorderFactory.createTitledBorder("Scoreur"));

        jtbMostTdTeam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(jtbMostTdTeam);

        jPanel7.add(jScrollPane7);

        jScrollPane11.setBorder(javax.swing.BorderFactory.createTitledBorder("Desctructeur"));

        jtbMostSorTeam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane11.setViewportView(jtbMostSorTeam);

        jPanel7.add(jScrollPane11);

        jScrollPane12.setBorder(javax.swing.BorderFactory.createTitledBorder("Crampon"));

        jtbMostFoulTeam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane12.setViewportView(jtbMostFoulTeam);

        jPanel7.add(jScrollPane12);

        jScrollPane13.setBorder(javax.swing.BorderFactory.createTitledBorder("Passoire"));

        jtbMostTdNegTeam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane13.setViewportView(jtbMostTdNegTeam);

        jPanel7.add(jScrollPane13);

        jScrollPane14.setBorder(javax.swing.BorderFactory.createTitledBorder("Punching-Ball"));

        jtbMostSorNegTeam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane14.setViewportView(jtbMostSorNegTeam);

        jPanel7.add(jScrollPane14);

        jScrollPane15.setBorder(javax.swing.BorderFactory.createTitledBorder("Paillasson"));

        jtbMostFoulNegTeam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane15.setViewportView(jtbMostFoulNegTeam);

        jPanel7.add(jScrollPane15);

        jSplitPane2.setTopComponent(jPanel7);

        jspVertical.setLeftComponent(jSplitPane2);

        jtbMatches2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtbMatches2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jtbMatches2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jspMatch.setViewportView(jtbMatches2);

        jspVertical.setRightComponent(jspMatch);

        jpnTeam.add(jspVertical, java.awt.BorderLayout.CENTER);

        add(jpnTeam, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtGeneralTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGeneralTeamActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jbtGeneralTeamActionPerformed

    private void jbtScorePosTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtScorePosTeamActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jbtScorePosTeamActionPerformed

    private void jbtScoreNegTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtScoreNegTeamActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jbtScoreNegTeamActionPerformed

    private void jbtSorPosTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSorPosTeamActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jbtSorPosTeamActionPerformed

    private void jbtSorNegTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSorNegTeamActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jbtSorNegTeamActionPerformed

    private void jbtFoulPosTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtFoulPosTeamActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jbtFoulPosTeamActionPerformed

    private void jbtFoulNegTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtFoulNegTeamActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jbtFoulNegTeamActionPerformed

    private void jbtShowMatchTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtShowMatchTeamActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jbtShowMatchTeamActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JButton jbtFoulNegTeam;
    private javax.swing.JButton jbtFoulPosTeam;
    private javax.swing.JButton jbtGeneralTeam;
    private javax.swing.JButton jbtScoreNegTeam;
    private javax.swing.JButton jbtScorePosTeam;
    private javax.swing.JButton jbtShowMatchTeam;
    private javax.swing.JButton jbtSorNegTeam;
    private javax.swing.JButton jbtSorPosTeam;
    private javax.swing.JPanel jpnTeam;
    private javax.swing.JScrollPane jspMatch;
    private javax.swing.JSplitPane jspVertical;
    private javax.swing.JTable jtbMatches2;
    private javax.swing.JTable jtbMostFoulNegTeam;
    private javax.swing.JTable jtbMostFoulTeam;
    private javax.swing.JTable jtbMostSorNegTeam;
    private javax.swing.JTable jtbMostSorTeam;
    private javax.swing.JTable jtbMostTdNegTeam;
    private javax.swing.JTable jtbMostTdTeam;
    private javax.swing.JTable jtbRankingTeam;
    // End of variables declaration//GEN-END:variables

    public void update() {

        Vector<Round> v = new Vector<Round>();
        for (int i = 0; i < _tournament.getRounds().size(); i++) {
            if (_tournament.getRounds().get(i).getHeure().before(_round.getHeure())) {
                v.add(_tournament.getRounds().get(i));
            }
        }
        v.add(_round);

        mjtAnnexRankTeam mTdPosTeam = new mjtAnnexRankTeam(v, mjtAnnexRankIndiv.C_MOST_TD_POS, _tournament.getTeams(), false, _tournament.getParams()._ranking1, _tournament.getParams()._ranking2, _tournament.getParams()._ranking3, _tournament.getParams()._ranking4, _tournament.getParams()._ranking5);
        mjtAnnexRankTeam mTdNegTeam = new mjtAnnexRankTeam(v, mjtAnnexRankIndiv.C_MOST_TD_NEG, _tournament.getTeams(), false, _tournament.getParams()._ranking1, _tournament.getParams()._ranking2, _tournament.getParams()._ranking3, _tournament.getParams()._ranking4, _tournament.getParams()._ranking5);
        mjtAnnexRankTeam mSorPosTeam = new mjtAnnexRankTeam(v, mjtAnnexRankIndiv.C_MOST_SOR_POS, _tournament.getTeams(), false, _tournament.getParams()._ranking1, _tournament.getParams()._ranking2, _tournament.getParams()._ranking3, _tournament.getParams()._ranking4, _tournament.getParams()._ranking5);
        mjtAnnexRankTeam mSorNegTeam = new mjtAnnexRankTeam(v, mjtAnnexRankIndiv.C_MOST_SOR_NEG, _tournament.getTeams(), false, _tournament.getParams()._ranking1, _tournament.getParams()._ranking2, _tournament.getParams()._ranking3, _tournament.getParams()._ranking4, _tournament.getParams()._ranking5);
        mjtAnnexRankTeam mFoulPosTeam = new mjtAnnexRankTeam(v, mjtAnnexRankIndiv.C_MOST_FOUL_POS, _tournament.getTeams(), false, _tournament.getParams()._ranking1, _tournament.getParams()._ranking2, _tournament.getParams()._ranking3, _tournament.getParams()._ranking4, _tournament.getParams()._ranking5);
        mjtAnnexRankTeam mFoulNegTeam = new mjtAnnexRankTeam(v, mjtAnnexRankIndiv.C_MOST_FOUL_NEG, _tournament.getTeams(), false, _tournament.getParams()._ranking1, _tournament.getParams()._ranking2, _tournament.getParams()._ranking3, _tournament.getParams()._ranking4, _tournament.getParams()._ranking5);

        jtbMostTdTeam.setModel(mTdPosTeam);
        jtbMostTdTeam.setDefaultRenderer(String.class, mTdPosTeam);
        jtbMostTdTeam.setDefaultRenderer(Integer.class, mTdPosTeam);
        jtbMostTdNegTeam.setModel(mTdNegTeam);
        jtbMostTdNegTeam.setDefaultRenderer(String.class, mTdNegTeam);
        jtbMostTdNegTeam.setDefaultRenderer(Integer.class, mTdNegTeam);
        jtbMostSorTeam.setModel(mSorPosTeam);
        jtbMostSorTeam.setDefaultRenderer(String.class, mSorPosTeam);
        jtbMostSorTeam.setDefaultRenderer(Integer.class, mSorPosTeam);
        jtbMostSorNegTeam.setModel(mSorNegTeam);
        jtbMostSorNegTeam.setDefaultRenderer(String.class, mSorNegTeam);
        jtbMostSorNegTeam.setDefaultRenderer(Integer.class, mSorNegTeam);
        jtbMostFoulTeam.setModel(mFoulPosTeam);
        jtbMostFoulTeam.setDefaultRenderer(String.class, mFoulPosTeam);
        jtbMostFoulTeam.setDefaultRenderer(Integer.class, mFoulPosTeam);
        jtbMostFoulNegTeam.setModel(mFoulNegTeam);
        jtbMostFoulNegTeam.setDefaultRenderer(String.class, mFoulNegTeam);
        jtbMostFoulNegTeam.setDefaultRenderer(Integer.class, mFoulNegTeam);

        mjtRankingTeam mRankingTeam = new mjtRankingTeam(v, _tournament.getParams()._ranking1, _tournament.getParams()._ranking2, _tournament.getParams()._ranking3, _tournament.getParams()._ranking4, _tournament.getParams()._ranking5, _tournament.getTeams());
        jtbRankingTeam.setModel(mRankingTeam);
        jtbRankingTeam.setDefaultRenderer(String.class, mRankingTeam);
        jtbRankingTeam.setDefaultRenderer(Integer.class, mRankingTeam);

        setColumnSize(jtbRankingTeam);

        setColumnSize(jtbRankingTeam);
        setColumnSize(jtbMostFoulNegTeam);
        setColumnSize(jtbMostFoulTeam);
        setColumnSize(jtbMostSorNegTeam);
        setColumnSize(jtbMostSorTeam);
        setColumnSize(jtbMostTdNegTeam);
        setColumnSize(jtbMostTdTeam);

    }

    public void setColumnSize(JTable t) {
        FontMetrics fm = t.getFontMetrics(t.getFont());
        for (int i = 0; i < t.getColumnCount(); i++) {
            int max = 0;
            for (int j = 0; j < t.getRowCount(); j++) {
                Object value = t.getValueAt(j, i);
                String tmp = "";
                if (value instanceof String) {
                    tmp = (String) value;
                }
                if (value instanceof Integer) {
                    tmp = "" + (Integer) value;
                }
                int taille = fm.stringWidth(tmp);
                if (taille > max) {
                    max = taille;
                }
            }
            String nom = (String) t.getColumnModel().getColumn(i).getIdentifier();
            int taille = fm.stringWidth(nom);
            if (taille > max) {
                max = taille;
            }
            t.getColumnModel().getColumn(i).setPreferredWidth(max + 10);
        }
    }
}
