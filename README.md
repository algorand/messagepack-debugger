# MessagePack Debugger

Copy you base64 encoded MessagePack objects into the text fields and they will be displayed as a tree with any differences highlighted red.

# Usage

To start from the IDE run the main function in **com.algorand.msgpack.debugger.app.MyApp.kt**.

Start from the command line with maven:
```
mvn compile javafx:run
```

To run with a jar:
```
~$ java -jar target/messagepack-debugger-1.0-jar-with-dependencies.jar
```

### Arguments

The debugger will look at the first 2 arguments and use them to initialize the UI:
```
~$ java -jar target/messagepack-debugger-1.0-jar-with-dependencies.jar <object-1> <object-2>
```

For example:
```
~$ java -jar target/messagepack-debugger-1.0-jar-with-dependencies.jar gqRsc2lngqNhcmeRxAhwcmVpbWFnZaFsxJcBIAToBwEAwM8kJgMg5pqWHm8tX3rIZgeSZVK+mCNe0zNjyoiRi7nJOKkVtvkgEHZhE08h/HwCIj1Qq56zYAvD/8NxJCOh5Hux+anb9V8g/ryguxRKWk6ntDikaBrIDmyhBby2B/xWUyXJVpX2ohMxASIOMRAjEhAxBzIDEhAxCCQSEDEJKBItAykSEDEJKhIxAiUNEBEQo3R4boelY2xvc2XEIOaalh5vLV96yGYHkmVSvpgjXtMzY8qIkYu5yTipFbb5o2ZlZc0D6KJmdgGiZ2jEIBB2YRNPIfx8AiI9UKues2ALw//DcSQjoeR7sfmp2/Vfomx2ZKNzbmTEIAXpFmK85H3IBcFHiLtBPZNeppVgQscpIfaGLin5FHM5pHR5cGWjcGF5 gqRsc2lngqNhcmeRxAhwcmVpbWFnZaFsxJcBIAToBwEAwM8kJgMg5pqWHm8tX3rIZgeSZVK+mCNe0zNjyoiRi7nJOKkVtvkgEHZhE08h/HwCIj1Qq56zYAvD/8NxJCOh5Hux+anb9V8g/ryguxRKWk6ntDikaBrIDmyhBby2B/xWUyXJVpX2ohMxASIOMRAjEhAxBzIDEhAxCCQSEDEJKBItASkSEDEJKhIxAiUNEBEQo3R4boelY2xvc2XEIOaalh5vLV96yGYHkmVSvpgjXtMzY8qIkYu5yTipFbb5o2ZlZc0D6KJmdgGiZ2jEIH+DsWV/8fxTuS3BgUih1l38LUsfo9Z3KErd0gASbZBpomx2ZKNzbmTEIChyiO42rPQZmq42un3UDl1H3kZii2K4CElLvSrIU+oqpHR5cGWjcGF5
```


# Artifacts

To build a jar with bundled dependencies:
```
mvn package
```

# Screenshot
![Screenshot showing different objects loaded](/screenshots/screenshot.png?raw=true "Screenshot showing different objects loaded")
