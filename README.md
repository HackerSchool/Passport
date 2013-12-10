Passport
========

Setting up the work environment
-------------------------------

1. Install the Play 2.X Framework and make sure it is in your path
2. Make sure you have access to the repository
3. Clone the repository https://github.com/HackerSchool-IST/Passport.git
4. In the console go to the directory where you cloned the repository and do "play eclipse" and "play dependencies"
5. Open eclipse and import the project
6. Install Scala in eclipse update site = http://download.scala-ide.org/sdk/e38/scala210/stable/site, select "Scala IDE for Eclipse" and at least "Play2 support in Scala IDE" under the "Scala IDE Plugins".
7. Still in the console (in the same directory) do "play run"
8. Make sure you have MySQL running with a database named "passport" and a user with the same name with permissions on that databse, and with password "a really humungusly ginormous password for the passport mysql db"
9. You play project is now accessible in http://localhost:9000/
10. Whenever you edit the code the framework will recompile the necessary files, and will show any errors the code might have in the browser
