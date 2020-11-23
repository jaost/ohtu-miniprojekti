package miniprojekti;
        
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import miniprojekti.domain.*;
import miniprojekti.data_access.*;
import static org.mockito.Mockito.*;

public class Stepdefs {
    Logic logic;
    ReadingTipDao testdao;
    
    @Before
    public void setup(){
        testdao = mock(ReadingTipDao.class);
        logic = new Logic(testdao);     
    }

    @Given("add is selected")
    public void addIsSelected() {
        // miten lomakkeen lahetys tarkistetaan?
        throw new io.cucumber.java.PendingException();
    }

    @When("new reading tip is entered")
    public void newReadingTipIsEntered() {
        verify(logic).saveNewTip(anyString(), anyString(), anyString());
    }
    
    @Then("new reading tip is added")
    public void newReadingTipIsAdded() {
        verify(testdao).save(anyString(), anyString(), anyString());
    }
}

