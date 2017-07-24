#!/usr/bin/env bash
rm -rf ./test-data/ftp/admin/.camel
rm -rf ./test-data/ftp/user/.camel
rm -rf ./test-data/SimpleJmsRoute/.camel
rm -rf ./test-data/startingPoint/.camel
rm -rf ./test-data/eip/message_routing/recipient_list
rm -f ./target/FtpToFileRoute/*
rm -f ./target/MessageTranslatorUsingProcessor/*
rm -f ./target/MessageTranslatorUsingBean/*
