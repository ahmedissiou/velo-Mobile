/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.plaf.Border;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.controllers.LoginController;
import com.mycompany.myapp.entities.fos_user;
import com.mycompany.myapp.services.UserService;
import com.mycompany.myapp.utils.JavaMialutil;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 *
 */
public class SignInForm extends Form {

    public SignInForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public SignInForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("SigninTitle");
        //FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SigninTitle", 3.5f);
        //getToolbar().addCommandToLeftBar("", mat, e -> new SplashForm().show());
        getContentPane().setUIID("SignInForm");
       
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    private com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    private com.codename1.ui.TextField gui_Text_Field_2 = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField gui_Text_Field_1 = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField gui_Text_Field_3 = new com.codename1.ui.TextField();

    private com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_3 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_4 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_5 = new com.codename1.ui.Button();

// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void guiBuilderBindComponentListeners() {
        EventCallbackClass callback = new EventCallbackClass();
        gui_Button_2.addActionListener(callback);
    }

    class EventCallbackClass implements com.codename1.ui.events.ActionListener, com.codename1.ui.events.DataChangedListener {

        private com.codename1.ui.Component cmp;

        public EventCallbackClass(com.codename1.ui.Component cmp) {
            this.cmp = cmp;
        }

        public EventCallbackClass() {
        }

        public void actionPerformed(com.codename1.ui.events.ActionEvent ev) {
            com.codename1.ui.Component sourceComponent = ev.getComponent();
            if (sourceComponent.getParent().getLeadParent() != null) {
                sourceComponent = sourceComponent.getParent().getLeadParent();
            }

            if (sourceComponent == gui_Button_2) {
                //onButton_2ActionEvent(ev);
            }
        }

        public void dataChanged(int type, int index) {
        }
    }

    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        guiBuilderBindComponentListeners();
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Connexion");
        setName("SignInForm");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        //gui_Container_1.addComponent(gui_Label_1);
        gui_Container_1.addComponent(gui_Component_Group_1);
        gui_Component_Group_1.setName("Component_Group_1");
        ///username
        gui_Component_Group_1.addComponent(gui_Text_Field_2);
        //password
        gui_Component_Group_1.addComponent(gui_Text_Field_1);
        gui_Text_Field_2.setHint("Nom d'utilisateur");
        gui_Text_Field_2.setName("Text_Field_2");
        gui_Text_Field_1.setHint("Mot de passe");
        gui_Text_Field_1.setConstraint(TextField.PASSWORD);
        gui_Text_Field_1.setName("Text_Field_1");
        gui_Container_1.addComponent(gui_Button_2);
        gui_Container_1.addComponent(gui_Button_5);
        gui_Button_5.addActionListener((s) -> {
            LoginController.login();
            refreshTheme();
            if (MyApplication.user != null) {

            }

        });
        gui_Container_1.addComponent(gui_Button_3);
        gui_Text_Field_3.setVisible(false);
        gui_Button_4.setVisible(false);

        gui_Container_1.addComponent(gui_Text_Field_3);
        gui_Container_1.addComponent(gui_Button_4);
        gui_Button_4.setText("Nouveau mot de passe");
        gui_Button_4.setUIID("CenterLabel");
        gui_Label_1.setUIID("CenterLabel");
        gui_Label_1.setName("Label_1");
        //gui_Label_1.setIcon(resourceObjectInstance.getImage("profile_image.png"));
        gui_Component_Group_1.setName("Component_Group_1");
        ///:se connecter
        gui_Button_2.setText("Se connecter");
        gui_Button_5.setText("Facebook");

        gui_Button_2.setName("Button_2");
        ///mdp oublié
        gui_Button_3.setText("Mot de passe oublié ?");
        gui_Button_3.setUIID("CenterLabelSmall");
        gui_Button_3.setName("Button_3");
        addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Button_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        ///new account
        gui_Button_1.setText("Créer un nouveau compte");
        gui_Button_1.setUIID("CenterLabel");
        gui_Button_1.setName("Button_1");
        gui_Button_2.addActionListener((e) -> {
            System.out.println(gui_Text_Field_2.getText());
            if (gui_Text_Field_2.getText().equals("") && gui_Text_Field_1.getText().equals("")) {
                System.out.println("in if");
                Dialog dlg = new Dialog("Authentication");
                TextArea ta = new TextArea("veliez remplir les deux champs avec votre login et mot de passe");
                dlg.add(ta);
                Button ok = new Button(new Command("OK"));
                ok.getAllStyles().setBorder(Border.createEmpty());
                ok.getAllStyles().setFgColor(0);
                dlg.add(ok);
                dlg.showDialog();
            } else {
                LoginController l = new LoginController();
                String logged;
                logged = l.login(gui_Text_Field_2.getText(), gui_Text_Field_1.getText());
                //System.err.println(u.getText());
                if (logged.equals("good")) {

                    System.err.println("Logged good");
                    System.out.println("ready for red");
                    HomeForm r = new HomeForm();
                    r.show();

                } else if (logged.equals("nouser")) {
                    Dialog dlg = new Dialog("Authentication");
                    TextArea ta = new TextArea("Le nom d'utilisateur saissie ne correspent a aucun compte");
                    dlg.add(ta);
                    Button ok = new Button(new Command("OK"));
                    ok.getAllStyles().setBorder(Border.createEmpty());
                    ok.getAllStyles().setFgColor(0);
                    dlg.add(ok);
                    dlg.showDialog();
                    System.err.println("nouser");
                    MyApplication.user = null;

                } else {
                    Dialog dlg = new Dialog("Authentication");
                    TextArea ta = new TextArea("Verifier le saisse de votre mot de passe");
                    dlg.add(ta);
                    Button ok = new Button(new Command("OK"));
                    ok.getAllStyles().setBorder(Border.createEmpty());
                    ok.getAllStyles().setFgColor(0);
                    dlg.add(ok);
                    dlg.showDialog();
                    System.err.println("error");
                    MyApplication.user = null;

                }
            }
        });
        gui_Button_1.addActionListener((evt) -> {
            Registration r = new Registration();
            r.getF().show();
        });
        gui_Button_3.addActionListener((evt) -> {
            gui_Text_Field_3.setVisible(true);
            gui_Button_4.setVisible(true);

            refreshTheme();

            gui_Button_4.addActionListener((e) -> {
                UserService us = new UserService();
                fos_user user = new fos_user();
                JavaMialutil j = new JavaMialutil();
                String newpass = us.getSaltString();
                    String passw = BCrypt.hashpw(newpass, BCrypt.gensalt());
                try {
                    j.sendMail(gui_Text_Field_3.getText(), newpass);
                    ConnectionRequest r = new ConnectionRequest();
                  r.setUrl("http://localhost/pass.php?email="+gui_Text_Field_3.getText()+"&passw="+passw);
                    r.setPost(false);
                    NetworkManager.getInstance().addToQueueAndWait(r);
                } catch (Exception ex) {

                }
                /*user = us.getUserbyEmail(gui_Text_Field_3.getText());
                
                if (user != null) {
                    String newpass = us.getSaltString();
                    String password = BCrypt.hashpw(newpass, BCrypt.gensalt());
                    user.setPassword(password);
                    us.updatepasswordUser(user);

                    ConnectionRequest r = new ConnectionRequest();
                    r.setUrl("http://localhost/sendmail.php?to=" + user.getEmail() + "&subject=Velo.tn - Mot de passe&body=" + newpass);
                    r.setPost(false);
                    NetworkManager.getInstance().addToQueueAndWait(r);
                } else {
                    System.out.println("mal9itch");
                }
                 */
            });
        });
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
    /*public void onButton_2ActionEvent(com.codename1.ui.events.ActionEvent ev) {
        new InboxForm().show();
    }*/
}
