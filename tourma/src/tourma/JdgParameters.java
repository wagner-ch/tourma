/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import tourma.data.EIndivPairing;
import tourma.data.ETeamPairing;
import tourma.data.Parameters;
import tourma.data.RosterType;
import tourma.data.Tournament;
import tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public final class JdgParameters extends javax.swing.JDialog {

    static final ResourceBundle language = ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE);
    private Parameters mParams;

    /**
     * Creates new form JdgParameters
     * @param parent
     * @param modal
     */
    public JdgParameters(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();


        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final GraphicsDevice gs = ge.getDefaultScreenDevice();
        final DisplayMode dmode = gs.getDisplayMode();

        this.setSize(640, 480);

            final int screenWidth = dmode.getWidth();
            final int screenHeight = dmode.getHeight();
            this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);


        mParams = Tournament.getTournament().getParams();

        // Load current parameters

        if (mParams.getGame() == RosterType.C_BLOOD_BOWL) {
            jrbBloodBowl.setSelected(true);
        } else {
            jrbDreadBall.setSelected(true);
        }


        if (mParams.isTeamTournament()) {
            jrbTeam.setSelected(true);
        } else {
            jrbIndividual.setSelected(true);
        }

        jcbMultiroster.setSelected(mParams.isMultiRoster());

        if (mParams.getTeamPairing() == ETeamPairing.INDIVIDUAL_PAIRING) {
            jrbIndividualTeamPairing.setSelected(true);
        } else {
            jrbTeamPairing.setSelected(true);
        }

        switch (mParams.getTeamIndivPairing()) {

            case FREE:
                jrbIndivPairingManual.setSelected(true);
                break;
            case RANDOM:
                jrbIndivPairingRandom.setSelected(true);
                break;
            case NAF:
                jrbIndivPairingNaf.setSelected(true);
                break;
            case RANKING:
            default:
                jrbIndivPairingByRanking.setSelected(true);
                break;
        }

        jspCoachNumber.setValue(mParams.getTeamMatesNumber());
        jckSubstitutes.setEnabled(mParams.isSubstitutes());

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

        btgGame = new javax.swing.ButtonGroup();
        btgTeam = new javax.swing.ButtonGroup();
        btgTeamPairing = new javax.swing.ButtonGroup();
        btgIndivTeamPairing = new javax.swing.ButtonGroup();
        jPanel5 = new javax.swing.JPanel();
        jcbMultiroster = new javax.swing.JCheckBox();
        jpnGame = new javax.swing.JPanel();
        jrbDreadBall = new javax.swing.JRadioButton();
        jrbBloodBowl = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jpnByTeam = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jpnPairing = new javax.swing.JPanel();
        jrbIndividualTeamPairing = new javax.swing.JRadioButton();
        jrbTeamPairing = new javax.swing.JRadioButton();
        jpnNbCoachs = new javax.swing.JPanel();
        jlbNbCoachs = new javax.swing.JLabel();
        jspCoachNumber = new javax.swing.JSpinner();
        jckSubstitutes = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jpnMisc = new javax.swing.JPanel();
        jcxMultipleRoster = new javax.swing.JCheckBox();
        jpnIndivTeamRanking = new javax.swing.JPanel();
        jrbIndivPairingByRanking = new javax.swing.JRadioButton();
        jrbIndivPairingRandom = new javax.swing.JRadioButton();
        jrbIndivPairingManual = new javax.swing.JRadioButton();
        jrbIndivPairingNaf = new javax.swing.JRadioButton();
        jpnTeam = new javax.swing.JPanel();
        jrbTeam = new javax.swing.JRadioButton();
        jrbIndividual = new javax.swing.JRadioButton();
        jpnOK = new javax.swing.JPanel();
        jbtOK = new javax.swing.JButton();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        jcbMultiroster.setText(bundle.getString("MULTI ROSTER")); // NOI18N
        jPanel5.add(jcbMultiroster);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jpnGame.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("GameKind"))); // NOI18N

        btgGame.add(jrbDreadBall);
        jrbDreadBall.setText("DreadBall");
        jrbDreadBall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbDreadBallActionPerformed(evt);
            }
        });
        jpnGame.add(jrbDreadBall);

        btgGame.add(jrbBloodBowl);
        jrbBloodBowl.setSelected(true);
        jrbBloodBowl.setText("Blood Bowl");
        jrbBloodBowl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbBloodBowlActionPerformed(evt);
            }
        });
        jpnGame.add(jrbBloodBowl);

        getContentPane().add(jpnGame, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jpnByTeam.setLayout(new java.awt.GridLayout(1, 2));

        jPanel3.setLayout(new java.awt.BorderLayout());

        jpnPairing.setBorder(javax.swing.BorderFactory.createTitledBorder("Appariement"));
        jpnPairing.setLayout(new java.awt.GridLayout(2, 1));

        btgTeamPairing.add(jrbIndividualTeamPairing);
        jrbIndividualTeamPairing.setText(bundle.getString("IndividualPairing")); // NOI18N
        jrbIndividualTeamPairing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbIndividualTeamPairingActionPerformed(evt);
            }
        });
        jpnPairing.add(jrbIndividualTeamPairing);

        btgTeamPairing.add(jrbTeamPairing);
        jrbTeamPairing.setText(bundle.getString("TeamPairing")); // NOI18N
        jrbTeamPairing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbTeamPairingActionPerformed(evt);
            }
        });
        jpnPairing.add(jrbTeamPairing);

        jPanel3.add(jpnPairing, java.awt.BorderLayout.CENTER);

        jpnNbCoachs.setBorder(javax.swing.BorderFactory.createTitledBorder("Nombre de coéquipiers"));
        jpnNbCoachs.setLayout(new java.awt.GridLayout(2, 2));

        jlbNbCoachs.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbNbCoachs.setText(language.getString("CoachNumber")); // NOI18N
        jpnNbCoachs.add(jlbNbCoachs);

        jspCoachNumber.setModel(new javax.swing.SpinnerNumberModel(1, 1, 16, 1));
        jspCoachNumber.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jspCoachNumberStateChanged(evt);
            }
        });
        jpnNbCoachs.add(jspCoachNumber);

        jckSubstitutes.setText(bundle.getString("AllowedSubstitutes")); // NOI18N
        jckSubstitutes.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jckSubstitutes.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jckSubstitutes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jckSubstitutesActionPerformed(evt);
            }
        });
        jpnNbCoachs.add(jckSubstitutes);
        jpnNbCoachs.add(jLabel3);

        jPanel3.add(jpnNbCoachs, java.awt.BorderLayout.NORTH);

        jpnMisc.setBorder(javax.swing.BorderFactory.createTitledBorder("Divers"));
        jpnMisc.setLayout(new java.awt.GridLayout(1, 1));

        jcxMultipleRoster.setText(bundle.getString("UseSeveralRosters")); // NOI18N
        jcxMultipleRoster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxMultipleRosterActionPerformed(evt);
            }
        });
        jpnMisc.add(jcxMultipleRoster);

        jPanel3.add(jpnMisc, java.awt.BorderLayout.SOUTH);

        jpnByTeam.add(jPanel3);

        jpnIndivTeamRanking.setBorder(javax.swing.BorderFactory.createTitledBorder("Appariement des membres d'équipe"));
        jpnIndivTeamRanking.setLayout(new java.awt.GridLayout(4, 0));

        btgIndivTeamPairing.add(jrbIndivPairingByRanking);
        jrbIndivPairingByRanking.setSelected(true);
        jrbIndivPairingByRanking.setText(bundle.getString("Ranking")); // NOI18N
        jrbIndivPairingByRanking.setToolTipText("");
        jrbIndivPairingByRanking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbIndivPairingByRankingActionPerformed(evt);
            }
        });
        jpnIndivTeamRanking.add(jrbIndivPairingByRanking);

        btgIndivTeamPairing.add(jrbIndivPairingRandom);
        jrbIndivPairingRandom.setText(bundle.getString("Random")); // NOI18N
        jrbIndivPairingRandom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbIndivPairingRandomActionPerformed(evt);
            }
        });
        jpnIndivTeamRanking.add(jrbIndivPairingRandom);

        btgIndivTeamPairing.add(jrbIndivPairingManual);
        jrbIndivPairingManual.setText(bundle.getString("Manual")); // NOI18N
        jrbIndivPairingManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbIndivPairingManualActionPerformed(evt);
            }
        });
        jpnIndivTeamRanking.add(jrbIndivPairingManual);

        btgIndivTeamPairing.add(jrbIndivPairingNaf);
        jrbIndivPairingNaf.setText(bundle.getString("NafRanking")); // NOI18N
        jrbIndivPairingNaf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbIndivPairingNafActionPerformed(evt);
            }
        });
        jpnIndivTeamRanking.add(jrbIndivPairingNaf);

        jpnByTeam.add(jpnIndivTeamRanking);

        jPanel2.add(jpnByTeam, java.awt.BorderLayout.CENTER);

        jpnTeam.setBorder(javax.swing.BorderFactory.createTitledBorder("Type de tournoi"));

        btgTeam.add(jrbTeam);
        jrbTeam.setText(bundle.getString("byTeam")); // NOI18N
        jrbTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbTeamActionPerformed(evt);
            }
        });
        jpnTeam.add(jrbTeam);

        btgTeam.add(jrbIndividual);
        jrbIndividual.setSelected(true);
        jrbIndividual.setText(bundle.getString("Individual")); // NOI18N
        jrbIndividual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbIndividualActionPerformed(evt);
            }
        });
        jpnTeam.add(jrbIndividual);

        jPanel2.add(jpnTeam, java.awt.BorderLayout.NORTH);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jbtOK.setText(bundle.getString("OK")); // NOI18N
        jbtOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOKActionPerformed(evt);
            }
        });
        jpnOK.add(jbtOK);

        getContentPane().add(jpnOK, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOKActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jbtOKActionPerformed

    private void jrbDreadBallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbDreadBallActionPerformed
        if (jrbDreadBall.isSelected()) {
            mParams.setGame(RosterType.C_DREAD_BALL);
        } else {
            mParams.setGame(RosterType.C_BLOOD_BOWL);
        }
        update();
    }//GEN-LAST:event_jrbDreadBallActionPerformed

    private void jrbBloodBowlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbBloodBowlActionPerformed
        if (jrbBloodBowl.isSelected()) {
            mParams.setGame(RosterType.C_BLOOD_BOWL);
        } else {
            mParams.setGame(RosterType.C_DREAD_BALL);
        }
        update();
    }//GEN-LAST:event_jrbBloodBowlActionPerformed

    private void jrbTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbTeamActionPerformed
        mParams.setTeamTournament(jrbTeam.isSelected());
        update();
    }//GEN-LAST:event_jrbTeamActionPerformed

    private void jrbIndividualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbIndividualActionPerformed
        mParams.setTeamTournament(jrbTeam.isSelected());
        update();
    }//GEN-LAST:event_jrbIndividualActionPerformed

    private void jckSubstitutesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jckSubstitutesActionPerformed
        mParams.setSubstitutes(jckSubstitutes.isSelected());
        update();
    }//GEN-LAST:event_jckSubstitutesActionPerformed

    private void jspCoachNumberStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jspCoachNumberStateChanged
        mParams.setTeamMatesNumber((Integer) jspCoachNumber.getValue());
        update();
    }//GEN-LAST:event_jspCoachNumberStateChanged

    private void jrbIndividualTeamPairingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbIndividualTeamPairingActionPerformed
        if (jrbIndividualTeamPairing.isSelected()) {
            mParams.setTeamPairing(ETeamPairing.INDIVIDUAL_PAIRING);
        } else {
            mParams.setTeamPairing(ETeamPairing.TEAM_PAIRING);
        }
        update();
    }//GEN-LAST:event_jrbIndividualTeamPairingActionPerformed

    private void jrbTeamPairingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbTeamPairingActionPerformed

        if (jrbIndividualTeamPairing.isSelected()) {
            mParams.setTeamPairing(ETeamPairing.INDIVIDUAL_PAIRING);
        } else {
            mParams.setTeamPairing(ETeamPairing.TEAM_PAIRING);
        }
        update();
    }//GEN-LAST:event_jrbTeamPairingActionPerformed

    private void jrbIndivPairingByRankingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbIndivPairingByRankingActionPerformed
        if (jrbIndivPairingByRanking.isSelected()) {
            mParams.setTeamIndivPairing(EIndivPairing.RANKING);
        } else {
            if (jrbIndivPairingManual.isSelected()) {
                mParams.setTeamIndivPairing(EIndivPairing.FREE);
            } else {
                if (jrbIndivPairingRandom.isSelected()) {
                    mParams.setTeamIndivPairing(EIndivPairing.RANDOM);
                } else {
                    if (jrbIndivPairingNaf.isSelected()) {
                        mParams.setTeamIndivPairing(EIndivPairing.NAF);
                    }
                }
            }
        }
        update();
    }//GEN-LAST:event_jrbIndivPairingByRankingActionPerformed

    private void jrbIndivPairingRandomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbIndivPairingRandomActionPerformed
        if (jrbIndivPairingByRanking.isSelected()) {
            mParams.setTeamIndivPairing(EIndivPairing.RANKING);
        } else {
            if (jrbIndivPairingManual.isSelected()) {
                mParams.setTeamIndivPairing(EIndivPairing.FREE);
            } else {
                if (jrbIndivPairingRandom.isSelected()) {
                    mParams.setTeamIndivPairing(EIndivPairing.RANDOM);
                } else {
                    if (jrbIndivPairingNaf.isSelected()) {
                        mParams.setTeamIndivPairing(EIndivPairing.NAF);
                    }
                }
            }
        }
        update();
    }//GEN-LAST:event_jrbIndivPairingRandomActionPerformed

    private void jrbIndivPairingManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbIndivPairingManualActionPerformed
        if (jrbIndivPairingByRanking.isSelected()) {
            mParams.setTeamIndivPairing(EIndivPairing.RANKING);
        } else {
            if (jrbIndivPairingManual.isSelected()) {
                mParams.setTeamIndivPairing(EIndivPairing.FREE);
            } else {
                if (jrbIndivPairingRandom.isSelected()) {
                    mParams.setTeamIndivPairing(EIndivPairing.RANDOM);
                } else {
                    if (jrbIndivPairingNaf.isSelected()) {
                        mParams.setTeamIndivPairing(EIndivPairing.NAF);
                    }
                }
            }
        }
        update();
    }//GEN-LAST:event_jrbIndivPairingManualActionPerformed

    private void jrbIndivPairingNafActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbIndivPairingNafActionPerformed
        if (jrbIndivPairingByRanking.isSelected()) {
            mParams.setTeamIndivPairing(EIndivPairing.RANKING);
        } else {
            if (jrbIndivPairingManual.isSelected()) {
                mParams.setTeamIndivPairing(EIndivPairing.FREE);
            } else {
                if (jrbIndivPairingRandom.isSelected()) {
                    mParams.setTeamIndivPairing(EIndivPairing.RANDOM);
                } else {
                    if (jrbIndivPairingNaf.isSelected()) {
                        mParams.setTeamIndivPairing(EIndivPairing.NAF);
                    }
                }
            }
        }
        update();
    }//GEN-LAST:event_jrbIndivPairingNafActionPerformed

    private void jcxMultipleRosterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxMultipleRosterActionPerformed
        mParams.setMultiRoster(jcxMultipleRoster.isSelected());
    }//GEN-LAST:event_jcxMultipleRosterActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgGame;
    private javax.swing.ButtonGroup btgIndivTeamPairing;
    private javax.swing.ButtonGroup btgTeam;
    private javax.swing.ButtonGroup btgTeamPairing;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JButton jbtOK;
    private javax.swing.JCheckBox jcbMultiroster;
    private javax.swing.JCheckBox jckSubstitutes;
    private javax.swing.JCheckBox jcxMultipleRoster;
    private javax.swing.JLabel jlbNbCoachs;
    private javax.swing.JPanel jpnByTeam;
    private javax.swing.JPanel jpnGame;
    private javax.swing.JPanel jpnIndivTeamRanking;
    private javax.swing.JPanel jpnMisc;
    private javax.swing.JPanel jpnNbCoachs;
    private javax.swing.JPanel jpnOK;
    private javax.swing.JPanel jpnPairing;
    private javax.swing.JPanel jpnTeam;
    private javax.swing.JRadioButton jrbBloodBowl;
    private javax.swing.JRadioButton jrbDreadBall;
    private javax.swing.JRadioButton jrbIndivPairingByRanking;
    private javax.swing.JRadioButton jrbIndivPairingManual;
    private javax.swing.JRadioButton jrbIndivPairingNaf;
    private javax.swing.JRadioButton jrbIndivPairingRandom;
    private javax.swing.JRadioButton jrbIndividual;
    private javax.swing.JRadioButton jrbIndividualTeamPairing;
    private javax.swing.JRadioButton jrbTeam;
    private javax.swing.JRadioButton jrbTeamPairing;
    private javax.swing.JSpinner jspCoachNumber;
    // End of variables declaration//GEN-END:variables

    /**
     * update hmi
     */
    private void update() {
        if (mParams.isTeamTournament()) {
            jrbIndividualTeamPairing.setEnabled(true);
            jrbTeamPairing.setEnabled(true);
            jspCoachNumber.setEnabled(true);
            jckSubstitutes.setEnabled(true);
            jlbNbCoachs.setEnabled(true);
            jpnNbCoachs.setEnabled(true);
            jpnPairing.setEnabled(true);
        } else {
            jrbIndividualTeamPairing.setEnabled(false);
            jrbTeamPairing.setEnabled(false);
            jspCoachNumber.setEnabled(false);
            jckSubstitutes.setEnabled(false);
            jlbNbCoachs.setEnabled(false);
            jpnNbCoachs.setEnabled(false);
            jpnPairing.setEnabled(false);
        }

        if (mParams.isTeamTournament() && mParams.getTeamPairing() == ETeamPairing.TEAM_PAIRING) {
            jrbIndivPairingByRanking.setEnabled(true);
            jrbIndivPairingManual.setEnabled(true);
            jrbIndivPairingNaf.setEnabled(true);
            jrbIndivPairingRandom.setEnabled(true);
            jpnIndivTeamRanking.setEnabled(true);
        } else {
            jrbIndivPairingByRanking.setEnabled(false);
            jrbIndivPairingManual.setEnabled(false);
            jrbIndivPairingNaf.setEnabled(false);
            jrbIndivPairingRandom.setEnabled(false);
            jpnIndivTeamRanking.setEnabled(false);
        }
    }
    private static final Logger LOG = Logger.getLogger(JdgParameters.class.getName());
private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException {
        throw new java.io.NotSerializableException(getClass().getName());
    }

    private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException {
        throw new java.io.NotSerializableException(getClass().getName());
    }
}
