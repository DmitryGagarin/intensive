
import org.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class UserServiceTests {

    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserService();
        userService.createAdmin(); // Create initial admin user
    }

    @Test
    public void testRegisterNewUser_success() {
        String result = userService.registerNewUser("John", "john@example.com", "password123");
        assertThat(result).isEqualTo("User successfully registered");
        assertThat(userService.userModels).hasSize(2); // Admin + new user
    }

    @Test
    public void testRegisterNewUser_success_twoUser() {
        String result1 = userService.registerNewUser("John", "john@example.com", "password123");
        String result2 = userService.registerNewUser("John1", "john1@example.com", "password123");
        assertThat(result2).isEqualTo("User successfully registered");
        assertThat(userService.userModels).hasSize(3); // Admin + new user
    }

    @Test
    public void testRegisterNewUser_failureName() {
        String result1 = userService.registerNewUser("John", "john@example.com", "password123");
        String result2 = userService.registerNewUser("John", "john1@example.com", "password123");
        assertThat(result2).isEqualTo("User can't be registered, this name already exists");
        assertThat(userService.userModels).hasSize(2); // Admin + new user
    }

    @Test
    public void testRegisterNewUser_failureEmail() {
        String result1 = userService.registerNewUser("John", "john@example.com", "password123");
        String result2 = userService.registerNewUser("John1", "john@example.com", "password123");
        assertThat(result2).isEqualTo("User can't be registered, this email already exists");
        assertThat(userService.userModels).hasSize(2); // Admin + new user
    }

    @Test
    public void testRegisterNewUser_failure_duplicateName() {
        userService.registerNewUser("John", "john@example.com", "password123");
        String result = userService.registerNewUser("John", "john.doe@example.com", "password456");
        assertThat(result).isEqualTo("User can't be registered, this name already exists");
    }

    @Test
    public void testLoginUserSuccessful() {
        userService.registerNewUser("John", "john@example.com", "password123");
        boolean result = userService.loginUser("john@example.com", "password123");
        assertThat(result).isTrue();
    }

    @Test
    public void testLoginUserFailed_incorrectEmail() {
        userService.registerNewUser("John", "john@example.com", "password123");
        boolean result = userService.loginUser("john.doe@example.com", "password123");
        assertThat(result).isFalse();
    }

    @Test
    public void testUserChangeName() {
        userService.registerNewUser("John", "john@example.com", "password123");
        userService.userChangeName("john@example.com", "Johnny");
        assertThat(userService.userModels.get(1).getName()).isEqualTo("Johnny");
    }

    @Test
    public void testUserChangeEmail() {
        userService.registerNewUser("John", "john@example.com", "password123");
        userService.userChangeEmail("john@example.com", "Johnny@example.com");
        assertThat(userService.userModels.get(1).getEmail()).isEqualTo("Johnny@example.com");
    }

    @Test
    public void testUserChangePassword() {
        userService.registerNewUser("John", "john@example.com", "password123");
        userService.userChangePassword("john@example.com", "password123", "password1234");
        assertThat(userService.userModels.get(1).getPassword()).isEqualTo("password1234");
    }

    @Test
    public void testDeleteUser() {
        userService.registerNewUser("John", "john@example.com", "password123");
        userService.deleteUser("john@example.com", "password123");
        assertThat(userService.userModels).hasSize(1); // Only admin should remain
    }

    @Test
    public void testEmailVerification_valid() {
        boolean isValid = userService.emailVerification("test@example.com");
        assertThat(isValid).isTrue();
    }

    @Test
    public void testEmailVerification_invalid() {
        boolean isValid = userService.emailVerification("invalid-email");
        assertThat(isValid).isFalse();
    }

    @Test
    public void testForgetPassword() {
        userService.registerNewUser("John", "john@example.com", "password123");
        String password = userService.sendPasswordToEmail("john@example.com");
        assertThat(password).isEqualTo("password123");
    }

    @Test
    public void testAdminDeleteUser() {
        userService.registerNewUser("John", "john@example.com", "password123");
        userService.adminDeleteUser("john@example.com");
        assertThat(userService.userModels).hasSize(1); // Only admin should remain
    }

    @Test
    public void testAdminGetCertainUser() {
        userService.registerNewUser("John", "john@example.com", "password123");
        String userInfo = userService.adminGetCertainUser("john@example.com");
        assertThat(userInfo).contains("John", "john@example.com");
    }
}
