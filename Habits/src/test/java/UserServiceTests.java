//import org.example.service.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class UserServiceTests {
//
//    private UserService userService;
//
//    @BeforeEach
//    public void setUp() {
//        userService = new UserService();
//        userService.createAdmin();
//    }
//
//    @Test
//    public void testRegisterNewUser() {
//        String result = userService.registerNewUser("John", "john@example.com", "password123");
//        assertEquals("User successfully registered", result);
//        assertEquals(2, UserService.userModels.size());
//    }
//
//    @Test
//    public void testRegisterExistingUser() {
//        userService.registerNewUser("John", "john@example.com", "password123");
//        String result = userService.registerNewUser("John", "john@example.com", "password456");
//        assertEquals("User can't be registered, this name already exists", result);
//    }
//
//    @Test
//    public void testLoginUserSuccessful() {
//        userService.registerNewUser("John", "john@example.com", "password123");
//        boolean result = userService.loginUser("john@example.com", "password123");
//        assertTrue(result);
//    }
//
//    @Test
//    public void testLoginUserFailed() {
//        userService.registerNewUser("John", "john@example.com", "password123");
//        boolean result = userService.loginUser("john@example.com", "wrongpassword");
//        assertFalse(result);
//    }
//
//    @Test
//    public void testUserChangeName() {
//        userService.registerNewUser("John", "john@example.com", "password123");
//        userService.userChangeName("john@example.com", "Johnny");
//        assertEquals("Johnny", userService.userModels.get(1).getName());
//    }
//
//    @Test
//    public void testUserDelete() {
//        userService.registerNewUser("John", "john@example.com", "password123");
//        userService.deleteUser("john@example.com", "password123");
//        assertEquals(1, UserService.userModels.size());
//    }
//
//    @Test
//    public void testEmailVerification() {
//        assertTrue(userService.emailVerification("test@example.com"));
//        assertFalse(userService.emailVerification("invalid-email"));
//    }
//
//    @Test
//    public void testPasswordVerification() {
//        assertTrue(userService.passwordVerification("password", "password"));
//        assertFalse(userService.passwordVerification("password", "differentpassword"));
//    }
//}
