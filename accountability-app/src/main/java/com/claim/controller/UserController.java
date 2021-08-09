package com.claim.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.claim.entity.Favorites;
//import com.claim.entity.Favorites;
import com.claim.entity.User;
import com.claim.repository.UserRepository;
import com.claim.repository.FavoritesRepository;

//@CrossOrigin
//@RestController
@Controller
//@RequestMapping(path="/")
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	FavoritesRepository favoritesRepository;

	@Autowired
	private HttpServletRequest request;

	@GetMapping("/index")
	public String welcome(Model model) {
		return "index";
	}

	@GetMapping({ "/", "/home" })
	public String home(Model model) {
		return "home";
	}

//	@PostMapping(path="/save")
//	public @ResponseBody void submitUserDetails(@RequestBody User user) {
//		userRepository.save(user);
//	}

	@GetMapping("/login")
	public ModelAndView login(Model model) {
		return new ModelAndView("login", "loginUser", new User());
	}

	@PostMapping("login")
	public String handleLogin(Model model, @ModelAttribute("loginUser") User user) {
//	public String handleLogin(@RequestParam String email, @RequestParam String password, Model model) {
		User loggedInUser = userRepository.loginUser(user.getEmail(), user.getPassword());
//		User loggedInUser = userRepository.loginUser(email, password);
		if (loggedInUser == null) {
			model.addAttribute("loginError", "Invalid login. Please check your email and password");
			return "login";
		}
		System.out.println("Logged in User is: " + loggedInUser.getEmail().toString());
		model.addAttribute("userAccount", loggedInUser);
		request.getSession().setAttribute("email", loggedInUser.getEmail().toString());
		return "index";
	}

	@GetMapping("logout")
//	String logout(@ModelAttribute("loggedInUser") User user, Model model, HttpSession session) {
	String logout(Model model) {
		request.getSession().removeAttribute("email");
		model.addAttribute("msg", "You have been logged out");
		return "home";
	}

	@GetMapping("/register")
	public ModelAndView register(Model model) {
		return new ModelAndView("register", "user", new User());
	}

	@PostMapping("/register")
	public String handleRegistration(Model model, @ModelAttribute("user") User user) {
		userRepository.save(user);
		model.addAttribute("savedUser", user);
//		session.setAttribute("savedUser", user);
//		System.out.println("Size of User List: " + userService.getUserArray().size());
		model.addAttribute("msg", "Thank you for registering!");
		return "thankyou";
	}

	@GetMapping("/partner-search")
	public String partnerSearch(Model model) {
		List<User> userList = (List<User>) userRepository.findAll();
		model.addAttribute("userList", userList);
		return "partner-search";
	}

	@GetMapping("/viewDetails")
	public ModelAndView handlePartnerPageDetailsButton(Model model, HttpServletRequest request) {
		String selectedUserProfilesEmail = request.getParameter("id");
		System.out.println("Selected User's email is: " + selectedUserProfilesEmail);
		User selectedUsersData = userRepository.findUserByEmail(selectedUserProfilesEmail);
		model.addAttribute("user", selectedUsersData);
		return new ModelAndView("details", "selectedUser", new User());
	}

//	@PostMapping("/favorites")
	@RequestMapping(value = "/favorites", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView handleFavorites(@RequestParam String id, Model model, HttpServletRequest request) {
//		System.out.println("I'm here in favorites");
//		String selectedEmailString = request.getParameter("id");
//		System.out.println("The users Email to Add to Favorites is: " + selectedEmailString);
		String currentlyLoggedInUser = (String) request.getSession().getAttribute("email");
//		System.out.println("currentUserEmail is: " + currentlyLoggedInUser);
		
//		User currentUser = userRepository.findUserByEmail(currentlyLoggedInUser); // Probably don't need
		User currentUser = (User) userRepository.findUserByEmail(currentlyLoggedInUser);
//		String currentUserString = 
//		currentUser.get().setFavorites(userEmailToAddToFavorite); // Need to send to database, not this
//		userRepository.addUserFavorite(userEmailToAddToFavorite, currentlyLoggedInUser); // Going to try to pass in an object, instead of a String
//		User tempUser = new User();
//		List<Favorites> tempFavoriteList = new ArrayList<Favorites>();
		// ----------- Suppressing errors for now until I figure out what I need to do ------------------ //
//		tempFavorite.setId(0);
//		System.out.println("Passed #1");
//		Favorites favoriteToAdd = new Favorites(selectedEmailString, currentUser);
//		System.out.println("Passed #2");
//		Favorites tempFavorite = new Favorites();
//		tempFavorite.setEmail(userEmailToAddToFavorite);
//		tempFavorite.setUser(tempUser);
//		tempFavoriteList.add(tempFavorite);
//		currentUser.addUserFavorite(tempFavoriteList, currentlyLoggedInUser);
		Favorites favorites = new Favorites();
		favorites.setTitle(id);
		favorites.setUser(currentUser);
		favoritesRepository.save(favorites);
//		currentUser.get().getFavorites().add(favoriteToAdd);
//		currentUser.getFavorites().add(favoriteToAdd);
//		System.out.println("Passed #3");
//		userRepository.addUserFavorite(id, currentlyLoggedInUser);
//		System.out.println("Passed #4");
//		System.out.println("This user was added to your favorites: " + currentUser.get().getFavorites());
		String selectedUserProfilesEmail = request.getParameter("id");
		User selectedUsersData = userRepository.findUserByEmail(selectedUserProfilesEmail);
		model.addAttribute("user", selectedUsersData);
		model.addAttribute("favoriteAddedMessage", "User added to your Favorites");
		return new ModelAndView("details", "selectedUser", new User());
	}

//	@RequestMapping(
//	value="/save",  
//	consumes=MediaType.APPLICATION_JSON_VALUE, 
//	produces=MediaType.APPLICATION_JSON_VALUE,
//	method= RequestMethod.POST)
//private void submitUserDetails(@RequestBody User user) {
//userRepository.save(user);
//}
//
//@RequestMapping(
//	value="/findUserById",  
//	produces=MediaType.APPLICATION_JSON_VALUE,
//	method= RequestMethod.GET)
//private Optional<User> findUserById(String email) {
//Optional<User> userFound = userRepository.findById(email);
//return userFound;
//}
//
//@RequestMapping(
//	value="/loginUser",
//	consumes=MediaType.APPLICATION_JSON_VALUE, 
//	produces=MediaType.APPLICATION_JSON_VALUE,
//	method= RequestMethod.POST)
//private ResponseEntity<User> loginUser(@RequestBody User user) {
//User loggedInUser = userRepository.loginUser(user.getEmail(),user.getPassword());
//if (loggedInUser == null) {
//	return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
//}
//return new ResponseEntity<User>(loggedInUser, HttpStatus.OK);
//}
//
//	@RequestMapping(
//			value="/getAllUsers",
//			produces=MediaType.APPLICATION_JSON_VALUE,
//			method= RequestMethod.GET)
//	private ResponseEntity<List<User>> getAllUsers(){
//		List<User> userList = (List<User>) userRepository.findAll();
//		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
//	}
}
