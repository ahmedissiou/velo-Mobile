/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.mycompany.myapp.entities.*;
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
public class PanierService {
    
    ArrayList<Panier> listEvents;
    public static PanierService instance=null;
    public boolean resultOK;
    
    private ConnectionRequest req;
    

    public PanierService() {

        req = new ConnectionRequest();
    }
    public static PanierService getInstance() {
        if (instance == null) {
            instance = new PanierService();
        }
        return instance;
    }
        
        
        
    public boolean addppp(int idp, int idc) throws Exception  {
        
      String url = Statics.BASE_URL + "/addppp/" + idp+"/" +idc;
         //String url = Statics.BASE_URL + "/addppp/" + p.getProduit_id()+"/" +Session.getCurrentSession(); //création de l'URL
    //    String url = Statics.BASE_URL+"/Apilignecommande/new?idProduit=" + p.getId_Produit() + "&user="+Statics.CurrentUser.getId();


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
    
    
    
    
        /*
      // int id = 0;
     //  ConnectionRequest con = new ConnectionRequest();
        req.setUrl(Statics.BASE_URL+"/addppp/" +p.getProduit_id() +"where client=" + Session.getCurrentSession()); 
        //req.setPost();
        
        req.setHttpMethod("POST");
        //req.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        req.addArgument("id", ""+p.getId());
        req.addArgument("produit", ""+p.getProduit_id());
        req.addArgument("quantite", ""+p.getQuantite());
        req.addArgument("client", ""+p.getClient_id());
   
        NetworkManager.getInstance().addToQueueAndWait(req);
    }*/



     
    
     public ArrayList<Panier> parsePanier(String jsonText) throws Exception{
        try {
            listEvents =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> ProduitListJson = j.parseJSON(new com.codename1.io.CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)ProduitListJson.get("root");
            for(Map<String,Object> obj : list){
                Panier C = new Panier();
                float id = Float.parseFloat(obj.get("id").toString());
                C.setId((int)id);
                float quantite = Float.parseFloat(obj.get("quantite").toString());
                C.setQuantite((int)quantite);
               
                float produit = Float.parseFloat(((Map) obj.get("produit")).get("id").toString());
                C.setProduit_id((int)produit);
                
              
                


          listEvents.add(C);
           //System.out.println(listEvents);
          
            }
        } catch (IOException ex) {

        }


        return listEvents;
    }
     
      public ArrayList<Panier> getpppuser(int id) throws Exception{    
         String url = Statics.BASE_URL+"/getppp/" + id;

        req.setUrl(url);
       
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    listEvents = parsePanier(new String(req.getResponseData()));
                            

                } catch (Exception ex) {
                 
                }
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
      
        return listEvents;
      }
      
      public boolean Supprimer(Panier p)  {
          int id= p.getId(); 
       String url = Statics.BASE_URL+"/deleteppp/"+id;
    

        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; // Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOK;
    }
      
        public boolean modppp(int idprd, int idp, int mod) throws Exception{    
         String url = Statics.BASE_URL+"/modppp/" + idprd  +"/" +idp+"/"+mod ;

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
    
