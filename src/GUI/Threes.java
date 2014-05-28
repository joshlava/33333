package GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * TO DO **********
 *New Tiles
 *fix the case 1203 -> 0033
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;

/*
 * @author joshlaverghetta
 */
public class Threes {
	static LinkedList<String> nums = new LinkedList<String>();
	public static LinkedList<String> next = new LinkedList<String>();
	static String moves = "";

	public static int[][] board = new int[4][4];

	public static void readFile() {
		try {
			Scanner sc = new Scanner(new FileReader("exampleinput.txt"));
			// sc.useDelimiter("\\t");
			sc.nextLine();
			sc.nextLine();
			int z = 0;

			while (sc.hasNext() && z < 16) {

				nums.add(sc.next());
				z++;

			}
			while (sc.hasNext()) {

				next.add(sc.next());
				z++;

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void printToFile() {
		try {

			File file = new File("out.txt");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.newLine();
			bw.write("Your score was: " + countScore());
			bw.newLine();
			bw.write(moves);
			bw.close();
			moves = "";

			System.out.println("Game Over");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String setBoard(int r, int c) {
		String Text = "";
		if (board[r][c] != 0)
			Text = Text + board[r][c];
		return Text;
	}

	public static void set() {
		int k = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				try {

					board[i][j] = Integer.parseInt(nums.get(k));

				} catch (NumberFormatException n) {
					board[i][j] = -1;
				}
				k++;
			}
		}

	}

	public static void setNext(int i, int j) {
		try {
			board[i][j] = Integer.parseInt(next.remove());
		} catch (NumberFormatException n) {
			if (next.size() != 0) {
				next.remove();
				setNext(i, j);
			}
		}

	}

	public static int countScore() {
		String score = "";
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
		score += count;
		return count;
	}

	public static void addMove(String move) {
		moves += move;
		moves += ",";
	}
public static void finalBoard(){
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 4; j++) {
			System.out.print(board[i][j] + " ");
		}
		System.out.println("");
	}
}
	public static void printArr(ArrayDeque moves) {
		System.out.println();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println("");

		}
		System.out.print("____________");

		ArrayDeque tempMoves = moves.clone();

		while (!tempMoves.isEmpty()) {
			System.out.print(tempMoves.remove());
		}
	}

	public static void printListArr(LinkedList moves) {
		System.out.println();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println("");

		}
		System.out.print("***********");

		LinkedList tempMoves = new LinkedList(moves);

