FROM ubuntu:20.04

RUN export DEBIAN_FRONTEND=noninteractive && apt update && \
 apt install -yq curl \
    wget \
    vim \
    git \
    less \
    jq \
    openjdk-11-jdk
COPY . ./ktest
CMD ["/bin/bash"]

# docker build -t . ./ktest
#docker run -it ktest
#docker cp containerName:path/to/file path/to/host