package com.examples.projectorganizerapi.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.examples.projectorganizerapi.model.Client;
import com.examples.projectorganizerapi.service.ClientService;

@RunWith(SpringRunner.class)
@WebMvcTest(value=ClientController.class, secure = false)
public class ClientControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ClientService clientService;
	
	Client mockClient = new Client (1l,"Critical", "josedias","josedias@crtitical.com");
	
	String exampleClientJson = "{\"clientId\":1l,\"clientName\":\"Critical\",\"managerName\": \"josedias\", \"managerEmail\" : \"josedias@crtitical.com\" }";
	
	@Test
	public void createClient() throws Exception {
		Mockito.when(((OngoingStubbing<Object>) clientService.createClient((Client) Mockito.any())).thenReturn(mockClient));
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/clients").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println(result.getResponse());
		String expected = "{clientId:1,clientName:Critical,managerName:josedias,managerEmail:josedias@critical.com}";
		
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}
	
}
