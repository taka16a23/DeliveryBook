package info.no_ip.taka16.deliverybook.book;

import android.content.Context;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;



public class BookRepository {

    private static final String LOG_TAG = info.no_ip.taka16.deliverybook.book.Book.class.getSimpleName();

    private Context context;

    public BookRepository(Context context){
        this.context = context;
    }

    public void register(Book book){
    }

    public void unregister(Book book){

    }

    public Book getBook(String areaName){
        return null;
    }

    public boolean hasBook(String areaName){
        return false;
    }

    public boolean hasBook(Book book){
        return hasBook(book.getAreaName());
    }

    public List<Book> listBook(){
        List<Book> books = new ArrayList<Book>();
        return books;
    }

    public void deleteAll(){
    }

}
