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
    public void  setUp() throws SQLException, ClassNotFoundException {
        // open global connections
        mysqlDataBase = MySQL_Database.getDataBase();

    }

    @After
    public void tearDown() throws SQLException {
        // alt Enter to add Exceptions
        mysqlDataBase.quit();

    }

    @Test
    public void  testDataFromDataBase() throws SQLException {
        List<Map<String,String>> dataFromSeleniumTable =
                //select * from seleniumTable where login = 'G2Taras1'
                mysqlDataBase.selectTableAsMap("select * from seleniumTable");
        System.out.println(dataFromSeleniumTable);
        System.out.println(dataFromSeleniumTable.get(0).get("passWord"));
        System.out.println(mysqlDataBase.selectTableAsMap("select * from seleniumTable where login = 'LIO_Iren'"));
// insert
//        int numberOfRows = mysqlDataBase.changeTable("INSERT INTO seleniumTable VALUES(10703, 'LIO_Iren', 'newPass22')");
//        System.out.println(mysqlDataBase.selectTableAsMap("select * from seleniumTable where login = 'LIO_Iren'"));
//        System.out.println(numberOfRows);
//        System.out.println(mysqlDataBase.selectTableAsMap("select * from seleniumTable where login = 'LIO_Iren'"));

    }
}
