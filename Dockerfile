FROM ubuntu:14.04

MAINTAINER Blaz Solar "blaz.solar@dlabs.si"

RUN apt-get update
RUN echo "Y" | sudo apt-get install kvm

RUN kvm-ok
