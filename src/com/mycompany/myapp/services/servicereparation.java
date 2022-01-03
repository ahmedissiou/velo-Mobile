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
import com.codename1.maps.Coord;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.reparation;
import com.mycompany.myapp.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author h^
 */
public class servicereparation {
     public ArrayList<reparation> reparation;
    
    public static servicereparation instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    public servicereparation() {
         req = new ConnectionRequest();
    }
    
     public static servicereparation getInstance() {
        if (instance == null) {
            instance = new servicereparation();
        }
        return instance;
    }
  
     public boolean addreparation(reparation r) {
          req.setUrl("http://localhost/pifinal/pidevfinal/web/app_dev.php/api/newvelorep/"+"/?descriptionprob="+r.getDescriptionprob()+"&dateRep="+r.getDateRep()+"&marque="+r.getMarque()+"&type=" +r.getType()+"&ville=" +r.getVille() );
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminÃ© de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle mÃ©thode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistrÃ© et donc Ã©xÃ©cutÃ© mÃªme si 
                la rÃ©ponse reÃ§ue correspond Ã  une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

     
  
    
       public ArrayList<reparation> parserep(String jsonText){
        try {
            reparation=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du rÃ©sultat json

            
           Map<String,Object> reparationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            
          List<Map<String,Object>> list = (List<Map<String,Object>>)reparationListJson.get("root");
            
            //Parcourir la liste des tÃ¢ches Json
            for(Map<String,Object> obj : list){
                //CrÃ©ation des tÃ¢ches et rÃ©cupÃ©ration de leurs donnÃ©es
                reparation r = new reparation();
                float id = Float.parseFloat(obj.get("id").toString());
                String dd = (obj.get("dateRep").toString());
                
                String dt = dd.substring(0, 10);
                
                r.setId((int)id);  
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                Date d = new Date();

                try {

                    d = formatter.parse(dt);

                } catch (ParseException exp) {
                }

                System.out.println(formatter.format(d));
                r.setDescriptionprob(obj.get("descriptionprob").toString());
                r.setType(obj.get("type").toString());
                r.setMarque(obj.get("marque").toString());
                r.setVille(obj.get("ville").toString());
             
              
             
             
           

                //Ajouter la tÃ¢che extraite de la rÃ©ponse Json Ã  la liste
                r.setDateRep(d);
                reparation.add(r);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu rÃ©cupÃ©rer une liste des tÃ¢ches Ã  partir
        de la base de donnÃ©es Ã  travers un service web
        
        */
       return reparation;
    }
       public ArrayList<reparation> getEventsByDate(String date) {
        ConnectionRequest req = new ConnectionRequest();

       String url ="http://localhost/pifinal/pidevfinal/web/app_dev.php/api/date/"+date;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reparation = parserep(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reparation;
    }
       public ArrayList<reparation> getAllEvents2() {
        ConnectionRequest req = new ConnectionRequest();
      String url ="http://localhost/pifinal/pidevfinal/web/app_dev.php/api/come";
        
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reparation = parserep(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reparation;
    }
       
    
    public ArrayList<reparation> getAllreparation(){
        String url ="http://localhost/pifinal/pidevfinal/web/app_dev.php/api/affichevelorep";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reparation = parserep(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reparation;
    }
    
    
     private static final String MAPS_KEY = "AIzaSyC80gbKBnEtbZxbSAEY7v8gZSTjhd8_wOA"; // Your maps key here
 public static Coord getCoords(String address) {
        Coord ret = null;
        try {
            ConnectionRequest request = new ConnectionRequest("https://maps.googleapis.com/maps/api/geocode/json", false);
            request.addArgument("key", MAPS_KEY);
            request.addArgument("address", address);

            NetworkManager.getInstance().addToQueueAndWait(request);
            Map<String, Object> response = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            if (response.get("results") != null) {
                ArrayList results = (ArrayList) response.get("results");
                if (results.size() > 0) {
                    LinkedHashMap location = (LinkedHashMap) ((LinkedHashMap) ((LinkedHashMap) results.get(0)).get("geometry")).get("location");
                    ret = new Coord((double) location.get("lat"), (double) location.get("lng"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
  public reparation getStation() {
         
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pifinal/pidevfinal/web/app_dev.php/api/affichestation" );
        con.setPost(false);
        con.setHttpMethod("GET");

        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                servicereparation es = new servicereparation();
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return null;
    }
  
}

   
    
 

    
