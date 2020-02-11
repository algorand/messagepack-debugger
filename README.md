# MessagePack Debugger

View and compare messagepack encoded objects. Put the file path or base64 encoded MessagePack objects into the text fields and they will be displayed as a tree with any differences highlighted red.

# Usage
Run with **java -jar**:
```
~$ java -jar messagepack-debugger.jar
```

### Arguments

The first 2 arguments will be used to initialize the UI during launch, you can provide any combination of file paths and base64 encoded objects:
```
~$ java -jar messagepack-debugger.jar <object-1> <object-2>
```

Here is an example launching the UI with two encoded objects:
```
~$ java -jar messagepack-debugger.jar \
          gqRsc2lngqNhcmeRxAhwcmVpbWFnZaFsxJcBIAToBwEAwM8kJgMg5pqWHm8tX3rIZgeSZVK+mCNe0zNjyoiRi7nJOKkVtvkgEHZhE08h/HwCIj1Qq56zYAvD/8NxJCOh5Hux+anb9V8g/ryguxRKWk6ntDikaBrIDmyhBby2B/xWUyXJVpX2ohMxASIOMRAjEhAxBzIDEhAxCCQSEDEJKBItAykSEDEJKhIxAiUNEBEQo3R4boelY2xvc2XEIOaalh5vLV96yGYHkmVSvpgjXtMzY8qIkYu5yTipFbb5o2ZlZc0D6KJmdgGiZ2jEIBB2YRNPIfx8AiI9UKues2ALw//DcSQjoeR7sfmp2/Vfomx2ZKNzbmTEIAXpFmK85H3IBcFHiLtBPZNeppVgQscpIfaGLin5FHM5pHR5cGWjcGF5 \
          gqRsc2lngqNhcmeRxAhwcmVpbWFnZaFsxJcBIAToBwEAwM8kJgMg5pqWHm8tX3rIZgeSZVK+mCNe0zNjyoiRi7nJOKkVtvkgEHZhE08h/HwCIj1Qq56zYAvD/8NxJCOh5Hux+anb9V8g/ryguxRKWk6ntDikaBrIDmyhBby2B/xWUyXJVpX2ohMxASIOMRAjEhAxBzIDEhAxCCQSEDEJKBItASkSEDEJKhIxAiUNEBEQo3R4boelY2xvc2XEIOaalh5vLV96yGYHkmVSvpgjXtMzY8qIkYu5yTipFbb5o2ZlZc0D6KJmdgGiZ2jEIH+DsWV/8fxTuS3BgUih1l38LUsfo9Z3KErd0gASbZBpomx2ZKNzbmTEIChyiO42rPQZmq42un3UDl1H3kZii2K4CElLvSrIU+oqpHR5cGWjcGF5
```

You can also launch with a transaction files:
```
~$ java -jar messagepack-debugger /home/will/algorand/java-algorand-sdk/expected
```

Or any combination of the two:
```
java -jar target/messagepack-debugger.jar \
       /home/will/algorand/messagepack-debugger/expected \
       gqRsc2lngaFsxLcBIAoAAcCWsQICkE4EuWBkHsDEByYBIP68oLsUSlpOp7Q4pGgayA5soQW8tgf8VlMlyVaV9qITMRYiEjEQIxIQMQEkDhAyBCMSQABVMgQlEjEIIQQNEDEJMgMSEDMBECEFEhAzAREhBhIQMwEUKBIQMwETMgMSEDMBEiEHHTUCNQExCCEIHTUENQM0ATQDDUAAJDQBNAMSNAI0BA8QQAAWADEJKBIxAiEJDRAxBzIDEhAxCCISEBCjdHhuiaNhbXTNJxCjZmVlzQmKomZ2zQfQomdoxCB/g7Flf/H8U7ktwYFIodZd/C1LH6PWdyhK3dIAEm2QaaNncnDEIDLoxg2J+e7ixq7CMmuLHIDdr+YAIdIgdnb95185yupUomx2zQu4o3JjdsQgzUWoS2tM1RvtuQQyGhwdXUqCByLFEPBRXHZwnSqBvgajc25kxCBd4Wnq60VaWXeVmuPt0x1XgMQSyP4nUnlh5G2Rhyo+taR0eXBlo3BheYKjc2lnxEAnRhPWfzTensUjfRxaJ6mFOiJQYVoIpsSjI0koev2BnomM8EXLatV+wZotqG44I+q4Y3GX4kShGPXpTjbL3ugLo3R4boqkYWFtdM0LuKRhcmN2xCD+vKC7FEpaTqe0OKRoGsgObKEFvLYH/FZTJclWlfaiE6NmZWXNCg6iZnbNB9CiZ2jEIH+DsWV/8fxTuS3BgUih1l38LUsfo9Z3KErd0gASbZBpo2dycMQgMujGDYn57uLGrsIya4scgN2v5gAh0iB2dv3nXznK6lSibHbNC7ijc25kxCDNRahLa0zVG+25BDIaHB1dSoIHIsUQ8FFcdnCdKoG+BqR0eXBlpWF4ZmVypHhhaWTNMDk=
```

# Screenshot
![Screenshot showing a single transaction file being inspected](/screenshots/load_file.png?raw=true "Screenshot showing a single transaction file being inspected")
![Screenshot showing different objects loaded](/screenshots/file_and_base64_string.png?raw=true "Screenshot showing different objects loaded")
![Screenshot showing transaction groups](/screenshots/compare_transaction_group_files.png?raw=true "Screenshot showing transaction groups")


# Development
To start from the IDE run the main function in **com.algorand.msgpack.debugger.app.MyApp.kt**. You may need to configure your IDE to run the maven install target.

Start from the command line with maven:
```
mvn compile javafx:run
```

# Artifacts

To build a jar with bundled dependencies:
```
mvn package
```

