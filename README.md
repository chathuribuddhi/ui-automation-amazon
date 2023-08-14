# ui-automation-amazon
Automated test built for the Amazon implemented using Selenium + JAVA + TestNG

## Challenges / Issues which I have faced
1. Unable to add "Audible Audiobook" type of books. Since it's required to select 2nd book, the logic is added to select next book if the 2nd book is "Audible Audiobook" type of book.
2. In some cases, the site is loading without Category dropdown when the app is launched through automation script. Test is failing in this kind of situations.

## Information to run the code
1. Make sure to install Java and Maven 
2. Clone the project 
3. Install maven dependencies
4. Run the testng.xml file

### Note
1. No need to setup drivers locally since I'm using driver manager here
2. The IDE used : Intellij idea
