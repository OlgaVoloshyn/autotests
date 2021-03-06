package dmsDws.settings.websiteSettings.general;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utility.ConfigurationManager;
import utility.Page;

/**
 * Created by Julia on 19.04.2017.
 */
public class GeneralWebsiteSettings extends Page {

    /*declare elements on the page*/
    @FindBy(how = How.ID, using = "tab2")
    private WebElement leadsEmailTab;

    @FindBy(how = How.ID, using = "tab7")
    private WebElement localizationTab;

    @FindBy(how = How.XPATH, using = "//tr[@id='forms_captcha']//a[@class='button-style b_edit notranslate']")
    private WebElement captchaEditBtn;

    @FindBy(how = How.XPATH, using = "//tr[@id='forms_captcha']//a[@class='button-style b_save']")
    private WebElement captchaSaveBtn;

    @FindBy(how = How.XPATH, using = "//tr[@id='forms_captcha']//input[@value='1']")
    private WebElement captchaOnRadio;

    @FindBy(how = How.XPATH, using = "//tr[@id='forms_captcha']//input[@value='0']")
    private WebElement captchaOffRadio;

    @FindBy(how = How.XPATH, using = "//tr[@id='type_of_captcha']//a[@class='button-style b_edit notranslate']")
    private WebElement captchaTypeEditBtn;

    @FindBy(how = How.XPATH, using = "//tr[@id='type_of_captcha']//a[@class='button-style b_save']")
    private WebElement captchaTypeSaveBtn;

    @FindBy(how = How.XPATH, using = "//tr[@id='type_of_captcha']//select")
    private WebElement captchaTypeSelect;

    @FindBy(how = How.XPATH, using = "//tr[@id='jquery_version']//a[@class='button-style b_edit notranslate']")
    private WebElement jQueryEditBtn;

    @FindBy(how = How.XPATH, using = "//tr[@id='jquery_version']//a[@class='button-style b_save']")
    private WebElement jQuerySaveBtn;

    @FindBy(how = How.XPATH, using = "//tr[@id='jquery_version']//input")
    private WebElement jQueryInput;

    @FindBy(how = How.XPATH, using = "//tr[@id='jquery_version']//span[@class='settings_view dotted_note']")
    private WebElement jQueryValue;

    @FindBy(how = How.XPATH, using = "//tr[@id='redirect_404']//a[@class='button-style b_edit notranslate']")
    private WebElement the404EditBtn;

    @FindBy(how = How.XPATH, using = "//tr[@id='redirect_404']//a[@class='button-style b_save']")
    private WebElement the404SaveBtn;

    @FindBy(how = How.XPATH, using = "//tr[@id='redirect_404']//select")
    private WebElement the404Select;

    public GeneralWebsiteSettings(ConfigurationManager manager, WebDriver driver) {
        super(manager, driver);
    }

    /*open Leads Email tab*/
    public void clickOnLeadsTab() {
        leadsEmailTab.click();
    }

    /*open Localization tab */
    public void clickOnLocalizationTab() {
        localizationTab.click();
    }

    /*Turn on Captcha*/
    public void enableCaptcha() {
        captchaEditBtn.click();
        captchaOnRadio.click();
        captchaSaveBtn.click();
    }

    /*Turn off Captcha*/
    public void disableCaptcha() {
        captchaEditBtn.click();
        captchaOffRadio.click();
        captchaSaveBtn.click();
    }

    /*set Captcha Type - Captcha*/
    public void setCaptchaType() {
        captchaTypeEditBtn.click();
        WebElement select = captchaTypeSelect;
        Select options = new Select(select);
        options.selectByValue("Captcha");
        captchaTypeSaveBtn.click();
    }

    /*set Captcha Type - ReCaptcha*/
    public void setReCaptchaType() {
        captchaTypeEditBtn.click();
        WebElement select = captchaTypeSelect;
        Select options = new Select(select);
        options.selectByValue("ReCaptcha");
        captchaTypeSaveBtn.click();
    }

    /*set Captcha Type - Motion*/
    public void setMotionCaptchaType() {
        captchaTypeEditBtn.click();
        WebElement select = captchaTypeSelect;
        Select options = new Select(select);
        options.selectByValue("Motion");
        captchaTypeSaveBtn.click();
    }

    /*set Captcha Type - ReCaptcha2*/
    public void setReCaptcha2Type() {
        captchaTypeEditBtn.click();
        WebElement select = captchaTypeSelect;
        Select options = new Select(select);
        options.selectByValue("ReCaptcha V2");
        captchaTypeSaveBtn.click();
    }

    /*set jQuery version - 1.11.2 (if it is not equal to that)*/
    public void setjQueryVersion(String version) {
        if (jQueryValue.getText() != version) {
            jQueryEditBtn.click();
            jQueryInput.clear();
            jQueryInput.sendKeys(version);
            jQuerySaveBtn.click();
        }
    }

    public void set404Redir(String value404){
        the404EditBtn.click();
        WebElement select = the404Select;
        Select options = new Select(select);
        options.selectByVisibleText(value404);
    }
}
