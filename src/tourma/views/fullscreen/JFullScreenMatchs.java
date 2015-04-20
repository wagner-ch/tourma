/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.views.fullscreen;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.Competitor;
import tourma.data.Criteria;
import tourma.data.EIndivPairing;
import tourma.data.Match;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.TeamMatch;
import tourma.data.Tournament;
import tourma.utils.ImageTreatment;
import tourma.utils.TourmaProtocol;

/**
 *
 * @author WFMJ7631
 */
public final class JFullScreenMatchs extends JFullScreen {

    private Round round;
    private boolean loopStop = false;
    private boolean clash = false;

    public JFullScreenMatchs(Socket s) throws IOException {
        super(s);
        initComponents();
        loopStop = false;
    }

    protected void clientLoop() {
        try {

            Font font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/tourma/languages/calibri.ttf"));

            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            int width = gd.getDisplayMode().getWidth();
            int height = gd.getDisplayMode().getHeight();

            float size = (float) height / 50;
            Font f0 = font.deriveFont(Font.ITALIC, size);
            Font f1 = font.deriveFont(Font.BOLD, size);
            Font f = font.deriveFont(Font.PLAIN, size);

            int computed_height = height / 20;

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(TourmaProtocol.TKey.MATCHS.toString());
            out.println(TourmaProtocol.TKey.END.toString());

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));

