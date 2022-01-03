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
public class EventService {
    
     public ArrayList<Event> Event;
    public static EventService instance=null;
    public boolean resultOK;

    private ConnectionRequest req;
    
    public EventService() {
         req = new ConnectionRequest();
    }
    
     public static EventService getInstance() {
        if (instance == null) {
            instance = new EventService();
        }
        return instance;
    }
     
     
  
    
       public ArrayList<Event> parseEvents(String jsonText){
        try {
            Event=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            
           Map<String,Object> coursListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            
          List<Map<String,Object>> list = (List<Map<String,Object>>)coursListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Event e  = new Event();
                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int)id);
                e.setNomevet(obj.get("nomevet").toString());
//                e.setDatedebe(obj.get("datedebut").toString());
//                e.setDatefine(obj.get("datefin").toString());
//                e.setLieuevet(obj.get("lieuevent").toString());
//                e.Description(obj.get("desription").toString());  
//                float nbparticipants = Float.parseFloat(obj.get("nbparticipant").toString());
//                e.setNbparticipants (((int)Double.parseDouble(obj.get("nbparticipant").toString())));
//                e.setPrixcours(((int)Double.parseDouble(obj.get("prixcours").toString())));
                  e.setIsActive(Boolean.parseBoolean(obj.get("active").toString()));
                  e.setNom_image(obj.get("nomImage").toString());
            
