package ek.dk.friday_2026_02_20.persistence;

import ek.dk.friday_2026_02_20.entities.Member;
import ek.dk.friday_2026_02_20.exceptions.DatabaseException;
import ek.dk.friday_2026_02_20.exceptions.IllegalInputException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberMapper {


        private Database database; 

        public MemberMapper(Database database) {
            this.database = database;
        }

        public List<Member> getAllMembers() throws DatabaseException {

            List<Member> memberList = new ArrayList<>();

            String sql = "select member_id, name, address, m.zip, gender, city, year " +
                         "from member m inner join zip using(zip) " +
                         "order by member_id";

            try (Connection connection = database.connect()) {
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        int memberId = rs.getInt("member_id");
                        String name = rs.getString("name");
                        String address = rs.getString("address");
                        int zip = rs.getInt("zip");
                        String city = rs.getString("city");
                        String gender = rs.getString("gender");
                        int year = rs.getInt("year");
                        memberList.add(new Member(memberId, name, address, zip, city, gender, year));
                    }
                } catch (SQLException throwables) {
                    throw new DatabaseException("Could not get all members from database");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new DatabaseException("Could not establish connection to database");
            }
            return memberList;
        }

        public Member getMemberById(int memberId) throws DatabaseException {
            Member member = null;

            String sql =  "select member_id, name, address, m.zip, gender, city, year " +
            "from member m inner join zip using(zip) " +
            "where member_id = ?";

            try (Connection connection = database.connect()) {
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setInt(1, memberId);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        memberId = rs.getInt("member_id");
                        String name = rs.getString("name");
                        String address = rs.getString("address");
                        int zip = rs.getInt("zip");
                        String city = rs.getString("city");
                        String gender = rs.getString("gender");
                        int year = rs.getInt("year");
                        member = new Member(memberId, name, address, zip, city, gender, year);
                    } else {
                        throw new DatabaseException("Member with id = " + memberId + " is not found");
                    }
                } catch (SQLException ex) {
                    throw new DatabaseException("Could not find member with id = " + memberId + " in database");
                }
            } catch (SQLException ex) {
                throw new DatabaseException("Could not establish connection to database");
            }
            int a = 1;
            return member;
        }

        public boolean deleteMember(int memberId) throws DatabaseException {
            boolean result = false;
            String sql = "delete from member where member_id = ?";
            try (Connection connection = database.connect()) {
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setInt(1, memberId);
                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected == 1){
                        result = true;
                    } else {
                        throw new DatabaseException("Member with id = " + memberId + " could not be deleted");
                    }
                } catch (SQLException throwables) {
                    throw new DatabaseException("Could not delete member with id = " + memberId + " in database");
                }
            } catch (SQLException throwables) {
                throw new DatabaseException("Could not establish connection to database");
            }
            return result;
        }

        public Member insertMember(Member member) throws DatabaseException, IllegalInputException {
            boolean result = false;
            int newId = 0;
            String sql = "insert into member (name, address, zip, gender, year) values (?,?,?,?,?)";
            try (Connection connection = database.connect()) {
                try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS )) {
                    ps.setString(1, member.getName());
                    ps.setString(2, member.getAddress());
                    ps.setInt(3, member.getZip());
                    if (member.getGender().equals("m") || member.getGender().equals("f")) {
                        ps.setString(4, member.getGender());
                    } else {
                        throw new IllegalInputException("Error inserting new member. Gender should be 'm' or 'f'");
                    }
                    ps.setInt(5, member.getYear());
                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected == 1){
                        result = true;
                    } else {
                        throw new DatabaseException("Member with name = " + member.getName() + " could not be inserted into database");
                    }
                    ResultSet idResultset = ps.getGeneratedKeys();
                    if (idResultset.next()){
                        newId = idResultset.getInt(1);
                        member.setMemberId(newId);
                    } else {
                        member = null;
                    }
                } catch (SQLException throwables) {
                    throw new DatabaseException("Could not insert member in database");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new DatabaseException("Could not establish connection to database");
            }
            return member;
        }

        public boolean updateMember(Member member) throws DatabaseException {
            boolean result = false;
            String sql =    "update member " +
                            "set name = ?, address = ?, zip = ?, gender = ?, year = ? " +
                            "where member_id = ?";
            try (Connection connection = database.connect()) {
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setString(1, member.getName());
                    ps.setString(2, member.getAddress());
                    ps.setInt(3, member.getZip());
                    ps.setString(4, member.getGender());
                    ps.setInt(5, member.getYear());
                    ps.setInt(6, member.getMemberId());
                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected == 1){
                        result = true;
                    } else {
                        throw new DatabaseException("Member with id = " + member.getMemberId() + " could not be updated");
                    }
                } catch (SQLException ex) {
                    throw new DatabaseException("Could not insert member in database");
                }
            } catch (SQLException ex) {
                throw new DatabaseException("Could not establish connection to database");
            }
            return result;
        }
}
