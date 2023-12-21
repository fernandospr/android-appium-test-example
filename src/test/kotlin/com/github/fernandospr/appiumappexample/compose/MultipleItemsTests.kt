package com.github.fernandospr.appiumappexample.compose

import BaseAndroidTest
import io.appium.java_client.AppiumBy
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MultipleItemsTests : BaseAndroidTest() {

    override fun getActivity() = "${APP_ID}.compose.MultipleItemsActivity"

    @Test
    fun `Inputting value and clicking Set should update result text view`() {
        with(driver) {
            val newValue = "Hello"
            val existingValue = "Item 30"
            scrollTo(existingValue)
            val parentElement = findElement(AppiumBy.xpath("//*[@text='$existingValue']/.."))
            val editTextExampleElement =
                parentElement.findElement(By.id("${APP_ID}:id/editText_example"))
            val buttonSetElement = parentElement.findElement(By.id("${APP_ID}:id/button_set"))
            editTextExampleElement.click()
            editTextExampleElement.sendKeys(newValue)
            buttonSetElement.click()

            val textViewResultElementText =
                parentElement.findElement(By.id("${APP_ID}:id/textView_result")).text

            assertEquals(newValue, textViewResultElementText)
        }
    }

    @Test
    fun `Inputting value, clicking Set and Delete should blank input and result text views`() {
        with(driver) {
            val existingValue = "Item 30"
            scrollTo(existingValue)
            val parentElement = findElement(AppiumBy.xpath("//*[@text='$existingValue']/.."))
            val editTextExampleElement =
                parentElement.findElement(By.id("${APP_ID}:id/editText_example"))
            val buttonSetElement = parentElement.findElement(By.id("${APP_ID}:id/button_set"))
            val buttonDeleteElement = parentElement.findElement(By.id("${APP_ID}:id/button_delete"))
            editTextExampleElement.click()
            editTextExampleElement.sendKeys("Bye")
            buttonSetElement.click()
            buttonDeleteElement.click()

            val editTextExampleElementText =
                parentElement.findElement(By.id("${APP_ID}:id/editText_example")).text
            assertTrue(editTextExampleElementText.isEmpty())

            val textViewResultElements =
                parentElement.findElements(By.id("${APP_ID}:id/textView_result"))
            assertTrue(textViewResultElements.isEmpty())
        }
    }

    private fun scrollTo(text: String, maxSwipes: Int = 10) {
        var foundElements: Int
        var swipes = 0
        do {
            driver.findElements(
                AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector()" +
                        ".scrollable(true))" +
                        ".scrollForward()"
                )
            )
            foundElements = driver.findElements(AppiumBy.xpath("//*[@text='$text']/..")).size
            swipes++
        } while (foundElements == 0 && swipes < maxSwipes)
    }
}
