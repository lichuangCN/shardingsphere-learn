package site.muzhi.learn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@WebAppConfiguration
class ShardingDbApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * 保存
     *
     * @throws Exception
     */
    @Test
    void save() throws Exception {
        ResultActions userRA = mockMvc.perform(MockMvcRequestBuilders.post("/user/save")
                .accept(MediaType.APPLICATION_JSON));
        userRA.andReturn().getResponse().setCharacterEncoding("UTF-8");
        userRA.andExpect(MockMvcResultMatchers.status().isOk())
                //输出整个响应结果信息
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        ResultActions cityRA = mockMvc.perform(MockMvcRequestBuilders.post("/city/save")
                .accept(MediaType.APPLICATION_JSON));
        cityRA.andReturn().getResponse().setCharacterEncoding("UTF-8");
        cityRA.andExpect(MockMvcResultMatchers.status().isOk())
                //输出整个响应结果信息
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void list() throws Exception {
        ResultActions userRA = mockMvc.perform(MockMvcRequestBuilders.get("/user/list")
                .accept(MediaType.APPLICATION_JSON));
        userRA.andReturn().getResponse().setCharacterEncoding("UTF-8");
        userRA.andExpect(MockMvcResultMatchers.status().isOk())
                //输出整个响应结果信息
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        ResultActions cityRA = mockMvc.perform(MockMvcRequestBuilders.get("/city/list")
                .accept(MediaType.APPLICATION_JSON));
        cityRA.andReturn().getResponse().setCharacterEncoding("UTF-8");
        cityRA.andExpect(MockMvcResultMatchers.status().isOk())
                //输出整个响应结果信息
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
