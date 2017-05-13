package hiddenMarkovChain;

import java.util.LinkedList;

public class BaumWelchAlg {
	
	Matrix transitionMatrix;
	Matrix emissionMatrix;
	Status hiddenStatus;
	Status observedStatus;
	
	int length;
	public void setObservedStatus(Status obs){
		observedStatus = obs;
	}
	public void setTransitionMatrix(Matrix trans){
		transitionMatrix = trans;
	}
	public void setEmissionMatrix(Matrix emi){
		emissionMatrix = emi;
	}
	public void setHiddenStatus(Status hidd){
		hiddenStatus = hidd;
	}
	
	public BaumWelchAlg(){
		transitionMatrix = new Matrix();
		emissionMatrix = new Matrix();
		hiddenStatus = new Status();
		observedStatus = new Status();
		
		//length of the observedStatus
		length = observedStatus.getSize();
		
	}
	
	public LinkedList<Double>[] forwardAlg(double pi0, double pi1){
		double ak0, ak1;
		double trans0, trans1;
	
		LinkedList[] temp = new LinkedList[2];
		LinkedList<Double> ak0List = new LinkedList<Double>(); //hidden state x = 0
		LinkedList<Double> ak1List = new LinkedList<Double>(); //hidden state x = 1	
		
		//calculate ak(0)
		ak0 = pi0 * emissionMatrix.getEntry(0, observedStatus.getNth(0));
		ak0List.add(ak0); // store in the list
		
		//calculate ak(1)
		ak1 = pi1 * emissionMatrix.getEntry(1, observedStatus.getNth(0));
		ak1List.add(ak1);
		
		for(int i = 1; i < length; i++){
			
			// (ak(0) * T0,0 + ak(1) * T1,0)
			trans0 = ak0 * transitionMatrix.getEntry(0, 0) 
					+ ak1 * transitionMatrix.getEntry(1, 0);
			
			// (ak(0) * T0,1 + ak(1) * T1,1)
			trans1 = ak0 * transitionMatrix.getEntry(0, 1) 
					+ ak1 * transitionMatrix.getEntry(1, 1);
			
			ak0 = emissionMatrix.getEntry(0, observedStatus.getNth(i)) * trans0;
			ak0List.add(ak0); //store
			
			ak1 = emissionMatrix.getEntry(1, observedStatus.getNth(i)) * trans1;
			ak1List.add(ak1);
			
			
			
		}
		

		temp[0] = ak0List;
		temp[1] = ak1List;
		return temp;
	}
	
	public LinkedList<Double>[] backwardAlg(){
		
		double bk0, bk1;
		double trans0, trans1;
		
		LinkedList[] temp = new LinkedList[2];
		LinkedList<Double>bk0List = new LinkedList<Double>();
		LinkedList<Double>bk1List = new LinkedList<Double>();
		
		bk0 = 1;
		bk0List.add(bk0);
		
		bk1 = 1;
		bk1List.add(bk1);
		
		for (int i = length - 1; i >= 0; i--){
			
			//(bk+1(0) * T0,0 * M0,zk+1)
			trans0 = bk0 * transitionMatrix.getEntry(0, 0) 
				* emissionMatrix.getEntry(0, observedStatus.getNth(i));
			trans0 += bk1 * transitionMatrix.getEntry(0, 1) 
				* emissionMatrix.getEntry(1, observedStatus.getNth(i));
			
			trans1 = bk0 * transitionMatrix.getEntry(1, 0) 
				* emissionMatrix.getEntry(0, observedStatus.getNth(i));
			trans1 += bk1 * transitionMatrix.getEntry(1, 1) 
				* emissionMatrix.getEntry(1, observedStatus.getNth(i));
			
			bk0 = trans0;
			bk0List.add(bk0);
			
			bk1 = trans1;
			bk1List.add(bk1);
		}
		
		
		temp[0] = bk0List;
		temp[1] = bk1List;
		return temp;
	}
	
	public LinkedList smoothing(LinkedList<Double> aks, 
							LinkedList<Double> bks, double sumats){
		double yks;
		LinkedList<Double> yksList = new LinkedList<Double>();
		
		for(int i = 0; i < aks.size(); i++){
			yks = aks.get(i) * bks.get(aks.size() - i - 1) / sumats;
			yksList.add(yks);
		} 
		return yksList;
	}

	public double sks(LinkedList<Double> aks, 
							LinkedList<Double> bks, int q, int s, int k){
		if(k >= length){
			System.out.print("K out of range");
			return -1;
		}
		double sks;
		sks = aks.get(k) * transitionMatrix.getEntry(q,s) 
			* emissionMatrix.getEntry(s, observedStatus.getNth(k+1)) * bks.get(k+1);
		return sks;
	}
}
