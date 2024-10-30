import java.util.ArrayList;

public class GameMomentumAB extends NodeGameAB {

	private int[][] board = new int[7][7];
	private int myColor;

	public GameMomentumAB(String node) {
		super(1);
		myColor = getPlayer();
		processNode(node);
	}

	public GameMomentumAB(int[][] p, int myColor, int depth) {
		super(depth);
		for (int l = 0; l < 7; l++)
			for (int c = 0; c < 7; c++)
				this.board[l][c] = p[l][c];
		this.myColor = myColor;
	}

	public void processNode(String node) {
		String[] v = node.trim().split(" ");
		for (int l = 0; l < 7; l++)
			for (int c = 0; c < 7; c++)
				try {
					board[l][c] = Integer.parseInt(v[l * 7 + c]);
				} catch (Exception ex) {
					ex.printStackTrace();
					System.out.println("board " + v + "  l " + l + "  c " + c);
				}
	}

	@Override
	public ArrayList<Move> expandAB() {
		ArrayList<Move> suc = new ArrayList<>();
		// Generate all possible moves based on the current board state.
		for (int l = 0; l < 7; l++) {
			for (int c = 0; c < 7; c++) {
				// Check if the cell is empty (represented by 0)
				if (board[l][c] == 0) {
					// Create a new move
					int[][] newBoard = makeCopy(board);
					newBoard[l][c] = myColor; // Place the player's color
					Move move = new Move("Move to (" + l + ", " + c + ")", new GameMomentumAB(newBoard, myColor, getDepth() + 1));
					suc.add(move);
				}
			}
		}
		return suc;
	}

	@Override
	public double getH() {
		double h = 0;

		for (int l = 0; l < 7; l++) {
			for (int c = 0; c < 7; c++) {
				if (board[l][c] == myColor) {
					h++;
				}
			}
		}
		return h;
	}


	private int[][] makeCopy(int[][] p) {
		int[][] np = new int[7][7];
		for (int l = 0; l < 7; l++)
			for (int c = 0; c < 7; c++)
				np[l][c] = p[l][c];
		return np;
	}

	public void setMyColor(int color) {
		myColor = color;
	}

	public String toString() {
		String st = "";
		for (int l = 0; l < 7; l++) {
			for (int c = 0; c < 7; c++) {
				st += " " + (board[l][c] == 0 ? "." : "" + (board[l][c]));
			}
			st += "\n";
		}
		st += "\n";
		return st;
	}

	// for testing
	public static void main(String[] args) {
		GameMomentumAB jogo = new GameMomentumAB("0 0 0 0 0 0 0 " + "0 0 1 0 0 0 0 " + "0 0 1 0 0 0 0 "
				+ "0 0 0 2 2 2 2 " + "0 0 0 0 0 0 0 " + "0 0 0 0 0 0 0 " + "0 0 0 0 0 0 0 ");
		jogo.setMyColor(1);
		ArrayList<Move> suc = jogo.expandAB();
		for (Move j : suc)
			System.out.println(j);
	}

}
