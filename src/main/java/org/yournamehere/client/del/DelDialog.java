package org.yournamehere.client.del;

import org.yournamehere.client.gewgwt.GWTServiceUsageExample;
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
import java.util.Date;
import java.util.List;
import static org.yournamehere.client.gewgwt.GWTServiceUsageExample.getService;


public class DelDialog extends DialogBox{
    
    private static class ErrorMes extends DialogBox
    {
        private Label message = new Label();

        public ErrorMes() {
            DockPanel panel = new DockPanel();
            message.setStyleName("lebl");
                        
            Button ok = new Button("Ok");
            
            ok.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                  setVisible(false);
                  setModal(false);
                }
            });
            
            ok.setStyleName("butons");
            
            panel.add(message, DockPanel.CENTER);
            panel.add(ok, DockPanel.SOUTH);
            
            add(panel);
            setText("Error:");
            addStyleName("minidialog");            
        }
        
        public void setMessage(String msg)
        {
            message.setText(msg);
        }
    }//end ErrorMes
    
    private ErrorMes error;

    public DelDialog(List<GWTServiceUsageExample.AppData> jlist) {
        
        final List<GWTServiceUsageExample.AppData> inlist = jlist;
        
        DockPanel panel = new DockPanel();
        VerticalPanel vpanel = new VerticalPanel();
        HorizontalPanel hpanelLbTb = new HorizontalPanel();
        HorizontalPanel hpanelBut = new HorizontalPanel();
        
        Label delLb = new Label("Delete by ISBN short:");
        final TextBox delTex = new TextBox();
        
        delTex.setStyleName("minidialog");
        delTex.setWidth("100px");
        
        Button delBut = new Button("Delete");
        Button cancel = new Button("Cancel");
        
        delBut.setStyleName("butons");
        cancel.setStyleName("butons");
        
        final AsyncCallback<String> callback = new AsyncCallback<String>() {
            public void onSuccess(String result) {
                
                /*inlist.add(new GWTServiceUsageExample.AppData(res[0], res[1],
                            res[2],Integer.parseInt(res[3]),Integer.parseInt(res[4]),
                            new Date(12354) ,Double.parseDouble(res[6])));*/
                inlist.remove(new GWTServiceUsageExample.AppData("123", "123",
                            result,123,123, new Date() ,123));
                setVisible(false);
                setModal(false);
                
                delTex.setText("");
            }//end callback
            
            public void onFailure(Throwable caught) {
                //inlist.add(new GWTServiceUsageExample.AppData("fail", "fail"));
            }
        };

        // Listen for the button clicks
        delBut.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if(delTex.getText().length() != 8 || 
                        !(delTex.getText().matches("[0-9]+")))
                {
                    if(error == null)
                    {
                        error = new ErrorMes();
                        error.setMessage("the field can contain only numbers and "
                                + "the field length should be 8 characters");
                        error.center();
                        error.show();
                    }
                    error.center();
                    error.setVisible(true);
                    error.setModal(true);
                    return;
                }
               getService().myMethod("rem:"+delTex.getText(), callback);
            }
        });
        
        cancel.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                // Make remote call. Control flow will continue immediately and later
                // 'callback' will be invoked when the RPC completes.
                setVisible(false);
                setModal(false);
                delTex.setText("");
            }
        });
        
        
        hpanelLbTb.add(delLb);
        hpanelLbTb.add(delTex);
        hpanelLbTb.setStyleName("hpm");
        
        hpanelBut.add(delBut);
        hpanelBut.add(cancel);
        hpanelBut.setStyleName("hpm");
        
        panel.add(hpanelLbTb, DockPanel.CENTER);
        panel.add(hpanelBut, DockPanel.SOUTH);
        
        add(panel); 
        setText("Delete Book Dialog");
        addStyleName("minidialog");
        center();
    }
    
    
    
}
