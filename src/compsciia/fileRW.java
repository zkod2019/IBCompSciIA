/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compsciia;
import java.io.FileNotFoundException; //file handeling
import java.io.FileReader; 
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import org.json.simple.JSONArray; // external library 
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 *
 * @author zaida
 */
public class fileRW {
    
    String fileAddress = "players.json";
    
    
    public void save(List<Player> players){
        JSONArray playersArray = new JSONArray();
        Iterator<Player> iterator = players.iterator();
        while(iterator.hasNext()){
            playersArray.add(playerToJSON(iterator.next()));
        }
        try(FileWriter file = new FileWriter(fileAddress, false)){
            file.write(playersArray.toJSONString());
            file.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public List<Player> load(){
        List<Player> players = new ArrayList<>();
        JSONParser parser = new JSONParser(); //parser is used when loading JSON
        try{
           Object obj = parser.parse(new FileReader(fileAddress));
           JSONArray JSONPlayers = (JSONArray) obj;
           Iterator<JSONObject> iterator = JSONPlayers.iterator();
            while(iterator.hasNext()){
            players.add(jsonToPlayer(iterator.next()));
        }
        }
        catch(FileNotFoundException e){ //complex 4 : error catching. catching error that the file does not exist. So, we are going to create the file and recursively call the method again so it reads from the newly created file.
            try(FileWriter file = new FileWriter(fileAddress,false)){
                file.write("");
                file.flush();
                return load();
            }
            catch(IOException nope){
                nope.printStackTrace();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(ParseException e){
            e.printStackTrace();
        }
        
        return players;
    }
    private JSONObject playerToJSON(Player p){ //mapping player to json object
        JSONObject j = new JSONObject();
        j.put("lastname",p.lastname);
        j.put("name", p.name);
        j.put("pnumber", p.pnumber);
        j.put("goals", p.goals);
        j.put("currentgoal", p.currentgoal);
        j.put("games", p.games);
        return j;
    }
    
    private Player jsonToPlayer(JSONObject j){//mapping json object to player object
        String lastname = (String) j.get("lastname");
        String name = (String) j.get("name");
        String pnumber = (String) j.get("pnumber");
        int goals = Integer.parseInt(j.get("goals").toString());
        int currentgoal = Integer.parseInt(j.get("currentgoal").toString());
        int games = Integer.parseInt(j.get("games").toString());
        Player p = new Player(lastname, name, pnumber, goals, currentgoal, games);
        return p;
    }
   
}
