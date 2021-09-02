import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DualConverter {
	
	private BufferedWriter exit;
	public DualConverter(Double[][]Anew,int row,int col,ArrayList<Double> b,ArrayList<Double> c,ArrayList<Integer> Eqin,int MinMax) {
		//Opening the file which the Dual problem will be written
		File output = new File("Output/DualLp.txt");
		try {
			exit =  new BufferedWriter(new FileWriter(output));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Writing operation
		try {
			//Writing the objective function of the dual
			DualObj(b,MinMax);
			//Writing the technological Constraints of the dual
			DualTechCon(Anew,row,col,c,MinMax);
			//Writing the constraints for each variable
			DualVars(Eqin,MinMax);
	
			exit.close();
		}
		catch(IOException e){
			e.printStackTrace();
			
		}	
	}
	
	public void DualObj(ArrayList<Double> b,int MinMax) {
		try {
			//Checking primal problem's type
		if(MinMax==-1) {
			exit.write("max ");
			
		}
		else {
			exit.write("min ");
		}
		for (int i=0;i<b.size();i++) {
			//Checking the number of the variable in the loop in order 
			//to avoid putting the + sign in case it is the first variable
			if(i>0 && b.get(i)>0) {
				if(b.get(i)==1.0) {       // We also check the existence of number "1" in order to avoid writing it in the file
					exit.write("w"+(i+1) +" ");
				}
				else if(b.get(i)==-1.0) {
					exit.write("-w"+(i+1) +" ");
				}
				else {
			   exit.write("+" + b.get(i)+ "w"+(i+1) +" ");}
			   }
			else {
				if(b.get(i)==1.0) {
					exit.write("w"+(i+1) +" ");
				}
				else if(b.get(i)==-1.0) {
					exit.write("-w"+(i+1) +" ");
				}
				else {
				    exit.write(b.get(i)+ "w"+(i+1) +" ");}
			}
		  }
			exit.write("\n s.t   ");
	    }
		catch(IOException e){
			e.printStackTrace();
		}
		
 }
	
	public void DualTechCon(Double[][]Anew,int row,int col,ArrayList<Double> c,int MinMax){

		try {
		if(MinMax==-1) {     // Checking the primal problem's type in order to act accordingly
			// Same process as above for the variables
			for(int i1=0;i1<col;i1++) {
				for(int j=0;j<row;j++) {
						if(j>0 && Anew[j][i1]>0) {
							if(Anew[j][i1]==1.0) {
								exit.write("w"+(j+1) +" ");
							}
							else if(Anew[j][i1]==-1.0) {
								exit.write("-w"+(j+1) +" ");
							}
							else {
							exit.write("+"+Anew[j][i1]+"w"+(j+1)+ " ");}
						}
						else if(Anew[j][i1]==0) {
							exit.write("    ");
						}
						else {
							if(Anew[j][i1]==1.0) {
								exit.write("w"+(j+1) +" ");
							}
							else if(Anew[j][i1]==-1.0) {
								exit.write("-w"+(j+1) +" ");
							}
							else {
							exit.write(Anew[j][i1]+"w"+(j+1)+ " ");}
						}
						
					
					if(j==row-1) {
						exit.write("<= "+ c.get(i1)+"\n        ");
					}
				}
				
			}
		}
		else {
			for(int i1=0;i1<col;i1++) {
				for(int j=0;j<row;j++) {
						if(j>0 && Anew[j][i1]>0) {
							if(Anew[j][i1]==1.0) {
								exit.write("+w"+(j+1) +" ");
							}
							else if(Anew[j][i1]==-1.0) {
								exit.write("-w"+(j+1) +" ");
							}
							else {
							exit.write("+"+Anew[j][i1]+"w"+(j+1)+ " ");}
						}

						else if(Anew[j][i1]==0) {
							exit.write("    ");
						}
						else {
							if(Anew[j][i1]==1.0) {
								exit.write("w"+(j+1) +" ");
							}
							else if(Anew[j][i1]==-1.0) {
								exit.write("-w"+(j+1) +" ");
							}
							else {
							exit.write(Anew[j][i1]+"w"+(j+1)+ " ");}
						}
					
					if(j==row-1) {
						exit.write("=> "+ c.get(i1)+"\n       ");
					}
				}
				
			}
			
			
		}
	  }
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void DualVars(ArrayList<Integer> Eqin,int MinMax) {
		try {
			//Checking the Eqin of the primal problem in order to convert to the proper variable constraints
			//i the dual problem
			for(int i=0;i<Eqin.size();i++) {
				if(MinMax==-1) {
					if(Eqin.get(i)==-1) {
						exit.write("w"+(i+1)+"<=0");
					}
					else if(Eqin.get(i)==1){
						exit.write("w"+(i+1)+"=>0");
					}
					else {
						exit.write("w"+(i+1)+" ελεύθερη");
					}
					
					if(i!=(Eqin.size()-1)) {
						exit.write(", ");
					}
					
				}
				else {
					if(Eqin.get(i)==-1) {
						exit.write("w"+(i+1)+"=>0");
					}
					else if(Eqin.get(i)==1){
						exit.write("w"+(i+1)+"<=0");
					}
					else {
						exit.write("w"+(i+1)+" ελεύθερη");
					}
					
					if(i!=(Eqin.size()-1)) {
						exit.write(", ");
					}
				}
				
			}
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}
