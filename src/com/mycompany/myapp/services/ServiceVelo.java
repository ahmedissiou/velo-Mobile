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
import com.mycompany.myapp.entities.velo;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hajer
 */
public class ServiceVelo {
    public ArrayList<velo> velos;
    velo v = new velo();
    
    public static ServiceVelo instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceVelo() {
         req = new ConnectionRequest();
    }

    public static ServiceVelo getInstance() {
        if (instance == null) {
            instance = new ServiceVelo();
        }
        return instance;
    }
     public ArrayList<velo> parseTasks(String jsonText){
        try {
            velos=new ArrayList<>();
            JSONParser j = new JSONParser();
           
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                velo v = new velo();
                float id = Float.parseFloat(obj.get("id").toString());
                v.setId((int)id);
                v.setPrix((int)Float.parseFloat(obj.get("prix").toString()));
                 v.setAge((int)Float.parseFloat(obj.get("age").toString()));
                v.setMarque(obj.get("marque").toString());
                v.setNomvelo(obj.get("nomvelo").toString());
                v.setNomImage(obj.get("nomImage").toString());
                v.setEtat((int)Float.parseFloat(obj.get("etat").toString()));
               // v.setImage(obj.get("image").toString());
                //Ajouter la tâche extraite de la réponse Json à la liste
                velos.add(v);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return velos;
    }
    
    public ArrayList<velo> getAllvelos(){
        String url = Statics.BASE_URL+"/velos/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                velos = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return velos;
    }
    
      public velo getEvent2(int id) {

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pifinal/pidevfinal/web/app_dev.php/velo/find/" + id);
         
        
        
        con.setPost(false);
        con.setHttpMethod("GET");

        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    ServiceVelo es = new ServiceVelo();
                    v = es.getEvent(new String(con.getResponseData()));
                } catch (IOException ex) {
                    
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return v;
    }
      public velo getEvent(String jsonText) throws IOException {
    
       velo v = new velo();

        try {
            System.out.println(jsonText);
             velos = new ArrayList<>();
            JSONParser j = new JSONParser();
           
            Map<String, Object> evt = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(evt);

            //<Map<String,Object>> list = (List<Map<String,Object>>)evt.get("root");
//System.out.println(list);
            // (Map<String, Object> obj : v) {

               float id = Float.parseFloat(evt.get("id").toString());
                v.setId((int)id);
                v.setPrix((int)Float.parseFloat(evt.get("prix").toString()));
                 v.setAge((int)Float.parseFloat(evt.get("age").toString()));
                v.setMarque(evt.get("marque").toString());
                v.setNomvelo(evt.get("nomvelo").toString());
                v.setNomImage(evt.get("nomImage").toString());
                v.setEtat((int)Float.parseFloat(evt.get("etat").toString()));

               velos.add(v);
            
       

        } catch (IOException ex) {
        }

        return v;

      }
      public ArrayList<velo> getdispovelos(){
        String url = Statics.BASE_URL+"/velos/dispo";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                velos = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return velos;
    }
      
}


    
    

