package testDB;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import libs.Database;
import libs.MySQL_Database;

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
        List<Map<String,String>> dataFromSeleniumTable =
              mysqlDataBase.selectTableAsMap("select * from seleniumTable where login = 'G2Taras1'");
        System.out.println(dataFromSeleniumTable.get(0).get("passWord"));

//        System.out.println(mysqlDataBase.selectTableAsMap("select * from seleniumTable where login = 'G4Taras'"));
//        int numberOfRows = mysqlDataBase.changeTable("INSERT INTO seleniumTable  VALUES(777, 'G4Taras', 'pass')");
//
//        System.out.println(mysqlDataBase.selectTableAsMap("select * from seleniumTable where login = 'G4Taras'"));
//        System.out.println(numberOfRows);
    }
}
