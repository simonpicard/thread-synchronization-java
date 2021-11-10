import java.util.ArrayList;
import java.util.List;

//Simon Picard | Info-f-202 | Projet Java | Graph.java

public class Graph{
	private List<Workstation> mWsList = null;
	private List<Workstation> mFirstWs = null;
	
	public Graph(){
		mWsList = new ArrayList<Workstation>();
		mFirstWs = new ArrayList<Workstation>();
	}
	
	public void addWsLink(Workstation ws1, Workstation ws2){
		//ajoute un lien de ws1 vers ws2
		ws1.addSuccessor(ws2);
		ws2.addInitiator(ws1);
	}
	
	public void addWorkstation(Workstation ws){
		mWsList.add(ws);
	}

	public boolean isReady(Workstation ws, Article o){
		if(ws.getLastObject() == o.getId()-1){
			//est pret a etre fait donc il faut que l'article le derniere article de la workstation soit l'article qui precede l'article pour lequel on fait le test
			return true;
		}
		else{
			return false;
		}
	}

	public boolean isDone(Workstation ws, Article o){
		if(ws.getLastObject() >= o.getId()){
			return true;
		}
		else{
			return false;
		}
	}
	
	public List<Workstation> getFirstWs(){
		synchronized(mFirstWs){
			if(mFirstWs.isEmpty()){
				setFirstWs();
			}
		}
		return new ArrayList<Workstation>(mFirstWs);
	}
	
	private void setFirstWs(){
		int pred[] = new int[mWsList.size()];
		for(int i=0; i<mWsList.size(); i++){
			for(int j=0; j<mWsList.get(i).getSuccessor().size(); j++){
				pred[mWsList.get(i).getSuccessor().get(j).getId()]++;
				//une list initialisee 0, a chaque fois qu'une workstation est le succeseur d'une autre, on augmente de 1 sa valeur
			}
		}

		for(int i=0; i<pred.length; i++){
			if(pred[i] == 0){
				//apres la creation de la liste, les workstation dont la valeur vaut 0 sont les premiere du graphe
				mFirstWs.add(mWsList.get(i));
			}
		}
	}

	public List<Workstation> getSuccessors(Workstation ws){
		return ws.getSuccessor();
	}

	public List<Workstation> getInitiators(Workstation ws){
		return ws.getInitiator();
	}
	
}
