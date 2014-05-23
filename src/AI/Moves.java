package AI;

import java.util.ArrayDeque;
import java.util.LinkedList;

public class Moves {

	public static LinkedList<Object> doMove(int[][] board,
			ArrayDeque<String> moves) {
		int movesSize = moves.size();
		int[] isValid = { 0, 0 };
		LinkedList<Object> Listy = new LinkedList<Object>();
		int score = 0;
		int whiteSpace = 0;
		for (int i = 0; i < movesSize; i++) {
			isValid[0] = 0;
			isValid[1] = 0;
			if (moves.peek().equals("L")) {
				isValid = Left(board);

				if (isValid[0] != -1) {
					score = isValid[0];
					whiteSpace = isValid[1];

					Listy.add(moves.poll());
				}
			} else if (moves.peek().equals("R")) {
				isValid = Right(board);

				if (isValid[0] != -1) {
					score = isValid[0];
					whiteSpace = isValid[1];

					Listy.add(moves.poll());
				}
			} else if (moves.peek().equals("U")) {
				isValid = Up(board);

				if (isValid[0] != -1) {
					score = isValid[0];
					whiteSpace = isValid[1];

					Listy.add(moves.poll());
				}
			} else if (moves.peek().equals("D")) {
				isValid = Down(board);

				if (isValid[0] != -1) {
					score = isValid[0];
					whiteSpace = isValid[1];

					Listy.add(moves.poll());
				}
			}
		}
		Listy.addFirst(whiteSpace);
		Listy.addFirst(score);
		return Listy;
	}

	public static int[] Left(int[][] board) {

		boolean did = false;
		boolean did1 = false;
		int numMoved = 0;
		int[] rowScore = new int[4];
		int lowest = 0;
		int[] scores = { -1, -1 };
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
			if (!did) {
				for (int j = 0; j < 3; j++) {
					if (board[i][j] == 0 && board[i][j + 1] != 0) {
						board[i][j] = board[i][j + 1];
						board[i][j + 1] = 0;
						did = true;
					}
				}
			}
			for (int j = 0; j < 4; j++) {
				rowScore[i] += board[i][j];

			}
			if (did) {
				numMoved++;
				if (numMoved == 1 || rowScore[i] <= rowScore[lowest])
					lowest = i;

			}
			if (!did1)
				did1 = did;

		}

		if (did1) {
			// setNext(lowest, 3);
			try {
				board[lowest][3] = Integer.parseInt(GUI.Threes.next.remove());
			} catch (NumberFormatException n) {
				if (GUI.Threes.next.size() != 0) {

				}
			}

		}

