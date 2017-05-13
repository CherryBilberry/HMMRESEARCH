package hiddenMarkovChain;

import java.util.LinkedList;

public class Status {
	LinkedList<Integer> Status;
	
	public Status(){
		Status = new LinkedList<Integer>();
	}
	
	public void insert(Integer Xk){
		Status.add(Xk);
	}
	
	public int getNth(int index){
		return Status.get(index);
	}
	
	public void printStatus(){
		for(int i = 0; i < Status.size(); i++) System.out.print(Status.get(i));
		System.out.println();
	}
	
	public int getSize(){
		return Status.size();
	}
}
