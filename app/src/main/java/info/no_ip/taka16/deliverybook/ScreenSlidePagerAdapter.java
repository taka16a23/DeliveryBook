package info.no_ip.taka16.deliverybook;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentStatePagerAdapter;

import java.util.List;


public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
//    private static final int NUM_PAGES = SubScriberFactory.countSubscribers();
    private Context context;
    private int num_pages;
    private List<Subscriber> subscribers;

    public ScreenSlidePagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        subscribers = new SubscriberDbTable(this.context).findAll();
        num_pages = subscribers.size();
    }

    @Override
    public Fragment getItem(int position) {
        return ScreenSlidePageFragment.create(subscribers.get(position), position);
    }

    @Override
    public int getCount() {
        return num_pages;
    }

}
