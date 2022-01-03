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
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.cours;
import com.mycompany.myapp.entities.paiement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author h^
 */
public class paiementService {
      public ArrayList<paiement> tasks;
    
    public static paiementService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    cours evnt = new cours();
    

    public paiementService() {
         req = new ConnectionRequest();
    }

    public static paiementService getInstance() {
        if (instance == null) {
            instance = new paiementService();
        }
        return instance;
    }

    public boolean addReservation(paiement r , int cour_id ) {
   evnt.getPrixcours();
        req.setUrl("http://localhost/pifinal/pidevfinal/web/app_dev.php/api/payermobile/"+ cour_id + "/?nom="+r.getNom()+"&prenom="+r.getPrénom()+"&mail=" +r.getMail());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.0µµ                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }


}