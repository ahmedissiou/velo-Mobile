/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.controllers;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.MyApplication;
import java.util.ArrayList;
import com.codename1.facebook.FaceBookAccess;
import com.codename1.io.Oauth2;
import com.codename1.io.Storage;
import com.codename1.social.FacebookConnect;
import com.codename1.social.Login;
import com.codename1.social.LoginCallback;
import com.codename1.ui.Dialog;
import com.mycompany.myapp.entities.fos_user;
import com.mycompany.myapp.gui.HomeForm;
import com.mycompany.myapp.services.UserService;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author berrahal
 */
public class LoginController {

    String status;
    fos_user usertest = new fos_user();

    public String login(String username, String password) {
        ArrayList<fos_user> users = new ArrayList<>();
        UserService us = new UserService();
        users = us.getAllUsers();
        System.out.println(users);

        for (fos_user user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("we are in 1 case");
                MyApplication.user = user;
                System.out.println(MyApplication.user);
                String p;
            }

        }
        if (MyApplication.user != null) {
            System.out.println(MyApplication.user);
            System.out.println("we are inthe 2 case");

            ConnectionRequest r = new ConnectionRequest();

            r.setUrl("http://localhost/testpass.php?user=" + MyApplication.user.getId() + "&password=" + password);
            r.setPost(false);
            r.addResponseListener(new ActionListener<NetworkEvent>() {

                @Override
                public void actionPerformed(NetworkEvent evt) {

                    status = testpass(new String(r.getResponseData()));

                }

            });

            NetworkManager.getInstance().addToQueueAndWait(r);
            System.out.println(status);
            return status;
        } else {

            return "nouser";
        }

    }

    public String testpass(String status) {
        if (status.equals("nice")) {
            return "good";
        } else {
            return "notgood";
        }
    }
    public static String TOKEN;

    private static void signIn() {

        String clientId = "242073250350099";
        String redirectURI = "http://localhost/";
        String clientSecret = "80d4f6fcfb1bf33577d7ff19496c8d83";
        Login fb = FacebookConnect.getInstance();
        fb.setClientId(clientId);
        fb.setRedirectURI(redirectURI);
        fb.setClientSecret(clientSecret);

        //Sets a LoginCallback listener
        fb.setCallback(new LoginCallback() {
            @Override
            public void loginSuccessful() {
                String token = fb.getAccessToken().getToken();

                System.err.println(token);
                UserService us = new UserService();

                fos_user u = new fos_user();

                //u=us.getUserInfoFB(token);
                System.err.println(u.getEmail());

                if (us.getUserbyEmail(u.getEmail()) != null) {

                    MyApplication.user = us.getUserbyEmail(u.getEmail());

                } else {

                    us.addUser(u);

                    MyApplication.user = u;

                }

                HomeForm r = new HomeForm();
                r.show();             
            }

            @Override
            public void loginFailed(String errorMessage) {
                Dialog.show("No!", "it did not work!", "sad", null);
            }

        });
        //trigger the login if not already logged in
        if (!fb.isUserLoggedIn()) {
            fb.doLogin();

        } else {

//get the token and now you can query the facebook API
            String token = fb.getAccessToken().getToken();
            System.err.println(token);
            UserService us = new UserService();
            fos_user u = new fos_user();
            //u=us.getUserInfoFB(token);
            System.err.println(u.getEmail());

            if (us.getUserbyEmail(u.getEmail()) != null) {
                MyApplication.user = us.getUserbyEmail(u.getEmail());

            } else {
                us.addUser(u);
                MyApplication.user = u;

            }

        }

    }

    public static boolean firstLogin() {
        return Storage.getInstance().readObject("token") == null;
    }

    public static void login() {
        if (firstLogin()) {
            signIn();
        } else {
            //token exists no need to authenticate
            TOKEN = (String) Storage.getInstance().readObject("token");
            FaceBookAccess.setToken(TOKEN);
            //in case token has expired re-authenticate
            FaceBookAccess.getInstance().addResponseCodeListener(new ActionListener() {

                public void actionPerformed(ActionEvent evt) {
                    NetworkEvent ne = (NetworkEvent) evt;
                    int code = ne.getResponseCode();
                    //token has expired
                    if (code == 400) {
                        System.err.println("400");
                    }
                }
            });
        }
    }

}
