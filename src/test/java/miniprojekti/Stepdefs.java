package miniprojekti;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import miniprojekti.data_access.ReadingTipDao;
import miniprojekti.domain.Logic;

public class Stepdefs {
    Logic logic;
    ReadingTipDao testdao;
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://readingtips.herokuapp.com/";

    @Before
    public void setup() {
        testdao = mock(ReadingTipDao.class);
        logic = new Logic(testdao);
    }

    @Given("add is selected")
    public void addIsSelected() {
        driver.get(baseUrl);
        assertTrue(driver.getPageSource().contains("Add a new tip"));
    }

    @When("new reading tip is entered")
    public void newReadingTipIsEntered() {
        WebElement element = driver.findElement(By.name("author"));
        element = fillTheFields(element);
        assertNotNull(driver.findElement(By.name("author")).toString());
        assertNotNull(driver.findElement(By.name("title")).toString());
        assertNotNull(driver.findElement(By.name("url")).toString());
    }

    @Then("new reading tip is added")
    public void newReadingTipIsAdded() {
        WebElement element = driver.findElement(By.name("author"));
        ;
        element = fillTheFields(element);
        element.submit();
        pageHasContent("Tip added succesfully");
    }

    @Given("User is on the frontpage")
    public void isFrontPage() {
        driver.get(baseUrl);
        pageHasContent("Add a new tip");
    }

    @Then("User can see the list")
    public void listIsShown() {
        List<WebElement> elements = driver.findElements(By.name("author"));
        verify(testdao).equals(elements);
    }

    // apumetodit

    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    private WebElement fillTheFields(WebElement element) {
        element = driver.findElement(By.name("author"));
        element.clear();
        element.sendKeys("testiAuthor");
        element = driver.findElement(By.name("title"));
        element.clear();
        element.sendKeys("testiTitle");
        element = driver.findElement(By.name("url"));
        element.clear();
        element.sendKeys("www.fi");
        return element;
    }
}