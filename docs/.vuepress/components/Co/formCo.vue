<template>
  <el-dialog
    ref="form"
    :title="formTitle"
    :visible.sync="formVisible"
    :close-on-click-modal="false"
    :before-close="beforeCancelForm"
  >
    <el-form
      ref="formCommon"
      :model="formData"
      label-width="130px"
      :rules="formRules"
    >
      <div
        v-for="col in formKey"
        :key="col.fieldName"
      >
        <component
          :is="'el-form-item'"
          v-if="col.isForm===1"
          :label="col.nameCn"
          :prop="col.fieldName"
        >
          <div>
            <component
              :is="col.webType"
              v-if="
                (col.javaType !== 'date' || isAdd) &&
                  col.webType !== 'textArea' && col.webType!=='password'
              "
              v-model="formData[col.fieldName]"
              filterable
              :type="col.javaType"
              style="width:80%"
              :value-format="timeFormat"
              :disabled="disFunction(col.isChange, col.fieldName)"
            >
              <div v-if="col.selectList">
                <component
                  :is="'el-option'"
                  v-for="(sel,val) in col.selectList"
                  :key="val"
                  :label="sel.selectKey"
                  :value="formatSelectVal(sel.selectValue,col.javaType)"
                />
              </div>
            </component>
            <el-input
              v-if="col.webType === 'textArea'"
              v-model="formData[col.fieldName]"
              style="width:80%"
              type="textarea"
              :disabled="disFunction(col.isChange, col.fieldName)"
            />
            <el-input
              v-if="col.webType === 'password'"
              v-model="formData[col.fieldName]"
              style="width:80%"
              type="password"
              :disabled="disFunction(col.isChange, col.fieldName)"
            />
            <el-col
              v-if="!isAdd && col.javaType === 'date'"
              :span="8"
            >
              <el-form-item prop="date1">
                <el-date-picker
                  v-model="formData[col.fieldName + '_start']"
                  type="datetime"
                  placeholder="选择时间"
                  style="width: 95%;"
                  :value-format="timeFormat"
                />
              </el-form-item>
            </el-col>
            <el-col
              v-if="!isAdd && col.javaType === 'date'"
              class="line"
              :span="1"
            >
              -
            </el-col>
            <el-col
              v-if="!isAdd && col.javaType === 'date'"
              :span="10"
            >
              <el-form-item prop="date2">
                <el-date-picker
                  v-model="formData[col.fieldName + '_end']"
                  :type="col.javaType"
                  placeholder="选择时间"
                  :value-format="timeFormat"
                  style="width: 80%;"
                />
              </el-form-item>
            </el-col>
          </div>
        </component>
      </div>
    </el-form>
    <div
      slot="footer"
      class="dialog-footer"
    >
      <el-button @click="cancelForm">
        取消
      </el-button>
      <el-button
        type="primary"
        @click="formSubmit('formCommon', formType)"
      >
        {{ formSubBotton }}
      </el-button>
    </div>
  </el-dialog>
</template>

<script>
export default {
  name: 'formCo',
  props: {
    timeFormat: {
      type: String,
      default: 'timestamp'
    },
    formTitle: {
      type: String
    },
    formData: {
      type: Object
    },
    formVisible: {
      type: Boolean
    },
    formKey: {
      type: Array
    },
    formSubBotton: {
      type: String
    },
    formRules: {
      type: Object
    },
    isAdd: {
      type: Boolean,
      default: true
    },
    formType: {
      type: Number
    },
    disableField: {
      type: Array
    }
  },
  methods: {
    disFunction (change, fieldName) {
      let flag = false
      this.disableField.forEach((value) => {
        if (value === fieldName) {
          flag = true
        }
      })
      if (this.formType === 1) {
        let flag = false
        this.disableField.forEach((value) => {
          if (value === fieldName) {
            flag = true
          }
        })
        return flag
      } else {
        if (change === 0) {
          return true
        } else {
          let flag = false
          this.disableField.forEach((value) => {
            if (value === fieldName) {
              flag = true
            }
          })
          return flag
        }
      }
    },
    formSubmit (val, type) {
      this.$emit('form-submit', val, type)
    },
    cancelForm () {
      this.$emit('cancel-form')
    },
    beforeCancelForm () {
      this.$emit('before-cancel-form')
    },
    formatSelectVal (val, type) {
      if (type === 'Integer') {
        return +val
      } else {
        return val
      }
    }
  }
}
</script>

