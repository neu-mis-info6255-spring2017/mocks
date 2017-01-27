package org.mocks.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mocks.domain.Comment;
import org.mocks.domain.Post;

/**
 * Service for post related operations.
 */
public class PostService {
	
	List<Post> allPosts;
	
	/**
	 * Constructor
	 * 
	 * @param allPosts
	 */
	public PostService(List<Post> allPosts) {
		allPosts = allPosts != null ? allPosts : new ArrayList<>();
	}
	
	/**
	 * Returns all posts created by the {@link User}.
	 * 
	 * @param username the username of the {@link User}
	 * @return List of {@link Post}
	 */
	public List<Post> getUserPosts(String username) {
		List<Post> posts = allPosts.stream()
				//filter posts by username
				.filter(p -> p.getUsername().equals(username))
				//Convert filter stream to List
				.collect(Collectors.toList());
		return posts;
	}
	
	/**
	 * Returns all comments added on any post by the {@link User}.
	 * 
	 * @param username the username of the {@link User}
	 * @return List of {@link Comment}
	 */
	public List<Comment> getUserComments(String username) {
		List<Comment> comments = new ArrayList<>();
		allPosts.stream()
		//Check each post
		.forEach(p -> {
			//Add all comments created by the user
			comments.addAll(p.getComments().stream()
					//Filter comments with username
					.filter(c -> c.getUsername().equals(username))
					//Convert the stream to List
					.collect(Collectors.toList()));
		});
		return comments;
	}
	
	/**
	 * Returns the username who made the comment. Input comment should be the
	 * exact comment text.
	 * 
	 * @param comment the exact comment text.
	 * @return username the username who created the comment.
	 */
	public String findCommentUser(String comment) {
		String username = allPosts.stream()
				//Get all post comments
				.map(Post::getComments)
				//Convert List<List<Comment>> to List<Comment>
				.flatMap(List::stream)
				//Filter by comment search string
				.filter(c -> c.getText().equals(comment))
				//Get the first match
				.findFirst()
				//Get Comment Object
				.get()
				//Get username
				.getUsername();
		return username;
	}
	
	/**
	 * Returns the username who created the post. The input post content should 
	 * be the exact post.
	 * 
	 * @param post the post string
	 * @return username the username who created the post.
	 */
	public String findPostUser(String post) {
		String username = allPosts.stream()
				//Filter the stream for the post value
				.filter(p -> p.getContent().equals(post))
				//Find the first match
				.findFirst()
				//Get the post object
				.get()
				//Return username
				.getUsername();
		return username;
	}
	
	/**
	 * Returns all posts which contains the search string. 
	 * 
	 * @param post the post search string.
	 * @return List of {@link Post}
	 */
	public List<Post> searchPost(String post) {
		List<Post> posts = allPosts.stream()
				//Filter by post search string
				.filter(p -> p.getContent().contains(post))
				//Convert to list
				.collect(Collectors.toList());
		return posts;
	}
	
	/**
	 * Returns all comments which contains the search string. 
	 * 
	 * @param comment the comment search string.
	 * @return List of {@link Comment}
	 */
	public List<Comment> searchComment(String comment) {
		List<Comment> comments = new ArrayList<>();
		allPosts.stream()
		//Check comments of all posts
		.forEach(p -> {
			//Add all comments 
			comments.addAll(p.getComments().stream()
					//Filter by comment search string
					.filter(c -> c.getText().contains(comment))
					//Convert to list
					.collect(Collectors.toList()));
		});
		return comments;
	}
	
	/**
	 * Returns the first post.
	 * 
	 * @return {@link Post} the first post
	 */
	public Post getFirstPost() {
		return allPosts.get(0);
	}

}
