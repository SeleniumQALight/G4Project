package testDB;

import libs.Database;
import libs.MySQL_Database;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class TestDB {

    private Database mysqlDataBase;

    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        mysqlDataBase = MySQL_Database.getDataBase();
    }

    @After
    public void tearDown() throws SQLException {
        mysqlDataBase.quit();
    }

    @Test
    public void testDataFromDataBase() throws SQLException {
        List<Map<String,String>> dataFromSeleniumTable = mysqlDataBase.selectTableAsMap("select * from seleniumTable where login = 'G2Taras1'");
        System.out.println(dataFromSeleniumTable);

       /* System.out.println(mysqlDataBase.selectTableAsMap("select * from seleniumTable where login = 'G4Roman'"));
        int numberOfRows = mysqlDataBase.changeTable("INSERT INTO seleniumTable VALUES(1234, 'G4Roman', 'Pa$$w0rd')");
        System.out.println(mysqlDataBase.selectTableAsMap("select * from seleniumTable where login = 'G4Roman'"));
        System.out.println(numberOfRows); */

    }
}
