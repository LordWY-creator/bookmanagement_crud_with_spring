package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.spring.model.LoginBean;
import com.spring.model.UserBean;

public class UserRepository {
	
	public int insertUser(UserBean bean) {
		int i = 0;
		
		Connection con = UserConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("insert into user (name,email,password) values(?,?,?)");
			ps.setString(1, bean.getName());
			ps.setString(2, bean.getEmail());
			ps.setString(3, bean.getPassword());
			
			i=ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("User Insert error : " + e.getMessage());
		}
		return i;
	}
	
	public boolean checkEmail(String email) {
		boolean check = false;
		
		Connection con = UserConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from user where email = ?");
			ps.setString(1, email);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				check = true;
			}
			
			
		} catch (SQLException e) {
			System.out.println("check email : " + e.getMessage());
		}
		
		return check;
	}
	
	public UserBean loginUser(LoginBean bean) {
		
		UserBean user = null;
		Connection con = UserConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from user where email = ? and password=?");
			ps.setString(1, bean.getEmail());
			ps.setString(2, bean.getPassword());
			
			
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				user = new UserBean();
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
			}
			
			
		} catch (SQLException e) {
			System.out.println("login user : " + e.getMessage());
		}
		
		return user;
		
	}
	public UserBean getUserById(int userId) {
        UserBean user = null;
        Connection con = UserConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE id=?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new UserBean();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving user: " + e.getMessage());
        }
        return user;
    }
}
