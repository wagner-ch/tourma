package tourma.views.round;

import java.util.ArrayList;
import tourma.MainFrame;
import tourma.data.Coach;
import tourma.data.Group;
import tourma.data.Tournament;
import tourma.languages.Translate;
import tourma.tableModel.MjtRanking;
import tourma.tableModel.MjtRankingIndiv;
import tourma.utils.display.TableFormat;
import tourma.views.report.JdgRanking;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JPNGroup.java
 *
 * Created on 21 mai 2011, 23:18:17
 */
/**
 *
 * @author Administrateur
 */
public final class JPNGroup extends javax.swing.JPanel {

    private final Tournament mTournament;
    private final Group mGroup;
    private final int mRoundNumber;

    /**
     *
     */
    private  boolean mRoundOnly=false;
    
    /** Creates new form JPNGroup
     * @param t
     * @param g
     * @param roundNumber */
    public JPNGroup(final Tournament t,final  Group g,final int roundNumber) {
        initComponents();
        mTournament = t;
        mGroup = g;
        mRoundNumber = roundNumber;
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
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "PMD"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtbGroup = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jbtGeneral = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jtbGroup.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtbGroup);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jbtGeneral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Document.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        jbtGeneral.setText(bundle.getString("GeneralRankingKey")); // NOI18N
        jbtGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGeneralActionPerformed(evt);
            }
        });
        jPanel1.add(jbtGeneral);

        add(jPanel1, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Update panel
     */
    public void update() {
        final ArrayList<Coach> ArrayList = new ArrayList<>();

        for (int i = 0; i < mTournament.getCoachsCount(); i++) {
            final Coach c = mTournament.getCoach(i);
            for (int j = 0; j < mGroup.getRosterCount(); j++) {
                if (mGroup.getRoster(j).getName().equals(c.getRoster().getName())) {
                    ArrayList.add(c);
                    break;
                }
            }
        }

        final MjtRankingIndiv tableModel = new MjtRankingIndiv(mRoundNumber, mTournament.getParams().getRankingIndiv1(), mTournament.getParams().getRankingIndiv2(), mTournament.getParams().getRankingIndiv3(), mTournament.getParams().getRankingIndiv4(), mTournament.getParams().getRankingIndiv5(),
                ArrayList, mTournament.getParams().isTeamTournament(),mRoundOnly,false);
        jtbGroup.setModel(tableModel);
        jtbGroup.setDefaultRenderer(String.class, tableModel);
        jtbGroup.setDefaultRenderer(Integer.class, tableModel);

        jtbGroup.setRowHeight(25);
        TableFormat.setColumnSize(jtbGroup);
    }

    private static final String CS_Group="GENERAL PAR GROUPE";
    

     @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGeneralActionPerformed
        final JdgRanking jdg = new JdgRanking(MainFrame.getMainFrame(), true, 
               Translate.translate(CS_Group)+": " +mGroup.getName(), mRoundNumber, mTournament, (MjtRanking) jtbGroup.getModel(), 0);
        jdg.setVisible(true);
}//GEN-LAST:event_jbtGeneralActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtGeneral;
    private javax.swing.JTable jtbGroup;
    // End of variables declaration//GEN-END:variables

    
    
}
