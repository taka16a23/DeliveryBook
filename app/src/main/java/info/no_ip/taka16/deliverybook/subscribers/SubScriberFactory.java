package info.no_ip.taka16.deliverybook.subscribers;


import java.util.ArrayList;
import java.util.List;

import info.no_ip.taka16.deliverybook.subscribers.Subscriber;

public class SubScriberFactory {

    private static List<Subscriber> Subscribers = new ArrayList<Subscriber>() {
        {
            add(new Subscriber("name", "displayName", "滋賀県大津市下阪本6丁目", null));
            add(new Subscriber("山田花子", null, "滋賀県大津市里4丁目6-9", null));
            add(new Subscriber("name2", "滋賀太郎", "滋賀県大津市里1丁目1-1", null));
            add(new Subscriber("name3", null, "滋賀県大津市里1丁目1-2", null));
        }
    };

    static int countSubscribers(){
        return Subscribers.size();
    }

    static Subscriber getSubscriber(int id){
        return Subscribers.get(id);
    }
}
