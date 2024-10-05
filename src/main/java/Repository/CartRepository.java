package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.spring.model.BookBean;
import com.spring.model.CartBean;
import com.spring.model.UserBean;

public class CartRepository {
	
	public int insertToCart(CartBean cart) {
        int i = 0;
        Connection con = UserConnection.getConnection();
        String query = "INSERT INTO library.cart (books_id, user_id, quantity, cart_status) VALUES (?, ?, ?, ?);";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, cart.getBookid().getId());  // book_id
            ps.setInt(2, cart.getUserid().getId());  // user_id
            ps.setInt(3, cart.getQuantity());  // quantity
            ps.setInt(4, cart.getStatus());  // cart_status (1 means active)
            
            i = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Insert Into Cart Error: " + e.getMessage());
        }
        return i;
    }

	public List<CartBean> showCart() {
        List<CartBean> cartList = new ArrayList<>();
        Connection con = UserConnection.getConnection();
        String query = "SELECT c.id, c.quantity, b.id, b.title, b.author, b.price, u.id, u.name FROM library.cart c "
                + "INNER JOIN books b ON c.book_id = b.id "
                + "INNER JOIN users u ON c.user_id = u.id WHERE c.cart_status = 1;";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CartBean cart = new CartBean();
                
                // Set cart details
                cart.setId(rs.getInt(1));
                cart.setQuantity(rs.getInt(2));
                cart.setStatus(1);  // Assuming 1 means active cart
                
                // Set Book details
                BookBean book = new BookBean();
                book.setId(rs.getInt(3));
                book.setTitle(rs.getString(4));
                book.setAuthor(rs.getString(5));
                book.setPrice(rs.getDouble(6));
                cart.setBookid(book);  // Set book in cart
                
                // Set User details
                UserBean user = new UserBean();
                user.setId(rs.getInt(7));
                user.setName(rs.getString(8));  // Assuming you have name field in UserBean
                cart.setUserid(user);
                
                cartList.add(cart);
            }
        } catch (SQLException e) {
            System.out.println("Showing Cart Error: " + e.getMessage());
        }
        return cartList;
    }
	
	
	public int order() {
        int i = 0;
        Connection con = UserConnection.getConnection();
        String query = "UPDATE library.cart SET cart_status = 0;";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            i = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Order Error: " + e.getMessage());
        }
        return i;
    }

    public int deleteFromCart(int id) {
        int i = 0;
        Connection con = UserConnection.getConnection();
        String query = "DELETE FROM library.cart WHERE id = ? AND cart_status = 1;";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            i = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete from Cart Error: " + e.getMessage());
        }
        return i;
    }
}

