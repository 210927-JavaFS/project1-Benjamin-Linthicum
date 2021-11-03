import static org.junit.jupiter.api.Assertions.assertTrue;
import com.revature.services.UserService;
import com.revature.models.UserDTO;


import org.junit.jupiter.api.Test;

public class UserServiceTests {
    
    UserService userService = new UserService();

    @Test
    public void testLogin(){
        UserDTO userDTO = new UserDTO();
        userDTO.username = "Employee";
        userDTO.username = "Employeepassword";
        assertTrue(userService.login(userDTO));
    }

}
