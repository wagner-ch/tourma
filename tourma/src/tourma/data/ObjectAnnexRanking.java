/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import org.jdom.Attribute;
import org.jdom.Element;

/**
 *
 * @author Frederic Berger
 */
public class ObjectAnnexRanking extends ObjectRanking {

    public int mValue;

    public int getValue() {
        return mValue;
    }

    public ObjectAnnexRanking(final Comparable c, final int value,final  int value1, final int value2,final  int value3,final  int value4, final int value5) {
        super(c, value1, value2, value3, value4, value5);
        mValue = value;
    }

    @Override
    public int compareTo(final Object o) {
        int result = -65535;
        if (o instanceof ObjectAnnexRanking) {
            if (((ObjectAnnexRanking) o).mValue == mValue) {
                result = super.compareTo(o);
            } else {
                result = ((ObjectAnnexRanking) o).mValue - mValue;
            }
        }
        return result;
    }

    @Override
    public Element getXMLElement() {
        final Element ic = super.getXMLElement();

        ic.setAttribute(new Attribute("value", Integer.toString(mValue)));

        ic.removeAttribute("rank1");
        ic.removeAttribute("rank2");
        ic.removeAttribute("rank3");
        ic.removeAttribute("rank4");
        ic.removeAttribute("rank5");
        return ic;
    }

    @Override
    public void setXMLElement(final Element e) {
        super.setXMLElement(e);
    }
}
