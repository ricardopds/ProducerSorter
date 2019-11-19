# ProducerSorter
Web service RESTful that returns, from a previous csv file, the producer that received two awards in the minimum amount of time and the one that received two awards in the maximum amount of time.


(1) Run the Web Service Application

1.1 - Add the movie project to your workspace.<br>
1.2 - Click with the right button on your project and go to: Run As->Run on Server.<br>
1.3 - Selects the server type, for this example I will be using the TomCat v9.0 Server.<br>
1.3.1 - Define the server's host name, the default is 'localhost'.<br>
1.3.2 - Define the server's name, the default is 'Tomcat v9.0 Server at localhost'.<br>
1.3.3 - Click on 'Next'.<br>
1.3.4 - In 'Tomcat installation directory' browse to the Tomcat server files.<br>
1.3.5 - Click on 'Next'.<br>
1.3.6 - Add the project to the 'Configured' selection for the server resources.<br>
1.3.7 - Click on 'Finish'.<br>
1.3.8 - Open the browser on the following URL: http://localhost:8080/movie/producers. This URL will list the producer with the minimum interval between two awards winning and the one with the maximum interval. You will receive the following JSON:<br>
'{
    "min": [
        {
            "followingWin": 1991,
            "interval": 1,
            "previousWin": 1990,
            "producer": "Joel Silver"
        }
    ],
    "max": [
        {
            "followingWin": 2015,
            "interval": 13,
            "previousWin": 2002,
            "producer": "Matthew Vaughn"
        }
    ]
'}<br>
1.3.9 - Use the http verbes to manage the producers on that list:<br>
  -GET, movie/producers: List the producer with the minimum interval between two awards winning and the one with the maximum interval.<br>
  -GET, movie/producers/{name}: Returns the producers data.<br>
  -POST, producers: Create the producer sent through service. The producer is formed by:<br>
    "followingWin", Long<br>
    "previousWin", Long<br>
    "interval", Long,<br>
    "producer", String<br>
    "multipleWins", boolean<br>
  -PUT, movie/producers/{name}: Changes the producer listed on {name} with the given by the PUT verbe.<br>
  -DELETE, movie/producers/{name}: Delete the producer listed on {name}.<br>
<br>
(2) Run integration tests<br>
2.1 - Configure the JUnit in the framework<br>
2.2 - Click with the right button on your project and go to: Run As->JUnit Test.<br>
2.3 - The test will run and show the status for the following methods (in this order): listProducerWithMinimumAndMaximumTwoPrizesInterval, createProducer, getProducer, updateProducer, deleteProducer. This order is required so to test all CRUD.<br>
