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
import com.mycompany.myapp.entities.asurance;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Hajer
 */
public class ServiceAsurance {
    

    public static  ServiceAsurance getInstance() {
        if (instance == null) {
          instance = new  ServiceAsurance();
        }
        return instance;
    }
    

    
        public ArrayList<asurance> locationns;
    asurance a= new asurance();
    public static  ServiceAsurance instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public  ServiceAsurance() {
         req = new ConnectionRequest();
    }
     public asurance getEvent2(int id) {

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pifinal/pidevfinal/web/app_dev.php/location/asurance/" + id);
         
        
        
        con.setPost(false);
        con.setHttpMethod("GET");

        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    ServiceAsurance es = new ServiceAsurance();
                    a = es.getEvent(new String(con.getResponseData()));
                } catch (IOException ex) {
                    
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return a;
    }
      public asurance getEvent(String jsonText) throws IOException {
    
       asurance a = new asurance();

        try {
            System.out.println(jsonText);
             locationns = new ArrayList<>();
            JSONParser j = new JSONParser();
           
            Map<String, Object> evt = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(evt);

        
                
                        a.setMontant((int)Float.parseFloat(evt.get("montant").toString()));
               

             locationns.add(a);
            
       

        } catch (IOException ex) {
        }

        return a;

      }
      
      
}
