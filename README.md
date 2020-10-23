# Leagues Of Football (LOF)

Will present all teams from an user selected football league

There are 2 modules in the app.  

- First one is a ***non android dependent*** module and is named ***core***  
  It contains ***data*** as models from domain layer  
  Also ***usecases*** as independent action from a feature  
  And abstract definition of all ***datasources***  
  
 - Second one is an android dependant module and is refered as ***app***   
   It contains concrete implementation for the data layer (datasources)  
   Also android framework dependent implementations  
   And ui interaction and display as ***presentation***  
   Dependency injection is also set up in this module  
   
Presenters will act as middle-man between ****model**** and ***view***  

## TODO  

* Inject `context.getResources()` to use it like `res.getString(R.string.awesome_sentence))` in presenters
* Clearable AutoCompleteTextView
* More abstraction
* Add league part tests (no more TDD at the end...)  
* Add a freaking logo for the app  

## BONUS  

* It's possible to change League Of Football into ... something else...  
  `const val SPORT_TYPE = "Soccer"` in TeamsActivity could be changed easily to see teams from an another sport  
  (i.e. Golf because why not)  
* Handle lack of information from WS like team banner image => will hide it  
* Main colors freely inspired from "Parions Sport" app  

## CHOICES OR ISSUES  

To be discussed
