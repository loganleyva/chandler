package test.com.lleyva.chandler.data.repositories;

import com.lleyva.chandler.data.Post;
import com.lleyva.chandler.data.repositories.PostsRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import test.com.lleyva.chandler.data.config.DataTestConfiguration;

import java.util.Optional;
import java.util.UUID;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = DataTestConfiguration.class)
public class PostsRepositoryTests {

	////////////////
	// Attributes //
	////////////////

	////////////////
	// Injections //
	////////////////

	@Autowired
	private PostsRepository postsRepository;

	///////////
	// Tests //
	///////////

	@Test
	public void saveAndReadTest() {
		// Init Test Objects //
		String testMessage = RandomStringUtils.randomAlphanumeric(100);

		Post post = new Post();
		post.setAuthor(UUID.randomUUID());
		post.setMessage(testMessage);

		// Persist and Verify //
		post = postsRepository.save(post);
		Assert.assertNotNull(post.getId());
		Assert.assertEquals(testMessage, post.getMessage());

		Optional<Post> readMessage = postsRepository.findById(post.getId());
		Assert.assertTrue(readMessage.isPresent());
		Assert.assertEquals(testMessage, readMessage.get().getMessage());

		readMessage = postsRepository.findByExposedId(post.getExposedId());
		Assert.assertTrue(readMessage.isPresent());
		Assert.assertEquals(testMessage, readMessage.get().getMessage());
	}

}
