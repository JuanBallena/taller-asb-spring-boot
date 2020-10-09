package com.taller.asb.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
//import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.springframework.stereotype.Component;
import com.taller.asb.controller.UserController;

@Component
public class LinkCreator {

	public Object createLinkToUser(Long idUser) {
		return linkTo(methodOn(UserController.class).getUser(idUser)).withRel("user");
	}
}
