package com.marcusdacoregio.devtec2023;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UserInfoController {

	@GetMapping("/userinfo")
	CustomUserDetails getUserInfo(@AuthenticationPrincipal CustomUserDetails authentication) {
		return authentication;
	}

}
