1: mvn jib:dockerBuild
2: docker run --name spring-boot-multi-module --rm -p 8080:8080 spring-boot-multi-module 
3: open browser and then visit the following url:
	http://localhost:8080/
	http://localhost:8080/classpath
	http://localhost:8080/api-component
4：比较懒，改了下别人的代码 https://github.com/australianfrog/spring-boot-multi-module 如果有侵权，立即删除
