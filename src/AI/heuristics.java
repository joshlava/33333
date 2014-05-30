package AI;

/**
 * 
 * @author Josh La Verghetta 20762905
 * @author Andrew Edwards 20937641 A class used for calculation our heuristic
 *         scores.
 */
public class heuristics {
	/**
	 * 
	 * @param board
	 * @return the number of whitespaces on the board
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

	/**
	 * 
	 * @param board
	 * @return the number of 1's and 2's on the board, this is subtracted from
	 *         the score
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
		int multi = rat;

		double ran = Math.random() * 1000;
		hscore[1] = CountWhite(board) * (rat) - num12(board) * rat
				+ getBig(board) * rat /*
									 * + board[0][0] * multi + (multi - multi /
									 * 10) * board[0][1] + (multi - multi / 10)
									 * board[1][0] + (multi - multi / 10) *
									 * board[1][1]
									 */+ hscore[0] * rat + numCom(board)// +(int)ran
		;
		// hscore[1]=CountWhite(board)*rat+hscore[0]*rat;
		// hscore[1]=numCom(board);
		// hscore[1]=-getBig(board);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				hscore[1] += board[i][j] * multi / (1 + i + j);
			}
		}
		hscore[1] = newHeur(board) * rat + hscore[0];
		// hscore[1]=CountWhite(board);
		return hscore;
	}

	/**
	 * 
	 * @param board
	 * @return our main heuristic function, this has 3 main functions, 1.
	 *         monotonicity, we try and keep the baord tiles in decreasing order
	 *         from top->bottom and left->right 2. smoothness, we try and keep
	 *         like tiles next to each other so they can be easily combined 3.
	 *         amount of whitespace, we try and maximise the amount of
	 *         whitespace on the board, the hscore assigned to whitespace is
	 *         also increased as the games scored increases
	 */
	public static int newHeur(int[][] board) {
		int heur = 0;
		int z = CountWhite(board) ;
		int y=z* countScore(board) / 6;
	//	heur += z;
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++)
				if (board[i][j] > board[i][j + 1]) {
					heur += board[i][j];
				}

		}
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 3; i++)
				if (board[i][j] > board[i + 1][j]) {
					heur += board[i][j];

				}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++)
				if (board[i][j] == board[i][j + 1] && board[i][j] > 2) {
					heur += board[i][j];
				}

		}
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 3; i++)
				if (board[i][j] == board[i + 1][j] && board[i][j] > 2) {
					heur += board[i][j];

				}
		}

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++)
				if (board[i][j] > board[i][j + 1] * 4)
					heur -= board[i][j + 1];
				else if (4 * board[i][j] < board[i][j + 1]) {
					heur -= board[i][j];
				}
		}
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 3; i++)
				if (board[i][j] > board[i + 1][j] * 4)
					heur -= board[i + 1][j];
				else if (board[i][j] * 4 < board[i + 1][j]) {
					heur -= board[i][j];
				}

		}
		if(z>5)
			heur=heur*2;
		heur+=y;

		return heur;

	}

	/**
	 * 
	 * @param board
	 * @return the number of addjacent tiles that can be combined
	 */
	public static int numCom(int[][] board) {
		int score = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] > 2 && board[i][j] == board[i][j + 1])
					score += board[i][j];

			}
		}
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 3; i++) {
				if (board[i][j] > 2 && board[i][j] == board[i + 1][j])
					score += board[i][j];

			}
		}
		return score;

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

	/**
	 * 
	 * @param board
	 * @return a function that returns the distance between the 2 biggest tiles
	 */
	public static int getBig(int[][] board) {
		int max = 0;
		int[] macord = { 0, 0 };
		int[] ma2cord = { 0, 0 };
		int max2 = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j] > max) {
					max2 = max;
					ma2cord[0] = i;
					ma2cord[1] = j;
					max = board[i][j];
					macord[0] = i;
					macord[1] = j;
				}
			}

		}
		int dist = macord[0] - ma2cord[0] + macord[1] - ma2cord[1];
		return dist;
	}

}
