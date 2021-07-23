package com.example.demo.controller;

import org.springframework.boot.web.server.ErrorPage;

public class ErrorHandle extends ErrorPage {

	public ErrorHandle(String path) {
		super("/error.html");
	}

}
