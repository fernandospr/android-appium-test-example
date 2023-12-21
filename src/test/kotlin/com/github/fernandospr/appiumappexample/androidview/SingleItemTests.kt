package com.github.fernandospr.appiumappexample.androidview

import BaseAndroidTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.openqa.selenium.By

class SingleItemTests : BaseAndroidTest() {

    override fun getActivity() = "${APP_ID}.androidview.SingleItemActivity"
    
    @Test
    fun `Inputting value and clicking Set should update result text view`() {
        with(driver) {
            val newValue = "Hello"
            val editTextExampleElement = findElement(By.id("${APP_ID}:id/editText_example"))
            val buttonSetElement = findElement(By.id("${APP_ID}:id/button_set"))
            editTextExampleElement.click()
            editTextExampleElement.sendKeys(newValue)
            buttonSetElement.click()

            val textViewResultElementText = findElement(By.id("${APP_ID}:id/textView_result")).text

            assertEquals(newValue, textViewResultElementText)
        }
    }

    @Test
    fun `Inputting value, clicking Set and Delete should blank input and result text views`() {
        with(driver) {
            val editTextExampleElement = findElement(By.id("${APP_ID}:id/editText_example"))
            val buttonSetElement = findElement(By.id("${APP_ID}:id/button_set"))
            val buttonDeleteElement = findElement(By.id("${APP_ID}:id/button_delete"))
            editTextExampleElement.click()
            editTextExampleElement.sendKeys("Bye")
            buttonSetElement.click()
            buttonDeleteElement.click()

            val editTextExampleElementText = editTextExampleElement.text
            assertEquals("Input something...", editTextExampleElementText)

            val textViewResultElementText = findElement(By.id("${APP_ID}:id/textView_result")).text
            assertTrue(textViewResultElementText.isEmpty())
        }
    }
}
