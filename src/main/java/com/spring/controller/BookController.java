package com.spring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.model.BookBean;
import com.spring.model.CartBean;
import com.spring.model.UserBean;

import Repository.BookRepository;
import Repository.CartRepository;
import Repository.UserRepository;

@Controller
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	  private CartRepository cartRepo;
	
	@GetMapping(value="/showBook")
	public String showAllbooks(BookBean book , Model model,HttpSession session) {
		
		List<BookBean> list = bookRepo.showAllBook();
		if(list.size() > 0) {
			model.addAttribute("list" , list);
			UserBean obj = (UserBean) session.getAttribute("user");
			if(obj.getRole()!= null && obj.getRole().equals("admin")) {
				
				return "bookList";
				
			}
			else {
				return "userBookList";	
			}
		}
		else {
			
			model.addAttribute("error" , "No book avaliable now.");
			return "bookList";
			
		}
	
	}
	@GetMapping("/insertBook")
	public ModelAndView insertBook(BookBean bean , Model model) {
		
		
		model.addAttribute("bean" , bean);
		return new ModelAndView("addBook","bean",new BookBean());
		
	}
	
	@PostMapping(value="/addBook")
	public String addBook(@ModelAttribute("bean")BookBean book , Model model) {
		
		int i = bookRepo.insertBook(book);
		
		return "redirect:/book/showBook";
	}
	
	
	@GetMapping(value="/editBook/{id}")
	public String selectBookbyId( @PathVariable("id")int id , Model model) {
		
		BookBean obj = bookRepo.showBookbyId(id);
		model.addAttribute("obj" , obj);
		return "editBook";
		
	}
	
	@PostMapping(value="/editBook")
	public String editBook(@ModelAttribute("obj") @Validated BookBean bean , Model model) {
		
		int i = bookRepo.updateBook(bean); 

		if(i>0) {
			return "redirect:/book/showBook";
		}
		else {
			model.addAttribute("error", "Book edit failed!!");
			return "editBook";
		}

	}
	@GetMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable int id , Model model) {
		
		BookBean book = new BookBean();
		book.setId(id);
		
		int i = bookRepo.deleteBook(id);
		
		if(i == 0) {
			model.addAttribute("error" , "Failed to delete!!");
		}
		return "redirect:/book/showBook";
	}
	
	@GetMapping(value="/showDeletedBooks")
	public String showDeletedBooks(BookBean book , Model model) {
		
		List<BookBean> list = bookRepo.showDeletedBooks();
		if(list.size() > 0) {
			model.addAttribute("list" , list);
			
			return "deleteHistory";
		}
		else {
			
			model.addAttribute("error" , "No book avaliable now.");
			return "deleteHistory";
			
		}
	
	}
	
	
	@GetMapping(value="/restoreBook/{id}")
	public String restoreBook(@PathVariable int id , Model model) {
		
		int i = bookRepo.restoreBook(id);
		
		return "redirect:/book/showBook";
	}
	
	@GetMapping("/viewbooks")
	  public String viewBooks(Model m) {
	    List<CartBean> booklist = cartRepo.showCart();
	    m.addAttribute("bookList", booklist);
	    return "showmycart";
	    
	  }
	
	
	@GetMapping("/addingCart")
	public ModelAndView addToCart(@RequestParam("id") int bookId, HttpSession session) {
	    ModelAndView mv = new ModelAndView("redirect:/viewbooks");

	    // Get user ID from session
	    UserBean user = (UserBean) session.getAttribute("user"); // Change this to match how you're storing the user

	    if (user != null) {
	    	 System.out.println("User ID: " + user.getId()); 
	        // Fetch user details from the repository using the user ID
	    	

	        // Fetch book details using showBookbyId method
	        BookBean book = bookRepo.showBookbyId(bookId);

	        if (book != null && book.getQuantity() > 0) {  // Check if the book is available
	            // Create a new CartBean entry
	            CartBean cartBean = new CartBean();
	            cartBean.setBookid(book);
	            cartBean.setUserid(user); // Now the full UserBean is used
	            cartBean.setQuantity(1);  // Default quantity to 1
	            cartBean.setStatus(1);    // Active cart status

	            // Add to cart repository
	            cartRepo.insertToCart(cartBean);
	            mv.addObject("message", "Book added to cart successfully!");
	        } else {
	            mv.addObject("error", "Book is unavailable or out of stock.");
	        }
	    } else {
	        mv.addObject("error", "You must be logged in to add items to the cart.");
	    }

	    return mv;
	}
	@GetMapping("/watchmycart")
	public String showCart(Model model) {
	    List<CartBean> cartList = cartRepo.showCart();
	    model.addAttribute("cartList", cartList);
	    return "showmycart"; // Ensure 'cart.jsp' is the correct JSP page to render
	}

	
	@PostMapping("/order")
	  public String order(Model model) {
	    cartRepo.order();
	    return "ordersuccess";
	  }
	  
	  @PostMapping("/deleteCart")
	  public String deleteFromCart(@RequestParam("id")int id) {
	    int i=cartRepo.deleteFromCart(id);
	    if(i>0) {
	      return "redirect:watchmycart";
	    }else {
	      System.out.println("Deleting from cart error ");
	      return "";
	    }
	    
	  }
	
	
	
}
