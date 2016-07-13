package test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextHierarchy({
    @ContextConfiguration(name = "parent", locations = "classpath:application-context.xml"),
    @ContextConfiguration(name = "child",  locations = "classpath:web-context.xml")
})
public class DemoControllerTest {
    @Autowired
    private WebApplicationContext wac;
    
    private MockMvc mockMvc;
	
    @Before
    public void setUp() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).addFilter(filter).build();
    }
	
    @Test
    public void getShopShareList() throws Exception {
    	
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                        .get("/shopList") //get请求 ＋ url路径 + parth变量和对应参数值
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }
    @Test
	public void param1() throws Exception {
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/param1").param("id", "2"));

		resultActions.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print()).andReturn();
	}
    
    @Test
    public void param2() throws Exception {
    	ResultActions resultActions = mockMvc.perform(
    			MockMvcRequestBuilders
    			.post("/param2").param("id", "3")
    			.contentType(MediaType.APPLICATION_JSON) 
//    			.content(JSON.toJSONString(attrBO).getBytes())
    			);
    	
    	resultActions.andExpect(MockMvcResultMatchers.status().isOk())
    		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON+";charset=UTF-8"))
//    		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
    		.andDo(MockMvcResultHandlers.print())
    		.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
    		.andReturn();
    }
}
