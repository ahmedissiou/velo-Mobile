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
import com.mycompany.myapp.entities.cours;
import com.mycompany.myapp.entities.Evaluations;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class EvaluationService {
      public ArrayList<Evaluations> tasks;
    
    public static EvaluationService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
  //  cours evnt = new cours();
    Evaluations re = new Evaluations();

    public EvaluationService() {
         req = new ConnectionRequest();
    }

    public static EvaluationService getInstance() {
        if (instance == null) {
            instance = new EvaluationService();
        }
        return instance;
    }

    public boolean addEvaluation(Evaluations t , int cour_id ) {
      String url =  "http://localhost/pifinal/pidevfinal/web/app_dev.php/api/evaluer/" + cour_id + "/?commentaire="+t.getCommentaire()+ "&note="+ t.getNote()  ;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public Evaluations getEv2(Evaluations l ,int id) {
           ConnectionRequest req = new ConnectionRequest();
      String url = "http://localhost/pifinal/pidevfinal/web/app_dev.php/api/eval/"+id;
      req.setUrl(url);
      req.setPost(false);
        req.addResponseListener((NetworkEvent evt) -> {
            byte[] data = (byte[]) evt.getMetaData();
            String s = new String(data);
           System.out.println(data);
          });
       NetworkManager.getInstance().addToQueue(req);
       return l;
    }
    
   
   
    
    }