            Round r;
            while (!loopStop) {
                String inputLine;
                inputLine = in.readLine();
                String buffer = "";
                while (inputLine != null) {
                    if (inputLine.equals(TourmaProtocol.TKey.END.toString())) {
                        SAXBuilder sb = new SAXBuilder();
                        try {
                            Document doc = sb.build(new StringReader(buffer));
                            Tournament.getTournament().loadRosters(doc.getRootElement());
                            Element element = doc.getRootElement().getChild("Parameters");
                            Tournament.getTournament().getParams().setXMLElement(element);
                            element = doc.getRootElement().getChild("Round");
                            r = new Round();
                            r.setXMLElementForDisplay(element);

                            buildPanel(r);

                            this.getGraphicsConfiguration().getDevice().setFullScreenWindow(this);

                        } catch (JDOMException ex) {
                            Logger.getLogger(JFullScreenIndivRank.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(JFullScreenIndivRank.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        out.println(TourmaProtocol.TKey.MATCHS.toString());
                        out.println(TourmaProtocol.TKey.END.toString());

                        buffer = "";
                    } else {
                        buffer += inputLine;
                    }
                    inputLine = in.readLine();
                }
            }

        } catch (IOException | FontFormatException e) {
            Logger.getLogger(JFullScreenIndivRank.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void buildClash() {
        Font font;

        JPanel jpn = new JPanel();
        jpn.setLayout(null);

        getContentPane().removeAll();
        jpnContent = jpn;

        this.getContentPane().add(jpn, BorderLayout.CENTER);
        this.repaint();
    }

    JPanel jpn = new JPanel();

    private void buildPanel(Round r) {

        Font font;

        jpn = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        jpn.setLayout(gbl);

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/tourma/languages/calibri.ttf"));
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(JFullScreenIndivRank.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            font = this.getFont();
        }

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();

        Criteria td = Tournament.getTournament().getParams().getCriteria(0);

        float size = (float) height / 50;
        Font f0 = font.deriveFont(Font.ITALIC, size);
        Font f1 = font.deriveFont(Font.BOLD, size);
        Font f1Winner = font.deriveFont(Font.BOLD, size);
        Font f1Draw = font.deriveFont(Font.ITALIC, size);
        Font f1Looser = font.deriveFont(Font.PLAIN, size);
        Font fBig = font.deriveFont(Font.BOLD, (float) height / 20);

        int computed_height = height / 10;
        int nbCols = 3;
        int computed_width = width / nbCols;

        for (int i = 0; i < r.getMatchsCount(); i++) {
            Color bkg = new Color(255, 255, 255);
            if (i % 2 != 0) {
                bkg = new Color(220, 220, 220);
            }
            Match m = r.getMatch(i);
            if (m instanceof CoachMatch) {
                CoachMatch cm = (CoachMatch) m;

                int colIndex = 0;
                int rowspan = cm.getValueCount();
                if (Tournament.getTournament().getClansCount() > 1) {
                    nbCols = 5;
                    computed_width = width / nbCols;
                    colIndex = 1;
                    JLabel ClanIcon1 = getLabelForObject((cm.getCompetitor1()).getClan(), computed_height, computed_width, f0, bkg);
                    JLabel ClanIcon2 = getLabelForObject((cm.getCompetitor2()).getClan(), computed_height, computed_width, f0, bkg);

                    ClanIcon1.setHorizontalTextPosition(JLabel.LEADING);
                    ClanIcon1.setHorizontalAlignment(JLabel.RIGHT);
                    ClanIcon2.setHorizontalAlignment(JLabel.LEFT);

                    jpn.add(ClanIcon1, getGridbBagConstraints(0, i * (rowspan + 1), rowspan + 1, 1));
                    jpn.add(ClanIcon2, getGridbBagConstraints(nbCols - 1, i * (rowspan + 1), rowspan + 1, 1));
                }
                if (Tournament.getTournament().getParams().isTeamTournament() && (Tournament.getTournament().getParams().getTeamIndivPairing() == EIndivPairing.RANKING)) {
                    nbCols = 5;
                    computed_width = width / nbCols;
                    colIndex = 1;

                    JLabel TeamIcon1 = getLabelForObject(((Coach) (cm.getCompetitor1())).getTeamMates(), computed_height, computed_width, f0, bkg);
                    JLabel TeamIcon2 = getLabelForObject(((Coach) (cm.getCompetitor2())).getTeamMates(), computed_height, computed_width, f0, bkg);
                    TeamIcon1.setHorizontalTextPosition(JLabel.LEADING);
                    TeamIcon1.setHorizontalAlignment(JLabel.RIGHT);
                    TeamIcon2.setHorizontalAlignment(JLabel.LEFT);

                    jpn.add(TeamIcon1, getGridbBagConstraints(0, i * (rowspan + 1), rowspan + 1, 1));
                    jpn.add(TeamIcon2, getGridbBagConstraints(nbCols - 1, i * (rowspan + 1), rowspan + 1, 1));
                }

                JLabel CoachIcon1 = getLabelForObject(cm.getCompetitor1(), computed_height, computed_width, getCoachMatchFont(cm, cm.getCompetitor1(), f1Winner, f1Looser, f1Draw, f1), bkg);
                JLabel CoachIcon2 = getLabelForObject(cm.getCompetitor2(), computed_height, computed_width, getCoachMatchFont(cm, cm.getCompetitor2(), f1Winner, f1Looser, f1Draw, f1), bkg);

                CoachIcon1.setHorizontalAlignment(JLabel.CENTER);
                CoachIcon2.setHorizontalAlignment(JLabel.CENTER);

                jpn.add(CoachIcon1, getGridbBagConstraints(colIndex, i * (rowspan + 1), rowspan, 1));
                jpn.add(CoachIcon2, getGridbBagConstraints(nbCols - colIndex - 1, i * (rowspan + 1), rowspan, 1));

                int value1 = cm.getValue(td).getValue1();
                int value2 = cm.getValue(td).getValue2();
                String sV1 = value1 >= 0 ? Integer.toString(value1) : " - ";
                String sV2 = value2 >= 0 ? Integer.toString(value2) : " - ";

                JLabel CoachScore1 = new JLabel();
                CoachScore1.setSize(computed_width, computed_height);
                CoachScore1.setFont(getCoachMatchFont(cm, cm.getCompetitor1(), f1Winner, f1Looser, f1Draw, f1));

                JLabel CoachScore2 = new JLabel();
                CoachScore2.setSize(computed_width, computed_height);
                CoachScore2.setFont(getCoachMatchFont(cm, cm.getCompetitor2(), f1Winner, f1Looser, f1Draw, f1));

                CoachScore1.setText(sV1);
                CoachScore2.setText(sV2);

                CoachScore1.setHorizontalAlignment(JLabel.CENTER);
                CoachScore1.setBackground(bkg);
                CoachScore1.setOpaque(true);

                CoachScore2.setBackground(bkg);
                CoachScore2.setOpaque(true);
                CoachScore2.setHorizontalAlignment(JLabel.CENTER);

                jpn.add(CoachScore1, getGridbBagConstraints(colIndex, (i + 1) * (rowspan + 1) - 1, 1, 1));
                jpn.add(CoachScore2, getGridbBagConstraints(nbCols - colIndex - 1, (i + 1) * (rowspan + 1) - 1, 1, 1));

                // Score                    
                JLabel score = new JLabel(sV1 + " " + td.getName() + " " + sV2);

                score.setHorizontalAlignment(JLabel.CENTER);
                score.setOpaque(true);
                score.setBackground(bkg);
                score.setFont(f1);

                jpn.add(score, getGridbBagConstraints(colIndex + 1, i * (rowspan + 1), 2, 1));

                Font f2 = font.deriveFont(Font.ITALIC, (float) height / 75);
                for (int j = 1; j < Tournament.getTournament().getParams().getCriteriaCount(); j++) {
                    Criteria crit = Tournament.getTournament().getParams().getCriteria(j);
                    value1 = cm.getValue(crit).getValue1();
                    value2 = cm.getValue(crit).getValue2();
                    JLabel tmp = new JLabel("" + value1 + " " + crit.getName() + " " + value2);
                    tmp.setBackground(bkg);
                    tmp.setOpaque(true);
                    tmp.setFont(f2);
                    tmp.setHorizontalAlignment(JLabel.CENTER);
                    jpn.add(tmp, getGridbBagConstraints(colIndex + 1, i * (rowspan + 1) + j + 1, 1, 1));
                }
            }

            if (m instanceof TeamMatch) {
                TeamMatch tm = (TeamMatch) m;

                int colIndex;
                nbCols = 5;
                int nbValues = tm.getMatch(0).getValueCount();
                int NbLinesPerCoachMatch = nbValues + 1;
                int nbMatchs = tm.getMatchCount();
                int NbLinesPerTeamMatch = NbLinesPerCoachMatch * nbMatchs + 1;

                computed_width = width / nbCols;
                colIndex = 1;

                JLabel TeamIcon1 = getLabelForObject(tm.getCompetitor1(), computed_height, computed_width, f0, bkg);
                JLabel TeamIcon2 = getLabelForObject(tm.getCompetitor2(), computed_height, computed_width, f0, bkg);

                TeamIcon1.setHorizontalTextPosition(JLabel.LEADING);
                TeamIcon1.setHorizontalAlignment(JLabel.RIGHT);
                TeamIcon2.setHorizontalAlignment(JLabel.LEFT);

                jpn.add(TeamIcon1, getGridbBagConstraints(0, i * NbLinesPerTeamMatch, NbLinesPerTeamMatch, 1));
                jpn.add(TeamIcon2, getGridbBagConstraints(nbCols - 1, i * NbLinesPerTeamMatch, NbLinesPerTeamMatch, 1));

                JLabel v = new JLabel("");
                if ((tm.getCompetitor1().getPicture() != null) && Tournament.getTournament().getParams().isUseImage()) {
                    v.setIcon(ImageTreatment.resize(new ImageIcon(tm.getCompetitor1().getPicture()), computed_height, computed_height));
                }
                v.setFont(f1);
                v.setOpaque(true);
                v.setBackground(bkg);
                v.setHorizontalAlignment(JLabel.CENTER);

                jpn.add(v, getGridbBagConstraints(1, i * NbLinesPerTeamMatch, 1, 1));

                JLabel n = new JLabel("" + tm.getVictories((Team) tm.getCompetitor1()) + " - " + tm.getDraw((Team) tm.getCompetitor1()) + " - " + tm.getVictories((Team) tm.getCompetitor2()));
                n.setFont(fBig);
                n.setBackground(bkg);
                n.setOpaque(true);
                n.setHorizontalAlignment(JLabel.CENTER);
                jpn.add(n, getGridbBagConstraints(2, i * NbLinesPerTeamMatch, 1, 1));

                JLabel l = new JLabel("");
                l.setFont(f1);
                if ((tm.getCompetitor2().getPicture() != null) && Tournament.getTournament().getParams().isUseImage()) {
                    l.setIcon(ImageTreatment.resize(new ImageIcon(tm.getCompetitor2().getPicture()), computed_height, computed_height));
                }
                l.setHorizontalTextPosition(JLabel.LEFT);
                l.setBackground(bkg);
                l.setOpaque(true);
                l.setHorizontalAlignment(JLabel.CENTER);
                jpn.add(l, getGridbBagConstraints(3, i * NbLinesPerTeamMatch, 1, 1));

                for (int j = 0; j < tm.getMatchCount(); j++) {

                    Color cmBkg = bkg;
                    if (j % 2 == 0) {
                        cmBkg = new Color(bkg.getRed() - 10, bkg.getGreen() - 10, bkg.getBlue() - 10);
                    }
                    CoachMatch cm = tm.getMatch(j);

                    JLabel CoachIcon1 = getLabelForObject(cm.getCompetitor1(), computed_height, computed_width, getCoachMatchFont(cm, cm.getCompetitor1(), f1Winner, f1Looser, f1Draw, f1), cmBkg);
                    JLabel CoachIcon2 = getLabelForObject(cm.getCompetitor2(), computed_height, computed_width, getCoachMatchFont(cm, cm.getCompetitor2(), f1Winner, f1Looser, f1Draw, f1), cmBkg);
                    CoachIcon1.setHorizontalAlignment(JLabel.CENTER);
                    CoachIcon2.setHorizontalAlignment(JLabel.CENTER);

                    jpn.add(CoachIcon1, getGridbBagConstraints(colIndex, i * NbLinesPerTeamMatch + 1 + j * NbLinesPerCoachMatch, nbValues, 1));
                    jpn.add(CoachIcon2, getGridbBagConstraints(nbCols - colIndex - 1, i * NbLinesPerTeamMatch + 1 + j * NbLinesPerCoachMatch, nbValues, 1));

                    int value1 = cm.getValue(td).getValue1();
                    int value2 = cm.getValue(td).getValue2();
                    String sV1 = value1 >= 0 ? Integer.toString(value1) : " - ";
                    String sV2 = value2 >= 0 ? Integer.toString(value2) : " - ";

                    JLabel CoachScore1 = new JLabel();
                    CoachScore1.setSize(computed_width, computed_height);
                    CoachScore1.setFont(getCoachMatchFont(cm, cm.getCompetitor1(), f1Winner, f1Looser, f1Draw, f1));

                    JLabel CoachScore2 = new JLabel();
                    CoachScore2.setSize(computed_width, computed_height);
                    CoachScore2.setFont(getCoachMatchFont(cm, cm.getCompetitor2(), f1Winner, f1Looser, f1Draw, f1));

                    CoachScore1.setText(sV1);
                    CoachScore2.setText(sV2);

                    CoachScore1.setHorizontalAlignment(JLabel.CENTER);
                    CoachScore1.setBackground(cmBkg);
                    CoachScore1.setOpaque(true);
                    CoachScore2.setBackground(cmBkg);
                    CoachScore2.setOpaque(true);
                    CoachScore2.setHorizontalAlignment(JLabel.CENTER);

                    jpn.add(CoachScore1, getGridbBagConstraints(colIndex, i * NbLinesPerTeamMatch + (j + 1) * NbLinesPerCoachMatch, 1, 1));
                    jpn.add(CoachScore2, getGridbBagConstraints(nbCols - colIndex - 1, i * NbLinesPerTeamMatch + (j + 1) * NbLinesPerCoachMatch, 1, 1));

                    // Score                    
                    JLabel score = new JLabel(sV1 + " " + td.getName() + " " + sV2);

                    score.setHorizontalAlignment(JLabel.CENTER);
                    score.setOpaque(true);
                    score.setBackground(cmBkg);
                    score.setFont(f1);
                    score.setHorizontalAlignment(JLabel.CENTER);
                    jpn.add(score, getGridbBagConstraints(colIndex + 1, i * NbLinesPerTeamMatch + j * NbLinesPerCoachMatch + 1, 2, 1));

                    Font f2 = font.deriveFont(Font.ITALIC, (float) height / 75);
                    for (int k = 1; k < Tournament.getTournament().getParams().getCriteriaCount(); k++) {
                        Criteria crit = Tournament.getTournament().getParams().getCriteria(k);
                        value1 = cm.getValue(crit).getValue1();
                        value2 = cm.getValue(crit).getValue2();
                        JLabel tmp = new JLabel("" + value1 + " " + crit.getName() + " " + value2);
                        tmp.setBackground(cmBkg);
                        tmp.setOpaque(true);
                        tmp.setFont(f2);
                        tmp.setHorizontalAlignment(JLabel.CENTER);
                        jpn.add(tmp, getGridbBagConstraints(colIndex + 1, i * NbLinesPerTeamMatch + j * NbLinesPerCoachMatch + k + 2, 1, 1));
                    }
                }

            }
        }

        this.getContentPane().removeAll();
        jscrp = new JScrollPane();
        jscrp.setViewportView(jpn);
        jpnContent = jpn;

        this.getContentPane().add(jscrp, BorderLayout.CENTER);
        this.repaint();

    }

    protected void setStop(boolean s) {
        loopStop = true;
    }

    /**
     *
     * @param r
     * @throws IOException
     */
    public JFullScreenMatchs(Round r) throws IOException {
        this(r, false);
    }

    @SuppressWarnings("LeakingThisInConstructor")
    public JFullScreenMatchs(Round r, boolean clash) throws IOException {
        super();

        initComponents();

        this.clash = clash;
        round = r;

        if (clash) {
            buildClash();
        } else {
            buildPanel(r);
        }
        this.getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
    }

    private Font getCoachMatchFont(CoachMatch cm, Competitor comp, Font winner, Font looser, Font draw, Font def) {
        Font f;
        if (comp.equals(cm.getWinner())) {
            f = winner;
        } else {
            if (comp.equals(cm.getLooser())) {
                f = looser;
            } else {
                Criteria td = Tournament.getTournament().getParams().getCriteria(0);
                if ((cm.getValue(td).getValue1() == -1) || (cm.getValue(td).getValue2() == -1)) {
                    f = def;
                } else {
                    f = draw;
                }
            }
        }
        return f;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setAlwaysOnTop(true);
        setName("FullScreen Tourma"); // NOI18N
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private JFullScreenMatchs.Animation clashAnim;

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_C) {
            if (animationStarted) {
                jscrp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
                jscrp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                animationStarted = false;
                if (clashAnim.isAlive()) {
                    try {
                        clashAnim.join();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(JFullScreenMatchs.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                animationStarted = true;
                clashAnim = new JFullScreenMatchs.Animation();
                clashAnim.start();
            }

        }
    }//GEN-LAST:event_formKeyPressed
    private boolean animationStarted = false;
    private Animation animation;

    private JFullScreenMatchs me = this;

    public JPanel createClashTeamPane(Team t, TeamMatch tm, boolean right) {
        JPanel p = null;

        if (t != null) {
            int max_width=0, c_height=0;
            
            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            int width = gd.getDisplayMode().getWidth();
            int height = gd.getDisplayMode().getHeight();

            p = new JPanel();
            //Compute dimensions
            int nbPlayers = t.getActivePlayerNumber();

            // Compute number of lines: 2x nbPlayers
            int line_height = (height * 6 / 10) / (nbPlayers * 2);
            JLabel jlbTeam = new JLabel();
            jlbTeam.setHorizontalAlignment(JLabel.CENTER);
            Font font;
            try {
                font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/tourma/languages/calibri.ttf"));
            } catch (FontFormatException | IOException ex) {
                Logger.getLogger(JFullScreenIndivRank.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                font = this.getFont();
            }
            jlbTeam.setBackground(Color.WHITE);

            jlbTeam.setFont(font.deriveFont((float) (line_height * nbPlayers / 2)));

            jlbTeam.setText(t.getName());

            p.setLayout(new BorderLayout());
            p.setBackground(Color.WHITE);
            p.add(jlbTeam, BorderLayout.CENTER);
            c_height+=jlbTeam.getPreferredSize().height;
            max_width=Math.max(max_width, jlbTeam.getPreferredSize().width);
            
            if (t.getPicture() != null) {
                BufferedImage pict = t.getPicture();
                JLabel icon = new JLabel();
                icon.setIcon(ImageTreatment.resize(new ImageIcon(pict), line_height * nbPlayers / 2, pict.getWidth() * (line_height * nbPlayers / 2) / pict.getHeight()));
                icon.setBackground(Color.WHITE);
                icon.setHorizontalAlignment(JLabel.CENTER);
                p.add(icon, BorderLayout.NORTH);
                c_height+=pict.getWidth() * (line_height * nbPlayers / 2) / pict.getHeight();
            }

            JPanel players = new JPanel(new GridLayout(tm.getMatchCount(), 1));
            players.setBackground(Color.WHITE);
            for (int i = 0; i < tm.getMatchCount(); i++) {
                Coach c = null;
                int p_width=0;
                if (tm.getCompetitor1() == t) {
                    c = (Coach) tm.getMatch(i).getCompetitor1();
                }
                if (tm.getCompetitor2() == t) {
                    c = (Coach) tm.getMatch(i).getCompetitor2();
                }
                if (c != null) {
                    JPanel jpn2 = new JPanel(new BorderLayout());
                    if (c.getPicture() != null) {
                        BufferedImage pict = t.getPicture();
                        JLabel icon = new JLabel();
                        icon.setIcon(ImageTreatment.resize(new ImageIcon(pict), line_height * nbPlayers / 2, pict.getWidth() * (line_height * nbPlayers / 2) / pict.getHeight()));
                        jpn2.add(icon, right ? BorderLayout.EAST : BorderLayout.WEST);
                        p_width=line_height * nbPlayers / 2;
                    }
                    jpn2.setBackground(Color.WHITE);
                    JLabel jlbCoach = new JLabel();
                    jlbCoach.setOpaque(true);
                    jlbCoach.setHorizontalAlignment(right ? JLabel.LEFT : JLabel.RIGHT);
                    if (right) {
                        jlbCoach.setText(" " + c.getName());
                    } else {
                        jlbCoach.setText(c.getName() + " ");
                    }
                    jlbCoach.setBackground(i % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
                    jlbCoach.setFont(font.deriveFont((float) line_height));
                    jpn2.add(jlbCoach, BorderLayout.CENTER);
                    p_width+=jlbCoach.getPreferredSize().width;                    
                    players.add(jpn2);
                    max_width=Math.max(max_width,p_width);
                    if (c.getPicture()!=null)
                    {
                        c_height+=Math.max(jlbCoach.getPreferredSize().height,c.getPicture().getWidth() * (line_height * nbPlayers / 2) / c.getPicture().getHeight());
                    }
                    else
                    {
                        c_height+=jlbCoach.getPreferredSize().height;
                    }
                }
            }
            p.add(players, BorderLayout.SOUTH);
            p.setSize(max_width, c_height);
        }

        return p;
    }

    public JPanel createClashCoachPane(Coach t, boolean right) {
        JPanel p = null;
        if (t != null) {
            p = new JPanel();
            //Compute dimensions

            // Compute number of lines: 2x nbPlayers
            int line_height = (this.getHeight() * 8 / 10) / 2;
            JLabel jlbCoach = new JLabel(t.getName());
            Font font;
            try {
                font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/tourma/languages/calibri.ttf"));
            } catch (FontFormatException | IOException ex) {
                Logger.getLogger(JFullScreenIndivRank.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                font = this.getFont();
            }
            jlbCoach.setFont(font.deriveFont(line_height));

            p.setLayout(new BorderLayout());
            p.add(jlbCoach, BorderLayout.CENTER);

            if (t.getPicture() != null) {
                BufferedImage pict = t.getPicture();
                JLabel icon = new JLabel();

                icon.setIcon(ImageTreatment.resize(new ImageIcon(pict), line_height, pict.getWidth() * line_height / pict.getHeight()));
                p.add(icon, BorderLayout.NORTH);
            }
        }
        return p;
    }

    private JPanel jpnClash1 = null;
    private JPanel jpnClash2 = null;
    private JLabel jlbTitle1 = null;
    private JLabel jlbTitle2 = null;
    private int jpn1X = 0;
    private int jpn2X = 0;
    private int jpn1Y = 0;
    private int jpn2Y = 0;

    /*@Override
     public void paint(Graphics g) {
     super.paint(g);

     GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
     int width = gd.getDisplayMode().getWidth();
     int height = gd.getDisplayMode().getHeight();
     if (clash && jpnClash1 != null && jpnClash2 != null && jlbVS != null) {
     jlbVS.paintImmediately((height - jlbVS.getHeight()) / 2, (width - jlbVS.getWidth()) / 2, jlbVS.getHeight(), jlbVS.getWidth());

     jpnClash1.paintImmediately(jpn1X, jpn1Y, size1.width, jpnClash1.getHeight());
     jpnClash2.paintImmediately(jpn2X, jpn2Y, size2.width, jpnClash2.getHeight());

     }
     }*/
    /**
     * Internal class for animation
     */
    private class Animation extends Thread {

        @SuppressFBWarnings(value = "SWL_SLEEP_WITH_LOCK_HELD", justification = "Sleep is used for animation")
        @Override
        @SuppressWarnings("SleepWhileInLoop")
        public void run() {

            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            int width = gd.getDisplayMode().getWidth();
            int height = gd.getDisplayMode().getHeight();
            //System.out.println("Clash: " + clash);
            me.setSize(width, height);

            while (animationStarted) {
                if (clash) {
                    for (int i = 0; i < round.getMatchsCount(); i++) {
                        if (jpnClash1 != null) {
                            jpnContent.remove(jpnClash1);
                        }
                        if (jpnClash2 != null) {
                            jpnContent.remove(jpnClash2);
                        }
                        if (jlbTitle1 != null) {
                            jpnContent.remove(jlbTitle1);
                        }
                        if (jlbTitle2 != null) {
                            jpnContent.remove(jlbTitle2);
                        }

                        Match m = round.getMatch(i);
                        jpnClash1 = null;
                        jpnClash2 = null;
                        if (m instanceof TeamMatch) {
                            jpnClash1 = createClashTeamPane((Team) m.getCompetitor1(), (TeamMatch) m, false);
                            jpnClash2 = createClashTeamPane((Team) m.getCompetitor2(), (TeamMatch) m, true);
                        }
                        if (m instanceof CoachMatch) {
                            jpnClash1 = createClashCoachPane((Coach) m.getCompetitor1(), false);
                            jpnClash2 = createClashCoachPane((Coach) m.getCompetitor2(), true);
                        }

                        if ((jpnClash1 != null) && (jpnClash2 != null)) {
                            jlbTitle1 = new JLabel();
                            int line_height = (height * 1 / 10);
                            jlbTitle1.setFont(getFont().deriveFont((float) line_height));
                            jlbTitle1.setText("Round " + (Tournament.getTournament().getRoundIndex(round) + 1));

                            jlbTitle2 = new JLabel();
                            line_height = (height * 1 / 20);
                            jlbTitle2.setFont(getFont().deriveFont((float) line_height));
                            jlbTitle2.setText("Table " + (i + 1));

                            jpnContent.add(jlbTitle1);
                            jpnContent.add(jlbTitle2);
                            if ((jpnClash1 != null) && (jpnClash2 != null) && (clash)) {

                                jpnContent.add(jpnClash1);
                                jpnContent.add(jpnClash2);
                                jpnContent.setBackground(Color.WHITE);

                                jpnContent.setSize(width, height);

                                me.getContentPane().validate();

                                me.repaint();
                                /*me.setResizable(false);
                                 me.getContentPane().setSize(width, height);
                                 me.getContentPane().validate();
                                
                                 //                                me.setUndecorated(true);
                                 //me.setExtendedState(JFrame.MAXIMIZED_BOTH);
                                 me.setSize(width, height);*/
                            }

                            long time = 1000000000;
                            Dimension size1 = jpnClash1.getPreferredSize();
                            Dimension size2 = jpnClash2.getPreferredSize();
                            jpn1Y = (height - size1.height) / 2;
                            jpn2Y = (height - size2.height) / 2;

                            Dimension sizeTitle1 = jlbTitle1.getPreferredSize();
                            jlbTitle1.setBounds((width - sizeTitle1.width) / 2, 0, sizeTitle1.width, sizeTitle1.height);

                            Dimension sizeTitle2 = jlbTitle2.getPreferredSize();
                            jlbTitle2.setBounds((width - sizeTitle2.width) / 2, sizeTitle1.height, sizeTitle2.width, sizeTitle2.height);

                            // 1 seconde <=> lateral margin => timer
                            long timer = time / (width / 2);

                            for (int j = 0; j < width; j++) {
                                // Clear
                                // Draw vs
                                // Set JPN1 & JPN2 position

                                jpn1X = 0 - size1.width + (j < width / 2 ? j : width / 2);
                                jpn2X = width - (j > width / 2 ? j - width / 2 : 0);

                                //System.err.println("X1: " + jpn1X + " Y1: " + jpn1Y + " X2: " + jpn2X + " Y2: " + jpn2Y);
                                //me.repaint();
                                try {
                                    // Animate !!
                                    sleep(timer / 1000000, (int) timer % 1000000);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(JFullScreenMatchs.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                //Insets insets = jpnContent.getInsets();
                                jpnClash1.setBounds(jpn1X, jpn1Y, size1.width, size1.height);
                                jpnClash2.setBounds(jpn2X, jpn2Y, size2.width, size2.height);

                                //jpnClash1.paintComponents(jpnContent.getGraphics());
                                //jpnClash2.paintComponents(jpnContent.getGraphics());
                                me.repaint();
                                //jpnContent.paintComponents(me.getGraphics());
                            }
                            try {
                                sleep((2 * time) / 1000000, (int) (2 * time) % 1000000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(JFullScreenMatchs.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    private static final Logger LOG = Logger.getLogger(JFullScreenMatchs.class.getName());

    private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException {
        throw new java.io.NotSerializableException(getClass().getName());
    }

    private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException {
        throw new java.io.NotSerializableException(getClass().getName());
    }
}
