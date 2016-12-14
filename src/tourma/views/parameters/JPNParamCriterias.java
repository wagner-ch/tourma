/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.views.parameters;

import java.rmi.RemoteException;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import tourma.data.CoachMatch;
import tourma.data.Criteria;
import tourma.data.Tournament;
import tourma.data.Round;
import tourma.data.Tournament;
import tourma.data.Value;
import tourma.languages.Translate;
import tourma.tableModel.MjtCriterias;

/**
 *
 * @author WFMJ7631
 */
public final class JPNParamCriterias extends javax.swing.JPanel {

    private Tournament mTournament;

    /**
     * Creates new form JPNParamCriterias
     */
    public JPNParamCriterias() {
        try {
            mTournament = Tournament.getTournament();
        } catch (RemoteException re) {
            re.printStackTrace();
        }
        initComponents();
    }

    private final static String CS_Criteria = "CRITÈRE";

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "PMD"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel16 = new javax.swing.JPanel();
        jbtAddCriteria = new javax.swing.JButton();
        jbtRemoveCriteria = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtbCriteria = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jcxTableBonus = new javax.swing.JCheckBox();
        jcxTableCoefPerRound = new javax.swing.JCheckBox();
        jlbCoef = new javax.swing.JLabel();
        jspCoef = new javax.swing.JSpinner();

        setLayout(new java.awt.BorderLayout());

        jbtAddCriteria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Add.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        jbtAddCriteria.setText(bundle.getString("AJOUTER CRITÈRE")); // NOI18N
        jbtAddCriteria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAddCriteriaActionPerformed(evt);
            }
        });
        jPanel16.add(jbtAddCriteria);

        jbtRemoveCriteria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Close.png"))); // NOI18N
        jbtRemoveCriteria.setText(bundle.getString("RETIRER CRITÈRE")); // NOI18N
        jbtRemoveCriteria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRemoveCriteriaActionPerformed(evt);
            }
        });
        jPanel16.add(jbtRemoveCriteria);

        add(jPanel16, java.awt.BorderLayout.PAGE_START);

        jtbCriteria.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(jtbCriteria);

        add(jScrollPane5, java.awt.BorderLayout.CENTER);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("TableBonus"))); // NOI18N
        jPanel1.setLayout(new java.awt.GridLayout(2, 2));

        jcxTableBonus.setText(bundle.getString("TablePoints")); // NOI18N
        jcxTableBonus.setToolTipText(bundle.getString("PointsTableTooltip")); // NOI18N
        jcxTableBonus.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jcxTableBonus.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jcxTableBonus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxTableBonusActionPerformed(evt);
            }
        });
        jPanel1.add(jcxTableBonus);

        jcxTableCoefPerRound.setText(bundle.getString("TableBonusPonderation")); // NOI18N
        jcxTableCoefPerRound.setToolTipText(bundle.getString("BonusPointPonderationTooltip")); // NOI18N
        jcxTableCoefPerRound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxTableCoefPerRoundActionPerformed(evt);
            }
        });
        jPanel1.add(jcxTableCoefPerRound);

        jlbCoef.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbCoef.setText(bundle.getString("TablePonderation")); // NOI18N
        jPanel1.add(jlbCoef);

        jspCoef.setModel(new javax.swing.SpinnerNumberModel(1.0,0.0 ,1000.0,0.01));
        jspCoef.setEditor(new JSpinner.NumberEditor(jspCoef));
        jspCoef.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jspCoefStateChanged(evt);
            }
        });
        jPanel1.add(jspCoef);

        add(jPanel1, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtAddCriteriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAddCriteriaActionPerformed
        try {
            final int nb = Tournament.getTournament().getParams().getCriteriaCount();
            final Criteria c = new Criteria(
                    Translate.translate(CS_Criteria)
                    + " " + Integer.toString(nb));
            Tournament.getTournament().getParams().addCriteria(c);
            for (int i = 0; i < mTournament.getRoundsCount(); i++) {
                final Round r = mTournament.getRound(i);
                for (int j = 0; j < r.getCoachMatchs().size(); j++) {
                    final CoachMatch m = r.getCoachMatchs().get(j);
                    m.putValue(c, new Value(c));
                }
            }
        } catch (RemoteException re) {
            re.printStackTrace();
        }
        update();
    }//GEN-LAST:event_jbtAddCriteriaActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtRemoveCriteriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRemoveCriteriaActionPerformed
        try {
            if ((jtbCriteria.getSelectedRow() > 1) && (jtbCriteria.getSelectedRow() < mTournament.getParams().getCriteriaCount())) {
                final Criteria crit = mTournament.getParams().getCriteria(jtbCriteria.getSelectedRow());
                for (int i = 0; i < mTournament.getRoundsCount(); i++) {
                    final Round r = mTournament.getRound(i);
                    for (int j = 0; j < r.getCoachMatchs().size(); j++) {
                        final CoachMatch m = r.getCoachMatchs().get(j);
                        m.removeValue(crit);
                    }
                }
                mTournament.getParams().removeCriteria(jtbCriteria.getSelectedRow());
            }
        } catch (RemoteException re) {
            re.printStackTrace();
        }
        repaint();
    }//GEN-LAST:event_jbtRemoveCriteriaActionPerformed

    private void jcxTableCoefPerRoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxTableCoefPerRoundActionPerformed
        try {
            Tournament.getTournament().getParams().setTableBonusPerRound(jcxTableCoefPerRound.isSelected());
        } catch (RemoteException re) {
            re.printStackTrace();
        }
        update();
    }//GEN-LAST:event_jcxTableCoefPerRoundActionPerformed

    private void jcxTableBonusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxTableBonusActionPerformed
        try {
            Tournament.getTournament().getParams().setTableBonus(jcxTableBonus.isSelected());
        } catch (RemoteException re) {
            re.printStackTrace();
        }
        update();
    }//GEN-LAST:event_jcxTableBonusActionPerformed

    private void jspCoefStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jspCoefStateChanged
        try {
            Tournament.getTournament().getParams().setTableBonusCoef((Double) jspCoef.getValue());
        } catch (RemoteException re) {
            re.printStackTrace();
        }
        update();
    }//GEN-LAST:event_jspCoefStateChanged

    /**
     * Update Panel
     */
    public void update() {
        try {
            final boolean bTourStarted = mTournament.getRoundsCount() > 0;
            jtbCriteria.setModel(new MjtCriterias(mTournament));
            jbtAddCriteria.setEnabled(!bTourStarted);
            jbtRemoveCriteria.setEnabled(!bTourStarted);

            jcxTableBonus.setSelected(mTournament.getParams().isTableBonus());
            jcxTableCoefPerRound.setSelected(mTournament.getParams().isTableBonusPerRound());

            jspCoef.setValue(Tournament.getTournament().getParams().getTableBonusCoef());
            jspCoef.setEnabled(mTournament.getParams().isTableBonus());
            jlbCoef.setEnabled(mTournament.getParams().isTableBonus());
        } catch (RemoteException re) {
            re.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JButton jbtAddCriteria;
    private javax.swing.JButton jbtRemoveCriteria;
    private javax.swing.JCheckBox jcxTableBonus;
    private javax.swing.JCheckBox jcxTableCoefPerRound;
    private javax.swing.JLabel jlbCoef;
    private javax.swing.JSpinner jspCoef;
    private javax.swing.JTable jtbCriteria;
    // End of variables declaration//GEN-END:variables

}
