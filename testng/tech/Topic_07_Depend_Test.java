package tech;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_07_Depend_Test {
    @Test
    public void Product_01_Create () {
        Assert.assertTrue(false);

    }

    @Test (dependsOnMethods = "Product_01_Create")
    public void Product_02_View () {

    }

    @Test(dependsOnMethods = "Product_01_Create")
    public void Product_03_Edit () {

    }

    @Test(dependsOnMethods = {"Product_01_Create","Product_03_Edit"})
    public void Product_04_Delete () {

    }
}
