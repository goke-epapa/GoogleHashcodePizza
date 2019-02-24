import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PizzaCutter {
    private static String[] datasets = new String[]{"a_example", "b_small", "c_medium", "d_big"};

    public static void main(String[] args) throws IOException {

        for (int i = 0; i < datasets.length; i++) {
            process(datasets[i]);
        }
    }

    private static void process(String filename) throws IOException {
        Pizza pizza = parseInput(filename);

        ArrayList<Slice> slices = solve(pizza);

        output(filename, slices);
    }

    private static void output(String filename, ArrayList<Slice> slices) throws IOException {
        FileWriter fileWriter = new FileWriter(filename + ".out");
        writelnToFile(fileWriter, slices.size() + "");
        for (Slice slice :
                slices) {
            writelnToFile(fileWriter, slice.toString());
        }
        closeOutputFile(fileWriter);
    }

    private static Pizza parseInput(String filename) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename + ".in"));

        String metadata = bufferedReader.readLine();

        String[] metaDataArr = metadata.split(" ");
        Pizza pizza = new Pizza();
        pizza.R = Integer.parseInt(metaDataArr[0]);
        pizza.C = Integer.parseInt(metaDataArr[1]);
        pizza.L = Integer.parseInt(metaDataArr[2]);
        pizza.H = Integer.parseInt(metaDataArr[3]);

        for (int i = 0; i < pizza.R; i++) {
            ArrayList<Character> row = new ArrayList<>();
            String line = bufferedReader.readLine();

            char[] chars = line.toCharArray();
            for (char c : chars) {
                row.add(c);
            }
            pizza.grid.add(row);
        }
        return pizza;
    }

    private static ArrayList<Slice> solve(Pizza pizza) {
        ArrayList<Slice> slices = new ArrayList<>();

        for (int row = 0; row < pizza.R; row++) {
            int numOfTomatoes = 0;
            int numOfMushrooms = 0;

            Point start = new Point(row, 0);

            int columnLength = pizza.H > pizza.C ? pizza.C : pizza.H;

            int presentColumn = 0;
            int endColumn = columnLength;
            int numOfIterations = 0;
            while (numOfIterations < pizza.C) {
                for (int column = presentColumn; column < endColumn; column++) {
                    if (pizza.grid.get(row).get(column) == 'T') {
                        numOfTomatoes++;
                    }

                    if (pizza.grid.get(row).get(column) == 'M') {
                        numOfMushrooms++;
                    }
                }

                if (numOfMushrooms >= pizza.L && numOfTomatoes >= pizza.L) {
                    slices.add(new Slice(start, new Point(row, endColumn - 1)));
                }

                numOfMushrooms = 0;
                numOfTomatoes = 0;
                presentColumn = endColumn;
                start = new Point(row, presentColumn);
                endColumn = (pizza.H > (pizza.R - endColumn)) ? (pizza.R - endColumn) + presentColumn : pizza.H + presentColumn;

                numOfIterations += endColumn;
            }
        }

        return slices;
    }

    private static void writelnToFile(FileWriter fileWriter, String string) throws IOException {
        fileWriter.append(string);
        fileWriter.append("\n");
    }

    private static void closeOutputFile(FileWriter fileWriter) throws IOException {
        fileWriter.close();
    }
}
