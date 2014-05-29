package AI;

//****************************************
//need to fix input file so it ignores anything other than LRUD
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.Math;

/*
 * Josh La Verghetta 20762905
 * Andrew Edwards 20937641
 * A class to carry out moves on the Threes board.
 */
public class Moves {

	private static LinkedList<String> next = new LinkedList<String>();
	private static LinkedList<String> nextTemp = new LinkedList<String>();

	/*
	 * A function to actually perform the moves on the board
	 * 
	 * @param board the board to be changed
	 * 
	 * @param moves the list of moves to be performed on board
	 * 
	 * @param print a variable to determine whether we want debugging print
	 * statements or not
	 * 
	 * @return a linked list containing the moves we actually performed on the
	 * board
	 */
	public static LinkedList<Object> doMove(int[][] board,
			ArrayDeque<String> moves, boolean print) {
		boolean isFail = false;
		if (print) {
			Moves.finalNext();
		}
		int[][] tempBoard = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				tempBoard[i][j] = board[i][j];
			}
		}

		ArrayDeque<String> tempMoves = moves.clone();

		// GUI.Threes.printArr(tempMoves);

		int movesSize = tempMoves.size();
		int[] isValid = { 0, 0 };
		LinkedList<Object> Listy = new LinkedList<Object>();
		int score = 0;
		int hScore = 0;

		for (int i = 0; i < movesSize; i++) {
			isValid[0] = -1;
			isValid[1] = -1;
			if (tempMoves.peekLast().equals("L")) {
				isValid = Left(tempBoard);

				if (isValid[0] != -1) {
					score = isValid[0];
					hScore = isValid[1];

					Listy.add(tempMoves.pollLast());
				} else
					isFail = true;
			} else if (tempMoves.peekLast().equals("R")) {
				isValid = Right(tempBoard);

				if (isValid[0] != -1) {
					score = isValid[0];
					hScore = isValid[1];

					Listy.add(tempMoves.pollLast());
				} else
					isFail = true;
			} else if (tempMoves.peekLast().equals("U")) {
				isValid = Up(tempBoard);

				if (isValid[0] != -1) {
					score = isValid[0];
					hScore = isValid[1];

					Listy.add(tempMoves.pollLast());
				} else
					isFail = true;
			} else if (tempMoves.peekLast().equals("D")) {
				isValid = Down(tempBoard);

				if (isValid[0] != -1) {
					score = isValid[0];
					hScore = isValid[1];

					Listy.add(tempMoves.pollLast());
				} else
					isFail = true;
			} else {
				tempMoves.remove();
			}
			
		}

		resetNext();

		if (isFail) {
			Listy.addFirst(-1);
			Listy.addFirst(-1);
		} else {
			/*for (int k=0;k<4;k++){
				for(int j=0;j<4;j++){
					System.out.print(tempBoard[k][j]+" ");
				}System.out.println();
			}System.out.println("Moves ");
			int loop= Listy.size();
			for(int i=0;i<loop;i++){
				System.out.print(Listy.get(i));
			}System.out.println();*/
			Listy.addFirst(score);
			Listy.addFirst(hScore);
			
		}
		if (print){
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					System.out.print(tempBoard[i][j] + " ");
				}System.out.println();
			}
		}
		return Listy;
	}

	/*
	 * 
	 */
	public static void resetNext() {
		next = new LinkedList<String>(nextTemp);
	}

	/*
	 * 
	 */
	public static void popNext() {
		GUI.Threes.next.poll();
	}

	/*
	 * 
	 */
	public static void storeNext() {
		nextTemp = new LinkedList<String>(GUI.Threes.next);
	}

	/*
	 * 
	 */
	public static void finalNext() {
		next = new LinkedList<String>(nextTemp);
	}

	/*
	 * A function to carry out a move left on the board
	 * 
	 * @param board the board to be manipulated
	 * 
	 * @return an array containing the heuristic score and the score of the
	 * board after the move has been performed.
	 */
	public static int[] Left(int[][] board) {

		boolean did = false;
		boolean did1 = false;
		int numMoved = 0;
		ArrayList<Integer>[] rowScore = new ArrayList[4];
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

			rowScore[i] = GenList(board, 0, i);

			if (did) {
				rowScore[i].add(0, 1);
			} else
				rowScore[i].add(0, -1);
			if (!did1)
				did1 = did;

			if (did) {
				numMoved++;
				if (numMoved == 1
						|| ListComp(rowScore[lowest], rowScore[i]) != -1)
					lowest = i;

			}

		}

		if (did1) {
			// setNext(lowest, 3);
			try {
				board[lowest][3] = Integer.parseInt(next.remove());
			} catch (NumberFormatException n) {
				if (GUI.Threes.next.size() != 0) {
					System.out.println("IHAVENOIDEAWHATSGOINGON");
				}
			}

		}

		if (did1) {
			int[] temp = Hscore(board);
			scores[0] = temp[0];
			scores[1] = temp[1];
			return (scores);

		} else {
			scores[0] = -1;
			scores[1] = -1;
		}
		return (scores);

	}

	/*
	 * A function to carry out a move right on the board
	 * 
	 * @param board the board to be manipulated
	 * 
	 * @return an array containing the heuristic score and the score of the
	 * board after the move has been performed.
	 */
	public static int[] Right(int[][] board) {
		int k = 3;
		boolean did = false;
		boolean did1 = false;
		int lowest = 0;
		int numMoved = 0;
		int[] scores = { -1, -1 };
		ArrayList<Integer>[] rowScore = new ArrayList[4];
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

			rowScore[i] = GenList(board, 1, i);

			if (did) {
				rowScore[i].add(0, 1);
			} else
				rowScore[i].add(0, -1);
			if (did) {
				numMoved++;
				if (ListComp(rowScore[lowest], rowScore[i]) == 1
						|| numMoved == 1) {
					lowest = i;
				}
			}
			if (!did1)
				did1 = did;

		}

		if (did1) {
			// setNext(lowest, 0);
			try {
				board[lowest][0] = Integer.parseInt(next.remove());
			} catch (NumberFormatException n) {
				if (GUI.Threes.next.size() != 0) {

				}
			}
			// addMove("R");
		}
		if (did1) {
			int[] temp = Hscore(board);
			scores[0] = temp[0];
			scores[1] = temp[1];
			return (scores);
		} else
			return (scores);

	}

	/*
	 * A function to carry out a move up on the board
	 * 
	 * @param board the board to be manipulated
	 * 
	 * @return an array containing the heuristic score and the score of the
	 * board after the move has been performed.
	 */

	public static int[] Up(int[][] board) {
		boolean did = false;
		boolean did1 = did;
		int lowest = 0;
		int numMoved = 0;
		int[] scores = { -1, -1 };
		ArrayList<Integer>[] colScore = new ArrayList[4];
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

			colScore[i] = GenList(board, 2, i);

			if (did)
				colScore[i].add(0, 1);
			else
				colScore[i].add(0, -1);

			if (did) {
				numMoved++;

				if (numMoved == 1)
					lowest = i;
				else if (ListComp(colScore[lowest], colScore[i]) == 1) {
					lowest = i;
				}
			}
			if (!did1)
				did1 = did;

		}

		if (did1) {
			// setNext(3, lowest);
			try {
				board[3][lowest] = Integer.parseInt(next.remove());
			} catch (NumberFormatException n) {
				if (GUI.Threes.next.size() != 0) {

				}
			}
			// addMove("U");
		}
		if (did1) {
			int[] temp = Hscore(board);
			scores[0] = temp[0];
			scores[1] = temp[1];
			return (scores);
		} else
			return (scores);

	}

	/*
	 * A function to carry out a move down on the board
	 * 
	 * @param board the board to be manipulated
	 * 
	 * @return an array containing the heuristic score and the score of the
	 * board after the move has been performed.
	 */
	public static int[] Down(int[][] board) {
		int k = 3;
		boolean did = false;
		boolean did1 = did;
		int lowest = 0;
		int[] scores = { -1, -1 };
		int numMoved = 0;
		ArrayList<Integer>[] colScore = new ArrayList[4];
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

			colScore[i] = GenList(board, 3, i);

			if (did)
				colScore[i].add(0, 1);
			else
				colScore[i].add(0, -1);
			if (did) {
				numMoved++;

				if (numMoved == 1)
					lowest = i;
				else if (ListComp(colScore[lowest], colScore[i]) != -1) {
					lowest = i;
				}
			}
			if (!did1)
				did1 = did;

		}

		if (did1) {
			// setNext(0, lowest);
			try {
				board[0][lowest] = Integer.parseInt(next.remove());
			} catch (NumberFormatException n) {
				if (GUI.Threes.next.size() != 0) {

				}
			}
			// addMove("D");
		}
		if (did1) {
			int[] temp = Hscore(board);
			scores[0] = temp[0];
			scores[1] = temp[1];
			return (scores);
		} else
			return (scores);

	}

	/*
	 * A function to count the number of white(empty) tiles on a given board
	 * 
	 * @param board the board to be counted
	 * 
	 * @return an int containing the number of counted empty tiles
	 */
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

	/*
	 * 
	 */
	public static int num12(int board[][]) {
		int num = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j] == 0)
					if (board[i][j] == 1 || board[i][j] == 2)
						num++;
			}

		}
		return num;
	}

	/*
	 * A function to calculate the heuristic score of a given board
	 * 
	 * @param board the board to be analysed
	 * 
	 * @return an array of integers containing the heuristic score
	 */
	public static int[] Hscore(int[][] board) {
		int[] hscore = { -1, -1 };
		hscore[0] = countScore(board);
		int rat = hscore[0] / 10 + 1;
		int multi = rat / 3;

		double ran = Math.random() * 1000;
		hscore[1] = countScore(board) * (rat) - num12(board) * rat
				+ getBig(board) * rat /*+ board[0][0] * multi
				+ (multi - multi / 10) * board[0][1] + (multi - multi / 10)
				* board[1][0] + (multi - multi / 10) * board[1][1]*/ + hscore[0]
				* rat // +(int)ran
		;
		return hscore;
	}

	/*
	 * A function to calculate the score of a given board
	 * 
	 * @param board the board to be analysed
	 * 
	 * @return an int containing the score
	 */
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

	/*
	 * A function to calculate the biggest tile in a board
	 * 
	 * @param board the board to be analysed
	 * 
	 * @return an int containing the biggest tile on the board
	 */
	public static int getBig(int[][] board) {
		int max = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j] > max) {
					max = board[i][j];
				}
			}
		}
		return max;
	}

	/*
	 * 
	 */
	public static ArrayList<Integer> GenList(int[][] board, int type, int rc) {
		ArrayList<Integer> score = new ArrayList<Integer>();
		int x = 0;
		int y = 0;

		for (int i = 0; i < 4; i++) {
			switch (type) {
			case 0:
				x = rc;
				y = 3 - i;
				break;
			case 1:
				x = rc;
				y = i;
				break;
			case 2:
				y = rc;
				x = 3 - i;
				break;
			case 3:
				y = rc;
				x = i;
				break;

			}
			score.add(board[x][y]);
		}

		return score;
	}

	/**
	 * 
	 * @param A
	 * @param B
	 * @return 1 if B less than A or -1 if A<B or 0 if they are equal
	 */
	public static int ListComp(ArrayList<Integer> A, ArrayList<Integer> B) {
		int ret = 0;
		if (A.get(0) == -1) {
			return 1;
		} else if (B.get(0) == -1) {
			return -1;
		} else {
			for (int i = 1; i < A.size(); i++) {
				if (A.get(i) > B.get(i)) {
					ret = 1;
					break;
				} else if (A.get(i) < B.get(i)) {
					ret = -1;
					break;

				}
			}

		}
		return ret;
	}

	public static void main(String[] args) {
		/*
		 * ArrayDeque<String> ard = new ArrayDeque<String>(); ard.add("L");
		 * ard.add("L"); ard.add("R"); ard.add("U"); ard.add("D"); ard.add("U");
		 * ard.add("R"); ard.add("L");
		 */
		GUI.Threes.readFile();
		GUI.Threes.set();

		ArrayList<Integer> test = GenList(GUI.Threes.board, 3, 3);
		while (!test.isEmpty()) {
			System.out.println("contents of test " + test.remove(0));
		}
		/*
		 * int[][] testBoard =new int[4][4]; for (int i=0;i<4;i++){ for(int
		 * j=0;j<4;j++){ testBoard[i][j]=3; }
		 * 
		 * } int testScore=0; for (int i=0;i<4;i++){ for(int j=0;j<4;j++){
		 * testScore+=testBoard[i][j]; }
		 * 
		 * } System.out.println("moves executed:");
		 * System.out.println("Test score score: " +testScore);
		 * System.out.println("white space of initial board is: " +
		 * CountWhite(GUI.Threes.board));
		 * System.out.println("The initial board is:"); //GUI.Threes.printArr();
		 * LinkedList<Object> dog = doMove(testBoard, ard);
		 * 
		 * System.out.println("white space of final board is: " +
		 * CountWhite(GUI.Threes.board));
		 * System.out.println("The final board is"); //GUI.Threes.printArr();
		 * System.out.println("score is: " + dog.get(0));
		 * System.out.println("Amount of WhiteSpace is: " + dog.get(1));
		 * testScore=0; for (int i=0;i<4;i++){ for(int j=0;j<4;j++){
		 * testScore+=testBoard[i][j]; }
		 * 
		 * } System.out.println("moves executed:");
		 * System.out.println("Test score score: " +testScore); int i = 2; while
		 * (i < dog.size()) { System.out.println(dog.get(i)); i++; }
		 */

	}
}
