# NYPD Complaint Application

> Start project:  _mvn spring-boot:run_

I did a web application that exposes the next REST endpoints on a 1000 records file stored in MongoDB

The endpoints are:

- **GET** `/dataset/stats/total` -> total number of events
- **GET** `/dataset/stats/offenses` -> total number of events grouped by KY_CD column
- **DELETE** `/dataset/${id}` -> deletes the record with the specified id 
- **POST** `/dataset` -> inserts a new document with CMPLNT_NUM, KY_CD fields





