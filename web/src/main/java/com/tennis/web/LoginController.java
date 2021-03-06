package com.tennis.web;

import static com.tennis.authentication.BaseAuthentication.AUTH_TOKEN_HEADER_NAME;

import com.tennis.authentication.AuthenticationService;
import com.tennis.configuration.Config;
import com.tennis.domain.AuthorizationToken;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@ContextConfiguration(classes = Config.class)
public class LoginController {

	private static final Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	private AuthenticationService authenticationService;

	@ResponseBody
	@RequestMapping(value = "/authentication", method = RequestMethod.POST)
	public ResponseEntity<AuthorizationToken> authentication(
			@RequestParam("password") String password,
			@RequestParam("login") String login) {
		AuthorizationToken token = null;
		HttpStatus code;
		HttpHeaders headers = new HttpHeaders();
		logger.info("Calling logger controller");
		try {
			token = new AuthorizationToken();//authenticationService.authentication(login, password);
			token.setToken("123456");
			logger.info("Log in");
			code = HttpStatus.CREATED;
			headers.add(AUTH_TOKEN_HEADER_NAME, token.getToken());
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Authentication failed");
			code = HttpStatus.NETWORK_AUTHENTICATION_REQUIRED;
		}
		return new ResponseEntity<AuthorizationToken>(token, headers, code);
	}

	@ResponseBody
	@RequestMapping(value = "/fake", method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> fakeEndpoint(@RequestParam String login,
			@RequestParam("password") String password) {
		// @RequestParam String password) {
		HttpStatus code = HttpStatus.OK;
		System.out.println("In endpoint " + code);
		System.out.println(login);
		return new ResponseEntity<HttpStatus>(code);
	}
}
