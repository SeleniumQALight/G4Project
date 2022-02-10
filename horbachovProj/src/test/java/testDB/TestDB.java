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
        List<Map<String, String>> dataFromSeleniumTable = mysqlDataBase.selectTableAsMap("select * from seleniumTable");
        System.out.println(dataFromSeleniumTable.get(0).get("passWord"));

//        System.out.println(mysqlDataBase.selectTableAsMap("select * from seleniumTable WHERE LOGIN = 'G4-L00PCHOCKER'"));
//        int numberOfRows = mysqlDataBase.changeTable("INSERT INTO seleniumTable VALUES(1398,'G4-L00PCHOCKER','HAHA')");
//
//        System.out.println(mysqlDataBase.selectTableAsMap("select * from seleniumTable WHERE LOGIN = 'G4-L00PCHOCKER'"));
//        System.out.println(numberOfRows);
    }
}
