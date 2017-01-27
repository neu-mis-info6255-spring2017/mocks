package org.mocks.data;

import java.util.ArrayList;
import java.util.List;

import org.mocks.domain.Post;

public class Posts {
	
	List<Post> posts;
	
	public Posts() {
		posts = new ArrayList<>();
	}
	
	public List<Post> getAllPosts() {
		return posts;
	}

}
