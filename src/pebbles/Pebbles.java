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
import java.util.Arrays;
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
        Scanner sc = new Scanner(System.in); //makes new scanner to read command lin input
        System.out.println("Enter number of players:");  
        int numPlayers = sc.nextInt();
        
        BasePebbleBag[] table; //initialises the BasePebbleBags that will be on the table
        table = new BasePebbleBag[numBags];
        
        
        ArrayList<ArrayList<Integer>> pebbleWeightsArray; 
        pebbleWeightsArray = new ArrayList<>(numBags);//initialises the array of arrays of integers that will be the individual pebble weights
        
        for (int x = 0; x < numBags; x++){
             try{
                if(x < 3){
                    System.out.println("Enter file location of bag "+ x +" to load:"); 
                    String txt = new String(Files.readAllBytes(Paths.get(sc.next()))); //get the next thing on the command line, read it and make it into a string
                    pebbleWeightsArray.add(x, parseInput(txt)); // change the string into integers and add to the pebbleWeightsArray
                    table[x] = new BasePebbleBag(pebbleWeightsArray.get(x));
                    System.out.println("pebbleWeightsArray" + pebbleWeightsArray); // testing line
                }
                else {
                    table[x] = new BasePebbleBag(new ArrayList<Integer>());
                }
                // rest of bags on table have no contents as they are the discard bags
                //System.out.println("pebbleWeightsArray" + pebbleWeightsArray);
                    
                
                //give empty array list 
            } catch (NumberFormatException e) {
                System.out.println("File presented was in an invalid format. Please provide file in a valid format (integers separated by commas)");
                System.exit(1);
            }

        }
/* Test table contents
        for (int x = 0; x < numBags; x++){
            System.out.println("Table contents: "+table[x].toStr());
        }
**/
        //timer or turn counter that will stop the game after certain amount of time or turns assuming that no one can win
        BasePlayer[] players;
        CyclicBarrier barrier = new CyclicBarrier(numPlayers);
        players = new BasePlayer[numPlayers];
        for (int x = 0; x < numPlayers; x++){
            players[x] = new BasePlayer(x, table, barrier);
        }
        
        players[0].run();
    }
    
    public static ArrayList<Integer> parseInput(String input) throws NumberFormatException{
        String[] split = input.split(",");
        ArrayList<Integer> bag = new ArrayList<>();
        for (int x = 0; x < split.length; x++){
            bag.add(Integer.parseInt(split[x]));
           
        }
        return bag;
    }
    
}
