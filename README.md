This project tests screens from the [appium-tests-android-app-example](https://github.com/fernandospr/appium-tests-android-app-example) project.

## appium-tests-example

### How to run the tests
1. Clone this repository.
2. Ensure you already installed the app from the [appium-tests-android-app-example](https://github.com/fernandospr/appium-tests-android-app-example) project in a device/emulator.
3. Execute `./gradlew test` or open the project using an IDE and run them manually.

> Note: This project launches an Appium Service, so that you don't need to run it separately.

### Project content

* Android View Folder
	* Single Item: Tests for the Single Item screen.
	* Three Items: Tests for the Three Items screen.
	* Multiple Items: Tests for the Multiple Items screen.

* Compose Folder
	* Single Item: Tests for the Single Item screen.
	* Three Items: Tests for the Three Items screen.
	* Multiple Items: Tests for the Multiple Items screen.

For each of the above, you'll find these tests:
* Inputting value and clicking Set should update result text view
* Inputting value, clicking Set and Delete should blank input and result text views

### Repeated IDs
Checking the code related to Three Items, you'll notice you can find all the elements with the same ID, resulting in a list of elements, and then just select one of them using an index.

Multiple Items have a hundred elements and these are rendered using `RecyclerView (Android View)` / `Lazy Column (Compose)`, therefore you won't be able to get all the elements like the previous example. 
For this case, you'll notice it's possible to scroll to an element matching a text, get its parent element and then find the child element with the desired ID.
