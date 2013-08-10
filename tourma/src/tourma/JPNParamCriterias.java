/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma;

import tourma.data.Criteria;
import tourma.data.Match;
import tourma.data.Round;
import tourma.data.Tournament;
import tourma.data.Value;
import tourma.tableModel.mjtCriterias;

/**
 *
 * @author WFMJ7631
 */
public class JPNParamCriterias extends javax.swing.JPanel {

    Tournament mTournament;

    /**
     * Creates new form JPNParamCriterias
     */
    public JPNParamCriterias() {
        mTournament = Tournament.getTournament();
        initComponents();
    }

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

        setLayout(new java.awt.BorderLayout());

        jbtAddCriteria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Add.png"))); // NOI18N
        jbtAddCriteria.setText("Ajouter Critère");
        jbtAddCriteria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAddCriteriaActionPerformed(evt);
            }
        });
        jPanel16.add(jbtAddCriteria);

        jbtRemoveCriteria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Close.png"))); // NOI18N
        jbtRemoveCriteria.setText("Retirer critère");
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
    }// </editor-fold>//GEN-END:initComponents
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtAddCriteriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAddCriteriaActionPerformed
    final int nb = Tournament.getTournament().getParams().mCriterias.size();
    final Criteria c = new Criteria("Critère " + Integer.toString(nb));
    Tournament.getTournament().getParams().mCriterias.add(c);
    for (int i = 0; i < mTournament.getRounds().size(); i++) {
        final Round r = mTournament.getRounds().get(i);
        for (int j = 0; j < r.getMatchs().size(); j++) {
            final Match m = r.getMatchs().get(j);
            m.mValues.put(c, new Value(c));
        }
    }

    update();
    }//GEN-LAST:event_jbtAddCriteriaActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtRemoveCriteriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRemoveCriteriaActionPerformed
    if ((jtbCriteria.getSelectedRow() > 1) && (jtbCriteria.getSelectedRow() < mTournament.getParams().mCriterias.size())) {
        final Criteria crit = mTournament.getParams().mCriterias.get(jtbCriteria.getSelectedRow());
        for (int i = 0; i < mTournament.getRounds().size(); i++) {
            final Round r = mTournament.getRounds().get(i);
            for (int j = 0; j < r.getMatchs().size(); j++) {
                final Match m = r.getMatchs().get(j);
                m.mValues.remove(crit);
            }
        }
        mTournament.getParams().mCriterias.remove(jtbCriteria.getSelectedRow());
    }
    repaint();
    }//GEN-LAST:event_jbtRemoveCriteriaActionPerformed

    protected void update() {

        final boolean bTourStarted = mTournament.getRounds().size() > 0;
        jtbCriteria.setModel(new mjtCriterias(mTournament));
        jbtAddCriteria.setEnabled(!bTourStarted);
        jbtRemoveCriteria.setEnabled(!bTourStarted);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel16;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JButton jbtAddCriteria;
    private javax.swing.JButton jbtRemoveCriteria;
    private javax.swing.JTable jtbCriteria;
    // End of variables declaration//GEN-END:variables
}
