package AI;

import java.util.ArrayList;

public class betterMoves {
	public static Board Down(int[][] board,int n) {
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

			colScore[i] = Moves.GenList(board, 3, i);

			if (did)
				colScore[i].add(0, 1);
			else
				colScore[i].add(0, -1);
			if (did) {
				numMoved++;

				if (numMoved == 1)
					lowest = i;
				else if (Moves.ListComp(colScore[lowest], colScore[i]) != -1) {
					lowest = i;
				}
			}
			if (!did1)
				did1 = did;

		}

		if (did1) {
			// setNext(0, lowest);
			try {
				board[0][lowest] = Integer.parseInt(GUI.Threes.getNext(n));
			} catch (NumberFormatException f) {
				if (GUI.Threes.next.size() != 0) {

				}
			}
			// addMove("D");
		}
		Board temp=new Board(board,did1,"D");
		return(temp);

	}
	
	
	public static Board Left(int[][] board,int n) {

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

			rowScore[i] = Moves.GenList(board, 0, i);

			if (did) {
				rowScore[i].add(0, 1);
			} else
				rowScore[i].add(0, -1);
			if (!did1)
				did1 = did;

			if (did) {
				numMoved++;
				if (numMoved == 1
						|| Moves.ListComp(rowScore[lowest], rowScore[i]) != -1)
					lowest = i;

			}

		}

		if (did1) {
			// setNext(lowest, 3);
			try {
				board[lowest][3] = Integer.parseInt(GUI.Threes.getNext(n));
			} catch (NumberFormatException s) {
				if (GUI.Threes.next.size() != 0) {
					System.out.println("IHAVENOIDEAWHATSGOINGON");
				}
			}

		}

		Board temp=new Board(board,did1,"L");
		return temp;

	}
	public static Board Right(int[][] board,int n) {
		int k = 3;
		boolean did = false;
		boolean did1 = false;
		int lowest = 0;
		int numMoved = 0;
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

			rowScore[i] = Moves.GenList(board, 1, i);

			if (did) {
				rowScore[i].add(0, 1);
			} else
				rowScore[i].add(0, -1);
			if (did) {
				numMoved++;
				if (Moves.ListComp(rowScore[lowest], rowScore[i]) == 1
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
				board[lowest][0] = Integer.parseInt(GUI.Threes.getNext(n));
			} catch (NumberFormatException x) {
				if (GUI.Threes.next.size() != 0) {

				}
			}
			// addMove("R");
		}
		Board temp=new Board(board,did1,"R");
		return temp;

	}

	public static Board Up(int[][] board,int n) {
		boolean did = false;
		boolean did1 = did;
		int lowest = 0;
		int numMoved = 0;
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

			colScore[i] = Moves.GenList(board, 2, i);

			if (did)
				colScore[i].add(0, 1);
			else
				colScore[i].add(0, -1);

			if (did) {
				numMoved++;

				if (numMoved == 1)
					lowest = i;
				else if (Moves.ListComp(colScore[lowest], colScore[i]) == 1) {
					lowest = i;
				}
			}
			if (!did1)
				did1 = did;

		}

		if (did1) {
			// setNext(3, lowest);
			try {
				board[3][lowest] = Integer.parseInt(GUI.Threes.getNext(n));
			} catch (NumberFormatException s) {
				if (GUI.Threes.next.size() != 0) {

				}
			}
			// addMove("U");
		}
		Board temp=new Board(board,did1,"U");
		return temp;

	}

}
