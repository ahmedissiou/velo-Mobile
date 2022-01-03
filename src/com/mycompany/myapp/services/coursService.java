/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.cours;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author h^
 */
public class coursService {
     public ArrayList<cours> cours;
    
    public static coursService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    public coursService() {
         req = new ConnectionRequest();
    }
    
     public static coursService getInstance() {
        if (instance == null) {
            instance = new coursService();
        }
        return instance;
    }
     
     public cours getcours(cours l ,int id) {
           ConnectionRequest req = new ConnectionRequest();
      String url = "http://localhost/pifinal/pidevfinal/web/app_dev.php/api/voir/"+id;
      req.setUrl(url);
      req.setPost(false);
        req.addResponseListener((NetworkEvent evt) -> {
            byte[] data = (byte[]) evt.getMetaData();
            String s = new String(data);
           System.out.println(data);
          });
       NetworkManager.getInstance().addToQueue(req);
       return l;
    }
  
    
       public ArrayList<cours> parseEvents(String jsonText){
        try {
            cours=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            
           Map<String,Object> coursListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            
          List<Map<String,Object>> list = (List<Map<String,Object>>)coursListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                cours e = new cours();
                

         
                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int)id);
                   float niveau = Float.parseFloat(obj.get("niveau").toString());
                e.setNiveau(((int)Double.parseDouble(obj.get("niveau").toString())));
                   e.setPrixcours(((int)Double.parseDouble(obj.get("prixcours").toString())));
               e.setLieu_cours(obj.get("lieuCours").toString());
               e.setDate_cours(obj.get("dateCours").toString());
                e.setNom_image(obj.get("nomImage").toString());
               float signale = Float.parseFloat(obj.get("signale").toString());
               e.setSignale((int)signale);
              
               e.setNb(((int)Double.parseDouble(obj.get("nb").toString())));
                
           
               

                //Ajouter la tâche extraite de la réponse Json à la liste
                cours.add(e);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
       return cours;
    }
    
    public ArrayList<cours> getAllcours(){
        String url ="http://localhost/pifinal/pidevfinal/web/app_dev.php/api/affichecours";
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cours = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return cours;
    }
    String res = "";
    public void removecours(int id){
        ConnectionRequest con = new ConnectionRequest();
        
        con.setUrl("http://localhost/pifinal/pidevfinal/web/app_dev.php/api/delete/"+id);
        
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                res = new String(con.getResponseData());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
       public void signaler(int id){
        ConnectionRequest con = new ConnectionRequest();
        
        con.setUrl("http://localhost/pifinal/pidevfinal/web/app_dev.php/api/signaler/"+id);
        
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                res = new String(con.getResponseData());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
   
    
}