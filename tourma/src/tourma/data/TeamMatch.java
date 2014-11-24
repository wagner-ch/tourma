/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import org.jdom2.Element;

/**
 *
 * @author Frederic Berger
 */
public class TeamMatch extends Match {
    private static final Logger LOG = Logger.getLogger(TeamMatch.class.getName());

    /**
     *
     */
    private final ArrayList<CoachMatch> mMatchs;

    /**
     *
     * @param round
     */
    public TeamMatch(Round round) {
        super(round);
        mMatchs = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    @Override
    public Competitor getWinner() {

        Tournament tour = Tournament.getTournament();
        final Team team1 = (Team) getCompetitor1();
        final Team team2 = (Team) getCompetitor2();

        Team winner;

        int nbVictory = 0;
        int nbLost = 0;

        Criteria td = tour.getParams().getCriteria(0);
        for (CoachMatch m : mMatchs) {
            if (m.getValue(td).getValue1() > m.getValue(td).getValue2()) {
                nbVictory++;
            } else {
                if (m.getValue(td).getValue1() < m.getValue(td).getValue2()) {
                    nbLost++;
                }
            }
        }

        if (team1 == Team.getNullTeam()) {
            winner = team2;
        } else {
            if (team2 == Team.getNullTeam()) {
                winner = team1;
            } else {
                if (nbVictory > nbLost) {
                    winner = team1;
                } else {
                    if (nbVictory < nbLost) {
                        winner = team2;
                    } else {
                        Random ran=new Random();
                        final int r = ran.nextInt()%2;
                        if (r == 0) {
                            winner = team1;
                        } else {
                            winner = team2;
                        }
                    }
                }
            }
        }
        return winner;
    }

    /**
     *
     * @return
     */
    @Override
    public Competitor getLooser() {
        Tournament tour = Tournament.getTournament();
        final Team team1 = (Team) getCompetitor1();
        final Team team2 = (Team) getCompetitor2();

        Team looser;

        int nbVictory = 0;
        int nbLost = 0;

        Criteria td = tour.getParams().getCriteria(0);
        for (CoachMatch m : mMatchs) {
            if (m.getValue(td).getValue1() > m.getValue(td).getValue2()) {
                nbVictory++;
            } else {
                if (m.getValue(td).getValue1() < m.getValue(td).getValue2()) {
                    nbLost++;
                }
            }
        }

        if (team1 == Team.getNullTeam()) {
            looser = team1;
        } else {
            if (team2 == Team.getNullTeam()) {
                looser = team2;
            } else {
                if (nbVictory > nbLost) {
                    looser = team2;
                } else {
                    if (nbVictory < nbLost) {
                        looser = team1;
                    } else {
                        Random ran=new Random();
                        final int r = ran.nextInt()%2;
                        if (r == 0) {
                            looser = team2;
                        } else {
                            looser = team1;
                        }
                    }
                }
            }
        }
        return looser;

    }

    /* public static Team getTeamMatchWinner(final int teamMatesNumber, final int matchIndex, final ArrayList<CoachMatch> matchs) {
     final Criteria td = Tournament.getTournament().getParams().mCriterias.get(0);
     CoachMatch m = matchs.get(matchIndex * teamMatesNumber);
     final Team team1 = m.mCoach1.mTeamMates;
     final Team team2 = m.mCoach2.mTeamMates;

     Team winner;

     int nbVictory = 0;
     int nbLost = 0;

     for (int j = 0; j < teamMatesNumber; j++) {
     m = matchs.get(matchIndex * teamMatesNumber + j);
     if (m.mValues.get(td).mValue1 > m.mValues.get(td).mValue2) {
     nbVictory++;
     } else {
     if (m.mValues.get(td).mValue1 < m.mValues.get(td).mValue2) {
     nbLost++;
     }
     }
     }

     if (team1 == Team.sNullTeam) {
     winner = team2;
     } else {
     if (team2 == Team.sNullTeam) {
     winner = team1;
     } else {
     if (nbVictory > nbLost) {
     winner = team1;
     } else {
     if (nbVictory < nbLost) {
     winner = team2;
     } else {
     if (((int) Math.random()) % 2 == 0) {
     winner = team1;
     } else {
     winner = team2;
     }
     }
     }
     }
     }
     return winner;
     }*/

    /**
     *
     * @return
     */
    
    @Override
    public Element getXMLElement() {
        final Element match = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MATCH"));
        match.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TEAM1"), this.getCompetitor1().getName());
        match.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TEAM2"), this.getCompetitor2().getName());

        for (CoachMatch mMatch : mMatchs) {
            Element subMatch = mMatch.getXMLElement();
            match.addContent(subMatch);
        }
        return match;
    }

    /**
     *
     * @param match
     */
    @Override
    public void setXMLElement(final Element match) {

        final String c1 = match.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TEAM1")).getValue();
        final String c2 = match.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TEAM2")).getValue();
        this.setCompetitor1(Team.getTeam(c1));
        this.setCompetitor2(Team.getTeam(c2));

        if (((Team) getCompetitor1()) != null) {
            if (getCompetitor1().isMatchsNotNull()) {
                getCompetitor1().addMatch(this);
            }
        } else {
            setCompetitor1(Team.getNullTeam());
        }

        if (((Team) getCompetitor2()) != null) {
            if (getCompetitor2().isMatchsNotNull()) {
                getCompetitor2().addMatch(this);
            }
        } else {
            setCompetitor2(Team.getNullTeam());
        }

        final List<Element> values = match.getChildren(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MATCH"));
        final Iterator<Element> v = values.iterator();

        while (v.hasNext()) {
            CoachMatch m = new CoachMatch(getRound());
            final Element val = v.next();
            m.setXMLElement(val);

            mMatchs.add(m);
        }
    }

    /**
     *
     * @param t1
     * @return
     */
    public int getVictories(Team t1) {
        Tournament tour = Tournament.getTournament();
        final Team team1 = t1;
        Team team2;

        int nbVictories = 0;

        if (t1 == getCompetitor1()) {
            team2 = (Team) getCompetitor2();
        } else {
            if (t1 == getCompetitor2()) {
                team2 = (Team) getCompetitor1();
            } else {
                team2 = null;
            }
        }

        if (team2 == Team.getNullTeam()) {
            return 8;
        }
        if (team1 == Team.getNullTeam()) {
            return 0;
        }

        if (team2 != null) {
            int nbVictory = 0;
            int nbLost = 0;

            Criteria td = tour.getParams().getCriteria(0);
            for (CoachMatch m : mMatchs) {
                if (m.getValue(td).getValue1() > m.getValue(td).getValue2()) {
                    nbVictory++;
                } else {
                    if (m.getValue(td).getValue1() < m.getValue(td).getValue2()) {
                        nbLost++;
                    }
                }
            }

            if (this.getCompetitor2() == t1) {
                nbVictories = nbLost;
            } else {
                nbVictories = nbVictory;
            }
        }
        return nbVictories;
    }

    /**
     *
     * @param t1
     * @return
     */
    public int getLoss(Team t1) {
        Tournament tour = Tournament.getTournament();
        final Team team1 = t1;
        Team team2;

        int nbLoose = 0;

        if (t1 == getCompetitor1()) {
            team2 = (Team) getCompetitor2();
        } else {
            if (t1 == getCompetitor2()) {
                team2 = (Team) getCompetitor1();
            } else {
                team2 = null;
            }
        }

        if (team2 == Team.getNullTeam()) {
            return 0;
        }
        if (team1 == Team.getNullTeam()) {
            return 8;
        }

        if (t1 == getCompetitor1()) {
            team2 = (Team) getCompetitor2();
        } else {
            if (t1 == getCompetitor2()) {
                team2 = (Team) getCompetitor1();
            } else {
                team2 = null;
            }
        }

        if (team2 != null) {
            int nbVictory = 0;
            int nbLost = 0;

            Criteria td = tour.getParams().getCriteria(0);
            for (CoachMatch m : mMatchs) {
                if (m.getValue(td).getValue1() > m.getValue(td).getValue2()) {
                    nbVictory++;
                } else {
                    if (m.getValue(td).getValue1() < m.getValue(td).getValue2()) {
                        nbLost++;
                    }
                }
            }

            if (team2 == t1) {
                nbLoose = nbVictory;
            } else {
                nbLoose = nbLost;
            }
        }
        return nbLoose;
    }

    /**
     *
     * @param t1
     * @return
     */
    public int getDraw(Team t1) {
        Tournament tour = Tournament.getTournament();
        final Team team1 = t1;
        Team team2;

        int nbDraw = 0;

        if (t1 == getCompetitor1()) {
            team2 = (Team) getCompetitor2();
        } else {
            if (t1 == getCompetitor2()) {
                team2 = (Team) getCompetitor1();
            } else {
                team2 = null;
            }
        }

        if (team2 == Team.getNullTeam()) {
            return 0;
        }
        if (team1 == Team.getNullTeam()) {
            return 0;
        }

        if (team2 != null) {
            int nbVictory = 0;
            int nbLost = 0;

            Criteria td = tour.getParams().getCriteria(0);
            for (CoachMatch m : mMatchs) {
                if ((m.getValue(td).getValue1() == m.getValue(td).getValue2()) && (m.getValue(td).getValue2() != -1)) {
                    nbDraw++;
                }
            }
        }
        return nbDraw;
    }

    /**
     * @return the mMatchs count
     */
    public int getMatchCount() {
        return mMatchs.size();
    }
    
    /**
     * @param i
     * @return the mMatchs
     */
    public CoachMatch getMatch(int i) {
        return mMatchs.get(i);
    }

    /**
     * Clear the match list
     */
   public void clearMatchs()
   {
       mMatchs.clear();
   }
    
   /**
    * 
    * @param c
    * @return 
    */
   public boolean containsMatch(CoachMatch c)
   {
       return mMatchs.contains(c);
   }
   
   /**
    * 
    * @param c 
    */
   public void addMatch(CoachMatch c)
   {
       mMatchs.add(c);
   }
    
}
