/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pebbles;

import java.util.Scanner;  
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

/**
 *
 * @author laura
 */
public class Pebbles {
    static int numBags = 6;

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    
    public static void main(String[] args) throws IOException {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter number of players:");  
        int players = sc.nextInt();
        
        BasePebbleBag[] table;
        
        table = new BasePebbleBag[numBags];
        ArrayList<ArrayList<Integer>> pebbleWeightsArray;
        pebbleWeightsArray = new ArrayList<ArrayList<Integer>>(numBags);
        for (int x = 0; x < numBags; x++){
             try{
                if(x < 3){
                System.out.println("Enter file location of bag "+x+" to load:"); 
                String txt = new String(Files.readAllBytes(Paths.get(sc.next())));
                pebbleWeightsArray.add(x, parseInput(txt)); // pebble lists 0,1,2
                System.out.println(pebbleWeightsArray);
                }
                else{
                    
                }
                //give empty array list 
            } catch (NumberFormatException e) {
                System.out.println("File presented was in an invalid format. Please provide file in a valid format (integers separated by commas)");
                System.exit(1);
            }
            table[x] = new BasePebbleBag(pebbleWeightsArray.get(x));
        }
        
        //timer or turn counter that will stop the game after certain amount of time or turns assuming that no one can win
        String txt = new String(Files.readAllBytes(Paths.get(sc.next)));
        int numPlayers = 3;
        BasePlayer[] players;
        CyclicBarrier barrier = new CyclicBarrier(numPlayers);
        players = new BasePlayer[numPlayers];
        for (int x = 0; x < numPlayers; x++){
            players[x] = new BasePlayer(x, table, barrier);
        }
        
        //create a player instance for each player
        //set game running
        return;
    }
    
    public static ArrayList<Integer> parseInput(String input) throws NumberFormatException{
        String[] split = input.split(",");
        ArrayList<Integer> bag = new ArrayList<Integer>();
        for (int x = 0; x < split.length; x++){
            bag.add(Integer.parseInt(split[x]));
           
        }
        return bag;
    }
    
}
