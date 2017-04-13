# Camel Demo

This project holds all kinds of Camel demo routes

All things should work :-) if you can follow instructions ...

The reason I chose spring-boot as the glue to make it all work is that spring 
makes it very easy to integrate all components needed and because of that I can 
start playing with routes almost immediately

This project works best when run in combination with docker. I've included a `docker-compose.yml`
file providing all needed 'middleware'. This enables actually testing the ftp protocol or jms, mq and stuff.
You can read more about that in the docker section.

## Prerequisites

* docker (native) installed
* docker-compose installed
* java 8 installed
* maven 3+ installed
* a sense of adventure :-)

# run the demo

In order to be able to run the demo you first need to start
the docker containers from the docker-compose file.

Then you can run application by starting CamelDemoApplication from the IDE
or with maven:

```bash
docker-compose up -d
mvn clean package spring-boot:run
```

## What will happen

* a HelloWorld file is located here: `test-data/SimpleFileCopyRoute/HelloWorld.txt`
* this file will be copied by the SimpleFileCopyRoute to the `test-data/ftp/admin` folder
* the FtpToFtoRoute will see this because that is the volume "mounted" by the 
ftp docker container for the admin user. 
* The FtpToFtoRoute will copy the file to the ftp `user` account 
* it will also move the file in the `admin` account to a .camel folder
* The FtpToFileRoute will see the file in the ftp `user` account and copy it to `target/FtpToFileRoute`
* it will also move the file in the `user` account to .camel in its home folder
* the log will also print out something like:

```text
2017-04-13 00:32:05.277  INFO 9736 --- [eFileCopyRoute/] SimpleFileCopyRoute                      : Found file [HelloWorld.txt] and copying it to: /Users/ivonet/dev/camel-demo/test-data/ftp/admin/
2017-04-13 00:32:05.341  INFO 9736 --- [localhost:11021] FtpToFtpRoute                            : Found file [HelloWorld.txt] and cp-ing it to the ftp user: user
2017-04-13 00:32:05.844  INFO 9736 --- [localhost:11021] FtpToFileRoute                           : Found file [HelloWorld.txt] and cp-ing it to: /Users/ivonet/dev/camel-demo/target/FtpToFileRoute
```

# docker

The `docker-compose.yml` file in this project has been tuned to work for this camel-demo project.
So some volumes have been mounted with a specific goal.

## Start / Stop docker-compose

Start:

```bash
docker-compose up [-d]
```

the -d option will start in daemon mode

Stop:

```bash
docker-compose down [-v]
```

the -v option will also remove the volumes created


# FTP docker image

In order to demo the ftp routes in this project an ftp server has been configured in the docker-compose file.
This ftp server mounts 'native local' folders in the `test-data/ftp` folder but it will also actually enable 
the ftp protocol. So the FtpTo*Routes really talk ftp in the demo.

You will be able to see what happens on your native file system though. Please look at the ftp config in the 
docker-compose file to see how that works.

## FTP User

This project has two users pre-configured:

* USER: admin / PWD: secret
* USER: user / PWD: secret

Their home folders reside in `test-data/ftp` and the users have been saved in `docker/ftp/passwd/pureftpd.passwd` file as described below.

### FTP Create your own user

You can add your own user(s) if you want to play:

First you need to start the ivonet/ftp docker container:

```bash
docker run --rm --name ftp \
   -v $(pwd)/docker/ftp/passwd:/etc/pure-ftpd/passwd \
   -v $(pwd)/target/ftpusers:/home/ftpusers/ \
   -p 11021:21 \
   -p 30000-30009:30000-30009 \
   ivonet/ftp
```

Then you need to enter the running image:

```bash
docker exec -it ftp /bin/bash
pure-pw useradd USERHERE -f /etc/pure-ftpd/passwd/pureftpd.passwd -m -u ftpuser -d /home/ftpusers/USERHERE
```

Exit the image with logout and stop the image with ctrl-c or

```bash
docker stop ftp
```

## ActiveMQ docker image

For this demo ActiveMQ has been chosen as the JMS provider and Message Queue middleware.

* [Console](http://localhost:8161) (admin:secret)
* read more about this image [here](https://hub.docker.com/r/ivonet/activemq/)

## Mysql docker image

To make looking at the results of what happens to to de db `phpmyadmin` has also been included.

* [Console](http://localhost:8888) (root:secret)
* read more about this image [here](https://hub.docker.com/r/ivonet/mysql/)
* this images looks in `docker/mysql/setup` for initializing sql scripts when the first `docker-compose up` is done

