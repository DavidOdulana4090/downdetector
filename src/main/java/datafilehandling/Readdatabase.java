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
    httpconnection httpconnection = new httpconnection();

    public Readdatabase(){
        try(Scanner scanner = new Scanner(System.in)){
            System.out.println("Enter your full data base url : ");
            url = scanner.nextLine();
            System.out.println("SELECT : ");
            columnname = scanner.nextLine();
            System.out.println("FROM : ");
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
                    boolean result = httpconnection.isReachable(foundurl);

                    if (input.equalsIgnoreCase("y")){

                    } else {
                        System.out.println(foundurl + " " + (result ? "is reachable" : "is not reachable"));
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }

    @Override
    public String getfileSource() {
        return "";
    }
}
