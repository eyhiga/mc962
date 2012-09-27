import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        if(args.length != 3)
        {
            System.out.println("Usage: <exec> <number_of_events> <mean_time> <mean_size>");
        }
        else
        {
            int numberOfEvents = Integer.parseInt(args[0]);
            //double seed = Double.parseDouble(args[1]);
            double mean_time = Double.parseDouble(args[1]);
            double mean_size = Double.parseDouble(args[2]);
            
            ArrayList<Packet> eventList = generateEvents(numberOfEvents, mean_time, mean_size);
            
            ArrayList<Double> waitList = new ArrayList<Double>();
            
            double currentTime = 0.0;
            double totalWaitTime = 0.0;
            double totalUsefulTime = 0.0;
            
            double delay = 0.0;
            
            for(Packet currentPacket : eventList)
            {
            	delay = 0.0;
            	
                double currentPacketArrivalTime = currentPacket.getArrivalTime();
                double currentPacketTransferTime = currentPacket.getTransferTime();
                
                //System.out.println(currentTime);
                
                // Pacote chegou e não precisou esperar
                if(currentTime <= currentPacketArrivalTime)
                {
                	delay = 0.0;
                	
                    currentTime = currentPacketArrivalTime;   
                }
                else // Pacote precisou esperar
                {
                
                	delay = currentTime - currentPacketArrivalTime;
                	
                    totalWaitTime += delay;
                    
                    //currentTime += delay;
                }
                
                waitList.add(delay);
                
                currentTime += currentPacketTransferTime;
                totalUsefulTime += currentPacketTransferTime;
                
                //System.out.println("Tempo de chegada: " + currentPacketArrivalTime + " / Tempo de transferencia: " + currentPacketTransferTime);
                //System.out.println(delay);
                //System.out.println(currentTime);
            }
            
            List<Double> waitListWithoutTransient = removeTransient(waitList);
            
            for(Double currentWait : waitListWithoutTransient)
            {
            	System.out.println(currentWait);
            }
            
            //System.out.println(currentTime);
            
            /*System.out.println("# de eventos: " + numberOfEvents);
            System.out.println("Seed: " + seed);
            System.out.println("Media tempo: " + mean_time);
            System.out.println("Media tamanho: " + mean_size);
            System.out.println("Tempo total: " + currentTime);
            System.out.println("Tempo de utilizacao: " + totalUsefulTime);
            System.out.println("Tempo de espera: " + totalWaitTime);*/
        }
    }

    private static ArrayList<Packet> generateEvents(int numberOfEvents, double mean_time, double mean_size)
    {
        ArrayList<Packet> eventList = new ArrayList<Packet>();
        
        //RandomGenerator random = new RandomGenerator(1.0);
		//ExponentialDistribution exp = new ExponentialDistribution(1.0);
		RandomGenerator random_time = new RandomGenerator();
		ExponentialDistribution exp_time = new ExponentialDistribution(mean_time);
		
		RandomGenerator random_size = new RandomGenerator();
		ExponentialDistribution exp_size = new ExponentialDistribution(mean_size);
        
        Packet newPacket;
        double arrivalTime;
        double lastArrivalTime = 0.0;
        double size;
        
        for(int i=0; i<numberOfEvents; i++)
        {
            arrivalTime = exp_time.generateExponentialValue(random_time.generateRandom());
            arrivalTime += lastArrivalTime;
            lastArrivalTime = arrivalTime;
            
            size = exp_size.generateExponentialValue(random_size.generateRandom());
            
            newPacket = new Packet(i, arrivalTime, size);
            
            eventList.add(newPacket);
        }        
        
        
        return eventList;
    }
    
    private static List<Double> removeTransient(ArrayList<Double> waitList)
    { 	
        
        List<Double> newWaitList = new ArrayList<Double>();
        
    	if(waitList.size() > 1)
    	{
    	    ArrayList<Double> eventListAux;
    	    int pos = 1;
    	    for(int i=1; i>waitList.size(); i++)
    	    {
    	        Double currentWait = waitList.get(i);
    	        
                List<Double> waitListAux = waitList.subList(i, waitList.size());
                
                Double max = getMax(waitListAux);
                Double min = getMin(waitListAux);
                
                if(currentWait != max && currentWait != min)
                {
                	pos = i;
                	break;
                }
                
    	    }
    	    
    	    newWaitList = waitList.subList(pos, waitList.size());
    	    
    	}
    	else
    	{
    	    newWaitList = waitList;
    	}
    	
    	return newWaitList;	
    }
    
    private static Double getMax(List<Double> waitList)
    {
        Double max = Double.MAX_VALUE;
        
        for(Double currentWait : waitList)
        {
            if(currentWait > max)
            {
                max = currentWait;
            }
        }
        
        return max;
    }
    
    private static Double getMin(List<Double> waitList)
    {
        Double min = Double.MIN_VALUE;
        
        for(Double currentWait : waitList)
        {
            if(currentWait < min)
            {
                min = currentWait;
            }
        }
        
        return min;
    }
    
    /*private static ArrayList<Packet> copyEventList(List<Packet> eventList)
    {
        ArrayList<Packet> newEventList = new ArrayList<Packet>();
        Packet currentPacket;
        
        for(Packet packet : eventList)
        {
            currentPacket = new Packet(packet.getID(), packet.getArrivalTime(), packet.getTransferTime());
            newEventList.add(currentPacket);
        }
        
        return newEventList;
    }*/
    
}
