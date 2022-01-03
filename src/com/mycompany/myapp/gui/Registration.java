/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InteractionDialog;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.fos_user;
import com.mycompany.myapp.services.UserService;

/**
 *
 * @author berrahal
 */
public class Registration {

    Form f;

    Button btnaff;
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    private com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    private com.codename1.ui.TextField gui_Text_Field_1 = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField gui_Text_Field_5 = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField gui_Text_Field_6 = new com.codename1.ui.TextField();
    private com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();

    public Registration() {
        f = new Form("Inscription", new com.codename1.ui.layouts.BorderLayout());
        f.getToolbar().addCommandToRightBar("retour", null, (ev) -> {

            SignInForm h = new SignInForm();

            h.show();
        });
        f.setScrollable(true);
        f.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(gui_Label_1);
        gui_Container_1.addComponent(gui_Component_Group_1);
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Component_Group_1.addComponent(gui_Text_Field_1);
        gui_Component_Group_1.addComponent(gui_Text_Field_5);
        gui_Component_Group_1.addComponent(gui_Text_Field_6);
        //gui_Text_Field_1.setText("Email@:");
        //gui_Text_Field_5.setText("Nom utilisateur:");
        //gui_Text_Field_6.setText("Mot de passe:");

        gui_Container_1.addComponent(gui_Button_2);
        gui_Label_1.setUIID("CenterLabel");
        gui_Label_1.setName("Label_1");
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Button_2.setText("S'inscrire");
        gui_Button_2.setName("Button_2");
        TextField email = new TextField("", "Email");
        TextField utilisateur = new TextField("", "Nom d'utilisateur");
        TextField password = new TextField("", "Mot de passe");
        gui_Text_Field_1.setHint("Email");
        gui_Text_Field_5.setHint("nom d'utilisateur");
        gui_Text_Field_6.setHint("Mot de passe");
        btnaff = new Button("S'inscrire");

        gui_Button_2.addActionListener((evt) -> {
            int s = gui_Text_Field_1.getText().indexOf("@");

            System.out.println("index est " + s);
            if (s < 0) {
                Dialog.show("Erreur", "Veillez vérifier votre email", "OK", null);

            } else {

                UserService us = new UserService();
                fos_user u = new fos_user(gui_Text_Field_5.getText(), gui_Text_Field_1.getText(), gui_Text_Field_6.getText(), 0);
                us.addUser(u);
                System.out.println(u);
                SignInForm h = new SignInForm();
                h.show();
            }

        });
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setScrollableX(true);
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }

        return true;
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
