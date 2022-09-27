package iss.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import iss.models.Reviews;
import iss.services.FoodSearchService;

@Controller
@RequestMapping(path = "/reviews")
public class SearchController {

	@Autowired
	private FoodSearchService foodSvc;

    @GetMapping
	public String getResults(Model model, @RequestParam(required = true) String keyword) {
		
		List<Reviews> reviews = foodSvc.getResults(keyword);
            model.addAttribute("keyword", keyword);
			model.addAttribute("reviews", reviews);
            
			return "reviews";
        
		}

}

			

	/* @PostMapping
	public String postResults(@RequestBody MultiValueMap<String, String> form,
			Model model, HttpSession sess) {

		List<FoodSearchResult> articles = (List<NewsArticle>)sess.getAttribute("articles");

		List<String> saveIds = form.get("resId");
		List<NewsArticle> toSave = articles.stream()
			.filter(art -> {
				for (String i: saveIds)
					if (i.equals(art.getId()))
						return true;
				return false;
			})
			.toList();

		if (toSave.size() > 0)
			newsSvc.save(toSave);

		model.addAttribute("articles", articles);
		return "news";

	}
    /* */
