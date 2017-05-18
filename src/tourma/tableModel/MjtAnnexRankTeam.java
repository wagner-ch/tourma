/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Component;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import tourma.data.Criteria;
import tourma.data.IWithNameAndPicture;
import tourma.data.ObjectAnnexRanking;
import tourma.data.Parameters;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.TeamMatch;
import tourma.data.Tournament;
import tourma.languages.Translate;
import tourma.utility.StringConstants;
import tourma.utils.ImageTreatment;

/**
 *
 * @author Frederic Berger
 */
@SuppressWarnings({"serial", "ClassWithoutLogger"})
public final class MjtAnnexRankTeam extends MjtAnnexRank {

    /**
     *
     * @param round
     * @param criteria
     * @param subtype
     * @param teams
     * @param full
     * @param ranking_type1
     * @param ranking_type2
     * @param ranking_type3
     * @param ranking_type4
     * @param ranking_type5
     * @param round_only
     */
    public MjtAnnexRankTeam(final int round, final Criteria criteria, final int subtype, final ArrayList<Team> teams, final boolean full, final int ranking_type1, final int ranking_type2, final int ranking_type3, final int ranking_type4, final int ranking_type5, final boolean round_only) {
        super(round, criteria, subtype, teams, full, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5, round_only);
        sortDatas();
    }

    /**
     *
     * @param teamVictory
     * @param round
     * @param criteria
     * @param subtype
     * @param teams
     * @param full
     * @param round_only
     */
    public MjtAnnexRankTeam(final boolean teamVictory, final int round, final Criteria criteria, final int subtype, final ArrayList<Team> teams, final boolean full, final boolean round_only) {

        super(round, criteria, subtype, teams, full, Tournament.getTournament().getParams().getRankingTeam1(), Tournament.getTournament().getParams().gemRankingTeam2(), Tournament.getTournament().getParams().getRankingTeam3(), Tournament.getTournament().getParams().getRankingTeam4(), Tournament.getTournament().getParams().getRankingTeam5(), round_only);
        Parameters params = Tournament.getTournament().getParams();
        if (!teamVictory) {
            this.mRankingType1 = params.getRankingIndiv1();
            this.mRankingType2 = params.getRankingIndiv2();
            this.mRankingType3 = params.getRankingIndiv3();
            this.mRankingType4 = params.getRankingIndiv4();
            this.mRankingType5 = params.getRankingIndiv5();
        }
        sortDatas();

    }

