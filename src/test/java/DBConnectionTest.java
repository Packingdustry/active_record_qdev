import org.example.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import javax.xml.transform.Result;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class DBConnectionTest {

    @Test
    void verifierSingleton() throws ClassNotFoundException, SQLException {
        Connection connect = DBConnection.getConnection();
        Connection connect2 = DBConnection.getConnection();

        assertEquals(connect, connect2, "Les deux connect devraient être la même instance. ");
    }

    @Test
    void verifierClasseConnect() throws ClassNotFoundException, SQLException {
        Connection connect = DBConnection.getConnection();

        assertInstanceOf(java.sql.Connection.class, connect);
    }

    @Test
    void verifierSetNomDBNomDiff() throws ClassNotFoundException, SQLException {
        Connection connect = DBConnection.getConnection();

        DBConnection.setNomDB("test");
        Connection connect2 = DBConnection.getConnection();

        assertNotEquals(connect, connect2, "Les deux connect devraient être des instances différentes. ");
    }

    @Test
    void verifierSetNomDBMemeNom() throws ClassNotFoundException, SQLException {
        Connection connect = DBConnection.getConnection();

        DBConnection.setNomDB("testpersonne");
        Connection connect2 = DBConnection.getConnection();

        assertEquals(connect, connect2, "Les deux connect devraient être la même instance. ");
    }

    @Test
    void verifierSetNomDBChangement() throws ClassNotFoundException, SQLException {
        Connection connect = DBConnection.getConnection();

        DBConnection.setNomDB("test");
        Connection connect2 = DBConnection.getConnection();

        String SQLPrep = "SELECT DATABASE() dbname;";
        PreparedStatement prep1 = connect.prepareStatement(SQLPrep);
        prep1.execute();
        ResultSet rs = prep1.getResultSet();
        String dbname = "";
        while (rs.next()) {
            dbname = rs.getString("dbname");
        }

        SQLPrep = "SELECT DATABASE() dbname;";
        prep1 = connect2.prepareStatement(SQLPrep);
        prep1.execute();
        rs = prep1.getResultSet();
        String dbname2 = "";
        while (rs.next()) {
            dbname2 = rs.getString("dbname");
        }

        assertEquals("testpersonne", dbname, "La première base à laquelle on fait la requête devrait être `testpersonne`. ");
        assertEquals("test", dbname2, "La seconde base à laquelle on fait la requête devrait être `test`. ");
    }

    @AfterEach
    void resetDBName() throws ClassNotFoundException, SQLException {
        DBConnection.setNomDB("testpersonne");
    }
}