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

import tourma.tableModel.mjtAnnexRankIndiv;
import tourma.tableModel.mjtRankingIndiv;
import tourma.*;
import tourma.data.Round;
import tourma.data.Tournament;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import tourma.tableModel.mjtAnnexRankTeam;
import tourma.tableModel.mjtRankingTeam;

/**
 *
 * @author Frederic Berger
 */
public class jdgGlobal extends javax.swing.JDialog {

    public static final int RANKING_GENERAL = 0;
    public static final int RANKING_SCORER = 1;
    public static final int RANKING_DESTROYER = 2;
    public static final int RANKING_FOULER = 3;
    public static final int RANKING_SCORED = 4;
    public static final int RANKING_DESTROYED = 5;
    public static final int RANKING_FOULED = 6;
    Round _round;
    int _roundNumber;
    Tournament _tour;
    boolean _result;
    int _rankType;
    boolean _team = false;
    File _filename=null;

    /** Creates new form jdgRoundReport */
    public jdgGlobal(java.awt.Frame parent, boolean modal, Round round, int roundNumber, Tournament tour, boolean team) {
        super(parent, modal);
        initComponents();
        _round = round;
        _roundNumber = roundNumber;
        _tour = tour;
        _team = team;
        this.setTitle(tour.getParams()._tournament_name + java.util.ResourceBundle.getBundle("tourma/languages/language").getString(" - RONDE ") + roundNumber);
        try {
            jepHTML.setContentType(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("HTML"));
            _filename = CreateReport();
            jepHTML.setPage(_filename.toURI().toURL());
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jbtOK = new javax.swing.JButton();
        jbtPrint = new javax.swing.JButton();
        jbtExport = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jepHTML = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Ranking"); // NOI18N

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        jbtOK.setText(bundle.getString("OK")); // NOI18N
        jbtOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOKActionPerformed(evt);
            }
        });
        jPanel1.add(jbtOK);

        jbtPrint.setText(bundle.getString("IMPRIMER")); // NOI18N
        jbtPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtPrintActionPerformed(evt);
            }
        });
        jPanel1.add(jbtPrint);

        jbtExport.setText(bundle.getString("EXPORT HTML")); // NOI18N
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

    private void jbtOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOKActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jbtOKActionPerformed

    private void jbtPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtPrintActionPerformed
        try {
            jepHTML.print();

        } catch (PrinterException e) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getLocalizedMessage());
        }
    }//GEN-LAST:event_jbtPrintActionPerformed

    private void jbtExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtExportActionPerformed
        JFileChooser jfc = new JFileChooser();
        if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File export = jfc.getSelectedFile();

            try
            {
                FileReader in = new FileReader(_filename);
                FileWriter out = new FileWriter(export);
                int c;

                while ((c = in.read()) != -1) {
                    out.write(c);
                }

                in.close();
                out.close();
            }
            catch (FileNotFoundException fnf)
            {
                JOptionPane.showMessageDialog(this, fnf.getLocalizedMessage());
            }
             catch (IOException ioe)
            {
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
            Configuration cfg = new Configuration();
            URI uri = getClass().getResource("/tourma/views/report").toURI();
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
            Template temp = cfg.getTemplate("global.html");

            TableModel generalModel;
            TableModel tdPosModel;
            TableModel tdNegModel;
            TableModel sorPosModel;
            TableModel sorNegModel;
            TableModel foulPosModel;
            TableModel foulNegModel;

            Vector<Round> rounds = new Vector();
            for (int i = 0; i < _tour.getRounds().size() && i < _roundNumber; i++) {
                rounds.add(_tour.getRounds().get(i));
            }

            Map root = new HashMap();
            root.put("nom", _tour.getParams()._tournament_name + java.util.ResourceBundle.getBundle("tourma/languages/language").getString(" - RONDE ") + _roundNumber);
            root.put("title", java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CLASSEMENT GÉNÉRAL"));
            if (_team)
            {
                root.put ("team",1);
            }
            else
            {
                root.put ("team",0);
            }
            if (_team) {
                if (_tour.getParams()._team_victory_only) {
                    generalModel = new mjtRankingTeam(rounds, _tour.getParams()._ranking1_team, _tour.getParams()._ranking2_team, _tour.getParams()._ranking3_team, _tour.getParams()._ranking4_team, _tour.getParams()._ranking5_team, _tour.getTeams());
                } else {
                    generalModel = new mjtRankingTeam(rounds, _tour.getParams()._ranking1, _tour.getParams()._ranking2, _tour.getParams()._ranking3, _tour.getParams()._ranking4, _tour.getParams()._ranking5, _tour.getTeams());
                }
                tdPosModel = new mjtAnnexRankTeam(rounds, mjtAnnexRankIndiv.C_MOST_TD_POS, _tour.getTeams(), true, _tour.getParams()._ranking1, _tour.getParams()._ranking2, _tour.getParams()._ranking3, _tour.getParams()._ranking4, _tour.getParams()._ranking5);
                sorPosModel = new mjtAnnexRankTeam(rounds, mjtAnnexRankIndiv.C_MOST_SOR_POS, _tour.getTeams(), true, _tour.getParams()._ranking1, _tour.getParams()._ranking2, _tour.getParams()._ranking3, _tour.getParams()._ranking4, _tour.getParams()._ranking5);
                foulPosModel = new mjtAnnexRankTeam(rounds, mjtAnnexRankIndiv.C_MOST_FOUL_POS, _tour.getTeams(), true, _tour.getParams()._ranking1, _tour.getParams()._ranking2, _tour.getParams()._ranking3, _tour.getParams()._ranking4, _tour.getParams()._ranking5);
                tdNegModel = new mjtAnnexRankTeam(rounds, mjtAnnexRankIndiv.C_MOST_TD_NEG, _tour.getTeams(), true, _tour.getParams()._ranking1, _tour.getParams()._ranking2, _tour.getParams()._ranking3, _tour.getParams()._ranking4, _tour.getParams()._ranking5);
                sorNegModel = new mjtAnnexRankTeam(rounds, mjtAnnexRankIndiv.C_MOST_SOR_NEG, _tour.getTeams(), true, _tour.getParams()._ranking1, _tour.getParams()._ranking2, _tour.getParams()._ranking3, _tour.getParams()._ranking4, _tour.getParams()._ranking5);
                foulNegModel = new mjtAnnexRankTeam(rounds, mjtAnnexRankIndiv.C_MOST_FOUL_NEG, _tour.getTeams(), true, _tour.getParams()._ranking1, _tour.getParams()._ranking2, _tour.getParams()._ranking3, _tour.getParams()._ranking4, _tour.getParams()._ranking5);
            } else {
                generalModel = new mjtRankingIndiv(rounds, _tour.getParams()._ranking1, _tour.getParams()._ranking2, _tour.getParams()._ranking3, _tour.getParams()._ranking4, _tour.getParams()._ranking5, _tour.getCoachs(), _tour.getParams()._teamTournament);
                tdPosModel = new mjtAnnexRankIndiv(rounds, mjtAnnexRankIndiv.C_MOST_TD_POS, _tour.getCoachs(), true, _tour.getParams()._ranking1, _tour.getParams()._ranking2, _tour.getParams()._ranking3, _tour.getParams()._ranking4, _tour.getParams()._ranking5, _tour.getParams()._teamTournament);
                sorPosModel = new mjtAnnexRankIndiv(rounds, mjtAnnexRankIndiv.C_MOST_SOR_POS, _tour.getCoachs(), true, _tour.getParams()._ranking1, _tour.getParams()._ranking2, _tour.getParams()._ranking3, _tour.getParams()._ranking4, _tour.getParams()._ranking5, _tour.getParams()._teamTournament);
                foulPosModel = new mjtAnnexRankIndiv(rounds, mjtAnnexRankIndiv.C_MOST_FOUL_POS, _tour.getCoachs(), true, _tour.getParams()._ranking1, _tour.getParams()._ranking2, _tour.getParams()._ranking3, _tour.getParams()._ranking4, _tour.getParams()._ranking5, _tour.getParams()._teamTournament);
                tdNegModel = new mjtAnnexRankIndiv(rounds, mjtAnnexRankIndiv.C_MOST_TD_NEG, _tour.getCoachs(), true, _tour.getParams()._ranking1, _tour.getParams()._ranking2, _tour.getParams()._ranking3, _tour.getParams()._ranking4, _tour.getParams()._ranking5, _tour.getParams()._teamTournament);
                sorNegModel = new mjtAnnexRankIndiv(rounds, mjtAnnexRankIndiv.C_MOST_SOR_NEG, _tour.getCoachs(), true, _tour.getParams()._ranking1, _tour.getParams()._ranking2, _tour.getParams()._ranking3, _tour.getParams()._ranking4, _tour.getParams()._ranking5, _tour.getParams()._teamTournament);
                foulNegModel = new mjtAnnexRankIndiv(rounds, mjtAnnexRankIndiv.C_MOST_FOUL_NEG, _tour.getCoachs(), true, _tour.getParams()._ranking1, _tour.getParams()._ranking2, _tour.getParams()._ranking3, _tour.getParams()._ranking4, _tour.getParams()._ranking5, _tour.getParams()._teamTournament);
            }

            // Meilleur Marqueur
            Vector BestScorerLines = new Vector();
            for (int i = 0; i < 3; i++) {
                HashMap line = new HashMap();
                Vector l = new Vector();
                l.add(i + 1);
                if (_team) {
                    l.add(tdPosModel.getValueAt(i, 1));
                    l.add(tdPosModel.getValueAt(i, 2));
                } else {
                    l.add(tdPosModel.getValueAt(i, 2));
                    l.add(tdPosModel.getValueAt(i, 3));
                    l.add(tdPosModel.getValueAt(i, 4));
                }
                line.put("cols", l);
                BestScorerLines.add(line);
            }
            root.put("BestScorerLines", BestScorerLines);

            // Moins bon marqueur (Savonette)
            Vector WorstScorerLines = new Vector();
            for (int i = 0; i < 3; i++) {
                HashMap line = new HashMap();
                Vector l = new Vector();
                l.add(i + 1);
                if (_team) {
                    l.add(tdPosModel.getValueAt(tdPosModel.getRowCount() - 1 - i, 1));
                    l.add(tdPosModel.getValueAt(tdPosModel.getRowCount() - 1 - i, 2));
                } else {
                    l.add(tdPosModel.getValueAt(tdPosModel.getRowCount() - 1 - i, 2));
                    l.add(tdPosModel.getValueAt(tdPosModel.getRowCount() - 1 - i, 3));
                    l.add(tdPosModel.getValueAt(tdPosModel.getRowCount() - 1 - i, 4));
                }
                line.put("cols", l);
                WorstScorerLines.add(line);
            }
            root.put("WorstScorerLines", WorstScorerLines);

            // Passoire
            Vector WorstDefenseLines = new Vector();
            for (int i = 0; i < 3; i++) {
                HashMap line = new HashMap();
                Vector l = new Vector();
                l.add(i + 1);
                if (_team) {
                    l.add(tdNegModel.getValueAt(i, 1));
                    l.add(tdNegModel.getValueAt(i, 2));
                } else {
                    l.add(tdNegModel.getValueAt(i, 2));
                    l.add(tdNegModel.getValueAt(i, 3));
                    l.add(tdNegModel.getValueAt(i, 4));
                }
                line.put("cols", l);
                WorstDefenseLines.add(line);
            }
            root.put("WorstDefenseLines", WorstDefenseLines);

            // Muraille
            Vector BestDefenseLines = new Vector();
            for (int i = 0; i < 3; i++) {
                HashMap line = new HashMap();
                Vector l = new Vector();
                l.add(i + 1);
                if (_team) {
                    l.add(tdNegModel.getValueAt(tdNegModel.getRowCount() - 1 - i, 1));
                    l.add(tdNegModel.getValueAt(tdNegModel.getRowCount() - 1 - i, 2));
                } else {
                    l.add(tdNegModel.getValueAt(tdNegModel.getRowCount() - 1 - i, 2));
                    l.add(tdNegModel.getValueAt(tdNegModel.getRowCount() - 1 - i, 3));
                    l.add(tdNegModel.getValueAt(tdNegModel.getRowCount() - 1 - i, 4));
                }
                line.put("cols", l);
                BestDefenseLines.add(line);
            }
            root.put("BestDefenseLines", BestDefenseLines);

            // Meilleur Destructer
            Vector BestDestructorLines = new Vector();
            for (int i = 0; i < 3; i++) {
                HashMap line = new HashMap();
                Vector l = new Vector();
                l.add(i + 1);
                if (_team) {
                    l.add(sorPosModel.getValueAt(i, 1));
                    l.add(sorPosModel.getValueAt(i, 2));
                } else {
                    l.add(sorPosModel.getValueAt(i, 2));
                    l.add(sorPosModel.getValueAt(i, 3));
                    l.add(sorPosModel.getValueAt(i, 4));
                }
                line.put("cols", l);
                BestDestructorLines.add(line);
            }
            root.put("BestDestructorLines", BestDestructorLines);

            // Moufle d'or
            Vector WorstDestructorLines = new Vector();
            for (int i = 0; i < 3; i++) {
                HashMap line = new HashMap();
                Vector l = new Vector();
                l.add(i + 1);
                if (_team) {
                    l.add(sorPosModel.getValueAt(sorPosModel.getRowCount() - 1 - i, 1));
                    l.add(sorPosModel.getValueAt(sorPosModel.getRowCount() - 1 - i, 2));
                } else {
                    l.add(sorPosModel.getValueAt(sorPosModel.getRowCount() - 1 - i, 2));
                    l.add(sorPosModel.getValueAt(sorPosModel.getRowCount() - 1 - i, 3));
                    l.add(sorPosModel.getValueAt(sorPosModel.getRowCount() - 1 - i, 4));
                }
                line.put("cols", l);
                WorstDestructorLines.add(line);
            }
            root.put("WorstDestructorLines", WorstDestructorLines);

            // Punching Ball
            Vector BestDestructedLines = new Vector();
            for (int i = 0; i < 3; i++) {
                HashMap line = new HashMap();
                Vector l = new Vector();
                l.add(i + 1);
                if (_team) {
                    l.add(sorNegModel.getValueAt(i, 1));
                    l.add(sorNegModel.getValueAt(i, 2));
                } else {
                    l.add(sorNegModel.getValueAt(i, 2));
                    l.add(sorNegModel.getValueAt(i, 3));
                    l.add(sorNegModel.getValueAt(i, 4));
                }
                line.put("cols", l);
                BestDestructedLines.add(line);
            }
            root.put("BestDestructedLines", BestDestructedLines);

            // String D'acier
            Vector WorstDestructedLines = new Vector();
            for (int i = 0; i < 3; i++) {
                HashMap line = new HashMap();
                Vector l = new Vector();
                l.add(i + 1);
                if (_team) {
                    l.add(sorNegModel.getValueAt(sorNegModel.getRowCount() - 1 - i, 2));
                    l.add(sorNegModel.getValueAt(sorNegModel.getRowCount() - 1 - i, 2));
                } else {
                    l.add(sorNegModel.getValueAt(sorNegModel.getRowCount() - 1 - i, 2));
                    l.add(sorNegModel.getValueAt(sorNegModel.getRowCount() - 1 - i, 3));
                    l.add(sorNegModel.getValueAt(sorNegModel.getRowCount() - 1 - i, 4));
                }
                line.put("cols", l);
                WorstDestructedLines.add(line);
            }
            root.put("WorstDestructedLines", WorstDestructedLines);

            // Crampon
            Vector BestFoulerLines = new Vector();
            for (int i = 0; i < 3; i++) {
                HashMap line = new HashMap();
                Vector l = new Vector();
                l.add(i + 1);
                if (_team) {
                    l.add(foulPosModel.getValueAt(i, 1));
                    l.add(foulPosModel.getValueAt(i, 2));
                } else {
                    l.add(foulPosModel.getValueAt(i, 2));
                    l.add(foulPosModel.getValueAt(i, 3));
                    l.add(foulPosModel.getValueAt(i, 4));
                }
                line.put("cols", l);
                BestFoulerLines.add(line);
            }
            root.put("BestFoulerLines", BestFoulerLines);

            // Vertueux
            Vector WorstFoulerLines = new Vector();
            for (int i = 0; i < 3; i++) {
                HashMap line = new HashMap();
                Vector l = new Vector();
                l.add(i + 1);
                if (_team) {
                    l.add(foulPosModel.getValueAt(foulPosModel.getRowCount() - 1 - i, 1));
                    l.add(foulPosModel.getValueAt(foulPosModel.getRowCount() - 1 - i, 2));

                } else {
                    l.add(foulPosModel.getValueAt(foulPosModel.getRowCount() - 1 - i, 2));
                    l.add(foulPosModel.getValueAt(foulPosModel.getRowCount() - 1 - i, 3));
                    l.add(foulPosModel.getValueAt(foulPosModel.getRowCount() - 1 - i, 4));
                }
                line.put("cols", l);
                WorstFoulerLines.add(line);
            }
            root.put("WorstFoulerLines", WorstFoulerLines);

            // Paillasson
            Vector BestFouledLines = new Vector();
            for (int i = 0; i < 3; i++) {
                HashMap line = new HashMap();
                Vector l = new Vector();
                l.add(i + 1);
                if (_team) {
                    l.add(foulNegModel.getValueAt(i, 1));
                    l.add(foulNegModel.getValueAt(i, 2));
                } else {
                    l.add(foulNegModel.getValueAt(i, 2));
                    l.add(foulNegModel.getValueAt(i, 3));
                    l.add(foulNegModel.getValueAt(i, 4));
                }
                line.put("cols", l);
                BestFouledLines.add(line);
            }
            root.put("BestFouledLines", BestFouledLines);

            // 
            Vector WorstFouledLines = new Vector();
            for (int i = 0; i < 3; i++) {
                HashMap line = new HashMap();
                Vector l = new Vector();
                l.add(i + 1);
                if (_team) {
                    l.add(foulNegModel.getValueAt(foulNegModel.getRowCount() - 1 - i, 1));
                    l.add(foulNegModel.getValueAt(foulNegModel.getRowCount() - 1 - i, 2));
                } else {
                    l.add(foulNegModel.getValueAt(foulNegModel.getRowCount() - 1 - i, 2));
                    l.add(foulNegModel.getValueAt(foulNegModel.getRowCount() - 1 - i, 3));
                    l.add(foulNegModel.getValueAt(foulNegModel.getRowCount() - 1 - i, 4));
                }
                line.put("cols", l);
                WorstFouledLines.add(line);
            }
            root.put("WorstFouledLines", WorstFouledLines);

            if (!_team) {
                Vector BestMinusLines = new Vector();
                int count = 1;
                for (int i = 0; (i < generalModel.getRowCount()) && (count <= 3); i++) {
                    HashMap line = new HashMap();
                    Vector l = new Vector();
                    if (generalModel.getValueAt(i, 3).equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("GOBELIN")) ||
                            generalModel.getValueAt(i, 3).equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("HALFLING")) ||
                            generalModel.getValueAt(i, 3).equals(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("OGRE"))) {
                        l.add(count);
                        count++;
                        l.add(generalModel.getValueAt(i, 2));
                        l.add(generalModel.getValueAt(i, 3));
                        l.add(i + 1);
                        line.put("cols", l);
                        BestMinusLines.add(line);
                    }
                }
                root.put("BestMinusLines", BestMinusLines);
            }

            Vector rankLines = new Vector();
            Vector titles = new Vector();
            for (int i = 0; i < generalModel.getColumnCount(); i++) {
                titles.add(generalModel.getColumnName(i));
            }
            root.put("titles", titles);

            for (int i = 0; i < generalModel.getRowCount(); i++) {
                HashMap line = new HashMap();
                Vector l = new Vector();
                for (int j = 0; j < generalModel.getColumnCount(); j++) {
                    l.add(generalModel.getValueAt(i, j));
                }
                line.put("cols", l);
                rankLines.add(line);
            }
            root.put("lines", rankLines);

            SimpleDateFormat format = new SimpleDateFormat("EEEEEEE dd MMMMMMMMMMM yyyy");
            SimpleDateFormat formatShort = new SimpleDateFormat("dd/MM/yyyy");
            root.put("dateGeneration", formatShort.format(new Date()));
            address = File.createTempFile("result" + format.format(new Date()), ".tmp");
            address.deleteOnExit();
            Writer out = new FileWriter(address);
            temp.process(root, out);
            out.flush();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        } catch (TemplateException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        } catch (URISyntaxException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
        }




        return address;
    }
}
