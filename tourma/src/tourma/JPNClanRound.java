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

import tourma.tableModel.mjtAnnexRank;
import java.awt.FontMetrics;
import java.util.HashMap;
import java.util.ArrayList;
import javax.swing.JTable;
import tourma.data.Criteria;
import tourma.data.Parameters;
import tourma.data.Round;
import tourma.data.Tournament;
import tourma.tableModel.mjtAnnexRankClan;
import tourma.tableModel.mjtRankingClan;
import tourma.views.report.jdgGlobal;
import tourma.views.report.jdgRanking;
import tourma.utility.StringConstants;
import tourma.utils.TableFormat;

/**
 *
 * @author Frederic Berger
 */
public class JPNClanRound extends javax.swing.JPanel {

    Round mRound;
    Tournament mTournament;
    JTable mJtbTeamMatch = null;
    public boolean mRoundOnly = false;

    /**
     * Creates new form JPNTeamRound
     */
    public JPNClanRound(final Round r, final Tournament t) {
        initComponents();
        mRound = r;
        mTournament = t;

        for (int i = 0; i < mTournament.getParams().mCriterias.size(); i++) {
            final Criteria criteria = mTournament.getParams().mCriterias.get(i);
            final JPNAnnexRanking jpn = new JPNAnnexRanking(criteria.mName, criteria, mTournament, mRound, true, false);
            jtpAnnexRank.add(criteria.mName, jpn);
        }

        update();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "PMD"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtbRankingClan = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jbtGeneralClan = new javax.swing.JButton();
        jbtGGlobalClan = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jtpAnnexRank = new javax.swing.JTabbedPane();

        setLayout(new java.awt.BorderLayout());

        jSplitPane1.setDividerLocation(600);

        jPanel2.setLayout(new java.awt.BorderLayout());

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE); // NOI18N
        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("GeneralRankingKey"))); // NOI18N
        jScrollPane3.setPreferredSize(new java.awt.Dimension(466, 300));

        jtbRankingClan.setModel(new javax.swing.table.DefaultTableModel(
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
        jtbRankingClan.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(jtbRankingClan);

        jPanel2.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jbtGeneralClan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Document.png"))); // NOI18N
        jbtGeneralClan.setText(bundle.getString("GeneralRankingKey")); // NOI18N
        jbtGeneralClan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGeneralClanActionPerformed(evt);
            }
        });
        jPanel3.add(jbtGeneralClan);

        jbtGGlobalClan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Document.png"))); // NOI18N
        jbtGGlobalClan.setText(bundle.getString("GlobalRankingKey")); // NOI18N
        jbtGGlobalClan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGGlobalClanActionPerformed(evt);
            }
        });
        jPanel3.add(jbtGGlobalClan);

        jPanel2.add(jPanel3, java.awt.BorderLayout.SOUTH);

        jSplitPane1.setLeftComponent(jPanel2);

        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(jtpAnnexRank, java.awt.BorderLayout.CENTER);

        jSplitPane1.setRightComponent(jPanel1);

        add(jSplitPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
 @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtGeneralClanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGeneralClanActionPerformed
        for (int i = 0; i < mTournament.getRounds().size(); i++) {
            if (mRound == mTournament.getRounds().get(i)) {
                final mjtRankingClan model = new mjtRankingClan(i, mTournament.getParams().mRankingIndiv1, mTournament.getParams().mRankingIndiv2, mTournament.getParams().mRankingIndiv3, mTournament.getParams().mRankingIndiv4, mTournament.getParams().mRankingIndiv5, mTournament.getDisplayClans(), mRoundOnly);
                final jdgRanking jdg = new jdgRanking(MainFrame.getMainFrame(), true, "General par Clan", i + 1, mTournament, model, 0);
                jdg.setVisible(true);
                break;
            }
        }
}//GEN-LAST:event_jbtGeneralClanActionPerformed
 @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtGGlobalClanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGGlobalClanActionPerformed
        for (int i = 0; i < mTournament.getRounds().size(); i++) {
            if (mRound == mTournament.getRounds().get(i)) {
                final mjtRankingClan model = new mjtRankingClan(i, mTournament.getParams().mRankingIndiv1, mTournament.getParams().mRankingIndiv2, mTournament.getParams().mRankingIndiv3, mTournament.getParams().mRankingIndiv4, mTournament.getParams().mRankingIndiv5, mTournament.getDisplayClans(), mRoundOnly);
                final HashMap<Criteria, mjtAnnexRank> annexForRankings = new HashMap<Criteria, mjtAnnexRank>();
                final HashMap<Criteria, mjtAnnexRank> annexAgainstRankings = new HashMap<Criteria, mjtAnnexRank>();
                for (int j = 0; j < mTournament.getParams().mCriterias.size(); j++) {
                    final Criteria crit = mTournament.getParams().mCriterias.get(j);
                    mjtAnnexRank annex = new mjtAnnexRankClan(i, crit, Parameters.C_RANKING_SUBTYPE_POSITIVE,
                            mTournament.getDisplayClans(), true,
                            mTournament.getParams().mRankingIndiv1, mTournament.getParams().mRankingIndiv2,
                            mTournament.getParams().mRankingIndiv3, mTournament.getParams().mRankingIndiv4,
                            mTournament.getParams().mRankingIndiv5, mRoundOnly);
                    annexForRankings.put(crit, annex);
                    annex = new mjtAnnexRankClan(i, crit, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                            mTournament.getDisplayClans(), true,
                            mTournament.getParams().mRankingIndiv1, mTournament.getParams().mRankingIndiv2,
                            mTournament.getParams().mRankingIndiv3, mTournament.getParams().mRankingIndiv4,
                            mTournament.getParams().mRankingIndiv5, mRoundOnly);
                    annexAgainstRankings.put(crit, annex);
                }
                final jdgGlobal jdg = new jdgGlobal(MainFrame.getMainFrame(), true, i + 1, mTournament, model, annexForRankings, annexAgainstRankings, false, false);
                jdg.setVisible(true);
                break;
            }
        }
    }//GEN-LAST:event_jbtGGlobalClanActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JButton jbtGGlobalClan;
    private javax.swing.JButton jbtGeneralClan;
    private javax.swing.JTable jtbRankingClan;
    private javax.swing.JTabbedPane jtpAnnexRank;
    // End of variables declaration//GEN-END:variables

    public void update() {

        final ArrayList<Round> v = new ArrayList<Round>();
        for (int i = 0; i < mTournament.getRounds().size(); i++) {
            if (mTournament.getRounds().get(i).getHour().before(mRound.getHour())) {
                v.add(mTournament.getRounds().get(i));
            }
        }
        v.add(mRound);

        mjtRankingClan mRankingClan = null;
        mRankingClan = new mjtRankingClan(v.size() - 1, mTournament.getParams().mRankingIndiv1, mTournament.getParams().mRankingIndiv2, mTournament.getParams().mRankingIndiv3, mTournament.getParams().mRankingIndiv4, mTournament.getParams().mRankingIndiv5, mTournament.getDisplayClans(), mRoundOnly);
        jtbRankingClan.setModel(mRankingClan);
        jtbRankingClan.setDefaultRenderer(String.class, mRankingClan);
        jtbRankingClan.setDefaultRenderer(Integer.class, mRankingClan);
        TableFormat.setColumnSize(jtbRankingClan);

        for (int i = 0; i < jtpAnnexRank.getComponentCount(); i++) {
            ((JPNAnnexRanking) jtpAnnexRank.getComponent(i)).update();
        }
        jtbRankingClan.setRowHeight(25);
    }

    
}
