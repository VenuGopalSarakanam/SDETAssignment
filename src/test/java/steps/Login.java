package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;


public class Login{
	WebDriver driver = null;
	RequestSpecification request;
		Response response;
		String getURI="https://reqres.in/api/users/2";
		String postReq="https://reqres.in/api/users";


	@Given("Browser is open")
	public void browser_is_open() throws InterruptedException {
	   
		System.setProperty("webdriver.chrome.driver","./chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		Thread.sleep(2000);
		driver.manage().window().maximize();
		throw new io.cucumber.java.PendingException();
	}

	@Given("User is in login page")
	public void user_is_in_login_page() {
	    // Write code here that turns the phrase above into concrete actions
		String actualTitle = driver.getTitle();
		String expectedTitle = "OrangeHRM";
		if(actualTitle.equalsIgnoreCase(expectedTitle))
			System.out.println("Title Matched");
		else
			System.out.println("Title didn't match");
	    throw new io.cucumber.java.PendingException();
	}

	@When("User enters valid username and password")
	public void user_enters_valid_username_and_password() {
	    // Write code here that turns the phrase above into concrete actions
		WebElement username=driver.findElement(By.xpath("//input[@name='username']"));
		   username.sendKeys("Admin");
		   WebElement password=driver.findElement(By.xpath("//input[@name='password']"));
		   password.sendKeys("admin123");
	    throw new io.cucumber.java.PendingException();
	}

	@When("Click Submit Button")
	public void click_submit_button() {
		WebElement loginButton=driver.findElement(By.xpath("//*[text()=' Login ']"));
		 loginButton.click();
	    throw new io.cucumber.java.PendingException();
	}

	@When("User clicks Admin options")
	public void user_clicks_admin_options() {
	   
		WebElement adminButton=driver.findElement(By.xpath("//span[text()='Admin']"));
		 adminButton.click();
	    throw new io.cucumber.java.PendingException();
	}

	@When("Click Job Nav link")
	public void click_job_nav_link() {
	    // Write code here that turns the phrase above into concrete actions
		WebElement jobButton=driver.findElement(By.xpath("//span[text()='Job ']"));
		 jobButton.click();
		 WebElement jobTitleButton=driver.findElement(By.xpath("//a[text()='Job Titles']"));
		 jobTitleButton.click();
	    throw new io.cucumber.java.PendingException();
	}

	@Then("User clicks on QA Engineer")
	public void user_clicks_on_qa_engineer() {
		
		WebElement qaEngineerCheckBox=driver.findElement(By.xpath("//div[text()='QA Engineer']/ancestor::div[@class='oxd-table-card']//input"));	
		if ( !qaEngineerCheckBox.isSelected() )
		{
			qaEngineerCheckBox.click();
		}
	    throw new io.cucumber.java.PendingException();
	}
	
	@Given("User sets request Header")
	public void user_sets_request_Header() {
	    // Write code here that turns the phrase above into concrete actions
		
		RestAssured.baseURI = this.postReq;
		System.out.println("Base URL :"+RestAssured.baseURI );
		
		//RequestSpecification request = RestAssured.given();
	    JSONObject requestParams = new JSONObject();
	    requestParams.put("name", "Venu1");
	    requestParams.put("job", "AutomationQA");
	    request.header("Content-Type","application/json");
	    throw new io.cucumber.java.PendingException();
		
		
	}
	


	@When("User sends Post Request")
	public void user_sends_Post_Request() {
	    // Write code here that turns the phrase above into concrete actions
		 response = request.post(RestAssured.baseURI);
		    System.out.println(response.asPrettyString());
		    request.header("Content-Type","application/json");
		
	
	}
	
	
 

	@Then("User gets response")
	public void user_gets_response() {
	    // Write code here that turns the phrase above into concrete actions
		String getUrl="https://reqres.in/api/users/";
		String id=response.getBody().jsonPath().getString("id");

		 response = request.get(getUrl+id);
		 System.out.println(response.getBody().asPrettyString());
		 
	    throw new io.cucumber.java.PendingException();
	}

}
 
	