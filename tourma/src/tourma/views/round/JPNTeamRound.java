/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JPNTeamRound.java
 *
 * Created on 20 juil. 2010, 10:47:49
 */
package tourma.views.round;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import tourma.MainFrame;
import tourma.data.Coach;
import tourma.data.Criteria;
import tourma.data.ETeamPairing;
import tourma.data.Match;
import tourma.data.Parameters;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.Tournament;
import tourma.tableModel.MjtAnnexRank;
import tourma.tableModel.MjtAnnexRankTeam;
import tourma.tableModel.MjtMatchTeams;
import tourma.tableModel.MjtRankingTeam;
import tourma.utils.TableFormat;
import tourma.views.report.JdgGlobal;
import tourma.views.report.JdgRanking;
import tourma.views.report.JdgRound;

/**
 *
 * @author Frederic Berger
 */
public final class JPNTeamRound extends javax.swing.JPanel {

    private final Round mRound;
    private final Tournament mTournament;
    private JTable mJtbTeamMatch = null;

    /**
     *
     */
    private boolean mRoundOnly = false;

    /**
     * Creates new form JPNTeamRound
     *
     * @param r
     * @param t
     */
    public JPNTeamRound(final Round r, final Tournament t) {
        initComponents();
        mRound = r;
        mTournament = t;

        if (mTournament.getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING) {
            final JScrollPane jsp = new JScrollPane();
            mJtbTeamMatch = new JTable();
            jsp.setViewportView(mJtbTeamMatch);
            jtpTeams.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MATCHS"), jsp);
            /*jpnTeam.add(jsp, BorderLayout.EAST);*/
            jbtShowMatchTeam.setVisible(true);
        } else {
            jbtShowMatchTeam.setVisible(false);
        }

        for (int i = 0; i < mTournament.getParams().getCriteriaCount(); i++) {
            final Criteria criteria = mTournament.getParams().getCriteria(i);
            ArrayList<Team> teams = new ArrayList<>();
            for (int cpt = 0; cpt < Tournament.getTournament().getTeamsCount(); cpt++) {
                teams.add(Tournament.getTournament().getTeam(cpt));
            }
            ArrayList<Coach> coachs = new ArrayList<>();
            for (int cpt = 0; cpt < Tournament.getTournament().getCoachsCount() ;cpt++) {
                coachs.add(Tournament.getTournament().getCoach(cpt));
            }
            final JPNAnnexRanking jpn = new JPNAnnexRanking(criteria.getName(),
                    criteria, Tournament.getTournament(),coachs,teams, mRound, false, true);
            jtpAnnexRank.add(criteria.getName(), jpn);
        }

        update();

    }

