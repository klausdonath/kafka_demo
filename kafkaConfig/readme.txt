######### KAFKA BASIC ###########
Topics > Stream of data. Wie eine DB Tabelle. So viele Topics wie man will.
Topics are split into partitions - 0,1,2 ... jede partition hat eine order. Jede message da drin eine
    incremental ID (offset)

Beispiel: Wir haben eine Spedition. Die LKW's wollen GPS Position reporten. Also haben wir ein
Topic trucks_gps. Alle 10.000 Trucks senden Ihre Position mit Ihrer ID und Position da rein.

Data is kept for a limited amount of time (default 1 week)
immutability > Messages nicht veränderbar. Misst geschrieben, PECH!

Anzahl Partitionen muss am Anfang festgelegt werden (kann nachher verändert werden)

Daten gehen in eine random partition außer ein key wird übergeben

########## BROKERS #############

Wir haben über TOPICS geredet. Aber wo sind die Topics drin? In Brokers!
Ein Cluster sind verschiedene Broker. Jeder Broker ein Server
Jeder broker kann bestimmte Topic Partitionen enthalten, aber nicht alle
Wenn man sich mit einem Broker connected, connected man sich mit dem ganzen Cluster aus Brokern.

> 3 Broker guter Start
> 101, 102, 103
> ONE NOTE


