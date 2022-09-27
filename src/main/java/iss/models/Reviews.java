package iss.models;

import java.io.StringReader;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Reviews {
    private String body;
    private String companyDisplayName;
    private String reviews;
    private String text;
    private String authorName;
    private String authorURL;
    private String profilePhoto;
    private String rating;
    private String time;
    private String uuid;

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getCompanyDisplayName() {
        return companyDisplayName;
    }
    public void setCompanyDisplayName(String companyDisplayName) {
        this.companyDisplayName = companyDisplayName;
    }
    public String getReviews() {
        return reviews;
    }
    public void setReviews(String reviews) {
        this.reviews = reviews;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    public String getAuthorURL() {
        return authorURL;
    }
    public void setAuthorURL(String authorURL) {
        this.authorURL = authorURL;
    }
    public String getProfilePhoto() {
        return profilePhoto;
    }
    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
    public String getRating() {
        return rating;
    }
    public void setRating(String rating) {
        this.rating = rating;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    public static Reviews create(JsonObject json) {
		Reviews search = new Reviews();
		search.setCompanyDisplayName(json.getString("companyDisplayName"));
        search.setBody(json.getString("body"));
        search.setReviews(json.getString("reviews"));
		search.setText(json.getString("text"));
		search.setAuthorName(json.getString("authorname"));
        search.setAuthorURL(json.getString("authorurl"));
		search.setProfilePhoto(json.getString("profilephoto"));
		search.setRating(json.getString("rating"));
		search.setTime(json.getString("time"));
        search.setUuid(json.getString("uuid"));
		return search;
	}

	public static Reviews create(String json) {
		try (StringReader strReader = new StringReader(json)) {
			JsonReader j = Json.createReader(strReader);
			return create(j.readObject());
		}
	}

	public JsonObject toJson() {
		return Json.createObjectBuilder()
            .add("companydisplayname", this.companyDisplayName)
			.add("body", this.body)
            .add("reviews", this.reviews)
			.add("text", this.text)
			.add("authorname", this.authorName)
            .add("authorurl", this.authorURL)
			.add("profilephoto", this.profilePhoto)
			.add("rating", this.rating)
			.add("time", this.time)
			.add("uuid", this.uuid)
			.build();
	}
	@Override
    public String toString() {
        return "FoodSearchResult [authorName=" + authorName + ", authorURL=" + authorURL + ", body=" + body
                + ", companyDisplayName=" + companyDisplayName + ", profilePhoto=" + profilePhoto
                + ", rating=" + rating + ", reviews=" + reviews + ", text=" + text + ", time=" + time + ", uuid=" + uuid
                + "]";
    }

    
    
}
