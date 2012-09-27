public class Packet
{
    private Double arrivalTime;
    private Double transferTime;
    private int id;

    public Packet(int id, Double arrivalTime, Double transferTime)
    {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.transferTime = transferTime;
    }

    public Double getArrivalTime()
    {
        return arrivalTime;
    }

    /*public Double getSize()
    {
        return size;
    }*/
    
    public Double getTransferTime()
    {
        return transferTime;
    }
    
    public int getID()
    {
        return id;
    }

}
