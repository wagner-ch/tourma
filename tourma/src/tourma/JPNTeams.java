/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma;

import javax.swing.JOptionPane;
import tourma.data.Tournament;

import tourma.tableModel.mjtTeams;
import tourma.utility.StringConstants;
import tourma.utils.TableFormat;
import tourma.data.Team;

/**
 *
 * @author WFMJ7631
 */
public class JPNTeams extends javax.swing.JPanel {

    Tournament mTournament;

    /**
     * Creates new form JPNTeams
     */
    public JPNTeams() {
        mTournament = Tournament.getTournament();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked","PMD"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jlbDetails = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtbTeam = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jbtAddTeam = new javax.swing.JButton();
        jbtRemoveTeam = new javax.swing.JButton();
        jbtModifyTeam = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jPanel3.add(jlbDetails);

        add(jPanel3, java.awt.BorderLayout.PAGE_END);

        jtbTeam.setModel(new javax.swing.table.DefaultTableModel(
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
        jtbTeam.setCellSelectionEnabled(true);
        jtbTeam.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jtbTeam);

        add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jbtAddTeam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Add.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        jbtAddTeam.setText(bundle.getString("Add")); // NOI18N
        jbtAddTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAddTeamActionPerformed(evt);
            }
        });
        jPanel8.add(jbtAddTeam);

        jbtRemoveTeam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Close.png"))); // NOI18N
        jbtRemoveTeam.setText(bundle.getString("Remove")); // NOI18N
        jbtRemoveTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRemoveTeamActionPerformed(evt);
            }
        });
        jPanel8.add(jbtRemoveTeam);

        jbtModifyTeam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Swap.png"))); // NOI18N
        jbtModifyTeam.setText(bundle.getString("Modify")); // NOI18N
        jbtModifyTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtModifyTeamActionPerformed(evt);
            }
        });
        jPanel8.add(jbtModifyTeam);

        add(jPanel8, java.awt.BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents
@SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtAddTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAddTeamActionPerformed

        final jdgTeam jdg = new jdgTeam(MainFrame.getMainFrame(), true);
        jdg.setVisible(true);

        update();
    }//GEN-LAST:event_jbtAddTeamActionPerformed
@SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtRemoveTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRemoveTeamActionPerformed
        final Team t = mTournament.getTeams().get(jtbTeam.getSelectedRow());
        for (int i = 0; i < t.mCoachs.size(); i++) {
            mTournament.getCoachs().remove(t.mCoachs.get(i));
        }
        t.mCoachs.clear();
        mTournament.getTeams().remove(t);
        update();
    }//GEN-LAST:event_jbtRemoveTeamActionPerformed

     public void update() {

          final boolean bTourStarted = mTournament.getRounds().size() > 0;
          
        jbtAddTeam.setEnabled(!bTourStarted);
        jbtRemoveTeam.setEnabled(!bTourStarted);

        if (mTournament.getParams().mTeamTournament) {
            String text = "";
            if (mTournament.getParams().mTeamPairing == 0) {
                text = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Single");
            } else {
                text = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ByTeam");
            }
            jlbDetails.setText(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("MembersNumber") + ": " + mTournament.getParams().mTeamMatesNumber + " " + java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Pairing") + " " + text);
        } 

        final mjtTeams teamModel = new mjtTeams(mTournament.getTeams());
        jtbTeam.setModel(teamModel);
        TableFormat.setColumnSize(jtbTeam);
        jtbTeam.setDefaultRenderer(String.class, teamModel);
        jtbTeam.setDefaultRenderer(Integer.class, teamModel);
    }
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtModifyTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtModifyTeamActionPerformed
        if (mTournament.getTeams().size() > jtbTeam.getSelectedRow()) {
            final Team t = mTournament.getTeams().get(jtbTeam.getSelectedRow());
            if (jtbTeam.getSelectedColumn() == 1) {
                final String name = JOptionPane.showInputDialog(this, java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("EnterTeamName"), t.mName);
                t.mName = name;
            } else if (jtbTeam.getSelectedColumn() > 1) {
                final jdgCoach jdg = new jdgCoach(MainFrame.getMainFrame(), true, t.mCoachs.get(jtbTeam.getSelectedColumn() - 2));
                jdg.setVisible(true);
            }
            update();
        }
    }//GEN-LAST:event_jbtModifyTeamActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtAddTeam;
    private javax.swing.JButton jbtModifyTeam;
    private javax.swing.JButton jbtRemoveTeam;
    private javax.swing.JLabel jlbDetails;
    private javax.swing.JTable jtbTeam;
    // End of variables declaration//GEN-END:variables
}
