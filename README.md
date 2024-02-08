# Popular Repositories REST API
The service is designed to
List most popular repositories, sorted by number of stars. 
You would be able to view the top 10, 50, 100 repositories. 
Repositories created from given date are returned.
Repositories can also be filtered by language.
Note: To utilize these features, simply include the desired options as request parameters when interacting with the service.

# How to use
+ With Docker

```sh
$ git clone https://github.com/Sowjanyaml/popular-repositories.git

$ docker build --tag=popularrepositories:latest . 
$ docker run -e "SPRING_PROFILES_ACTIVE=dev" -p8080:8080 popularrepositories:latest
```
+ Without Docker:
```sh
$ git clone https://github.com/Sowjanyaml/popular-repositories.git

```
* Open project with your favorite editor.
* Build and run Spring boot project

