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
import com.mycompany.myapp.entities.Comment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Manel 
 */
public class CommentService {
    public ArrayList<Comment> Comment;
    public static CommentService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    public CommentService() {
         req = new ConnectionRequest();
    }
    
     public static CommentService getInstance() {
        if (instance == null) {
            instance = new CommentService();
        }
        return instance;
    }
     
           public ArrayList<Comment> parseEvents(String jsonText){
        try {
            Comment=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            
           Map<String,Object> coursListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            
          List<Map<String,Object>> list = (List<Map<String,Object>>)coursListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Comment cmt   = new Comment();
                float id = Float.parseFloat(obj.get("id").toString());
                cmt.setId((int)id);
                cmt.setCommentaire(obj.get("commentaire").toString());
                Map<String, Object> data3 = (Map<String, Object>) (obj.get("User"));
                                 float userId = Float.parseFloat(data3.get("id").toString());

                cmt.setUser_id((int) userId);
                cmt.setUser_name(data3.get("username").toString());
                //Ajouter la tâche extraite de la réponse Json à la liste
                Comment.add(cmt);
            }
            
            
        } catch (IOException ex) {
            
        }
        return Comment;
        
           }
        
           
           public Comment AfficherComments(int id ) {
        Comment cmt  = new Comment (); 
        String url ="http://localhost/pifinal/pidevfinal/web/app_dev.php/api/afficherComment/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              String json = new String(req.getResponseData());
              System.out.println(json);
                 try {
              JSONParser j = new JSONParser();
              Map<String, Object>  comments = j.parseJSON(new CharArrayReader(json.toCharArray()));
            
                float id = Float.parseFloat(comments.get("id").toString());
                cmt.setId((int) id);
                cmt.setCommentaire(comments.get("commentaire ").toString());
                }
              catch (IOException ex) {
        }
                 }
        });
         NetworkManager.getInstance().addToQueueAndWait(req);
        return cmt;
    }
     
               
               public ArrayList<Comment> getAllComments(int id ){
                                ConnectionRequest con = new ConnectionRequest();

        String url ="http://localhost/pifinal/pidevfinal/web/app_dev.php/api/afficherComment/"+id;
        con.setUrl(url);
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Comment = parseEvents(new String(con.getResponseData()));
                con.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return Comment;
    }
    
        
     public void supprimercmt(int id) {
          ConnectionRequest con = new ConnectionRequest();
             String Url ="http://localhost/pifinal/pidevfinal/web/app_dev.php/api/deleteComment/"+id;
              con.setUrl(Url);
     
          NetworkManager.getInstance().addToQueueAndWait(con);
    }         
               
          public void ajoutCmt(Comment cmt ) {
             String Url = "http://localhost/pifinal/pidevfinal/web/app_dev.php/api/addComment";
                 req.setPost(true);
                 req.setUrl(Url);
                 req.addArgument("Commentaire", cmt.getCommentaire());
                 req.addArgument("eventId",Integer.toString( cmt.getEvent_id()));
                 req.addArgument("userId",Integer.toString( cmt.getUser_id()));

                 req.addResponseListener((e) -> {
                 String str = new String(req.getResponseData());
                 System.out.println(str);

       
          });
             NetworkManager.getInstance().addToQueueAndWait(req);
    }
     
           public void modifierComment (Comment cmt ) {
     
               ConnectionRequest con = new ConnectionRequest();

     String Url = "http://localhost/pifinal/pidevfinal/web/app_dev.php/api/modifiercomment/"+cmt.getId();
        con.setPost(true);
        con.setUrl(Url);
        con.addArgument("Commentaire", cmt.getCommentaire());
       
       
                con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
     }

          
          
}
