/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Context;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author DELL
 */
public class UserDBContext extends DBContext{
    
    public User get(String username, String password) {
        String sql = "SELECT username, displayname FROM Users \n"
                + "WHERE username = ? AND [password] = ?";
        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if(rs.next())
            {
                User user = new User();
                user.setUsername(username);
                user.setDisplayname(rs.getNString("displayname"));
                return user;
            }
        }catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            if(connection!=null)
                try {
                    connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
