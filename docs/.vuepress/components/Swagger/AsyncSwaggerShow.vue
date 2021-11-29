<template>
  <div class="importSwagger" style="width: 80%;margin: 10px auto;text-align: center;">
    <div id="swagger-ui-async" >
    </div>
  </div>
</template>
<script>
import { SwaggerUIBundle,SwaggerUIStandalonePreset } from 'swagger-ui-dist'
export default {
  data() {
    return {
    }
  },
  created() {
  },
  mounted() {
    const ui = SwaggerUIBundle({
      url: "https://btajl.gitee.io/api-coord/swagger/async.json",
      // url: "/swagger/async.json",
      dom_id: '#swagger-ui-async',
      presets: [
        SwaggerUIBundle.presets.apis,
        SwaggerUIStandalonePreset
      ],
      layout: "BaseLayout"
    })
  }
}
</script>
<style scoped>
@import "~swagger-ui-dist/swagger-ui.css";
</style>
