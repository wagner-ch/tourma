/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JPNAnnexRanking.java
 *
 * Created on 13 mai 2011, 21:52:44
 */
package tourma.views.round;

import java.util.ArrayList;
import java.util.ResourceBundle;
import tourma.MainFrame;
import tourma.data.Coach;
import tourma.data.Criteria;
import tourma.data.Parameters;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.Tournament;
import tourma.tableModel.mjtAnnexRank;
import tourma.tableModel.mjtAnnexRankClan;
import tourma.tableModel.mjtAnnexRankIndiv;
import tourma.tableModel.mjtAnnexRankTeam;
import tourma.utils.TableFormat;
import tourma.views.report.jdgRanking;

/**
 *
 * @author Administrateur
 */
public class JPNAnnexRanking extends javax.swing.JPanel {
    private static final ResourceBundle language = ResourceBundle.getBundle("tourma/languages/language");

    String mName = "";
    Criteria mCriteria = null;
    Tournament mTour = null;
    Round mRound = null;
    boolean mClan;
    boolean mTeam;
    public boolean mRoundOnly = false;
    ArrayList<Coach> mCoachs = null;
    ArrayList<Team> mTeams = null;

    /**
     * Creates new form JPNAnnexRanking
     */
    public JPNAnnexRanking(final String name, final Criteria criteria, final Tournament tour, final Round round, final boolean clan, final boolean team) {
        this(name, criteria, tour, round, clan, team, tour.getCoachs(), tour.getTeams());
    }

