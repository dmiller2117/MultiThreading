## Synchronized Collections
```java 
Map<String> map = Collections.synchronizedMap(new HashMap<>());
```
We can make ```Map```synchronized with the help of the ```Collections``` class.
**But this in not an efficient solution**<br>
It uses the **intrinsic lock** which means that independent operations may have to wait for each other.

###ConcurrentHashMap
We can make a map synchronized with defining sections of the underlying array.<br>
These segments(16 items) can be updated only by a single thread.<br>
We assign a lock to every segment instead of using a single lock?<br>
Every thread can read any item from the underlying array without restrictions