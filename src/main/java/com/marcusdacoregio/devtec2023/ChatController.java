package com.marcusdacoregio.devtec2023;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ChatController {

	private final List<Message> messages = new ArrayList<>(List.of(
			new Message("Opa, tudo certo?", "marcus", "john"),
			new Message("Vc ta onde?", "marcus", "john"),
			new Message("Já chegou no DevTEC?", "marcus", "john"),
			new Message("Opa, tudo beleza", "john", "marcus"),
			new Message("Já cheguei sim", "john", "marcus"),
			new Message("To aqui no meio da galera", "john", "marcus")
	));

	@GetMapping("/chat/{username}/messages")
	@PreAuthorize("#username == authentication.name")
	List<Message> getMessages(@PathVariable String username, Authentication authentication) {
		return this.messages.stream().filter(message -> message.to().equals(username))
				.toList();
	}

	@PostMapping("/chat/{username}/messages")
	@PreAuthorize("#to != authentication.name")
	void sendMessage(@PathVariable("username") String to, @RequestBody SendMessageRequest request, Authentication authentication) {
		this.messages.add(new Message(request.message(), to, authentication.getName()));
	}

	record SendMessageRequest(String message) {}

	record Message(String message, String to, String from) {}

}
