package com.github.fernandospr.appiumappexample.compose

import BaseAndroidTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.openqa.selenium.By

class ThreeItemsTests : BaseAndroidTest() {

    override fun getActivity() = "${APP_ID}.compose.ThreeItemsActivity"

    @Test
    fun `Inputting value and clicking Set should update result text view`() {
        with(driver) {
            val newValue = "Hello"
            val editTextExampleElement = findElements(By.id("${APP_ID}:id/editText_example"))[1]
            val buttonSetElement = findElements(By.id("${APP_ID}:id/button_set"))[1]
            editTextExampleElement.click()
            editTextExampleElement.sendKeys(newValue)
            buttonSetElement.click()

            val textViewResultElementText =
                findElements(By.id("${APP_ID}:id/textView_result"))[1].text

            assertEquals(newValue, textViewResultElementText)
        }
    }

    @Test
    fun `Inputting value, clicking Set and Delete should blank input and result text views`() {
        with(driver) {
            val editTextExampleElement = findElements(By.id("${APP_ID}:id/editText_example"))[1]
            val buttonSetElement = findElements(By.id("${APP_ID}:id/button_set"))[1]
            val buttonDeleteElement = findElements(By.id("${APP_ID}:id/button_delete"))[1]
            editTextExampleElement.click()
            editTextExampleElement.sendKeys("Bye")
            buttonSetElement.click()
            buttonDeleteElement.click()

            val editTextExampleElementText = editTextExampleElement.text
            assertTrue(editTextExampleElementText.isEmpty())

            val textViewResultElements = findElements(By.id("${APP_ID}:id/textView_result"))
            assertEquals(2, textViewResultElements.size)
        }
    }
}
