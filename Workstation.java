import java.util.ArrayList;
import java.util.List;

//Simon Picard | Info-f-202 | Projet Java | Workstation.java

public class Workstation {
	private static int mI;
	private int mId = mI;
	private int mLastObject;
	private String mName;
	private Task mTask;
	private List<Workstation> mInitiator = null;
	//list ayant une reference vers tout les initiateurs de la workstation
	private List<Workstation> mSuccessor = null;
	//list ayant une reference vers tout les successeurs de la workstation
	
	public Workstation(String name, Task task){
		mI++;
		mLastObject = -1;
		mName = name;
		mTask = task;
		mInitiator = new ArrayList<Workstation>();
		mSuccessor = new ArrayList<Workstation>();
	}
	
	public Workstation(Task task){
		mI++;
		mLastObject = -1;
		mName = Integer.toString(mId);
		mTask = task;
		mInitiator = new ArrayList<Workstation>();
		mSuccessor = new ArrayList<Workstation>();
	}

	public String getName() {
		return mName;
	}
	
	public int getId() {
		return mId;
	}

	public int getLastObject(){
		return mLastObject;
	}
	
	public void addSuccessor(Workstation ws){
		mSuccessor.add(ws);
	}
	
	public void addInitiator(Workstation ws){
		mInitiator.add(ws);
	}
	
	public List<Workstation> getSuccessor() {
		return mSuccessor;
	}

	public List<Workstation> getInitiator(){
		return mInitiator;
	}
	
	public synchronized void process(Article article){
		mTask.process(article);
		mLastObject = article.getId();
	}
}
