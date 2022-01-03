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
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.reclamation;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hajer
 */
public class ServiceReclamation {
    public ArrayList<reclamation> reclamations;
    
    public static ServiceReclamation  instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceReclamation () {
         req = new ConnectionRequest();
    }

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation ();
        }
        return instance;
    }
     public ArrayList<reclamation> parseTasks(String jsonText){
        try {
            reclamations=new ArrayList<>();
            JSONParser j = new JSONParser();
           
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(tasksListJson);
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
              
                reclamation r = new reclamation();
                float id = Float.parseFloat(obj.get("id").toString());
                r.setId((int)id);
                r.setProbleme(obj.get("probleme").toString());
                
                r.setTitrereclam(obj.get("titrereclam").toString());
                float user_id= Float.parseFloat(obj.get("user_id").toString());
                r.setUser_id((int)user_id);
                
                //Ajouter la tâche extraite de la réponse Json à la liste
                reclamations.add(r);
            }
            
            
        } catch (IOException ex) {
            
        }
        
        return reclamations;
    }
    
    public ArrayList<reclamation> getAllvelos(){
        String url = Statics.BASE_URL+"/reclamation/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reclamations = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reclamations;
    }
     public boolean addTask(reclamation r) {
        String url = Statics.BASE_URL + "/reclamation/new?titrereclam=" +r.getTitrereclam()+ "&probleme=" + r.getProbleme(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
}

    
    

 

