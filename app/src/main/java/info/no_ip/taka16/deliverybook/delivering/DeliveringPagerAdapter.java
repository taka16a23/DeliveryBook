package info.no_ip.taka16.deliverybook.delivering;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentStatePagerAdapter;

import info.no_ip.taka16.deliverybook.book.Book;
import info.no_ip.taka16.deliverybook.book.BookRepository;


public class DeliveringPagerAdapter extends FragmentStatePagerAdapter {
    private Context context;
    private Book book;
    private String areaName;

    public DeliveringPagerAdapter(Context context, FragmentManager fm, String areaName) {
        super(fm);
        this.context = context;
        this.areaName = areaName;
        book = (new BookRepository(this.context)).getBook(this.areaName);
    }

    @Override
    public Fragment getItem(int position) {
        return DeliveringPageFragment.create(
                book.getFrame(position), new Position(getCount(), position));
    }

    @Override
    public int getCount() {
        return book.size();
    }

}
