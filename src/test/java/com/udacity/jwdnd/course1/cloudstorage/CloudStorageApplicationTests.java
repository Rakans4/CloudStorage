package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	private SignupPage signupPage;
	private LoginPage loginPage;
	private HomePage homePage;
	private ResultPage resultPage;


	@BeforeAll
	static void beforeAll() {
		WebDriverManager.firefoxdriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new FirefoxDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

//	Write a test that verifies that an unauthorized user can only access the login and signup pages.
	@Test
	@Order(1)
	public void testAuthorizedAccess() {
		this.driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());

		this.driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());

		this.driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}

//	Write a test that signs up a new user, logs in, verifies that the home page is accessible, logs out, and verifies that the home page is no longer accessible.
	@Test
	@Order(2)
	public void testSignUpAndLogin() {
		this.signupPage = new SignupPage(driver);
		this.loginPage = new LoginPage(driver);
		String username = "testUser";
		String password = "123";
		String firstName = "Test";
		String lastName = "User";

		this.driver.get("http://localhost:" + this.port + "/signup");
		this.signupPage.signup(firstName, lastName, username, password);
		Assertions.assertTrue(signupPage.isSignedUp());

		this.driver.get("http://localhost:" + this.port + "/login");
		this.loginPage.login(username, password);
		Assertions.assertEquals("Home", driver.getTitle());
	}


//	Write a test that creates a note, and verifies it is displayed.
	@Test
	@Order(3)
	public void testAddingAndDisplayingNote() {
		signupAndLogin();

		this.homePage = new HomePage(driver);
		this.resultPage = new ResultPage(driver);
		String title = "Test Note title";
		String description = "Test Note description";
		this.homePage.addNote(title, description);
		this.resultPage.returnHome();
		this.homePage.goToNotes();
		Assertions.assertEquals(title, this.homePage.getNote().getNotetitle());
		this.homePage.deleteNote();
	}

//	Write a test that edits an existing note and verifies that the changes are displayed.
	@Test
	@Order(4)
	public void testEditingNote() {
		signupAndLogin();
		addNewNote();

		this.homePage.goToNotes();
		String title = "Edited";
		String description = "Test Note description";

		this.homePage.editNote(title, description);
		this.resultPage.returnHome();
		this.homePage.goToNotes();
		Assertions.assertEquals("Edited", this.homePage.getNote().getNotetitle());
		this.homePage.deleteNote();
	}
//	Write a test that deletes a note and verifies that the note is no longer displayed.
	@Test
	@Order(5)
	public void testDeletingNote() {
		signupAndLogin();

		this.homePage = new HomePage(driver);
		this.resultPage = new ResultPage(driver);

		String title = "Test Note title";
		String description = "Test Note description";

		this.homePage.addNote(title, description);
		this.resultPage.returnHome();
		this.homePage.goToNotes();

		this.homePage.deleteNote();
		this.resultPage.returnHome();
		this.homePage.goToNotes();
		Assertions.assertTrue(this.homePage.isNotesTableEmpty());
	}

//	Write a test that creates a set of credentials, verifies that they are displayed, and verifies that the displayed password is encrypted.
	@Test
	@Order(6)
	public void testAddingCredentials(){
		signupAndLogin();

		this.homePage = new HomePage(driver);
		this.resultPage = new ResultPage(driver);
		String url = "gmail.com";
		String username = "test@gmail.com";
		String password = "pass123";
		this.homePage.addCredentials(url, username, password);
		this.resultPage.returnHome();
		this.homePage.goToCredentials();
		Assertions.assertEquals(url, this.homePage.getCredentials().getUrl());
		this.homePage.deleteCredentials();
	}

//	Write a test that views an existing set of credentials, verifies that the viewable password is unencrypted, edits the credentials, and verifies that the changes are displayed.
	@Test
	@Order(7)
	public void testPasswordEncryption() {
		signupAndLogin();

		this.homePage = new HomePage(driver);
		this.resultPage = new ResultPage(driver);
		String url = "gmail.com";
		String username = "test@gmail.com";
		String password = "pass123";
		this.homePage.addCredentials(url, username, password);
		this.resultPage.returnHome();
		this.homePage.goToCredentials();
		Assertions.assertNotEquals(password, this.homePage.getCredentials().getPassword());
		this.homePage.deleteCredentials();
	}

//	Write a test that deletes an existing set of credentials and verifies that the credentials are no longer displayed.
	@Test
	@Order(8)
	public void testDeletingCredentials() {
		signupAndLogin();

		this.homePage = new HomePage(driver);
		this.resultPage = new ResultPage(driver);
		String url = "gmail.com";
		String username = "test@gmail.com";
		String password = "pass123";
		this.homePage.addCredentials(url, username, password);
		this.resultPage.returnHome();
		this.homePage.goToCredentials();
		this.homePage.deleteCredentials();
		this.resultPage.returnHome();
		this.homePage.goToCredentials();
		Assertions.assertTrue(this.homePage.isCredentialsTableEmpty());
	}

	public void signupAndLogin() {
		this.signupPage = new SignupPage(driver);
		this.loginPage = new LoginPage(driver);
		String username = "testUser";
		String password = "123";
		String firstName = "Test";
		String lastName = "User";
		this.driver.get("http://localhost:" + this.port + "/signup");
		this.signupPage.signup(firstName, lastName, username, password);

		this.driver.get("http://localhost:" + this.port + "/login");
		this.loginPage.login(username, password);
	}

	public void addNewNote() {
		this.homePage = new HomePage(driver);
		this.resultPage = new ResultPage(driver);

		String title = "Test Note title";
		String description = "Test Note description";

		this.homePage.addNote(title, description);
		this.resultPage.returnHome();
	}
}
