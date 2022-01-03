/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import static com.codename1.io.Log.p;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.reparation;
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
public class ServiceRecherche {
        public ArrayList<reparation> reparation;

        reparation evnt = new reparation();

    public static ServiceRecherche instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    
    public ServiceRecherche() {
        ConnectionRequest req = new ConnectionRequest();

        req = new ConnectionRequest();
    }

     public static ServiceRecherche getInstance() {
        if (instance == null) {
            instance = new ServiceRecherche();
        }
        return instance;
    }
   
    
    public ArrayList<reparation> getAllreparation(String p) {
                ConnectionRequest req = new ConnectionRequest();

          String url ="http://localhost/pifinal/pidevfinal/web/app_dev.php/api/search/"+p;
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reparation = parseOneEvent(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reparation;
    }



public ArrayList<reparation> parseOneEvent(String jsonText) {
        try { reparation = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> eventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) eventsListJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                reparation e = new reparation();
              
               
                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int)id);
                
                String dd = (obj.get("dateRep").toString());
                
                String dt = dd.substring(0, 10);
                
                e.setId((int)id);  
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                Date d = new Date();

                try {

                    d = formatter.parse(dt);

                } catch (ParseException exp) {
                }

                System.out.println(formatter.format(d));
                e.setDescriptionprob(obj.get("descriptionprob").toString());
                e.setType(obj.get("type").toString());
                e.setMarque(obj.get("marque").toString());
                e.setVille(obj.get("ville").toString());
              
                
               reparation.add(e);
            }

        } catch (IOException ex) {

        }
        return reparation;
    }
    
}
