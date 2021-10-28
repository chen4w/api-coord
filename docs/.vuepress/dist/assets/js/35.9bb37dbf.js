(window.webpackJsonp=window.webpackJsonp||[]).push([[35],{793:function(t,a,v){"use strict";v.r(a);var _=v(28),r=Object(_.a)({},(function(){var t=this,a=t.$createElement,v=t._self._c||a;return v("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[v("h1",{attrs:{id:"引言"}},[v("a",{staticClass:"header-anchor",attrs:{href:"#引言"}},[t._v("#")]),t._v(" 引言")]),t._v(" "),v("div",{staticClass:"custom-block warning"},[v("p",{staticClass:"custom-block-title"},[t._v("注意")]),t._v(" "),v("p",[t._v("首次阅读本文档，请严格按照文档顺序进行阅读，以便可以更加全面的理解如何使用链外接口协同组件，谢谢。")])]),t._v(" "),v("div",{staticClass:"custom-block tip"},[v("p",{staticClass:"custom-block-title"},[t._v("提示")]),t._v(" "),v("p",[t._v("本项目主要参考了专利《一种可举证的接口协同方法及系统》，并通过RepChain区块链进行实现，提供了两种调用存证方式，分别为"),v("a",{attrs:{href:"https://gitee.com/BTAJL/api-coord/tree/http-lhc/",target:"_blank",rel:"noopener noreferrer"}},[t._v("HTTP调用"),v("OutboundLink")],1),t._v("和"),v("a",{attrs:{href:"https://gitee.com/BTAJL/api-coord/tree/master/",target:"_blank",rel:"noopener noreferrer"}},[t._v("GRPC调用"),v("OutboundLink")],1),t._v("，且均提供了代码示例。")])]),t._v(" "),v("h2",{attrs:{id:"一、为什么会有接口协同组件"}},[v("a",{staticClass:"header-anchor",attrs:{href:"#一、为什么会有接口协同组件"}},[t._v("#")]),t._v(" 一、为什么会有接口协同组件")]),t._v(" "),v("p",[t._v("在开发人员开发过程中，难免会遇到如下情况：")]),t._v(" "),v("ol",[v("li",[t._v("调用其他系统的接口时，需要双方协调。")]),t._v(" "),v("li",[t._v("接口提供方文档不清晰。")]),t._v(" "),v("li",[t._v("接口调用时，由于一些原因，调用方无法正确拿到调用数据，进而双方产生矛盾却无从考证。")])]),t._v(" "),v("h2",{attrs:{id:"二、链外接口协同组件"}},[v("a",{staticClass:"header-anchor",attrs:{href:"#二、链外接口协同组件"}},[t._v("#")]),t._v(" 二、链外接口协同组件")]),t._v(" "),v("p",[t._v("接口协同组件旨在解决上述等问题而产生，并提供如下方案：")]),t._v(" "),v("ol",[v("li",[t._v("规范约束了统一的IDL (接口描述语言)，给服务方在提供接口定义时提供了统一规范。")]),t._v(" "),v("li",[t._v("服务方及调用方均需要在区块链上进行登记，登记后按照接口描述语言进行实现即可完成调用。")]),t._v(" "),v("li",[t._v("接口调用时需要双方对数据进行签名，并存证于区块链上。保证了在接口调用中，若出现意料之外的问题时有据可查。")]),t._v(" "),v("li",[t._v("双方存证于区块链的数据均为传输数据的HASH取值，并且由双方进行签名后存证，保证了敏感数据的安全性和可靠性。")])]),t._v(" "),v("h2",{attrs:{id:"三、术语"}},[v("a",{staticClass:"header-anchor",attrs:{href:"#三、术语"}},[t._v("#")]),t._v(" 三、术语")]),t._v(" "),v("h3",{attrs:{id:"同步请求"}},[v("a",{staticClass:"header-anchor",attrs:{href:"#同步请求"}},[t._v("#")]),t._v(" 同步请求")]),t._v(" "),v("blockquote",[v("p",[t._v("指调用方调用服务方接口时，可直接通过当前请求获取数据。无需等待服务方推送。")])]),t._v(" "),v("h3",{attrs:{id:"异步请求"}},[v("a",{staticClass:"header-anchor",attrs:{href:"#异步请求"}},[t._v("#")]),t._v(" 异步请求")]),t._v(" "),v("blockquote",[v("p",[t._v("指调用方调用服务方接口时，服务方会返回一个应答信息，但应答信息中不包含调用方所需要的数据。")]),t._v(" "),v("p",[t._v("服务方需要通过访问调用方的接口进行数据推送，再返回调用方所需要的数据。此类场景常常出现在审批流程等业务中。")])]),t._v(" "),v("h3",{attrs:{id:"数据签名"}},[v("a",{staticClass:"header-anchor",attrs:{href:"#数据签名"}},[t._v("#")]),t._v(" 数据签名")]),t._v(" "),v("blockquote",[v("p",[t._v("通过RepChain注册的证书和私钥对数据进行签名，签名算法使用接口定义中所定义的算法。")])]),t._v(" "),v("h3",{attrs:{id:"数据验签"}},[v("a",{staticClass:"header-anchor",attrs:{href:"#数据验签"}},[t._v("#")]),t._v(" 数据验签")]),t._v(" "),v("blockquote",[v("p",[t._v("通过对签名数据的证书验证，判断数据签名是否由当前证书持有者进行签名。")])]),t._v(" "),v("h3",{attrs:{id:"交易对象"}},[v("a",{staticClass:"header-anchor",attrs:{href:"#交易对象"}},[t._v("#")]),t._v(" 交易对象")]),t._v(" "),v("blockquote",[v("p",[t._v("提交给区块链的数据结构，由区块链合约进行约束。与普通后台接口的接口请求参数类似，不同点在于需要签名后提交，且通过RepChain的Api提交给区块链。")])]),t._v(" "),v("h2",{attrs:{id:"四、-接口协同存证方式"}},[v("a",{staticClass:"header-anchor",attrs:{href:"#四、-接口协同存证方式"}},[t._v("#")]),t._v(" 四、 接口协同存证方式")]),t._v(" "),v("p",[t._v("当前版本接口协同存证方式，需要按照代码示例进行编码实现，提供了两种代码示例：")]),t._v(" "),v("ol",[v("li",[t._v("HTTP请求代码示例")]),t._v(" "),v("li",[t._v("GRPC代码示例")])]),t._v(" "),v("p",[t._v("具体代码示例的结构可通过"),v("RouterLink",{attrs:{to:"/pages/5536f8/"}},[t._v("此处")]),t._v("查看")],1),t._v(" "),v("h2",{attrs:{id:"五、http方式"}},[v("a",{staticClass:"header-anchor",attrs:{href:"#五、http方式"}},[t._v("#")]),t._v(" 五、HTTP方式")]),t._v(" "),v("p",[t._v("Http调用方式提供了如下几种代码示例：")]),t._v(" "),v("ol",[v("li",[v("p",[t._v("同步请求")]),t._v(" "),v("ul",[v("li",[t._v("同步单次调用服务方")])]),t._v(" "),v("blockquote",[v("p",[t._v("此类为最常见的场景，即请求后则得到返回数据。")])]),t._v(" "),v("ul",[v("li",[t._v("同步多次调用服务方")])]),t._v(" "),v("blockquote",[v("p",[t._v("此类场景为当数据量过大，一次请求无法获取全部所需数据时，需要多次请求服务方接口。")])])]),t._v(" "),v("li",[v("p",[t._v("异步请求")]),t._v(" "),v("ul",[v("li",[t._v("异步单次调用请求方，请求方返回单次数据")])]),t._v(" "),v("blockquote",[v("p",[t._v("此类场景为当调用服务方数据时，服务方需要有流程审批等原因，无法同步返回数据。此类情况需要在接口定义时，定义应答方接口规范，并让调用方进行接口实现。")])]),t._v(" "),v("ul",[v("li",[t._v("异步多次调用请求方，请求方多次返回数据")])]),t._v(" "),v("blockquote",[v("p",[t._v("此类场景为当调用服务方数据时，服务方需要有流程审批等原因，无法同步返回数据，且数据量较大。此类情况需要在接口定义时，定义应答方接口规范，并让应答方进行接口实现。")])])])])])}),[],!1,null,null,null);a.default=r.exports}}]);