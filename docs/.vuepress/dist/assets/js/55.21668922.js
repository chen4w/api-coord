(window.webpackJsonp=window.webpackJsonp||[]).push([[55],{829:function(a,s,t){"use strict";t.r(s);var e=t(32),r=Object(e.a)({},(function(){var a=this,s=a.$createElement,t=a._self._c||s;return t("ContentSlotsDistributor",{attrs:{"slot-key":a.$parent.slotKey}},[t("h1",{attrs:{id:"快速开始"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#快速开始"}},[a._v("#")]),a._v(" 快速开始")]),a._v(" "),t("h2",{attrs:{id:"一、安装jdk"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#一、安装jdk"}},[a._v("#")]),a._v(" 一、安装JDK")]),a._v(" "),t("blockquote",[t("p",[a._v("下载jdk8，安装并根据自己的操作系统配置好环境变量。")]),a._v(" "),t("p",[a._v("推荐下载 "),t("a",{attrs:{href:"https://www.azul.com/downloads/?version=java-8-lts&package=jdk",target:"_blank",rel:"noopener noreferrer"}},[a._v("zulu-jdk-1.8"),t("OutboundLink")],1)])]),a._v(" "),t("h2",{attrs:{id:"二、下载中间件"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#二、下载中间件"}},[a._v("#")]),a._v(" 二、下载中间件")]),a._v(" "),t("blockquote",[t("p",[a._v("根据不同系统下载中间件的压缩包")])]),a._v(" "),t("h3",{attrs:{id:"_1-windows"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#_1-windows"}},[a._v("#")]),a._v(" 1. Windows")]),a._v(" "),t("p",[a._v("下载 "),t("strong",[a._v("Zip")]),a._v(" 文件 "),t("a",{attrs:{href:"https://gitee.com/BTAJL/api-coord/attach_files/893372/download/Interface-Cooperation-Middleware-1.0-SNAPSHOT.zip",target:"_blank",rel:"noopener noreferrer"}},[a._v("https://gitee.com/BTAJL/api-coord/attach_files/893372/download/Interface-Cooperation-Middleware-1.0-SNAPSHOT.zip"),t("OutboundLink")],1)]),a._v(" "),t("h3",{attrs:{id:"_2-linux"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#_2-linux"}},[a._v("#")]),a._v(" 2. Linux")]),a._v(" "),t("ul",[t("li",[t("p",[a._v("使用 "),t("strong",[a._v("wget")]),a._v(" 下载 "),t("strong",[a._v("tar")])]),a._v(" "),t("div",{staticClass:"language-sh line-numbers-mode"},[t("pre",{pre:!0,attrs:{class:"language-sh"}},[t("code",[t("span",{pre:!0,attrs:{class:"token function"}},[a._v("wget")]),a._v(" https://gitee.com/BTAJL/api-coord/attach_files/893371/download/Interface-Cooperation-Middleware-1.0-SNAPSHOT.tar\n")])]),a._v(" "),t("div",{staticClass:"line-numbers-wrapper"},[t("span",{staticClass:"line-number"},[a._v("1")]),t("br")])])]),a._v(" "),t("li",[t("p",[a._v("使用远程传输 "),t("strong",[a._v("tar")])]),a._v(" "),t("blockquote",[t("p",[a._v("可以先下载 "),t("strong",[a._v("tar")]),a._v(" 到本地电脑上，然后通过"),t("strong",[a._v("SSH")]),a._v("工具将tar包传输到服务器中。")])])])]),a._v(" "),t("h3",{attrs:{id:"_3-mac"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#_3-mac"}},[a._v("#")]),a._v(" 3. Mac")]),a._v(" "),t("p",[a._v("下载 "),t("strong",[a._v("Zip")]),a._v(" 文件或下载 "),t("strong",[a._v("Tar")]),a._v(" 文件。")]),a._v(" "),t("h2",{attrs:{id:"三、解压"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#三、解压"}},[a._v("#")]),a._v(" 三、解压")]),a._v(" "),t("h3",{attrs:{id:"_1-windows-2"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#_1-windows-2"}},[a._v("#")]),a._v(" 1. Windows")]),a._v(" "),t("blockquote",[t("p",[a._v("使用解压工具将 "),t("strong",[a._v("Zip")]),a._v(" 压缩包解压缩。")])]),a._v(" "),t("h3",{attrs:{id:"_2-linux-2"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#_2-linux-2"}},[a._v("#")]),a._v(" 2. Linux")]),a._v(" "),t("div",{staticClass:"language-sh line-numbers-mode"},[t("pre",{pre:!0,attrs:{class:"language-sh"}},[t("code",[t("span",{pre:!0,attrs:{class:"token function"}},[a._v("tar")]),a._v(" -xvf Interface-Cooperation-Middleware-1.0-SNAPSHOT.tar\n")])]),a._v(" "),t("div",{staticClass:"line-numbers-wrapper"},[t("span",{staticClass:"line-number"},[a._v("1")]),t("br")])]),t("h3",{attrs:{id:"_3-mac-2"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#_3-mac-2"}},[a._v("#")]),a._v(" 3. Mac")]),a._v(" "),t("blockquote",[t("p",[a._v("解压方式使用上述两种任选其一即可。")])]),a._v(" "),t("h2",{attrs:{id:"四、修改配置文件"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#四、修改配置文件"}},[a._v("#")]),a._v(" 四、修改配置文件")]),a._v(" "),t("ul",[t("li",[t("p",[a._v("打开文件 "),t("code",[a._v("conf/application-middle.yml")])])]),a._v(" "),t("li",[t("p",[a._v("填写 RepChain 相关地址")]),a._v(" "),t("div",{staticClass:"language-yaml line-numbers-mode"},[t("pre",{pre:!0,attrs:{class:"language-yaml"}},[t("code",[t("span",{pre:!0,attrs:{class:"token comment"}},[a._v("# repchain配置文件")]),a._v("\n"),t("span",{pre:!0,attrs:{class:"token key atrule"}},[a._v("repchain")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(":")]),a._v("\n  "),t("span",{pre:!0,attrs:{class:"token comment"}},[a._v("# repchain 地址")]),a._v("\n  "),t("span",{pre:!0,attrs:{class:"token key atrule"}},[a._v("host")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(":")]),a._v(" 192.168.2.76"),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(":")]),t("span",{pre:!0,attrs:{class:"token number"}},[a._v("8081")]),a._v("\n  "),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("...")]),a._v("\n")])]),a._v(" "),t("div",{staticClass:"line-numbers-wrapper"},[t("span",{staticClass:"line-number"},[a._v("1")]),t("br"),t("span",{staticClass:"line-number"},[a._v("2")]),t("br"),t("span",{staticClass:"line-number"},[a._v("3")]),t("br"),t("span",{staticClass:"line-number"},[a._v("4")]),t("br"),t("span",{staticClass:"line-number"},[a._v("5")]),t("br")])])]),a._v(" "),t("li",[t("p",[a._v("填写你的服务器地址")])])]),a._v(" "),t("blockquote",[t("p",[a._v("由于中间件和宿主服务需要部署在同一个操作系统中，所以host填写127.0.0.1即可")])]),a._v(" "),t("div",{staticClass:"language-yaml line-numbers-mode"},[t("pre",{pre:!0,attrs:{class:"language-yaml"}},[t("code",[t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("...")]),a._v("\n"),t("span",{pre:!0,attrs:{class:"token comment"}},[a._v("# 中间件用于访问宿主服务的客户端配置")]),a._v("\n"),t("span",{pre:!0,attrs:{class:"token key atrule"}},[a._v("recClient")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(":")]),a._v("\n  "),t("span",{pre:!0,attrs:{class:"token comment"}},[a._v("# 服务地址")]),a._v("\n  "),t("span",{pre:!0,attrs:{class:"token key atrule"}},[a._v("host")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(":")]),a._v(" 127.0.0.1\n  "),t("span",{pre:!0,attrs:{class:"token comment"}},[a._v("# 服务端口号")]),a._v("\n  "),t("span",{pre:!0,attrs:{class:"token key atrule"}},[a._v("port")]),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v(":")]),a._v(" "),t("span",{pre:!0,attrs:{class:"token number"}},[a._v("8080")]),a._v("    \n  "),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("...")]),a._v("\n")])]),a._v(" "),t("div",{staticClass:"line-numbers-wrapper"},[t("span",{staticClass:"line-number"},[a._v("1")]),t("br"),t("span",{staticClass:"line-number"},[a._v("2")]),t("br"),t("span",{staticClass:"line-number"},[a._v("3")]),t("br"),t("span",{staticClass:"line-number"},[a._v("4")]),t("br"),t("span",{staticClass:"line-number"},[a._v("5")]),t("br"),t("span",{staticClass:"line-number"},[a._v("6")]),t("br"),t("span",{staticClass:"line-number"},[a._v("7")]),t("br"),t("span",{staticClass:"line-number"},[a._v("8")]),t("br")])]),t("h2",{attrs:{id:"五、启动中间件"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#五、启动中间件"}},[a._v("#")]),a._v(" 五、启动中间件")]),a._v(" "),t("h3",{attrs:{id:"_1-windows-3"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#_1-windows-3"}},[a._v("#")]),a._v(" 1. Windows")]),a._v(" "),t("ul",[t("li",[t("p",[a._v("终端执行启动命令")]),a._v(" "),t("div",{staticClass:"language-sh line-numbers-mode"},[t("pre",{pre:!0,attrs:{class:"language-sh"}},[t("code",[a._v("bin"),t("span",{pre:!0,attrs:{class:"token punctuation"}},[a._v("\\")]),a._v("start.cmd\n")])]),a._v(" "),t("div",{staticClass:"line-numbers-wrapper"},[t("span",{staticClass:"line-number"},[a._v("1")]),t("br")])])]),a._v(" "),t("li",[t("p",[a._v("或双击启动脚本 "),t("code",[a._v("bin/start.cmd")])])])]),a._v(" "),t("p",[t("img",{attrs:{src:"/api-coord/img/middleware/image-20211119095648760.png",alt:"image-20211119095648760"}})]),a._v(" "),t("h3",{attrs:{id:"_2-linux-或-mac"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#_2-linux-或-mac"}},[a._v("#")]),a._v(" 2. Linux 或 Mac")]),a._v(" "),t("ul",[t("li",[t("p",[a._v("终端执行启动脚本 "),t("code",[a._v("bin/startup.sh")])]),a._v(" "),t("div",{staticClass:"language-sh line-numbers-mode"},[t("pre",{pre:!0,attrs:{class:"language-sh"}},[t("code",[t("span",{pre:!0,attrs:{class:"token function"}},[a._v("sh")]),a._v(" bin/startup.sh \n")])]),a._v(" "),t("div",{staticClass:"line-numbers-wrapper"},[t("span",{staticClass:"line-number"},[a._v("1")]),t("br")])])])]),a._v(" "),t("h2",{attrs:{id:"六、查看日志"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#六、查看日志"}},[a._v("#")]),a._v(" 六、查看日志")]),a._v(" "),t("blockquote",[t("p",[a._v("中间件启动后会创建 "),t("code",[a._v("logs")]),a._v("文件夹，文件下会产生相应的日志文件。")])]),a._v(" "),t("p",[t("img",{attrs:{src:"/img/middleware/image-20211119100853078.png",alt:"image-20211119100853078"}})]),a._v(" "),t("h2",{attrs:{id:"七、停止中间件"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#七、停止中间件"}},[a._v("#")]),a._v(" 七、停止中间件")]),a._v(" "),t("h3",{attrs:{id:"_1-windows-4"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#_1-windows-4"}},[a._v("#")]),a._v(" 1. Windows")]),a._v(" "),t("ul",[t("li",[a._v("关闭终端窗口或者在终端执行 "),t("code",[a._v("Ctrl + C")])])]),a._v(" "),t("h3",{attrs:{id:"_2-linux-或-mac-2"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#_2-linux-或-mac-2"}},[a._v("#")]),a._v(" 2. Linux 或 Mac")]),a._v(" "),t("ul",[t("li",[t("p",[a._v("终端执行停止脚本 "),t("code",[a._v("bin/shutdown.sh")])]),a._v(" "),t("div",{staticClass:"language-sh line-numbers-mode"},[t("pre",{pre:!0,attrs:{class:"language-sh"}},[t("code",[t("span",{pre:!0,attrs:{class:"token function"}},[a._v("sh")]),a._v(" bin/shutdown.sh \n")])]),a._v(" "),t("div",{staticClass:"line-numbers-wrapper"},[t("span",{staticClass:"line-number"},[a._v("1")]),t("br")])])])])])}),[],!1,null,null,null);s.default=r.exports}}]);