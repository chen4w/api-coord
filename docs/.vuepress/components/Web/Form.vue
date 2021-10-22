<template>
  <div>
    <el-button
      type="primary"
      size="medium"
      @click="showAdd"
    >
      新增表单
    </el-button>
    <form-co
      ref="formCo"
      :form-title="dialogTitle"
      :form-visible="dialogCreateFormVisible"
      :form-sub-botton="'提交'"
      :form-key="tableHead"
      :form-data="formData"
      :form-rules="formRules"
      :form-type="formType"
      :disable-field="disableField"
      @form-submit="formSubmit"
      @cancel-form="cancelForm"
      @before-cancel-form="cancelForm"
    />
  </div>
</template>
<script>
import formCo from '../Co/formCo'
export default {
  components: {
    formCo
  },
  data () {
    return {
      dialogTitle: '新增记录',
      dialogCreateFormVisible: false,
      tableHead: [
        {
          fieldId: 1,
          fieldName: 'user_id',
          isChange: 0,
          isForm: 0,
          javaType: 'Integer',
          nameCn: '用户ID',
          webType: 'el-input'
        },
        {
          fieldId: 2,
          fieldName: 'username',
          isChange: 1,
          isForm: 1,
          javaType: 'String',
          nameCn: '用户名',
          webType: 'el-input'
        },
        {
          fieldId: 3,
          fieldName: 'password',
          isChange: 1,
          isForm: 1,
          javaType: 'String',
          nameCn: '密码',
          webType: 'password'
        },
        {
          fieldId: 4,
          fieldName: 'enabled',
          isChange: 1,
          isForm: 1,
          javaType: 'Integer',
          nameCn: '用户状态',
          selectList: [
            {
              fieldId: 4,
              selectId: 1,
              selectKey: '禁用',
              selectType: 1,
              selectValue: '1'
            },
            {
              fieldId: 4,
              selectId: 2,
              selectKey: '启用',
              selectType: 1,
              selectValue: '2'
            }
          ],
          webType: 'el-select'
        },
        {
          fieldId: 5,
          fieldName: 'user_type',
          isChange: 1,
          isForm: 1,
          javaType: 'Integer',
          nameCn: '用户类型',
          selectList: [
            {
              fieldId: 5,
              selectId: 3,
              selectKey: '外部用户',
              selectType: 1,
              selectValue: '1'
            },
            {
              fieldId: 5,
              selectId: 4,
              selectKey: '内部用户',
              selectType: 1,
              selectValue: '2'
            }
          ],
          webType: 'el-select'
        },
        {
          fieldId: 6,
          fieldName: 'create_time',
          isChange: 1,
          isForm: 1,
          javaType: 'datetime',
          nameCn: '创建时间',
          webType: 'el-date-picker'
        },
        {
          fieldId: 7,
          fieldName: 'update_time',
          isChange: 1,
          isForm: 1,
          javaType: 'datetime',
          nameCn: '修改时间',
          webType: 'el-date-picker'
        }
      ],
      formData: {},
      formType: 1,
      disableField: ['create_time', 'update_time'],
      formRules: {
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' }
        ],
        username: [
          { required: true, message: '用户名不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    // 提交表单
    formSubmit (value) {
      this.dialogCreateFormVisible = false
    },
    // 关闭表单
    cancelForm () {
      this.dialogCreateFormVisible = false
      this.formData = {}
      this.$nextTick(() => {
        const ref = this.$refs.formCo
        ref.$refs.formCommon.resetFields()
      })
    },
    showAdd () {
      this.dialogTitle = '新增记录'
      this.formType = 1
      this.formData = {}
      this.dialogCreateFormVisible = true
    }
  }
}
</script>
