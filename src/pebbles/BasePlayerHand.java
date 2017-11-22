package pebbles;

import java.util.ArrayList;

/**
 * An implementation of PebbleBag specific to the player's hand.
 * 
 * @author tmlb201
 * @author lb552
 */
public class BasePlayerHand implements PebbleBag {
    
    final private ArrayList<Integer> PebbleBag;
    
    /**
     * Method initializes an empty BasePlayerHand
     */
    
    public BasePlayerHand() {
        PebbleBag = new ArrayList();    
    }

/**
     * {@inheritDoc}
     */
    @Override
    public synchronized int size() {
        return PebbleBag.size();
    }

    
/**
     * {@inheritDoc}
     */
    @Override
    public synchronized String toStr() {
        return PebbleBag.toString();
    }    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void put(Integer i) {
        PebbleBag.add(i);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized Integer remove() {
        if(PebbleBag.isEmpty()){  
            //need to add from white bag to refil not throw exception??
            //return zero to indicate that bag is empty so we can do above
            throw new IllegalStateException("Can't remove an item from an empty array");         
        }       
        int rando = (int)((Math.random()*PebbleBag.size()));
        Integer i = PebbleBag.remove(rando);         
        
        return i;
    }

    
    /**
     * Method returns a if the bag's pebbles total 100.
     * <p>
     * This method returns a if the bag's pebbles total 100.
     * 
     * @return boolean
     */
    public boolean totals100() {
        int total = 0;
        for (int i=0; i < PebbleBag.size(); i++) { 
            total += (int)(PebbleBag.get(i)); 
        }      
        return total == 100;
    }

}
