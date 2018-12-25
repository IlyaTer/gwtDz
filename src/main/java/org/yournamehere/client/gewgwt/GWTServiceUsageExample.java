/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.client.gewgwt;

import org.yournamehere.client.gewgwt.GWTServiceAsync;
import org.yournamehere.client.gewgwt.GWTService;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;

import com.google.gwt.user.client.rpc.AsyncCallback;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.DockPanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.view.client.ListDataProvider;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.yournamehere.client.del.DelDialog;
import org.yournamehere.client.add.JDialog;
import org.yournamehere.client.edit.EditDialog;
import org.yournamehere.client.sort.SortDialog;

/**
 * Example class using the GWTService service.
 *
 * @author mr_user
 */
public class GWTServiceUsageExample extends DockPanel {

    private Label lblServerReply = new Label();
    private TextBox txtUserInput = new TextBox();
    private Button btnSend = new Button("Send to server");
    private VerticalPanel vertPanel = new VerticalPanel();

    public JDialog miniDialog;
    public DelDialog delDialog;
    public SortDialog sortDialog;
    public EditDialog editDialog;

    public static class AppData extends Object {

        private String name;
        private String author;
        private String isbn;
        private int pages;
        private int age;
        private Date date;
        private double price;

        public AppData(String name, String author, String isbn, int pages, int age, Date date, double price) {
            this.name = name;
            this.author = author;
            this.isbn = isbn;
            this.pages = pages;
            this.age = age;
            this.date = date;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public String getAuthor() {
            return author;
        }

        public String getIsbn() {
            return isbn;
        }

        public int getPages() {
            return pages;
        }

        public int getAge() {
            return age;
        }

        public Date getDate() {
            return date;
        }

        public double getPrice() {
            return price;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof AppData) {
                return isbn.equals(((AppData) obj).isbn);
            }
            return false;
        }

        @Override
        public int hashCode() {
            int res = 17;

            res = 31 * res + isbn.hashCode();
            return res;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public void setPrice(double price) {
            this.price = price;
        }
        
        

    }//end AppData

