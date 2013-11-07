/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jdgRoundReport.java
 *
 * Created on 28 juin 2010, 10:52:47
 */
package tourma.views.report;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.awt.Dimension;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import tourma.*;
import tourma.data.Coach;
import tourma.data.Criteria;
import tourma.data.CoachMatch;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.Tournament;
import tourma.tableModel.mjtMatchTeams;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
public class jdgRound extends javax.swing.JDialog {

    Round mRound;
    int mRoundNumber;
    Tournament mTour;
    boolean mResult;
    boolean mTeam = false;
    ArrayList<Team> mTeams1;
    ArrayList<Team> mTeams2;
    File mFilename = null;

    /** Creates new form jdgRoundReport */
    public jdgRound(final java.awt.Frame parent, final boolean modal, final Round round, final int roundNumber,final  Tournament tour, final boolean result, final boolean team) {
        super(parent, modal);
        initComponents();
        mRound = round;
        mRoundNumber = roundNumber;
        mTour = tour;
        mResult = result;
        mTeam = team;

        this.setTitle(tour.getParams().mTournamentName + java.util.ResourceBundle.getBundle("tourma/languages/language").getString(" -") +StringConstants.CS_THICK + java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString(StringConstants.CS_ROUND)+java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(" {0}"), roundNumber));
        try {
            jepHTML.setContentType(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("HTML"));

            if (mTeam) {
                mFilename = CreateTeamReport();
            } else {
                mFilename = CreateReport();
            }
            jepHTML.setPage(mFilename.toURI().toURL());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(parent, e.getLocalizedMessage());
        }
        this.setPreferredSize(new Dimension(800, 600));
        pack();
    }

    public jdgRound(final java.awt.Frame parent,final  boolean modal,final  ArrayList<Team> teams1, final ArrayList<Team> teams2, final int roundNumber,final  Tournament tour) {
        super(parent, modal);
        initComponents();

        mTeams1 = teams1;
        mTeams2 = teams2;
        mRoundNumber = roundNumber;
        mTour = tour;

        this.setTitle(tour.getParams().mTournamentName + StringConstants.CS_THICK + java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString(StringConstants.CS_ROUND)+java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(" {0}"), roundNumber));
        try {
            jepHTML.setContentType(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("HTML"));
            File f;
            f = CreateEmptyTeamReport();

            jepHTML.setPage(f.toURI().toURL());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(parent, e.getLocalizedMessage());
        }
        this.setPreferredSize(new Dimension(800, 600));
        pack();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked","PMD"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jbtOK = new javax.swing.JButton();
        jbtPrint = new javax.swing.JButton();
        jbtExport = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jepHTML = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);

        jbtOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Select.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        jbtOK.setText(bundle.getString("OK")); // NOI18N
        jbtOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOKActionPerformed(evt);
            }
        });
        jPanel1.add(jbtOK);

        jbtPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Document.png"))); // NOI18N
        jbtPrint.setText(bundle.getString("Print")); // NOI18N
        jbtPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtPrintActionPerformed(evt);
            }
        });
        jPanel1.add(jbtPrint);

        jbtExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Html.png"))); // NOI18N
        jbtExport.setText(bundle.getString("HTMLExport")); // NOI18N
        jbtExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtExportActionPerformed(evt);
            }
        });
        jPanel1.add(jbtExport);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jScrollPane1.setViewportView(jepHTML);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

     @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOKActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jbtOKActionPerformed

      @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtPrintActionPerformed
        try {
            jepHTML.print();

        } catch (PrinterException e) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getLocalizedMessage());
        }
    }//GEN-LAST:event_jbtPrintActionPerformed

       @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jbtExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtExportActionPerformed
       final  JFileChooser jfc = new JFileChooser();
        if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            final File export = jfc.getSelectedFile();

            try {
                final FileWriter out;
                try (FileReader in = new FileReader(mFilename)) {
                    out = new FileWriter(export);
                    int c;
                    while ((c = in.read()) != -1) {
                        out.write(c);
                    }
                }
                out.close();
            } catch (FileNotFoundException fnf) {
                JOptionPane.showMessageDialog(this, fnf.getLocalizedMessage());
            } catch (IOException ioe) {
                JOptionPane.showMessageDialog(this, ioe.getLocalizedMessage());
            }
        }
    }//GEN-LAST:event_jbtExportActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtExport;
    private javax.swing.JButton jbtOK;
    private javax.swing.JButton jbtPrint;
    private javax.swing.JEditorPane jepHTML;
    // End of variables declaration//GEN-END:variables

    private File CreateReport() {
        File address = null;

        try {
            final Configuration cfg = new Configuration();
            final URI uri = getClass().getResource("/tourma/views/report").toURI();
            if (uri.toString().contains(".jar!")) {
                /*String tmp = uri.toString();
                tmp = tmp.substring(10, tmp.indexOf(".jar!") - 4);
                //tmp=tmp+"";
                cfg.setDirectoryForTemplateLoading(new File(tmp));*/
                cfg.setClassForTemplateLoading(getClass(), "");
            } else {
                cfg.setDirectoryForTemplateLoading(new File(uri));
            }
            cfg.setObjectWrapper(new DefaultObjectWrapper());
            final Template temp = cfg.getTemplate("round.html");

            final Map root = new HashMap();
            root.put(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NOM"), mTour.getParams().mTournamentName + StringConstants.CS_THICK + java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString(StringConstants.CS_ROUND)+java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(" {0}"), mRoundNumber));
            root.put(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TABLE"), mRound.getMatchs().size());

            final ArrayList<CoachMatch> matches = mRound.getCoachMatchs();
            final ArrayList parMatches = new ArrayList();
            if (mResult) {
                root.put(StringConstants.CS_RESULT, 1);
            } else {
                root.put(StringConstants.CS_RESULT, 0);
            }

            final ArrayList crits = new ArrayList();
            final ArrayList<Criteria> criterias = Tournament.getTournament().getParams().mCriterias;
            for (int i = 1; i < criterias.size(); i++) {
                crits.add(criterias.get(i).mName);
            }
            root.put(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CRITERIAS"), crits);

            for (int i = 0; i < matches.size(); i++) {
               final  CoachMatch match = matches.get(i);

                final HashMap m = new HashMap();
                m.put("numero", i + 1);
                
                if (match.mRoster1==null)
                {
                    m.put("roster1", ((Coach)match.mCompetitor1).mRoster.mName);
                }
                else
                {
                    m.put("roster1", match.mRoster1.mName);
                }
                
                if (match.mRoster2==null)
                {
                    m.put("roster2", ((Coach)match.mCompetitor2).mRoster.mName);
                }
                else
                {
                    m.put("roster2", match.mRoster2.mName);
                }
                
                if (!mTour.getParams().mTeamTournament) {
                    m.put("coach1", match.mCompetitor1.mName);                    
                }
                else
                {
                       m.put("coach1", ((Coach)match.mCompetitor1).mTeamMates.mName+StringConstants.CS_THICK+ match.mCompetitor1.mName);
                }
                if (mResult) {
                    m.put("score1", match.mValues.get(Tournament.getTournament().getParams().mCriterias.get(0)).mValue1);
                    m.put("score2", match.mValues.get(Tournament.getTournament().getParams().mCriterias.get(0)).mValue2);

                    final ArrayList values = new ArrayList();
                    for (int j = 1; j < criterias.size(); j++) {
                        final HashMap value = new HashMap();
                        value.put("value1", match.mValues.get(criterias.get(j)).mValue1);
                        value.put("value2", match.mValues.get(criterias.get(j)).mValue2);
                        values.add(value);
                    }
                    m.put("values", values);

                } else {
                    m.put("score1", StringConstants.CS_HTML_EMPTY);
                    m.put("score2", StringConstants.CS_HTML_EMPTY);
                    final ArrayList values = new ArrayList();
                    for (int j = 1; j < criterias.size(); j++) {
                        final HashMap value = new HashMap();
                        value.put("value1", StringConstants.CS_HTML_EMPTY);
                        value.put("value2", StringConstants.CS_HTML_EMPTY);
                        values.add(value);
                    }
                    m.put("values", values);
                }
                if (!mTour.getParams().mTeamTournament) {
                    m.put("coach2", match.mCompetitor2.mName);
                }
                else
                {
                       m.put("coach2", ((Coach)match.mCompetitor2).mTeamMates.mName+StringConstants.CS_THICK+ match.mCompetitor2.mName);
                }
                //m.put("coach2", match.mCompetitor2.mName);
                parMatches.add(m);
            }

            root.put("matches", parMatches);

            final SimpleDateFormat format = new SimpleDateFormat(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("EEEEEEE DD MMMMMMMMMMM YYYY"),Locale.getDefault());
            final SimpleDateFormat formatShort = new SimpleDateFormat(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DD/MM/YYYY"),Locale.getDefault());
            root.put(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DATEGENERATION"), formatShort.format(new Date()));
            address = File.createTempFile(StringConstants.CS_RESULT + format.format(new Date()), java.util.ResourceBundle.getBundle("tourma/languages/language").getString(".TMP"));
            address.deleteOnExit();
            final Writer out = new FileWriter(address);
            temp.process(root, out);
            out.flush();

        } catch (IOException | TemplateException | URISyntaxException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        }
        return address;
    }

    private File CreateTeamReport() {
        File address = null;

        try {
            final Configuration cfg = new Configuration();
            final URI uri = getClass().getResource("tourma/views/report").toURI();
            if (uri.toString().contains(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(".JAR!"))) {
                /* JOptionPane.showMessageDialog(this,"Dans un jar: "+uri.toString());
                String tmp=uri.toString();
                tmp=tmp.substring(10, tmp.indexOf(".jar!")-4);
                JOptionPane.showMessageDialog(this,tmp);
                //tmp=tmp+"";
                cfg.setDirectoryForTemplateLoading(new File(tmp));*/
                cfg.setClassForTemplateLoading(getClass(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString(""));
            } else {
                cfg.setDirectoryForTemplateLoading(new File(uri));
            }
            cfg.setObjectWrapper(new DefaultObjectWrapper());
            final Template temp = cfg.getTemplate("team_round.tml");

           final  Map root = new HashMap();
            root.put(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NOM"), mTour.getParams().mTournamentName + StringConstants.CS_THICK + java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString(StringConstants.CS_ROUND)+java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(" {0}"), mRoundNumber));
            root.put(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TABLES"), mRound.getMatchs().size());

            if (mResult) {
                root.put(StringConstants.CS_RESULT, 1);
            } else {
                root.put(StringConstants.CS_RESULT, 0);
            }

            final ArrayList<Team> teams = new ArrayList<>();
            for (int i = 0; i < mRound.getMatchs().size(); i++) {
                final CoachMatch m = mRound.getCoachMatchs().get(i);
                final Team team1 = ((Coach)m.mCompetitor1).mTeamMates;
               final  Team team2 = ((Coach)m.mCompetitor2).mTeamMates;
                if (!teams.contains(team1)) {
                    teams.add(team1);
                }
                if (!teams.contains(team2)) {
                    teams.add(team2);
                }
            }
           final  mjtMatchTeams model = new mjtMatchTeams(teams, mRound);
            final ArrayList parMatches = new ArrayList();
            for (int i = 0; i < model.getRowCount(); i++) {

                final HashMap m = new HashMap();
                m.put(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NUMERO"), model.getValueAt(i, 0));
                m.put(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TEAM1"), model.getValueAt(i, 1));
                if (mResult) {
                    m.put(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("V1"), model.getValueAt(i, 2));
                    m.put(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("N"), model.getValueAt(i, 3));
                    m.put(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("V2"), model.getValueAt(i, 4));
                } else {
                    m.put(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("V1"), StringConstants.CS_HTML_EMPTY);
                    m.put(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("N"), StringConstants.CS_HTML_EMPTY);
                    m.put(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("V2"), StringConstants.CS_HTML_EMPTY);
                }
                m.put(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TEAM2"), model.getValueAt(i, 5));
                parMatches.add(m);
            }

            root.put(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MATCHES"), parMatches);

            final SimpleDateFormat format = new SimpleDateFormat(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("EEEEEEE DD MMMMMMMMMMM YYYY"),Locale.getDefault());
            final SimpleDateFormat formatShort = new SimpleDateFormat(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DD/MM/YYYY"),Locale.getDefault());
            root.put(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DATEGENERATION"), formatShort.format(new Date()));
            address = File.createTempFile(StringConstants.CS_RESULT + format.format(new Date()), java.util.ResourceBundle.getBundle("tourma/languages/language").getString(".TMP"));
            address.deleteOnExit();
            final Writer out = new FileWriter(address);
            temp.process(root, out);
            out.flush();

        } catch (IOException | TemplateException | URISyntaxException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        }
        return address;
    }

    private File CreateEmptyTeamReport() {
        File address = null;

        try {
           final  Configuration cfg = new Configuration();
            final URI uri = getClass().getResource("tourma/views/report").toURI();
            if (uri.toString().contains(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(".JAR!"))) {
                /* JOptionPane.showMessageDialog(this,"Dans un jar: "+uri.toString());
                String tmp=uri.toString();
                tmp=tmp.substring(10, tmp.indexOf(".jar!")-4);
                JOptionPane.showMessageDialog(this,tmp);
                //tmp=tmp+"";
                cfg.setDirectoryForTemplateLoading(new File(tmp));*/
                cfg.setClassForTemplateLoading(getClass(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString(""));
            } else {
                cfg.setDirectoryForTemplateLoading(new File(uri));
            }
            cfg.setObjectWrapper(new DefaultObjectWrapper());
            final Template temp = cfg.getTemplate("team_round.html");

            final Map root = new HashMap();
            root.put(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NOM"), mTour.getParams().mTournamentName + java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(" - RONDE {0}"), mRoundNumber));
            root.put(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TABLES"), mTour.getTeams().size() / 2);
            root.put(StringConstants.CS_RESULT, 0);

            final ArrayList parMatches = new ArrayList();
            for (int i = 0; i < mTeams1.size(); i++) {
                final HashMap m = new HashMap();
                m.put(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NUMERO"), i + 1);
                m.put(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CLAN1"), mTeams1.get(i).mName);
                m.put(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("V1"), StringConstants.CS_HTML_EMPTY);
                m.put(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("N"), StringConstants.CS_HTML_EMPTY);
                m.put(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("V2"), StringConstants.CS_HTML_EMPTY);

                m.put(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CLAN2"), mTeams2.get(i).mName);
                parMatches.add(m);
            }

            root.put(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MATCHES"), parMatches);

            final SimpleDateFormat format = new SimpleDateFormat(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("EEEEEEE DD MMMMMMMMMMM YYYY"),Locale.getDefault());
            final SimpleDateFormat formatShort = new SimpleDateFormat(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DD/MM/YYYY"),Locale.getDefault());
            root.put(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DATEGENERATION"), formatShort.format(new Date()));
            address = File.createTempFile(StringConstants.CS_RESULT + format.format(new Date()), java.util.ResourceBundle.getBundle("tourma/languages/language").getString(".TMP"));
            address.deleteOnExit();
            final Writer out = new FileWriter(address);
            temp.process(root, out);
            out.flush();

        } catch (IOException | TemplateException | URISyntaxException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        }
        return address;
    }
}
