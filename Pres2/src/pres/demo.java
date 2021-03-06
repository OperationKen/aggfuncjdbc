package pres;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class demo {
	
	static String url = "jdbc:mysql://localhost:3306/presentation2";
	static String username = "root";
	static String password = "root";
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url, username, password);
		Statement stmt = conn.createStatement();
		
		Scanner inputter = new Scanner(System.in);
		
		menu();
		
		int input = 0;
		do {
			
			System.out.print("Input: ");
			input = inputter.nextInt();
			
			switch(input) {
			case 1:
				System.out.println("SELECT SUM(grade) FROM class_grades;");
				sum(stmt);
				System.out.println();
				break;
			case 2:
				System.out.println("SELECT AVG(grade) FROM class_grades;");
				avg(stmt);
				System.out.println();
				break;
			case 3:
				System.out.println("SELECT MIN(grade) FROM class_grades;");
				min(stmt);
				System.out.println();
				break;
			case 4:
				System.out.println("SELECT MAX(grade) FROM class_grades;");
				max(stmt);
				System.out.println();
				break;
			case 5:
				System.out.println("SELECT COUNT(grade) FROM class_grades;");
				count(stmt);
				System.out.println();
				break;
			case 6:
				System.out.println("SELECT COUNT(class) FROM class_grades WHERE class = 1;");
				System.out.println("SELECT COUNT(class) FROM class_grades WHERE class = 2;");
				simpleCondition(stmt);
				System.out.println();
				break;
			case 7:
				System.out.println("SELECT name FROM class_grades GROUP BY name HAVING SUM(grade) < 70;");
				simpleConditionSecond(stmt);
				System.out.println();
				break;
			}
			
		}while(input != 0);

		conn.close();
		System.exit(0);
	}
	
	public static void sum(Statement stmt) throws ClassNotFoundException, SQLException {
		String querySum = "SELECT SUM(grade) FROM class_grades";
		ResultSet sumNum = stmt.executeQuery(querySum);
		while(sumNum.next()) {
			System.out.println("sum() = " + sumNum.getInt(1));
		}
	}

	public static void avg(Statement stmt) throws ClassNotFoundException, SQLException {
		String queryAvg = "SELECT AVG(grade) FROM class_grades";
		ResultSet avgNum = stmt.executeQuery(queryAvg);
		while(avgNum.next()) {
			System.out.println("avg() = " + avgNum.getFloat(1));
		}
	}

	public static void min(Statement stmt) throws ClassNotFoundException, SQLException {
		String queryMin = "SELECT MIN(grade) FROM class_grades";
		ResultSet minNum = stmt.executeQuery(queryMin);
		while(minNum.next()) {
			System.out.println("min() = " + minNum.getInt(1));
		}
	}
	
	public static void max(Statement stmt) throws ClassNotFoundException, SQLException {
		String queryMax = "SELECT MAX(grade) FROM class_grades";
		ResultSet maxNum = stmt.executeQuery(queryMax);
		while(maxNum.next()) {
			System.out.println("max() = " + maxNum.getInt(1));
		}
	}
	
	public static void count(Statement stmt)throws ClassNotFoundException, SQLException {
		String queryCount = "SELECT COUNT(grade) FROM class_grades";
		ResultSet countStudents = stmt.executeQuery(queryCount);
		while(countStudents.next()) {
			System.out.println("count() = " + countStudents.getInt(1));
		}
	}
	
	public static void simpleCondition(Statement stmt)throws ClassNotFoundException, SQLException {
		String queryCountClassOne = "SELECT COUNT(class) FROM class_grades WHERE class = 1";
		ResultSet classOne = stmt.executeQuery(queryCountClassOne);
		while(classOne.next()) {
			System.out.println("# of students in class 1 = " + classOne.getInt(1));
		}
		
		String queryCountClassTwo = "SELECT COUNT(class) FROM class_grades WHERE class = 2";
		ResultSet classTwo = stmt.executeQuery(queryCountClassTwo);
		while(classTwo.next()) {
			System.out.println("# of students in class 2 = " + classTwo.getInt(1));
		}
	}
	
	public static void simpleConditionSecond(Statement stmt)throws ClassNotFoundException, SQLException {
		String queryCountClassOne = "SELECT name from class_grades group by name having sum(grade) < 70";
		ResultSet classOne = stmt.executeQuery(queryCountClassOne);
		while(classOne.next()) {
			System.out.println(classOne.getString(1));
		}
	}
	
	public static void menu() {
		System.out.println("Press 1-7 for a demonstration of the aggregate function:");
		System.out.println("1. sum()");
		System.out.println("2. avg()");
		System.out.println("3. min()");
		System.out.println("4. max()");
		System.out.println("5. count()");
		System.out.println("6. Conditional - WHERE clause");
		System.out.println("7. Conditional - HAVING clause and GROUP BY statement");
		System.out.println("0. Terminate the program");
		System.out.println("---------------------------------------------------------");
	}
}
