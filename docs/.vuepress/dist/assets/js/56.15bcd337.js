(window.webpackJsonp=window.webpackJsonp||[]).push([[56],{830:function(s,t,a){"use strict";a.r(t);var n=a(32),e=Object(n.a)({},(function(){var s=this,t=s.$createElement,a=s._self._c||t;return a("ContentSlotsDistributor",{attrs:{"slot-key":s.$parent.slotKey}},[a("h1",{attrs:{id:"前期准备"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#前期准备"}},[s._v("#")]),s._v(" 前期准备")]),s._v(" "),a("h2",{attrs:{id:"一、安装jdk"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#一、安装jdk"}},[s._v("#")]),s._v(" 一、安装JDK")]),s._v(" "),a("blockquote",[a("p",[s._v("需要在部署中间件的服务器及开发的机器上都进行安装。")]),s._v(" "),a("p",[s._v("参考准备工作中的"),a("RouterLink",{attrs:{to:"/pages/d07be6/#_1-java-1-8-必须"}},[s._v("Java安装")])],1)]),s._v(" "),a("h2",{attrs:{id:"二、定义接口"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#二、定义接口"}},[s._v("#")]),s._v(" 二、定义接口")]),s._v(" "),a("blockquote",[a("p",[s._v("服务提供方需要提供接口定义标准。")]),s._v(" "),a("p",[s._v("具体使用方式请参考 "),a("RouterLink",{attrs:{to:"/pages/a190e6/"}},[s._v("服务定义")])],1)]),s._v(" "),a("h2",{attrs:{id:"三、服务登记及服务调用登记"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#三、服务登记及服务调用登记"}},[s._v("#")]),s._v(" 三、服务登记及服务调用登记")]),s._v(" "),a("blockquote",[a("p",[s._v("服务登记为服务提供方进行服务登记，登记服务方的服务器地址及端口号等信息。")]),s._v(" "),a("p",[s._v("调用方登记同服务方法登记相同，为调用方登记服务器地址及端口号等信息。")]),s._v(" "),a("p",[s._v("具体登记步骤请参考 "),a("RouterLink",{attrs:{to:"/pages/99c155/"}},[s._v("服务登记")])],1)]),s._v(" "),a("h2",{attrs:{id:"四、部署中间件"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#四、部署中间件"}},[s._v("#")]),s._v(" 四、部署中间件")]),s._v(" "),a("div",{staticClass:"custom-block tip"},[a("p",{staticClass:"custom-block-title"},[s._v("提示")]),s._v(" "),a("ol",[a("li",[a("p",[s._v("此处部署中间件需要服务 "),a("strong",[s._v("提供方")]),s._v(" 及服务 "),a("strong",[s._v("调用方")]),s._v(" 都进行 "),a("strong",[s._v("部署")]),s._v(" ，所有的接口协同数据传输及存证均由 "),a("strong",[s._v("中间件")]),s._v(" 进行完成。")])]),s._v(" "),a("li",[a("p",[s._v("部署前，服务提供方和服务调用方需要通过线下的方式，进行证书交换。")])]),s._v(" "),a("li",[a("p",[s._v("双方交换好证书后，需要将证书写入配置文件中 "),a("code",[s._v("repchain-services")]),s._v(" 下，同时将双方的ID也写入其中。")])])])]),s._v(" "),a("h3",{attrs:{id:"_1-下载中间件-参考下载中间件"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#_1-下载中间件-参考下载中间件"}},[s._v("#")]),s._v(" 1. 下载中间件，参考"),a("RouterLink",{attrs:{to:"/pages/8237f3/#二、下载中间件"}},[s._v("下载中间件")])],1),s._v(" "),a("h3",{attrs:{id:"_2-解压缩压缩包-参考解压"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#_2-解压缩压缩包-参考解压"}},[s._v("#")]),s._v(" 2. 解压缩压缩包，参考"),a("RouterLink",{attrs:{to:"/pages/8237f3/#三、解压"}},[s._v("解压")])],1),s._v(" "),a("h3",{attrs:{id:"_3-修改配置"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#_3-修改配置"}},[s._v("#")]),s._v(" 3.  修改配置")]),s._v(" "),a("div",{staticClass:"custom-block warning"},[a("p",{staticClass:"custom-block-title"},[s._v("注意")]),s._v(" "),a("p",[s._v("修改配置请按照下面配置说明进行修改，下面所述说明均为 "),a("strong",[s._v("必须")]),s._v(" 修改的选项。")]),s._v(" "),a("p",[s._v("配置文件中的其他的默认配置可以根据自己的需求进行修改，但是不要删除任何默认配置。")])]),s._v(" "),a("p",[s._v("​\t此处说明需要修改的几个 "),a("strong",[s._v("必要")]),s._v(" 的配置:")]),s._v(" "),a("ul",[a("li",[s._v("RepChain相关配置都需要进行修改，具体如下，详细说明可参考"),a("RouterLink",{attrs:{to:"/pages/485cb8/#_1-repchain"}},[s._v("此处")])],1)]),s._v(" "),a("div",{staticClass:"language-yaml line-numbers-mode"},[a("pre",{pre:!0,attrs:{class:"language-yaml"}},[a("code",[a("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# repchain配置文件")]),s._v("\n"),a("span",{pre:!0,attrs:{class:"token key atrule"}},[s._v("repchain")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(":")]),s._v("\n  "),a("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# repchain 地址")]),s._v("\n  "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[s._v("host")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(":")]),s._v(" 192.168.2.76"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(":")]),a("span",{pre:!0,attrs:{class:"token number"}},[s._v("8081")]),s._v("\n  "),a("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 用户唯一标识")]),s._v("\n  "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[s._v("creditCode")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(":")]),s._v(" CGZL7XOwwa\n  "),a("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 证书名称")]),s._v("\n  "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[s._v("certName")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(":")]),s._v(" test\n  "),a("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 证书")]),s._v("\n  "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[s._v("cert")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(":")]),s._v(" "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("---")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("-")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("-")]),s._v("BEGIN CERTIFICATE"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("---")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("-")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("-")]),s._v("\n    MIIBIDCBx6ADAgECAgQDNHtwMAoGCCqGSM49BAMCMA8xDTALBgNVBAMMBFRFU1Qw\n    IhgPMjAyMTEwMjYwMTE0MDVaGA85OTk5MTIzMTIzNTk1OVowDzENMAsGA1UEAwwE\n    VEVTVDBZMBMGByqGSM49AgEGCCqGSM49AwEHA0IABO3voNU9OV4EoDyBHQcD+az/\n    E5TB1i5fRf+tTaNgjCWr6jErt/Ce21b18uwTuZXx38nvA5l26S1MEpCAuuJSFymj\n    DTALMAkGA1UdIwQCMAAwCgYIKoZIzj0EAwIDSAAwRQIhANjTlEKVu0O5lgEloHrh\n    ZLcNMHH9S2sVzasTK5OJuXwGAiBGi2GL4W4XlWzjl94swApuv2bb7rbo2huBIzhc\n    zZXMcw==\n    "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("---")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("-")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("-")]),s._v("END CERTIFICATE"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("---")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("-")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("-")]),s._v("\n  "),a("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 加密私钥")]),s._v("\n  "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[s._v("privateKey")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(":")]),s._v(" "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("---")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("-")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("-")]),s._v("BEGIN ENCRYPTED PRIVATE KEY"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("---")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("-")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("-")]),s._v("\n    MIHUMD8GCSqGSIb3DQEFDTAyMBoGCSqGSIb3DQEFDDANBAi0q5jmLN61vAIBZDAU\n    BggqhkiG9w0DBwQITUCFquFjnwEEgZAbZouweJPtR6zRaDhULbyzF3uaOnMErtx3\n    kIguDP/qeb3wVoTW8kFuOw1jdrY5WyHGCngfpM5br6AUaEqIlBnA7rS3KZfhsxxq\n    yLQcaySsfUowUE61oStDJtDgHMJE0dbxuYXjnzSQrnp9AnnX65uwUeDE6c+i7DnW\n    pWoroVTXK/dQb4vWNmxmacQ6G8jmW0s=\n    "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("---")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("-")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("-")]),s._v("END ENCRYPTED PRIVATE KEY"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("---")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("-")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("-")]),s._v("\n  "),a("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 私钥设置的密码")]),s._v("\n  "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[s._v("password")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(":")]),s._v(" "),a("span",{pre:!0,attrs:{class:"token number"}},[s._v("123456")]),s._v("\n  "),a("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 签名证书对应的请求方id和服务方id可在dashboard查看")]),s._v("\n  "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[s._v("services")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(":")]),s._v("\n    "),a("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 用户自定义id，不能重复，必填")]),s._v("\n    "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("-")]),s._v(" "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[s._v("serviceId")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(":")]),s._v(" "),a("span",{pre:!0,attrs:{class:"token number"}},[s._v("1")]),s._v("\n      "),a("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 调用方ID")]),s._v("\n      "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[s._v("e_from")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(":")]),s._v(" bb743c821635920387678\n      "),a("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 调用方证书")]),s._v("\n      "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[s._v("from_cert")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(":")]),s._v(" "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("---")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("-")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("-")]),s._v("BEGIN CERTIFICATE"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("---")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("-")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("-")]),s._v("\n        MIIBIDCBx6ADAgECAgQDNHtwMAoGCCqGSM49BAMCMA8xDTALBgNVBAMMBFRFU1Qw\n        IhgPMjAyMTEwMjYwMTE0MDVaGA85OTk5MTIzMTIzNTk1OVowDzENMAsGA1UEAwwE\n        VEVTVDBZMBMGByqGSM49AgEGCCqGSM49AwEHA0IABO3voNU9OV4EoDyBHQcD+az/\n        E5TB1i5fRf+tTaNgjCWr6jErt/Ce21b18uwTuZXx38nvA5l26S1MEpCAuuJSFymj\n        DTALMAkGA1UdIwQCMAAwCgYIKoZIzj0EAwIDSAAwRQIhANjTlEKVu0O5lgEloHrh\n        ZLcNMHH9S2sVzasTK5OJuXwGAiBGi2GL4W4XlWzjl94swApuv2bb7rbo2huBIzhc\n        zZXMcw==\n        "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("---")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("-")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("-")]),s._v("END CERTIFICATE"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("---")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("-")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("-")]),s._v("\n      "),a("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 服务提供方id")]),s._v("\n      "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[s._v("e_to")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(":")]),s._v(" 7b5c66df1635920342945\n      "),a("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 提供方证书")]),s._v("\n      "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[s._v("to_cert")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(":")]),s._v(" "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("---")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("-")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("-")]),s._v("BEGIN CERTIFICATE"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("---")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("-")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("-")]),s._v("\n        MIIBIDCBx6ADAgECAgQDNHtwMAoGCCqGSM49BAMCMA8xDTALBgNVBAMMBFRFU1Qw\n        IhgPMjAyMTEwMjYwMTE0MDVaGA85OTk5MTIzMTIzNTk1OVowDzENMAsGA1UEAwwE\n        VEVTVDBZMBMGByqGSM49AgEGCCqGSM49AwEHA0IABO3voNU9OV4EoDyBHQcD+az/\n        E5TB1i5fRf+tTaNgjCWr6jErt/Ce21b18uwTuZXx38nvA5l26S1MEpCAuuJSFymj\n        DTALMAkGA1UdIwQCMAAwCgYIKoZIzj0EAwIDSAAwRQIhANjTlEKVu0O5lgEloHrh\n        ZLcNMHH9S2sVzasTK5OJuXwGAiBGi2GL4W4XlWzjl94swApuv2bb7rbo2huBIzhc\n        zZXMcw==\n        "),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("---")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("-")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("-")]),s._v("END CERTIFICATE"),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("---")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("-")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v("-")]),s._v("\n")])]),s._v(" "),a("div",{staticClass:"line-numbers-wrapper"},[a("span",{staticClass:"line-number"},[s._v("1")]),a("br"),a("span",{staticClass:"line-number"},[s._v("2")]),a("br"),a("span",{staticClass:"line-number"},[s._v("3")]),a("br"),a("span",{staticClass:"line-number"},[s._v("4")]),a("br"),a("span",{staticClass:"line-number"},[s._v("5")]),a("br"),a("span",{staticClass:"line-number"},[s._v("6")]),a("br"),a("span",{staticClass:"line-number"},[s._v("7")]),a("br"),a("span",{staticClass:"line-number"},[s._v("8")]),a("br"),a("span",{staticClass:"line-number"},[s._v("9")]),a("br"),a("span",{staticClass:"line-number"},[s._v("10")]),a("br"),a("span",{staticClass:"line-number"},[s._v("11")]),a("br"),a("span",{staticClass:"line-number"},[s._v("12")]),a("br"),a("span",{staticClass:"line-number"},[s._v("13")]),a("br"),a("span",{staticClass:"line-number"},[s._v("14")]),a("br"),a("span",{staticClass:"line-number"},[s._v("15")]),a("br"),a("span",{staticClass:"line-number"},[s._v("16")]),a("br"),a("span",{staticClass:"line-number"},[s._v("17")]),a("br"),a("span",{staticClass:"line-number"},[s._v("18")]),a("br"),a("span",{staticClass:"line-number"},[s._v("19")]),a("br"),a("span",{staticClass:"line-number"},[s._v("20")]),a("br"),a("span",{staticClass:"line-number"},[s._v("21")]),a("br"),a("span",{staticClass:"line-number"},[s._v("22")]),a("br"),a("span",{staticClass:"line-number"},[s._v("23")]),a("br"),a("span",{staticClass:"line-number"},[s._v("24")]),a("br"),a("span",{staticClass:"line-number"},[s._v("25")]),a("br"),a("span",{staticClass:"line-number"},[s._v("26")]),a("br"),a("span",{staticClass:"line-number"},[s._v("27")]),a("br"),a("span",{staticClass:"line-number"},[s._v("28")]),a("br"),a("span",{staticClass:"line-number"},[s._v("29")]),a("br"),a("span",{staticClass:"line-number"},[s._v("30")]),a("br"),a("span",{staticClass:"line-number"},[s._v("31")]),a("br"),a("span",{staticClass:"line-number"},[s._v("32")]),a("br"),a("span",{staticClass:"line-number"},[s._v("33")]),a("br"),a("span",{staticClass:"line-number"},[s._v("34")]),a("br"),a("span",{staticClass:"line-number"},[s._v("35")]),a("br"),a("span",{staticClass:"line-number"},[s._v("36")]),a("br"),a("span",{staticClass:"line-number"},[s._v("37")]),a("br"),a("span",{staticClass:"line-number"},[s._v("38")]),a("br"),a("span",{staticClass:"line-number"},[s._v("39")]),a("br"),a("span",{staticClass:"line-number"},[s._v("40")]),a("br"),a("span",{staticClass:"line-number"},[s._v("41")]),a("br"),a("span",{staticClass:"line-number"},[s._v("42")]),a("br"),a("span",{staticClass:"line-number"},[s._v("43")]),a("br"),a("span",{staticClass:"line-number"},[s._v("44")]),a("br"),a("span",{staticClass:"line-number"},[s._v("45")]),a("br"),a("span",{staticClass:"line-number"},[s._v("46")]),a("br"),a("span",{staticClass:"line-number"},[s._v("47")]),a("br"),a("span",{staticClass:"line-number"},[s._v("48")]),a("br"),a("span",{staticClass:"line-number"},[s._v("49")]),a("br"),a("span",{staticClass:"line-number"},[s._v("50")]),a("br"),a("span",{staticClass:"line-number"},[s._v("51")]),a("br"),a("span",{staticClass:"line-number"},[s._v("52")]),a("br"),a("span",{staticClass:"line-number"},[s._v("53")]),a("br"),a("span",{staticClass:"line-number"},[s._v("54")]),a("br"),a("span",{staticClass:"line-number"},[s._v("55")]),a("br"),a("span",{staticClass:"line-number"},[s._v("56")]),a("br")])]),a("ul",[a("li",[s._v("Middleware配置，以下配置请根据服务登记或服务调用相关内容进行修改，详细说明可参考"),a("RouterLink",{attrs:{to:"/pages/485cb8/#_2-middleware"}},[s._v("此处")])],1)]),s._v(" "),a("div",{staticClass:"language-yaml line-numbers-mode"},[a("pre",{pre:!0,attrs:{class:"language-yaml"}},[a("code",[a("span",{pre:!0,attrs:{class:"token comment"}},[s._v("## 中间件配置")]),s._v("\n"),a("span",{pre:!0,attrs:{class:"token key atrule"}},[s._v("middleware")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(":")]),s._v("\n  "),a("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 用来访问宿主机的服务接口的客户端")]),s._v("\n  "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[s._v("recClient")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(":")]),s._v("\n    "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[s._v("host")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(":")]),s._v(" 127.0.0.1\n    "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[s._v("port")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(":")]),s._v(" "),a("span",{pre:!0,attrs:{class:"token number"}},[s._v("80")]),s._v("\n  "),a("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 提供给宿主机调用中间件的服务接口配置")]),s._v("\n  "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[s._v("recServer")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(":")]),s._v("\n    "),a("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 端口")]),s._v("\n    "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[s._v("port")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(":")]),s._v(" "),a("span",{pre:!0,attrs:{class:"token number"}},[s._v("8888")]),s._v("\n  "),a("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 提供给其他中间件访问的grpc服务端口，此处需要和服务登记或服务调用中提交的接口保持一致")]),s._v("\n  "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[s._v("comServer")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(":")]),s._v("\n    "),a("span",{pre:!0,attrs:{class:"token comment"}},[s._v("# 端口")]),s._v("\n    "),a("span",{pre:!0,attrs:{class:"token key atrule"}},[s._v("port")]),a("span",{pre:!0,attrs:{class:"token punctuation"}},[s._v(":")]),s._v(" "),a("span",{pre:!0,attrs:{class:"token number"}},[s._v("8080")]),s._v("\n")])]),s._v(" "),a("div",{staticClass:"line-numbers-wrapper"},[a("span",{staticClass:"line-number"},[s._v("1")]),a("br"),a("span",{staticClass:"line-number"},[s._v("2")]),a("br"),a("span",{staticClass:"line-number"},[s._v("3")]),a("br"),a("span",{staticClass:"line-number"},[s._v("4")]),a("br"),a("span",{staticClass:"line-number"},[s._v("5")]),a("br"),a("span",{staticClass:"line-number"},[s._v("6")]),a("br"),a("span",{staticClass:"line-number"},[s._v("7")]),a("br"),a("span",{staticClass:"line-number"},[s._v("8")]),a("br"),a("span",{staticClass:"line-number"},[s._v("9")]),a("br"),a("span",{staticClass:"line-number"},[s._v("10")]),a("br"),a("span",{staticClass:"line-number"},[s._v("11")]),a("br"),a("span",{staticClass:"line-number"},[s._v("12")]),a("br"),a("span",{staticClass:"line-number"},[s._v("13")]),a("br"),a("span",{staticClass:"line-number"},[s._v("14")]),a("br")])]),a("h3",{attrs:{id:"_4-启动中间件-参考启动停止重启"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#_4-启动中间件-参考启动停止重启"}},[s._v("#")]),s._v(" 4. 启动中间件，参考"),a("RouterLink",{attrs:{to:"/pages/764dac/"}},[s._v("启动停止重启")])],1),s._v(" "),a("h2",{attrs:{id:"五、引入客户端"}},[a("a",{staticClass:"header-anchor",attrs:{href:"#五、引入客户端"}},[s._v("#")]),s._v(" 五、引入客户端")]),s._v(" "),a("blockquote",[a("p",[s._v("发布接入中间件客户端到本地仓库并引入代码中，参考"),a("RouterLink",{attrs:{to:"/pages/d07be6/#_10-接入中间件客户端-可选"}},[s._v("此处")]),s._v("。")],1)]),s._v(" "),a("blockquote",[a("p",[s._v("同时，客户端也提供 "),a("strong",[s._v("jar")]),s._v(" 方式引入，点击 "),a("a",{attrs:{href:"https://gitee.com/BTAJL/api-coord/attach_files/901593/download/Interface-Cooperation-Client-1.0-SNAPSHOT.jar",target:"_blank",rel:"noopener noreferrer"}},[s._v("此处"),a("OutboundLink")],1),s._v(" 下载jar包。")])])])}),[],!1,null,null,null);t.default=e.exports}}]);