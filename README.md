# Prospecta Assignment

# Coding Challenge
## Functionalities

**1. Retrieve API Data by Category:** Obtain a list of public APIs filtered based on a specified category.
##
**2. Simulate Adding New API Entries:** Simulate the addition of new API entries to the system without actually posting data to the public APIs database.
##
### Fetching APIs by Category
To acquire a list of APIs within a specific category, utilize the following endpoint:

```GET http://localhost:8080/byCategory?category=Anime```

### Adding a New API Entry
To simulate the addition of a new API entry, make use of the following endpoint:

POST http://localhost:8080/AddEntry

**Note: The application runs on port 8080, so adjust the URL accordingly if necessary.**

# Theoretical Challenge
### CSV Calculator
##
This program processes data from a CSV file, performs calculations on specified formulas, and generates a CSV output containing the results.

### Usage

Compile the Main.java file using a Java compiler:

```javac Main.java```

Run the compiled program:

```java Main```

Provide the input CSV file named Prospecta.csv located in the src directory.
The program will compute the formulas, generate an output CSV file named Output.csv in the src directory, and print the calculated results to the console.

### Input CSV Format
The input CSV file should include two columns: cell references and their corresponding values or formulas.

#### Example:
```
A1,5
A2,7
A3,9
B1,3
B2,8
B3,=4+5
C1,=5+A1
C2,=A2+B2
C3,=C2+B3
```



