package testDataBase;

import libs.Database;
import libs.MySQL_Database;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class TestDB {
    private Database mySqlDb;
    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        mySqlDb = MySQL_Database.getDataBase();

    }

    @After
    public void tearDown() throws SQLException {
        mySqlDb.quit();
    }

    @Test
    public void testDataFromDb() throws SQLException{
        List<Map<String, String>> dataFromSeleniumTable=
            mySqlDb.selectTableAsMap("select * from seleniumTable where login = 'G2Taras1'");

        System.out.println(dataFromSeleniumTable);
//        System.out.println(mySqlDb.selectTableAsMap("select * from seleniumTable where login = 'G4Iryna1'"));
//        int numberOfRows = mySqlDb.changeTable("INSERT INTO seleniumTable VALUES(123, 'G4Iryna1', 'password')");
//        System.out.println(mySqlDb.selectTableAsMap("select * from seleniumTable where login = 'G4Iryna1'"));
//        System.out.println(numberOfRows);
        }

    }





