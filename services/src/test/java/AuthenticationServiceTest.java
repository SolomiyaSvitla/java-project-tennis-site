import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tennis.authentication.AuthenticationService;
import com.tennis.authentication.AuthenticationServiceImpl;
import com.tennis.domain.AuthorizationToken;
import com.tennis.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class AuthenticationServiceTest {

	@Mock
	AuthenticationService authenticationService = new AuthenticationServiceImpl();

	private static final String USER_NAME = "solomiya";
	private static final String PASSWORD = "password";
	private static final String SEX = "F";
	private static final String EMAIL = "some@ru.com";
	private static final String TEST_TOKEN = "8815f97f-5756-4719-90d3-025503c53640";

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	private User createUserDto() {
		User user = new User();
		user.setUserName(USER_NAME);
		user.setEmail(EMAIL);
		user.setSex(SEX);
		user.setPassword(PASSWORD);
		user.setUserID(1);
		return user;
	}

	@Test
	public void testAuthenticationSuccess() throws Exception {

		AuthorizationToken expected = new AuthorizationToken();
		User userDto = createUserDto();
		expected.setToken(TEST_TOKEN);
		expected.setUserID(userDto.getUserID());

		when(authenticationService.authentication(USER_NAME, PASSWORD))
				.thenReturn(expected);
		assertEquals(expected,
				authenticationService.authentication(USER_NAME, PASSWORD));

	}
}
