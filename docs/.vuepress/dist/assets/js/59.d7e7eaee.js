(window.webpackJsonp=window.webpackJsonp||[]).push([[59],{839:function(v,_,t){"use strict";t.r(_);var s=t(32),r=Object(s.a)({},(function(){var v=this,_=v.$createElement,t=v._self._c||_;return t("ContentSlotsDistributor",{attrs:{"slot-key":v.$parent.slotKey}},[t("h1",{attrs:{id:"配置文件"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#配置文件"}},[v._v("#")]),v._v(" 配置文件")]),v._v(" "),t("div",{staticClass:"custom-block warning"},[t("p",{staticClass:"custom-block-title"},[v._v("注意")]),v._v(" "),t("p",[v._v("以下所有数据均为示例数据，请用户不要在生产环境中使用以下数据。")]),v._v(" "),t("p",[v._v("特别注意，一定 "),t("strong",[v._v("不要")]),v._v(" 使用示例数据中的证书及私钥！！！")])]),v._v(" "),t("h2",{attrs:{id:"一、说明"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#一、说明"}},[v._v("#")]),v._v(" 一、说明")]),v._v(" "),t("blockquote",[t("p",[v._v("配置文件位于中间件 "),t("code",[v._v("conf")]),v._v(" 目录下 "),t("code",[v._v("application-middle.yml")]),v._v(" 。")])]),v._v(" "),t("h2",{attrs:{id:"二、配置说明"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#二、配置说明"}},[v._v("#")]),v._v(" 二、配置说明")]),v._v(" "),t("h3",{attrs:{id:"_1-repchain"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#_1-repchain"}},[v._v("#")]),v._v(" 1. repchain")]),v._v(" "),t("blockquote",[t("p",[v._v("repchain及接口存证相关配置")])]),v._v(" "),t("h4",{attrs:{id:"host"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#host"}},[v._v("#")]),v._v(" host")]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("描述")]),v._v("：配置中间件连接RepChain的地址")]),v._v(" "),t("li",[t("strong",[v._v("示例")]),v._v("：192.168.2.76:8081")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])]),v._v(" "),t("h4",{attrs:{id:"height"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#height"}},[v._v("#")]),v._v(" height")]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：0")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：初始同步的区块高度，与已经持久化的区块高度做对比，取最大值。例：如果此处配置0，已经持久化的区块高度为2，则从2开始同步区块。")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])]),v._v(" "),t("h4",{attrs:{id:"creditcode"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#creditcode"}},[v._v("#")]),v._v(" creditCode")]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：CGZL7XOwwa")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：用户在区块链上的唯一标识。")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])]),v._v(" "),t("h4",{attrs:{id:"certname"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#certname"}},[v._v("#")]),v._v(" certName")]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：cert")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：用户用于接口存证的证书名称。")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])]),v._v(" "),t("h4",{attrs:{id:"cert"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#cert"}},[v._v("#")]),v._v(" cert")]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：")])]),v._v(" "),t("div",{staticClass:"language- line-numbers-mode"},[t("pre",{pre:!0,attrs:{class:"language-text"}},[t("code",[v._v("  -----BEGIN CERTIFICATE-----\n  MIIBIDCBx6ADAgECAgQDNHtwMAoGCCqGSM49BAMCMA8xDTALBgNVBAMMBFRFU1Qw\n  IhgPMjAyMTEwMjYwMTE0MDVaGA85OTk5MTIzMTIzNTk1OVowDzENMAsGA1UEAwwE\n  VEVTVDBZMBMGByqGSM49AgEGCCqGSM49AwEHA0IABO3voNU9OV4EoDyBHQcD+az/\n  E5TB1i5fRf+tTaNgjCWr6jErt/Ce21b18uwTuZXx38nvA5l26S1MEpCAuuJSFymj\n  DTALMAkGA1UdIwQCMAAwCgYIKoZIzj0EAwIDSAAwRQIhANjTlEKVu0O5lgEloHrh\n  ZLcNMHH9S2sVzasTK5OJuXwGAiBGi2GL4W4XlWzjl94swApuv2bb7rbo2huBIzhc\n  zZXMcw==\n  -----END CERTIFICATE-----\n")])]),v._v(" "),t("div",{staticClass:"line-numbers-wrapper"},[t("span",{staticClass:"line-number"},[v._v("1")]),t("br"),t("span",{staticClass:"line-number"},[v._v("2")]),t("br"),t("span",{staticClass:"line-number"},[v._v("3")]),t("br"),t("span",{staticClass:"line-number"},[v._v("4")]),t("br"),t("span",{staticClass:"line-number"},[v._v("5")]),t("br"),t("span",{staticClass:"line-number"},[v._v("6")]),t("br"),t("span",{staticClass:"line-number"},[v._v("7")]),t("br"),t("span",{staticClass:"line-number"},[v._v("8")]),t("br"),t("span",{staticClass:"line-number"},[v._v("9")]),t("br")])]),t("ul",[t("li",[t("strong",[v._v("描述")]),v._v("：用户用于接口存证的证书。")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])]),v._v(" "),t("h4",{attrs:{id:"privatekey"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#privatekey"}},[v._v("#")]),v._v(" privateKey")]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：")])]),v._v(" "),t("div",{staticClass:"language- line-numbers-mode"},[t("pre",{pre:!0,attrs:{class:"language-text"}},[t("code",[v._v(" -----BEGIN ENCRYPTED PRIVATE KEY-----\nMIHUMD8GCSqGSIb3DQEFDTAyMBoGCSqGSIb3DQEFDDANBAi0q5jmLN61vAIBZDAU\nBggqhkiG9w0DBwQITUCFquFjnwEEgZAbZouweJPtR6zRaDhULbyzF3uaOnMErtx3\nkIguDP/qeb3wVoTW8kFuOw1jdrY5WyHGCngfpM5br6AUaEqIlBnA7rS3KZfhsxxq\nyLQcaySsfUowUE61oStDJtDgHMJE0dbxuYXjnzSQrnp9AnnX65uwUeDE6c+i7DnW\npWoroVTXK/dQb4vWNmxmacQ6G8jmW0s=\n-----END ENCRYPTED PRIVATE KEY-----\n")])]),v._v(" "),t("div",{staticClass:"line-numbers-wrapper"},[t("span",{staticClass:"line-number"},[v._v("1")]),t("br"),t("span",{staticClass:"line-number"},[v._v("2")]),t("br"),t("span",{staticClass:"line-number"},[v._v("3")]),t("br"),t("span",{staticClass:"line-number"},[v._v("4")]),t("br"),t("span",{staticClass:"line-number"},[v._v("5")]),t("br"),t("span",{staticClass:"line-number"},[v._v("6")]),t("br"),t("span",{staticClass:"line-number"},[v._v("7")]),t("br")])]),t("ul",[t("li",[t("strong",[v._v("描述")]),v._v("：用户用于接口存证的私钥，用户的私钥请保存好，不要泄露给任何人。")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])]),v._v(" "),t("h4",{attrs:{id:"password"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#password"}},[v._v("#")]),v._v(" password")]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：123456")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：用户设置的私钥的密码")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])]),v._v(" "),t("h4",{attrs:{id:"services"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#services"}},[v._v("#")]),v._v(" services")]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("描述")]),v._v("：用户用来设置请求方或应答方证书的配置，可设置多个，每个请求方对应的应答方都需要再此配置。")])]),v._v(" "),t("p",[t("strong",[v._v("serviceId")])]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：1")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：用户自定义ID，使用客户端请求中间件时需要提供此ID来定位需要请求的服务。")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])]),v._v(" "),t("p",[t("strong",[v._v("e_from")])]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：bb743c821635920387678")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：服务调用方ID，可在RepChain管理控制台-链外协同-服务调用中Id列中获取。")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])]),v._v(" "),t("p",[t("strong",[v._v("from_cert")])]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：")])]),v._v(" "),t("div",{staticClass:"language- line-numbers-mode"},[t("pre",{pre:!0,attrs:{class:"language-text"}},[t("code",[v._v(" -----BEGIN CERTIFICATE-----\n   MIIBIDCBx6ADAgECAgQDNHtwMAoGCCqGSM49BAMCMA8xDTALBgNVBAMMBFRFU1Qw\n   IhgPMjAyMTEwMjYwMTE0MDVaGA85OTk5MTIzMTIzNTk1OVowDzENMAsGA1UEAwwE\n   VEVTVDBZMBMGByqGSM49AgEGCCqGSM49AwEHA0IABO3voNU9OV4EoDyBHQcD+az/\n   E5TB1i5fRf+tTaNgjCWr6jErt/Ce21b18uwTuZXx38nvA5l26S1MEpCAuuJSFymj\n   DTALMAkGA1UdIwQCMAAwCgYIKoZIzj0EAwIDSAAwRQIhANjTlEKVu0O5lgEloHrh\n   ZLcNMHH9S2sVzasTK5OJuXwGAiBGi2GL4W4XlWzjl94swApuv2bb7rbo2huBIzhc\n   zZXMcw==\n   -----END CERTIFICATE-----\n")])]),v._v(" "),t("div",{staticClass:"line-numbers-wrapper"},[t("span",{staticClass:"line-number"},[v._v("1")]),t("br"),t("span",{staticClass:"line-number"},[v._v("2")]),t("br"),t("span",{staticClass:"line-number"},[v._v("3")]),t("br"),t("span",{staticClass:"line-number"},[v._v("4")]),t("br"),t("span",{staticClass:"line-number"},[v._v("5")]),t("br"),t("span",{staticClass:"line-number"},[v._v("6")]),t("br"),t("span",{staticClass:"line-number"},[v._v("7")]),t("br"),t("span",{staticClass:"line-number"},[v._v("8")]),t("br"),t("span",{staticClass:"line-number"},[v._v("9")]),t("br")])]),t("ul",[t("li",[t("strong",[v._v("描述")]),v._v("： 服务调用方证书，用于请求接口校验身份时使用。需要调用方提供。")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])]),v._v(" "),t("p",[t("strong",[v._v("e_to")])]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：7b5c66df1635920342945")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：服务提供方Id，可在RepChain管理控制台-链外协同-服务登记中Id列中获取。")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])]),v._v(" "),t("p",[t("strong",[v._v("to_cert")])]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：")])]),v._v(" "),t("div",{staticClass:"language- line-numbers-mode"},[t("pre",{pre:!0,attrs:{class:"language-text"}},[t("code",[v._v(" -----BEGIN CERTIFICATE-----\n   MIIBIDCBx6ADAgECAgQDNHtwMAoGCCqGSM49BAMCMA8xDTALBgNVBAMMBFRFU1Qw\n   IhgPMjAyMTEwMjYwMTE0MDVaGA85OTk5MTIzMTIzNTk1OVowDzENMAsGA1UEAwwE\n   VEVTVDBZMBMGByqGSM49AgEGCCqGSM49AwEHA0IABO3voNU9OV4EoDyBHQcD+az/\n   E5TB1i5fRf+tTaNgjCWr6jErt/Ce21b18uwTuZXx38nvA5l26S1MEpCAuuJSFymj\n   DTALMAkGA1UdIwQCMAAwCgYIKoZIzj0EAwIDSAAwRQIhANjTlEKVu0O5lgEloHrh\n   ZLcNMHH9S2sVzasTK5OJuXwGAiBGi2GL4W4XlWzjl94swApuv2bb7rbo2huBIzhc\n   zZXMcw==\n   -----END CERTIFICATE-----\n")])]),v._v(" "),t("div",{staticClass:"line-numbers-wrapper"},[t("span",{staticClass:"line-number"},[v._v("1")]),t("br"),t("span",{staticClass:"line-number"},[v._v("2")]),t("br"),t("span",{staticClass:"line-number"},[v._v("3")]),t("br"),t("span",{staticClass:"line-number"},[v._v("4")]),t("br"),t("span",{staticClass:"line-number"},[v._v("5")]),t("br"),t("span",{staticClass:"line-number"},[v._v("6")]),t("br"),t("span",{staticClass:"line-number"},[v._v("7")]),t("br"),t("span",{staticClass:"line-number"},[v._v("8")]),t("br"),t("span",{staticClass:"line-number"},[v._v("9")]),t("br")])])])]),v._v(" "),t("ul",[t("li",[t("strong",[v._v("描述")]),v._v("：服务提供方证书，用于异步请求应答接口时校验身份使用。需要服务提供方提供")])]),v._v(" "),t("blockquote",[t("blockquote",[t("ul",[t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])])]),v._v(" "),t("h3",{attrs:{id:"_2-middleware"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#_2-middleware"}},[v._v("#")]),v._v(" 2. middleware")]),v._v(" "),t("blockquote",[t("p",[v._v("中间件相关配置。")])]),v._v(" "),t("h4",{attrs:{id:"file"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#file"}},[v._v("#")]),v._v(" file")]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("描述")]),v._v("：文件配置")])]),v._v(" "),t("p",[t("strong",[v._v("temp")])]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：/file/tmp")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：临时文件目录，此处必须填写绝对路径，默认路径为中间目录下 "),t("code",[v._v("file/tmp")])]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 非必填")])])]),v._v(" "),t("p",[t("strong",[v._v("backupPath")])]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：/file/backup")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：持久化备份文件目录，此处必须填写绝对路径，默认路径为中间目录下 "),t("code",[v._v("file/backup")])]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 非必填")])])])]),v._v(" "),t("h4",{attrs:{id:"log"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#log"}},[v._v("#")]),v._v(" log")]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("描述")]),v._v("： 日志相关配置")])]),v._v(" "),t("p",[t("strong",[v._v("path")])]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：/data/logs")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：日志保存路径")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 非必填")])])])]),v._v(" "),t("h4",{attrs:{id:"recclient"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#recclient"}},[v._v("#")]),v._v(" recClient")]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("描述")]),v._v("：中间件访问用户的服务的客户端")])]),v._v(" "),t("p",[t("strong",[v._v("host")])]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：127.0.0.1")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：提供服务或应答的地址，由于和宿主服务部署在同一个服务器上此处填写127.0.0.1")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])]),v._v(" "),t("p",[t("strong",[v._v("port")])]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：80")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：提供服务或应答的端口")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])]),v._v(" "),t("p",[t("strong",[v._v("protocol")])]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：http")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：协议，当前版本暂时只支持http")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])]),v._v(" "),t("p",[t("strong",[v._v("timeout")])]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：5000")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：访问服务的超时时间，单位为毫秒")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])])]),v._v(" "),t("h4",{attrs:{id:"recserver"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#recserver"}},[v._v("#")]),v._v(" recServer")]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("描述")]),v._v("：宿主服务调用中间件服务的相关配置，此处提供的为http服务。")])]),v._v(" "),t("p",[t("strong",[v._v("port")])]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：8888")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：提供的http服务的端口")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])]),v._v(" "),t("p",[t("strong",[v._v("corePoolSize")])]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：50")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：初始线程池大小")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])]),v._v(" "),t("p",[t("strong",[v._v("maxPoolSize")])]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：200")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：最大线程池大小")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])]),v._v(" "),t("p",[t("strong",[v._v("workQueue")])]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：100")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：队列，用来存储未执行的线程")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])])]),v._v(" "),t("h4",{attrs:{id:"comclient"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#comclient"}},[v._v("#")]),v._v(" comClient")]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("描述")]),v._v("：中间件访问其他中间件的客户端。")])]),v._v(" "),t("p",[t("strong",[v._v("timeout")])]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：5000")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：超时时间（毫秒）")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])]),v._v(" "),t("p",[t("strong",[v._v("maxTotal")])]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：8")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：池中最大连接数")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])]),v._v(" "),t("p",[t("strong",[v._v("minIdle")])]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：1")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：最少的空闲连接数")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])]),v._v(" "),t("p",[t("strong",[v._v("maxIdle")])]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：8")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：最多的空闲连接数")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("：必填")])])]),v._v(" "),t("p",[t("strong",[v._v("maxWaitMillis")])]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：-1")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：当连接池资源耗尽时,调用者最大阻塞的时间,超时时抛出异常 单位:毫秒数")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("：必填")])])]),v._v(" "),t("p",[t("strong",[v._v("lifo")])]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：true")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：连接池存放池化对象方式,true放在空闲队列最前面,false放在空闲队列最后")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("：必填")])])]),v._v(" "),t("p",[t("strong",[v._v("minEvictableIdleTimeMillis")])]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：1800000")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：连接空闲的最小时间,达到此值后空闲连接可能会被移除,默认即为30分钟")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("：必填")])])]),v._v(" "),t("p",[t("strong",[v._v("blockWhenExhausted")])]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：true")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：连接耗尽时是否阻塞,默认为true")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("：必填")])])])]),v._v(" "),t("h4",{attrs:{id:"comserver"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#comserver"}},[v._v("#")]),v._v(" comServer")]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("描述")]),v._v("：中间件提供其他中间件访问的服务，默认使用Grpc协议。")])]),v._v(" "),t("p",[t("strong",[v._v("port")])]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：8888")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：提供的http服务的端口")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])]),v._v(" "),t("p",[t("strong",[v._v("corePoolSize")])]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：50")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：初始线程池大小")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])]),v._v(" "),t("p",[t("strong",[v._v("maxPoolSize")])]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：200")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：最大线程池大小")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])]),v._v(" "),t("p",[t("strong",[v._v("workQueue")])]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：100")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：队列，用来存储未执行的线程")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])])]),v._v(" "),t("h3",{attrs:{id:"_3-datasource"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#_3-datasource"}},[v._v("#")]),v._v(" 3. datasource")]),v._v(" "),t("h4",{attrs:{id:"url"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#url"}},[v._v("#")]),v._v(" url")]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：jdbc:sqlite:data/repchain_mid.db")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：此处可以填写sqlite或mysql，其他数据库暂未支持，sqlite默认填写为中间件文件夹相对路径。")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])]),v._v(" "),t("h4",{attrs:{id:"user"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#user"}},[v._v("#")]),v._v(" user")]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：test")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：数据库连接用户名")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])]),v._v(" "),t("h4",{attrs:{id:"pass"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#pass"}},[v._v("#")]),v._v(" pass")]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：123456")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：数据库连接密码")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])]),v._v(" "),t("h4",{attrs:{id:"driver"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#driver"}},[v._v("#")]),v._v(" driver")]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：org.sqlite.JDBC")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：数据库驱动")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])]),v._v(" "),t("h4",{attrs:{id:"showsql"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#showsql"}},[v._v("#")]),v._v(" showSql")]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：true")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：是否在日志中显示执行的SQL")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])]),v._v(" "),t("h4",{attrs:{id:"formatsql"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#formatsql"}},[v._v("#")]),v._v(" formatSql")]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：true")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：是否格式化显示的SQL")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])]),v._v(" "),t("h4",{attrs:{id:"showparams"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#showparams"}},[v._v("#")]),v._v(" showParams")]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：true")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：是否显示SQL参数")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])]),v._v(" "),t("h4",{attrs:{id:"sqllevel"}},[t("a",{staticClass:"header-anchor",attrs:{href:"#sqllevel"}},[v._v("#")]),v._v(" sqlLevel")]),v._v(" "),t("blockquote",[t("ul",[t("li",[t("strong",[v._v("示例")]),v._v("：debug")]),v._v(" "),t("li",[t("strong",[v._v("描述")]),v._v("：打印SQL的日志等级，默认debug，可以是info、warn、error")]),v._v(" "),t("li",[t("strong",[v._v("约束")]),v._v("： 必填")])])])])}),[],!1,null,null,null);_.default=r.exports}}]);