    /**
     * 
     * @param r 
     */
    public void setRoundOnly(boolean r)
    {
        mRoundOnly=r;
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
        jpnTeam = new javax.swing.JPanel();
        jtpTeams = new javax.swing.JTabbedPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtbRankingTeam = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jbtGeneralTeam = new javax.swing.JButton();
        jbtGlobal = new javax.swing.JButton();
        jbtShowMatchTeam = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jtpAnnexRank = new javax.swing.JTabbedPane();

        setLayout(new java.awt.BorderLayout());

        jSplitPane1.setDividerLocation(600);

        jpnTeam.setLayout(new java.awt.BorderLayout());

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("GeneralRankingKey"))); // NOI18N
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

        jtpTeams.addTab(bundle.getString("CLASSEMENT"), jScrollPane3); // NOI18N

        jpnTeam.add(jtpTeams, java.awt.BorderLayout.CENTER);

        jbtGeneralTeam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Document.png"))); // NOI18N
        jbtGeneralTeam.setText(bundle.getString("GeneralRankingKey")); // NOI18N
        jbtGeneralTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGeneralTeamActionPerformed(evt);
            }
        });
        jPanel8.add(jbtGeneralTeam);

        jbtGlobal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Document.png"))); // NOI18N
        jbtGlobal.setText(bundle.getString("GlobalRankingKey")); // NOI18N
        jbtGlobal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGlobalActionPerformed(evt);
            }
        });
        jPanel8.add(jbtGlobal);

        jbtShowMatchTeam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Document.png"))); // NOI18N
        jbtShowMatchTeam.setText(bundle.getString("TeamMatchViewKey")); // NOI18N
        jbtShowMatchTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtShowMatchTeamActionPerformed(evt);
            }
        });
        jPanel8.add(jbtShowMatchTeam);

        jpnTeam.add(jPanel8, java.awt.BorderLayout.SOUTH);

        jSplitPane1.setLeftComponent(jpnTeam);

        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(jtpAnnexRank, java.awt.BorderLayout.CENTER);

        jSplitPane1.setRightComponent(jPanel1);

        add(jSplitPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtGeneralTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGeneralTeamActionPerformed
        for (int i = 0; i < mTournament.getRoundsCount(); i++) {
            if (mRound == mTournament.getRound(i)) {
                ArrayList<Team> teams=new ArrayList<>();
        for (int cpt=0;cpt<mTournament.getTeamsCount(); cpt++)
        {
            teams.add(mTournament.getTeam(cpt));
        }
                final MjtRankingTeam model = new MjtRankingTeam(mTournament.getParams().isTeamVictoryOnly(), i,
                        teams, mRoundOnly);
                final JdgRanking jdg = new JdgRanking(MainFrame.getMainFrame(), true, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("GENERAL PAR EQUIPE"), i + 1, mTournament, model, 0);
                jdg.setVisible(true);
                break;
            }
        }
}//GEN-LAST:event_jbtGeneralTeamActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtShowMatchTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtShowMatchTeamActionPerformed
        for (int i = 0; i < mTournament.getRoundsCount(); i++) {
            if (mRound == mTournament.getRound(i)) {
                final JdgRound jdg = new JdgRound(MainFrame.getMainFrame(), true, mRound, i + 1, mTournament, true, true);
                jdg.setVisible(true);
                break;
            }
        }
}//GEN-LAST:event_jbtShowMatchTeamActionPerformed

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtGlobalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGlobalActionPerformed
        for (int i = 0; i < mTournament.getRoundsCount(); i++) {
            if (mRound == mTournament.getRound(i)) {
                ArrayList<Team> teams = new ArrayList<>();
                for (int cpt = 0; cpt < mTournament.getTeamsCount(); cpt++) {
                    teams.add(mTournament.getTeam(cpt));
                }

                final MjtRankingTeam model = new MjtRankingTeam(mTournament.getParams().isTeamVictoryOnly(), i, teams, mRoundOnly);
                final HashMap<Criteria, MjtAnnexRank> annexForRankings = new HashMap<>();
                final HashMap<Criteria, MjtAnnexRank> annexAgainstRankings = new HashMap<>();

                for (int j = 0; j < mTournament.getParams().getCriteriaCount(); j++) {
                    final Criteria crit = mTournament.getParams().getCriteria(j);
                    MjtAnnexRank annex = new MjtAnnexRankTeam(i, crit, Parameters.C_RANKING_SUBTYPE_POSITIVE,
                            teams, true, mTournament.getParams().getRankingIndiv1(), mTournament.getParams().getRankingIndiv2(), mTournament.getParams().getRankingIndiv3(), mTournament.getParams().getRankingIndiv4(), mTournament.getParams().getRankingIndiv5(), mRoundOnly);
                    annexForRankings.put(crit, annex);
                    annex = new MjtAnnexRankTeam(i, crit, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                            teams, true, mTournament.getParams().getRankingIndiv1(), mTournament.getParams().getRankingIndiv2(), mTournament.getParams().getRankingIndiv3(), mTournament.getParams().getRankingIndiv4(), mTournament.getParams().getRankingIndiv5(), mRoundOnly);
                    annexAgainstRankings.put(crit, annex);
                }
                final JdgGlobal jdg = new JdgGlobal(MainFrame.getMainFrame(), true, i + 1, mTournament, model, annexForRankings, annexAgainstRankings, false, false);
                jdg.setVisible(true);
                break;
            }
        }
    }//GEN-LAST:event_jbtGlobalActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JButton jbtGeneralTeam;
    private javax.swing.JButton jbtGlobal;
    private javax.swing.JButton jbtShowMatchTeam;
    private javax.swing.JPanel jpnTeam;
    private javax.swing.JTable jtbRankingTeam;
    private javax.swing.JTabbedPane jtpAnnexRank;
    private javax.swing.JTabbedPane jtpTeams;
    // End of variables declaration//GEN-END:variables

    /**
     * Upadtae Panel
     */
    public void update() {

        final ArrayList<Round> v = new ArrayList<>();
        for (int i = 0; i < mTournament.getRoundsCount(); i++) {
            if (mTournament.getRound(i).getHour().before(mRound.getHour())) {
                v.add(mTournament.getRound(i));
            }
        }
        v.add(mRound);

        for (int i = 0; i < jtpAnnexRank.getComponentCount(); i++) {
            final JPNAnnexRanking jpn = (JPNAnnexRanking) jtpAnnexRank.getComponent(i);
            jpn.update();
        }

        MjtRankingTeam mRankingTeam;
        ArrayList<Team> teams = new ArrayList<>();
        for (int i = 0; i < mTournament.getTeamsCount(); i++) {
            teams.add(mTournament.getTeam(i));
        }
        mRankingTeam = new MjtRankingTeam(mTournament.getParams().isTeamVictoryOnly(), v.size() - 1, teams, mRoundOnly);
        jtbRankingTeam.setModel(mRankingTeam);
        jtbRankingTeam.setDefaultRenderer(String.class, mRankingTeam);
        jtbRankingTeam.setDefaultRenderer(Integer.class, mRankingTeam);

        TableFormat.setColumnSize(jtbRankingTeam);

        if (mJtbTeamMatch != null) {
            for (int i = 0; i < mRound.getMatchsCount(); i++) {
                final Match m = mRound.getMatch(i);
                final Team team1 = (Team) m.getCompetitor1();
                final Team team2 = (Team) m.getCompetitor2();
                if (!teams.contains(team1)) {
                    teams.add(team1);
                }
                if (!teams.contains(team2)) {
                    teams.add(team2);
                }
            }
            final MjtMatchTeams model = new MjtMatchTeams(teams, mRound);
            mJtbTeamMatch.setModel(model);
            mJtbTeamMatch.setDefaultRenderer(String.class, model);
            mJtbTeamMatch.setDefaultRenderer(Integer.class, model);
            TableFormat.setColumnSize(mJtbTeamMatch);
            mJtbTeamMatch.setRowHeight(30);
        }

        jtbRankingTeam.setRowHeight(30);

    }
    private static final Logger LOG = Logger.getLogger(JPNTeamRound.class.getName());
    
     private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException {
        throw new java.io.NotSerializableException(getClass().getName());
    }

    private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException {
        throw new java.io.NotSerializableException(getClass().getName());
    }
    
}
