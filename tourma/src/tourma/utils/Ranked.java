/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils;

import tourma.data.ObjectRanking;

/**
 *
 * @author WFMJ7631
 */
public interface Ranked {
    public int getRowCount();
    public ObjectRanking getSortedObject(int i);
    public int getSortedValue(int i,int valIndex);
}
