package datafilehandling;
import Exception. *;

import java.sql.SQLException;

abstract class datasource {

    public abstract void loadsource(String sourcename) throws CustomIOException, CustomFileNotFoundException;

    public abstract boolean exist() throws SQLException;

}
