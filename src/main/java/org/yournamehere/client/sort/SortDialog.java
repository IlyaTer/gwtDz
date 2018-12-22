package org.yournamehere.client.sort;

import org.yournamehere.client.gewgwt.GWTServiceUsageExample;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import java.util.Date;
import java.util.List;
import static org.yournamehere.client.gewgwt.GWTServiceUsageExample.getService;

public class SortDialog extends DialogBox {

    public SortDialog(List<GWTServiceUsageExample.AppData> jlist) {
        final List<GWTServiceUsageExample.AppData> list = jlist;
        
        DockPanel panel = new DockPanel();
        VerticalPanel vpanel = new VerticalPanel();
        /*AppData(String name, String author, String isbn, int pages, int age, Date date, double price)*/
        RadioButton isbn = new RadioButton("sort", "ISBN Short");
        RadioButton name = new RadioButton("sort", "Name");
        RadioButton author = new RadioButton("sort", "Author");
        RadioButton pages = new RadioButton("sort", "Pages");
        RadioButton age = new RadioButton("sort", "Age");
        RadioButton date = new RadioButton("sort", "Add Date");
        RadioButton price = new RadioButton("sort", "Price");
        
        final AsyncCallback<String> callbackSort = new AsyncCallback<String>() {
            public void onSuccess(String result) {
                list.clear();
                String[] strs = result.split("\n");
                
                for(String str: strs)
                {
                    String[] res = str.split("&");
                    list.add(new GWTServiceUsageExample.AppData(res[0], res[1], res[2], Integer.parseInt(res[3]),
                        Integer.parseInt(res[4]), new Date(Long.parseLong(res[5])),
                        Double.parseDouble(res[6])));
                }
            }
            
            public void onFailure(Throwable caught) {
                //lblServerReply.setText("Communication failed");
            }
        };
        
        isbn.addValueChangeHandler(new ValueChangeHandler<java.lang.Boolean>(){
               public void onValueChange(ValueChangeEvent<java.lang.Boolean> event) {
                    getService().myMethod("srt:isbn", callbackSort);
                }});        
        name.addValueChangeHandler(new ValueChangeHandler<java.lang.Boolean>(){
               public void onValueChange(ValueChangeEvent<java.lang.Boolean> event) {
                    getService().myMethod("srt:name", callbackSort);
                }});
        author.addValueChangeHandler(new ValueChangeHandler<java.lang.Boolean>(){
               public void onValueChange(ValueChangeEvent<java.lang.Boolean> event) {
                    getService().myMethod("srt:author", callbackSort);
                }});
        pages.addValueChangeHandler(new ValueChangeHandler<java.lang.Boolean>(){
               public void onValueChange(ValueChangeEvent<java.lang.Boolean> event) {
                    getService().myMethod("srt:pages", callbackSort);
                }});
        age.addValueChangeHandler(new ValueChangeHandler<java.lang.Boolean>(){
               public void onValueChange(ValueChangeEvent<java.lang.Boolean> event) {
                    getService().myMethod("srt:age", callbackSort);
                }});
        date.addValueChangeHandler(new ValueChangeHandler<java.lang.Boolean>(){
               public void onValueChange(ValueChangeEvent<java.lang.Boolean> event) {
                    getService().myMethod("srt:date", callbackSort);
                }});
        price.addValueChangeHandler(new ValueChangeHandler<java.lang.Boolean>(){
               public void onValueChange(ValueChangeEvent<java.lang.Boolean> event) {
                    getService().myMethod("srt:price", callbackSort);
                }});
        
        
        Button ok = new Button("Ok");
        ok.setStyleName("butons");
        
        ok.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                setVisible(false);
                setModal(false);
           }
        });
        
        vpanel.add(isbn);
        vpanel.add(name);
        vpanel.add(author);
        vpanel.add(pages);
        vpanel.add(age);
        vpanel.add(date);
        vpanel.add(price);
        
        
        panel.add(vpanel, DockPanel.CENTER);
        panel.add(ok, DockPanel.SOUTH);
        
        add(panel);
        setText("Sort Book Dialog");
        addStyleName("minidialog");
        center();
    }
    
       
}
