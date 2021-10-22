<template>
  <div class="importSwagger" style="width: 80%;margin: 10px auto;text-align: center;">
    <div id="swagger-ui" >
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
      url: "/swagger/swagger.json",
      dom_id: '#swagger-ui',
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
