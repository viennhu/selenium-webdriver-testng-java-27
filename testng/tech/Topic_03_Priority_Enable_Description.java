package tech;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_03_Priority_Enable_Description {

    @Test(priority = 1)
    public void TC_01_AssertTrue() {
        String country = "Vietnam";
        Boolean countryisVietnam = country.equals("Vietnam");
        Assert.assertTrue(countryisVietnam);
    }
    @Test(priority = 3)
    public void TC_02_AssertFalse() {
        String country = "Germany";
        Boolean countrycontainsGermany = country.contains("Vietnam");
        Assert.assertFalse(countrycontainsGermany);
    }

    @Test(priority = 2)
    public void TC_03_AssertEquals() {
        String country = "Germany";
        Assert.assertEquals(country,"Germany");
    }

    @Test(priority = 4, enabled = false,description = "assert")
    public void TC_04_AssertNull_NotNull() {
        String country = null;
        Topic_02_Assert topic02;
        Assert.assertNull(country);
        topic02 = new Topic_02_Assert();
        country = "Germany";
        Assert.assertNotNull(country);
    }


}
