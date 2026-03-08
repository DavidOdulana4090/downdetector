package datafilehandling;
import Exception. *;
import connection. *;

import java.sql.*;
import java.util.Scanner;

public class ReadJdbc extends AbstractSource {

    String url;
    String tablename;
    String columnname;
    String input;
    boolean result;
    ResultSet resultSet;

    HttpConnection httpconnection = new HttpConnection();

    public ReadJdbc(){
        try(Scanner scanner = new Scanner(System.in)){
            System.out.print("Enter your full data base url : ");
            url = scanner.nextLine();
            System.out.print("SELECT [column] ");
            columnname = scanner.nextLine();
            System.out.print("FROM [table] ");
            tablename = scanner.nextLine();
            System.out.println("Create a report? (Y/N) : ");
            input = scanner.nextLine();

            loadSource(url);
        } catch (CustomIOException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void loadSource(String jdbc) throws CustomIOException {
            try(Connection dbconnection = DriverManager.getConnection(url)){

                Statement statement = dbconnection.createStatement();
                StringBuilder sqlcommand = new StringBuilder();
                sqlcommand.append("SELECT ");
                sqlcommand.append(columnname);
                sqlcommand.append(" FROM ");
                sqlcommand.append(tablename);

                System.out.println( sqlcommand.toString() + "\n");

                resultSet = statement.executeQuery(sqlcommand.toString());

                while (resultSet.next()) {
                    processResult(resultSet.getString(columnname));
                }
                System.out.println("Finished.");
            } catch (SQLException e) {
                throw new CustomRuntimeException("not a db connection " + e.getMessage());
            }
    }

    @Override
    public void processResult(String line) throws CustomIOException {
        if (!input.equalsIgnoreCase("y")){
            System.out.println(line + " " + (httpconnection.isReachable(line) ? "is reachable" : "is not reachable"));
        } else {
            CreateReport.log(line, httpconnection.isReachable(line), httpconnection.getStatusCode());
        }
    }
}
