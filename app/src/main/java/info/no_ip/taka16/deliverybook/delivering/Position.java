package info.no_ip.taka16.deliverybook.delivering;


public class Position {
    private int maxPosition;
    private int currentPosition = 0;

    public Position(int maxPosition){
        this.maxPosition = maxPosition;
    }

    public Position(int maxPosition, int currentPosition){
        this(maxPosition);
        this.currentPosition = currentPosition;
    }

    public int getMaxPosition(){
        return maxPosition;
    }

    public int getCurrentPosition(){
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition){
        this.currentPosition = currentPosition;
    }

}
