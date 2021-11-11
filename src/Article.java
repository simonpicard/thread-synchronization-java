
//Simon Picard | Info-f-202 | Projet Java | Article.java

public class Article {
	private static int mI;
	private int mId = mI;
	private String mLog;
	
	public Article(){
		mI++;
		mLog = "Article " + mId + " :\n";
	}
	
	public String getLog() {
		return mLog;
	}

	public synchronized void addLog(String log) {
		mLog += log;
	}

	public int getId(){
		return mId;
	}

}
