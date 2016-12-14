/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.views.parameters;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import tourma.data.Clan;
import tourma.data.Tournament;
import tourma.data.Tournament;
import tourma.languages.Translate;
import tourma.utility.ExtensionFileFilter;
import tourma.utility.StringConstants;
import tourma.utils.ImageTreatment;

/**
 *
 * @author WFMJ7631
 */
public final class JPNParamClan extends javax.swing.JPanel {

    private class JLSCellRenderer implements ListCellRenderer<Clan> {

        private JLSCellRenderer() {

        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Clan> list, Clan value, int index, boolean isSelected, boolean cellHasFocus) {

            JLabel label = new JLabel(value.getName());
            try {
                if (mTournament.getParams().isUseImage()) {
                    if (value.getName() != null) {
                        label.setIcon(ImageTreatment.resize(new ImageIcon(value.getName()), 30, 30));
                    }
                }
            } catch (RemoteException re) {
                re.printStackTrace();
            }
            label.setSize(100, 30);
            label.setOpaque(true);
            label.setBorder(new LineBorder(new Color(200, 200, 200), 1));
            if (isSelected) {
                label.setBackground(new Color(200, 200, 200));

            } else {
                label.setBackground(Color.WHITE);
            }
            return label;
        }
    }

    private Tournament mTournament;

