package iss.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ProfileController {

    @PostMapping
	public String postCart(@RequestBody MultiValueMap<String, String> form
				, Model model) {

		String name = form.getFirst("name");
		if ((null == name) || (name.trim().length() <= 0))
			name = "anonymous";

		model.addAttribute("name", name.toUpperCase());

		return "profile";
	}
    
}
