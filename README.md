# FortStats - Code Base
This code is the base of an app called FortStats, a stats tracker for the Fortnite game by Epic Games.


<img src="http://i1320.photobucket.com/albums/u540/ROFactum/ad587c96-5b6f-429f-8b39-33dfecf06abb_zpscxuppxup.jpg" width="300" height="533">                      <img src="http://i1320.photobucket.com/albums/u540/ROFactum/afecfac8-31bc-4bc5-a184-d965fad1b37e_zpsc8nxnrhs.jpg" width="300" height="533">


The app uses MVP architecture to organize the code and to allow better testability, Dagger2 to dependency injection and Retrofit to make calls to the API used to get user stats.

## How to make it works?
Firstly, after oppening the code in your Android Studio you will have to create a class called ConfigUtils and add two static fields:
+ APPLICATION_ID
+ API_URL

In the API_URL variable you will have to put the URL of the API you will be using to make requests to get user info, at this case I used the [FortniteTracker](https://fortnitetracker.com) (more about it in the API dependencies section).

To fill the second variable (APPLICATION_ID) you will have to create an account at the FortniteTracker website and get a TRN-Api-Key.
This key will be at the following link after you create an account: [FortniteTracker API KEY](https://fortnitetracker.com/site-api/)

After getting the API key you will have to put it in the APPLICATION_ID variable to make the app able to get info from the [FortniteTracker](https://fortnitetracker.com).

Finnaly, with all the ConfigUtils class configured you will just have to run the application and put an username to get some stats about the player.

PS: This version of the code only works for PC players, to allow search for player from other platforms you can change the RetrofitInterface, a simple way to do that would be changing the @Get annotation from "pc/{user}" to "yourplatform/{user}".

PS: This version of the code only works for PC players, to allow search for player from other platforms you can change the RetrofitInterface, a simple way to do that would be changing

```Java
@GET("pc/{user}")  
Call<Player> getPlayerStats(@Path("user") String user);
```
to 

```Java
@GET("yourPlatform/{user}")  
Call<Player> getPlayerStats(@Path("user") String user);
```
 
 The available platforms by the API are: *pc*, *psn* and *xb1*.

## API Dependencies
The API used to get info from users is the [FortniteTracker](https://fortnitetracker.com).

## Right of use
You are free to use this base code to build your own app. If your app has an open source code, please refer this page.

## Copyrights
All rights of the *Fortnite* game are reserved to __Epic Games__, this app does not have any relation with the company.

All API rights are reserved to __FortniteTracker__, this app does not have any relation with the API creators.
