package AI;

public class Board {
	private int[][] board;
	private boolean gameOver;
	int hScore; //the score our heuristic function gives for the current move
	int score; //the current score of the board at this current move
	public String newMove;
	public Board(int [][] board,boolean gameOver){
		this.board=board;
		this.gameOver=gameOver;
		hScore=Moves.Hscore(board)[0];
		score=Moves.Hscore(board)[1];
	}
	public Board(int [][] board,boolean gameOver,String newMove){
		this.board=board;
		this.gameOver=gameOver;
		hScore=Moves.Hscore(board)[0];
		score=Moves.Hscore(board)[1];
		this.newMove=newMove;
	}
	public Board(int [][] board){
		this.board=board;
		gameOver=false;
		hScore=Moves.Hscore(board)[0];
		score=Moves.Hscore(board)[1];
	}
	
	public void setBoard(int [][]board){
		this.board=board;
		
	}
	
	public void setGame(boolean gameOver){
		this.gameOver=gameOver;
	}
	public int getScore()
	{
		return score;
	}
	public int getHScore(){
		return hScore;
	}
	public void setScore(int score){
		this.score=score;
	}
	public void setHScore(int hScore){
		this.hScore=hScore;
	}
	public int [][] getBoard(){
		return board;
	}
	public String getNewMove(){
		return newMove;
	}
	public boolean getGame(){
		return gameOver;
	}
	public void doMove(){
		
	}
	public void printBoard(){
		
		for (int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				System.out.print(board[i][j]);
			}System.out.println();
		}System.out.println();
	}
}
