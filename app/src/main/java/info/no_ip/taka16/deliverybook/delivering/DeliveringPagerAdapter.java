package info.no_ip.taka16.deliverybook.delivering;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentStatePagerAdapter;

import java.util.List;

import info.no_ip.taka16.deliverybook.subscribers.Subscriber;
import info.no_ip.taka16.deliverybook.subscribers.SubscribersBook;


public class DeliveringPagerAdapter extends FragmentStatePagerAdapter {
    private Context context;
    private Position mPosition;
    private List<Subscriber> subscribers;

    public DeliveringPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        subscribers = new SubscribersBook(this.context).findAll();
        this.mPosition = new Position(subscribers.size());
    }

    @Override
    public Fragment getItem(int position) {
        mPosition.setCurrentPosition(position);
        return DeliveringPageFragment.create(subscribers.get(position), mPosition);
    }

    @Override
    public int getCount() {
        return mPosition.getMaxPosition();
    }

}
