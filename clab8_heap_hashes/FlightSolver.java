package clab8_heap_hashes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.lang.Integer;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {
	Comparator<Flight> minStartComp = (o1, o2) -> (o1.startTime() - o2.startTime());
	Comparator<Flight> minEndComp = (o1, o2) -> (o1.endTime() - o2.endTime());
	PriorityQueue<Flight> minStartPQ;
	PriorityQueue<Flight> minEndPQ;

    public FlightSolver(ArrayList<Flight> flights) {
        
    	minStartPQ = new PriorityQueue<>(flights.size(), minStartComp);
    	minEndPQ = new PriorityQueue<>(flights.size(), minEndComp);
    	for(Flight x: flights) {
    		minStartPQ.add(x);
    		minEndPQ.add(x);
    	}
    }

    public int solve() {
        int maxVal = 0;
        int curr = 0;
        while(minStartPQ.size() >0 && minEndPQ.size() > 0){
        	int start = minStartPQ.peek().startTime();
        	int end = minEndPQ.peek().endTime();
        	if(start <= end) {
        		Flight f = minStartPQ.remove();
        		curr += f.passengers();
        	}else {
        		Flight f = minEndPQ.remove();
        		curr -= f.passengers();
        	}
        	
        	maxVal = Integer.compare(maxVal, curr);
        	
        }
        return maxVal;
    }

}
