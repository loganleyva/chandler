package test.com.lleyva.chandler.services;

import com.lleyva.chandler.data.Account;
import com.lleyva.chandler.data.Post;
import com.lleyva.chandler.data.repositories.RepositoryBase;
import com.lleyva.chandler.services.AccountsService;
import com.lleyva.chandler.services.PostsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Instant;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class PostsServiceTests {

	////////////////
	// Attributes //
	////////////////

	private Random random = new Random();

	////////////////
	// Injections //
	////////////////

	@InjectMocks
	private PostsService testPostsService;

	@Mock
	private RepositoryBase<Post> mockPostRepositoryBase;

	@Mock
	private AccountsService mockAccountsService;

	///////////
	// Setup //
	///////////

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	///////////
	// Tests //
	///////////

	@Test
	public void createTest() {
		// Init Test Object //
		UUID testExposedId = UUID.randomUUID();

		Post post = new Post();
		post.setId(random.nextLong());
		post.setExposedId(testExposedId);
		post.setCreatedTimeDate(Instant.EPOCH);
		post.setAuthor(UUID.randomUUID());
		post.setPicture(UUID.randomUUID());
		post.setMessage("some message");

		// Configure Mocks //
		Account account = new Account();
		Mockito.doReturn(account).when(mockAccountsService)
				.getCurrentAccount();

		// Call and Verify //
		testPostsService.create(post);

		ArgumentCaptor<Post> postArgumentCaptor = ArgumentCaptor.forClass(Post.class);
		Mockito.verify(mockPostRepositoryBase, Mockito.times(1))
				.save(postArgumentCaptor.capture());

		Post capturedPost = postArgumentCaptor.getValue();
		Assert.assertNull(capturedPost.getId());
		Assert.assertNotEquals(Instant.EPOCH, capturedPost.getCreatedTimeDate());
		Assert.assertNotEquals(testExposedId, capturedPost.getExposedId());
		Assert.assertEquals(account.getExposedId(), capturedPost.getAuthor());
		Assert.assertEquals("some message", capturedPost.getMessage());
	}

	@Test
	public void readTest() {
		// Init Test Objects //
		UUID testId = UUID.randomUUID();
		Post testPost = new Post();
		testPost.setExposedId(testId);

		// Configure Mocks //
		Mockito.doReturn(Optional.of(testPost)).when(mockPostRepositoryBase)
				.findByExposedId(testId);

		// Call and Verify //
		Post foundPost = testPostsService.read(testId);

		Mockito.verify(mockPostRepositoryBase, Mockito.times(1))
				.findByExposedId(testId);

		Assert.assertNotNull(foundPost);
		Assert.assertEquals(testPost, foundPost);
	}

	@Test
	public void deleteTest() {
		// Init Test Objects //
		UUID testId = UUID.randomUUID();

		// Call and Verify //
		testPostsService.delete(testId);

		Mockito.verify(mockPostRepositoryBase, Mockito.times(1))
				.deleteByExposedId(testId);
	}

}
