package iss.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iss.repository.ResultRepository;

@Service
public class IndexService {

	@Autowired
	private ResultRepository resultRepo;

	public void save(List<FoodSearchResult> toSave) {
		resultRepo.save(toSave);
	}

	public Optional<FoodSearchResult> get(String articleId) {
		return resultRepo.get(articleId);
	}

}
