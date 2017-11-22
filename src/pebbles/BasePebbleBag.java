package pebbles;

import java.util.ArrayList;

/**
 * An implementation of PebbleBag specific to the bags that will be placed on the table.
 * 
 * @author tmlb201
 * @author lb552
 */
public class BasePebbleBag implements PebbleBag {
    
    final private ArrayList<Integer> PebbleBag;
    
    /**
     * Method initializes an empty BasePebbleBag
     * @param weights
     */
    public BasePebbleBag(ArrayList<Integer> weights) {
        PebbleBag = weights;
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
    public synchronized int size() {
        return PebbleBag.size();
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
            //return zero to indicate that bag is empty so we can do above?
            throw new IllegalStateException("Can't remove an item from an ampty array");         
        }       
        int rando = (int)((Math.random()*PebbleBag.size()));
        Integer i = PebbleBag.remove(rando);         
        
        return i;
    }

    
    /**
     * Method returns if the bag is empty.
     * <p>
     * This returns if the PebbleBag is empty
     * 
     * @return boolean
     */
    public boolean isEmpty() {
        return PebbleBag.isEmpty();
    }

    
    /**
     * Method returns if the bag valid for the current game.
     * <p>
     * This returns if the PebbleBag valid for the current game for the current number of players.
     * The PebbleBag must contain only positive pebble weights and the number of pebbles in the bag must be more than 11 times the number of players as per the specification
     * 
     * @param numPlayers the game must be valid for the current number of players in the game
     * @return boolean
     */
    public boolean isValid(int numPlayers) {
        int size = PebbleBag.size();
        if (size != numPlayers * 11) { //Bag size must be 11 times number of players on startup.
            return false;
        }
        //positive integers, comma separated, contains minimum 11*no of players
        //comma separated regex check done when read in 
        for (int i=0; i < size; i++) {   
            if(0 > i) {
                //contains minimum 11*no of players need to access command line input for no of players??
                return false;
            }        
        }

         return true;
    }
    
}
