/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.Travedia.myapp.PaiementService;
import com.Travedia.myapp.ReclamationService;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.SpanMultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entities.Paiement;
import entities.Reclamation;
import entities.ReclamationReponse;
import java.util.Map;

/**
 *
 * @author riadh
 */
public class ListePaiementForm extends BaseForm{
    Form current;
    public ListePaiementForm(Form previous) {
        setTitle("List Paiement");
        setLayout(BoxLayout.y());
        //setUIID("SingIn");
        Resources res;
        res = UIManager.initFirstTheme("/theme");
        Tabs swipe = new Tabs();
        Label s1 = new Label();
        addTab(swipe, res.getImage("payment.jpg"),s1,"","","");
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        current=this;
        SpanLabel sp = new SpanLabel();
        //sp.setText(PaiementService.getInstance().getAllPaiement().toString());
        Container listRec = new Container(BoxLayout.y());
        listRec.setScrollableY(true);
        
        for (Paiement p : PaiementService.getInstance().getAllPaiement())
        {
            SpanMultiButton mb = new SpanMultiButton(p.getOwnerName() + " - " +p.getClientName());
            mb.setTextLine2(p.getPrix()+" ");
            mb.addActionListener(e -> new AfficherPaiementForm(current,(int)p.getId()).show());
            listRec.add(mb);
        }
        add(listRec);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        getToolbar().addSearchCommand(e -> 
        {
            String text= (String)e.getSource();
            if(text==null || text.length()==0)
            {
                //Search Bar Empty
                for(Component cmp : getContentPane())
                {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
                
            }else
            {
                text=text.toLowerCase();
                System.out.println(text);
                for (Component cmp: listRec.getChildrenAsList(focusScrolling))
                {
                    if (cmp instanceof MultiButton){
                    MultiButton mb = (MultiButton)cmp;
                    String line1 = mb.getTextLine1();
                    String line2 = mb.getTextLine2();
                    boolean show = line1 != null && line1.toLowerCase().indexOf(text)>-1 
                            || line2!=null && line2.toLowerCase().indexOf(text)>-1;
                        System.out.println(show);
                    mb.setHidden(!show);
                    mb.setVisible(show);
                    }
                }
                
            }
        });
    
    
    
    }

        private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
       // heartStyle.setFgColor(0xff2d55);
        //FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        //likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        //FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            FlowLayout.encloseIn(likes, comments),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
}
