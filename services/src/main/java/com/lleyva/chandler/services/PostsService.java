package com.lleyva.chandler.services;

import com.lleyva.chandler.data.Account;
import com.lleyva.chandler.data.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.Instant;

@Service
public class PostsService extends CrudServiceBase<Post> {

	////////////////
	// Injections //
	////////////////

	@Autowired
	private AccountsService accountsService;

	////////////////////
	// Public Methods //
	////////////////////

	/**
	 * Persist(/Create) passed in Post for the User
	 * @param post
	 * @return
	 */
	@Override
	public Post create(
			Post post) {
		Assert.notNull(post, "entity is null");

		// Assign the Authenticated User as the Post author //
		Account account = accountsService.getCurrentAccount();
		post.setAuthor(account.getExposedId());
		post.setCreatedTimeDate(Instant.now());

		return super.create(post);
	}

}
