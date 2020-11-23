package miniprojekti;
        
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import miniprojekti.domain.*;
import miniprojekti.data_access.*;
import static org.mockito.Mockito.*;

public class Stepdefs {
    Logic logic;
    ReadingTipDao testDao;
    List<String> inputLines;

    
    @Before
    public void setup(){
        testDao = mock(ReadingTipDao.class);
        logic = new Logic(testDao);     
    }



    @Given("add is selected")
    public void addIsSelected() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


    @When("new reading tip is entered")
    public void newReadingTipIsEntered() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("new reading tip is added")
    public void newReadingTipIsAdded() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}

