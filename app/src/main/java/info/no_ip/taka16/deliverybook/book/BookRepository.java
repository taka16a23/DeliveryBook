package info.no_ip.taka16.deliverybook.book;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import info.no_ip.taka16.deliverybook.book.root.Root;
import info.no_ip.taka16.deliverybook.book.root.RootRepository;


public class BookRepository {

    private static final String LOG_TAG = info.no_ip.taka16.deliverybook.book.Book.class.getSimpleName();

    private Context context;
    private RootRepository rootRepository;

    public BookRepository(Context context){
        this.context = context;
        this.rootRepository = new RootRepository(context);
    }

    public Book getBook(String areaName){
        if(areaName == null){
            throw new NullPointerException();
        }
        if(areaName.equals("")){
            throw new IllegalArgumentException();
        }
        Root root = rootRepository.getRoot(areaName);
        return new Book(context, root);
    }

    public void removeBook(Book book){
        removeBook(book.getAreaName());
    }

    public void removeBook(String areaName){
        rootRepository.removeRoot(areaName);
    }

    public boolean hasBook(String areaName){
        return rootRepository.listAreaNames().contains(areaName);
    }

    public boolean hasBook(Book book){
        return hasBook(book.getAreaName());
    }

    public List<Book> listBook(){
        List<Book> books = new ArrayList<Book>();
        for (String areaName : rootRepository.listAreaNames()) {
            books.add(getBook(areaName));
        }
        return books;
    }

    public void deleteAll(){
    }

    public boolean isEmpty(){
        return listBook().isEmpty();
    }

}
