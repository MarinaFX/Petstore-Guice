# <samp> Guice's Petstore <img src="https://raw.githubusercontent.com/MarinaFX/MarinaFX/master/gifs/oie_2215623KLveOSaL.gif" width="100px"/></samp>

## This is a simple project of a petstore using Google's framework of IoC Guice. Furthermore, it is going to be deployed on a Docker container using a Jetty server. 

## The Dockerfile
As mentioned earlier, this project will be deployed using docker, therefore I made a Dockerfile to optimize the process of pulling images and deploying the project. This is one of my first projects dealing with docker, humour me if you find any mistakes/bad practices within it!  
Here follows the Dockerfile: 

```
FROM jetty:9

LABEL maintainer="github.com/MarinaFX"

COPY ./build/libs/Tema6.war ./webapps/

EXPOSE 8080
```
So, basically the Dockerfile is a much faster way to get your container up and running, thus the first line we are pulling the official image of a Jetty server, specifically version 9. 

Then, I am only labeling with my github address (this line is purely optional). 

The third line, tells docker the following: "hey docker, in order to run my application I need you to COPY the file `Tema6.war` located in this project at the folder `/build/libs/` TO the folder `/webapps` within the Jetty folder I just downloaded inside you". 

Finally, we are exposing the port 8080 of this container in order. I will explain about the exposure of the port in bit. So far, pretty basic right? 

## Wrapping the application into a docker container
Without further due, here follows the step by step to replicate this project.

1. Clone the project locally.
2. Make sure you have docker installed. If not, these links may be useful: 
    1. [Windows](https://docs.docker.com/docker-for-windows/install/) (not really recommended)
    2. [Linux](https://docs.docker.com/engine/install/)
    3. [MacOS](https://docs.docker.com/docker-for-mac/install/)
3. Enter the project folder you just cloned. To make sure you are in the right folder, type `ls -la or ll -lha` in your terminal. The out put should be something similar to: 

    ```
    marina-marina78421:~/.../ll -lha
    total 64K
    drwxrwxr-x 8 marina marina 4,0K out 14 20:48 ./
    drwxr-xr-x 4 marina marina 4,0K out 14 18:48 ../
    drwxrwxr-x 6 marina marina 4,0K out 14 20:00 build/
    -rw-rw-r-- 1 marina marina  591 out 14 18:49 build.gradle
    -rw-rw-r-- 1 marina marina  161 out 14 20:22 Dockerfile
    drwxrwxr-x 8 marina marina 4,0K out 14 20:38 .git/
    -rw-rw-r-- 1 marina marina   61 out 14 18:49 .gitignore
    drwxrwxr-x 5 marina marina 4,0K out 14 19:39 .gradle/
    drwxrwxr-x 3 marina marina 4,0K out 14 18:49 gradle/
    -rw-rw-r-- 1 marina marina 5,2K out 14 18:49 gradlew
    -rw-rw-r-- 1 marina marina 2,2K out 14 18:49 gradlew.bat
    drwxrwxr-x 4 marina marina 4,0K out 14 20:48 .idea/
    -rw-rw-r-- 1 marina marina 2,6K out 14 20:48 README.md
    -rw-rw-r-- 1 marina marina   34 out 14 18:49 settings.gradle
    drwxrwxr-x 3 marina marina 4,0K out 14 18:49 src/
    ``` 
4. To build the image of the container run in the terminal: `docker build . -t petstore:latest`
    - Alright, so remember about that Dockerfile I mentioned above? Here, were are telling docker to `build` that docker file (execute it) within our current directory, that's why there a dot right in the middle, that dot is a representation of the current dir. Then, by writing `-t` we are tagging this image as 'petstore:latest'. petstore is an example name, whilst latest refers about the version of this image. 
    
5. After building the image, execute the container: `docker run -d -p 8080:8080 petstore`
    - Okay, so remember when we exposed the port 8080 in the Dockerfile? Here's why: this command, basically tell the docker to run our image, to turn this image into a container. The `-d or --detach` param tells docker to run this container in the background, that's why your terminal is free to use after you ran this command. Now here is the cat's jump, when we tell docker `-p or --publish` this means we are opening the following ports to be accessed. "Wait, but didn't you just opened? when you wrote EXPOSE 8080 in the dockerfile?" Kind of... Here is the deal: 
        - EXPOSE xxxx: this tells docker to open this port INTERNALLY, inside Docker. This means only other containers will be able to access that port. 
        - EXPOSE xxxx + -p xxxx: when both are used, this tells docker: "hey docker, not only I want you to open this port for only other containers, but also I want you to PUBLISH (--publish) this port wide open to the world"
        - Neither EXPOSE xxx nor -p: this means this container will not be accessed neither internally nor externally. 
        
    - "Okay, but you wrote two times: -p 8080:8080. You are double opening the door?" Could be right? but not, actually when we write 8080:8080 this means we want to map the CONTAINER port 8080 to OUR MACHINE port 8080. In other words, no one could access our container in 8080 because its local right? So we map its 8080 port to ours 8080 :D 
    
6. Test it [here](http://localhost:8080/Tema6)

The image of the container can also be found by clicking [here](https://hub.docker.com/r/marifx/petstore-jetty)

To kill the container, simply insert in the terminal: `docker ps`. The output will be similar to: 

```
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                            NAMES
99f44f670516        petstore            "/docker-entrypoint.…"   2 minutes ago       Up 2 minutes        80/tcp, 0.0.0.0:8080->8080/tcp   dazzling_cray
```

Then copy the Container ID with CTRL+Insert. Finally, insert in the terminal `docker kill {container ID}`

Thanks for trying out :D 🤘🤘
