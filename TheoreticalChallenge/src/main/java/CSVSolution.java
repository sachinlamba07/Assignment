package main.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVSolution {

   
    private static void evaluateCSV(List<String[]> rows) 
    {
        for (int i = 0; i < rows.size(); ++i) {
            for (int j = 0; j < rows.get(i).length; ++j) {
                rows.get(i)[j] = evaluateCell(rows.get(i)[j], rows);
            }
        }
    }

    
    private static String evaluateCell(String cell, List<String[]> rows) {
        if (cell.startsWith("=")) {
            String formula = cell.substring(1);
            return evaluateFormula(formula, rows);
        } else {
            return cell;
        }
    }
    
    
    public static void main(String[] args) throws IOException {
        useCSV("input.csv");
    }

    public static void useCSV(String fileName) throws IOException {
        
            // Read input CSV file
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            List<String[]> rows = new ArrayList<>();
            String line;
            try {
				while ((line = reader.readLine()) != null) {
				    rows.add(line.split(", "));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            evaluateCSV(rows);

            BufferedWriter wr = new BufferedWriter(new FileWriter("output.csv"));
            for (String[] row : rows)
            {
                wr.write(String.join(", ", row));
                wr.newLine();
            }
            wr.close();

            System.out.println("CSV file saved succsfully.");
        
    }

    
   

   
    private static String evaluateFormula(String formula, List<String[]> rows) {
        String[] parts = formula.split("\\+");
        int sum = 0;
        for (String part : parts) {
          
                String[] coordinates = part.split("(?<=\\D)(?=\\d)");
                int row = Integer.parseInt(coordinates[1]) - 1;
                int col = coordinates[0].charAt(0) - 'A';
                String value = evaluateCell(rows.get(row)[col], rows);
                sum += Integer.parseInt(value);
            
        }
        return String.valueOf(sum);
    }

 
    
}

