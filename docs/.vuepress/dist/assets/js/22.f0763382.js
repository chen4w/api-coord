(window.webpackJsonp=window.webpackJsonp||[]).push([[22,25],{606:function(n,e,t){"use strict";t.r(e);var i=t(509),o={props:{showData:{default:null}},data:function(){return{}},mounted:function(){this.showData&&this.showBpmn(this.showData)},methods:{showBpmn:function(n){var e=this;this.bpmnViewer&&this.bpmnViewer.destroy(),this.bpmnViewer=new i.a({container:this.$refs.showCanvas}),this.bpmnViewer.importXML(n,(function(n){n?e.$message({message:"打开模型出错,请确认该模型符合Bpmn2.0规范",type:"error"}):e.bpmnViewer.get("canvas").zoom("fit-viewport","auto")}))}}},s=t(32),a=Object(s.a)(o,(function(){var n=this.$createElement;return(this._self._c||n)("div",{ref:"showCanvas"})}),[],!1,null,null,null);e.default=a.exports},800:function(n,e,t){"use strict";t.r(e);var i={components:{bpmnView:t(606).default},data:function(){return{dialogShowBpmnVisible:!1,showBpmnData:'\n        <?xml version="1.0" encoding="UTF-8"?>\n        <definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI" xmlns:omgdc="http://www.omg.org/spec/DD/20100524/DC" xmlns:camunda="http://camunda.org/schema/1.0/bpmn" xmlns:di="http://www.omg.org/spec/DD/20100524/DI" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:activiti="http://activiti.org/bpmn" id="m1577635100724" name="" targetNamespace="http://www.activiti.org/testm1577635100724">\n        <process id="process1" name="流程图1" processType="None" isClosed="false" isExecutable="true">\n            <extensionElements>\n            <camunda:properties />\n            </extensionElements>\n            <startEvent id="_2" name="start">\n            <outgoing>Flow_1mn3ncy</outgoing>\n            </startEvent>\n            <sequenceFlow id="Flow_1mn3ncy" sourceRef="_2" targetRef="Activity_03u7gdx" />\n            <endEvent id="Event_1jrsaua">\n            <incoming>Flow_0wm6kz4</incoming>\n            </endEvent>\n            <sequenceFlow id="Flow_0wm6kz4" sourceRef="Activity_03u7gdx" targetRef="Event_1jrsaua" />\n            <userTask id="Activity_03u7gdx">\n            <incoming>Flow_1mn3ncy</incoming>\n            <outgoing>Flow_0wm6kz4</outgoing>\n            </userTask>\n        </process>\n        <bpmndi:BPMNDiagram id="BPMNDiagram_leave">\n            <bpmndi:BPMNPlane id="BPMNPlane_leave" bpmnElement="process1">\n            <bpmndi:BPMNEdge id="Flow_1mn3ncy_di" bpmnElement="Flow_1mn3ncy">\n                <di:waypoint x="176" y="384" />\n                <di:waypoint x="230" y="384" />\n            </bpmndi:BPMNEdge>\n            <bpmndi:BPMNEdge id="Flow_0wm6kz4_di" bpmnElement="Flow_0wm6kz4">\n                <di:waypoint x="330" y="384" />\n                <di:waypoint x="392" y="384" />\n            </bpmndi:BPMNEdge>\n            <bpmndi:BPMNShape id="BPMNShape__2" bpmnElement="_2">\n                <omgdc:Bounds x="144" y="368" width="32" height="32" />\n                <bpmndi:BPMNLabel>\n                <omgdc:Bounds x="149" y="400" width="23" height="14" />\n                </bpmndi:BPMNLabel>\n            </bpmndi:BPMNShape>\n            <bpmndi:BPMNShape id="Event_1jrsaua_di" bpmnElement="Event_1jrsaua">\n                <omgdc:Bounds x="392" y="366" width="36" height="36" />\n            </bpmndi:BPMNShape>\n            <bpmndi:BPMNShape id="Activity_0f4773x_di" bpmnElement="Activity_03u7gdx">\n                <omgdc:Bounds x="230" y="344" width="100" height="80" />\n            </bpmndi:BPMNShape>\n            </bpmndi:BPMNPlane>\n        </bpmndi:BPMNDiagram>\n        </definitions>\n      '}},methods:{showBpmn:function(){this.dialogShowBpmnVisible=!0}}},o=t(32),s=Object(o.a)(i,(function(){var n=this,e=n.$createElement,t=n._self._c||e;return t("div",[t("el-button",{attrs:{type:"primary"},on:{click:function(e){return n.showBpmn()}}},[n._v("\n    查看流程图\n  ")]),n._v(" "),t("el-dialog",{attrs:{title:"流程图",visible:n.dialogShowBpmnVisible},on:{"update:visible":function(e){n.dialogShowBpmnVisible=e}}},[t("bpmnView",{attrs:{"show-data":n.showBpmnData}})],1)],1)}),[],!1,null,null,null);e.default=s.exports}}]);