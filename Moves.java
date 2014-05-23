import java.util.ArrayDeque;
import java.util.LinkedList;
public class Moves {
	public LinkedList doMove(int [][] board, ArrayDeque moves){
		LinkedList Listy =new LinkedList();
		int score=0;
		for (int i =0;i<moves.size();i++){
			if(moves.poll().equals("L"))
				score+=Left(board);
			else if(moves.poll().equals("R"))
				score+=Right(board);
			else if(moves.poll().equals("U"))
				score+=Up(board);
			else if(moves.poll().equals("D"))
				score+=Down(board);
			
		}
		Listy.add(score);
		Listy.add(moves);
		return Listy;
	}
	
	public int Left(int [][]board){
		
		boolean did = false;
		boolean did1 = false;
		int numMoved=0;
		int[] rowScore = new int[4];
		int lowest = 0;
		for (int i = 0; i < 4; i++) {
			did = false;
			if (board[i][0] == 1 && board[i][1] == 2 || board[i][0] == 2
					&& board[i][1] == 1) {
				board[i][0] = 3;
				board[i][1] = board[i][2];
				board[i][2] = board[i][3];
				board[i][3] = 0;
				did = true;
			} else if (board[i][0] == board[i][1] && board[i][1] > 2) {
				did = true;
				board[i][0] *= 2;
				board[i][1] = board[i][2];
				board[i][2] = board[i][3];
				board[i][3] = 0;
			} else if ((board[i][2] > 2 && board[i][0] != 0)
					&& board[i][1] == board[i][2]) {
				board[i][1] *= 2;
				board[i][2] = board[i][3];
				board[i][3] = 0;
				did = true;
			} else if ((board[i][2] != 0 && board[i][0] != 0)
					&& (board[i][1] == 1 && board[i][2] == 2 || board[i][1] == 2
					&& board[i][2] == 1)) {
				board[i][1] = 3;
				board[i][2] = board[i][3];
				board[i][3] = 0;
				did = true;
			} else if (board[i][3] > 2 && board[i][1] != 0 && board[i][0] != 0
					&& board[i][2] == board[i][3]) {

				board[i][2] *= 2;
				board[i][3] = 0;
				did = true;
			} else if (board[i][2] == 1 && board[i][3] == 2 || board[i][2] == 2
					&& board[i][3] == 1 && board[i][1] != 0 && board[i][0] != 0) {
				board[i][2] = 3;
				board[i][3] = 0;
				did = true;

			}
			if(!did){
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == 0 && board[i][j + 1] != 0) {
					board[i][j] = board[i][j + 1];
					board[i][j + 1] = 0;
					did = true;
				}
			}}
			for (int j = 0; j < 4; j++) {
				rowScore[i] += board[i][j];

			}
			if (did) {
				numMoved++;
				if(numMoved==1||rowScore[i] <= rowScore[lowest])
					lowest=i;
				
			}
			if (!did1)
				did1 = did;

		}

		if (did1) {
			//setNext(lowest, 3);
			try {
				board[lowest][3] =  Integer.parseInt(Threes.next.remove()) ; 
			} catch (NumberFormatException n) {
				if (Threes.next.size() != 0) {
				
				}
			}
			
		}
		if(!did)
			return(-1);
		else return(CountWhite(board));
		
		
	}
	public int Right(int [][]board){
		int score=0;
		
		return score;
	}
	public int Up(int [][]board){
		int score=0;
		
		return score;
	}
	public int Down(int [][]board){
		int score=0;
		
		return score;
	}
public int CountWhite(int board[][]){
	int white=0;
	for (int i=0;i<4;i++){
		for(int j=0;j<4;j++){
			if(board[i][j]==0)
				white++;
		}
		
	}
	return white;
}

}
