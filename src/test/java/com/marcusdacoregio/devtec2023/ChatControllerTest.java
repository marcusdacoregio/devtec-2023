package com.marcusdacoregio.devtec2023;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ChatController.class)
class ChatControllerTest {

	@Autowired
	MockMvc mvc;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	@WithMockUser("marcus")
	void sendMessageWhenNoCsrfTokenThenForbidden() throws Exception {
		ChatController.SendMessageRequest message = new ChatController.SendMessageRequest("Hello");
		this.mvc.perform(
				post("/chat/{username}/messages", "john")
						.content(this.objectMapper.writeValueAsBytes(message))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpectAll(
						status().isForbidden()
				);
	}

	@Test
	@WithMockUser("marcus")
	void sendMessageWhenProvidedCsrfTokenThenSuccess() throws Exception {
		ChatController.SendMessageRequest message = new ChatController.SendMessageRequest("Hello");
		this.mvc.perform(
						post("/chat/{username}/messages", "john")
								.content(this.objectMapper.writeValueAsBytes(message))
								.contentType(MediaType.APPLICATION_JSON)
								.with(csrf()))
				.andExpectAll(
						status().isOk()
				);
	}

}
