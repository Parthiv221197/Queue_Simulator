
import java.lang.*;

public class QueueSimulator{
  public enum Event { ARRIVAL, DEPARTURE };
  private double currTime;
  private double arrivalRate;
  private double serviceTime;
  private double timeForNextArrival;
  private double timeForNextDeparture;
  private double totalSimTime;
  LinkedListQueue<Data> buffer = new LinkedListQueue<Data>();
  LinkedListQueue<Data> eventQueue = new LinkedListQueue<Data>();
  private Event e;
  private Data d;
  
  public double getRandTime(double arrivalRate){
    double num, time1, max=1, min=0, randNUM;
    randNUM= Math.random();
    time1= (-1/arrivalRate) * (Math.log(1-randNUM));
    //System.out.println(time1);
    return time1;
  }
  
  public QueueSimulator(double aR, double servT, double simT){
    //Initialising everything in the constructor
    this.arrivalRate = aR;
    this.serviceTime = servT;
    this.totalSimTime = simT;
    this.timeForNextArrival = getRandTime(aR);
    this.timeForNextDeparture = this.serviceTime + this.timeForNextArrival;
  }
  
  public double calcAverageWaitingTime(){
    //Since Average time is total time divided by total size
    double time = 0;
    int size = this.eventQueue.size();
    while(!eventQueue.isEmpty()){
      d = this.eventQueue.dequeue();
      time += (d.departureTime - d.arrivalTime);
    }
    return time/size;
  }
  
  public double runSimulation(){
    //loop till the total simulation time
    while(this.currTime <= this.totalSimTime){

      //If the time for next arrival is more than the time for departure the packet will depart first so event will be departure and current time would be time for next departure.
      if(this.timeForNextArrival > this.timeForNextDeparture){
        e = Event.DEPARTURE;
        this.currTime = this.timeForNextDeparture;
      }
      //else the event would be arrival and the current time would be the time for next arrival
      else{
        e = Event.ARRIVAL;
        this.currTime = this.timeForNextArrival;
      }

      //If the event is arrival then Inserting the data packet in buffer queue and changing the current time to arrival time of data packet
      //We also increase the time for the arrival of the next packet
      if(e == Event.ARRIVAL){
        d = new Data();
        buffer.enqueue(d);
        d.arrivalTime = this.currTime;
        this.timeForNextArrival += this.getRandTime(arrivalRate);
      }

      //If the event is departure then we dequeue data from buffer and add it to eventQueue and set the current time equal to the departure time of the packet
      //We update the time for next departure on the basis of remaining packets in buffer
      //If there are packets left in the buffer we process them so the time when next departure occurs would be the current departure time + service time
      //If the buffer is empty then we have to wait until the next packet arrives so the time for next departure then would be the time for next arrival + service time
      else{
        d = buffer.dequeue();
        eventQueue.enqueue(d);
        d.departureTime = this.currTime;
        if(!buffer.isEmpty()){
          this.timeForNextDeparture += this.serviceTime;
        }
        else{
          this.timeForNextDeparture = this.timeForNextArrival + this.serviceTime;
        }

      }


    }
    return this.calcAverageWaitingTime();

  }
}






