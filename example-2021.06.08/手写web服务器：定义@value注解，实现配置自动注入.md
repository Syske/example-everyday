# 手写web服务器：定义@value注解，实现配置自动注入

### 前言

昨天我们定义了`Configuration`注解和`Bean`注解，实现了更灵活的类注入，今天我们来看另一个配置注入的注解`Value`，这个注解也是我们在`springboot`中经常用到的，今天我们就来看下如何通过`value`注解实现`properties`配置的自动注入。

### 实现过程

#### 定义properties工具类

这个工具类的作用主要是解析我们的配置文件，并生成一个配置文件的字典数据，然后我们可以根据自己的需要获取对应的配置，这也是我们实现配置自动注入的第一步。

```java
public class PropertiesUtil {
    private static HashMap<String, PropertiesUtil> configMap = new HashMap();
    private Date loadTime = null;
    private ResourceBundle resourceBundle = null;
    private static final Integer TIME_OUT = 60000;

    private PropertiesUtil(String name) {
        this.loadTime = new Date();

        try {
            this.resourceBundle = ResourceBundle.getBundle(name);
        } catch (Exception var3) {
            this.resourceBundle = null;
        }

    }

    public static synchronized PropertiesUtil getInstance() {
        return getInstance("application");
    }

    public static synchronized PropertiesUtil getInstance(String name) {
        PropertiesUtil conf = configMap.get(name);
        if (null == conf) {
            conf = new PropertiesUtil(name);
            configMap.put(name, conf);
        }

        if ((new Date()).getTime() - conf.getLoadTime().getTime() > (long)TIME_OUT) {
            conf = new PropertiesUtil(name);
            configMap.put(name, conf);
        }

        return conf;
    }

    public String get(String key) {
        try {
            String value = this.resourceBundle.getString(key);
            return value;
        } catch (MissingResourceException var3) {
            return "";
        } catch (NullPointerException var4) {
            return "";
        }
    }

    public Integer getInt(String key) {
        try {
            String value = this.resourceBundle.getString(key);
            return Integer.parseInt(value);
        } catch (MissingResourceException var3) {
            return null;
        } catch (NullPointerException var4) {
            return null;
        }
    }

    public boolean getBoolean(String key) {
        try {
            String value = this.resourceBundle.getString(key);
            return "true".equals(value);
        } catch (MissingResourceException var3) {
            return false;
        } catch (NullPointerException var4) {
            return false;
        }
    }

    public Date getLoadTime() {
        return this.loadTime;
    }

    public static String getPropertiesValue(String name, String key) {
        try {
            return getInstance(name).get(key);
        } catch (MissingResourceException var3) {
            return "";
        } catch (NullPointerException var4) {
            return "";
        }
    }
}
```

#### 定义value注解

依然是轻车熟路，这里的`value()`是用来接受我们的配置名称的

```java
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Value {
    String value();
}
```

#### 实现配置注入

在之前的实现基础上，我们增加了一个配置处理的类，这个类有两个方法，分别是用于实现单个属性注入和批量属性注入：

```java
public class ConfigurationHandler {
    private static final PropertiesUtil propertiesUtil = PropertiesUtil.getInstance("application");

    /**
     * 初始化value配置信息
     * @param instance
     * @param field
     * @throws IllegalAccessException
     */
    public static void initValueConfig(Object instance, Field field) throws IllegalAccessException {
        Annotation annotation = field.getAnnotation(Value.class);
        if (Objects.nonNull(annotation)) {
            String propertiesKeyName = ((Value) annotation).value();
            Class<?> type = field.getType();
            if (!field.isAccessible()) {
                field.setAccessible(Boolean.TRUE);
            }
            if (Integer.class.equals(type)) {
                field.setInt(instance, propertiesUtil.getInt(propertiesKeyName));
            } else if (Boolean.class.equals(type)) {
                field.setBoolean(instance, propertiesUtil.getBoolean(propertiesKeyName));
            } else {
                field.set(instance, propertiesUtil.get(propertiesKeyName));
            }
        }
    }

    /**
     * 批量初始化value配置
     * @param aClass
     * @param instance
     * @throws IllegalAccessException
     */
    public static void batchInitValueConfig(Class aClass, Object instance) throws IllegalAccessException {
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            initValueConfig(instance, declaredField);
        }

    }
}
```

首先，我们在`IoC`实例化阶段，对各种组件的字段进行扫描，拿出有`Value`注解的属性，根据注解的值获取对应的配置。

#### 使用效果

我们在`service`组件上加入一个属性，并加上`value`注解：

![](https://gitee.com/sysker/picBed/raw/master/images/20210609085248.png)

然后在我们的配置文件中增加对应的配置：

![](https://gitee.com/sysker/picBed/raw/master/images/20210609085411.png)

