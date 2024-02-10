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

	private static Map<String, String> cells = new HashMap<>();
	private static Map<String, Double> evaluatedResults = new HashMap<>();

	public static void main(String[] args)
	{
	    try {
	        processCSV("input.csv", "output.csv");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	private static void processCSV(String inputFileName, String outputFileName) throws IOException
	{
	    readCSV(inputFileName);
	    evaluateFormulas();
	    writeCSV(outputFileName);
	}

	private static void readCSV(String fileName) throws IOException {
	    try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
	    {
	        String line;
	        while ((line = br.readLine()) != null) {
	            String[] parts = line.split(", ");
	            for (String part : parts) {
	                String[] cell = part.split(": ");
	                cells.put(cell[0], cell[1]);
	            }
	        }
	    }
	}

	private static void evaluateFormulas() {
	    for (Map.Entry<String, String> entry : cells.entrySet())
	    {
	        String key = entry.getKey();
	        String value = entry.getValue();
	        if (value.startsWith("="))
	        {
	            String formula = value.substring(1);
	            double result = evaluateFormula(key, formula, new HashMap<>());
	            cells.put(key, String.valueOf(result));
	        }
	    }
	}

	private static double evaluateFormula(String currentCell, String formula, Map<String, Double> evaluated) {
	    if (evaluated.containsKey(formula))
	    {
	        return evaluated.get(formula);
	    }

	    String[] tokens = formula.split("\\+|\\-|\\*|\\/");
	    double result = 0;

	    for (String token : tokens)
	    {
	        if (cells.containsKey(token)) 
	        {
	            result += evaluateFormula(token, cells.get(token), evaluated);
	        } else
	        {
	            result += Double.parseDouble(token);
	        }
	    }

	    evaluated.put(formula, result);
	    return result;
	}

	private static void writeCSV(String fileName) throws IOException
	{
	    try (FileWriter writer = new FileWriter(fileName))
	    {
	        for (Map.Entry<String, String> entry : cells.entrySet())
	        {
	            writer.write(entry.getKey() + ": " + entry.getValue() + ", ");
	        }
	    }
	}
    
}

