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
import com.mycompany.myapp.entities.locationn;
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
public class ServiceLocation {

    public static  ServiceLocation getInstance() {
        if (instance == null) {
            instance = new  ServiceLocation();
        }
        return instance;
    }
    

    
        public ArrayList<locationn> locationns;
    locationn l = new locationn();
    public static  ServiceLocation  instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public  ServiceLocation() {
         req = new ConnectionRequest();
    }

   /* public static Servicelocation getInstance() {
    
   
    }*/
    
     public locationn addTask( locationn lo ,int id,int idu) {
         
 String url = Statics.BASE_URL + "/location/new/" +id+"/" +idu+ "?datedebut=" +lo.getDate_debut()+ "&datefin=" + lo.getDate_fin()+ "&nomloc=" + lo.getNomlocation() ; //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
              if  (resultOK = req.getResponseCode() == 200)
              {   
                  //Code HTTP 200 OK
                  ServiceLocation es = new ServiceLocation ();
                  
                     l= es.getEvent(new String(req.getResponseData()));
                  
                  
                req.removeResponseListener(this); //Supprimer cet actionListener
               }
              } catch (IOException ex) {
                      
                  }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return l;
    }
     
       public locationn getEvent2(int id) {

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pifinal/pidevfinal/web/app_dev.php/location/find/" + id);
         
        
        
        con.setPost(false);
        con.setHttpMethod("GET");

        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    ServiceLocation es = new ServiceLocation ();
                    l = es.getEvent(new String(con.getResponseData()));
                } catch (IOException ex) {
                    
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return l;
    }
      public locationn getEvent(String jsonText) throws IOException {
    
       locationn l = new locationn();

        try {
            System.out.println(jsonText);
             locationns = new ArrayList<>();
            JSONParser j = new JSONParser();
           
            Map<String, Object> evt = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
          

          
          String dd = (evt.get("dateDebut").toString());
           System.out.println(dd);
                String dt = dd.substring(0, 10);

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                Date d = new Date();

                try {

                    d = formatter.parse(dt);

                } catch (ParseException exp) {
                }
                
                String df = (evt.get("dateFin").toString());

                String dm = df.substring(0, 10);

               // SimpleDateFormat formatterk = new SimpleDateFormat("yyyy-MM-dd");

                Date p = new Date();

                try {

                    p = formatter.parse(dm);

                } catch (ParseException exp) {
                }
         /* Map<String, Object> date = (Map<String, Object>) evt.get("dateDebut");
                float time = Float.parseFloat(date.get("timestamp").toString());
//              DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                l.setDate_debut(new Date((long) time * 1000));
                Map<String, Object> date1 = (Map<String, Object>) evt.get("dateFin");
                float time1 = Float.parseFloat(date1.get("timestamp").toString());
//              DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                l.setDate_fin(new Date((long) time * 1000));*/
                l.setId((int)Float.parseFloat(evt.get("id").toString()));
                 
               //Integer id = Integer.parseInt(evt.get("id").toString());
              //  l.setId(id);
                        l.setPrixloc((int)Float.parseFloat(evt.get("prixloc").toString()));
               l.setDate_debut(d);
              l.setDate_fin(p);
                l.setNomlocation(evt.get("nomloc").toString());
                 Map<String, Object> velo = (Map) evt.get("velo");
             l.setVelo_id((int)Float.parseFloat(velo.get("id").toString()));

             locationns.add(l);
            
       

        } catch (IOException ex) {
        }

        return l;

      }
      
      
        public void PayOrder( int id)
    {
     
        ConnectionRequest r = new ConnectionRequest();
               r.setUrl("http://localhost/pifinal/pidevfinal/web/app_dev.php/location/prix/" + id);
       
             // String url = Statics.BASE_URL + "/location/prix/" +id ;
                r.setPost(false);
                NetworkManager.getInstance().addToQueueAndWait(r);
                System.err.println(r.getResponseCode());
    }
        
         public void Afficherasurance( int id)
    {
     
        ConnectionRequest r = new ConnectionRequest();
               r.setUrl("http://localhost/pifinal/pidevfinal/web/app_dev.php/location/asurance/" + id);
       
             // String url = Statics.BASE_URL + "/location/prix/" +id ;
                r.setPost(false);
                NetworkManager.getInstance().addToQueueAndWait(r);
                System.err.println(r.getResponseCode());
    }
         
           String res = "";
           public void remove(int id){
        ConnectionRequest con = new ConnectionRequest();
        
        con.setUrl("http://localhost/pifinal/pidevfinal/web/app_dev.php/location/supprimer/"+id);
        
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                res = new String(con.getResponseData());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
        public locationn edit( locationn lo ,int id) {
         
        String url = Statics.BASE_URL + "/location/edit/" +id+ "?datedebut=" +lo.getDate_debut()+ "&datefin=" + lo.getDate_fin()+ "&nomloc=" + lo.getNomlocation() ; //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
              if  (resultOK = req.getResponseCode() == 200)
              {   
                  //Code HTTP 200 OK
                  ServiceLocation es = new ServiceLocation ();
                  
                     l= es.getEvent(new String(req.getResponseData()));
                  
                  
                req.removeResponseListener(this); //Supprimer cet actionListener
               }
              } catch (IOException ex) {
                      
                  }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return l;
    }    
        public boolean edit1( locationn lo ,int id) {
         
        String url = Statics.BASE_URL + "/location/edit/" +id+ "?datedebut=" +lo.getDate_debut()+ "&datefin=" + lo.getDate_fin()+ "&nomloc=" + lo.getNomlocation() ; //création de l'URL
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
          public ArrayList<locationn> parseTasks(String jsonText){
        try {
            locationns=new ArrayList<>();
            JSONParser j = new JSONParser();
           
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(tasksListJson);
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> evt : list){
              
                locationn l = new locationn();
                
          String dd = (evt.get("dateDebut").toString());

                // String dd = (evt.get("dateDebut").toString());
           System.out.println(dd);
                String dt = dd.substring(0, 10);

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                Date d = new Date();

                try {

                    d = formatter.parse(dt);

                } catch (ParseException exp) {
                }
                
                String df = (evt.get("dateFin").toString());

                String dm = df.substring(0, 10);

               // SimpleDateFormat formatterk = new SimpleDateFormat("yyyy-MM-dd");

                Date p = new Date();

                try {

                    p = formatter.parse(dm);

                } catch (ParseException exp) {
                }
        /*  Map<String, Object> date = (Map<String, Object>) evt.get("dateDebut");
                float time = Float.parseFloat(date.get("timestamp").toString());
//              DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                l.setDate_debut(new Date((long) time * 1000));
               
                Map<String, Object> date1 = (Map<String, Object>) evt.get("dateFin");
                float time1 = Float.parseFloat(date1.get("timestamp").toString());
//              DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                l.setDate_fin(new Date((long) time1 * 1000));*/
                l.setId((int)Float.parseFloat(evt.get("id").toString()));
                 
               //Integer id = Integer.parseInt(evt.get("id").toString());
              //  l.setId(id);
                        l.setPrixloc((int)Float.parseFloat(evt.get("prixloc").toString()));
               l.setDate_debut(d);
              l.setDate_fin(p);
           
                l.setNomlocation(evt.get("nomloc").toString());
                 Map<String, Object> velo = (Map) evt.get("velo");
             l.setVelo_id((int)Float.parseFloat(velo.get("id").toString()));

                /* Map<String, Object> user = (Map) evt.get("user");
             l.setUser_id((int)Float.parseFloat(user.get("id").toString()));*/
               /* float user_id= Float.parseFloat(evt.get("user_id").toString());
                l.setUser_id((int)user_id);*/
                
                //Ajouter la tâche extraite de la réponse Json à la liste
                locationns.add(l);
            }
            
            
        } catch (IOException ex) {
            
        }
        
        return locationns;
    }
    
    public ArrayList<locationn>  getAllloc( int id){
         req.setUrl("http://localhost/pifinal/pidevfinal/web/app_dev.php/location/finduser/" +id);
         req.setPost(false);
        req.setHttpMethod("GET");
 req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                locationns= parseTasks(new String(req.getResponseData()));
                System.out.println(locationns);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return locationns;
    }
       /* req.addResponseListener((NetworkEvent evt) -> {
            try {
                ServiceLocation es = new ServiceLocation ();
                l = es.getEvent(new String(req.getResponseData()));
            } catch (IOException ex) {
                
            }
         });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return l;
    }*/
     public ArrayList<locationn>  getlocv( int id){
         req.setUrl("http://localhost/pifinal/pidevfinal/web/app_dev.php/location/findvelo/" +id);
         req.setPost(false);
        req.setHttpMethod("GET");
 req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                locationns= parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return locationns;
    }
      public ArrayList<locationn> getAllvelos( int id){
        String url = Statics.BASE_URL+"/location/findvelo/" +id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                locationns= parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return locationns;
    }
     
     
     
     
   public locationn getEvent3(int id) {

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pifinal/pidevfinal/web/app_dev.php/location/findveloo/" + id);
         
        
        
        con.setPost(false);
        con.setHttpMethod("GET");

        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    ServiceLocation es = new ServiceLocation ();
                    l = es.getEvent(new String(con.getResponseData()));
                } catch (IOException ex) {
                    
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return l;
}

    


    
    
}
 




