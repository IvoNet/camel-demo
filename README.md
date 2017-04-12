# Camel Demo

This project holds all kinds of Camel demo routes
All things should work :-) if you can follow instructions ...

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

# docker-compose

The docker compose file in this project has been tuned to work for this demo.
So some volumes have been mounted with a specific goal.

e.g. the ftp service will actually mount volumes in `test-data/ftp`

Note that all commands should be run from the root of this project

## Start / Stop

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


# FTP User

This project has two users pre-configured:

* USER: admin / PWD: secret
* USER: user / PWD: secret

You can add your own if you want to play:

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


# ActiveMQ

* [Console](http://localhost:8161) (admin:secret)

# Mysql

* [Console](http://localhost:8888) (root:secret)

