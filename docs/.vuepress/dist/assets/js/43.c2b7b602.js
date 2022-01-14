(window.webpackJsonp=window.webpackJsonp||[]).push([[43],{817:function(t,a,s){"use strict";s.r(a);var v=s(32),_=Object(v.a)({},(function(){var t=this,a=t.$createElement,s=t._self._c||a;return s("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[s("h1",{attrs:{id:"服务登记"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#服务登记"}},[t._v("#")]),t._v(" 服务登记")]),t._v(" "),s("div",{staticClass:"custom-block tip"},[s("p",{staticClass:"custom-block-title"},[t._v("提示")]),t._v(" "),s("p",[t._v("请先注册RepChain账号，并登录到RepChain-Dashboard管理控制台页面。")]),t._v(" "),s("p",[t._v("接口协同所有操作均以此为前提。")])]),t._v(" "),s("div",{staticClass:"custom-block warning"},[s("p",{staticClass:"custom-block-title"},[t._v("注意")]),t._v(" "),s("ol",[s("li",[s("p",[t._v("服务登记分为服务提供方服务登记，服务调用方（调用登记）两种。")])]),t._v(" "),s("li",[s("p",[t._v("服务登记需要填写的表单内容完全一致，只是区分提供方与调用方。")])]),t._v(" "),s("li",[s("p",[t._v("其中，提供方可发布公告，而调用方只能查看公告，不可发布公告。")])]),t._v(" "),s("li",[s("p",[t._v("服务登记时，需要已经在接口定义定义好相关内容，否则无法提交服务登记")])])])]),t._v(" "),s("h2",{attrs:{id:"一、描述"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#一、描述"}},[t._v("#")]),t._v(" 一、描述")]),t._v(" "),s("blockquote",[s("p",[t._v("服务登记和服务调用，是为了服务方和调用方进行登记存证。")]),t._v(" "),s("p",[t._v("存证信息包含地址，端口号及服务定义，这样调用方即可知道需要调用哪些服务，而服务方可以知道有哪些调用方会调用自己的服务。")]),t._v(" "),s("p",[t._v("同时，由于有统一的接口定义规范约束，也防止了文档及定义不清晰的问题。服务方和调用方进行登记后，可以清晰的根据接口定义而实现相关接口及业务逻辑。")])]),t._v(" "),s("h2",{attrs:{id:"二、服务登记入口"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#二、服务登记入口"}},[t._v("#")]),t._v(" 二、服务登记入口")]),t._v(" "),s("p",[s("img",{attrs:{src:"/api-coord/img/register-user/register-menu.png",alt:"router"}})]),t._v(" "),s("p",[t._v("如图：")]),t._v(" "),s("ul",[s("li",[t._v("菜单中服务登记为服务提供方登记界面。")]),t._v(" "),s("li",[t._v("菜单中服务调用为服务调用方登记界面。")])]),t._v(" "),s("h2",{attrs:{id:"三、使用示例"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#三、使用示例"}},[t._v("#")]),t._v(" 三、使用示例")]),t._v(" "),s("p",[s("img",{attrs:{src:"/api-coord/img/register-user/register.gif",alt:"ro"}})]),t._v(" "),s("h2",{attrs:{id:"四、表单说明"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#四、表单说明"}},[t._v("#")]),t._v(" 四、表单说明")]),t._v(" "),s("h3",{attrs:{id:"登记名称"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#登记名称"}},[t._v("#")]),t._v(" 登记名称")]),t._v(" "),s("blockquote",[s("p",[t._v("约束：（必填）（不可更改）(不可重复)")]),t._v(" "),s("p",[t._v("类型：（字符串输入框）")]),t._v(" "),s("p",[t._v("描述：用于展示的登记名称，由用户定义。")])]),t._v(" "),s("h3",{attrs:{id:"地址"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#地址"}},[t._v("#")]),t._v(" 地址")]),t._v(" "),s("blockquote",[s("p",[t._v("约束：（必填）")]),t._v(" "),s("p",[t._v("类型：（字符串输入框）")]),t._v(" "),s("p",[t._v("描述：登记方的服务器地址，请确保服务方或调用方可访问到此地址。")])]),t._v(" "),s("div",{staticClass:"custom-block warning"},[s("p",{staticClass:"custom-block-title"},[t._v("注意")]),t._v(" "),s("p",[t._v("如果使用"),s("RouterLink",{attrs:{to:"/mid/"}},[t._v("接入中间件")]),t._v("，需要填写"),s("RouterLink",{attrs:{to:"/mid/"}},[t._v("接入中间件")]),t._v("所在地址。")],1)]),t._v(" "),s("h3",{attrs:{id:"端口"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#端口"}},[t._v("#")]),t._v(" 端口")]),t._v(" "),s("blockquote",[s("p",[t._v("约束：（必填）（必须为数字）")]),t._v(" "),s("p",[t._v("类型：（数字输入框）")]),t._v(" "),s("p",[t._v("描述：地址对应的端口号。")])]),t._v(" "),s("div",{staticClass:"custom-block warning"},[s("p",{staticClass:"custom-block-title"},[t._v("注意")]),t._v(" "),s("p",[t._v("如果使用"),s("RouterLink",{attrs:{to:"/mid/"}},[t._v("接入中间件")]),t._v("，需要填写配置文件中 "),s("code",[t._v("middleware.comServer.port")]),t._v(" 所配置的端口号。")],1)]),t._v(" "),s("h3",{attrs:{id:"服务名"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#服务名"}},[t._v("#")]),t._v(" 服务名")]),t._v(" "),s("blockquote",[s("p",[t._v("约束：（必填）")]),t._v(" "),s("p",[t._v("类型：（选择框）")]),t._v(" "),s("p",[t._v("描述：选择需要登记的服务名，选择后调用方需要实现接口登记中接口定义的内容。若为异步接口调用，则调用方需要实现应答方接口定义的内容。")])])])}),[],!1,null,null,null);a.default=_.exports}}]);