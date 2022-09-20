package iss.services;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import iss.models.FoodSearchResult;
import iss.models.Query;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

@Service
public class FoodSearchService {

    private static String URL = "https://tih-api.stb.gov.sg/content/v1/food-beverages/search";

    @Value("${API_KEY}")
	private String apiKey;

    @PostConstruct
	public void init() {
		if (Objects.isNull(apiKey))
			System.err.println("API_KEY is not set");
	}

	public List<FoodSearchResult> getResults(Query q) {
		String url = UriComponentsBuilder
				.fromHttpUrl(URL)
				.queryParam("keyword", q.getKeyword())
				.toUriString();

		RequestEntity<Void> req = RequestEntity
				.get(url)
				.header("Authorization", "Apikey %s".formatted(apiKey))
				.build();

		RestTemplate invoke = new RestTemplate();
		
		ResponseEntity<String> resp = invoke.exchange(req, String.class);

		final List<FoodSearchResult> results = new LinkedList<>();

		try (StringReader strReader = new StringReader(resp.getBody())) {
			JsonReader r = Json.createReader(strReader);
			JsonObject j = r.readObject();
			for (JsonValue v: j.getJsonArray("Data"))
                results.add(FoodSearchResult.create((JsonObject)v));
		}
		return results;
	}
}