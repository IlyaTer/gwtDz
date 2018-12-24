package org.yournamehere.client.edit;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.yournamehere.client.gewgwt.GWTServiceUsageExample;
import org.yournamehere.client.gewgwt.GWTServiceUsageExample.AppData;
import static org.yournamehere.client.gewgwt.GWTServiceUsageExample.getService;

public class EditDialog extends DialogBox {

    private AppData app;
    private final TextBox nameBox = new TextBox();
    private final TextBox authorBox = new TextBox();
    private final TextBox pagesBox = new TextBox();
    private final TextBox ageBox = new TextBox();
    private final TextBox priceBox = new TextBox();
    
    public EditDialog(List<GWTServiceUsageExample.AppData> jlist) {
                                
        final List<GWTServiceUsageExample.AppData> inlist = jlist;
        
        DockPanel panel = new DockPanel();
        VerticalPanel vpanel = new VerticalPanel();
        HorizontalPanel hpanelName = new HorizontalPanel();
        HorizontalPanel hpanelAuthor = new HorizontalPanel();
        HorizontalPanel hpanelPages = new HorizontalPanel();
        HorizontalPanel hpanelAge = new HorizontalPanel();
        HorizontalPanel hpanelPrice = new HorizontalPanel();
        HorizontalPanel hpanelBut = new HorizontalPanel();
        
        /*AppData(String name, String author, String isbn, int pages,
                int age, Date date, double price)*/
        nameBox.setStyleName("minidialog");
        authorBox.setStyleName("minidialog");
        pagesBox.setStyleName("minidialog");
        ageBox.setStyleName("minidialog");
        priceBox.setStyleName("minidialog");
        
        nameBox.setWidth("150px");
        authorBox.setWidth("150px");
        pagesBox.setWidth("150px");
        ageBox.setWidth("150px");
        priceBox.setWidth("150px");
        
        Label nameLb = new Label("Name:");
        Label authorLb = new Label("Author:");
        Label pagesLb = new Label("Pages:");
        Label ageLb = new Label("Age:");
        Label priceLb = new Label("Price:");
        
        nameLb.setWidth("80px");
        authorLb.setWidth("80px");
        pagesLb.setWidth("80px");
        ageLb.setWidth("80px");
        priceLb.setWidth("80px");
        
        hpanelName.add(nameLb);
        hpanelName.add(nameBox);
        hpanelName.setStyleName("hpm");
        
        hpanelAuthor.add(authorLb);
        hpanelAuthor.add(authorBox);
        hpanelAuthor.setStyleName("hpm");
        
        hpanelPages.add(pagesLb);
        hpanelPages.add(pagesBox);
        hpanelPages.setStyleName("hpm");
        
        hpanelAge.add(ageLb);
        hpanelAge.add(ageBox);
        hpanelAge.setStyleName("hpm");
        
        hpanelPrice.add(priceLb);
        hpanelPrice.add(priceBox);
        hpanelPrice.setStyleName("hpm");
        
        vpanel.add(hpanelName);
        vpanel.add(hpanelAuthor);
        vpanel.add(hpanelPages);
        vpanel.add(hpanelAge);
        vpanel.add(hpanelPrice);
        
        Button edit = new Button("Edit");
        Button cancel = new Button("Cancel");
        
        edit.setStyleName("butons");
        cancel.setStyleName("butons");
        
        final AsyncCallback<String> callback = new AsyncCallback<String>() {
            public void onSuccess(String result) {
               
                AppData appNew = new AppData(nameBox.getText(), 
                        authorBox.getText(),
                    app.getIsbn(), Integer.parseInt(pagesBox.getText()),
                        Integer.parseInt(ageBox.getText()), 
                        app.getDate(), Double.parseDouble(priceBox.getText()));
                Collections.replaceAll(inlist, app, appNew);
                setVisible(false);
                setModal(false);
               
            }//end callback
            
            public void onFailure(Throwable caught) {
                //inlist.add(new GWTServiceUsageExample.AppData("fail", "fail"));
            }
        };
        
        edit.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                AppData appNew = new AppData(nameBox.getText(), 
                        authorBox.getText(),
                    app.getIsbn(), Integer.parseInt(pagesBox.getText()),
                        Integer.parseInt(ageBox.getText()), 
                        app.getDate(), Double.parseDouble(priceBox.getText()));
                Collections.replaceAll(inlist, app, appNew);
                
                getService().myMethod("edt:"+appNew.getIsbn()+"&"+appNew.getName()+"&"+
                        appNew.getAuthor()+"&"+appNew.getPages()+"&"+appNew.getAge()+"&"+
                        appNew.getDate().getTime()+"&"+appNew.getPrice(), callback);
            }
        });
        
        cancel.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                setVisible(false);
                setModal(false);
            }
        });
        
        hpanelBut.add(edit);
        hpanelBut.add(cancel);
        
        panel.add(vpanel, DockPanel.CENTER);
        panel.add(hpanelBut, DockPanel.SOUTH);
        
        add(panel);  
        setText("Edit Book Dialog");
        addStyleName("minidialog");
        center();
        
    }
/*
    nameBox.setWidth("100px");
        authorBox.setWidth("100px");
        pagesBox.setWidth("100px");
        ageBox.setWidth("100px");
        priceBox.setWidth("100px");
        */
    public void setApp(AppData app) {
        this.app = app;
        nameBox.setText(app.getName());
        authorBox.setText(app.getAuthor());
        pagesBox.setText(String.valueOf(app.getPages()));
        ageBox.setText(String.valueOf(app.getAge()));
        priceBox.setText(String.valueOf(app.getPrice()));
    }
    
    
    
}
