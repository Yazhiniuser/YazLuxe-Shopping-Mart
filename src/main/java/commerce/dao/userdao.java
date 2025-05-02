package commerce.dao;
import java.sql.*;
import commerce.model.*;
public class userdao {
	private Connection con;

	private String query;
    private PreparedStatement pst;
    private ResultSet rs;
	

	public userdao(Connection connection) {
		this.con = connection;
	}

	public user userLogin(String email, String password) {
		user user = null;
        try {
            query = "select * from users where email=? and password=?";
            pst = this.con.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, password);
            rs = pst.executeQuery();
            if(rs.next()){
            	user = new user();
            	user.setId(rs.getInt("id"));
            	user.setName(rs.getString("name"));
            	user.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return user;
    }
}
 