    /**
     * Creates new form JPNParamClan
     */
    public JPNParamClan() {
        mTournament = null;
        try {
            mTournament = Tournament.getTournament();
        } catch (RemoteException re) {
            re.printStackTrace();
        }
        initComponents();
        jlsClans.setCellRenderer(new JLSCellRenderer());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "PMD", "UnnecessaryBoxing"})
    @SuppressFBWarnings({"SIC"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel12 = new javax.swing.JPanel();
        jlbActivateClans = new javax.swing.JLabel();
        jcxActivatesClans = new javax.swing.JCheckBox();
        jlbTeamMatesNumber = new javax.swing.JLabel();
        jspClanMembers = new javax.swing.JSpinner();
        jlbClansMembersNUmbers = new javax.swing.JLabel();
        jcxAvoidFirstMatch = new javax.swing.JCheckBox();
        jlbAvoidClansMembersMatch = new javax.swing.JLabel();
        jcxAvoidMatch = new javax.swing.JCheckBox();
        jPanel13 = new javax.swing.JPanel();
        jbtAddClan = new javax.swing.JButton();
        jbtEditClan = new javax.swing.JButton();
        jbtEditClanIcon = new javax.swing.JButton();
        jbtRemoveClan = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jlsClans = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        jlsCoachList = new javax.swing.JList();

        setLayout(new java.awt.BorderLayout());

        jPanel12.setLayout(new java.awt.GridLayout(4, 2));

        jlbActivateClans.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        jlbActivateClans.setText(bundle.getString("EnableClansKey")); // NOI18N
        jPanel12.add(jlbActivateClans);

        jcxActivatesClans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxActivatesClansActionPerformed(evt);
            }
        });
        jPanel12.add(jcxActivatesClans);

        jlbTeamMatesNumber.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbTeamMatesNumber.setText(bundle.getString("TeamMatesNumber")); // NOI18N
        jlbTeamMatesNumber.setToolTipText("");
        jlbTeamMatesNumber.setEnabled(false);
        jPanel12.add(jlbTeamMatesNumber);

        jspClanMembers.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(3), Integer.valueOf(1), null, Integer.valueOf(1)));
        jspClanMembers.setEnabled(false);
        jspClanMembers.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jspClanMembersStateChanged(evt);
            }
        });
        jPanel12.add(jspClanMembers);

        jlbClansMembersNUmbers.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbClansMembersNUmbers.setText(bundle.getString("AvoidMembersMatchsFirstRoundKey")); // NOI18N
        jlbClansMembersNUmbers.setEnabled(false);
        jPanel12.add(jlbClansMembersNUmbers);

        jcxAvoidFirstMatch.setEnabled(false);
        jcxAvoidFirstMatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxAvoidFirstMatchActionPerformed(evt);
            }
        });
        jPanel12.add(jcxAvoidFirstMatch);

        jlbAvoidClansMembersMatch.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlbAvoidClansMembersMatch.setText(bundle.getString("AvoidCLansMemberMatchKey")); // NOI18N
        jlbAvoidClansMembersMatch.setEnabled(false);
        jPanel12.add(jlbAvoidClansMembersMatch);

        jcxAvoidMatch.setEnabled(false);
        jcxAvoidMatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcxAvoidMatchActionPerformed(evt);
            }
        });
        jPanel12.add(jcxAvoidMatch);

        add(jPanel12, java.awt.BorderLayout.NORTH);

        jbtAddClan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Add.png"))); // NOI18N
        jbtAddClan.setText(bundle.getString("Add")); // NOI18N
        jbtAddClan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAddClanActionPerformed(evt);
            }
        });
        jPanel13.add(jbtAddClan);

        jbtEditClan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Swap.png"))); // NOI18N
        jbtEditClan.setText(bundle.getString("Edit")); // NOI18N
        jbtEditClan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtEditClanActionPerformed(evt);
            }
        });
        jPanel13.add(jbtEditClan);

        jbtEditClanIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Swap.png"))); // NOI18N
        jbtEditClanIcon.setText(bundle.getString("EditIcon")); // NOI18N
        jbtEditClanIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtEditClanIconActionPerformed(evt);
            }
        });
        jPanel13.add(jbtEditClanIcon);

        jbtRemoveClan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Close.png"))); // NOI18N
        jbtRemoveClan.setText(bundle.getString("Remove")); // NOI18N
        jbtRemoveClan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRemoveClanActionPerformed(evt);
            }
        });
        jPanel13.add(jbtRemoveClan);

        add(jPanel13, java.awt.BorderLayout.SOUTH);

        jPanel14.setLayout(new java.awt.GridLayout(1, 2));

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("ClansKey"))); // NOI18N

        jlsClans.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jlsClans.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlsClans.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlsClansMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jlsClans);

        jPanel14.add(jScrollPane3);

        jScrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("ClansMembersKey"))); // NOI18N

        jlsCoachList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jlsCoachList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(jlsCoachList);

        jPanel14.add(jScrollPane4);

        add(jPanel14, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jcxActivatesClansActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxActivatesClansActionPerformed
        final boolean clansEnable = jcxActivatesClans.isSelected();

        jlbAvoidClansMembersMatch.setEnabled(clansEnable);
        jlbClansMembersNUmbers.setEnabled(clansEnable);
        jlbTeamMatesNumber.setEnabled(clansEnable);

        jspClanMembers.setEnabled(clansEnable);
        jcxAvoidFirstMatch.setEnabled(clansEnable);
        jcxAvoidMatch.setEnabled(clansEnable);

        jbtAddClan.setEnabled(clansEnable);
        jbtRemoveClan.setEnabled(clansEnable);
        jbtEditClan.setEnabled(clansEnable);
        jlsClans.setEnabled(clansEnable);

        try {
            mTournament.getParams().setEnableClans(clansEnable);
        } catch (RemoteException re) {
            re.printStackTrace();
        }
        update();
    }//GEN-LAST:event_jcxActivatesClansActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jspClanMembersStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jspClanMembersStateChanged
        try {
            mTournament.getParams().setTeamMatesClansNumber((Integer) jspClanMembers.getValue());
        } catch (RemoteException re) {
            re.printStackTrace();
        }
    }//GEN-LAST:event_jspClanMembersStateChanged
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jcxAvoidFirstMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxAvoidFirstMatchActionPerformed
        try {
            mTournament.getParams().setAvoidClansFirstMatch(jcxAvoidFirstMatch.isSelected());
        } catch (RemoteException re) {
            re.printStackTrace();
        }
    }//GEN-LAST:event_jcxAvoidFirstMatchActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jcxAvoidMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcxAvoidMatchActionPerformed
        try {
            mTournament.getParams().setAvoidClansMatch(jcxAvoidMatch.isSelected());
        } catch (RemoteException re) {
            re.printStackTrace();
        }
    }//GEN-LAST:event_jcxAvoidMatchActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtAddClanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAddClanActionPerformed

        try {
            final String enterClanName
                    = Translate.translate(CS_EnterClanNameKey);
            final String clanName = JOptionPane.showInputDialog(this, enterClanName);
            if (clanName != null) {
                if (!clanName.equals(StringConstants.CS_NULL)) {
                    mTournament.addClan(new Clan(clanName));
                }
            }
        } catch (RemoteException re) {
            re.printStackTrace();
        }
        update();
    }//GEN-LAST:event_jbtAddClanActionPerformed

    private final static String CS_EnterClanNameKey = "EnterClanNameKey";

    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtEditClanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtEditClanActionPerformed
        final String enterClanName = Translate.translate(CS_EnterClanNameKey);
        final String clanName = jlsClans.getSelectedValue().toString();
        final String newClanName = JOptionPane.showInputDialog(this, enterClanName, clanName);
        try {
            if (!clanName.equals(StringConstants.CS_NULL)) {
                mTournament.getClan(jlsClans.getSelectedIndex()).setName(newClanName);
            }
        } catch (RemoteException re) {
            re.printStackTrace();
        }
        update();
    }//GEN-LAST:event_jbtEditClanActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtRemoveClanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRemoveClanActionPerformed
        final int index = jlsClans.getSelectedIndex();

        if (index > 0) {
            try {
                mTournament.removeClan(index);
            } catch (RemoteException re) {
                re.printStackTrace();
            }
        }
        update();
    }//GEN-LAST:event_jbtRemoveClanActionPerformed
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jlsClansMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlsClansMouseClicked
        updateSublist();
    }//GEN-LAST:event_jlsClansMouseClicked

    private final static String CS_SelectAnPicture = "SÉLECTIONNEZ UNE IMAGE";
    private final static String CS_Picture = "Picture";

    private void jbtEditClanIconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtEditClanIconActionPerformed
        if (jlsClans.getSelectedIndex() >= 0) {
            File folder;
            folder = new File(getClass().getResource("/tourma/images/flags").getFile());
            File[] listOfFiles = folder.listFiles();

            Object[] objects = new Object[listOfFiles.length + 1];

            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    String path = listOfFiles[i].getAbsolutePath();
                    ImageIcon icon = new ImageIcon(path);
                    objects[i] = ImageTreatment.resize(icon, 80, 80);
                } else if (listOfFiles[i].isDirectory()) {
                    LOG.log(Level.INFO, "Directory {0}", listOfFiles[i].getName());
                }
            }

            ImageIcon empty = new ImageIcon();
            objects[listOfFiles.length] = empty;

            JComboBox combo = new JComboBox(objects);
            JPanel panel = new JPanel(new BorderLayout());
            JLabel l = new JLabel(
                    Translate.translate(CS_SelectAnPicture));
            panel.add(l, BorderLayout.NORTH);
            panel.add(combo, BorderLayout.CENTER);

            combo.setSelectedItem(empty);

            JOptionPane.showConfirmDialog(null, panel, null, JOptionPane.YES_OPTION);

            try {
                Clan c = mTournament.getClan(jlsClans.getSelectedIndex());
                if (combo.getSelectedItem() == empty) {
                    final JFileChooser jfc = new JFileChooser();
                    final FileFilter filter1 = new ExtensionFileFilter(
                            Translate.translate(CS_Picture), new String[]{"PNG", "png", "JPG", "jpg", "GIF", "gif"});
                    jfc.setFileFilter(filter1);
                    if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        ImageIcon icon = new ImageIcon(jfc.getSelectedFile().getAbsolutePath());
                        icon = ImageTreatment.resize(icon, 80, 80);

                        c.setPicture(icon);
                    }
                } else {
                    ImageIcon icon = (ImageIcon) combo.getSelectedItem();
                    c.setPicture(icon);
                }
            } catch (RemoteException re) {
                re.printStackTrace();
            }
            update();
        }
    }//GEN-LAST:event_jbtEditClanIconActionPerformed

    private void updateSublist() {

        final int selectedClan = jlsClans.getSelectedIndex();
        final DefaultListModel memberListModel = new DefaultListModel();
        if (selectedClan >= 0) {
            try {
                final String clanName = mTournament.getClan(selectedClan).getName();
                if (mTournament.getParams().isTeamTournament()) {
                    for (int i = 0; i < mTournament.getTeamsCount(); i++) {
                        if (mTournament.getTeam(i).getClan() != null) {
                            if (clanName.equals(mTournament.getTeam(i).getClan().getName())) {
                                memberListModel.addElement(mTournament.getTeam(i).getName());
                            }
                        }
                    }
                } else {
                    for (int i = 0; i < mTournament.getCoachsCount(); i++) {
                        if (mTournament.getCoach(i).getClan() != null) {
                            if (clanName.equals(mTournament.getCoach(i).getClan().getName())) {
                                memberListModel.addElement(mTournament.getCoach(i).getName());
                            }
                        }
                    }
                }
            } catch (RemoteException re) {
                re.printStackTrace();
            }
        }
        jlsCoachList.setModel(memberListModel);
    }

    /**
     * Update panel
     */
    public void update() {

        try {
            jbtEditClanIcon.setVisible(mTournament.getParams().isUseImage());

            final boolean clansEnable = (mTournament.getParams().isEnableClans());

            jlbActivateClans.setEnabled(true);
            jlbAvoidClansMembersMatch.setEnabled(clansEnable);
            jlbClansMembersNUmbers.setEnabled(clansEnable);
            jlbTeamMatesNumber.setEnabled(clansEnable);

            jspClanMembers.setEnabled(clansEnable);
            jcxAvoidFirstMatch.setEnabled(clansEnable);
            jcxAvoidMatch.setEnabled(clansEnable);

            jbtAddClan.setEnabled(clansEnable);
            jbtRemoveClan.setEnabled(clansEnable);
            jbtEditClan.setEnabled(clansEnable);
            jbtEditClanIcon.setEnabled(clansEnable);
            jlsClans.setEnabled(clansEnable);

            jcxActivatesClans.setSelected(clansEnable);
            jcxAvoidFirstMatch.setSelected(mTournament.getParams().isAvoidClansFirstMatch());
            jcxAvoidMatch.setSelected(mTournament.getParams().isAvoidClansMatch());
            jspClanMembers.setValue(mTournament.getParams().getTeamMatesClansNumber());

            updateSublist();
            final DefaultListModel listModel = new DefaultListModel();
            for (int i = 0; i < mTournament.getClansCount(); i++) {
                //JLabel label = new JLabel(mTournament.getClan(i).getName());
                listModel.addElement(mTournament.getClan(i));
            }
            jlsClans.setModel(listModel);
        } catch (RemoteException re) {
            re.printStackTrace();
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton jbtAddClan;
    private javax.swing.JButton jbtEditClan;
    private javax.swing.JButton jbtEditClanIcon;
    private javax.swing.JButton jbtRemoveClan;
    private javax.swing.JCheckBox jcxActivatesClans;
    private javax.swing.JCheckBox jcxAvoidFirstMatch;
    private javax.swing.JCheckBox jcxAvoidMatch;
    private javax.swing.JLabel jlbActivateClans;
    private javax.swing.JLabel jlbAvoidClansMembersMatch;
    private javax.swing.JLabel jlbClansMembersNUmbers;
    private javax.swing.JLabel jlbTeamMatesNumber;
    private javax.swing.JList jlsClans;
    private javax.swing.JList jlsCoachList;
    private javax.swing.JSpinner jspClanMembers;
    // End of variables declaration//GEN-END:variables
    private static final Logger LOG = Logger.getLogger(JPNParamClan.class.getName());

}
