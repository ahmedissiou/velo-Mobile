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
public class RechercheService {
        public ArrayList<cours> cours;

        cours evnt = new cours();

    public static RechercheService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    
    public RechercheService() {
        ConnectionRequest req = new ConnectionRequest();

        req = new ConnectionRequest();
    }

     public static RechercheService getInstance() {
        if (instance == null) {
            instance = new RechercheService();
        }
        return instance;
    }
   
    
    public ArrayList<cours> getAllcours(String c) {
                ConnectionRequest req = new ConnectionRequest();

          String url ="http://localhost/pifinal/pidevfinal/web/app_dev.php/api/find/"+c;
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cours = parseOneEvent(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return cours;
    }



public ArrayList<cours> parseOneEvent(String jsonText) {
        try { cours = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> eventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) eventsListJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
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
              
                e.setNb(((int)Double.parseDouble(obj.get("nb").toString())));
               cours.add(e);
            }

        } catch (IOException ex) {

        }
        return cours;
    }
    
}
