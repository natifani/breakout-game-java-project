
package components;

public class Score {

	private int score;
	private String username;
	
	public Score(int score, String username) {
		
		this.score = score;
		this.username = username;
	}
	
	public void setScore(int score) {
		
		this.score = score;
	}
	
	public void setUsername(String username) {
		
		this.username = username;
	}
	 
	public int getScore() {
		
		return score;
	}
	
	public String getUsername() {
		
		return username;
	}
}
