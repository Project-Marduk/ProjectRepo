<template id="hello-world">
  <h1 class="hello-world">Hello, world</h1>
</template>



<script>
Vue.component("hello-world", {template:"hello-world"})
console.log("hi")
</script>
<style>
.hello-world {
  color: darkred;
}
</style>