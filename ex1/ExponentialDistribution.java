public class ExponentialDistribution
{

    private Double mean;

    public ExponentialDistribution(Double mean)
    {
        this.mean = mean;
    }

    public Double generateExponentialValue(Double u)
    {
        return -mean * Math.log(u);
    }
}

