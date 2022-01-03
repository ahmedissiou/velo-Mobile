/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.utils;

import com.codename1.components.InfiniteProgress;
import com.codename1.facebook.FaceBookAccess;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.io.InputStream;



/**
 *
 * @author El Kamel
 */
public class ShareTest {
    
    String token;
    private Form original;
    
    public ShareTest(String tkn , Form f) {
        
        this.token = tkn;
        this.original = f;
        
        
    }
    
        public void share(String text, final String image, final String mime) {
        final ShareForm[] f = new ShareForm[1];
        if (image == null) {
            f[0] = new ShareForm(getOriginal(), "Partager sur facebook", null, text,
                    new ActionListener() {

                        public void actionPerformed(ActionEvent evt) {
                            InfiniteProgress inf = new InfiniteProgress();
                            final Dialog progress = inf.showInifiniteBlocking();
                            FaceBookAccess.getInstance().addResponseCodeListener(new ActionListener() {
                                
                                public void actionPerformed(ActionEvent evt) {
                                    NetworkEvent ne = (NetworkEvent) evt;
                                    int code = ne.getResponseCode();
                                    FaceBookAccess.getInstance().removeResponseCodeListener(this);
                                    progress.dispose();
                                    Dialog.show("Failed to Share", "for some reason sharing has failed, try again later.", "Ok", null);
                                    finish();
                                }
                            });
                            try {
                                FaceBookAccess.getInstance().postOnWall("me", f[0].getMessage(), new ActionListener() {
                                    
                                    public void actionPerformed(ActionEvent evt) {
                                        progress.dispose();
                                        finish();
                                    }
                                });
                            } catch (IOException ex) {
                                System.out.println(ex.toString());
                            }
                        }
                    });
            f[0].show();
        } else {
            f[0] = new ShareForm(getOriginal(), "Post on My Wall", null, text, image,
                    new ActionListener() {

                        public void actionPerformed(ActionEvent evt) {

                            InfiniteProgress inf = new InfiniteProgress();
                            final Dialog progress = inf.showInifiniteBlocking();
                            FaceBookAccess.getInstance().addResponseCodeListener(new ActionListener() {

                                public void actionPerformed(ActionEvent evt) {
                                    NetworkEvent ne = (NetworkEvent) evt;
                                    int code = ne.getResponseCode();
                                    FaceBookAccess.getInstance().removeResponseCodeListener(this);
                                    progress.dispose();
                                    Dialog.show("Failed to Share", "for some reason sharing has failed, try again later.", "Ok", null);
                                    finish();
                                }
                            });

                            MultipartRequest req = new MultipartRequest();
                            req.addResponseListener(new ActionListener() {                                
                                public void actionPerformed(ActionEvent evt) {
                                    progress.dispose();
                                    finish();
                                }
                            });
                            
                           
                            final String endpoint = "https://graph.facebook.com/v2.12/me/photos?access_token=" + token;
                            req.setUrl(endpoint);
                            req.addArgumentNoEncoding("message", f[0].getMessage());
                            InputStream is = null;
                            try {
                                is = FileSystemStorage.getInstance().openInputStream(image);
                                req.addData("source", is, FileSystemStorage.getInstance().getLength(image), mime);
                                NetworkManager.getInstance().addToQueue(req);
                            } catch (IOException ioe) {
                                Log.e(ioe);
                            }
                        }
                    });
            f[0].show();

        }

    }
        
    public void finish(){
        original.showBack();
    }
    
    public Form getOriginal() {
        return original;
    }
    
    
    
}
