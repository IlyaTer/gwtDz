package org.yournamehere.client.add;

import org.yournamehere.client.gewgwt.GWTServiceUsageExample;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import java.util.List;
import java.util.Date;
import static org.yournamehere.client.gewgwt.GWTServiceUsageExample.getService;


public class JDialog extends DialogBox{
    
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
    
    private List<GWTServiceUsageExample.AppData> jlist;

    public JDialog(List<GWTServiceUsageExample.AppData> jlist) {
        
       this.jlist = jlist;
       final List<GWTServiceUsageExample.AppData> inlist = this.jlist;
       
       DockPanel panel = new DockPanel();
       VerticalPanel vpanel = new VerticalPanel();
       HorizontalPanel hpanelName = new HorizontalPanel();//название книги
       HorizontalPanel hpanelAuthor = new HorizontalPanel();//имя автора
       HorizontalPanel hpanelISBN = new HorizontalPanel();//ISBN
       HorizontalPanel hpanePageCount = new HorizontalPanel();//кол-во страниц
       HorizontalPanel hpaneAgeCreate = new HorizontalPanel();//год издания
       HorizontalPanel hpaneDateAddInDB = new HorizontalPanel();//дата добавления в базу
       HorizontalPanel hpanelPrice = new HorizontalPanel();//цена
       HorizontalPanel hpanelButtons = new HorizontalPanel();//кнопки
       
       
       
       Label lbISBN = new Label("ISBN:");
       Label lbPages = new Label("Pages:");
       Label lbAge = new Label("Age cr:");
       Label lbDate = new Label("Date add:");
       Label lbPrice = new Label("Price:");
       Label lbName = new Label("Name:");
       Label lbAuthor = new Label("Author:");
       
       lbName.setWidth("100px");
       lbAuthor.setWidth("100px");
       lbISBN.setWidth("100px");
       lbPages.setWidth("100px");
       lbDate.setWidth("100px");
       lbPrice.setWidth("100px");
       lbAge.setWidth("100px");
       
       
       
       final TextBox isbn = new TextBox();
       final TextBox pages = new TextBox();
       final TextBox age = new TextBox();
       final TextBox date = new TextBox();
       final TextBox price = new TextBox();
       final TextBox name = new TextBox();
       final TextBox author = new TextBox();
       
       name.setStyleName("minidialog");
       author.setStyleName("minidialog");
       isbn.setStyleName("minidialog");
       pages.setStyleName("minidialog");
       age.setStyleName("minidialog");
       date.setStyleName("minidialog");
       price.setStyleName("minidialog");
       
       name.setWidth("150px");
       author.setWidth("150px");
       isbn.setWidth("150px");
       pages.setWidth("150px");
       age.setWidth("150px");
       date.setWidth("150px");
       price.setWidth("150px");
       
       
       
       Button addBook = new Button("Add Book");
       Button cancel = new Button("Cancel");
       
       addBook.setStyleName("butons");
       cancel.setStyleName("butons");
       
       final AsyncCallback<String> callback = new AsyncCallback<String>() {
            public void onSuccess(String result) {
                
                String[] res = result.split("&");
                
                inlist.add(new GWTServiceUsageExample.AppData(res[1],res[2], res[0],
                        Integer.parseInt(res[3]),Integer.parseInt(res[4]), 
                        new Date(), Double.parseDouble(res[6])));
                setVisible(false);
                setModal(false);
                
                isbn.setText("");
                name.setText("");
                author.setText("");
                pages.setText("");
                age.setText("");
                price.setText("");
            }//end callback
            
            public void onFailure(Throwable caught) {
                //inlist.add(new GWTServiceUsageExample.AppData("fail", "fail"));
            }
        };

        
        addBook.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                
                if(!(isbn.getText().matches("[0-9]+")) ||
                        (isbn.getText().length() != 8))
                {
                    if(error == null)
                    {
                        error = new ErrorMes();
                        error.setMessage("ISBN Short: the field can contain only numbers and "
                                + "the field length should be 8 characters");
                        error.center();
                        error.show();
                    }
                    error.setMessage("ISBN Short: the field can contain only numbers and "
                                + "the field length should be 8 characters");
                    error.center();
                    error.setVisible(true);
                    error.setModal(true);
                    return;
                }
                
                if(name.getText().length() == 0)
                {
                    if(error == null)
                    {
                        error = new ErrorMes();
                        error.setMessage("Name: name lenght must be > 0");
                        error.center();
                        error.show();
                    }
                    error.setMessage("Name: name lenght must be > 0");
                    error.center();
                    error.setVisible(true);
                    error.setModal(true);
                    return;
                }
                if(author.getText().length() == 0)
                {
                    if(error == null)
                    {
                        error = new ErrorMes();
                        error.setMessage("Author: author lenght must be > 0");
                        error.center();
                        error.show();
                    }
                    error.setMessage("Author: author lenght must be > 0");
                    error.center();
                    error.setVisible(true);
                    error.setModal(true);
                    return;
                }
                if(!(pages.getText().matches("[0-9]+")) || Integer.parseInt(pages.getText()) <= 1)
                {
                    if(error == null)
                    {
                        error = new ErrorMes();
                        error.setMessage("Pages: the field can contain only numbers "
                                + "and the number of pages must be greater than 1");
                        error.center();
                        error.show();
                    }
                    error.setMessage("Pages: the field can contain only numbers "
                                + "and the number of pages must be greater than 1");
                    error.center();
                    error.setVisible(true);
                    error.setModal(true);
                    return;
                }
                if(!(age.getText().matches("[0-9]+")) || Integer.parseInt(age.getText()) > 2019 ||
                        Integer.parseInt(age.getText()) < 1900)
                {
                    if(error == null)
                    {
                        error = new ErrorMes();
                        error.setMessage("Age: age must be more 1900 and less 2019");
                        error.center();
                        error.show();
                    }
                    error.setMessage("Age: age must be more 1900 and less 2019");
                    error.center();
                    error.setVisible(true);
                    error.setModal(true);
                    return;
                }
                if(!(price.getText().matches("[0-9]+")) || Double.parseDouble(price.getText()) < 0)
                {
                    if(error == null)
                    {
                        error = new ErrorMes();
                        error.setMessage("Price: price must be more 0");
                        error.center();
                        error.show();
                    }
                    error.setMessage("Price: price must be more 0");
                    error.center();
                    error.setVisible(true);
                    error.setModal(true);
                    return;
                }
                        
                getService().myMethod("add:"+isbn.getText()+"&"+name.getText()+"&"+
                        author.getText()+"&"+pages.getText()+"&"+age.getText()+"&"+
                        date.getText()+"&"+price.getText(), callback);
            }//end addclick
        });
        
        cancel.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                setVisible(false);
                setModal(false);
                isbn.setText("");
                name.setText("");
                author.setText("");
                pages.setText("");
                age.setText("");
                price.setText("");
            }
        });
       
       
              
       
       hpanelISBN.add(lbISBN);
       hpanelISBN.add(isbn);
       hpanelISBN.setStyleName("hpm");
       
       hpanelName.add(lbName);
       hpanelName.add(name);
       hpanelName.setStyleName("hpm");
       
       hpanelAuthor.add(lbAuthor);
       hpanelAuthor.add(author);
       hpanelAuthor.setStyleName("hpm");
              
       hpanePageCount.add(lbPages);
       hpanePageCount.add(pages);
       hpanePageCount.setStyleName("hpm");
       
       hpaneAgeCreate.add(lbAge);
       hpaneAgeCreate.add(age);
       hpaneAgeCreate.setStyleName("hpm");
              
       hpanelPrice.add(lbPrice);
       hpanelPrice.add(price);
       hpanelPrice.setStyleName("hpm");
       
              
       
       vpanel.add(hpanelISBN);
       vpanel.add(hpanelName);
       vpanel.add(hpanelAuthor);
       vpanel.add(hpanePageCount);
       vpanel.add(hpaneAgeCreate);
       vpanel.add(hpanelPrice);
       
       
       hpanelButtons.add(addBook);
       hpanelButtons.add(cancel);
       
       panel.add(vpanel, DockPanel.CENTER);
       panel.add(hpanelButtons, DockPanel.SOUTH);
       
       add(panel);
       setText("Add Book Dialog");
       addStyleName("minidialog");
       center();
    }
 
    
}//end JDialog