    public JPNAnnexRanking(final String name, final Criteria criteria, final Tournament tour, final Round round, final boolean clan, final boolean team, final ArrayList v, final ArrayList t) {
        initComponents();
        mName = name;
        mCriteria = criteria;
        mTour = tour;
        mRound = round;
        mClan = clan;
        mTeam = team;

        mCoachs = v;
        mTeams = t;

        int roundnumber = 0;
        while (round != tour.getRounds().get(roundnumber)) {
            roundnumber++;
        }

        if (clan) {
            jbtPositive.setText(name + language.getString("(CLAN)"));
            jbtNegative.setText(name + language.getString("(ADVERSAIRES)"));
        } else {
            if (team) {
                jbtPositive.setText(name + language.getString("(EQUIPE)"));
                jbtNegative.setText(name + language.getString("(ADVERSAIRES)"));
            } else {
                jbtPositive.setText(name + language.getString("(COACH)"));
                jbtNegative.setText(name + language.getString("(ADVERSAIRES)"));
            }
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

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbPositive = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jbtPositive = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtbNegative = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jbtNegative = new javax.swing.JButton();

        jLabel1.setText(language.getString("JLABEL1")); // NOI18N

        setBorder(javax.swing.BorderFactory.createTitledBorder("Annex Ranking"));
        setLayout(new java.awt.GridLayout(2, 1, 1, 1));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jtbPositive.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtbPositive);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jbtPositive.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Document.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        jbtPositive.setText(bundle.getString("Positive")); // NOI18N
        jbtPositive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtPositiveActionPerformed(evt);
            }
        });
        jPanel3.add(jbtPositive);

        jPanel1.add(jPanel3, java.awt.BorderLayout.SOUTH);

        add(jPanel1);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jtbNegative.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jtbNegative);

        jPanel2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jbtNegative.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Document.png"))); // NOI18N
        jbtNegative.setText(bundle.getString("Negative")); // NOI18N
        jbtNegative.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtNegativeActionPerformed(evt);
            }
        });
        jPanel4.add(jbtNegative);

        jPanel2.add(jPanel4, java.awt.BorderLayout.SOUTH);

        add(jPanel2);
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtPositiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtPositiveActionPerformed
         for (int i = 0; i < mTour.getRounds().size(); i++) {
             if (mRound == mTour.getRounds().get(i)) {
                 mjtAnnexRank model;
                 if (mClan) {
                     model = new mjtAnnexRankClan(i, mCriteria, Parameters.C_RANKING_SUBTYPE_POSITIVE,
                             mTour.getDisplayClans(), true, mTour.getParams().mRankingIndiv1,
                             mTour.getParams().mRankingIndiv2, mTour.getParams().mRankingIndiv3,
                             mTour.getParams().mRankingIndiv4, mTour.getParams().mRankingIndiv5, mRoundOnly);
                 } else {
                     if (mTeam) {
                         model = new mjtAnnexRankTeam(i, mCriteria, Parameters.C_RANKING_SUBTYPE_POSITIVE,
                                 mTeams, true, mTour.getParams().mRankingIndiv1,
                                 mTour.getParams().mRankingIndiv2, mTour.getParams().mRankingIndiv3,
                                 mTour.getParams().mRankingIndiv4, mTour.getParams().mRankingIndiv5, mRoundOnly);

                     } else {
                         model = new mjtAnnexRankIndiv(i, mCriteria, Parameters.C_RANKING_SUBTYPE_POSITIVE, mCoachs, true, mTour.getParams().mRankingIndiv1, mTour.getParams().mRankingIndiv2, mTour.getParams().mRankingIndiv3, mTour.getParams().mRankingIndiv4, mTour.getParams().mRankingIndiv5, mTour.getParams().mTeamTournament, mRoundOnly);
                     }
                 }
                 final StringBuffer a = new StringBuffer(mCriteria.mName);
                 if (mClan) {
                     a.append(language.getString(" PAR LE CLAN"));
                 } else {
                     if (mTeam) {
                         a.append(language.getString(" PAR L'ÉQUIPE"));
                     } else {
                         a.append(language.getString(" PAR LE COACH"));
                     }
                 }
                 final jdgRanking jdg = new jdgRanking(MainFrame.getMainFrame(), true, a.toString(), i + 1, mTour, model, 0);
                 jdg.setVisible(true);
                 break;
             }
         }
    }//GEN-LAST:event_jbtPositiveActionPerformed

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtNegativeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtNegativeActionPerformed
         for (int i = 0; i < mTour.getRounds().size(); i++) {
             if (mRound == mTour.getRounds().get(i)) {
                 mjtAnnexRank model;
                 if (mClan) {
                     model = new mjtAnnexRankClan(i, mCriteria, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                             mTour.getDisplayClans(), true, mTour.getParams().mRankingIndiv1,
                             mTour.getParams().mRankingIndiv2, mTour.getParams().mRankingIndiv3,
                             mTour.getParams().mRankingIndiv4, mTour.getParams().mRankingIndiv5, mRoundOnly);
                 } else {
                     if (mTeam) {
                         model = new mjtAnnexRankTeam(i, mCriteria, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                                 mTeams, true, mTour.getParams().mRankingIndiv1,
                                 mTour.getParams().mRankingIndiv2, mTour.getParams().mRankingIndiv3,
                                 mTour.getParams().mRankingIndiv4, mTour.getParams().mRankingIndiv5, mRoundOnly);
                     } else {
                         model = new mjtAnnexRankIndiv(i, mCriteria, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                                 mCoachs, true, mTour.getParams().mRankingIndiv1,
                                 mTour.getParams().mRankingIndiv2, mTour.getParams().mRankingIndiv3,
                                 mTour.getParams().mRankingIndiv4, mTour.getParams().mRankingIndiv5,
                                 mTour.getParams().mTeamTournament, mRoundOnly);
                     }
                 }

                 final StringBuffer a = new StringBuffer(mCriteria.mName);
                 if (mClan) {
                     a.append(language.getString(" CONTRE LE CLAN"));
                 } else {
                     if (mTeam) {
                         a.append(language.getString(" CONTRE L'ÉQUIPE"));
                     } else {
                         a.append(language.getString(" CONTRE LE COACH"));
                     }
                 }
                 final jdgRanking jdg = new jdgRanking(MainFrame.getMainFrame(), true, a.toString(), i + 1, mTour, model, 0);
                 jdg.setVisible(true);
                 break;
             }
         }
    }//GEN-LAST:event_jbtNegativeActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtNegative;
    private javax.swing.JButton jbtPositive;
    private javax.swing.JTable jtbNegative;
    private javax.swing.JTable jtbPositive;
    // End of variables declaration//GEN-END:variables

   

    public void update() {

        if (mRound != null) {
            final ArrayList<Round> v = new ArrayList<>();
            for (int i = 0; i < mTour.getRounds().size(); i++) {
                if (mTour.getRounds().get(i).getHour().before(mRound.getHour())) {
                    v.add(mTour.getRounds().get(i));
                }
            }
            v.add(mRound);

            mjtAnnexRank modelPos ;
            mjtAnnexRank modelNeg ;

            if (mClan) {
                modelPos =
                        new mjtAnnexRankClan(v.size() - 1, mCriteria, Parameters.C_RANKING_SUBTYPE_POSITIVE,
                        mTour.getDisplayClans(), true, mTour.getParams().mRankingIndiv1, mTour.getParams().mRankingIndiv2, mTour.getParams().mRankingIndiv3, mTour.getParams().mRankingIndiv4, mTour.getParams().mRankingIndiv5, mRoundOnly);
                modelNeg =
                        new mjtAnnexRankClan(v.size() - 1, mCriteria, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                        mTour.getDisplayClans(), true, mTour.getParams().mRankingIndiv1, mTour.getParams().mRankingIndiv2, mTour.getParams().mRankingIndiv3, mTour.getParams().mRankingIndiv4, mTour.getParams().mRankingIndiv5, mRoundOnly);
            } else {
                if (mTeam) {
                    modelPos =
                            new mjtAnnexRankTeam(v.size() - 1, mCriteria, Parameters.C_RANKING_SUBTYPE_POSITIVE,
                            mTeams, true, mTour.getParams().mRankingIndiv1, mTour.getParams().mRankingIndiv2, mTour.getParams().mRankingIndiv3, mTour.getParams().mRankingIndiv4, mTour.getParams().mRankingIndiv5, mRoundOnly);
                    modelNeg =
                            new mjtAnnexRankTeam(v.size() - 1, mCriteria, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                            mTeams, true, mTour.getParams().mRankingIndiv1, mTour.getParams().mRankingIndiv2, mTour.getParams().mRankingIndiv3, mTour.getParams().mRankingIndiv4, mTour.getParams().mRankingIndiv5, mRoundOnly);
                } else {
                    modelPos =
                            new mjtAnnexRankIndiv(v.size() - 1, mCriteria, Parameters.C_RANKING_SUBTYPE_POSITIVE, this.mCoachs, true, mTour.getParams().mRankingIndiv1, mTour.getParams().mRankingIndiv2, mTour.getParams().mRankingIndiv3, mTour.getParams().mRankingIndiv4, mTour.getParams().mRankingIndiv5, mTour.getParams().mTeamTournament, mRoundOnly);
                    modelNeg =
                            new mjtAnnexRankIndiv(v.size() - 1, mCriteria, Parameters.C_RANKING_SUBTYPE_NEGATIVE, this.mCoachs, true, mTour.getParams().mRankingIndiv1, mTour.getParams().mRankingIndiv2, mTour.getParams().mRankingIndiv3, mTour.getParams().mRankingIndiv4, mTour.getParams().mRankingIndiv5, mTour.getParams().mTeamTournament, mRoundOnly);
                }
            }
            jtbNegative.setModel(modelNeg);
            jtbPositive.setModel(modelPos);

            jtbNegative.setDefaultRenderer(String.class, modelNeg);
            jtbNegative.setDefaultRenderer(Integer.class, modelNeg);

            jtbPositive.setDefaultRenderer(String.class, modelPos);
            jtbPositive.setDefaultRenderer(Integer.class, modelPos);

            TableFormat.setColumnSize(jtbPositive);
            TableFormat.setColumnSize(jtbNegative);

            jtbPositive.setRowHeight(25);
            jtbNegative.setRowHeight(25);
        }
    }
}
