package info.no_ip.taka16.deliverybook;


public class Subscriber {


    private String mName = "";
    private String mDisplayName;
    private String mAddress = "";
    private String mDisplayAddress;

    public Subscriber(String name, String displayName, String address, String displayAddress){
        // TODO: null check
        //mName = name;
        setName(name);
        mDisplayName = displayName;
        mDisplayAddress = displayAddress;
        mAddress = address;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        if (mName == null){
            throw new NullPointerException("Name not acceptable null.");
        }
        this.mName = mName;
    }

    public String getDisplayName(){
        if (mDisplayName == null || "".equals(mDisplayName)){
            return mName;
        }
        return mDisplayName;
    }

    public void setDisplayName(String displayName){
        mDisplayName = displayName;
    }

    public String getDisplayAddress(){
        return mAddress;
    }
}
