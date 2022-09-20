package iss.models;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Query {

    private String keyword;
    
    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public static Query create(JsonObject json) {
		final Query q = new Query();
		q.setKeyword(json.getString("keyword"));
	
		return q;
	}
	public static Query create(String json) {
		try (StringReader strReader = new StringReader(json)) {
			JsonReader j = Json.createReader(strReader);
			return create(j.readObject());
		}
	}
	public JsonObject toJson() {
		return Json.createObjectBuilder()
			.add("keyword", this.keyword)
			.build();
	}
    
}
