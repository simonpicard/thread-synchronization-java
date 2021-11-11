import java.util.List;
import java.util.ArrayList;

//Simon Picard | Info-f-202 | Projet Java | worker.java

public class Worker implements Runnable {
	private static int mI;
	private int mId = mI;
	private List<Article> mAList;
	//list des articles a traiter
	private String mLog;
	private Graph mGraph;
	private List<Workstation> mL;
	//list representant les workstation auxquelles on a acces et ou on doit traiter l'article

	public Worker(List<Article> aList, Graph graph) {
		mI++;
		mAList = aList;
		mGraph = graph;
		mLog = "Worker " + mId + " :\n";
	}

	public void run() {
		int action;
		Article O;
		Workstation W;
		for (int i = 0; i < mAList.size(); i++) {
			//pour chaque article a traiter
			mL = mGraph.getFirstWs();
			O = mAList.get(i);
			for (int j = 0; j < mL.size(); j++) {
				W = mL.get(j);
				synchronized (W){
					if (mGraph.isReady(W, O)) {
						//l'article O est pret a etre taiter sur la workstation W
						action=0;
						W.process(O);
						W.notifyAll();
					} else if (!mGraph.isDone(W, O)) {
						//l'article O n'est pret a etre taiter sur la workstation W
						action=1;
						try {
							W.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						j--;
					} else {
						//l'article O est a deja ete traite sur la workstation W
						action=2;
					}
					updateArticleLog(action, O, W);
				}
				if(action==0){
					updateL(W, O);
				}
				updateMyLog(action, O, W);
			}
		}
	}

	public String getLog() {
		return mLog;
	}

	private void updateL(Workstation W, Article O) {
		List<Workstation> Successors = new ArrayList<Workstation>();
		List<Workstation> initiators = new ArrayList<Workstation>();
		Successors = mGraph.getSuccessors(W);
		for (int i = 0; i < Successors.size(); i++) {
			//pour chaque successeur de W
			initiators = mGraph.getInitiators(Successors.get(i));
			//nous regardons ses initiateurs
			if (initiators.size() == 1) {
				//s'il n'y en a qu'un, qu'on vient de traiter, on peut ajouter la workstation dans la liste des workstation accessible
				mL.add(Successors.get(i));
			} else if(initiators.size() > 1) {
				//s'il y a plus d'un initiateurs
				boolean ready = true;
				for (int j = 0; j < initiators.size(); j++) {
					if (!mGraph.isDone(initiators.get(j), O)) {
						//nous verifions que l'article a ete procede sur chacune des workstation
						ready = false;
					}
				}
				if (ready) {
					//si c'est le cas nous ajoutons l'article
					mL.add(Successors.get(i));
				}
			}
		}
	}

	private void updateArticleLog(int action, Article O, Workstation W) {
		if (action == 0) {
			O.addLog("Worker " + mId + " processed Workstation " + W.getName()
					+ "\n");
		} else if (action == 2) {
			O.addLog("Worker " + mId + " skipped Workstation " + W.getName()
					+ "\n");
		}
	}

	private void updateMyLog(int action, Article O, Workstation W) {
		if (action == 0) {
			mLog += "Article " + O.getId() + " processed on Workstation "
					+ W.getName() + "\n";
		} else if (action == 1) {
			mLog += "Waited for the Workstation " + W.getName()
					+ " to be ready for the article " + O.getId() +"\n";
		} else if (action == 2) {
			mLog += "Article " + O.getId() + " skipped Workstation "
					+ W.getName() + "\n";
		}
	}

}
