package mk.ukim.finki.ezdravstvo.web.resources;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mk.ukim.finki.ezdravstvo.model.User;
import mk.ukim.finki.ezdravstvo.security.TokenTransfer;
import mk.ukim.finki.ezdravstvo.security.TokenUtils;
import mk.ukim.finki.ezdravstvo.security.UserTransfer;
import mk.ukim.finki.ezdravstvo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/user")
public class UserResource {

	 private static final int TOKEN_DURATION = 30 * 24 * 60 * 60; // 30 days
	 
	 @Autowired
	 private UserDetailsService userDetailsService;
	 
	 @Autowired
	 @Qualifier("authenticationManager")
	 private AuthenticationManager authManager;
	 
	 @Autowired
	 private UserService userService;
	 
	 @RequestMapping(method = RequestMethod.GET, produces = "application/json")
	 @ResponseBody
	  public UserTransfer getUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    Authentication authentication = SecurityContextHolder.getContext()
	      .getAuthentication();
	    Object principal = authentication.getPrincipal();
	    if (principal instanceof String
	      && ((String) principal).equals("anonymousUser")) {
	      response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	    }
	    if (principal instanceof UserDetails) {

	      UserDetails userDetails = (UserDetails) principal;
	      User user = userService.findByUsername(userDetails.getUsername());
	      return new UserTransfer(user.getUsername(), user.getRole().toString());
	    }
	    return null;
	  }
	 
	 @RequestMapping(value = "/authenticate", method = RequestMethod.POST, produces = "application/json")
	  @ResponseBody
	  public TokenTransfer authenticate(
	    @RequestParam("username") String username,
	    @RequestParam("password") String password,
	    @RequestParam("rememberMe") boolean rememberMe,
	    HttpServletRequest request, HttpServletResponse response) {

	    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
	      username, password);
	    Authentication authentication = this.authManager
	      .authenticate(authenticationToken);
	    SecurityContextHolder.getContext().setAuthentication(authentication);

			/*
	     * Reload user as password of authentication principal will be null
			 * after authorization and password is needed for token generation
			 */
	    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
	    Cookie cookie = new Cookie("token", TokenUtils.createToken(userDetails));
	    if (rememberMe) {
	      cookie.setMaxAge(TOKEN_DURATION);
	    }
	    cookie.setPath("https://ezdravstvoweb.herokuapp.com/");
	    response.addCookie(cookie);
	    return new TokenTransfer(TokenUtils.createToken(userDetails));
	  }
}
