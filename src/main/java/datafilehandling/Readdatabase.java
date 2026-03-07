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

    httpconnection httpconnection = new httpconnection();

    public Readdatabase(){
        try(Scanner scanner = new Scanner(System.in)){
            System.out.print("Enter your full data base url : ");
            url = scanner.nextLine();
            System.out.print("SELECT ");
            columnname = scanner.nextLine();
            System.out.print("FROM ");
            tablename = scanner.nextLine();
            System.out.println("Create a report? (Y/N) : ");
            input = scanner.nextLine();

            loadsource(url);
        } catch (CustomIOException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void loadsource(String dburl) throws CustomIOException {
            try(Connection dbconnection = DriverManager.getConnection(url)){

                Statement statement = dbconnection.createStatement();
                StringBuilder sqlcommand = new StringBuilder();
                sqlcommand.append("SELECT ");
                sqlcommand.append(columnname);
                sqlcommand.append(" FROM ");
                sqlcommand.append(tablename);

                System.out.println( sqlcommand.toString() + "\n");

                ResultSet resultSet = statement.executeQuery(sqlcommand.toString());

                while (resultSet.next()) {
                    String foundurl = resultSet.getString("url");
                    result = httpconnection.isReachable(foundurl);

                    if (!input.equalsIgnoreCase("y")){
                        System.out.println(foundurl + " " + (result ? "is reachable" : "is not reachable"));
                    } else {
                        createReport.log(foundurl, httpconnection.isReachable(foundurl), httpconnection.getStatusCode(), httpconnection.getResponse());
                    }
                }
            } catch (SQLException e) {
                throw new CustomRuntimeException("runtime error " + e.getMessage());
            }
    }

    @Override
    public String getfileSource() {
        return columnname;
    }
}
