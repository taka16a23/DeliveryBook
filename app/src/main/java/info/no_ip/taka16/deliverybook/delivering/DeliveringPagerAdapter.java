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

    public DeliveringPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        book = (new BookRepository(this.context)).getBook("11area");
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
