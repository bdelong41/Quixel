# Quixel
Quixel is an inventory management system that helps catalogue and track products, and associated parts. The program allows users to create/modify products and parts as well as configure product-part relationships. Product and part data is stored in a MySQL database which can be imported into a MySQL dbms. Users can configure the database connection through the application. Quixel runs on Windows, MaxOS, and Linux, with minor to no support for mobile devices.

## Setup Guide
Quixel is built using Java and JavaFX using the Maven environment. Most prerequisites are provided through the maven repositories included in the build package. One requirement is the JDBC 8.0.23 connector (Jar file) which can be downloaded from the [MYSQL official site](https://downloads.mysql.com/archives/c-j/). To build the program you must have [Intellij IDEA 2023](https://www.jetbrains.com/idea/) installed.
  1. Download the repository
  2. Extract the repo
  3. Open the repo using Intellij IDEA 2023
  4. Import the downloaded JDBC connector file into the project structure.
  5. Configure the included database
  6. Run the program
  7. Configure the database connection through the Database connection tool within the program

After that you're all set. Enjoy!
