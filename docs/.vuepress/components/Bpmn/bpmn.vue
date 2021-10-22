<template>
  <div class="container">
    <div class="bpmn-container">
      <div ref="canvas" class="bpmn-canvas" />
      <div id="js-properties-panel" class="bpmn-js-properties-panel" />
    </div>

    <div class="action">
      <el-upload action class="upload-demo" :before-upload="openBpmn">
        <el-button icon="el-icon-folder-opened" />
      </el-upload>
      <el-button class="new" icon="el-icon-circle-plus" @click="newDiagram" />
      <el-button icon="el-icon-download" @click="downloadBpmn" />
      <el-button icon="el-icon-picture" @click="downloadSvg" />
      <el-button v-if="updateDate!=undefined" icon="el-icon-upload" @click="handleUpdateData" />
      <a ref="downloadLink" hidden />
    </div>
  </div>
</template>

<script>
import BpmnModeler from "bpmn-js/lib/Modeler";
import propertiesProviderModule from "bpmn-js-properties-panel/lib/provider/camunda";
import propertiesPanelModule from "bpmn-js-properties-panel";
import camundaModdleDescriptor from "camunda-bpmn-moddle/resources/camunda";
import customTranslate from "./customTranslate/customTranslate";

export default {
  name: "Bpmn",
  props: {
    updateBpmnFlag: {
      type: Boolean,
      default: false,
    },
    updateDate: {
      default: null,
    },
  },
  data() {
    return {
      bpmnModeler: null,
      canvas: null,
      bpmnTemplate: `
          <?xml version="1.0" encoding="UTF-8"?>
          <definitions
              xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL"
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
              xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI"
              xmlns:omgdc="http://www.omg.org/spec/DD/20100524/DC"
              xmlns:camunda="http://camunda.org/schema/1.0/bpmn"
              xmlns:xsd="http://www.w3.org/2001/XMLSchema"
              xmlns:activiti="http://activiti.org/bpmn"
              id="m1577635100724"
              name=""
              targetNamespace="http://www.activiti.org/testm1577635100724"
            >
            <process id="process" name="" processType="None" isClosed="false" isExecutable="true">
              <extensionElements>
                <camunda:properties>
                </camunda:properties>
              </extensionElements>
              <startEvent id="_2" name="start" />
            </process>
            <bpmndi:BPMNDiagram id="BPMNDiagram_leave">
              <bpmndi:BPMNPlane id="BPMNPlane_leave" bpmnElement="leave">
                <bpmndi:BPMNShape id="BPMNShape__2" bpmnElement="_2">
                  <omgdc:Bounds x="144" y="368" width="32" height="32" />
                  <bpmndi:BPMNLabel>
                    <omgdc:Bounds x="149" y="400" width="23" height="14" />
                  </bpmndi:BPMNLabel>
                </bpmndi:BPMNShape>
              </bpmndi:BPMNPlane>
            </bpmndi:BPMNDiagram>
          </definitions>
      `,
      bpmnNameFrom: null,
      editBpmnFile: null,
    };
  },
  mounted() {
    this.$nextTick(() => {
      if (this.updateDate != undefined) {
        this.initUpdateDate();
      }
      this.initBpmn();
    });
  },
  methods: {
    initUpdateDate() {
      if (this.updateBpmnFlag) {
        const updateDate = this.updateDate.replace(new RegExp("activiti:", "gm"), "camunda:");
        this.editBpmnFile = this.updateDate;
      } else {
        this.bpmnNameFrom = this.updateDate;
      }
    },
    async initBpmn() {
      this.canvas = this.$refs.canvas;
      const customTranslateModule = {
        translate: ["value", customTranslate],
      };
      this.bpmnModeler = new BpmnModeler({
        container: this.canvas,
        propertiesPanel: {
          parent: "#js-properties-panel"
        },
        additionalModules: [
          propertiesProviderModule,
          propertiesPanelModule,
          customTranslateModule,
        ],
        moddleExtensions: {
          camunda: camundaModdleDescriptor,
        },
      });

      if (this.updateBpmnFlag) {
        await this.createNewDiagram(this.editBpmnFile);
      } else {
        await this.createNewDiagram(this.bpmnTemplate);
      }
    },
    replaceString(xml, nodeName, property, propertyLength, replaceChar) {
      const node = xml.indexOf(nodeName);
      let xmlName = xml.substr(node, xml.indexOf(property));
      const begin = node + xmlName.indexOf(property) + propertyLength;
      xmlName = xmlName.substr(xmlName.indexOf(property) + propertyLength);

      const end = begin + xmlName.indexOf('"');

      const fstStr = xml.substring(0, begin);
      const lstStr = xml.substring(end, xml.length);
      const newXml = fstStr + replaceChar + lstStr;

      return newXml;
    },
    createNewDiagram(bpmn) {
      if (!this.updateBpmnFlag && this.updateDate != undefined) {
        bpmn = this.replaceString(
          bpmn,
          "process",
          "name",
          6,
          this.bpmnNameFrom.name
        );
        bpmn = this.replaceString(
          bpmn,
          "process",
          "id",
          4,
          this.bpmnNameFrom.key
        );
      }
      this.bpmnModeler.importXML(bpmn, (err) => {
        if (err) {
          this.$message({
            message: "打开模型出错,请确认该模型符合Bpmn2.0规范",
            type: "error",
          });
        }
      });
    },
    newDiagram() {
      this.createNewDiagram(this.bpmnTemplate);
    },

    downloadBpmn() {
      this.bpmnModeler.saveXML({ format: true }, (err, xml) => {
        if (!err) {
          const name = `${this.getFilename(xml)}.bpmn`;
          this.download({ name: name, data: xml });
        }
      });
    },
    downloadSvg() {
      this.bpmnModeler.saveXML({ format: true }, (err, xml) => {
        if (!err) {
          const name = `${this.getFilename(xml)}.svg`;

          let context = "";
          const djsGroupAll = this.$refs.canvas.querySelectorAll(".djs-group");
          for (const item of djsGroupAll) {
            context += item.innerHTML;
          }
          const viewport = this.$refs.canvas
            .querySelector(".viewport")
            .getBBox();

          const svg = `
            <svg
              xmlns="http://www.w3.org/2000/svg"
              xmlns:xlink="http://www.w3.org/1999/xlink"
              width="${viewport.width}"
              height="${viewport.height}"
              viewBox="${viewport.x} ${viewport.y} ${viewport.width} ${viewport.height}"
              version="1.1"
              >
              ${context}
            </svg>
          `;
          this.download({ name: name, data: svg });
        }
      });
    },

    openBpmn(file) {
      const reader = new FileReader();
      reader.readAsText(file, "utf-8");
      reader.onload = () => {
        this.createNewDiagram(reader.result);
      };
      return false;
    },

    getFilename(xml) {
      const start = xml.indexOf("process");
      let filename = xml.substr(start, xml.indexOf(">"));
      filename = filename.substr(filename.indexOf("id") + 4);
      filename = filename.substr(0, filename.indexOf('"'));
      return filename;
    },

    getFileInfo(xml, nodeName, property, propertyLength) {
      const start = xml.indexOf(nodeName);
      let fileInfo = xml.substr(start, xml.indexOf(">"));
      fileInfo = fileInfo.substr(fileInfo.indexOf(property) + propertyLength);
      fileInfo = fileInfo.substr(0, fileInfo.indexOf('"'));
      return;
    },

    download({ name = "diagram.bpmn", data }) {
      const downloadLink = this.$refs.downloadLink;
      data = data.replace(new RegExp("camunda:", "gm"), "activiti:");
      const encodedData = encodeURIComponent(data);

      if (data) {
        downloadLink.href =
          "data:application/bpmn20-xml;charset=UTF-8," + encodedData;
        downloadLink.download = name;
        downloadLink.click();
      }
    },

    handleUpdateData() {
      if (!this.updateBpmnFlag) {
        this.handleCreateData();
      } else {
        this.handleEditData();
      }
    },

    handleCreateData() {
      this.bpmnModeler.saveXML({ format: true }, async (err, xml) => {
        if (!err) {
          const fileName = `${this.getFileInfo(xml, "process", "name", 6)}`;
          const filekey = `${this.getFileInfo(xml, "process", "id", 4)}.bpmn`;
          xml = xml.replace(new RegExp("camunda:", "gm"), "activiti:");
          this.$emit("createData", xml, filekey, fileName);
        }
      });
    },

    handleEditData() {
      this.bpmnModeler.saveXML({ format: true }, async (err, xml) => {
        if (!err) {
          xml = xml.replace(new RegExp("camunda:", "gm"), "activiti:");
          this.$emit("editData", xml);
        }
      });
    },
  },
};
</script>

<style>
.bpmn-container {
  position: relative;
  width: 100%;
  height: 100vh;
  display: flex;
}

.bpmn-canvas {
  width: calc(100% - 300px);
  height: 100vh;
}

.bpmn-js-properties-panel {
  width: 320px;
  height: inherit;
  overflow-y: auto;
}

.action {
  position: absolute;
  right: 30rem;
  display: flex;
  margin-top: -40px;
}
.upload-demo {
  margin-right: 10px;
}
.bjs-powered-by {
  display: none;
}
</style>
