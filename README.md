Mini E-Commerce Spring Boot App

The App should have basic CRUD operations for manipulating product and cart in online store and is incomplete at the moment.
Please note that there are some unfinished parts of the app and also some features that are not working enirely correct.

Unfinished features are regarding Spring Security and securing the app, I tried to incorporate already existing example I found online due to lack of time and experience with the topic and there is an issue with Bean recognition.
More precise, in SecurityConfig class there is @Bean for PasswordEncoder but other classes that are in need of it are not being able to see it and use it. Therefore the build fails with suggestion to add that bean in order for it to work.
I cannot add it in the class using this bean beacause it causes circular bean situation that is highly discouraged. That is why I left all security related classes commented out to not break the build and run of the app.

Other problems are regarding CRUD operations relating cart manipulation. First of them, i cannot incorporate User in cart operations, beacause it somehow cannot get it from cart stating it is null, so I omitted user in order for operations to work.
Second, deleting a cart item behaves odd, the operation executes, but it only changes related tables and fails to delete the actual CartItem row. I tried changing the order of the commands, redefining on update / delete options, adding additional annotation, but it still won't work.
Trying to solve this, I failed to added remaining unit tests.

The app was done using local MySQL database.

Also, I obtained the Dockerfile within it. 
Here are basic instructions for using it:

1. Configure Docker on your system
2. you can check if everything went well by running  docker -version in your command line 
3. From Your command line access the project folder
4. run the following command : docker -build -t <desiredNameOfTheImage> .
5. Check if the image was created by running a command : docker images
6. To run the container run the command : docker run -p<portForTheAppToRunOn>:<exposedPortInDockerfile> -d <nameOfTheImage>
7. To see if everything went well and see active containers run: docker ps

LITTLE UPDATE (02. 09. 2025): I found what was wrong with deleting the cart item. I put the wrong entity relationship between Item and CartItem entities. I don't know why, but I put One-To-One instead of One-To-Many, when I noticed and changed that and also added necessary changes in my service methods, everything worked just fine. 

LITTLE UPDATE (04. 09. 2025): By adding user Id in my CartDto I managed to resolve the user issue in CartItem CRUD operations (I used to try finding userId through the field in Cart and that was failing. Now, there is a request for user Id when creating new CartItem, so I can find user through field in CartItem for upadate/delete operations) and now I can connect user with their Cart Items.
