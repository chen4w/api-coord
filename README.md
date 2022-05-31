# Interface Cooperation
运用mkdocs工具的material主题，生成基于RepChain v1.3的链外接口协同组件文档。
## 项目环境
+ 安装mkdocs前需要安装python3环境，此处使用[Python3.9](https://www.python.org/downloads/)。
+ 在控制台运行如下命令即可安装[mkdocs](https://www.mkdocs.org/user-guide/installation/)：
```
pip install mkdocs
```

+ 使用pip安装[Material for MkDocs](https://pypi.org/project/mkdocs-material/)，这将自动安装所有依赖的兼容版本：MkDocs、Markdown、Pygments和Python Markdown Extensions：
```
pip install mkdocs-material
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
+ 运行如下命令实现修改保存后实时预览：
```
mkdocs serve
```
+ 完成编辑后，使用如下命令为Markdown文件构建静态站点：
```
mkdocs build
```
+ 通过配置[mike插件扩展](https://github.com/jimporter/mike)，可以使用Git管理MkDocs生成文档的多个版本。在mkdocs.yml配置文件中进行如下设置：
```
extra:
  version: 
    provider: mike
```
使用pip安装mike插件：
```
pip install mike
```
要部署当前版本的文档，只需运行：
```
mike deploy [version]
```
关于mike插件的使用方法，请访问[官方文档](https://github.com/jimporter/mike)。
+ 通过配置[mkdocs-with-pdf插件扩展](https://github.com/orzih/mkdocs-with-pdf)，可以将MkDocs生成文档导出为PDF文件。在mkdocs.yml配置文件中进行如下设置：
```
plugins:
    - with-pdf
```
使用pip安装mkdocs-with-pdf插件：
```
pip install mkdocs-with-pdf
```
关于mkdocs-with-pdf插件的使用方法，请访问[官方文档](https://github.com/orzih/mkdocs-with-pdf)。
+ 基于RepChain 1.3的链外接口协同组件的原理说明、安装配置、使用规范以及演示示例等，请访问[接口协同官方文档](https://btajl.gitee.io/api-coord/)。