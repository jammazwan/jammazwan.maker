# jammazwan
SCOPE!!!
    - see scope list. Covers every item on this scope list, randomly generated!!
    - If you can complete 100 jammazwan generated projects in rapid sequence, you will be a valuable asset in the Camel marketplace.
Stretch your camel skills - generate projects to complete. Randomly configured to 
    - push your skillsets
    - increase your speed, using reps
    - provide boilerplate so you can focus on value adds that matter
    - stretch beyond, with extra credit assignments
    - provide examples of completed projects to work against
    
Generates camel certification prep projects - tough projects to excercise every inch of your camel skillset

Or, if you don't want to generate your own, download 100 randomly generated projects in a workspace, from http://here

comments to integrate
- you may find a lot of stuff in here that you don't normally do but it is mentioned because it is covered in Camel In Action - for example bean by uri handle instead of reference
- practicing isolated tests - possible with naming convention PracticeTwoYada... but still requires common pom.xml so only mostly isolated
- used convention of putting large white spaces around added pom.xml dependencies to show what I added
- there are many conventions such as above that would be faster to "get" by watching the youtube
- there are many things that you would want to add to this if not limited to the test items - such as JDBC
- I found the CXF stuff to work in the test but not in real life from SOAP UI this would be a good thing to stackoverflow
- a number of routes actually require multiple routes when you try to execute the specs, for example a route that starts with jms and also consumes a file
- I find it impossible to work long periods of time without an interesting story
- list of inclusions and routes can be all jumbled.
    -- at least do everything mentioned, even if not in same route as mentioned in
    -- at least do number of routes mentioned if not more
    -- in other words, take what is prescribed and mix it up.
- there are things not provided here that you should also force yourself to do
    -- JDBC
    -- Type Converters
    -- Velocity
EXPLANATIONS OF THE SETUPS
    - running against remote docker containers and how
    - running against "setups" and FTP data sources

comments to self
- may wish to include activemq in pom? Except for darned logging conflict

glossary:
    INTEGRATION_TEST for purposes of these exercises means anything beyond just mocks. For example all of the default projects include an integration test - body text is set, and then tested to still be there after the run. 