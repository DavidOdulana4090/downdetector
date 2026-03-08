package datafilehandling;
import Exception. *;
import connection. *;

import java.sql.*;
import java.util.Scanner;

public class Readdatabase extends datasource{

    String url;
    String tablename;
    String columnname;
    String input;
    boolean result;
    ResultSet resultSet;

    httpconnection httpconnection = new httpconnection();

    public Readdatabase(){
        try(Scanner scanner = new Scanner(System.in)){
            System.out.print("Enter your full data base url : ");
            url = scanner.nextLine();
            System.out.print("SELECT [column] ");
            columnname = scanner.nextLine();
            System.out.print("FROM [table] ");
            tablename = scanner.nextLine();
            System.out.println("Create a report? (Y/N) : ");
            input = scanner.nextLine();

            loadsource(url);
        } catch (CustomIOException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void loadsource(String jdbc) throws CustomIOException {
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
                    String foundurl = resultSet.getString(columnname);
                    if (!input.equalsIgnoreCase("y")){
                        System.out.println(foundurl + " " + (httpconnection.isReachable(foundurl) ? "is reachable" : "is not reachable"));
                    } else {
                        createReport.log(foundurl, httpconnection.isReachable(foundurl), httpconnection.getStatusCode());
                    }
                }
            } catch (SQLException e) {
                throw new CustomRuntimeException("not a db connection " + e.getMessage());
            }
    }

    @Override
    public boolean exist() throws SQLException {
        if (resultSet != null && resultSet.isBeforeFirst()) {
            return true;
        } else {
            System.out.println("no data found ");
            return false;
        }
    }
}
