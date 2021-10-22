<template>
  <div>
    <table-head-co
      ref="tableCo"
      :form-list="tableList"
      :table-heads="tableHead"
      :width="200"
      :slot-visible="true"
    >
      <template
        v-slot="scope"
        style="text-align: center"
      >
        <el-button
          size="mini"
          @click="showEdit(scope.item.row)"
        >
          编辑
        </el-button>
      </template>
    </table-head-co>
  </div>
</template>
<script>
import tableHeadCo from '../Co/tableHeadCo'
export default {
  components: {
    tableHeadCo
  },
  data () {
    return {
      tableHead: [
        { fieldName: 'date', nameCn: '日期', isHead: 1, commonWidth: 120 },
        { fieldName: 'name', nameCn: '姓名', isHead: 1, commonWidth: 100 },
        { fieldName: 'address', nameCn: '地址', isHead: 1 }
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
  methods: {
    showEdit (val) {
      console.log('这里是插槽获取的内容--->', val)
    }
  }
}
</script>
<style scoped>

</style>

