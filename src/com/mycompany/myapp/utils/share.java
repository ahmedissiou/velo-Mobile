

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.utils;

import com.codename1.facebook.FaceBookAccess;
import com.codename1.io.AccessToken;
import com.codename1.share.FacebookShare;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.cours;
import com.mycompany.myapp.gui.détailscours;

/**
 *
 * @author asus
 */
public class share {
    
    
    String token;

    public void share(String img,cours e) {

        FaceBookAccess.setClientId("234205360478243");
        FaceBookAccess.setClientSecret("4a9918ab8152c7fde7f7c43411b3c597");
        FaceBookAccess.setRedirectURI("https://www.google.tn/");
        FaceBookAccess.setPermissions(new String[]{"publish_actions"});

        FaceBookAccess.getInstance().showAuthentication(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                
                if(evt.getSource() instanceof String){
                    token = (String) evt.getSource();
                }
                if(evt.getSource() instanceof AccessToken){
                    AccessToken t = (AccessToken) evt.getSource();
                    token = t.getToken();
                    
                }
                
                  
                
                FacebookShare f = new FacebookShare();
                détailscours sde = new détailscours(e);
                f.setOriginalForm(sde.getF());
                ShareTest st = new ShareTest(token,sde.getF());
                st.share("j'apprécie ce  Programme","file://C:/wamp67/www/pifinal/pidevfinal/web/images/"+img,"test");
                
                
                
                
                
                
                
                
       

                
            }
        });


    }


    
}