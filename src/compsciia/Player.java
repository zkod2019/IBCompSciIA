/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compsciia;

/**
 *
 * @author zaida
 */
public class Player{
    
    public String lastname = " ";
    public String name = " ";
    public String pnumber = " ";
    public int goals = 0;
    public int currentgoal = 0; 
    public int games = 0; 
    //instance variables
    
    public Player(String a, String b, String c, int g, int cg, int gm){
        lastname = a;
        name = b;
        pnumber = c;
        goals = g ;
        currentgoal = cg;
        games = gm;
    }

    public int compareTo(Player k){
        int pos=0;
        if (goals < k.goals){
            pos=-1;  
        }
        if (goals > k.goals){
            pos=1;
        }
        return pos;
        }
    }