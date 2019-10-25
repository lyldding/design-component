# design-component
## 1 组件化开发Demo，适配AndroidX，包含MVP、MVVM开发架构

[AndroidX设计架构MVVM之DataBinding+ViewModel+LiveData：包含各组件原理](https://blog.csdn.net/lylddingHFFW/article/details/102657746)

[AndroidX组件化搭建MVP、MVVM以及混合使用kotlin模块](https://blog.csdn.net/lylddingHFFW/article/details/102719721)

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
## 2新增moduleKotlin

实现组件化中java module和kotlin module 混用，主要就是一些grade配置的变化

