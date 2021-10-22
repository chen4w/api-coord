<template>
  <el-row>
      <el-col :span="24">
        <el-card shadow="always" class="select-card">
          <search-co
            :form-key="tableHead"
            :search-data="searchForm"
            :time-format="'yyyy-MM-dd HH:mm:ss'"
            @search-submit="query"
            @reset-search-form="resetSearch"
          />
        </el-card>
      </el-col>
    </el-row>
</template>

<script>
import searchCo from "../Co/searchCo";
export default {
  components: {
    searchCo,
  },
  data() {
    return {
      tableHead: [
        {
          fieldId: 1,
          fieldName: "user_id",
          isChange: 0,
          isForm: 0,
          javaType: "Integer",
          nameCn: "用户ID",
          webType: "el-input",
        },
        {
          fieldId: 2,
          fieldName: "username",
          isChange: 1,
          isSearch: 1,
          javaType: "String",
          nameCn: "用户名",
          webType: "el-input",
        },
        {
          fieldId: 3,
          fieldName: "password",
          isChange: 1,
          isSearch: 0,
          javaType: "String",
          nameCn: "密码",
          webType: "password",
        },
        {
          fieldId: 4,
          fieldName: "enabled",
          isChange: 1,
          isSearch: 1,
          javaType: "Integer",
          nameCn: "用户状态",
          selectList: [
            {
              fieldId: 4,
              selectId: 1,
              selectKey: "禁用",
              selectType: 1,
              selectValue: "1",
            },
            {
              fieldId: 4,
              selectId: 2,
              selectKey: "启用",
              selectType: 1,
              selectValue: "2",
            },
          ],
          webType: "el-select",
        },
        {
          fieldId: 5,
          fieldName: "user_type",
          isChange: 1,
          isSearch: 1,
          javaType: "Integer",
          nameCn: "用户类型",
          selectList: [
            {
              fieldId: 5,
              selectId: 3,
              selectKey: "外部用户",
              selectType: 1,
              selectValue: "1",
            },
            {
              fieldId: 5,
              selectId: 4,
              selectKey: "内部用户",
              selectType: 1,
              selectValue: "2",
            },
          ],
          webType: "el-select",
        },
        {
          fieldId: 6,
          fieldName: "create_time",
          isChange: 1,
          isSearch: 1,
          javaType: "datetime",
          nameCn: "创建时间",
          webType: "el-date-picker",
        },
        {
          fieldId: 7,
          fieldName: "update_time",
          isChange: 1,
          isSearch: 1,
          javaType: "datetime",
          nameCn: "修改时间",
          webType: "el-date-picker",
        },
      ],
      searchForm: {},
    };
  },
  methods: {
    // 查询表格
    query() {},
    // 重置search表单
    resetSearch() {
      this.searchForm = {};
    },
  },
};
</script>