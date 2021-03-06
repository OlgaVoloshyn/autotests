package dmsDws.tools.map2.widgets.advancedSearchHorizontal;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import utility.LogFactory;
import utility.PropertyLoader;
import utility.PropertySource;

import java.util.List;

/**
 * Created by Julia on 24.04.2017.
 */
public class AdvSearchHorizMakeSelectOnInventory extends AdvSearchHorizTestBaseInventory {

    private static final Logger LOG = LogFactory.getLogger(AdvSearchHorizMakeSelectOnInventory.class);


    @Test
    public void isMakeSelectEnabledOnHomePage() {
        driver.get(PropertyLoader.loadProperty(PropertySource.URL,"dws.url") + PropertyLoader.loadProperty(PropertySource.URL,"inventory.url"));
        waitForJSandJQueryToLoad();
        Assert.assertTrue(manager.getSearchDws(driver).isMakeSelectEnabled());
    }

    @Test
    public void makesAreTheSameWithInventory() {
        driver.manage().deleteAllCookies();
        driver.get(PropertyLoader.loadProperty(PropertySource.URL,"dms.url"));
        waitForJSandJQueryToLoad();
        manager.getDmsLoginPage(driver).loginToDms(PropertyLoader.loadProperty(PropertySource.CRED, "super.login"), PropertyLoader.loadProperty(PropertySource.CRED,"super.pw"));
        waitForJSandJQueryToLoad();
        wait.until(isHomeBreadcrumbsVisible());
        manager.getDmsMenu(driver).clickOnInventoryMenu();
        waitForJSandJQueryToLoad();
        List<String> optionMake = manager.getInventoryDms(driver).getMakeSelectOptionsText();
        driver.get(PropertyLoader.loadProperty(PropertySource.URL,"dws.url") + PropertyLoader.loadProperty(PropertySource.URL,"inventory.url"));
        waitForJSandJQueryToLoad();
        for (int i = 1; i < manager.getSearchDws(driver).getMakeSelectOptions().size(); i++) { //loop starts from 1, because there are differences in search and dma inventory ('Make' and 'Any Make')
            LOG.info("InvOption is " + optionMake.get(i));
            LOG.info("SearchOption is " + manager.getSearchDws(driver).getMakeSelectOptions().get(i).getText().trim());
            Assert.assertEquals(manager.getSearchDws(driver).getMakeSelectOptions().get(i).getText().trim(), optionMake.get(i));
        }
    }

    @Test
    public void makeValueIsInSearchResult() {
        driver.get(PropertyLoader.loadProperty(PropertySource.URL,"dws.url") + PropertyLoader.loadProperty(PropertySource.URL,"inventory.url"));
        waitForJSandJQueryToLoad();
        for (int i = 1; i < manager.getSearchDws(driver).getMakeSelectOptions().size(); i++) {
            manager.getSearchDws(driver).selectMakeByIndex(i);
            waitForJSandJQueryToLoad();
            String optionValue = manager.getSearchDws(driver).getMakeSelectValue();
            manager.getSearchDws(driver).clickSearchBtn();
            waitForJSandJQueryToLoad();
            Assert.assertTrue(driver.getCurrentUrl().contains("_make_" + optionValue));
            driver.get(PropertyLoader.loadProperty(PropertySource.URL,"dws.url") + PropertyLoader.loadProperty(PropertySource.URL,"inventory.url"));
            waitForJSandJQueryToLoad();
        }
    }

    @Test
    public void makeSelected() {
        driver.get(PropertyLoader.loadProperty(PropertySource.URL,"dws.url") + PropertyLoader.loadProperty(PropertySource.URL,"inventory.url"));
        waitForJSandJQueryToLoad();
        for (int i = 0; i < manager.getSearchDws(driver).getMakeSelectOptions().size(); i++) {
            manager.getSearchDws(driver).selectMakeByIndex(i);
            waitForJSandJQueryToLoad();
            LOG.info("actual is " + manager.getSearchDws(driver).getMakeSelectText());
            LOG.info("expected is " + manager.getSearchDws(driver).getMakeSelectOptions().get(i).getText());
            Assert.assertEquals(manager.getSearchDws(driver).getMakeSelectText().trim(), manager.getSearchDws(driver).getMakeSelectOptions().get(i).getText().trim());
        }
    }

