package com.abdullah.keycloak.controller;

import com.abdullah.keycloak.repository.BookRepository;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LibraryController {

	private final HttpServletRequest request;
	private final BookRepository bookRepository;

	public LibraryController(HttpServletRequest request, BookRepository bookRepository) {
		this.request = request;
		this.bookRepository = bookRepository;
	}

	@GetMapping(value = "/")
	public String getHome() {
		return "index";
	}

	@GetMapping(value = "/books")
	public String getBooks(Model model) {
		configCommonAttributes(model);
		model.addAttribute("books", bookRepository.readAll());
		return "books";
	}

	@GetMapping(value = "/books/{id}")
	public String getBooksById(Model model, @PathVariable("id") String id) {
		model.addAttribute("book", bookRepository.read(id));
		return "edit-book";
	}

	@GetMapping(value = "/manager")
	public String getManager(Model model) {
		configCommonAttributes(model);
		model.addAttribute("books", bookRepository.readAll());
		return "manager";
	}

	@GetMapping(value = "/logout")
	public String logout() throws ServletException {
		request.logout();
		return "redirect:/";
	}

	private void configCommonAttributes(Model model) {
		model.addAttribute("name", getKeycloakSecurityContext().getIdToken().getPreferredUsername());
	}

	private KeycloakSecurityContext getKeycloakSecurityContext() {
		return (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
	}
}
