/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.fos_user;
import com.mycompany.myapp.services.UserService;

/**
 *
 * @author ouertani
 */
public class Profile {

    Form f;
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();

    public Profile() {
        gui_Button_2.setText("Edit Profile");
        f = new Form("Profile", new com.codename1.ui.layouts.BorderLayout());
        f.getToolbar().addCommandToRightBar("retour", null, (ev) -> {

            HomeForm h = new HomeForm();

            h.show();
        });

        Form hi = new Form();
        f.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);

        gui_Container_1.add(new Label("Nom d'utilisateur : " + MyApplication.user.getUsername()));
        gui_Container_1.add(new Label("email : " + MyApplication.user.getEmail()));
        gui_Container_1.add(gui_Button_2);
        gui_Button_2.addActionListener((evt) -> {
            editprofile profileedit = new editprofile();
            profileedit.getF().show();

        });


        /*
hi.setTitle("Profil");
        hi.add(c);

        hi.show();
         */
    }

    public Form getF() {
        return f;
    }

}