		while (!tempMoves.isEmpty()) {
			int a = tempMoves.size();
			System.out.println("Size of List is: " + a);
			System.out.print(tempMoves.remove());
		}
	}

	public static void mess() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				board[i][j] = -1;
			}
		}
	}

	/*
	 * public static boolean Left() { boolean did = false; boolean did1 = false;
	 * int numMoved=0; int[] rowScore = new int[4]; int lowest = 0; for (int i =
	 * 0; i < 4; i++) { did = false; if (board[i][0] == 1 && board[i][1] == 2 ||
	 * board[i][0] == 2 && board[i][1] == 1) { board[i][0] = 3; board[i][1] =
	 * board[i][2]; board[i][2] = board[i][3]; board[i][3] = 0; did = true; }
	 * else if (board[i][0] == board[i][1] && board[i][1] > 2) { did = true;
	 * board[i][0] *= 2; board[i][1] = board[i][2]; board[i][2] = board[i][3];
	 * board[i][3] = 0; } else if ((board[i][2] > 2 && board[i][0] != 0) &&
	 * board[i][1] == board[i][2]) { board[i][1] *= 2; board[i][2] =
	 * board[i][3]; board[i][3] = 0; did = true; } else if ((board[i][2] != 0 &&
	 * board[i][0] != 0) && (board[i][1] == 1 && board[i][2] == 2 || board[i][1]
	 * == 2 && board[i][2] == 1)) { board[i][1] = 3; board[i][2] = board[i][3];
	 * board[i][3] = 0; did = true; } else if (board[i][3] > 2 && board[i][1] !=
	 * 0 && board[i][0] != 0 && board[i][2] == board[i][3]) {
	 * 
	 * board[i][2] *= 2; board[i][3] = 0; did = true; } else if (board[i][2] ==
	 * 1 && board[i][3] == 2 || board[i][2] == 2 && board[i][3] == 1 &&
	 * board[i][1] != 0 && board[i][0] != 0) { board[i][2] = 3; board[i][3] = 0;
	 * did = true;
	 * 
	 * } if(!did){ for (int j = 0; j < 3; j++) { if (board[i][j] == 0 &&
	 * board[i][j + 1] != 0) { board[i][j] = board[i][j + 1]; board[i][j + 1] =
	 * 0; did = true; } }} for (int j = 0; j < 4; j++) { rowScore[i] +=
	 * board[i][j];
	 * 
	 * } if (did) { numMoved++; if(numMoved==1||rowScore[i] <= rowScore[lowest])
	 * lowest=i;
	 * 
	 * } if (!did1) did1 = did;
	 * 
	 * }
	 * 
	 * if (did1) { setNext(lowest, 3); addMove("L"); } return (did1); }
	 */
	public static boolean Left() {
		boolean did = false;
		boolean did1 = false;
		int numMoved = 0;
		ArrayList<Integer>[] rowScore = new ArrayList[4];
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
			if (!did) {
				for (int j = 0; j < 3; j++) {
					if (board[i][j] == 0 && board[i][j + 1] != 0) {
						board[i][j] = board[i][j + 1];
						board[i][j + 1] = 0;
						did = true;
					}
				}
			}
			rowScore[i] = AI.Moves.GenList(board, 0, i);

			if (did) {
				rowScore[i].add(0, 1);
			} else
				rowScore[i].add(0, -1);
			if (!did1)
				did1 = did;
			  if (did) {
				  numMoved++; 
				  if(numMoved==1||AI.Moves.ListComp(rowScore[lowest] , rowScore[i])!=-1)
				  lowest=i;
			  
			  }
			 
			

		}
		

		if (did1) {
			setNext(lowest, 3);
			addMove("L");
		}
		return (did1);
	}

	public static boolean Right() {
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
			rowScore[i] = AI.Moves.GenList(board,1,i);

			if (did) {
				rowScore[i].add(0, 1);
				} else
					rowScore[i].add(0, -1);
			if (did) {
				numMoved++;
				if (AI.Moves.ListComp(rowScore[lowest],rowScore[i])==1 || numMoved == 1) {
					lowest = i;
				}
			}
			if (!did1)
				did1 = did;

		}
		
		if (did1) {
			setNext(lowest, 0);
			addMove("R");
		}
		return (did1);

	}

	public static boolean Up() {
		boolean did = false;
		boolean did1 = did;
		int lowest = 0;
		int numMoved = 0;
		ArrayList<Integer>[] colScore = new ArrayList[4];
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

			colScore[i] = AI.Moves.GenList(board,2,i);
			if(did)
				colScore[i].add(0,1);
			else
				colScore[i].add(0,-1);
					
			if (did) {
				numMoved++;

				if (numMoved == 1)
					lowest = i;
				else if (AI.Moves.ListComp(colScore[lowest],colScore[i])==1) {
					lowest = i;
				}
			}
			if (!did1)
				did1 = did;

		}

		if (did1) {
			setNext(3, lowest);
			addMove("U");
		}
		return (did1);

	}

	public static boolean Down() {
		int k = 3;
		boolean did = false;
		boolean did1 = did;
		int lowest = 0;
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
			colScore[i] =AI.Moves.GenList(board,3,i);
			
			
			if(did)
				colScore[i].add(0,1);
			else
				colScore[i].add(0,-1);
		if (did) {
			numMoved++;

			if (numMoved == 1)
				lowest = i;
			else if (AI.Moves.ListComp(colScore[lowest],colScore[i])!=-1) {
				lowest = i;
			}
		}
			if (!did1)
				did1 = did;

		}

		if (did1) {
			setNext(0, lowest);
			addMove("D");
		}
		return (did1);

	}

	/**
	 * @param args
	 *            the command line arguments
	 * 
	 *            public static void main(String[] args) { //
	 *            System.out.println(readFile()); readFile(); set(); printArr();
	 *            }
	 */

}
