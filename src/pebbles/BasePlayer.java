package pebbles;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * An implementation of Player that can play the game described in the 
 * specification. 
 * 
 * @author tmlb201
 * @author lb552
 */
public class BasePlayer implements Player {
    final private int id;
    final private BasePlayerHand hand;
    final private BasePebbleBag[] table;
    
    
    final private CyclicBarrier startingGate;
            
    /**
     * Method initializes a BasePlayer.
     * <p>
     * A BasePlayer needs a hand, access to the PebbleBags on the table and a starting
     * condition to allow a fair start.
     * 
     * @param id the unique identifier of a player
     * @param table the list of PebbleBags that represent those on the table
     * @param startingGate a gate to allow all players to start at once
     */
    public BasePlayer(int id, BasePebbleBag[] table, CyclicBarrier startingGate) {
        this.id = id;
        hand = new BasePlayerHand();
        this.table = table;
        this.startingGate = startingGate;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void addPebble(Integer i) {
        hand.put(i);
        checkDoneCondition();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkDoneCondition() {
        if(hand.totals100()) {
                //launch inturupt
                System.out.println("Player "+id+" wins");
                return true;
        }
        return false;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        try {
            startingGate.await();
            //Where each players loop runs
            //Got 9 pebbles
            //Draw pebble
            //check if 100
            //discard pebble
            //if 100 -> win and inturupt other threads.
            int chosenBag;
            chosenBag = chooseBag();
            while(hand.size() < 10) {
                hand.put(table[chosenBag].remove());
                System.out.println("hand: "+hand.toStr());
                System.out.println("chosenBag: "+table[chosenBag].toStr());
            }
            if(!checkDoneCondition()){
                table[chosenBag + 3].put(hand.remove());
            }
        }  catch (InterruptedException | BrokenBarrierException e) {
            return;
        }
        
        //gets done if interrupted, eg gracefully shut down game
         checkDoneCondition();
    }
    @Override
    public int chooseBag() {
        Random rand = new Random();
        int  n = rand.nextInt(2) + 1;
        return n;
    }
}