    public GWTServiceUsageExample() {

        DataGrid<AppData> table = new DataGrid<AppData>();
        //add column in table
        TextColumn<AppData> textName = new TextColumn<AppData>() {
            @Override
            public String getValue(AppData object) {
                return object.getName();
            }

        };
        TextColumn<AppData> ISBN = new TextColumn<AppData>() {
            @Override
            public String getValue(AppData object) {
                return object.getIsbn();
            }

        };

        TextColumn<AppData> authorName = new TextColumn<AppData>() {
            @Override
            public String getValue(AppData object) {
                return object.getAuthor();
            }

        };

        TextColumn<AppData> pagesColumn = new TextColumn<AppData>() {
            @Override
            public String getValue(AppData object) {
                return object.getPages() + "";
            }

        };

        TextColumn<AppData> ageColumn = new TextColumn<AppData>() {
            @Override
            public String getValue(AppData object) {
                return object.getAge() + "";
            }

        };

        TextColumn<AppData> priceColumn = new TextColumn<AppData>() {
            @Override
            public String getValue(AppData object) {
                return object.getPrice() + "";
            }

        };

        DateCell dateCell = new DateCell();
        Column<AppData, Date> dateColumn = new Column<AppData, Date>(dateCell) {
            @Override
            public Date getValue(AppData object) {
                return object.date;
            }
        };

        ButtonCell buttonCell = new ButtonCell();
        Column<AppData, String> buttonColumn = new Column<AppData, String>(buttonCell) {
            @Override
            public String getValue(AppData object) {
                return "Delete";
            }
        };
        
        ButtonCell buttonEditCell = new ButtonCell();
        Column<AppData, String> buttonEditColumn = new 
            Column<AppData, String>(buttonEditCell) {
            @Override
            public String getValue(AppData object) {
                return "Edit";
            }
        };
        
        
       
        table.addColumn(ISBN, "ISBN short");
        table.addColumn(textName, "Book Name");
        table.addColumn(authorName, "Author");
        table.addColumn(pagesColumn, "Pages");
        table.addColumn(ageColumn, "Age");
        table.addColumn(dateColumn, "Add Date");
        table.addColumn(priceColumn, "Price");
        table.addColumn(buttonEditColumn, "Edit");
        table.addColumn(buttonColumn, "Delete");

        /*AppData(String name, String author, String isbn, int pages, int age, Date date, double price)*/
        final List<AppData> DATA = Arrays.asList();

        ListDataProvider<AppData> dataProvider = new ListDataProvider<AppData>();
        dataProvider.addDataDisplay(table);
        final List<AppData> list = dataProvider.getList();
        for (AppData data : DATA) {
            list.add(data);
        }
        /* Edit Data
        AppData app = new AppData("Test00000000010", object.author,
                    object.isbn, object.pages,object.age, 
                        object.date, object.price);
                Collections.replaceAll(list, object, app);
        */
        final AsyncCallback<String> callback = new AsyncCallback<String>() {
            public void onSuccess(String result) {
                
                /*inlist.add(new GWTServiceUsageExample.AppData(res[0], res[1],
                            res[2],Integer.parseInt(res[3]),Integer.parseInt(res[4]),
                            new Date(12354) ,Double.parseDouble(res[6])));*/
                JSONObject js = JSONParser.parseStrict(result).isObject();
                list.remove(new GWTServiceUsageExample.AppData("123", "123",
                            js.get("rem").isString().stringValue()
                            ,123,123, new Date() ,123));
            }//end callback
            
            public void onFailure(Throwable caught) {
                //inlist.add(new GWTServiceUsageExample.AppData("fail", "fail"));
            }
        };
        buttonColumn.setFieldUpdater(new FieldUpdater<AppData, String>() {
            @Override
            public void update(int index, AppData object, String value) {
                //to add remove code
                JSONObject js  = new JSONObject();
                js.put("rem", new JSONString(object.isbn));
                js.put("method", new JSONString("rem"));
                getService().myMethod(js.toString(), callback);
            }
        });
        
        buttonEditColumn.setFieldUpdater(new FieldUpdater<AppData, String>() {
            @Override
            public void update(int index, AppData object, String value) {
                if (editDialog == null) {
                    editDialog = new EditDialog(list);
                    editDialog.setApp(object);
                    editDialog.show();
                } else {
                    editDialog.setApp(object);
                    editDialog.center();
                    editDialog.setVisible(true);
                    editDialog.setModal(true);
                }
            }
        });

        final AsyncCallback<String> callbackInit = new AsyncCallback<String>() {
            public void onSuccess(String result) {
                
                JSONObject js = JSONParser.parseStrict(result).isObject();
                              
                JSONArray array = js.get("array").isArray();
                
                for(int i = 0; i < array.size(); i++)
                {
                    JSONObject jsArr  = array.get(i).isObject();
                    list.add(new GWTServiceUsageExample.AppData(
                        jsArr.get("name").isString().stringValue(),
                        jsArr.get("author").isString().stringValue(), 
                        jsArr.get("isbn").isString().stringValue(),
                        Integer.parseInt(jsArr.get("pages").isString().stringValue()),
                        Integer.parseInt(jsArr.get("age").isString().stringValue()), 
                        new Date(Long.parseLong(jsArr.get("date").isString().stringValue())), 
                        Double.parseDouble(jsArr.get("price").isString().stringValue())));
                }
            }

            public void onFailure(Throwable caught) {
                lblServerReply.setText("Communication failed");
            }
        };
        
        JSONObject js = new JSONObject();
        js.put("method", new JSONString("int"));
        getService().myMethod(js.toString(), callbackInit);

        DockLayoutPanel p = new DockLayoutPanel(Unit.PX);
        p.setStyleName("ptab");
        p.add(table);
        p.setSize("1200px", "700px");

        //-------------------------------------------        
        final Label lb = new Label("build with 2.8.1 add tab");

        vertPanel.add(new Label("Input your text: "));
        vertPanel.add(txtUserInput);
        vertPanel.add(btnSend);
        vertPanel.add(lblServerReply);

        // Create an asynchronous callback to handle the result.
        /* final AsyncCallback<String> callback = new AsyncCallback<String>() {
            public void onSuccess(String result) {
                lblServerReply.setText(result);
                lb.setText(result);
                list.add(new AppData("new" ,result));
            }
            
            public void onFailure(Throwable caught) {
                lblServerReply.setText("Communication failed");
            }
        };

        // Listen for the button clicks
        btnSend.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                // Make remote call. Control flow will continue immediately and later
                // 'callback' will be invoked when the RPC completes.
                getService().myMethod(txtUserInput.getText(), callback);
            }
        });*/
        //----------------------------------------
        HorizontalPanel horPan = new HorizontalPanel();

        Command cmd = new Command() {
            public void execute() {
                if (miniDialog == null) {
                    miniDialog = new JDialog(list);
                    miniDialog.show();
                } else {
                    miniDialog.center();
                    miniDialog.setVisible(true);
                    miniDialog.setModal(true);
                }
            }
        };

        Command cmde = new Command() {
            public void execute() {
                if (sortDialog == null) {
                    sortDialog = new SortDialog(list);
                    sortDialog.show();
                } else {
                    sortDialog.center();
                    sortDialog.setVisible(true);
                    sortDialog.setModal(true);
                }
            }
        };

        Command delCMD = new Command() {
            public void execute() {
                if (delDialog == null) {
                    delDialog = new DelDialog(list);
                    delDialog.show();
                } else {
                    delDialog.center();
                    delDialog.setVisible(true);
                    delDialog.setModal(true);
                }
            }
        };

        MenuBar menuBar = new MenuBar();
        MenuBar subMenu = new MenuBar();
        menuBar.addStyleName("menu");

        MenuItem addBook = new MenuItem("Add Book", cmd);
        MenuItem sortBook = new MenuItem("Sort Books by", cmde);
        MenuItem delBook = new MenuItem("Delete book by ISBN short", delCMD);
        addBook.addStyleName("menit");
        sortBook.addStyleName("menit");
        delBook.addStyleName("menit");

        menuBar.addItem("General menu", false, subMenu);
        menuBar.addItem(addBook);
        menuBar.addItem(sortBook);
       //menuBar.addItem(delBook);

        horPan.add(menuBar);
        add(horPan, DockPanel.NORTH);
        add(p, DockPanel.CENTER);
        //add(vertPanel, DockPanel.WEST);
        //add(lb, DockPanel.SOUTH);
    }

    public static GWTServiceAsync getService() {
        // Create the client proxy. Note that although you are creating the
        // service interface proper, you cast the result to the asynchronous
        // version of the interface. The cast is always safe because the
        // generated proxy implements the asynchronous interface automatically.

        return GWT.create(GWTService.class);
    }
}