    @Override
    @SuppressWarnings("unchecked")
    protected void sortDatas() {
        mDatas.clear();
        mDatas = new ArrayList<>();
        @SuppressWarnings("unchecked")
        final ArrayList<Team> teams = mObjects;
        for (int k = 0; k < teams.size(); k++) {
            final Team t = teams.get(k);
            int value = 0;
            int value1 = 0;
            int value2 = 0;
            int value3 = 0;
            int value4 = 0;
            int value5 = 0;

            if (t.getMatchCount() > 0) {
                ArrayList<Integer> aValue = new ArrayList<>();
                ArrayList<Integer> aValue1 = new ArrayList<>();
                ArrayList<Integer> aValue2 = new ArrayList<>();
                ArrayList<Integer> aValue3 = new ArrayList<>();
                ArrayList<Integer> aValue4 = new ArrayList<>();
                ArrayList<Integer> aValue5 = new ArrayList<>();

                final ArrayList<Round> rounds = new ArrayList<>();

                if (mRoundOnly) {
                    rounds.add(Tournament.getTournament().getRound(mRound));
                } else {
                    for (int l = 0; (l <= mRound); l++) {
                        rounds.add(Tournament.getTournament().getRound(l));
                    }
                }

                for (int j = 0; j <= t.getMatchCount() - 1; j++) {

                    final TeamMatch tm = (TeamMatch) t.getMatch(j);
                    boolean bFound = false;
                    for (int i = 0; (i < rounds.size()) && (!bFound); i++) {
                        final Round r = rounds.get(i);
                        if (r.containsMatch(tm)) {
                            bFound = true;
                        }
                    }
                    // test if match is in round
                    if (bFound) {
                        aValue.add(tm.getValue(mCriteria, mSubtype, t));

                        aValue1.add(tm.getValue(1, t));
                        aValue1.add(tm.getValue(2, t));
                        aValue1.add(tm.getValue(3, t));
                        aValue1.add(tm.getValue(4, t));
                        aValue1.add(tm.getValue(5, t));
                    }
                }

                if (Tournament.getTournament().getParams().isApplyToAnnexTeam()) {
                    removeMaxValue(aValue);
                    removeMinValue(aValue);
                }
                for (Integer i : aValue) {
                    value += i;
                }
                if (Tournament.getTournament().getParams().isUseBestResultTeam()) {
                    while (aValue1.size() > Tournament.getTournament().getParams().getBestResultTeam()) {
                        removeMinValue(aValue1);
                    }
                    while (aValue2.size() > Tournament.getTournament().getParams().getBestResultTeam()) {
                        removeMinValue(aValue2);
                    }
                    while (aValue3.size() > Tournament.getTournament().getParams().getBestResultTeam()) {
                        removeMinValue(aValue3);
                    }
                    while (aValue4.size() > Tournament.getTournament().getParams().getBestResultTeam()) {
                        removeMinValue(aValue4);
                    }
                    while (aValue5.size() > Tournament.getTournament().getParams().getBestResultTeam()) {
                        removeMinValue(aValue5);
                    }
                } else {
                    if (Tournament.getTournament().getParams().isExceptBestAndWorstTeam()) {
                        removeMinValue(aValue1);
                        removeMinValue(aValue2);
                        removeMinValue(aValue3);
                        removeMinValue(aValue4);
                        removeMinValue(aValue5);
                        removeMaxValue(aValue1);
                        removeMaxValue(aValue2);
                        removeMaxValue(aValue3);
                        removeMaxValue(aValue4);
                        removeMaxValue(aValue5);
                    }
                }

                value1 = getValueFromArray(mRankingType1, aValue1);
                value2 = getValueFromArray(mRankingType2, aValue2);
                value3 = getValueFromArray(mRankingType3, aValue3);
                value4 = getValueFromArray(mRankingType4, aValue4);
                value5 = getValueFromArray(mRankingType5, aValue5);
            }
            mDatas.add(new ObjectAnnexRanking(t, value, value1, value2, value3, value4, value5));
        }

        Collections.sort(mDatas);
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(final int col) {
        String val = StringConstants.CS_NULL;
        switch (col) {
            case 0:
                val = StringConstants.CS_HASH;
                break;
            case 1:
                val = Translate.translate(Translate.CS_Team);
                break;
            case 2:
                if (mSubtype == 0) {
                    val = mCriteria.getName() + " " + Translate.translate(Translate.CS_Team);
                } else {
                    if (mSubtype == 1) {
                        val = mCriteria.getName() + " " + Translate.translate(Translate.CS_Opponent);
                    } else {
                        val = mCriteria.getName() + " " + Translate.translate(Translate.CS_Difference);
                    }
                }
                break;
            default:
                break;
        }
        return val;
    }

    @Override
    public Object getValueAt(final int row, final int col) {

        Object val = StringConstants.CS_NULL;
        try {
            final ObjectAnnexRanking obj = (ObjectAnnexRanking) mDatas.get(row);

            switch (col) {
                case 0:
                    val = row + 1;
                    break;
                case 1:
                    val = ((IWithNameAndPicture) obj.getObject()).getName();
                    break;
                case 2:
                    val = obj.getValue();
                    break;
                default:
            }
        } catch (RemoteException re) {
            re.printStackTrace();
        }
        return val;
    }

    @Override
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        JLabel obj = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (Tournament.getTournament().getParams().isUseImage()) {
            if (column == 1) {
                Team t = (Team) mObjects.get(row);
                if (t.getPicture() != null) {
                    ImageIcon icon = ImageTreatment.resize(t.getPicture(), 30, 30);
                    obj.setIcon(icon);
                }
            }
        }

        return obj;
    }
}
