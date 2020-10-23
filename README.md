# Leagues Of Football (LOF)

Will present all teams from an user selected football league

## TODO  

* Inject `context.getResources()` to use it like `res.getString(R.string.awesome_sentence))` in presenters
* Clearable AutoCompleteTextView
* More abstraction
* Add league part tests (no more TDD at the end...)  

## BONUS  

* It's possible to change League Of Football into ... something else...  
  `const val SPORT_TYPE = "Soccer"` in TeamsActivity could be changed easily to see teams from an another sport  
  (i.e. Golf because why not)  
* Handle lack of information from WS like team banner image => will hide it  
