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
* a good OS like linux or macOs :-)


# Copyright

Copyright 2017 Ivo Woltring <WebMaster@ivonet.nl>

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

# Wishes

* EIP's 
    √ MessageTranslator
* Error handling
√ FileToMqJms
√ XPath in path
* MqWithContract
* PubSub (topic)
    * persisted read/write
    * non persisted read/write
* Database as monitored (input) source
* Database as target    
* Scheduled polling consumer 
* Quartz scheduling (quartz2://report?cron=0+0+6+*+*+? or sumesuch) 
√ transformation during route
* add routes to routes
* UnitTesting of routes
√ usage of direct:...
* usage of mock
* Type conversions
* Processors with exchange type manipulation
* Jaxb2 marshalling / unmarshalling
* Json 
* GZip / Zip file
√ csv
* XPath
* Avro format
* Custom TypeConverter

