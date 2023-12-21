import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.options.UiAutomator2Options
import io.appium.java_client.service.local.AppiumDriverLocalService
import io.appium.java_client.service.local.AppiumServiceBuilder
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach

abstract class BaseAndroidTest {

    abstract fun getActivity(): String

    lateinit var driver: AndroidDriver

    @BeforeEach
    fun setUp() {
        val options = UiAutomator2Options()
            .setAppPackage(APP_ID)
            .setAppActivity(getActivity())
        driver = AndroidDriver(service.url, options)
    }

    @AfterEach
    fun tearDown() {
        driver.quit()
    }

    companion object {
        const val APP_ID = "com.github.fernandospr.appiumappexample"

        private lateinit var service: AppiumDriverLocalService

        @JvmStatic
        @BeforeAll
        fun setupService() {
            service = AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build()
            service.start()
        }

        @JvmStatic
        @AfterAll
        fun tearDownService() {
            service.stop()
        }
    }
}