package com.api.request.model;

public record LoginApiRequest(
		String username,
		String password) {
}
