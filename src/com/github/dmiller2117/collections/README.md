## Synchronized Collections
```java 
Map<String> map = Collections.synchronizedMap(new HashMap<>());
```
We can make ```Map```synchronized with the help of the ```Collections``` class.
**But this in not an efficient solution**<br>
It uses the **intrinsic lock** which means that independent operations may have to wait for each other.