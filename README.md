# Interface Cooperation
运用mkdocs工具的material主题，生成基于RepChain v1.3的链外接口协同组件文档。
## 项目环境
+ 安装mkdocs前需要安装python环境。
+ 在控制台运行如下命令即可安装安装[mkdocs](https://www.mkdocs.org/user-guide/installation/)：
```
pip install mkdocs
```
+ 在项目根目录的子文件夹mkdocs-material/material下使用git命令安装[Material for MkDocs](https://github.com/squidfunk/mkdocs-material)：
```
git clone https://github.com/squidfunk/mkdocs-material.git
```

+ 安装material主题的依赖项：
```
pip install -e mkdocs-material
```

## 使用说明
+ 在项目目录下，有一个子目录docs，其中包含了源文件、页面等数据；
还有一个文件mkdocs.yml，其中是配置文件。
+ 在配置文件mkdocs.yml中修改为material主题：
```
theme:
  name: material
```
+ 添加页面：在docs目录下添加新页面的Markdown文件，并在配置文件mkdocs.yml中添加nav配置项的信息。具体使用方法详见[MkDocs官方文档](https://www.mkdocs.org/)和[Material for MkDocs官方文档](https://squidfunk.github.io/mkdocs-material/)。
+ 运行mkdocs serve命令实现修改保存后实时预览。
+ 完成编辑后，使用如下命令为Markdown文件构建静态站点：
```
mkdocs build
```
+ 基于RepChain 1.3的链外接口协同组件的原理说明、安装配置、使用规范以及演示示例等，请访问[接口协同官方文档](https://btajl.gitee.io/api-coord/)。