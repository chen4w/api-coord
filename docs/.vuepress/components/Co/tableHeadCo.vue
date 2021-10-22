ya<template>
  <div>
    <el-table
      id="commonTable"
      ref="commonTable"
      v-loading="loading"
      :data="formList"
      border
      highlight-current-row
      style="width: 100%;"
      :header-cell-style="{background: '#3e7eaf',color:'#fff',textAlign:'center',fontWeight:'bold'}"
      :cell-style="{textAlign:'center'}"
      @sort-change="handleSortChange"
      @selection-change="handleSelectionChange"
    >
      <el-table-column
        v-if="checkBoxVisible"
        type="selection"
      />
      <el-table-column
        type="index"
        width="60"
        :label="'#'"
      />
      <template v-for="col in tableHeads">
        <el-table-column
          v-if="col.isHead ===1"
          :key="col.fieldName"
          :prop="col.fieldName"
          :label="col.nameCn"
          :formatter="formatter"
          :width="col.commonWidth"
          sortable
        />
      </template>
      <el-table-column
        v-if="slotVisible"
        label="操作"
        :width="width"
      >
        <template
          v-slot="scope"
        >
          <slot :item="scope" />
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: 'TableHeadCo',
  props: {
    formList: {
      type: Array,
      default: null
    },
    checkBoxVisible: {
      type: Boolean,
      default: false
    },
    tableHeads: {
      type: Array
    },
    loading: {
      type: Boolean,
      default: false
    },
    formatter: {
      type: Function
    },
    slotVisible: {
      type: Boolean,
      default: false
    },
    number: {
      type: Boolean,
      default: false
    },
    width: {
      type: Number,
      default: 200
    }
  },
  methods: {
    handleSortChange (val) {
      this.$emit('handle-sort-change', val)
    },
    handleSelectionChange (val) {
      this.$emit('handle-selection-change', val)
    }
  }
}
</script>
<style lang="scss">
  .el-table th {
    background-color: #eeeeee;
    font-weight: 200;
  }
  .form-expend .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 25%;
  }
  .form-expend {
    font-size: 0;
    margin-left: 20px;
    .el-form-item__label {
      font-size: 12px;
      // color: $middleGray;
    }
  }
  .form-expend label {
    width: 90px;
    color: #99a9bf;
  }
  .table-dropdown{
    .el-table--medium th, .el-table--medium td {
      padding: 5px 0;
    }
    /*.el-table-column--selection{*/
    /*  display:none;*/
    /*}*/
  }

</style>
