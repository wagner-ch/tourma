/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils.web;

import java.util.ArrayList;
import org.apache.commons.lang3.StringEscapeUtils;
import teamma.languages.Translate;
import tourma.data.CoachMatch;
import tourma.data.Criteria;
import tourma.data.ETeamPairing;
import tourma.data.Match;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.TeamMatch;
import tourma.data.Tournament;

/**
 *
 * @author WFMJ7631
 */
public class WebCup {

    protected static String CS_MainTable = "MainTable";
    protected static String CS_Victory = "Victory";
    protected static String CS_Draw = "Draw";
    protected static String CS_LooserTable = "LooserTable";

    public static String getHTML() {
        StringBuilder sb = new StringBuilder("");
        final ArrayList<Round> rounds_with_cup = new ArrayList<>();

        
        for (int i = 0; i < Tournament.getTournament().getRoundsCount(); i++) {
            Round round = Tournament.getTournament().getRound(i);
            if (round.isCup()) {
                rounds_with_cup.add(round);
            }
        }

        int nb_looseMatch = 0;        

        for (int i = 0; i < rounds_with_cup.size(); i++) {
            final Round r = rounds_with_cup.get(i);

            // Add Title for the Round
            sb.append("<CENTER>" + StringEscapeUtils.escapeHtml4(Translate.translate(tourma.languages.Translate.CS_Round)) + " " + (i + 1) + "</CENTER>");
            if (r.isLooserCup()) {
                sb.append("<BR><CENTER>" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_MainTable)) + " " + (i + 1) + "</CENTER>");
            }
            sb.append("<table>");
            // Add Title Line            
            sb.append(getHTMLHeader());

            final int remaining_tour = r.getCupMaxTour() - r.getCupTour() + 1;
            int nb_match = (int) Math.pow(2, remaining_tour - 1) / 2;
            if (nb_match == 0) {
                nb_match = 1;
            }

            //final Tournament tour = Tournament.getTournament();
            for (int j = 0; j < nb_match; j++) {
                Match m;
                m = r.getMatch(j);

                sb.append(getHTMLLine(m, j));
            }

            sb.append("</table>");
            if (r.isLooserCup()) {
                if (r.getCupTour() > 0) {
                    nb_looseMatch = nb_looseMatch / 2 + nb_match;

                    sb.append("<BR><CENTER>" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_LooserTable)) + " " + (i + 1) + "</CENTER>");
                    sb.append("<table>");

                    // Add Title Line  
                    sb.append(getHTMLHeader());
                    /* Check the maximum round for looser cup */
                    if (Math.round(Math.pow(2, i - 1) / 2) == rounds_with_cup.get(0).getMatchsCount()) {
                        /* We are at maximum looser cup round */
                        nb_looseMatch = 1;
                    }
                    if (Math.pow(2, i - 1) / 2 > rounds_with_cup.get(0).getMatchsCount()) {
                        /* We are at maximum looser cup round */
                        nb_looseMatch = 0;
                    }

                    for (int j = nb_match; (j < nb_match + nb_looseMatch) && (j < r.getMatchsCount()); j++) {
                        Match m;

                        m = r.getMatch(j);
                        sb.append(getHTMLLine(m, j));
                        // Draw line between the previous matches end this one
                    }
                    sb.append("</table>");
                }
            }
        }
        return sb.toString();
    }

    static protected String getHTMLLine(Match m, int index) {
        StringBuilder sb = new StringBuilder("");
        Criteria td = Tournament.getTournament().getParams().getCriteria(0);
        sb.append("<tr>");
        sb.append("<td>" + (index + 1) + "</td>");

        sb.append("<td>" + StringEscapeUtils.escapeHtml4(m.getCompetitor1().getName()) + " 1</td>");
        if (m instanceof TeamMatch) {
            sb.append("<td>" + ((TeamMatch) m).getVictories((Team) m.getCompetitor1()) + "</td>");
            sb.append("<td>" + ((TeamMatch) m).getDraw((Team) m.getCompetitor1()) + "</td>");
            sb.append("<td>" + ((TeamMatch) m).getVictories((Team) m.getCompetitor2()) + "</td>");
        } else {
            sb.append("<td>" + ((CoachMatch) m).getValue(td).getValue1() + "</td>");
            sb.append("<td>" + ((CoachMatch) m).getValue(td).getValue2() + "</td>");
        }
        sb.append("<td>" + StringEscapeUtils.escapeHtml4(m.getCompetitor2().getName()) + " 2</td>");

        sb.append("</tr>");
        return sb.toString();
    }

    static protected String getHTMLHeader() {
        StringBuilder sb = new StringBuilder("");
        sb.append("<tr>");
        sb.append("<td>#</td>");

        Criteria td = Tournament.getTournament().getParams().getCriteria(0);
        if (Tournament.getTournament().getParams().isTeamTournament() && (Tournament.getTournament().getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING)) {
            sb.append("<td>" + StringEscapeUtils.escapeHtml4(Translate.translate(tourma.languages.Translate.CS_Team)) + " 1</td>");
            sb.append("<td>" + StringEscapeUtils.escapeHtml4(CS_Victory) + " 1</td>");
            sb.append("<td>" + StringEscapeUtils.escapeHtml4(CS_Draw) + "</td>");
            sb.append("<td>" + StringEscapeUtils.escapeHtml4(CS_Victory) + " 2</td>");
            sb.append("<td>" + StringEscapeUtils.escapeHtml4(Translate.translate(tourma.languages.Translate.CS_Team)) + " 2</td>");
        } else {
            sb.append("<td>" + StringEscapeUtils.escapeHtml4(Translate.translate(tourma.languages.Translate.CS_Coach)) + " 1</td>");
            sb.append("<td>" + StringEscapeUtils.escapeHtml4(td.getName()) + " 1</td>");
            sb.append("<td>" + StringEscapeUtils.escapeHtml4(td.getName()) + " 2</td>");
            sb.append("<td>" + StringEscapeUtils.escapeHtml4(Translate.translate(tourma.languages.Translate.CS_Coach)) + " 2</td>");
        }

        sb.append("</tr>");

        return sb.toString();
    }
}
