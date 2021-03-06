package gradle.cucumber;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;

import static org.assertj.core.api.Assertions.assertThat;

public class SpreadsheetStepdefs {
    private SpreadSheet sheet;

    @Given("^Empty spreadsheet")
    public void iEmptySpreadSheet() throws Throwable {
        sheet = new SpreadSheet();
    }

    @When("^I set \"([^\"]*)\" content with \"([^\"]*)\"$")
    public void iSetContentWith(String cellAddress, String integerValue) throws Throwable {
        sheet.set(cellAddress, Integer.parseInt(integerValue));
    }

    @When("^I set \"([^\"]*)\" content with label \"([^\"]*)\"$")
    public void iSetContentWithLabel(String cellAddress, String label) throws Throwable {
        sheet.set(cellAddress, label);
    }

    @Then("^The cell \"([^\"]*)\" has value \"([^\"]*)\"$")
    public void iGetContent(String cellAddress, String integerValue) throws Throwable {
        Object actual = sheet.get(cellAddress );
        Object expected = Integer.parseInt(integerValue);

        assertThat(actual).isEqualTo(expected);
    }

    @Then("^The cell \"([^\"]*)\" has value label \"([^\"]*)\"$")
    public void the_cell_has_value_label(String cellAddress, String label) {
        Object actual = sheet.get(cellAddress );
        Object expected = label;

        assertThat(actual).isEqualTo(expected);
    }

    @And("^I set \"([^\"]*)\" using a formula \"([^\"]*)\"$")
    public void iSetUsingAFormula(String cellAddress, String formula) throws Throwable {
        String reference;
        if (  formula.equals("=id(a1)")) {
            reference = "a1";
        } else {
            reference = "a9";
        }
        sheet.set(cellAddress, new Identity(sheet.getReference(reference)));
    }
}
