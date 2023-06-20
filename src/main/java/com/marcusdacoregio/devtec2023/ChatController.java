package com.marcusdacoregio.devtec2023;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	List<Message> getMessages(@PathVariable String username, Authentication authentication) {
		if (!username.equals(authentication.getName())) {
			throw new AccessDeniedException("Acesso negado, você não pode ver as mensagens de outro usuário");
		}
		return this.messages.stream().filter(message -> message.to().equals(username))
				.toList();
	}

	record Message(String message, String to, String from) {}

}
