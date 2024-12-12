import java.util.ArrayList;

public class GameMomentumAB extends NodeGameAB {
	private int[][] board = new int[7][7];
	private int myColor;
	private static final int BOARD_SIZE = 7;//Criei uma variável  para não ter de escrever 7. É mais correto!

	public GameMomentumAB(String node) {
		super(1);
		myColor = getPlayer();
		processNode(node);
	}

	public GameMomentumAB(int[][] p, int myColor, int depth) {
		super(depth);
		this.board = makeCopy(p);
		this.myColor = myColor;
	}

	public void processNode(String node) {
		String[] v = node.trim().split(" ");
		for (int l = 0; l < 7; l++) {
			for (int c = 0; c < 7; c++) {
				board[l][c] = Integer.parseInt(v[l * 7 + c]);
			}
		}
	}

	@Override
	public ArrayList<Move> expandAB() {
		ArrayList<Move> moves = new ArrayList<>();
		for (int l = 0; l < BOARD_SIZE; l++) {
			for (int c = 0; c < BOARD_SIZE; c++) {
				if (board[l][c] == 0) {
					int[][] newBoard = makeCopy(board);
					newBoard[l][c] = myColor;

					// Gerando o movimento
					Move move = new Move("Move to (" + l + ", " + c + ")", new GameMomentumAB(newBoard, myColor, getDepth() + 1));

					// Imprimir o pedido antes de enviar ao servidor
					System.out.println("Pedido do jogador para o servidor: Move to (" + l + ", " + c + ")");

					// Adiciona o movimento à lista
					moves.add(move);
				}
			}
		}
		return moves;
	}

	@Override
	public double getH() {
		double h = 0;
		for (int[] row : board) {
			for (int cell : row) {
				if (cell == myColor) h++;
			}
		}
		return h;
	}

	private int[][] makeCopy(int[][] original) {
		int[][] copy = new int[BOARD_SIZE][BOARD_SIZE];
		for (int l = 0; l < BOARD_SIZE; l++) {
			System.arraycopy(original[l], 0, copy[l], 0, BOARD_SIZE);
		}
		return copy;
	}

	public void setMyColor(int color) {
		myColor = color;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int[] row : board) {
			for (int cell : row) {
				sb.append(cell == 0 ? "." : cell).append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
