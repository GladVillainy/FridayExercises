package friday_2026_02_20_test.persistence;

import ek.dk.friday_2026_02_20.entities.Member;
import ek.dk.friday_2026_02_20.exceptions.DatabaseException;
import ek.dk.friday_2026_02_20.exceptions.IllegalInputException;
import ek.dk.friday_2026_02_20.persistence.Database;
import ek.dk.friday_2026_02_20.persistence.MemberMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Uncomment the line below, to temporarily disable this test
//@Disabled
class MemberMapperTest {

    private final static String USER = "postgres";
    private final static String PASSWORD = "postgres";
    private final static String URL = "jdbc:postgresql://localhost:5432/sportsclub?currentSchema=test";

    private static Database db;
    private static MemberMapper memberMapper;

    @BeforeAll
    public static void setUpClass() {
        try {
            db = new Database(USER, PASSWORD, URL);
            memberMapper = new MemberMapper(db);
            try (Connection testConnection = db.connect())
            {
                try (Statement stmt = testConnection.createStatement())
                {
                    // The test schema is already created, so we only need to delete/create test tables
                    stmt.execute("DROP TABLE IF EXISTS test.registration");
                    stmt.execute("DROP TABLE IF EXISTS test.team");
                    stmt.execute("DROP TABLE IF EXISTS test.sport");
                    stmt.execute("DROP TABLE IF EXISTS test.member");
                    stmt.execute("DROP TABLE IF EXISTS test.zip");

                    stmt.execute("DROP SEQUENCE IF EXISTS test.member_member_id_seq CASCADE;");
                    stmt.execute("DROP SEQUENCE IF EXISTS test.sport_sport_id_seq CASCADE;");

                    // Create tables as copy of original public schema structure
                    stmt.execute("CREATE TABLE test.zip AS (SELECT * from public.zip) WITH NO DATA");
                    stmt.execute("CREATE TABLE test.sport AS (SELECT * from public.sport) WITH NO DATA");
                    stmt.execute("CREATE TABLE test.team AS (SELECT * from public.team) WITH NO DATA");
                    stmt.execute("CREATE TABLE test.member AS (SELECT * from public.member) WITH NO DATA");
                    stmt.execute("CREATE TABLE test.registration AS (SELECT * from public.registration) WITH NO DATA");

                    // Create sequences for auto generating id's for members and sports
                    stmt.execute("CREATE SEQUENCE test.member_member_id_seq");
                    stmt.execute("ALTER TABLE test.member ALTER COLUMN member_id SET DEFAULT nextval('test.member_member_id_seq')");
                    stmt.execute("CREATE SEQUENCE test.sport_sport_id_seq");
                    stmt.execute("ALTER TABLE test.sport ALTER COLUMN sport_id SET DEFAULT nextval('test.sport_sport_id_seq')");
                }
            }
            catch (SQLException throwables)
            {
                System.out.println(throwables.getMessage());
                fail("Database connection failed");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void setUp() {
        try (Connection testConnection = db.connect()) {
            try (Statement stmt = testConnection.createStatement() ) {
                // Remove all rows from all tables
                stmt.execute("DELETE FROM test.registration");
                stmt.execute("DELETE FROM test.team");
                stmt.execute("DELETE FROM test.sport");
                stmt.execute("DELETE FROM test.member");
                stmt.execute("DELETE FROM test.zip");

                // Reset the sequence number
                stmt.execute("SELECT setval('test.member_member_id_seq', 1)");

                // Insert rows
                stmt.execute("INSERT INTO test.zip VALUES " +
                        "(3700, 'Rønne'), (3730, 'Nexø'), (3740, 'Svanneke'), " +
                        "(3760, 'Gudhjem'), (3770, 'Allinge'), (3782, 'Klemmensker')");

                stmt.execute("INSERT INTO test.member (member_id, name, address, zip, gender, year) VALUES " +
                        "(1, 'Hans Sørensen', 'Agernvej 3', 3700, 'm', 2000), " +
                        "(2, 'Jens Kofoed', 'Agrevej 5', 3700, 'm', 2001), " +
                        "(3, 'Peter Hansen', 'Ahlegårdsvejen 7', 3700, 'm', 2002)");

                // Set sequence to continue from the largest member_id
                stmt.execute("SELECT setval('test.member_member_id_seq', COALESCE((SELECT MAX(member_id)+1 FROM test.member), 1), false)");
            }
        } catch (SQLException throwables) {
            fail("Database connection failed");
        }
    }

    @Test
    void testConnection() throws SQLException {
            assertNotNull(db.connect());
    }

    @Test
    void getAllMembers() throws DatabaseException {
        List<Member> members = memberMapper.getAllMembers();
        assertEquals(3, members.size());
        assertEquals(members.get(0), new Member(1,"Hans Sørensen", "Agernvej 3",3700, "Rønne","m",2000));
        assertEquals(members.get(1), new Member(2, "Jens Kofoed","Agrevej 5",3700,"Rønne","m",2001));
        assertEquals(members.get(2), new Member(3, "Peter Hansen","Ahlegårdsvejen 7",3700,"Rønne","m",2002));
    }

    @Test
    void getMemberById() throws DatabaseException {
        assertEquals(new Member(3, "Peter Hansen","Ahlegårdsvejen 7",3700,"Rønne","m",2002), memberMapper.getMemberById(3));
    }

    @Test
    void deleteMember() throws DatabaseException {
        assertTrue(memberMapper.deleteMember(2));
        assertEquals(2, memberMapper.getAllMembers().size());
    }

    @Test
    void insertMember() throws DatabaseException, IllegalInputException {
        Member m1 = memberMapper.insertMember(new Member("Jon Snow","Wintherfell 3", 3760, "Gudhjem", "m", 1992));
        assertNotNull(m1);
        assertEquals(4, memberMapper.getAllMembers().size());
        assertEquals(m1, memberMapper.getMemberById(4));
    }

    @Test
    void updateMember() throws DatabaseException {
        boolean result = memberMapper.updateMember(new Member(2, "Jens Kofoed","Agrevej 5",3760,"Gudhjem","m",1999));
        assertTrue(result);
        Member m1 = memberMapper.getMemberById(2);
        assertEquals(1999,m1.getYear());
        assertEquals(3, memberMapper.getAllMembers().size());
    }

}
