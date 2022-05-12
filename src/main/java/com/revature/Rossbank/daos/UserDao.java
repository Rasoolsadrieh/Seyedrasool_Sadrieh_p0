package com.revature.Rossbank.daos;

import com.revature.Rossbank.exceptions.ResourcePersistanceException;
import com.revature.Rossbank.models.BankUser;
import com.revature.Rossbank.util.ConnectionFactory;
import com.revature.Rossbank.util.logging.Logger;

import java.io.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDao implements Crudable<BankUser>{

    private Logger logger = Logger.getLogger();

    @Override
    public BankUser create(BankUser newBankUser) {
        System.out.println("Here is the new user as it enters our DAO layer: "+ newBankUser); // What happens here? Java knows to invoke the toString() method when printing the object to the terminal

        try(Connection conn = ConnectionFactory.getInstance().getConnection();) {

            // NEVER EVER EVER EVER EVER concatenate or directly use these strings inside of the sql statement
            // String sql = "insert into trainer value (" + newTrainer.getFname() + "," + newTrainer.getLname();

            // The commented out sql String is using default for auto-generating the ID ifyou used serial
            // String sql = "insert into trainer values (default, ?, ?, ?, ?, ?)"; // incomplete sql statement, with default if not specifiying columns
            String sql = "insert into Rossbank.user (fname, lname, email, password, dob) values (?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            System.out.println(newBankUser.getFname());
            System.out.println(newBankUser.getLname());

            // 1-indexed, so first ? starts are 1
            ps.setString(1, newBankUser.getFname());
            ps.setString(2, newBankUser.getLname());
            ps.setString(3, newBankUser.getEmail());
            ps.setString(4, newBankUser.getPassword());
            ps.setString(5, newBankUser.getDob());

            int checkInsert = ps.executeUpdate();

            if(checkInsert == 0){
                throw new ResourcePersistanceException("User was not entered into database due to some issue.");
            }

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return newBankUser;
    }

    @Override
    public List<BankUser> findAll() throws IOException {

        List<BankUser> bankUsers = new LinkedList<>();

        // TODO: we're trying something here and passing an argumetn ???
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) { // try with resources, because Connection extends the interface Auto-Closeable

            String sql = "select * from trainer";
            Statement s = conn.createStatement();

        // conn.createStatement().executeQuery("select * from trainer"); fine but generally not used
            // TODO: Hey why are we using the sql variable string here?
            ResultSet rs =s.executeQuery(sql);

            while (rs.next()) { // the last line of the file is null
                BankUser bankUser = new BankUser();

                bankUser.setFname(rs.getString("fname")); // this column lable MUST MATCH THE DB
                bankUser.setLname(rs.getString("lname"));
                bankUser.setDob(rs.getString("dob"));
                bankUser.setPassword(rs.getString("password"));
                bankUser.setEmail(rs.getString("email"));

                bankUsers.add(bankUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }



        System.out.println("Returning user information to user.");
        return bankUsers;
    }

    @Override
    public BankUser findById(String id) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection();){

            String sql = "select * from trainer where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(id)); // Wrapper class example

            ResultSet rs = ps.executeQuery(); // remember dql, bc selects are the keywords

            if(!rs.next()){
                throw new ResourcePersistanceException("User was not found in the database, please check ID entered was correct.");
            }

            BankUser bankUser = new BankUser();

            bankUser.setFname(rs.getString("fname")); // this column lable MUST MATCH THE DB
            bankUser.setLname(rs.getString("lname"));
            bankUser.setDob(rs.getString("dob"));
            bankUser.setPassword(rs.getString("password"));
            bankUser.setEmail(rs.getString("email"));

            return bankUser;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean update(BankUser updatedBankUser) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    public BankUser authenticateTrainer(String email, String password){

        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select * from trainer where email = ? and password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(!rs.next()){
                return null;
            }

            BankUser bankUser = new BankUser();

            bankUser.setFname(rs.getString("fname")); // this column lable MUST MATCH THE DB
            bankUser.setLname(rs.getString("lname"));
            bankUser.setDob(rs.getString("dob"));
            bankUser.setPassword(rs.getString("password"));
            bankUser.setEmail(rs.getString("email"));

            return bankUser;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }


    }
    public boolean checkEmail(String email) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select email from trainer where email = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if(!rs.isBeforeFirst()){
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
