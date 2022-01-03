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
import com.mycompany.myapp.entities.Addresse;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;




/**
 *
 * @author asus
 */
public class AddresseService {
    
     public ArrayList<Addresse> Addresse;
    public static AddresseService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
        Addresse aa = new Addresse();

    public AddresseService() {
             req = new ConnectionRequest();
    }
    public static AddresseService getInstance() {
        if (instance == null) {
            instance = new AddresseService();
        }
        return instance;
    }
    
    
        public Addresse addAddresse(Addresse a)
    {
        req.setUrl(Statics.BASE_URL+"/new1/"+a.getNumTel()+"/" +a.getMail()+"/" +a.getPayer()+"/"+a.getVille()+"/"+a.getPincode()); 
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                 try {
                if  (resultOK = req.getResponseCode() == 200)
              {
               

                     AddresseService as = new AddresseService();
               
                    aa= as.parseAddresse(new String(req.getResponseData()));
              }
                } catch (IOException ex) {
                  
                }
                //aa=as.parseAddresse(new String(req.))
                req.removeResponseListener(this);
               // System.out.println(req);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return aa;
    }
        
 public Addresse  parseAddresse(String jsonText) throws IOException {
    
       Addresse a = new Addresse();

        try {
            System.out.println(jsonText);
             Addresse = new ArrayList<>();
            JSONParser j = new JSONParser();
           
            Map<String, Object> evt = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(evt);

                a.setId((int)Float.parseFloat(evt.get("id").toString()));
               //Integer id = Integer.parseInt(evt.get("id").toString());
              //  l.setId(id);
          

             Addresse.add(a);
            
       

        } catch (IOException ex) {
        }

        return a;

      }

  
    
}
