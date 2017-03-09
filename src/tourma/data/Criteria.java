/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jdom2.DataConversionException;
import org.jdom2.Element;
import tourma.MainFrame;
import tourma.utility.StringConstants;

/**
 *
 * @author Administrateur
 */
public class Criteria implements IXMLExport, Serializable {

        protected static AtomicInteger sGenUID=new AtomicInteger(0);
    protected int UID=sGenUID.incrementAndGet();

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public void pull(Criteria crit)
    {
        this.UID=crit.UID;
        this.mCriticalValueThreshold=crit.mCriticalValueThreshold;
        this.mName=crit.mName;
        this.mPointsAgainst=crit.mPointsAgainst;
        this.mPointsFor=crit.mPointsFor;
        this.mPointsTeamAgainst=crit.mPointsTeamAgainst;
        this.mPointsTeamFor=crit.mPointsTeamFor;
    }
    
    protected static final Logger LOG = Logger.getLogger(Criteria.class.getName());

    /**
     * Name of the criteria
     */
    protected String mName;
    /**
     * Accronym of the criteria
     */
    protected String mAccronym;

    public String getAccronym() {
        if (mAccronym==null)
        {
            return mName;
        }
        if (mAccronym.equals(""))
        {
            return mName;
        }
        return mAccronym;
    }

    public void setAccronym(String accronym) {
        this.mAccronym = accronym;
    }
    /**
     * Points for
     */
    protected int mPointsFor;
    /**
     * Points against
     */
    protected int mPointsAgainst;
    /**
     * Team Points for
     */
    protected int mPointsTeamFor;
    /**
     * Team Points against
     */
    protected int mPointsTeamAgainst;
    /**
     * Threshold above which the value may be erroneous.
     */
    protected int mCriticalValueThreshold;

    /**
     *
     * @param name
     */
    public Criteria(final String name) {
        mName = name;
        mPointsFor = 0;
        mPointsAgainst = 0;
        mPointsTeamFor = 0;
        mPointsTeamAgainst = 0;
        mCriticalValueThreshold = 10;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(final Object obj) {

        boolean result;
        result = false;
        if (obj instanceof Criteria) {
            Criteria crit = (Criteria) obj;
            result = this.getName().equals(crit.getName());
            result &= this.getPointsAgainst() == crit.getPointsAgainst();
            result &= this.getPointsFor() == crit.getPointsFor();
            result &= this.getPointsTeamFor() == crit.getPointsTeamFor();
            result &= this.getPointsTeamAgainst() == crit.getPointsTeamAgainst();
        }
        return result;
    }

    public int getCriticalThreshold() {
        return mCriticalValueThreshold;
    }

    public void setCriticalThreshold(int value) {
        mCriticalValueThreshold = value;
    }

    /**
     *
     * @return
     */
    @Override
    public Element getXMLElement() {
        final Element crit = new Element(StringConstants.CS_CRITERIA);
        String key = StringConstants.CS_CRITERIA;
        crit.setAttribute(key, this.getName());
        key = StringConstants.CS_CRITERIA_ACCRONYM;
        crit.setAttribute(key, this.getAccronym());
        crit.setAttribute(StringConstants.CS_POINTS_FOR, Integer.toString(this.getPointsFor()));
        crit.setAttribute(StringConstants.CS_POINTS_AGAINST, Integer.toString(this.getPointsAgainst()));
        crit.setAttribute(StringConstants.CS_TEAM_POINTS_FOR, Integer.toString(this.getPointsTeamFor()));
        crit.setAttribute(StringConstants.CS_TEAM_POINTS_AGAINST, Integer.toString(this.getPointsTeamAgainst()));
        crit.setAttribute(StringConstants.CS_CRITICAL_THRESHOLD, Integer.toString(this.getCriticalThreshold()));
        return crit;
    }

    /**
     *
     * @param criteria
     */
    @Override
    public void setXMLElement(final Element criteria) {
        try {
            this.setName(criteria.getAttributeValue(StringConstants.CS_CRITERIA));
            if (getName() == null) {
                this.setName(criteria.getAttributeValue(StringConstants.CS_NAME));
            }
            this.setPointsFor(criteria.getAttribute(StringConstants.CS_POINTS_FOR).getIntValue());
            this.setPointsTeamFor(criteria.getAttribute(StringConstants.CS_TEAM_POINTS_FOR).getIntValue());
            this.setPointsAgainst(criteria.getAttribute(StringConstants.CS_POINTS_AGAINST).getIntValue());
            this.setPointsTeamAgainst(criteria.getAttribute(StringConstants.CS_TEAM_POINTS_AGAINST).getIntValue());
            try {
                this.setCriticalThreshold(criteria.getAttribute(StringConstants.CS_CRITICAL_THRESHOLD).getIntValue());
                
            } catch (NullPointerException npe) {
                this.setCriticalThreshold(10);
            }
             try {
                this.setAccronym(criteria.getAttributeValue(StringConstants.CS_CRITERIA_ACCRONYM));
            } catch (NullPointerException npe) {
                this.setCriticalThreshold(10);
            }
        } catch (DataConversionException dce) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), dce.getLocalizedMessage());
        }
    }

    /**
     * @return the mName
     */
    public String getName() {
        return mName;
    }

    /**
     * @param mName the mName to set
     */
    public void setName(String mName) {
        this.mName = mName;
    }

    /**
     * @return the mPointsFor
     */
    public int getPointsFor() {
        return mPointsFor;
    }

    /**
     * @param mPointsFor the mPointsFor to set
     */
    public void setPointsFor(int mPointsFor) {
        this.mPointsFor = mPointsFor;
    }

    /**
     * @return the mPointsAgainst
     */
    public int getPointsAgainst() {
        return mPointsAgainst;
    }

    /**
     * @param mPointsAgainst the mPointsAgainst to set
     */
    public void setPointsAgainst(int mPointsAgainst) {
        this.mPointsAgainst = mPointsAgainst;
    }

    /**
     * @return the mPointsTeamFor
     */
    public int getPointsTeamFor() {
        return mPointsTeamFor;
    }

    /**
     * @param mPointsTeamFor the mPointsTeamFor to set
     */
    public void setPointsTeamFor(int mPointsTeamFor) {
        this.mPointsTeamFor = mPointsTeamFor;
    }

    /**
     * @return the mPointsTeamAgainst
     */
    public int getPointsTeamAgainst() {
        return mPointsTeamAgainst;
    }

    /**
     * @param mPointsTeamAgainst the mPointsTeamAgainst to set
     */
    public void setPointsTeamAgainst(int mPointsTeamAgainst) {
        this.mPointsTeamAgainst = mPointsTeamAgainst;
    }
}
