<template>
  <div>
    <el-button
      type="primary"
      @click="showBpmn()"
    >
      查看流程图
    </el-button>
    <el-dialog
      title="流程图"
      :visible.sync="dialogShowBpmnVisible"
    >
      <bpmnView :show-data="showBpmnData" />
    </el-dialog>
  </div>
</template>

<script>
import bpmnView from '../Bpmn/bpmnView'
export default {
  components: {
    bpmnView
  },
  data () {
    return {
      dialogShowBpmnVisible: false,
      showBpmnData: `
        <?xml version="1.0" encoding="UTF-8"?>
        <definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI" xmlns:omgdc="http://www.omg.org/spec/DD/20100524/DC" xmlns:camunda="http://camunda.org/schema/1.0/bpmn" xmlns:di="http://www.omg.org/spec/DD/20100524/DI" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:activiti="http://activiti.org/bpmn" id="m1577635100724" name="" targetNamespace="http://www.activiti.org/testm1577635100724">
        <process id="process1" name="流程图1" processType="None" isClosed="false" isExecutable="true">
            <extensionElements>
            <camunda:properties />
            </extensionElements>
            <startEvent id="_2" name="start">
            <outgoing>Flow_1mn3ncy</outgoing>
            </startEvent>
            <sequenceFlow id="Flow_1mn3ncy" sourceRef="_2" targetRef="Activity_03u7gdx" />
            <endEvent id="Event_1jrsaua">
            <incoming>Flow_0wm6kz4</incoming>
            </endEvent>
            <sequenceFlow id="Flow_0wm6kz4" sourceRef="Activity_03u7gdx" targetRef="Event_1jrsaua" />
            <userTask id="Activity_03u7gdx">
            <incoming>Flow_1mn3ncy</incoming>
            <outgoing>Flow_0wm6kz4</outgoing>
            </userTask>
        </process>
        <bpmndi:BPMNDiagram id="BPMNDiagram_leave">
            <bpmndi:BPMNPlane id="BPMNPlane_leave" bpmnElement="process1">
            <bpmndi:BPMNEdge id="Flow_1mn3ncy_di" bpmnElement="Flow_1mn3ncy">
                <di:waypoint x="176" y="384" />
                <di:waypoint x="230" y="384" />
            </bpmndi:BPMNEdge>
            <bpmndi:BPMNEdge id="Flow_0wm6kz4_di" bpmnElement="Flow_0wm6kz4">
                <di:waypoint x="330" y="384" />
                <di:waypoint x="392" y="384" />
            </bpmndi:BPMNEdge>
            <bpmndi:BPMNShape id="BPMNShape__2" bpmnElement="_2">
                <omgdc:Bounds x="144" y="368" width="32" height="32" />
                <bpmndi:BPMNLabel>
                <omgdc:Bounds x="149" y="400" width="23" height="14" />
                </bpmndi:BPMNLabel>
            </bpmndi:BPMNShape>
            <bpmndi:BPMNShape id="Event_1jrsaua_di" bpmnElement="Event_1jrsaua">
                <omgdc:Bounds x="392" y="366" width="36" height="36" />
            </bpmndi:BPMNShape>
            <bpmndi:BPMNShape id="Activity_0f4773x_di" bpmnElement="Activity_03u7gdx">
                <omgdc:Bounds x="230" y="344" width="100" height="80" />
            </bpmndi:BPMNShape>
            </bpmndi:BPMNPlane>
        </bpmndi:BPMNDiagram>
        </definitions>
      `
    }
  },
  methods: {
    showBpmn () {
      this.dialogShowBpmnVisible = true
    }
  }
}
</script>
