/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

/**
 *
 * @author WFMJ7631
 */
public class GroupPoints  {
    
    private int victoryPoints=0;
    private int drawPoints=0;
    private int lossPoints=0;

     /**
     * 
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(final Object obj) {
        
        boolean result;
        result = false;
        if (obj instanceof GroupPoints) {
            GroupPoints gp=(GroupPoints) obj;
            result=lossPoints==gp.getLossPoints();
            result &=this.victoryPoints==gp.getVictoryPoints();
            result &=this.drawPoints==gp.getDrawPoints();
        } 
        return result;
    }
    
    /**
     * @return the victoryPoints
     */
    public int getVictoryPoints() {
        return victoryPoints;
    }

    /**
     * @param victoryPoints the victoryPoints to set
     */
    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    /**
     * @return the drawPoints
     */
    public int getDrawPoints() {
        return drawPoints;
    }

    /**
     * @param drawPoints the drawPoints to set
     */
    public void setDrawPoints(int drawPoints) {
        this.drawPoints = drawPoints;
    }

    /**
     * @return the lossPoints
     */
    public int getLossPoints() {
        return lossPoints;
    }

    /**
     * @param lossPoints the lossPoints to set
     */
    public void setLossPoints(int lossPoints) {
        this.lossPoints = lossPoints;
    }
    
    
    
}
