# tic-tac-toe responses to questions

view screencast of tests here: 
<https://www.loom.com/share/b99bab98cd6846898adf522781dfe730>

1.  In talking with the developers on selecting the most appropriate automation framework the things for consideration are a tool that will best deal with the following:  <br>

    *  A tool that is flexible that allows it to be able to work with other automation frameworks.
        -  For example, a BDD framework will better help to drive the conversation around an applications requirement and hence identify and translate these requirements into test step maintenance.  
    *  A tool that best manage typically pain points in automation such as dynamic waiting for elements to be ready, cross browser flakiness, ability to run tests in parallel, test data management
    *  a tool that is easy for QAs to adapt and learn from.  
    *  When tests fail it is important that the tool is helpful identifying the cause for the failure
    *  A tool that focuses on reporting of tests results in a helpful fashion especially on failures
    *  A tool that easily plugs into to the CI/CD pipeline.  
<br>
2.	In order to mitigate the flakiness that is occurring in the current application of tic-tac-toe, the team work together in flushing out the expectations and requirements ahead of time such that whole team is aware of how the application should behave.  With this in place, the tester can begin to implement test cases around these requirements.  As it currently stands, it is difficult to know how to test this application as there some major flaws that make it difficult to come up with the right level of tests cases for it.  For example, the tic-tac-toe board size should be a requirement as with this in place, appropriate tests can be identified and implemented.<br>  

3.	In order to help with CI completing in a reasonable fashion, tests can be run in parallel.  Additionally, running them in the cloud will also free-up resources that may be needed with the build process. With this concept in mind, it is important that each test is not dependent on one another and do not share test data.  Additionally, multiple data scenarios can be written to run in parallel.<br>  

4.	The testing pyramid is a guideline that each test type should focus on a different level of granularity.  The more high-level you get, the fewer tests there should be.  There should be more unit tests than service tests (integration) and more service tests than UI tests.  Unit test are closest to the logic and are function based.  They can be run in isolation and are much faster to run (and implement) than UI tests.  If needed, the developer can work with the QA to identify the unit tests to implement.  UI tests should only be implemented with the purpose in mind that the UI itself is being tested.  Identifying UI flows from the end user should be considered.  If you have a webservice that has multiple request types, it is better to test all the different permutations at the service level as opposed to the UI level.  Focus on at what you are trying to test at each level and write your automated tests as close to that layer as possible.  With this in mind, you will get faster feedback and know exactly where the test failure is occurring.  For example, if you have UI tests that are trying to validate something at the service level and the test fails, you have to spend the time trying to figure out where the failure occurred.  Was the failure at the UI level or was it indeed at the service level?<br>  

5.	To ensure buy-in, the main thing is communication and team agreements.  Communicating during stand-ups, during retrospectives, during reviews and during the typical workday.  Coming up with and agreeing on a definition of done is also helpful.   Also having exit criteria for when a user story can move from in development to ready for test (for example).  With these things in place expectations are understood by everyone.