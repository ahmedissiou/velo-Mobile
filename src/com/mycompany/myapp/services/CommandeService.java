/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.entities.Session;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;





/**
 *
 * @author asus
 */
public class CommandeService {
    
    public ArrayList<Commande> Commande;
    public static CommandeService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public CommandeService() {
              req = new ConnectionRequest();
    }
    public static CommandeService getInstance() {
        if (instance == null) {
            instance = new CommandeService();
        }
        return instance;
 
    }
    
     public boolean addcmd(int ida, int idc) throws Exception
    {
       
        req.setUrl(Statics.BASE_URL+"/addcmd/"+ida+"/" +idc); 
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
               // System.out.println(req);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
    
    public ArrayList<Commande> parseCommande(String jsonText) throws Exception{
        try {
            Commande=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> ProduitListJson = j.parseJSON(new com.codename1.io.CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)ProduitListJson.get("root");
            for(Map<String,Object> obj : list){
                Commande C = new Commande();
                float id = Float.parseFloat(obj.get("id").toString());
                C.setId((int)id);
                C.setPrixtotal(Float.parseFloat(obj.get("prixtotal").toString()));
                C.setEtat((obj.get("etat").toString()));
                float addresse = Float.parseFloat(((Map) obj.get("addresse")).get("id").toString());
                C.setAddresse_id((int)addresse);

          Commande.add(C);
            }


        } catch (IOException ex) {

        }
        return Commande;
    }
    
  ///////////Les Cmd de utilisateur Connecter

    public ArrayList<Commande> getCommande(int id) throws Exception{
      
        String url = Statics.BASE_URL+"/getcmd/" + Session.getCurrentSession();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    Commande = parseCommande(new String(req.getResponseData()));
                } catch (Exception ex) {
                 
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return Commande;
    }
    
    
    
    public boolean Supprimer(int id) {
      // int id= F.getId();
       String url = Statics.BASE_URL+"/deletecmd/"+id;
    
     req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
         public boolean modcmd(int id) throws Exception{    
         String url = Statics.BASE_URL+"/modcmd/"+id;

       req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
      }
}