		if (did1) {
			scores[0] = countScore(board);
			scores[1] = CountWhite(board);
			return (scores);
		} else
			return (scores);

	}

	public static int[] Right(int[][] board) {
		int k = 3;
		boolean did = false;
		boolean did1 = false;
		int lowest = 0;
		int numMoved = 0;
		int[] scores = { -1, -1 };
		int[] rowScore = new int[4];
		for (int i = 0; i < 4; i++) {
			did = false;

			if (board[i][k - 0] == board[i][k - 1] && board[i][k - 0] > 2) {
				did = true;
				board[i][k - 0] *= 2;
				board[i][k - 1] = board[i][k - 2];
				board[i][k - 2] = board[i][k - 3];
				board[i][k - 3] = 0;
			} else if (board[i][k - 0] == 1 && board[i][k - 1] == 2
					|| board[i][k - 0] == 2 && board[i][k - 1] == 1) {
				board[i][k - 0] = 3;
				board[i][k - 1] = board[i][k - 2];
				board[i][k - 2] = board[i][k - 3];
				board[i][k - 3] = 0;
				did = true;
			} else if ((board[i][k - 1] > 2 && board[i][k] != 0)
					&& board[i][k - 1] == board[i][k - 2]) {
				did = true;
				board[i][k - 1] *= 2;
				board[i][k - 2] = board[i][k - 3];
				board[i][k - 3] = 0;
			} else if ((board[i][k - 1] != 0 && board[i][k] != 0)
					&& board[i][k - 1] == 1 && board[i][k - 2] == 2
					|| board[i][k - 1] == 2 && board[i][k - 2] == 1) {
				board[i][k - 1] = 3;
				board[i][k - 2] = board[i][k - 3];
				board[i][k - 3] = 0;
				did = true;
			} else if (board[i][k - 3] > 2 && board[i][k - 1] != 0
					&& board[i][k] != 0 && board[i][k - 2] == board[i][k - 3]) {
				board[i][k - 2] *= 2;
				board[i][k - 3] = 0;
				did = true;
			} else if (board[i][k - 1] != 0 && board[i][k] != 0
					&& (board[i][k - 2] == 2 && board[i][k - 3] == 1)
					|| (board[i][k - 2] == 1 && board[i][k - 3] == 2)) {
				did = true;
				board[i][k - 2] = 3;
				board[i][k - 3] = 0;

			}
			if (!did) {
				for (int j = 3; j > 0; j--) {
					if (board[i][j] == 0 && board[i][j - 1] != 0) {
						board[i][j] = board[i][j - 1];
						board[i][j - 1] = 0;
						did = true;
					}
				}
			}
			for (int j = 0; j < 4; j++) {
				rowScore[i] += board[i][j];

			}
			if (did) {
				numMoved++;
				if (rowScore[i] <= rowScore[lowest] || numMoved == 1) {
					lowest = i;
				}
			}
			if (!did1)
				did1 = did;

		}

		if (did1) {
			// setNext(lowest, 0);
			try {
				board[lowest][0] = Integer.parseInt(GUI.Threes.next.remove());
			} catch (NumberFormatException n) {
				if (GUI.Threes.next.size() != 0) {

				}
			}
			// addMove("R");
		}
		if (did1) {
			scores[0] = countScore(board);
			scores[1] = CountWhite(board);
			return (scores);
		} else
			return (scores);

	}

	public static int[] Up(int[][] board) {
		boolean did = false;
		boolean did1 = did;
		int lowest = 0;
		int numMoved = 0;
		int[] scores = { -1, -1 };
		int[] colScore = new int[4];
		// colScore[4]=99999;
		for (int i = 0; i < 4; i++) {
			did = false;
			if (board[0][i] == board[1][i] && board[0][i] > 2) {

				board[0][i] *= 2;
				board[1][i] = board[2][i];
				board[2][i] = board[3][i];
				board[3][i] = 0;
				did = true;
			} else if (board[0][i] == 1 && board[1][i] == 2 || board[0][i] == 2
					&& board[1][i] == 1) {
				board[0][i] = 3;
				board[1][i] = board[2][i];
				board[2][i] = board[3][i];
				board[3][i] = 0;
				did = true;
			} else if (board[1][i] == board[2][i] && board[1][i] > 2
					&& board[0][i] != 0) {
				board[1][i] *= 2;
				board[2][i] = board[3][i];
				board[3][i] = 0;
				did = true;
			} else if ((board[1][i] != 0 && board[0][i] != 0)
					&& (board[1][i] == 1 && board[2][i] == 2 || board[1][i] == 2
							&& board[2][i] == 1)) {
				board[1][i] = 3;
				board[2][i] = board[3][i];
				board[3][i] = 0;
				did = true;
			} else if (board[2][i] == board[3][i] && board[2][i] > 2
					&& board[1][i] != 0 && board[0][i] != 0) {
				board[2][i] *= 2;
				board[3][i] = 0;
				did = true;
			} else if ((board[2][i] != 0 && board[1][i] != 0 && board[0][i] != 0)
					&& (board[2][i] == 1 && board[3][i] == 2 || board[2][i] == 2
							&& board[3][i] == 1)) {
				board[2][i] = 3;
				board[3][i] = 0;
				did = true;
			}
			if (!did) {
				for (int j = 0; j < 3; j++) {
					if (board[j][i] == 0 && board[j + 1][i] != 0) {
						board[j][i] = board[j + 1][i];
						board[j + 1][i] = 0;
						did = true;
					}
				}
			}

			for (int j = 0; j < 4; j++) {
				colScore[i] += board[j][i];

			}
			if (did) {
				numMoved++;

				if (numMoved == 1)
					lowest = i;
				else if (colScore[i] <= colScore[lowest]) {
					lowest = i;
				}
			}
			if (!did1)
				did1 = did;

		}

		if (did1) {
			// setNext(3, lowest);
			try {
				board[3][lowest] = Integer.parseInt(GUI.Threes.next.remove());
			} catch (NumberFormatException n) {
				if (GUI.Threes.next.size() != 0) {

				}
			}
			// addMove("U");
		}
		if (did1) {
			scores[0] = countScore(board);
			scores[1] = CountWhite(board);
			return (scores);
		} else
			return (scores);

	}

	public static int[] Down(int[][] board) {
		int k = 3;
		boolean did = false;
		boolean did1 = did;
		int lowest = 0;
		int[] scores = { -1, -1 };
		int numMoved = 0;
		int[] colScore = new int[4];

		for (int i = 0; i < 4; i++) {
			did = false;
			if (board[k - 0][i] == board[k - 1][i] && board[k][i] > 2) { // check
				// if
				// =
				board[k - 0][i] *= 2;
				board[k - 1][i] = board[k - 2][i];
				board[k - 2][i] = board[k - 3][i];
				board[k - 3][i] = 0;
				did = true;
			} else if (board[k - 0][i] == 1 && board[k - 1][i] == 2
					|| board[k][i] == 2 && board[k - 1][i] == 1) {
				board[k - 0][i] = 3;
				board[k - 1][i] = board[k - 2][i];
				board[k - 2][i] = board[k - 3][i];
				board[k - 3][i] = 0;
				did = true;
			} else if (board[k - 1][i] == board[k - 2][i] && board[k][i] != 0
					&& board[k - 1][i] > 2) {
				board[k - 1][i] *= 2;
				board[k - 2][i] = board[k - 3][i];
				board[k - 3][i] = 0;
				did = true;
			} else if ((board[k][i] != 0 && board[k - 1][i] != 0)
					&& (board[k - 1][i] == 1 && board[k - 2][i] == 2 || board[k - 1][i] == 2
							&& board[k - 2][i] == 1)) {
				board[k - 1][i] = 3;
				board[k - 2][i] = board[k - 3][i];
				board[k - 3][i] = 0;
				did = true;
			} else if (board[k - 2][i] == board[k - 3][i] && board[k][i] != 0
					&& board[k - 1][i] != 0 && board[k - 2][i] > 2) {
				board[k - 2][i] *= 2;
				board[k - 3][i] = 0;
				did = true;
			} else if ((board[k][i] != 0 && board[k - 1][i] != 0 && board[k - 2][i] != 0)
					&& ((board[k - 2][i] == 1 && board[k - 3][i] == 2 || board[k - 2][i] == 2
							&& board[k - 3][i] == 1))) {
				board[k - 2][i] = 3;
				board[k - 3][i] = 0;
				did = true;
			}
			if (!did) {
				for (int j = 3; j > 0; j--) {
					if (board[j][i] == 0 && board[j - 1][i] != 0) {
						board[j][i] = board[j - 1][i];
						board[j - 1][i] = 0;
						did = true;
					}
				}
			}
			for (int j = 0; j < 4; j++) {
				colScore[i] += board[j][i];

			}
			if (did) {
				numMoved++;

				if (numMoved == 1)
					lowest = i;
				else if (colScore[i] <= colScore[lowest]) {
					lowest = i;
				}
			}
			if (!did1)
				did1 = did;

		}

		if (did1) {
			// setNext(0, lowest);
			try {
				board[0][lowest] = Integer.parseInt(GUI.Threes.next.remove());
			} catch (NumberFormatException n) {
				if (GUI.Threes.next.size() != 0) {

				}
			}
			// addMove("D");
		}
		if (did1) {
			scores[0] = countScore(board);
			scores[1] = CountWhite(board);
			return (scores);
		} else
			return (scores);

	}

	public static int CountWhite(int board[][]) {
		int white = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j] == 0)
					white++;
			}

		}
		return white;
	}

	public static int countScore(int[][] board) {
		int count = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j] == 1 || board[i][j] == 2) {
					count += 1;
				} else {
					int x = board[i][j];
					double y = (Math.log10(x / 3) / Math.log10(2) + 1);// pretty
																		// sure
																		// that
																		// this
																		// works

					count += Math.pow(3, y);
					// 3 ^ (log2(x / 3) + 1)
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {
		ArrayDeque<String> ard = new ArrayDeque<String>();
		ard.add("L");
		ard.add("L");
		ard.add("R");
		ard.add("U");
		ard.add("D");
		ard.add("U");
		ard.add("R");
		ard.add("L");

		GUI.Threes.readFile();
		GUI.Threes.set();
		int[][] testBoard =new int[4][4];
		for (int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				testBoard[i][j]=3;
			}
			
		}
		int testScore=0;
		for (int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				testScore+=testBoard[i][j];
			}
			
		}
		System.out.println("moves executed:");
		System.out.println("Test score score: " +testScore);
		System.out.println("white space of initial board is: "
				+ CountWhite(GUI.Threes.board));
		System.out.println("The initial board is:");
		GUI.Threes.printArr();
		LinkedList<Object> dog = doMove(testBoard, ard);

		System.out.println("white space of final board is: "
				+ CountWhite(GUI.Threes.board));
		System.out.println("The final board is");
		GUI.Threes.printArr();
		System.out.println("score is: " + dog.get(0));
		System.out.println("Amount of WhiteSpace is: " + dog.get(1));
		 testScore=0;
		for (int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				testScore+=testBoard[i][j];
			}
			
		}
		System.out.println("moves executed:");
		System.out.println("Test score score: " +testScore);
		int i = 2;
		while (i < dog.size()) {
			System.out.println(dog.get(i));
			i++;
		}

	}
}