    @Test
    public void selectMakeByDefault() {
        driver.get(PropertyLoader.loadProperty(PropertySource.URL,"dws.url") + PropertyLoader.loadProperty(PropertySource.URL,"inventory.url"));
        waitForJSandJQueryToLoad();
        Assert.assertEquals(manager.getSearchDws(driver).getMakeSelectText(), "Any Make");
    }

    @Test
    public void noMakeInSearchResult() {
        driver.get(PropertyLoader.loadProperty(PropertySource.URL,"dws.url") + PropertyLoader.loadProperty(PropertySource.URL,"inventory.url"));
        waitForJSandJQueryToLoad();
        manager.getSearchDws(driver).clickSearchBtn();
        waitForJSandJQueryToLoad();
        Assert.assertFalse(driver.getCurrentUrl().contains("_make_"));
    }

    @Test
    public void clickedMakeSelectBackground() {
        driver.get(PropertyLoader.loadProperty(PropertySource.URL,"dws.url") + PropertyLoader.loadProperty(PropertySource.URL,"inventory.url"));
        waitForJSandJQueryToLoad();
        manager.getSearchDws(driver).selectMakeByIndex(1);
        waitForJSandJQueryToLoad();
        Assert.assertEquals(manager.getSearchDws(driver).getMakeSelectBorderColor(), PropertyLoader.loadProperty(PropertySource.CSS,"border_color_gray2"));
    }

    @Test
    public void makeIsFromURL() {
        driver.manage().deleteAllCookies();
        driver.get(PropertyLoader.loadProperty(PropertySource.URL,"dms.url"));
        waitForJSandJQueryToLoad();
        manager.getDmsLoginPage(driver).loginToDms(PropertyLoader.loadProperty(PropertySource.CRED, "super.login"), PropertyLoader.loadProperty(PropertySource.CRED,"super.pw"));
        waitForJSandJQueryToLoad();
        wait.until(isHomeBreadcrumbsVisible());
        manager.getDmsMenu(driver).clickOnInventoryMenu();;
        waitForJSandJQueryToLoad();
        List<WebElement> optionMake = manager.getInventoryDms(driver).getMakeSelectOptions();
        List<String> optionMakeValue = manager.getInventoryDms(driver).getMakeSelectOptionsValue();
        for (int i = 1; i < optionMake.size(); i++) {
            driver.get(PropertyLoader.loadProperty(PropertySource.URL,"dws.url") + PropertyLoader.loadProperty(PropertySource.URL,"inventory.url") + "_make_" + optionMakeValue.get(i));
            waitForJSandJQueryToLoad();
            LOG.info("make in URL is " + optionMakeValue.get(i));
            Assert.assertEquals(manager.getSearchDws(driver).getMakeSelectValue(), optionMakeValue.get(i));
        }
    }

    @Test
    public void advSearchHorizMakeSelectDisplayed() {
        driver.get(PropertyLoader.loadProperty(PropertySource.URL,"dws.url") + PropertyLoader.loadProperty(PropertySource.URL,"inventory.url"));
        waitForJSandJQueryToLoad();
        Assert.assertTrue(manager.getSearchDws(driver).isMakeSelectExists());
    }

    @Test
    public void advSearchHorizMakeLabelDisplayed() {
        driver.get(PropertyLoader.loadProperty(PropertySource.URL,"dws.url") + PropertyLoader.loadProperty(PropertySource.URL,"inventory.url"));
        waitForJSandJQueryToLoad();
        Assert.assertTrue(manager.getSearchDws(driver).isMakeSelectLabelExists());
    }

}
