# Camel Demo

This project holds all kinds of Camel demo routes

All things should work :-) if you can follow instructions ...

The reason I chose spring-boot as the glue to make it all work is that spring 
makes it very easy to integrate all components needed and because of that I can 
start playing with routes almost immediately

This project works best when run in combination with docker. I've included a `docker-compose.yml`
file providing all needed 'middleware'. This enables actually testing the ftp protocol or jms, mq and stuff.
You can read more about that in the docker section.

