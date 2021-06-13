#注意
**有时候下面这行代码会出现空指针异常**
```java
String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
```
原因：
* static文件路径是项目构建、编译后class存放的路径，如果static文件目录里面没有文件，那么项目编译后一般就不会生成static目录，所以导致取不到路径就出现空指针异常；