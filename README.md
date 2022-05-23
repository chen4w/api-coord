## 环境

java1.8+

## 开发准备

* 安装Git,Jdk,Maven 并配置好环境变量

* 下载客户端代码

```shell
git clone https://gitee.com/BTAJL/api-coord.git -b mid-client
```

* 发布到本地仓库

```shell
./gradlew publishToMavenLocal
```

## Build(打包)

* 打包

```shell
./gradlew build
```

* 发布版本

发布包位于`build/distributions`目录下