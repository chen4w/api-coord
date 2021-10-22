<template>
  <div>
    <bpmn :updateDate="updateDate" :updateBpmnFlag="updateBpmnFlag" />
  </div>
</template>

<script>
import bpmn from "../Bpmn/bpmn";
export default {
  components: {
    bpmn,
  },
  data() {
    return {
      updateDate: { key: "process", name: "流程图" },
      updateBpmnFlag: false
    };
  },
};
</script>
