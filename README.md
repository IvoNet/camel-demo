# Camel Demo

This project holds all kinds of Camel demo routes
All things should work :-) if you can follow instructions ...

## Prerequisites

* docker (native) installed
* docker-compose installed
* java 8 installed
* maven 3+ installed
* a sense of adventure :-)


# docker-compose

## Start / Stop

```bash
docker-compose up
docker-compose down [-v]
```

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

