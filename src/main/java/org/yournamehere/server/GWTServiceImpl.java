package org.yournamehere.server;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.yournamehere.client.gewgwt.GWTService;
import org.yournamehere.client.gewgwt.GWTServiceUsageExample;
import org.yournamehere.client.gewgwt.GWTServiceUsageExample.AppData;

public class GWTServiceImpl extends RemoteServiceServlet implements GWTService {

    private static List<GWTServiceUsageExample.AppData> books;

    private String path;

    @Override
    public String myMethod(String s) {
        if (path == null) {
            path = getServletContext().getRealPath("books");
        }
        String[] strs;
        String[] res;

        if (s.contains("{")) {
            String[] str = {"rem", s};
            strs = str;
            res = str;
        } else {
            strs = s.split(":");
            res = strs[1].split("&");
        }
        if (strs[0].equals("add")) {
            if (books == null) {
                books = getData(path);
            }

            books.add(new AppData(res[1], res[2], res[0],
                    Integer.parseInt(res[3]), Integer.parseInt(res[4]),
                    new Date(), Double.parseDouble(res[6])));
        }//end add if

        if (strs[0].equals("edt")) {
            if (books == null) {
                books = getData(path);
            }

            AppData app = books.get(books.indexOf(new AppData("123", "123", res[0],
                    Integer.parseInt("123"), Integer.parseInt("123"),
                    new Date(), Double.parseDouble("123"))));
            AppData appNew = new AppData(res[1], res[2], res[0],
                    Integer.parseInt(res[3]), Integer.parseInt(res[4]),
                    new Date(Long.parseLong(res[5])), Double.parseDouble(res[6]));
            Collections.replaceAll(books, app, appNew);
        }//end add if

        if (strs[0].equals("rem")) {
            if (books == null) {
                books = getData(path);
            }

            org.json.JSONObject js = new org.json.JSONObject(s);
            books.remove(new AppData("123", "123", 
                    js.getString("rem"),
                    Integer.parseInt("123"), Integer.parseInt("123"),
                    new Date(), Double.parseDouble("123")));
            return s;
        }//end remove if

        if (strs[0].equals("int")) {
            if (books == null) {
                books = getData(path);
            }

            StringBuffer writeStr = new StringBuffer();
            for (GWTServiceUsageExample.AppData book : books) {
                /*AppData(String name, String author, String isbn, int pages, int age, Date date, double price)*/
                writeStr.append(book.getName());
                writeStr.append("&");
                writeStr.append(book.getAuthor());
                writeStr.append("&");
                writeStr.append(book.getIsbn());
                writeStr.append("&");
                writeStr.append(book.getPages());
                writeStr.append("&");
                writeStr.append(book.getAge());
                writeStr.append("&");
                writeStr.append(book.getDate().getTime());
                writeStr.append("&");
                writeStr.append(book.getPrice());
                writeStr.append("\n");
            }

            return writeStr.toString();

        }//end init if

        if (strs[0].equals("srt")) {
            if (books == null) {
                books = getData(path);
            }

            if (strs[1].equals("isbn")) {
                Comparator<GWTServiceUsageExample.AppData> comp = new Comparator<GWTServiceUsageExample.AppData>() {
                    @Override
                    public int compare(AppData o1, AppData o2) {
                        return o1.getIsbn().compareTo(o2.getIsbn());
                    }
                };
                Collections.sort(books, comp);
            }
            if (strs[1].equals("name")) {
                Comparator<GWTServiceUsageExample.AppData> comp = new Comparator<GWTServiceUsageExample.AppData>() {
                    @Override
                    public int compare(AppData o1, AppData o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                };
                Collections.sort(books, comp);
            }
            if (strs[1].equals("author")) {
                Comparator<GWTServiceUsageExample.AppData> comp = new Comparator<GWTServiceUsageExample.AppData>() {
                    @Override
                    public int compare(AppData o1, AppData o2) {
                        return o1.getAuthor().compareTo(o2.getAuthor());
                    }
                };
                Collections.sort(books, comp);
            }
            if (strs[1].equals("pages")) {
                Comparator<GWTServiceUsageExample.AppData> comp = new Comparator<GWTServiceUsageExample.AppData>() {
                    @Override
                    public int compare(AppData o1, AppData o2) {
                        return o1.getPages() - o2.getPages();
                    }
                };
                Collections.sort(books, comp);
            }
            if (strs[1].equals("age")) {
                Comparator<GWTServiceUsageExample.AppData> comp = new Comparator<GWTServiceUsageExample.AppData>() {
                    @Override
                    public int compare(AppData o1, AppData o2) {
                        return o1.getAge() - o2.getAge();
                    }
                };
                Collections.sort(books, comp);
            }
            if (strs[1].equals("date")) {
                Comparator<GWTServiceUsageExample.AppData> comp = new Comparator<GWTServiceUsageExample.AppData>() {
                    @Override
                    public int compare(AppData o1, AppData o2) {
                        return (int) (o1.getDate().getTime() - o2.getDate().getTime());
                    }
                };
                Collections.sort(books, comp);
            }
            if (strs[1].equals("price")) {
                Comparator<GWTServiceUsageExample.AppData> comp = new Comparator<GWTServiceUsageExample.AppData>() {
                    @Override
                    public int compare(AppData o1, AppData o2) {
                        return (int) (o1.getPrice() - o2.getPrice());
                    }
                };
                Collections.sort(books, comp);
            }
            StringBuffer writeStr = new StringBuffer();
            for (GWTServiceUsageExample.AppData book : books) {
                /*AppData(String name, String author, String isbn, int pages, int age, Date date, double price)*/
                writeStr.append(book.getName());
                writeStr.append("&");
                writeStr.append(book.getAuthor());
                writeStr.append("&");
                writeStr.append(book.getIsbn());
                writeStr.append("&");
                writeStr.append(book.getPages());
                writeStr.append("&");
                writeStr.append(book.getAge());
                writeStr.append("&");
                writeStr.append(book.getDate().getTime());
                writeStr.append("&");
                writeStr.append(book.getPrice());
                writeStr.append("\n");
            }

            return writeStr.toString();

        }//end init if

        setData(path, books);
        return strs[1];
    }//end myMethod

    private static List<GWTServiceUsageExample.AppData> getData(String path) {
        List<GWTServiceUsageExample.AppData> lstBooks = new ArrayList<>();

        try (Scanner sc = new Scanner(Paths.get(path), "UTF-8")) {

            while (sc.hasNextLine()) {
                String[] res = sc.nextLine().split("&");
                lstBooks.add(new AppData(res[1], res[2], res[0],
                        Integer.parseInt(res[3]), Integer.parseInt(res[4]),
                        new Date(Long.parseLong(res[5])), Double.parseDouble(res[6])));
            }

            return lstBooks;
        } catch (IOException e) {

            e.printStackTrace();
        }

        return null;
    }

    private static void setData(String path, List<GWTServiceUsageExample.AppData> books) {

        try (FileWriter writer = new FileWriter(path, false)) {
            StringBuffer writeStr = new StringBuffer();
            for (GWTServiceUsageExample.AppData book : books) {
                /*AppData(String name, String author, String isbn, int pages, int age, Date date, double price)*/
                writeStr.append(book.getName());
                writeStr.append("&");
                writeStr.append(book.getAuthor());
                writeStr.append("&");
                writeStr.append(book.getIsbn());
                writeStr.append("&");
                writeStr.append(book.getPages());
                writeStr.append("&");
                writeStr.append(book.getAge());
                writeStr.append("&");
                writeStr.append(book.getDate().getTime());
                writeStr.append("&");
                writeStr.append(book.getPrice());
                writeStr.append("\n");
            }

            writer.write(writeStr.toString());
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }

    }//end setData

}//end Servlet
