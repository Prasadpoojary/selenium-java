package org.prasad;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class Main {
    public static void main(String[] args)
    {
        WebDriverManager.chromedriver().setup();

        // Initialize WebDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);
        String url ="http://127.0.0.1:5500/Index.html";
        driver.navigate().to(url);
        System.out.println(driver.getTitle());

        WebElement operAelementInp = driver.findElement(By.id("operand-a"));
        WebElement operBelementInp = driver.findElement(By.id("operand-b"));

        WebElement addBtnElement = driver.findElement(By.id("add-btn"));
        WebElement subBtnElement = driver.findElement(By.id("sub-btn"));
        WebElement mulBtnElement = driver.findElement(By.id("mul-btn"));
        WebElement divBtnElement = driver.findElement(By.id("div-btn"));

        WebElement outuptSpanElement = driver.findElement(By.id("output"));

        int a=20,b=5;

        operAelementInp.sendKeys(String.valueOf(a));
        operBelementInp.sendKeys(String.valueOf(b));

        addBtnElement.click();
        testCalc("add",Integer.parseInt(outuptSpanElement.getText()),a,b);

        subBtnElement.click();
        testCalc("sub",Integer.parseInt(outuptSpanElement.getText()),a,b);

        mulBtnElement.click();
        testCalc("mul",Integer.parseInt(outuptSpanElement.getText()),a,b);

        divBtnElement.click();
        testCalc("div",Integer.parseInt(outuptSpanElement.getText()),a,b);
    }



    private static void testCalc(String operation,int outupt,int a, int b)
    {
        BusinessLogic businessLogic=new BusinessLogic();

        boolean testresult= switch (operation) {
            case "add" -> outupt == businessLogic.add(a, b);
            case "sub" -> outupt == businessLogic.subtract(a, b);
            case "mul" -> outupt == businessLogic.multiply(a, b);
            case "div" -> outupt == businessLogic.division(a, b);
            default -> false;
        };

        if(testresult)
        {
            System.out.println("Tested operation : "+operation+" successfully");
        }
        else
        {
            System.out.println("Testcase failed for  : "+operation);
        }

    }


}