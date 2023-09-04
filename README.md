# DiceAssessmentTask

This is the springboot server to serve & provide the weather details <br>
This primarily focuses on the requirements provided by Dice.Tech 
### Requirements -
Authentication Method: Header-based authentication with random client id and random client secret. <br>
API 1: Get the Weather forecast summary of Any city. <br>
API 2: Get hourly Weather forecast details of Any city. <br>

### Obstacles - 
The twitter API in part 1 & the Forecast api in part 2 didn't include the APIs required in their free/OpenSource plans
& hence I went forward with <b>another free solution </b> for weather api - [ Open Weather ](https://openweathermap.org/api)
This weather api provided <br>
1. Current Weather
2. 3-hour Forecast for 5 days <br>
endpoints for free & hence have integrated that with this springboot application
<br> <br>

The backend server for this is hosted at - https://separate-tramp-production.up.railway.app <br>
This website is currently hosted at - https://vagishyagnik.github.io/DiseAssessmentTaskClient <br>
[Repository](https://github.com/vagishyagnik/DiseAssessmentTaskClient)

The [postman link](https://drive.google.com/file/d/15WgcqLVjLp9YzNuzmLzi6ct-FMy_lLKM/view?usp=sharing) for testing this backend server <br>
APIs - 
```
https://separate-tramp-production.up.railway.app/login
https://separate-tramp-production.up.railway.app/signup
https://separate-tramp-production.up.railway.app/hourly-forecast
https://separate-tramp-production.up.railway.app/forecast-summary
https://separate-tramp-production.up.railway.app/unique-client-names
```

### How header auth works
Some APIs here required the header authentication & some didn't so those were divided into 2 different auth controllers, <br>
1. Auth Controller (includes <b>login, signup, unique-client-names</b> endpoints)
2. Weather Controller (includes <b>hourly-forecast, forecast-summary</b> endpoints)
Conrollers not requiring HeaderAuthentication were marked with </b>@ExcludeHeaderInterceptor</b> annotation <br>
Now the endpoints at weather controller surely needed a <b>header based</b> authentication so a whole system of <b>client credentials containing clientId(userId) & clientSecret/password (ofcourse encrypted) was implemented</b>. A Header interceptor was introduced which would check all requests <b>leaving out the request coming from classes annotated with ExcludeHeaderInterceptor & preflight requests (cors from browser)</b>. <br>
<b>Now the logic inside HeaderInterceptor was simple just verify from the database that the clientId & clientSecret attached to the header are valid or not.</b>
