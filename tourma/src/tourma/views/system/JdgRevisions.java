/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jdgAbout.java
 *
 * Created on 27 mai 2009, 11:08:24
 */
package tourma.views.system;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.ListSelectionModel;
import tourma.utility.Version;

/**
 *
 * @author root.106572700130
 */
public final class JdgRevisions extends javax.swing.JDialog {

    /**
     * Creates new form jdgAbout
     * @param parent
     * @param modal
     */
    public JdgRevisions(final java.awt.Frame parent, final boolean modal) {
        super(parent, modal);
        initComponents();

        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final GraphicsDevice gs = ge.getDefaultScreenDevice();
        final DisplayMode dmode = gs.getDisplayMode();

        this.setSize(580, 350);

            final int screenWidth = dmode.getWidth();
            final int screenHeight = dmode.getHeight();
            this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);


        final ArrayList versions = new ArrayList();
        final ArrayList descriptions = new ArrayList();

        int n = 1;
        String tmpV = Version.getSingleton().getProperty(Integer.toString(n) + java.util.ResourceBundle.getBundle("tourma/languages/language").getString("-V"));
        String tmpD = Version.getSingleton().getProperty(Integer.toString(n) + java.util.ResourceBundle.getBundle("tourma/languages/language").getString("-D"));

        while (tmpV != null) {
            versions.add(tmpV);
            descriptions.add(tmpD);
            n++;
            tmpV = Version.getSingleton().getProperty(Integer.toString(n) + java.util.ResourceBundle.getBundle("tourma/languages/language").getString("-V"));
            tmpD = Version.getSingleton().getProperty(Integer.toString(n) + java.util.ResourceBundle.getBundle("tourma/languages/language").getString("-D"));
        }

        final MtRevisions model = new MtRevisions(versions, descriptions);
        jxtVersions.setModel(model);
        jxtVersions.setDefaultRenderer(String.class, model);
        jxtVersions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        pack();
        repaint();

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
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jxtVersions = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language"); // NOI18N
        setTitle(bundle.getString("Revisions")); // NOI18N
        setMinimumSize(new java.awt.Dimension(640, 480));

        jPanel1.setMinimumSize(new java.awt.Dimension(320, 33));
        jPanel1.setPreferredSize(new java.awt.Dimension(320, 33));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tourma/images/Select.png"))); // NOI18N
        jButton1.setText(bundle.getString("OK")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jxtVersions.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jxtVersions);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    @SuppressWarnings({"PMD.UnusedFormalParameter", "PMD.MethodArgumentCouldBeFinal"})
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jxtVersions;
    // End of variables declaration//GEN-END:variables
    private static final Logger LOG = Logger.getLogger(JdgRevisions.class.getName());
}
