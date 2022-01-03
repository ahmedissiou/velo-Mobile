/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.fos_user;
import com.mycompany.myapp.services.UserService;

/**
 *
 * @author berrahal
 */
public class editprofile {

    Form f;

    Button btnaff;

    public editprofile() {
        /*f.getToolbar().addCommandToRightBar("retour", null, (ev) -> {

            Profile h = new Profile();

            h.getF().show();
        });
         */
        f = new Form("Mon profil");

        Label l = new Label();
        TextField nom = new TextField("", "Nom d'utilisateur");
        TextField email = new TextField("", "Email");
        btnaff = new Button("Modifier");
        f.add(nom);
        f.add(email);
        f.add(btnaff);
        Label password = new Label("if you want to change your passworld please visit our webapplication");
        password.setAutoSizeMode(true);
        f.add(password);
        System.err.println(MyApplication.user.getPassword());
        email.setText(MyApplication.user.getEmail());
        nom.setText(MyApplication.user.getUsername());

        btnaff.addActionListener((evt) -> {
            UserService us = new UserService();
            fos_user u = new fos_user();
            u.setUsername(nom.getText());
            u.setEmail(email.getText());
            u.setId(MyApplication.user.getId());
            us.updateUser(u);
            MyApplication.user=u;
            System.out.println(MyApplication.user.getId());
            HomeForm h = new HomeForm();

            h.show();
        });

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
