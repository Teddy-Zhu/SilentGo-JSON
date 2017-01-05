#SilentGo JSON Parser

 the json parser for java 
 it's faster and lighter than fastjson and gson ...
 
# How to use

simple eg: (temporary usage)
```java
    // common parser
    String json = "[12,{\"axx\":[1.2,22,\"xxx\"]}]";
    JSONEntity entity = new StringParserImpl().parse(json);

```

```java
    // lazy parser
    String json = "[12,{\"axx\":[1.2,22,\"xxx\"]}]";
    JSON.config.setLazy(true);
    JSONEntity entity = new StringParserImpl().parse(json);
```

license MIT