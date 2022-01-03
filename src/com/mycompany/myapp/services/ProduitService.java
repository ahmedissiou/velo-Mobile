/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.mycompany.myapp.utils.Statics;
import com.mycompany.myapp.entities.Produit;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.codename1.io.CharArrayReader;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;







/**
 *
 * @author asus
 */

public class ProduitService {
    
     public ArrayList<Produit> Produit;
    
    public static ProduitService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    public ProduitService() {
         req = new ConnectionRequest();
    }
    
     public static ProduitService getInstance() {
        if (instance == null) {
            instance = new ProduitService();
        }
        return instance;
    }
     
     
  
    
       public ArrayList<Produit> parseEvents(String jsonText) throws Exception{
        try {
            Produit=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            
           Map<String,Object> coursListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            
          List<Map<String,Object>> list = (List<Map<String,Object>>)coursListJson.get("root");
            
            //Parcourir la liste des tâches Jsons
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Produit e = new Produit();
                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int)id);
                   
                e.setQuantite(((int)Double.parseDouble(obj.get("quantite").toString())));
                e.setPrix(((int)Double.parseDouble(obj.get("prix").toString())));
               e.setNom(obj.get("nom").toString());
                //e.setCategorie_id(((int)Double.parseDouble(obj.get("categorie").toString())));
                e.setImage(obj.get("image").toString());
              
             
             
           

                //Ajouter la tâche extraite de la réponse Json à la liste
                Produit.add(e);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
       return Produit;
    }
    
    public ArrayList<Produit> getproduit(){
       String url = Statics.BASE_URL+"/list";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                try {
                    Produit = parseEvents(new String(req.getResponseData()));
                } catch (Exception ex) {
                   
                }
              
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Produit;
    }
    

         
       
    
/*
    public ArrayList<Produit> Produit;
    public static ProduitService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;


    public ProduitService() {
        req = new ConnectionRequest();
    }
    public static ProduitService getInstance() {
        if (instance == null) {
            instance = new ProduitService();
        }
        return instance;
    }


    public ArrayList<Produit> parseProduit(String jsonText) throws Exception{
        try {
            Produit=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> ProduitListJson = j.parseJSON(new com.codename1.io.CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)ProduitListJson.get("root");
            for(Map<String,Object> obj : list){
                Produit C = new Produit();
                float id = Float.parseFloat(obj.get("id").toString());
                C.setId((int)id);
                C.setNom((obj.get("nom").toString()));
                C.setCategorie_id(Integer.parseInt(obj.get("categorie").toString()));
                C.setPrix(Float.parseFloat(obj.get("prix").toString()));
                C.setImage(obj.get("webPath").toString());
               
                
   
          Produit.add(C);
            }


        } catch (IOException ex) {

        }
        return Produit;
    }

    
    
        public ArrayList<Produit> getproduit(){
        ConnectionRequest con = new ConnectionRequest();
        
        con.setUrl("http://localhost/web/pifinal1/pidevfinal/web/app_dev.php/list");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    Produit = parseProduit(new String(con.getResponseData()));
                } catch (Exception ex) {
                   
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return Produit;
        }*/
  
}
