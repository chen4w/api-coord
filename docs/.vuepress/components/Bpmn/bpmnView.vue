<template>
  <div ref="showCanvas" />
</template>

<script>
import BpmnViewer from 'bpmn-js/lib/Viewer'
export default {
  props: {
    showData: {
      default: null
    }
  },
  data () {
    return {}
  },
  mounted () {
    if (this.showData) {
      this.showBpmn(this.showData)
    }
  },
  methods: {
    showBpmn (showData) {
      this.bpmnViewer && this.bpmnViewer.destroy()
      this.bpmnViewer = new BpmnViewer({ container: this.$refs.showCanvas })
      this.bpmnViewer.importXML(showData, (err) => {
        if (err) {
          this.$message({
            message: '打开模型出错,请确认该模型符合Bpmn2.0规范',
            type: 'error'
          })
        } else {
          this.bpmnViewer.get('canvas').zoom('fit-viewport', 'auto') // 居中显示
        }
      })
    }
  }
}
</script>
