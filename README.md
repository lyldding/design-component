# design-component
组件化开发Demo，适配AndroidX，包含MVP、MVVM开发架构

![image](https://img-blog.csdnimg.cn/20191023104144859.png)

在grade.properties中修改isRunModule来切换集成和module，每次切换要重新编译

```java
isRunModule=false // 集成模式
```
以下配置  每个lib or module 都要加
  
```java

    // java 8
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }

    //开启DataBinding
    dataBinding {
        enabled isEnableDatabingding.toBoolean()
    }
   
```
