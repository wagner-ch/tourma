/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.views.round;

import java.awt.Color;
import java.awt.Font;
import java.rmi.RemoteException;
import java.util.logging.Logger;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.Match;
import tourma.data.Team;
import tourma.data.TeamMatch;
import tourma.data.Tournament;
import tourma.data.Value;
import tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public class JPNMatch extends javax.swing.JPanel {

    /**
     * Creates new form JPNMatch
     *
     * @param m
     * @param winner
     */
    public JPNMatch(final Match m, final boolean winner) {
        initComponents();

        if (Tournament.getTournament().getParams().isUseColor()) {
            jlbPlayer1.setBackground(m.getCompetitor1().getColor());
            jlbPlayer2.setBackground(m.getCompetitor2().getColor());

            jPanel2.setBackground(m.getCompetitor1().getColor());
            jPanel4.setBackground(m.getCompetitor2().getColor());
        } else {
            jlbPlayer1.setBackground(Color.WHITE);
            jlbPlayer2.setBackground(Color.LIGHT_GRAY);

            jPanel2.setBackground(Color.WHITE);
            jPanel4.setBackground(Color.LIGHT_GRAY);
        }

        if (m instanceof CoachMatch) {

            final Value v = ((CoachMatch) m).getValue(Tournament.getTournament().getParams().getCriteria(0));
            if ((v.getValue1() == -1) || (v.getValue2() == -1)) {
                jlbScore1.setText("");
                jlbScore2.setText("");
            } else {

                if (m.getWinner() == m.getCompetitor1()) {
                    jlbPlayer1.setFont(jlbPlayer1.getFont().deriveFont(Font.BOLD));
                    jlbPlayer2.setFont(jlbPlayer2.getFont().deriveFont(Font.PLAIN));
                }

                if (m.getWinner() == m.getCompetitor2()) {
                    jlbPlayer2.setFont(jlbPlayer1.getFont().deriveFont(Font.BOLD));
                    jlbPlayer1.setFont(jlbPlayer2.getFont().deriveFont(Font.PLAIN));
                }
                jlbScore1.setText(Integer.toString(v.getValue1()));
                jlbScore2.setText(Integer.toString(v.getValue2()));

            }
        }

        if (m instanceof TeamMatch) {
            int nbVictories = ((TeamMatch) m).getVictories((Team) m.getCompetitor1());
            int nbDraw = ((TeamMatch) m).getDraw((Team) m.getCompetitor1());
            int nbLoss = ((TeamMatch) m).getVictories((Team) m.getCompetitor2());

            if (nbVictories + nbLoss + nbDraw != Tournament.getTournament().getParams().getTeamMatesNumber()) {
                jlbScore1.setText("");
                jlbScore2.setText("");
                jlbTag.setText("#");
            } else {
                jlbTag.setText("");
                if (m.getWinner() == m.getCompetitor1()) {
                    Font f = jlbPlayer1.getFont();
                    Font fb = f.deriveFont(Font.ITALIC | Font.BOLD);
                    jlbPlayer1.setFont(fb);
                    jlbPlayer2.setFont(f);
                    jlbPlayer2.setForeground(Color.DARK_GRAY);
                    jlbScore1.setFont(fb);
                    jlbScore2.setFont(f);
                    jlbScore2.setForeground(Color.DARK_GRAY);
                }

                if (m.getWinner() == m.getCompetitor2()) {
                    Font f = jlbPlayer2.getFont();
                    Font fb = f.deriveFont(Font.ITALIC | Font.BOLD);
                    jlbPlayer2.setFont(fb);
                    jlbPlayer1.setFont(f);
                    jlbPlayer1.setForeground(Color.DARK_GRAY);
                    jlbScore2.setFont(fb);
                    jlbScore1.setFont(f);
                    jlbScore1.setForeground(Color.DARK_GRAY);
                }

                jlbScore1.setText(Integer.toString(nbVictories));
                jlbScore2.setText(Integer.toString(nbLoss));

            }
        }

        if ((m.getCompetitor1() != Coach.getNullCoach()) && (m.getCompetitor1() != Team.getNullTeam())) {
            jlbPlayer1.setText(m.getCompetitor1().getName());
        } else {
            jlbPlayer1.setText(StringConstants.CS_NULL);
        }
        if ((m.getCompetitor2() != Coach.getNullCoach()) && (m.getCompetitor2() != Team.getNullTeam())) {
            jlbPlayer2.setText(m.getCompetitor2().getName());
        } else {
            jlbPlayer2.setText(StringConstants.CS_NULL);
        }

        if (!winner) {
            jPanel1.setBackground(Color.LIGHT_GRAY);
            this.setBackground(Color.LIGHT_GRAY);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "PMD"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jlbPlayer1 = new javax.swing.JLabel();
        jlbScore1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jlbPlayer2 = new javax.swing.JLabel();
        jlbScore2 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jlbTag = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        setMaximumSize(new java.awt.Dimension(100, 50));
        setMinimumSize(new java.awt.Dimension(100, 50));
        setPreferredSize(new java.awt.Dimension(150, 50));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(100, 50));
        jPanel1.setPreferredSize(new java.awt.Dimension(150, 50));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(150, 25));
        jPanel2.setMinimumSize(new java.awt.Dimension(150, 25));
        jPanel2.setPreferredSize(new java.awt.Dimension(150, 25));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jlbPlayer1.setBackground(new java.awt.Color(255, 255, 255));
        jlbPlayer1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        jlbPlayer1.setText(bundle.getString("PLAYER 1")); // NOI18N
        jlbPlayer1.setMaximumSize(new java.awt.Dimension(135, 25));
        jlbPlayer1.setMinimumSize(new java.awt.Dimension(135, 25));
        jlbPlayer1.setPreferredSize(new java.awt.Dimension(135, 25));
        jPanel2.add(jlbPlayer1, java.awt.BorderLayout.WEST);

        jlbScore1.setBackground(new java.awt.Color(255, 255, 255));
        jlbScore1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbScore1.setText(bundle.getString("0")); // NOI18N
        jlbScore1.setMaximumSize(new java.awt.Dimension(15, 25));
        jlbScore1.setMinimumSize(new java.awt.Dimension(15, 25));
        jlbScore1.setPreferredSize(new java.awt.Dimension(15, 25));
        jPanel2.add(jlbScore1, java.awt.BorderLayout.EAST);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setMinimumSize(new java.awt.Dimension(5, 25));
        jSeparator2.setPreferredSize(new java.awt.Dimension(5, 25));
        jPanel2.add(jSeparator2, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);
        jPanel1.add(jSeparator1, java.awt.BorderLayout.CENTER);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setMaximumSize(new java.awt.Dimension(150, 25));
        jPanel4.setMinimumSize(new java.awt.Dimension(150, 25));
        jPanel4.setPreferredSize(new java.awt.Dimension(150, 25));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jlbPlayer2.setBackground(new java.awt.Color(255, 255, 255));
        jlbPlayer2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbPlayer2.setText(bundle.getString("PLAYER 2")); // NOI18N
        jlbPlayer2.setMaximumSize(new java.awt.Dimension(135, 25));
        jlbPlayer2.setMinimumSize(new java.awt.Dimension(135, 25));
        jlbPlayer2.setPreferredSize(new java.awt.Dimension(135, 25));
        jPanel4.add(jlbPlayer2, java.awt.BorderLayout.WEST);

        jlbScore2.setBackground(new java.awt.Color(255, 255, 255));
        jlbScore2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbScore2.setText(bundle.getString("0")); // NOI18N
        jlbScore2.setMaximumSize(new java.awt.Dimension(15, 25));
        jlbScore2.setMinimumSize(new java.awt.Dimension(15, 25));
        jlbScore2.setPreferredSize(new java.awt.Dimension(15, 25));
        jPanel4.add(jlbScore2, java.awt.BorderLayout.EAST);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator3.setMinimumSize(new java.awt.Dimension(5, 25));
        jSeparator3.setPreferredSize(new java.awt.Dimension(5, 25));
        jPanel4.add(jSeparator3, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel4, java.awt.BorderLayout.SOUTH);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        jlbTag.setBackground(new java.awt.Color(255, 255, 255));
        jlbTag.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTag.setText(bundle.getString("#")); // NOI18N
        jlbTag.setMaximumSize(new java.awt.Dimension(10, 50));
        jlbTag.setMinimumSize(new java.awt.Dimension(10, 50));
        jlbTag.setPreferredSize(new java.awt.Dimension(10, 50));
        add(jlbTag, java.awt.BorderLayout.WEST);
        jlbTag.getAccessibleContext().setAccessibleName(bundle.getString("JLBID")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel jlbPlayer1;
    private javax.swing.JLabel jlbPlayer2;
    private javax.swing.JLabel jlbScore1;
    private javax.swing.JLabel jlbScore2;
    private javax.swing.JLabel jlbTag;
    // End of variables declaration//GEN-END:variables
    private static final Logger LOG = Logger.getLogger(JPNMatch.class.getName());

    /* private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException {
        throw new java.io.NotSerializableException(getClass().getName());
    }

    private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException {
        throw new java.io.NotSerializableException(getClass().getName());
    }*/
}
