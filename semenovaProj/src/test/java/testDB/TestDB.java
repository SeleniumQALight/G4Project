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
    private Database mySqlDataBase;

    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        mySqlDataBase = MySQL_Database.getDataBase();
    }

    @After
    public void tearDown() throws SQLException {
        mySqlDataBase.quit();
    }

    @Test
    public void testDataFromDataBase() throws SQLException {
        List<Map<String, String>> dataFromSeleniumTable =
                mySqlDataBase.selectTableAsMap("select * from seleniumTable where login = 'G4Anna1'");
        System.out.println(dataFromSeleniumTable.get(0).get("passWord"));
        System.out.println(mySqlDataBase.selectTableAsMap("select * from seleniumTable where login = 'G4Anna1'"));
        int numberOfRows = mySqlDataBase.changeTable("INSERT INTO seleniumTable VALUE(777, 'G4Anna1', 'pass')");
        System.out.println(mySqlDataBase.selectTableAsMap("select * from seleniumTable where login = 'G4Anna1'"));
        System.out.println(numberOfRows);
    }
}
