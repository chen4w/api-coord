(window.webpackJsonp=window.webpackJsonp||[]).push([[16],{472:function(e,t,l){},484:function(e,t,l){"use strict";l(472)},490:function(e,t,l){"use strict";l.r(t);l(238);var n={name:"TableHeadCo",props:{formList:{type:Array,default:null},checkBoxVisible:{type:Boolean,default:!1},tableHeads:{type:Array},loading:{type:Boolean,default:!1},formatter:{type:Function},slotVisible:{type:Boolean,default:!1},number:{type:Boolean,default:!1},width:{type:Number,default:200}},methods:{handleSortChange:function(e){this.$emit("handle-sort-change",e)},handleSelectionChange:function(e){this.$emit("handle-selection-change",e)}}},a=(l(484),l(32)),o=Object(a.a)(n,(function(){var e=this,t=e.$createElement,l=e._self._c||t;return l("div",[l("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],ref:"commonTable",staticStyle:{width:"100%"},attrs:{id:"commonTable",data:e.formList,border:"","highlight-current-row":"","header-cell-style":{background:"#3e7eaf",color:"#fff",textAlign:"center",fontWeight:"bold"},"cell-style":{textAlign:"center"}},on:{"sort-change":e.handleSortChange,"selection-change":e.handleSelectionChange}},[e.checkBoxVisible?l("el-table-column",{attrs:{type:"selection"}}):e._e(),e._v(" "),l("el-table-column",{attrs:{type:"index",width:"60",label:"#"}}),e._v(" "),e._l(e.tableHeads,(function(t){return[1===t.isHead?l("el-table-column",{key:t.fieldName,attrs:{prop:t.fieldName,label:t.nameCn,formatter:e.formatter,width:t.commonWidth,sortable:""}}):e._e()]})),e._v(" "),e.slotVisible?l("el-table-column",{attrs:{label:"操作",width:e.width},scopedSlots:e._u([{key:"default",fn:function(t){return[e._t("default",null,{item:t})]}}],null,!0)}):e._e()],2)],1)}),[],!1,null,null,null);t.default=o.exports}}]);