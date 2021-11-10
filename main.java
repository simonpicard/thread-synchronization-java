import java.util.List;
import java.util.ArrayList;

//Simon Picard | Info-f-202 | Projet Java | main.java

public class main {

	public static void main(String[] args) {
		//la structure initialise ici est la meme que celle presentee dans l'enonce du projet
		Workstation A = new Workstation("A", new Melting());
		Workstation B = new Workstation("B", new Macerating());
		Workstation C = new Workstation("C", new Extracting());
		Workstation D = new Workstation("D", new Compressing());
		Workstation E = new Workstation("E", new Assembling());
		
		Graph graph = new Graph();

		graph.addWorkstation(A);
		graph.addWorkstation(B);
		graph.addWorkstation(C);
		graph.addWorkstation(D);
		graph.addWorkstation(E);
		
		graph.addWsLink(A, C);
		graph.addWsLink(C, E);
		graph.addWsLink(A, D);
		graph.addWsLink(B, D);
		graph.addWsLink(D, E);
		
		Article O1 = new Article();
		Article O2 = new Article();
		Article O3 = new Article();
		List<Article> aL1 = new ArrayList<Article>(), aL2 = new ArrayList<Article>();
		aL1.add(O1);
		aL1.add(O2);
		aL2.add(O3);
		Worker a1 = new Worker(aL1, graph);
		Worker a2 = new Worker(aL1, graph);
		Worker a3 = new Worker(aL2, graph);
		Thread t1 = new Thread(a1);
		Thread t2 = new Thread(a2);
		Thread t3 = new Thread(a3);
		t2.start();
		t1.start();
		t3.start();
		
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("###########\n");
		System.out.println(a1.getLog());
		System.out.println(a2.getLog());
		System.out.println(a3.getLog());
		System.out.println("###########\n\n" + O1.getLog());
		System.out.println(O2.getLog());
		System.out.println(O3.getLog());
		
	}

}
