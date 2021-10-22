<template>
  <div>
    <table-head-co
      ref="tableCo"
      :form-list="tableList"
      :table-heads="tableHead"
      :width="200"
      :check-box-visible="true"
      @handle-selection-change="handleSelectionChange"
    />
  </div>
</template>
<script>
import tableHeadCo from '../Co/tableHeadCo'
export default {
  components: {
    tableHeadCo
  },
  data(){
    return {
      tableHead: [
      { fieldName: 'date', nameCn: '日期', isHead: 1 },
      { fieldName: 'name', nameCn: '姓名', isHead: 1 },
      { fieldName: 'address', nameCn: '地址', isHead: 1 },
    ],
    tableList: [{
            date: '2016-05-02',
            name: '小明',
            address: '北京市海淀区中关村南四街'
          }, {
            date: '2016-05-04',
            name: '小明',
            address: '北京市海淀区中关村南四街'
          }, {
            date: '2016-05-01',
            name: '小明',
            address: '北京市海淀区中关村南四街'
          }, {
            date: '2016-05-03',
            name: '小明',
            address: '北京市海淀区中关村南四街'
          }]
    }
  },
  methods:{
    // 复选框改变监听事件
    handleSelectionChange(val){
      console.log("这里是checkBox获取的内容--->",val)
    }
  }
}
</script>
<style scoped>

</style>