                //Ajouter la tâche extraite de la réponse Json à la liste
                Event.add(e);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
       return Event;
    }
    
    public ArrayList<Event> getAllEvent(){
        String url ="http://localhost/pifinal/pidevfinal/web/app_dev.php/api/afficherAll";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Event = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Event;
    }
    
     public void ajoutEvent(Event event) {
        String Url = "http://localhost/pifinal/pidevfinal/web/app_dev.php/api/AddEvent";
        req.setPost(true);
        req.setUrl(Url);
         req.addArgument("nomevent", event.getNomevet());
       
        req.addArgument("nomimage", event.getNom_image());
         System.err.println(event.getNom_image());
        req.addArgument("lieu", event.getLieuevt());
        req.addArgument("description", event.getDescription());
        req.addArgument("creator_id", Integer.toString(event.getCreator_id()));
        req.addArgument("nb_signal", Integer.toString(event.getNbsignal()));
        req.addArgument("isActive", String.valueOf(event.getIsActive()));
        
        req.addArgument("nbparticipents", Integer.toString(event.getNbparticipent()));
        req.addArgument("prix", Integer.toString(event.getPrixe())); 
         SimpleDateFormat    formatter = new SimpleDateFormat("dd-M-yyyy");  
          String   datedebe = formatter.format(event.getDate_debe()); 
        req.addArgument("datebede", datedebe); 
          String   datefin = formatter.format(event.getDate_fine()); 
        req.addArgument("datefine", datefin); 
                req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
     public Event afficherevent(int id ){
         Event e = new Event(); 
        String url ="http://localhost/pifinal/pidevfinal/web/app_dev.php/api/afficherevent/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
       
         String json = new String(req.getResponseData());
          System.out.println(json);
                 try {
          JSONParser j = new JSONParser();
         
            Map<String, Object>  events = j.parseJSON(new CharArrayReader(json.toCharArray()));
            
                float id = Float.parseFloat(events.get("id").toString());
               e.setId((int) id);
              
                e.setNomevet(events.get("nomevet").toString());
                e.setLieuevt(events.get("lieuevt").toString());
                e.setDescription(events.get("description").toString());
                
                float Nbparticipents = Float.parseFloat(events.get("nbparticipent").toString());
                e.setNbparticipent((int) Nbparticipents);
                 float prixe = Float.parseFloat(events.get("prixe").toString());
                e.setPrixe((int) prixe);
               
                e.setNom_image(events.get("nomImage").toString());
                
                 Map<String, Object> data1 = (Map<String, Object>) (events.get("dateDebe"));
                       int temp = (int) Float.parseFloat(data1.get("timestamp").toString());
                        Date myDate = new Date(temp * 1000L);
                        e.setDate_debe(myDate);
                        
                 Map<String, Object> data2 = (Map<String, Object>) (events.get("dateFine"));
                       int temp1 = (int) Float.parseFloat(data2.get("timestamp").toString());
                        Date myDate1 = new Date(temp1 * 1000L);
                        e.setDate_fine(myDate1);
                Map<String, Object> data3 = (Map<String, Object>) (events.get("creator"));
                                 float creatorId = Float.parseFloat(data3.get("id").toString());

                e.setCreator_id((int) creatorId);

                   
                 }
              catch (IOException ex) {
        }
                 }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return e;
    }
    
    
     
     public void modifier (Event event ) {
     
     
     String Url = "http://localhost/pifinal/pidevfinal/web/app_dev.php/api/modifierevent/"+event.getId();
        req.setPost(true);
        req.setUrl(Url);
        req.addArgument("nomevent", event.getNomevet());
        req.addArgument("nomimage", event.getNom_image());
         System.err.println(event.getNom_image());
        req.addArgument("lieu", event.getLieuevt());
        req.addArgument("description", event.getDescription());
        req.addArgument("creator_id", Integer.toString(event.getCreator_id()));
        req.addArgument("nb_signal", Integer.toString(event.getNbsignal()));
        req.addArgument("isActive", String.valueOf(event.getIsActive()));
        
        req.addArgument("nbparticipents", Integer.toString(event.getNbparticipent()));
        req.addArgument("prix", Integer.toString(event.getPrixe())); 
         SimpleDateFormat    formatter = new SimpleDateFormat("dd-M-yyyy");  
          String   datedebe = formatter.format(event.getDate_debe()); 
        req.addArgument("datebede", datedebe); 
          String   datefin = formatter.format(event.getDate_fine()); 
        req.addArgument("datefine", datefin); 
                req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
     }

     
     
     public void supprimer(int id) {
             ConnectionRequest con = new ConnectionRequest();

        String Url ="http://localhost/pifinal/pidevfinal/web/app_dev.php/api/deleteEvent/"+id;
  
        con.setUrl(Url);
     
        NetworkManager.getInstance().addToQueueAndWait(con);
    }  
     
          
     public void participer(int eventId,int userId) {
        ConnectionRequest con = new ConnectionRequest();

        String Url ="http://localhost/pifinal/pidevfinal/web/app_dev.php/api/participerevent";
        con.addArgument("eventId",Integer.toString( eventId));
        con.addArgument("userId",Integer.toString( userId));
        con.setUrl(Url);
     
        NetworkManager.getInstance().addToQueueAndWait(con);
    }  
     
     public void annuler(int eventId,int userId) {
        ConnectionRequest con = new ConnectionRequest();

        String Url ="http://localhost/pifinal/pidevfinal/web/app_dev.php/api/annulerparticipation";
        con.addArgument("eventId",Integer.toString( eventId));
        con.addArgument("userId",Integer.toString( userId));
        con.setUrl(Url);
     
        NetworkManager.getInstance().addToQueueAndWait(con);
    } 
     
     
      
     public void SignalerEvent(int eventId,int userId) {
        ConnectionRequest con = new ConnectionRequest();

        String Url ="http://localhost/pifinal/pidevfinal/web/app_dev.php/api/signalerevent";
        con.addArgument("eventId",Integer.toString( eventId));
        con.addArgument("userId",Integer.toString( userId));
        con.setUrl(Url);
     
        NetworkManager.getInstance().addToQueueAndWait(con);
    } 
                 String str1 ; 

     public boolean testSignaler(int userId,int eventId) {
        ConnectionRequest con = new ConnectionRequest();
                con.setPost(false);

        String Url ="http://localhost/pifinal/pidevfinal/web/app_dev.php/api/testsignaler";
        con.addArgument("eventId",Integer.toString( eventId));
        con.addArgument("userId",Integer.toString( userId));
        con.setUrl(Url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
              @Override
            public void actionPerformed(NetworkEvent evt) {
           String str = new String(con.getResponseData());
        str1 = str;
        }
            
        });

        NetworkManager.getInstance().addToQueueAndWait(con);

                 return Boolean.parseBoolean(str1);

    }
                      String str2 ; 

     public boolean testParticiper(int userId,int eventId) {
        ConnectionRequest con = new ConnectionRequest();
                con.setPost(false);

        String Url ="http://localhost/pifinal/pidevfinal/web/app_dev.php/api/testparticiper";
        con.addArgument("eventId",Integer.toString( eventId));
        con.addArgument("userId",Integer.toString( userId));
        con.setUrl(Url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
              @Override
            public void actionPerformed(NetworkEvent evt) {
           String str = new String(con.getResponseData());
        str2 = str;
        }
            
        });

        NetworkManager.getInstance().addToQueueAndWait(con);

                 return Boolean.parseBoolean(str2);

    }
        
}
