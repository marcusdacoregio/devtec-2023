package com.marcusdacoregio.devtec2023;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UserInfoController {

	@GetMapping("/userinfo")
	Authentication getUserInfo(Authentication authentication) {
		return authentication;
	}

}
