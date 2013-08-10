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

import tourma.tableModel.mjtRanking;
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
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import tourma.data.Criteria;
import tourma.tableModel.mjtAnnexRank;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
public class jdgGlobal extends javax.swing.JDialog {

    int mRoundNumber;
    Tournament mTour;
    boolean mResult;
    File mFilename = null;
    mjtRanking mRanking;
    HashMap<Criteria, mjtAnnexRank> mAnnexAgainstRankings;
    HashMap<Criteria, mjtAnnexRank> mAnnexForRankings;
    boolean mClan;
    boolean mTeam;

    /**
     * Creates new form jdgRoundReport
     */
    public jdgGlobal(final java.awt.Frame parent, final boolean modal, final int roundNumber, final Tournament tour, final mjtRanking model, final HashMap<Criteria, mjtAnnexRank> annexForRankings, final HashMap<Criteria, mjtAnnexRank> annexAgainstRankings, final boolean clan, final boolean team) {
        super(parent, modal);
        initComponents();
        //_round = round;
        mRoundNumber = roundNumber;
        mTour = tour;

        mClan = clan;
        mTeam = team;

        mRanking = model;
        mAnnexForRankings = annexForRankings;
        mAnnexAgainstRankings = annexAgainstRankings;

        this.setTitle(tour.getParams().mTournamentName + " - " + java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Round") + roundNumber);
        try {
            jepHTML.setContentType("html");
            mFilename = CreateReport();
            jepHTML.setPage(mFilename.toURI().toURL());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(parent, e.getLocalizedMessage());
        }
        this.setPreferredSize(new Dimension(800, 600));
        pack();
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
        jbtOK = new javax.swing.JButton();
        jbtPrint = new javax.swing.JButton();
        jbtExport = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jepHTML = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Ranking"); // NOI18N

        jbtOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Select.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE); // NOI18N
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
         final JFileChooser jfc = new JFileChooser();
         if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
             final File export = jfc.getSelectedFile();

             try {
                 final FileReader in = new FileReader(mFilename);
                 final FileWriter out = new FileWriter(export);
                 int c;

                 while ((c = in.read()) != -1) {
                     out.write(c);
                 }

                 in.close();
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
            final Template temp = cfg.getTemplate("global.html");


            final ArrayList<Round> rounds = new ArrayList();
            for (int i = 0; i < mTour.getRounds().size() && i < mRoundNumber; i++) {
                rounds.add(mTour.getRounds().get(i));
            }

            final Map root = new HashMap();
            root.put("nom", mTour.getParams().mTournamentName + " - " + java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Round") + mRoundNumber);
            String name = "Classements";
            if (mClan) {
                name = " par clan";
            } else {
                if (mTeam) {
                    name = " par &eacute;quipe";
                } else {
                    name = " par coach";
                }
            }

            root.put("title", name);


            final ArrayList titles = new ArrayList();
            for (int i = 0; i < mRanking.getColumnCount(); i++) {
                titles.add(mRanking.getColumnName(i));
            }
            root.put("titles", titles);

            final ArrayList lines = new ArrayList();
            for (int i = 0; i < mRanking.getRowCount(); i++) {
                final HashMap line = new HashMap();
                final ArrayList cols = new ArrayList();
                for (int j = 0; j < mRanking.getColumnCount(); j++) {
                    cols.add(mRanking.getValueAt(i, j));
                }
                line.put("cols", cols);
                lines.add(line);
            }
            root.put("lines", lines);

            root.put("titreMoreFor", "Le plus r&eacute;alis&eacute;");
            root.put("titreLessFor", "Le moins r&eacute;alis&eacute;");
            root.put("titreMoreAgainst", "Le plus subi");
            root.put("titreLessAgainst", "Le moins subi");

            final ArrayList criterias = new ArrayList();
            for (int i = 0; i < mTour.getParams().mCriterias.size(); i++) {
                final HashMap criteria = new HashMap();
                final Criteria c = mTour.getParams().mCriterias.get(i);
                criteria.put("name", c.mName);

                final mjtAnnexRank annexFor = mAnnexForRankings.get(c);
                final mjtAnnexRank annexAgainst = mAnnexAgainstRankings.get(c);
                criteria.put("MoreFor", annexFor.getValueAt(0, 1));
                criteria.put("LessFor", annexFor.getValueAt(annexFor.getRowCount() - 1, 1));
                criteria.put("MoreAgainst", annexAgainst.getValueAt(0, 1));
                criteria.put("LessAgainst", annexAgainst.getValueAt(annexAgainst.getRowCount() - 1, 1));

                criterias.add(criteria);
            }
            root.put("criterias", criterias);

            final SimpleDateFormat format = new SimpleDateFormat("EEEEEEE dd MMMMMMMMMMM yyyy", Locale.getDefault());
            final SimpleDateFormat formatShort = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            root.put("dateGeneration", formatShort.format(new Date()));
            address = File.createTempFile("result" + format.format(new Date()), ".tmp");
            address.deleteOnExit();
            final Writer out = new FileWriter(address);
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
