#SilentGo JSON Parser

 the json parser for java 
 it's faster and lighter than fastjson and gson ...
 
# How to use



# Usage
simple eg:
```java
    // common parser
    String json = "[12,{\"axx\":[1.2,22,\"xxx\"]}]";
    JSONEntity entity = JSON.parse(json)

```

```java
    // lazy parser
    String json = "[12,{\"axx\":[1.2,22,\"xxx\"]}]";
    JSONEntity entity = JSON.parse(json,new JSONConfig(true));
```

```java
    //parse complex object
    String jsonStirng = "{.....}";
    T object  = JSON.parse(jsonString, Class<T> tClass);
```

license MIT