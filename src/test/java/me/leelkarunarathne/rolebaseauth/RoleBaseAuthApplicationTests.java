package me.leelkarunarathne.rolebaseauth;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
class RoleBaseAuthApplicationTests {

	@Autowired
	private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "ROLE_TWO",username = "leel", password = "1234", authorities = "ROLE_TWO", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void checkEndPointTwo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/test2")
		.accept(MediaType.ALL))
				.andExpect(status().isOk());
    }


}
