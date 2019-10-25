package tictactoe;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[][] field = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = ' ';
            }
        }

        printField(field);
        int count = 0;
        while(true) {
            System.out.println("Enter the coordinates: ");
            String[] coordinates = scanner.nextLine().split(" ");
            if(coordinates[0].matches("\\d") && coordinates[0].matches("\\d")) {
                int j = Integer.parseInt(coordinates[0]) - 1;
                int i = Integer.parseInt(coordinates[1]) - 1;
                if ((i >= 0 && i <= 2) && (j >= 0 && j <= 2)) {
                    if (i != 1) {
                        i = i == 0 ? 2 : 0;
                    }
                    if (field[i][j] == ' ') {
                        if(count % 2 == 0) {
                            field[i][j] = 'X';
                        } else {
                            field[i][j] = 'O';
                        }
                        printField(field);
                        count++;

                        boolean winX = checkRows(field, 'X') || checkColumns(field, 'X') || checkDiagonals(field, 'X');
                        boolean winO = checkRows(field, 'O') || checkColumns(field, 'O') || checkDiagonals(field, 'O');
                        if (winO) {
                            System.out.println("O wins");
                            break;
                        } else if (winX) {
                            System.out.println("X wins");
                            break;
                        } else if (count == 9) {
                            System.out.println("Draw");
                            break;
                        }
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            } else {
                System.out.println("You should enter numbers!");
            }
        }
    }

    private static void printField(char[][] field) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private static boolean check(char cell1, char cell2, char cell3) {
        return ((cell1 != ' ') && (cell1 == cell2) && (cell2 == cell3));
    }
    private static boolean checkRows(char[][] field, char cell) {
        for(int i = 0; i < 3; i++) {
            if (check(field[i][0], field[i][1], field[i][2]) && field[i][0] == cell) {
                return true;
            }
        }
        return false;
    }
    private static boolean checkColumns(char[][] field, char cell) {
        for(int i = 0; i < 3; i++) {
            if (check(field[0][i], field[1][i], field[2][i]) && field[0][i] == cell) {
                return  true;
            }
        }
        return false;
    }
    private static boolean checkDiagonals(char[][] field, char cell) {
        if ((check(field[0][0], field[1][1], field[2][2]) && field[0][0] == cell) ||
                (check(field[0][2], field[1][1], field[2][0]) && field[0][2] == cell)) {
            return true;
        }
        return false;
    }
}
