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
import com.mycompany.myapp.entities.Event;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Manel
 */
public class RechercheEventService {
     public ArrayList<Event>event;

        Event evnt = new Event();

    public static RechercheEventService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    
    public RechercheEventService() {
        ConnectionRequest req = new ConnectionRequest();

        req = new ConnectionRequest();
    }

     public static RechercheEventService getInstance() {
        if (instance == null) {
            instance = new RechercheEventService();
        }
        return instance;
    }
   
    
    public ArrayList<Event> getAlleventsRecherche(String p) {
              ConnectionRequest req = new ConnectionRequest();
                      
               String url ="http://localhost/pifinal/pidevfinal/web/app_dev.php/api/rechercheEvent/"+p;
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                 event = parseOneEvent(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return event;
    }



public ArrayList<Event> parseOneEvent(String jsonText) {
        try { event = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> eventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) eventsListJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Event e = new Event();
              
               
                   float id = Float.parseFloat(obj.get("id").toString());
                   e.setId((int)id);
                    e.setNomevet(obj.get("nomevet").toString());
                  
                    //e.setLieuevt(obj.get("lieuevent").toString());
                    e.setDescription(obj.get("description").toString());  
                   float nbparticipants = Float.parseFloat(obj.get("nbparticipent").toString());
                   e.setNbparticipent(((int)Double.parseDouble(obj.get("nbparticipent").toString())));
//                    e.setPrixe(((int)Double.parseDouble(obj.get("prix").toString())));
                    e.setNom_image(obj.get("nomImage").toString());
                    
//                     Map<String, Object> data1 = (Map<String, Object>) (obj.get("dateDebe"));
//                       int temp = (int) Float.parseFloat(data1.get("timestamp").toString());
//                        Date myDate = new Date(temp * 1000L);
//                        e.setDate_debe(myDate);
//                        
//                 Map<String, Object> data2 = (Map<String, Object>) (obj.get("dateFine"));
//                       int temp1 = (int) Float.parseFloat(data2.get("timestamp").toString());
//                        Date myDate1 = new Date(temp1 * 1000L);
//                        e.setDate_fine(myDate1);
               event.add(e);
            }

        } catch (IOException ex) {

        }
        return event;
    }
    
    
}
